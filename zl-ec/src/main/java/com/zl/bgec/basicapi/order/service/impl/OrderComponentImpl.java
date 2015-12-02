package com.zl.bgec.basicapi.order.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.reflect.TypeToken;
import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.basecomponent.utils.ObjectConvertUtil;
import com.zl.bgec.basicapi.cart.dao.ICartItemDao;
import com.zl.bgec.basicapi.cart.po.CartItem;
import com.zl.bgec.basicapi.cart.vo.CartItemVo;
import com.zl.bgec.basicapi.commodity.dao.ICommodityCommentDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityDao;
import com.zl.bgec.basicapi.commodity.dao.IProductDao;
import com.zl.bgec.basicapi.commodity.po.Commodity;
import com.zl.bgec.basicapi.commodity.po.CommodityComment;
import com.zl.bgec.basicapi.commodity.po.Product;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.ResultVo;
import com.zl.bgec.basicapi.common.SendMessage;
import com.zl.bgec.basicapi.common.exception.OrderException;
import com.zl.bgec.basicapi.common.oauth2.SdkConstants;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;
import com.zl.bgec.basicapi.common.utils.ImageUtil;
import com.zl.bgec.basicapi.order.common.OrderConstants;
import com.zl.bgec.basicapi.order.common.OrderUtils;
import com.zl.bgec.basicapi.order.dao.IOrderAddressDao;
import com.zl.bgec.basicapi.order.dao.IOrderCouponsDao;
import com.zl.bgec.basicapi.order.dao.IOrderDao;
import com.zl.bgec.basicapi.order.dao.IOrderItemDao;
import com.zl.bgec.basicapi.order.dao.IOrderLogDao;
import com.zl.bgec.basicapi.order.dao.IOrderReturnDao;
import com.zl.bgec.basicapi.order.job.OrderNoPayCleanJob;
import com.zl.bgec.basicapi.order.po.Order;
import com.zl.bgec.basicapi.order.po.OrderAddress;
import com.zl.bgec.basicapi.order.po.OrderCoupons;
import com.zl.bgec.basicapi.order.po.OrderItem;
import com.zl.bgec.basicapi.order.po.OrderLog;
import com.zl.bgec.basicapi.order.po.OrderReturn;
import com.zl.bgec.basicapi.order.service.IOrderComponent;
import com.zl.bgec.basicapi.order.vo.OrderPageFinder;
import com.zl.bgec.basicapi.order.vo.OrderReturnVo;
import com.zl.bgec.basicapi.order.vo.OrderVo;
import com.zl.bgec.basicapi.order.vo.TempOrderVo;
import com.zl.bgec.basicapi.promotion.dao.IPromotionDao;
import com.zl.bgec.basicapi.promotion.dao.IPromotionInfoDao;
import com.zl.bgec.basicapi.promotion.po.Promotion;
import com.zl.bgec.basicapi.promotion.po.PromotionInfo;
import com.zl.bgec.basicapi.promotion.vo.PromotionVo;
import com.zl.bgec.basicapi.shop.dao.IShopDao;
import com.zl.bgec.basicapi.shop.po.Shop;
import com.zl.bgec.basicapi.order.vo.ReturnOrderRecord;

@Service
public class OrderComponentImpl implements IOrderComponent {
	@Resource
	private IOrderDao orderDao;
	@Resource
	private SdkConstants sdkConstants;
	@Resource
	private ICommodityCommentDao commodityCommentDao;
	@Resource
	private IOrderAddressDao orderAddressDao;
	@Resource
	private IOrderLogDao orderLogDao;
	@Resource
	private IOrderItemDao orderItemDao;
	@Resource
	private IOrderCouponsDao orderCouponsDao;
	@Resource
	private IOrderReturnDao orderReturnDao;
	@Resource
	private ICartItemDao cartItemDao;
	@Resource
	private IProductDao productDao;
	@Resource
	private ICommodityDao commodityDao;
	@Resource
	private SendMessage sendMessage;
	@Resource
	private IPromotionDao promotionDao;
	@Resource
	private IPromotionInfoDao promotionInfoDao;
	@Resource
	private IShopDao shopDao;
	private static Logger log = Logger.getLogger(OrderComponentImpl.class);
	@Resource
	private ImageUtil imageUtil;

	@Value("${auto_receive_order}")
	private String autoReceiveOrder;
	@Value("${auto_cancel_order}")
	private String autoCancelOrder;
	@Value("${auto_close_order}")
	private String autoCloseOrder;

	@Override
	@Transactional(readOnly = true)
	public List<TempOrderVo> confirmOrder(List<CartItemVo> cartItemVos,
			String memberNo) throws Exception {
		List<TempOrderVo> tempOrders = new ArrayList<TempOrderVo>();
		if (cartItemVos != null && !cartItemVos.isEmpty()) {// 购物车根据店铺分组
			Set<String> set = new HashSet<String>();
			for (CartItemVo cartItemVo : cartItemVos) {
				set.add(cartItemVo.getShopNo());
			}
			if (set != null && !set.isEmpty()) {
				for (String shopNo : set) {
					TempOrderVo tempOrderVo = new TempOrderVo();
					tempOrderVo.setShopNo(shopNo);
					List<CartItem> cartItems = new ArrayList<CartItem>();
					for (CartItemVo cartItemVo : cartItemVos) {
						if (shopNo.equals(cartItemVo.getShopNo())) {
							CartItem cartItem = cartItemDao.get("itemNo",
									cartItemVo.getItemNo());
							cartItems.add(cartItem);
						}
					}
					tempOrderVo.setCartItems(cartItems);
					tempOrders.add(tempOrderVo);
				}
			}
		}
		if (tempOrders != null && !tempOrders.isEmpty()) {
			for (TempOrderVo tempOrderVo : tempOrders) {
				List<CartItem> cartItems = tempOrderVo.getCartItems();
				BigDecimal priceTotal = new BigDecimal(0);
				if (cartItems != null && !cartItems.isEmpty()) {
					for (CartItem cartItem : cartItems) {
						Product product = productDao.get("prodNo",
								cartItem.getProdNo());
						cartItem.setModel(product.getModel());
						BigDecimal b1 = new BigDecimal(cartItem.getQuantity());
						BigDecimal b2 = new BigDecimal(product.getPrice());
						priceTotal = priceTotal.add(b1.multiply(b2));
					}
					tempOrderVo.setCartItems(cartItems);
				}// 查询商品价格
				Shop shop = shopDao.get("shopNo", tempOrderVo.getShopNo());
				tempOrderVo.setShopName(shop.getShopName());
				tempOrderVo.setPriceTotal(priceTotal.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
				tempOrderVo.setDeliveryFee(shop.getDeliveryFee()==null?0d:shop.getDeliveryFee());
				tempOrderVo.setShopAddress(shop.getShopAddress());
				tempOrderVo.setShopPhone(shop.getPhone());
				BigDecimal b2 = new BigDecimal(shop.getDeliveryFee()==null?0d:shop.getDeliveryFee());
				tempOrderVo.setFeeTotal(priceTotal.add(b2).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());// 计算金额
				String sql = "select tp.least_amount leastAmount,"
						+ "tp.discount_amount discountAmount,"
						+ "tp.promotion_pic_url promotionPic,"
						+ "tp.promotion_type promotionType,"
						+ "tp.promotion_name promotionName,"
						+ "tpi.coup_no coupNo "
						+ " from tbl_promotion_info tpi left join tbl_promotion tp "
						+ " on tpi.promotion_no = tp.promotion_no "
						+ " where tpi.member_no=:memberNo and tp.end_time>now() "
						+ "and tp.status='2' and tpi.status='0' and tp.least_amount<=:leastAmount and (tpi.shop_no=:shopNo or tpi.shop_no='0000000')";
				Query query = promotionDao.createSQLQuery(sql);
				query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				query.setParameter("memberNo", memberNo);
				query.setParameter("leastAmount", priceTotal.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
				query.setParameter("shopNo", tempOrderVo.getShopNo());
				tempOrderVo.setPromotionInfos(query.list());
			}
		}
		return tempOrders;
	}
	@Override
	@Transactional(readOnly = true)
	public TempOrderVo confirmGroupbuyOrder(CartItemVo cartItemVo,
			String memberNo) throws Exception {
		TempOrderVo tempOrderVo = new TempOrderVo();

		List<CartItem> cartItems = new ArrayList<CartItem>();
		CartItem cartItem = new CartItem();
		Product product = productDao.get("prodNo",cartItemVo.getProdNo());
		int stock = this.getStock(product.getStock());
		int stockPreemption = this.getStockPreemption(product.getStockPreemption());
		int quantity = cartItemVo.getQuantity()==null?0:cartItemVo.getQuantity();
		int leaveStock = stock-stockPreemption;
		/*if(leaveStock < quantity){
			log.debug("confirmGroupbuyOrder-Don't have enough stock.stock="+stock+",stockPreemption="+stockPreemption+",quantity="+quantity);
			throw new OrderException("20001","库存不足,剩余库存"+leaveStock);
		}*/
		cartItem.setModel(product.getModel());
		cartItem.setCreateTime(new Date());
		Criteria criteria = promotionDao.createCriteria(Restrictions.eq("refCommoNo", product.getCommoNo()));
		criteria.add(Restrictions.eq("status", "2"));//上架状态
		criteria.add(Restrictions.le("startTime", new Date()));
		criteria.add(Restrictions.ge("endTime", new Date()));
		List<Promotion> promotions = criteria.list();
		if(promotions!=null&&!promotions.isEmpty()){
			Promotion promotion = promotions.get(0);
			Double groupBuyPrice = promotion.getDiscountAmount();
			cartItem.setGourpbuyPrice(groupBuyPrice);
			tempOrderVo.setPromotionNo(promotion.getPromotionNo());
		}else{
			throw new Exception("不存在的团购。");
		}
		BigDecimal priceTotal = new BigDecimal(0);
		BigDecimal b1 = new BigDecimal(cartItemVo.getQuantity());
		BigDecimal price = new BigDecimal(cartItem.getGourpbuyPrice());
		priceTotal = priceTotal.add(b1.multiply(price));
		cartItem.setPriceTotal(priceTotal.doubleValue());
		cartItem.setProdNo(product.getProdNo());
		cartItem.setProdName(product.getProdName());
		cartItem.setProdPic(product.getDefaultPic());
		cartItem.setQuantity(cartItemVo.getQuantity());
		Shop shop = shopDao.get("shopNo", product.getSellerNo());
		tempOrderVo.setShopName(shop.getShopName());
		cartItem.setShopNo(product.getShopNo());
		cartItem.setShopName(shop.getShopName());
		tempOrderVo.setShopNo(product.getSellerNo());

		// 查询商品价格
		tempOrderVo.setPriceTotal(priceTotal.doubleValue());
		tempOrderVo.setDeliveryFee(shop.getDeliveryFee()==null?0d:shop.getDeliveryFee());
		tempOrderVo.setShopAddress(shop.getShopAddress());
		tempOrderVo.setShopPhone(shop.getPhone());
		BigDecimal b2 = new BigDecimal(shop.getDeliveryFee()==null?0d:shop.getDeliveryFee());
		tempOrderVo.setFeeTotal(priceTotal.add(b2).doubleValue());// 计算金额
		cartItems.add(cartItem);
		tempOrderVo.setCartItems(cartItems);

		return tempOrderVo;
	}

	private int getStockPreemption(Integer stockPreemption) {
		return stockPreemption==null?0:stockPreemption;
	}
	private int getStock(Integer stock) {
		return stock==null?0:stock;
	}

	@Transactional
	@Override
	public Map<String,Object> generateGroupbuyOrder(TempOrderVo tempOrderVo, String buyerNo, String buyerNickName, String buyerPhone)
			throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();

		Order order = new Order();
		String orderNo = OrderUtils.genOrderNo();
		order.setBasicState(OrderConstants.BASIC_STATE_WAITING_PAY);
		order.setBuyerMessage(tempOrderVo.getBuyerMessage());
		order.setBuyerNickName(buyerNickName);
		order.setBuyerPhone(buyerPhone);
		order.setCreateTime(new Date());
		order.setDeleteFlag((byte)0);
		order.setBuyerNo(buyerNo);
		order.setOrderNo(orderNo);
		order.setDeliveryType(tempOrderVo.getDeliveryType());
		String promotionNo = tempOrderVo.getPromotionNo();
		order.setOrderCouponsNo(promotionNo);
		Promotion promotion = promotionDao.get("promotionNo",promotionNo);
		Order buyerOrder = this.getOrderByBuyer(promotionNo,buyerNo);
		if(buyerOrder!=null){
			log.info("generateGroupbuyOrder-user had join this group activity.buyerNo="+buyerNo+",promotionNo="+promotionNo+",orderNo="+buyerOrder.getOrderNo());
			throw new OrderException("20002","用户"+buyerNickName+"已参加此团购");
		}

		List<CartItem> list = tempOrderVo.getCartItems();
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		BigDecimal priceTotal = new BigDecimal(0);
		if(list!=null&&!list.isEmpty()){
			CartItem cartItem = list.get(0);
			OrderItem orderItem = new OrderItem();

			orderItem.setOrderNo(orderNo);
			orderItem.setBuyerNo(buyerNo);
			orderItem.setQuantity(cartItem.getQuantity());

			Product product = productDao.get("prodNo",cartItem.getProdNo());
			int stock = this.getStock(product.getStock());
			int stockPreemption = this.getStockPreemption(product.getStockPreemption());
			int leaveStock = stock-stockPreemption;
			int quantity = this.getQuantity(cartItem.getQuantity());
			if(leaveStock>=quantity){
				product.setStockPreemption(stockPreemption+cartItem.getQuantity());//预占库存
				TempProduct tempProduct = new TempProduct();
				tempProduct.setProdNo(cartItem.getProdNo());
				tempProduct.setQuantity(cartItem.getQuantity());
			}else{
				log.debug("generateGroupbuyOrder-Don't have enough stock.stock="+stock+",stockPreemption="+stockPreemption+",quantity="+quantity);
				throw new OrderException("20001","库存不足,剩余库存"+leaveStock);
			}
			Commodity commodity = commodityDao.get("commoNo",product.getCommoNo());
			//commodity.setSellNum(commodity.getSellNum()==null||commodity.getSellNum().intValue()==0?cartItem.getQuantity():commodity.getSellNum()+cartItem.getQuantity());
			//product.setSellNum(commodity.getSellNum()==null||commodity.getSellNum().intValue()==0?cartItem.getQuantity():commodity.getSellNum()+cartItem.getQuantity());
			productDao.update(product);
			//commodityDao.update(commodity);
			order.setShopNo(product.getSellerNo());

			orderItem.setCategory(commodity.getCatNo());
			orderItem.setCommodityNo(product.getCommoNo());
			orderItem.setItemNo(OrderUtils.genOrderItemNo());
			orderItem.setOrderNo(orderNo);
			BigDecimal bDiscountPrice = new BigDecimal(promotion.getDiscountAmount()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
			BigDecimal bPrice = new BigDecimal(product.getPrice()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
			BigDecimal bQuantity = new BigDecimal(cartItem.getQuantity()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
			order.setDiscountTotal(bPrice.subtract(bDiscountPrice).multiply(bQuantity).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
			orderItem.setPrice(product.getPrice());
			orderItem.setGroupbuyPrice(promotion.getDiscountAmount());//团购价
			orderItem.setProdName(product.getProdName());
			orderItem.setProdNo(product.getProdNo());
			orderItem.setProdPic(product.getDefaultPic());
			orderItem.setModel(product.getModel());
			orderItemDao.save(orderItem);
			Shop shop = shopDao.get("shopNo",product.getSellerNo());
			result.put("shopName", shop.getShopName());
			order.setDeliveryFeeTotal(shop.getDeliveryFee());
			priceTotal = priceTotal.add(bQuantity.multiply(bPrice)).setScale(2, BigDecimal.ROUND_HALF_DOWN);
			result.put("prodNum", cartItem.getQuantity());
		}

		if(order.getDeliveryType()!=null&&order.getDeliveryType().equals("2")){
			order.setDeliveryFeeTotal(0d);
		}

		BigDecimal deliveryFee = new BigDecimal(order.getDeliveryFeeTotal()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal discountFee = new BigDecimal(order.getDiscountTotal()==null?0:order.getDiscountTotal()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal feeTotal = priceTotal.add(deliveryFee).subtract(discountFee).setScale(2, BigDecimal.ROUND_HALF_DOWN);
		order.setFeeTotal(feeTotal.doubleValue());
		result.put("feeTotal", order.getFeeTotal());
		result.put("orderNo", orderNo);
		order.setOrderItems(orderItems);
		order.setPriceTotal(priceTotal.doubleValue());

		OrderAddress orderAddress = tempOrderVo.getOrderAddress();
		orderAddress.setOrderNo(orderNo);
		orderAddress.setCreateTime(new Date());
		orderAddress.setAddressNo(OrderUtils.genOrderNo());
		orderAddressDao.save(orderAddress);
		orderDao.save(order);
		saveOrderLog(order, "提交订单");

		return result;
	}
	private Order getOrderByBuyer(String promotionNo, String buyerNo) {
		Criteria criteria = orderDao.createCriteria(Restrictions.eq("orderCouponsNo", promotionNo));
		criteria.add(Restrictions.eq("buyerNo", buyerNo));
		criteria.add(Restrictions.ne("deleteFlag", (byte)1));
		criteria.add(Restrictions.ne("basicState", OrderConstants.BASIC_STATE_CLOSED));
		List<Order> orders = criteria.list();
		if(orders != null && !orders.isEmpty())
			return orders.get(0);
		return null;
	}
	private int getQuantity(Integer quantity) {
		return quantity==null?0:quantity;
	}
	@Transactional
	@Override
	public List<Map<String,Object>> generateOrder(List<TempOrderVo> tempOrderVos,String buyerNo,String buyerNickName,String buyerPhone) throws Exception{
		List<Map<String,Object>> results = new ArrayList<Map<String,Object>>();
		if(tempOrderVos!=null&&!tempOrderVos.isEmpty()){
			for (TempOrderVo tempOrderVo : tempOrderVos) {
				Map<String,Object> result = new HashMap<String, Object>();
				Order order = new Order();
				String orderNo = OrderUtils.genOrderNo();
				order.setBasicState(OrderConstants.BASIC_STATE_WAITING_PAY);
				order.setBuyerMessage(tempOrderVo.getBuyerMessage());
				order.setBuyerNickName(buyerNickName);
				order.setBuyerPhone(buyerPhone);
				order.setCreateTime(new Date());
				order.setDeleteFlag((byte)0);
				order.setBuyerNo(buyerNo);
				order.setOrderNo(orderNo);
				order.setDeliveryType(tempOrderVo.getDeliveryType());
				List<TempProduct> tempProducts = new ArrayList<TempProduct>();
				List<CartItem> list = tempOrderVo.getCartItems();
				List<OrderItem> orderItems = new ArrayList<OrderItem>();
				BigDecimal priceTotal = new BigDecimal(0);
				if(list!=null&&!list.isEmpty()){
					int prodNum = 0;
					for (CartItem cartItem : list) {
						OrderItem orderItem = new OrderItem();
						CartItem cartItemPo = cartItemDao.get("itemNo", cartItem.getItemNo());
						order.setShopNo(cartItemPo.getShopNo());

						orderItem.setOrderNo(orderNo);
						orderItem.setBuyerNo(buyerNo);
						orderItem.setQuantity(cartItemPo.getQuantity());

						Product product = productDao.get("prodNo",cartItemPo.getProdNo());
						Commodity commodity = commodityDao.get("commoNo",product.getCommoNo());
						//commodity.setSellNum(commodity.getSellNum()==null||commodity.getSellNum().intValue()==0?cartItemPo.getQuantity():commodity.getSellNum()+cartItemPo.getQuantity());
						//product.setSellNum(commodity.getSellNum()==null||commodity.getSellNum().intValue()==0?cartItemPo.getQuantity():commodity.getSellNum()+cartItemPo.getQuantity());
						Integer stock = product.getStock();
						stock = stock==null?0:stock;
						Integer stockPreemption = product.getStockPreemption();
						stockPreemption = stockPreemption==null?0:stockPreemption;
						if(stock-stockPreemption>=cartItemPo.getQuantity()){
							product.setStockPreemption(stockPreemption+cartItemPo.getQuantity());//预占库存
							TempProduct tempProduct = new TempProduct();
							tempProduct.setProdNo(cartItemPo.getProdNo());
							tempProduct.setQuantity(cartItemPo.getQuantity());
						}else{
							throw new Exception("有商品缺货。。");
						}
						productDao.update(product);
						//commodityDao.update(commodity);

						orderItem.setCategory(commodity.getCatNo());
						orderItem.setCommodityNo(product.getCommoNo());
						orderItem.setItemNo(OrderUtils.genOrderItemNo());
						orderItem.setOrderNo(orderNo);
						orderItem.setPrice(product.getPrice());
						orderItem.setProdName(product.getProdName());
						orderItem.setProdNo(product.getProdNo());
						orderItem.setProdPic(product.getDefaultPic());
						orderItem.setModel(product.getModel());
						orderItemDao.save(orderItem);
						Shop shop = shopDao.get("shopNo",cartItemPo.getShopNo());
						result.put("shopName", shop.getShopName());
						order.setDeliveryFeeTotal(shop.getDeliveryFee());
						BigDecimal b1 = new BigDecimal(cartItemPo.getQuantity()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
						BigDecimal b2 = new BigDecimal(product.getPrice()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
						priceTotal = priceTotal.add(b1.multiply(b2)).setScale(2, BigDecimal.ROUND_HALF_DOWN);
						prodNum = prodNum+cartItemPo.getQuantity();
						cartItemDao.delete(cartItemPo);
					}
					result.put("prodNum", prodNum);
				}

				String promotionNo = tempOrderVo.getPromotionNo();
				if(promotionNo!=null&&!promotionNo.equals("")){//设置订单优惠信息
					String sql = "select "
							+" tpi.coup_no coupNo, "
							+" tp.discount_amount discountAmount, "
							+" tp.least_amount leastAmount, "
							+" tp.promotion_type promotionType, "
							+" tp.promotion_name promotionName, "
							+" (CASE WHEN tpi.shop_no='0000000' THEN '2' ELSE '1' END) coupType, "
							+" tp.promotion_pic_url promotionPic "
							+" from tbl_promotion_info tpi left join "
							+ "tbl_promotion tp on tpi.promotion_no = tp.promotion_no where tpi.coup_no = :coupNo ";
					Query query = promotionDao.createSQLQuery(sql);
					query.setParameter("coupNo", promotionNo);
					query.setMaxResults(1);
					query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					List<Map<String,Object>> promotions = query.list();
					if(promotions!=null&&!promotions.isEmpty()){
						List<OrderCoupons> orderCoupons = new ArrayList<OrderCoupons>();
						OrderCoupons orderCoupon = new OrderCoupons();
						Map<String,Object> coupon = promotions.get(0);
						log.info("createOrder-coupon="+GsonUtil.toJson(coupon));
						orderCoupon.setCouponName(String.valueOf(coupon.get("promotionName")));
						orderCoupon.setCouponNo(String.valueOf(coupon.get("coupNo")));
						orderCoupon.setCouponPic(String.valueOf(coupon.get("promotionPic")));
						orderCoupon.setCouponType(String.valueOf(coupon.get("promotionType")));
						orderCoupon.setPromotionType(String.valueOf(coupon.get("coupType")));
						Object discountAmount = coupon.get("discountAmount");
						discountAmount = discountAmount==null?0:discountAmount;
						orderCoupon.setDiscountAmount(Double.valueOf(String.valueOf(discountAmount)));
						Object leastAmount = coupon.get("leastAmount");
						leastAmount = leastAmount==null?0:leastAmount;
						orderCoupon.setLeastAmount(Double.valueOf(String.valueOf(leastAmount)));
						orderCoupon.setOrderCouponsNo(OrderUtils.genOrderItemNo());
						orderCoupon.setOrderNo(orderNo);
						orderCoupons.add(orderCoupon);
						order.setOrderCoupons(orderCoupons);
						orderCouponsDao.save(orderCoupon);
						order.setDiscountTotal(orderCoupon.getDiscountAmount());
						PromotionInfo promotionInfo = promotionInfoDao.get("coupNo",promotionNo);
						promotionInfo.setMemberNo(buyerNo);
						promotionInfo.setUseTime(new Date());
						promotionInfo.setStatus("1");
						promotionInfoDao.update(promotionInfo);
					}
				}
				if(order.getDeliveryType()!=null&&order.getDeliveryType().equals("2")){
					order.setDeliveryFeeTotal(0d);
				}

				BigDecimal deliveryFee = new BigDecimal(order.getDeliveryFeeTotal()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
				BigDecimal discountFee = new BigDecimal(order.getDiscountTotal()==null?0:order.getDiscountTotal()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
				BigDecimal feeTotal = priceTotal.add(deliveryFee).subtract(discountFee).setScale(2, BigDecimal.ROUND_HALF_DOWN);
				order.setFeeTotal(feeTotal.doubleValue());
				result.put("feeTotal", order.getFeeTotal());
				result.put("orderNo", orderNo);
				order.setOrderItems(orderItems);
				order.setPriceTotal(priceTotal.doubleValue());

				OrderAddress orderAddress = tempOrderVo.getOrderAddress();
				orderAddress.setOrderNo(orderNo);
				orderAddress.setCreateTime(new Date());
				orderAddress.setAddressNo(OrderUtils.genOrderNo());
				orderAddressDao.save(orderAddress);
				orderDao.save(order);
				saveOrderLog(order, "提交订单");
				results.add(result);
			}
		}

		return results;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zl.bgec.basicapi.order.service.IOrderComponent#getOrders(com.zl.bgec
	 * .basicapi.order.vo.OrderVo)
	 */
	@Override
	@Transactional(readOnly = true)
	public PageFinder<Map<String, Object>> getMyOrders(OrderVo orderVo) {
		StringBuffer sql = new StringBuffer(
				"select tor.fee_total feeTotal,"
						+ " tor.buyer_no buyerNo,"
						+ " tor.buyer_nick_name buyerNickName,"
						+ " tor.buyer_phone buyerPhone,"
						+ " tor.order_coupons_no orderCouponsNo,"
						+ " tor.create_time createTime,"
						+ " tor.order_no orderNo,"
						+ " tor.buyer_msg buyerMsg,"
						+ " tor.delivery_fee_total deliveryFee,"
						+ " tor.delivery_type deliveryType,"
						+ " tor.basic_state basicState,"
						+ " tor.payment_time paymentTime,"
						
						+ " toa.real_name realName,"
						+ " toa.cellphone cellPhone,"
						+ " toa.address address,"
						+ " toa.super_date deliveryDate,"
						
						+ " tsi.shop_name shopName, "
						+ " tsi.merch_no memberNo, "//商家编号
						+ " tsi.phone shopPhone, "
						+ " tsi.shop_no shopNo"
						+ " from tbl_order tor left join tbl_shop_info tsi on tor.shop_no = tsi.shop_no "
						+ " left join tbl_order_address toa on tor.order_no=toa.order_no "
						+ " where tor.buyer_no=:memberNo and tor.delete_flag=:deleteFlag ");
		StringBuffer countSql = new StringBuffer(
				"select count(*) "
						+ " from tbl_order tor left join tbl_shop_info tsi on tor.shop_no = tsi.shop_no where tor.buyer_no=:memberNo and tor.delete_flag=:deleteFlag ");
		String queryCondition = orderVo.getQueryCondition();

		log.info("autoCancelOrder:"+autoCancelOrder+",autoReceiveOrder:"+autoReceiveOrder);

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("deleteFlag", "0");
		values.put("memberNo", orderVo.getBuyerNo());
		if(queryCondition==null||queryCondition.equals("")){
			String orderState = orderVo.getBasicState();
			if(orderState!=null){
				sql.append(" and tor.basic_state = :basicState ");
				countSql.append(" and tor.basic_state = :basicState ");
				values.put("basicState", orderState);
			}
		}else
			if (queryCondition.equals("1")) {//待付款
				sql.append(" and tor.basic_state = :basicState ");
				countSql.append(" and tor.basic_state = :basicState ");
				values.put("basicState", OrderConstants.BASIC_STATE_WAITING_PAY);
			} else if (queryCondition.equals("2")) {//待发货
				sql.append(" and tor.basic_state in ('"+OrderConstants.BASIC_STATE_WAITING_DELIVERY+"') ");
				countSql.append(" and tor.basic_state in ('"+OrderConstants.BASIC_STATE_WAITING_DELIVERY+"') ");
			} else if (queryCondition.equals("3")) {// 已发货
				sql.append(" and tor.basic_state in ('"+OrderConstants.BASIC_STATE_ALREADY_DELIVERY+"') ");
				countSql.append(" and tor.basic_state in ('"+OrderConstants.BASIC_STATE_ALREADY_DELIVERY+"') ");
			} else if(queryCondition.equals("5")) {//售后退款
				sql.append(" and tor.basic_state in ('"+OrderConstants.BASIC_STATE_COMPLETED+"',"+ "'"+OrderConstants.BASIC_STATE_ALREADY_REFUND+"','"
						+OrderConstants.BASIC_STATE_REFUND_APPLY+"') ");
				countSql.append(" and tor.basic_state in ('"+OrderConstants.BASIC_STATE_COMPLETED+"',"+ "'"+OrderConstants.BASIC_STATE_ALREADY_REFUND+"','"
						+OrderConstants.BASIC_STATE_REFUND_APPLY+"') ");
			}

		sql.append(" order by tor.create_time desc");
		Query query = orderDao.createSQLQuery(sql.toString(), values);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		Query queryCount = orderDao.createSQLQuery(countSql.toString(), values);
		BigInteger totalRowsObj = (BigInteger) queryCount.list().get(0);
		int totalRows = totalRowsObj.intValue();

		OrderPageFinder<Map<String, Object>> pageFinder = new OrderPageFinder<Map<String, Object>>(
				orderVo.getPageNo(), orderVo.getPageSize(), totalRows);
		query.setMaxResults(pageFinder.getPageSize());
		query.setFirstResult(pageFinder.getStartOfPage());
		List<Map<String, Object>> list = query.list();

		if (list != null && !list.isEmpty()) {
			for (Map<String, Object> map : list) {
				String orderNo = String.valueOf(map.get("orderNo"));
				String orderItemSql = "select toi.prod_name prodName,"
						+ " toi.prod_pic prodPic,"
						+ " toi.price price,"
						+ " toi.quantity quantity"
						+ " from tbl_order_item toi where toi.order_no=:orderNo";
				Query queryOrderItem = orderItemDao
						.createSQLQuery(orderItemSql);
				queryOrderItem.setParameter("orderNo", orderNo);
				queryOrderItem
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				map.put("orderItems", queryOrderItem.list());
				map.put("orderAddress", orderAddressDao.get("orderNo",orderNo));
				map.put("orderPromotions", orderCouponsDao.getList("orderNo", orderNo));

				this.processGroupOrder(map);
			}
		}
		pageFinder.setData(list);
		this.setOrderCount(pageFinder,1,orderVo.getBuyerNo(),"0");
		this.setOrderCount(pageFinder,2,orderVo.getBuyerNo(),"0");
		this.setOrderCount(pageFinder,3,orderVo.getBuyerNo(),"0");
		return pageFinder;
	}


	private void setOrderCount(OrderPageFinder<Map<String, Object>> pageFinder,int condition, String buyerNo, String deleteFlag) {
		StringBuffer countSql = new StringBuffer("select count(*) from tbl_order tor where tor.buyer_no=:memberNo and tor.delete_flag=:deleteFlag ");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("deleteFlag", deleteFlag);
		values.put("memberNo", buyerNo);

		if(condition == 1){
			countSql.append(" and tor.basic_state = :basicState ");
			values.put("basicState", OrderConstants.BASIC_STATE_WAITING_PAY);
			Query queryCount = orderDao.createSQLQuery(countSql.toString(), values);
			BigInteger totalRowsObj = (BigInteger) queryCount.list().get(0);
			int orderCount = totalRowsObj.intValue();
			pageFinder.setOrderCount1(orderCount);
		}
		else if(condition == 2){
			countSql.append(" and tor.basic_state in ('"+OrderConstants.BASIC_STATE_WAITING_DELIVERY+"') ");
			Query queryCount = orderDao.createSQLQuery(countSql.toString(), values);
			BigInteger totalRowsObj = (BigInteger) queryCount.list().get(0);
			int orderCount = totalRowsObj.intValue();
			pageFinder.setOrderCount2(orderCount);
		}else if(condition == 3){
			countSql.append(" and tor.basic_state in ('"+OrderConstants.BASIC_STATE_ALREADY_DELIVERY+"') ");
			Query queryCount = orderDao.createSQLQuery(countSql.toString(), values);
			BigInteger totalRowsObj = (BigInteger) queryCount.list().get(0);
			int orderCount = totalRowsObj.intValue();
			pageFinder.setOrderCount3(orderCount);
		}else if(condition == 4){

		}else if(condition == 5){

		}
	}
	private void processGroupOrder(Map<String, Object> map) {
		//String orderNo = String.valueOf(map.get("orderNo"));
		String promotionNo = (String) map.get("orderCouponsNo");
		if(promotionNo!=null&&!promotionNo.equals("")){
			Promotion prom = promotionDao.get("promotionNo",promotionNo);
			if(prom!=null&&prom.getPromotionType().equals("2")){//团购
				int coupCount = prom.getCoupCount()==null?0:prom.getCoupCount();
				int alreadyCount = prom.getAlreadyCount()==null?0:prom.getAlreadyCount();
				double discountAmount = prom.getDiscountAmount()==null?0:prom.getDiscountAmount();
				int limitCount = prom.getLimitCount()==null?0:prom.getLimitCount();

				map.put("orderType","2");//1-普通订单，2-团购订单
				map.put("alreadyCount",alreadyCount);//已成团数
				map.put("coupCount",coupCount);//成团数
				map.put("discountAmount",discountAmount);//团购价
				map.put("limitCount",limitCount);//每人限购数
				map.put("promStartTime",prom.getStartTime());//团购开始时间
				map.put("promEndTime",prom.getEndTime());//团购结束时间
				this.setOrderBasicState(map,prom);
				this.setGroupMsg(map,prom);
			}
		}
		else{
			map.put("orderType","1");//1-普通订单，2-团购订单
		}
	}

	private void setOrderBasicState(Map<String, Object> map, Promotion prom) {
		//Date startTime = prom.getStartTime();
		Date endTime = prom.getEndTime();
		Date nowTime = new Date();
		//boolean nowUpStart = nowTime.compareTo(startTime)>=0;
		boolean nowDownEnd = nowTime.compareTo(endTime)<=0;
		if(nowDownEnd&&OrderConstants.BASIC_STATE_WAITING_DELIVERY.equals(map.get("basicState"))){
			map.put("basicState", OrderConstants.BASIC_STATE_BOOKING);
		}
	}
	private void setGroupMsg(Map<String, Object> map, Promotion prom) {
		int coupCount = prom.getCoupCount()==null?0:prom.getCoupCount();
		int alreadyCount = prom.getAlreadyCount()==null?0:prom.getAlreadyCount();
		if(coupCount==alreadyCount){
			String groupMsg = coupCount+"件成团，组团成功";
			map.put("groupMsg",groupMsg);//团购描述
		}
		else{
			String groupMsg = coupCount+"件成团，已预订"+alreadyCount+"件";
			map.put("groupMsg",groupMsg);//团购描述
		}
	}

	@Override
	@Transactional(readOnly = true)
	public PageFinder<Map<String, Object>> getOrders(OrderVo orderVo) {
		String shopNo = orderVo.getShopNo();
		String queryCondition = orderVo.getQueryCondition();
		queryCondition = queryCondition==null?"":queryCondition;
		String buyerNickName = orderVo.getBuyerNickName();
		String prodName = orderVo.getProdName();
		String phone = orderVo.getPhone();
		String orderNo = orderVo.getOrderNo();
		StringBuffer sql = new StringBuffer();
		sql.append("select "
				+ "tor.buyer_no buyerNo,"
				+ "tor.order_coupons_no orderCouponsNo,"
				+ "tor.buyer_nick_name buyerName,"
				+ "tor.buyer_phone buyerPhone,"
				+ "tor.fee_total feeTotal,"
				+ "tor.create_time createTime,"
				+ "tor.basic_state basicState,"
				+ "tor.order_no orderNo,"
				+ "tor.delivery_type deliveryType,"
				+ "tor.payment_time paymentTime,"

				+ "toa.real_name realName,"
				+ "toa.cellphone cellPhone,"
				+ "toa.address address,"
				+ "toa.super_date deliveryDate"
				+ " from tbl_order tor left outer join"
				+ " tbl_order_address toa on tor.order_no=toa.order_no where  tor.delete_flag = :deleteFlag");
		StringBuffer countSql = new StringBuffer();
		countSql.append("select count(*) "
				+ " from tbl_order tor left outer join"
				+ " tbl_order_address toa on tor.order_no=toa.order_no where  tor.delete_flag = :deleteFlag ");

		Map<String, Object> values = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(shopNo)){
			sql.append(" and tor.shop_no=:shopNo ");
			countSql.append(" and tor.shop_no=:shopNo ");
			values.put("shopNo", shopNo);
		}

		values.put("deleteFlag", "0");
		if ("1".equals(queryCondition)) {//待发货
			sql.append(" and tor.basic_state = :basicState ");
			countSql.append(" and tor.basic_state = :basicState "); 
			values.put("basicState", OrderConstants.BASIC_STATE_WAITING_DELIVERY);
		} else if ("2".equals(queryCondition)) {//已发货
			sql.append(" and tor.basic_state = :basicState ");
			countSql.append(" and tor.basic_state = :basicState ");
			values.put("basicState", OrderConstants.BASIC_STATE_ALREADY_DELIVERY);
		} else if ("3".equals(queryCondition)) {// 已完成
			sql.append(" and tor.basic_state ='"+ OrderConstants.BASIC_STATE_COMPLETED + "' ");
			countSql.append(" and tor.basic_state ='"+ OrderConstants.BASIC_STATE_COMPLETED + "' ");
		} else if ("4".equals(queryCondition)) {//已关闭
			sql.append(" and tor.basic_state ='"+OrderConstants.BASIC_STATE_CLOSED+"' ");
			countSql.append(" and tor.basic_state ='"+OrderConstants.BASIC_STATE_CLOSED+"' ");
			//			values.put("basicState", queryCondition);
		}
		if (buyerNickName != null && !buyerNickName.equals("")) {
			sql.append(" and tor.buyer_nick_name = :buyerNickName ");
			countSql.append(" and tor.buyer_nick_name = :buyerNickName ");
			values.put("buyerNickName", buyerNickName);
		}
		if (prodName != null && !prodName.equals("")) {
			sql.append(" and toi.prod_name like :prodName ");
			countSql.append(" and toi.prod_name like :prodName ");
			values.put("prodName", "%" + prodName + "%");
		}
		if (phone != null && !phone.equals("")) {
			sql.append(" and toa.cellphone = :phone ");
			countSql.append(" and toa.cellphone = :phone ");
			values.put("phone", phone);
		}
		if (orderNo != null && !orderNo.equals("")) {
			sql.append(" and tor.order_no = :orderNo ");
			countSql.append(" and tor.order_no = :orderNo ");
			values.put("orderNo", orderNo);
		}
		Query query = orderDao.createSQLQuery(sql.toString(), values);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		Query queryCount = orderDao.createSQLQuery(countSql.toString(), values);
		BigInteger totals = (BigInteger) queryCount.list().get(0);
		int totalRows = totals.intValue();
		//int totalRows = queryCount.list().size();
		PageFinder<Map<String, Object>> pageFinder = new PageFinder<Map<String, Object>>(
				orderVo.getPageNo(), orderVo.getPageSize(), totalRows);
		query.setMaxResults(pageFinder.getPageSize());
		query.setFirstResult(pageFinder.getStartOfPage());

		List<Map<String, Object>> list = query.list();

		if (list != null && !list.isEmpty()) {
			for (Map<String, Object> map : list) {
				int quantity = this.getOrderQuantity((String) map.get("orderNo"));
				map.put("quantity", quantity);
				this.processGroupOrder(map);
			}
		}
		pageFinder.setData(list);
		return pageFinder;
	}

	private int getOrderQuantity(String orderNo) {
		List<OrderItem> items = this.orderItemDao.getList("orderNo", orderNo);
		int quantity = 0;
		if(items!=null&&!items.isEmpty()){
			for(OrderItem r:items){
				int iquan = r.getQuantity()==null?0:r.getQuantity();
				quantity+=iquan;
			}
		}
		return quantity;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zl.bgec.basicapi.order.service.IOrderComponent#getOrderDetail(com
	 * .zl.bgec.basicapi.order.vo.OrderVo)
	 */
	@Override
	@Transactional
	public Map<String, Object> getOrderDetail(OrderVo orderVo,String memberNo) {
		String orderNo = orderVo.getOrderNo();
		Order order = orderDao.get("orderNo", orderNo);

		Map<String,Object> map = GsonUtil.fromJson(GsonUtil.toJson(order),new TypeToken<Map<String, String>>() {}.getType());
		this.processGroupOrder(map);

		map.put("orderAddress", orderAddressDao.get("orderNo", orderNo));
		//order.setOrderAddress(orderAddressDao.get("orderNo", orderNo));
		List<OrderItem> orderItems = orderItemDao.getList("orderNo", orderNo);
		if(memberNo!=null&&!memberNo.equals("")&&orderItems!=null&&!orderItems.isEmpty()){
			for (OrderItem orderItem : orderItems) {
				String productNo = orderItem.getProdNo();
				Map<String,Object> values = new HashMap<String, Object>();
				values.put("productNo", productNo);
				values.put("memberNo", memberNo);
				CommodityComment cc = commodityCommentDao.get(values);
				orderItem.setIsComment(cc!=null?"1":"0");
			}
		}
		Shop shop = shopDao.get("shopNo", order.getShopNo());
		map.put("orderItems",orderItems);
		map.put("shopPhone",shop.getPhone());
		map.put("orderLogs",orderLogDao.getList("orderNo", orderNo));
		map.put("orderCoupons",orderCouponsDao.getList("orderNo", orderNo));
		map.put("orderReturn",orderReturnDao.get("orderNo", orderNo));
		/*order.setOrderItems(orderItems);
		order.setShopPhone(shop.getPhone());
		order.setOrderLogs(orderLogDao.getList("orderNo", orderNo));
		order.setOrderCoupons(orderCouponsDao.getList("orderNo", orderNo));
		order.setOrderReturn(orderReturnDao.get("orderNo", orderNo));*/
		return map;
	}

	private void processGroupOrder(Order order) {
		//String orderNo = String.valueOf(map.get("orderNo"));
		String promotionNo = order.getOrderCouponsNo();
		if(promotionNo!=null&&!promotionNo.equals("")){
			Promotion prom = promotionDao.get("promotionNo",promotionNo);
			if(prom!=null&&prom.getPromotionType().equals("2")){//团购
				int coupCount = prom.getCoupCount()==null?0:prom.getCoupCount();
				int alreadyCount = prom.getAlreadyCount()==null?0:prom.getAlreadyCount();
				double discountAmount = prom.getDiscountAmount()==null?0:prom.getDiscountAmount();
				int limitCount = prom.getLimitCount()==null?0:prom.getLimitCount();

				order.setOrderType("2");//1-普通订单，2-团购订单
				order.setAlreadyCount(alreadyCount);//已成团数
				order.setCoupCount(coupCount);//成团数
				order.setDiscountAmount(discountAmount);//团购价
				order.setLimitCount(limitCount);//每人限购数
				order.setPromStartTime(prom.getStartTime());//团购开始时间
				order.setPromEndTime(prom.getEndTime());//团购结束时间
				this.setOrderBasicState(order,prom);
				this.setGroupMsg(order,prom);
			}
		}
		else{
			order.setOrderType("1");//1-普通订单，2-团购订单
		}

	}
	private void setGroupMsg(Order order, Promotion prom) {
		int coupCount = prom.getCoupCount()==null?0:prom.getCoupCount();
		int alreadyCount = prom.getAlreadyCount()==null?0:prom.getAlreadyCount();
		String groupMsg = "";
		if(coupCount==alreadyCount)
			groupMsg = coupCount+"件成团，组团成功";
		else
			groupMsg = coupCount+"件成团，已预订"+alreadyCount+"件";
		order.setGroupMsg(groupMsg);//团购描述
	}
	private void setOrderBasicState(Order order, Promotion prom) {
		//Date startTime = prom.getStartTime();
		Date endTime = prom.getEndTime();
		Date nowTime = new Date();
		//boolean nowUpStart = nowTime.compareTo(startTime)>=0;
		boolean nowDownEnd = nowTime.compareTo(endTime)<=0;
		if(nowDownEnd&&OrderConstants.BASIC_STATE_WAITING_DELIVERY.equals(order.getBasicState())){
			order.setBasicState(OrderConstants.BASIC_STATE_BOOKING);
		}

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zl.bgec.basicapi.order.service.IOrderComponent#closeOrder(java.lang
	 * .String)
	 */
	@Override
	@Transactional
	public void closeOrder(String orderNo, String cancelReason)
			throws Exception {
		Order order = orderDao.get("orderNo", orderNo);
		List<OrderItem> orderItems = orderItemDao.getList("orderNo", orderNo);
		if(orderItems!=null&&!orderItems.isEmpty()){
			for (OrderItem orderItem : orderItems) {
				String prodNo = orderItem.getProdNo();
				int quantity = orderItem.getQuantity();
				Product product = productDao.get("prodNo",prodNo);
				int stockPreemption = product.getStockPreemption();
				product.setStockPreemption(stockPreemption-quantity);//关闭订单减扣预占库存
				productDao.update(product);
			}
		}
		order.setCancelReason(cancelReason);
		order.setCancelTime(new Date());
		order.setBasicState(OrderConstants.BASIC_STATE_CLOSED);
		orderDao.update(order);
		saveOrderLog(order, "关闭订单");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zl.bgec.basicapi.order.service.IOrderComponent#editOrderPrice(java
	 * .lang.String, java.lang.Double)
	 */
	@Override
	@Transactional
	public void editOrderPrice(String orderNo, Double price) throws Exception {
		Order order = orderDao.get("orderNo", orderNo);
		order.setFeeTotal(price);
		orderDao.update(order);
		saveOrderLog(order, "修改订单价格");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zl.bgec.basicapi.order.service.IOrderComponent#deliveryOrder(com.
	 * zl.bgec.basicapi.order.vo.OrderVo)
	 */
	@Override
	@Transactional
	public void deliveryOrder(OrderVo orderVo) throws Exception {
		String orderNo = orderVo.getOrderNo();
		String deliveryName = orderVo.getDeliveryName();
		String deliveryPhone = orderVo.getDeliveryPhone();
		String deliveryDescription = orderVo.getDeliveryDescription();
		String expressCompany = orderVo.getExpressCompany();
		String expressNo = orderVo.getExpressNo();
		List<OrderItem> orderItems = orderItemDao.getList("orderNo", orderNo);
		/*if(orderItems!=null&&!orderItems.isEmpty()){
			for (OrderItem orderItem : orderItems) {
				String prodNo = orderItem.getProdNo();
				int quantity = orderItem.getQuantity();
				Product product = productDao.get("prodNo",prodNo);
				Commodity commodity = commodityDao.get("commoNo",orderItem.getCommodityNo());
				int stock = product.getStock();
				int stockPreemption = product.getStockPreemption();
				product.setStockPreemption(stockPreemption-quantity);//关闭订单减扣预占库存
				product.setStock(stock-quantity);//关闭订单减扣实际库存
				//销量
				commodity.setSellNum(commodity.getSellNum()==null||commodity.getSellNum().intValue()==0?orderItem.getQuantity():commodity.getSellNum()+orderItem.getQuantity());
				product.setSellNum(product.getSellNum()==null||product.getSellNum().intValue()==0?orderItem.getQuantity():product.getSellNum()+orderItem.getQuantity());
				productDao.update(product);
			}
		}*/

		Order order = orderDao.get("orderNo", orderNo);
		if((expressCompany==null||expressCompany.equals(""))&&(expressNo==null||expressNo.equals(""))){
			order.setDeliveryDescription(deliveryDescription);
			order.setDeliveryName(deliveryName);
			order.setDeliveryPhone(deliveryPhone);
		}else{
			order.setExpressCompany(expressCompany);
			order.setExpressNo(expressNo);
		}
		order.setBasicState(OrderConstants.BASIC_STATE_ALREADY_DELIVERY);// 订单状态改为以发货
		order.setUpdateTime(new Date());
		orderDao.update(order);
		saveOrderLog(order, "订单发货");

		Shop shop = shopDao.get("shopNo",order.getShopNo());
		sendMessage.sendMessage(order.getBuyerNo(), System.currentTimeMillis(), RandomUtils.nextInt(2), "text", 
				"您在"+shop.getShopName()+"店铺购买的商品已发货，请注意查收；");
	}

	@Override
	@Transactional
	public void agreeOrderReurn(String orderNo) throws Exception {
		Order order = orderDao.get("orderNo", orderNo);
		//TODO 退款接口
		String url = sdkConstants.getCLIENT_CREATE_REFUND_RECORD();//"http://192.168.10.117:8087/EDS_PAY/pay_common/refund/save_refundInfo_record";
		OrderReturn orderReturn = orderReturnDao.get("orderNo",orderNo);
		ReturnOrderRecord map = new ReturnOrderRecord();
		map.setOriginalOrderNo(orderNo);
		map.setRefundAmount(orderReturn.getReturnAmount());
		map.setRefundmark(orderReturn.getReturnReason());
		BaseVo<ReturnOrderRecord> baseVo = new BaseVo<ReturnOrderRecord>();
		baseVo.setBody(map);
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),url);
		log.info(json);
		if(json!=null){
			ResultVo resultVo = GsonUtil.fromJson(json, ResultVo.class);
			if(!resultVo.isResult()){
				throw new Exception("生成退款单失败。");
			}
		}

		order.setBasicState(OrderConstants.BASIC_STATE_ALREADY_REFUND);//已退款
		orderDao.update(order);
		saveOrderLog(order, "订单退货审核，同意订单退货");
	}

	@Transactional
	private void saveOrderLog(Order order, String remark) {
		OrderLog entity = new OrderLog();
		entity.setOperationRemark(remark);
		entity.setOperationTime(new Date());
		entity.setOperationType("1");
		entity.setOrderNo(order.getOrderNo());
		entity.setOperatorNo(order.getShopNo());
		orderLogDao.save(entity);
	}

	@Override
	@Transactional
	public void disagreeOrderReurn(Map<String, String> orderVo)
			throws Exception {
		String orderNo = orderVo.get("orderNo");
		String auditReason = orderVo.get("auditReason");
		// String auditRemark = orderVo.get("auditRemark");
		Order order = orderDao.get("orderNo", orderNo);
		order.setBasicState(OrderConstants.BASIC_STATE_COMPLETED);
		OrderReturn orderReturn = orderReturnDao.get("orderNo", orderNo);
		orderReturn.setAuditReason(auditReason);
		// orderReturn.setAuditRemark(auditRemark);
		orderReturnDao.update(orderReturn);
		saveOrderLog(order, "订单退货审核，不同意退货");
	}

	@Override
	@Transactional
	public void agreeCheckOrderReurn(String orderNo) throws Exception {
		Order order = orderDao.get("orderNo", orderNo);
		//		order.setBasicState(OrderConstants.BASIC_STATE_REFUND);
		orderDao.update(order);
		saveOrderLog(order, "订单退货验收，验收同意订单退货");
	}

	@Override
	@Transactional
	public void disagreeCheckOrderReurn(Map<String, String> orderVo)
			throws Exception {
		String orderNo = orderVo.get("orderNo");
		String checkReason = orderVo.get("checkReason");
		// String checkRemark = orderVo.get("checkRemark");
		Order order = orderDao.get("orderNo", orderNo);
		//		order.setBasicState(OrderConstants.BASIC_STATE_ALREADY_RECEIVE);
		OrderReturn orderReturn = orderReturnDao.get("orderNo", orderNo);
		orderReturn.setCheckReason(checkReason);
		// orderReturn.setCheckRemark(checkRemark);
		orderReturnDao.update(orderReturn);
		saveOrderLog(order, "订单退货验收，验收不同意退货");
	}


	@Override
	@Transactional
	public void confirmOrder(String orderNo) throws Exception {
		Order order = orderDao.get("orderNo", orderNo);
		//		order.setBasicState(OrderConstants.BASIC_STATE_ALREADY_RECEIVE);确认收货 到 已完成状态
		order.setBasicState(OrderConstants.BASIC_STATE_COMPLETED);
		orderDao.update(order);
		saveOrderLog(order, "用户确认订单收货");

		Shop shop = shopDao.get("shopNo",order.getShopNo());
		sendMessage.sendMessage(shop.getMerchNo(), System.currentTimeMillis(), RandomUtils.nextInt(2), "text", 
				"订单"+order.getOrderNo()+"共计"+order.getFeeTotal()+"元，买家已确认收货；");

	}

	@Override
	@Transactional
	public void autoConfirmOrder() throws Exception {
		log.info("autoConfirmOrder");
		Criteria criteria = orderDao.createCriteria(Restrictions.eq("basicState", OrderConstants.BASIC_STATE_ALREADY_DELIVERY));
		long days = 60*60*24*1000*Integer.parseInt(autoReceiveOrder);

		long currentTimeLong = System.currentTimeMillis();
		Date currentDate = new Date(currentTimeLong);

		Date now = new Date(currentTimeLong-days);

		log.info("autoConfirmOrder-now="+now+",currentTime="+currentDate+"days="+days);

		criteria.add(Restrictions.lt("updateTime", now));
		List<Order> orders = criteria.list();
		log.info("autoConfirmOrder-orders="+GsonUtil.toJson(orders));

		if(orders!=null&&!orders.isEmpty()){
			for (Order order : orders) {
				order.setBasicState(OrderConstants.BASIC_STATE_COMPLETED);
				orderDao.update(order);
				saveOrderLog(order, "到期自动确认订单收货");
				Shop shop = shopDao.get("shopNo",order.getShopNo());
				sendMessage.sendMessage(shop.getMerchNo(), System.currentTimeMillis(), RandomUtils.nextInt(2), "text", 
						"订单"+order.getOrderNo()+"共计"+order.getFeeTotal()+"元，买家已确认收货；");
				sendMessage.sendMessage(order.getBuyerNo(), System.currentTimeMillis(), RandomUtils.nextInt(2), "text", 
						"您在"+shop.getShopName()+"店铺购买的商品已发货，"+autoReceiveOrder+"天未确认，系统自动确认收货；");
			}
		}

	}

	@Override
	@Transactional
	public void applyReturnOrder(OrderReturnVo orderReturnVo) throws Exception {
		OrderReturn orderReturn = new OrderReturn();
		ObjectConvertUtil.convertVoToPo(orderReturnVo, orderReturn);
		orderReturnVo.setReturnTime(new Date());
		Order order = orderDao.get("orderNo", orderReturnVo.getOrderNo());
		String pics = orderReturnVo.getRenturnPic();
		if (pics != null && !pics.equals("")) {
			List<Map<String, String>> maps = GsonUtil.fromJson(pics,
					new TypeToken<List<Map<String, String>>>() {
			}.getType());
			if (maps != null && !maps.isEmpty()) {
				for (Map<String, String> map : maps) {
					String url = map.get("url");
					url = imageUtil.saveImg(url, order.getBuyerNo(), "order");
					map.put("url", url);
				}
			}
			orderReturn.setReturnPic(GsonUtil.toJson(maps));
		}
		orderReturnDao.save(orderReturn);
		order.setBasicState(OrderConstants.BASIC_STATE_REFUND_APPLY);
		orderDao.update(order);
		saveOrderLog(order, "用户退货申请");
	}

	@Override
	@Transactional
	public void deleteOrder(String orderNo) throws Exception {
		Order order = orderDao.get("orderNo", orderNo);
		order.setDeleteFlag((byte) 1);
		order.setUpdateTime(new Date());
		orderDao.update(order);
		this.operateDeleteOrder(order);
		saveOrderLog(order, "用户删除订单");
	}
	private void operateDeleteOrder(Order order) {
		if(order.getBasicState().equals(OrderConstants.BASIC_STATE_WAITING_PAY)){//待付款的订单
			this.returnOrderPromotion(order.getOrderNo());
			this.returnProdStockPreemption(order.getOrderNo());
		}
	}
	private void returnOrderPromotion(String orderNo) {
		String sql = "select "
				+"toc.coupon_no coupNo "
				+"from tbl_order_coupons toc "
				+"where toc.order_no=:orderNo";
		Query query = promotionDao.createSQLQuery(sql);
		query.setParameter("orderNo", orderNo);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> coupons = query.list();

		log.info("returnOrderPromotion-coupons="+GsonUtil.toJson(coupons));
		if(coupons!=null&&!coupons.isEmpty()){
			for(Map<String,Object> coupon : coupons){
				PromotionInfo promotionInfo = promotionInfoDao.get("coupNo",coupon.get("coupNo"));
				promotionInfo.setUseTime(null);
				promotionInfo.setStatus("0");
				promotionInfoDao.update(promotionInfo);
			}
		}

	}
	@Override
	@Transactional
	public void cleanNoPayOrder() throws Exception {
		Criteria criteria = orderDao.createCriteria(Restrictions.eq("basicState", OrderConstants.BASIC_STATE_WAITING_PAY));
		long days = 60*1000*Integer.parseInt(autoCancelOrder);
		//long days = 1000*Integer.parseInt(autoCancelOrder);

		long currentTimeLong = System.currentTimeMillis();
		Date currentDate = new Date(currentTimeLong);

		Date now = new Date(currentTimeLong-days);

		log.info("cleanNoPayOrder-now="+now+",currentTime="+currentDate+"days="+days);

		criteria.add(Restrictions.lt("createTime", now));
		List<Order> orders = criteria.list();
		log.info("cleanNoPayOrder-orders="+GsonUtil.toJson(orders));

		if(orders!=null&&!orders.isEmpty()){
			for (Order order : orders) {
				order.setCancelReason("0.5小时未支付订单，系统自动取消");
				order.setCancelTime(new Date());
				order.setDeleteFlag((byte) 1);
				order.setUpdateTime(new Date());
				this.returnProdStockPreemption(order.getOrderNo());
				orderDao.update(order);
				this.returnOrderPromotion(order.getOrderNo());
				saveOrderLog(order, "0.5小时未支付订单，系统自动取消");
			}
		}
	}
	private void returnProdStockPreemption(String orderNo) {
		List<OrderItem> orderItems = orderItemDao.getList("orderNo", orderNo);
		if(orderItems!=null&&!orderItems.isEmpty()){
			for (OrderItem orderItem : orderItems) {
				String prodNo = orderItem.getProdNo();
				String commoNo = orderItem.getOrderNo();
				int quantity = orderItem.getQuantity();
				Product product = productDao.get("prodNo",prodNo);
				int stockPreemption = product.getStockPreemption()==null?0:product.getStockPreemption();
				if(stockPreemption!=0&&stockPreemption>=quantity){
					product.setStockPreemption(stockPreemption-quantity);//关闭订单减扣预占库存
					productDao.update(product);
				}
			}
		}
	}

	@Override
	@Transactional
	public void payCallBack(Map<String, String> map) throws Exception {
		String orderNo = map.get("orderNo");
		String paymentBank = map.get("paymentBank");
		String paymentSequence = map.get("paymentSequence");
		Order order = orderDao.get("orderNo", orderNo);

		int quantity = 0;
		List<OrderItem> oitems = orderItemDao.getList("orderNo", orderNo);
		if(oitems!=null&&!oitems.isEmpty()){
			for(OrderItem r:oitems){
				int iquan = r.getQuantity()==null?0:r.getQuantity();
				quantity += iquan;
				Commodity com = commodityDao.get("commoNo",r.getCommodityNo());
				Product prod = productDao.get("prodNo",r.getProdNo());
				//商品-x销量，库存，预库存
				if(com!=null){
					int comSemmNum = com.getSellNum()==null?0:com.getSellNum();
					com.setSellNum(comSemmNum+iquan);
					commodityDao.update(com);
				}
				if(prod!=null){
					int prodSellNum = prod.getSellNum()==null?0:prod.getSellNum();
					prod.setSellNum(prodSellNum+iquan);
					int stock = prod.getStock()==null?0:prod.getStock();
					int nowStock = stock-iquan;
					String shopStockMsg = null;
					//库存为0,5发消息
					if(nowStock==0){
						shopStockMsg="商品"+prod.getProdName()+"已售罄，请及时增加库存";
						Shop shop = shopDao.get("shopNo",order.getShopNo());
						sendMessage.sendMessage(shop.getMerchNo(), System.currentTimeMillis(), RandomUtils.nextInt(2), "text",shopStockMsg);
					}else if(nowStock==5){
						shopStockMsg="商品 "+prod.getProdName()+"即将售罄，请及时增加库存";
						Shop shop = shopDao.get("shopNo",order.getShopNo());
						sendMessage.sendMessage(shop.getMerchNo(), System.currentTimeMillis(), RandomUtils.nextInt(2), "text",shopStockMsg);
					}
					
					//更新prod的库存和预库存数量
					if(nowStock<0)
						log.info("payCallBack-stock error.stock="+stock+",quantity="+iquan+",prodNo="+prod.getProdNo());
					else
						prod.setStock(nowStock);
					int stockPreemption = prod.getStockPreemption()==null?0:prod.getStockPreemption();
					int nowStockPreemption = stockPreemption-iquan;
					if(nowStockPreemption<0)
						log.info("payCallBack-stockPreemption error.stockPreemption="+stockPreemption+",quantity="+iquan+",prodNo="+prod.getProdNo());
					else
						prod.setStockPreemption(nowStockPreemption);
					productDao.update(prod);
				}
			}
		}
		
		String userMsg=null;
		String shopMsg=null;
		Shop shop = shopDao.get("shopNo",order.getShopNo());
		
		//团购订单-已购买数量
		if(!StringUtils.isEmpty(order.getOrderCouponsNo())){
			String promotionNo = order.getOrderCouponsNo();
			Promotion prom = promotionDao.get("promotionNo",promotionNo);
			if(prom!=null&&prom.getPromotionType().equals("2")){//团购订单
				int alreadyCount = prom.getAlreadyCount()==null?0:prom.getAlreadyCount();
				prom.setAlreadyCount(alreadyCount+quantity);
				
				String refCommNo = prom.getRefCommoNo();
				Commodity commodity = commodityDao.get("commoNo",refCommNo);
				userMsg="您已成功参加"+shop.getShopName()+"店铺"+commodity.getCommoName()+"商品团购，共计"+quantity+"件；团购报名结束后，若未达到成团件数"+prom.getCoupCount()+"件，系统将自动退款";
				shopMsg=order.getBuyerNickName()+"已报名参加 "+shop.getShopName()+"店铺"+commodity.getCommoName()+"商品团购，共计"+quantity+"件；当前团购总件数"+prom.getAlreadyCount()+"件";
				
				promotionDao.update(prom);
			}
		}
		else{
			userMsg="您已成功购买"+shop.getShopName()+"店铺商品，共计"+order.getFeeTotal()+"元，我们会通知店主及时发货。";
			shopMsg="您有新的订单"+order.getOrderNo()+"，用户"+order.getBuyerNickName()+"消费"+order.getFeeTotal()+"元，请及时安排配送。";
		}

		order.setBasicState(OrderConstants.BASIC_STATE_WAITING_DELIVERY);
		order.setPaidTotal(order.getFeeTotal());
		order.setPaymentTime(new Date());
		order.setPaymentBank(paymentBank);
		order.setPaymentSequence(paymentSequence);
		orderDao.update(order);

		sendMessage.sendMessage(order.getBuyerNo(), System.currentTimeMillis(), RandomUtils.nextInt(2), "text", userMsg);
		sendMessage.sendMessage(shop.getMerchNo(), System.currentTimeMillis(), RandomUtils.nextInt(2), "text",shopMsg);
	}
	@Override
	@Transactional
	public void payFail(Map<String, String> map) throws Exception {
		String orderNo = map.get("orderNo");
		String paymentBank = map.get("paymentBank");
		Order order = orderDao.get("orderNo", orderNo);
		saveOrderLog(order, paymentBank+"支付失败");
		//		Shop shop = shopDao.get("shopNo",order.getShopNo());
		//		sendMessage.sendMessage(order.getBuyerNo(), System.currentTimeMillis(), RandomUtils.nextInt(2), "text", 
		//				"您已成功购买"+shop.getShopName()+"店铺商品，共计"+order.getFeeTotal()+"元，我们会通知店主及时发货；");
	}
	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> getOrderAmount(String orderNo) throws Exception {
		Order order = orderDao.get("orderNo",orderNo);
		Map<String, Object> map = new HashMap();
		if(order!=null){
			map.put("orderAmount", order.getFeeTotal());
		}
		return map;
	}
	@Override
	@Transactional
	public void closeFinishedOrder() throws Exception {
		String [] basicStates = new String []{OrderConstants.BASIC_STATE_COMPLETED,OrderConstants.BASIC_STATE_ALREADY_REFUND};
		Criteria criteria = orderDao.createCriteria(Restrictions.in("basicState", basicStates));
		long days = 60*60*24*1000*Integer.parseInt(autoCloseOrder);

		long currentTimeLong = System.currentTimeMillis();
		Date currentDate = new Date(currentTimeLong);

		Date now = new Date(currentTimeLong-days);

		log.info("closeFinishedOrder-now="+now+",currentTime="+currentDate+"days="+days);

		criteria.add(Restrictions.lt("createTime", now));
		List<Order> orders = criteria.list();
		log.info("closeFinishedOrder-orders="+GsonUtil.toJson(orders));

		if(orders!=null&&!orders.isEmpty()){
			for (Order order : orders) {
				String reason = null;
				if(order.getBasicState().equals(OrderConstants.BASIC_STATE_COMPLETED))
					reason="5天未处理已完成订单，系统自动关闭";
				if(order.getBasicState().equals(OrderConstants.BASIC_STATE_ALREADY_REFUND))
					reason="5天未处理已退款订单，系统自动关闭";
				order.setCancelReason(reason);
				order.setCancelTime(new Date());
				order.setBasicState(OrderConstants.BASIC_STATE_CLOSED);
				orderDao.update(order);
				saveOrderLog(order, reason);
			}
		}
	}
	@Override
	@Transactional
	public PageFinder<Map<String,Object>> queryGroupOrder(PromotionVo promVo) throws Exception {
		String promNo = promVo.getPromotionNo();
		int pageNo = promVo.getPageNo();
		int pageSize = promVo.getPageSize();
		if(pageNo==0){
			pageNo = 1;
		}

		StringBuffer sql = new StringBuffer();
		sql.append("select tor.basic_state basicState,"
				+ "tor.order_no orderNo,"
				+ "tor.buyer_no buyerNo,"
				+ "tor.create_time createTime,"
				+ "tor.delivery_type deliveryType,"
				+ "tor.order_coupons_no orderCouponsNo,"
				+ "tor.fee_total feeTotal,"
				+ "tor.buyer_nick_name buyerNickName,"
				+ "tp.promotion_type promotionType,"
				+ "tp.coup_count coupCount,"
				+ "tp.ref_commo_no refCommoNo,"
				+ "tp.already_count alreadyCount,"
				+ "tp.start_time startTime,"
				+ "tp.end_time endTime"
				+ " from tbl_order tor left join tbl_promotion tp on tor.order_coupons_no=tp.promotion_no"
				+ " where tor.order_coupons_no=:orderCouponsNo and tor.delete_flag='0'");

		StringBuffer countSql = new StringBuffer();
		countSql.append("select count(*) "
				+ "from tbl_order tor left join tbl_promotion tp on tor.order_coupons_no=tp.promotion_no"
				+ " where tor.order_coupons_no=:orderCouponsNo and tor.delete_flag='0'");
		Map<String,String> values = new HashMap<String, String>();
		values.put("orderCouponsNo", promNo);
		Query sqlQuery = orderDao.createSQLQuery(sql.toString(), values);
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Query countQuery = orderDao.createSQLQuery(countSql.toString(), values);
		BigInteger totals = (BigInteger) countQuery.list().get(0);
		int totalRows = totals.intValue();
		PageFinder<Map<String, Object>> pageFinder = null;
		if(totalRows!=0){
			if(pageSize!=0){
				pageFinder = new PageFinder<Map<String, Object>>(pageNo,pageSize,totalRows);
				sqlQuery.setMaxResults(pageFinder.getPageSize());
				sqlQuery.setFirstResult(pageFinder.getStartOfPage());
			}else{
				pageFinder = new PageFinder<Map<String, Object>>(pageNo,10,totalRows);
			}
			List<Map<String, Object>> map = sqlQuery.list();
			if(map!=null&&!map.isEmpty()){
				for(Map<String, Object> r:map){
					//设置组团中状态
					Date now = new Date();
					int nowCompareEnd = now.compareTo((Date) r.get("endTime"));
					if(r.get("basicState").equals(OrderConstants.BASIC_STATE_WAITING_DELIVERY)&&nowCompareEnd<0)
						r.put("basicState", OrderConstants.BASIC_STATE_BOOKING);
					//购买数
					List<OrderItem> items = orderItemDao.getList("orderNo", r.get("orderNo"));
					if(items!=null&&!items.isEmpty()){
						int quantity=0;
						for(OrderItem item:items){
							int quan = item.getQuantity()==null?0:item.getQuantity();
							quantity+=quan;
						}
						r.put("quantity", quantity);
					}
					else
						r.put("quantity", 0);
				}
			}
			pageFinder.setData(map);
		}
		return pageFinder;
	}
	@Override
	@Transactional
	public void refundCallback(Map<String, String> map) throws Exception {
		String orderNo = map.get("orderNo");
		Order order = orderDao.get("orderNo",orderNo);
		log.info("refundCallback-order="+GsonUtil.toJson(order));
		if(order==null)
			throw new OrderException("20001", "查询不到订单,orderNo="+orderNo);
		order.setBasicState(OrderConstants.BASIC_STATE_ALREADY_REFUND);
		orderDao.update(order);
	}
}

class TempProduct{
	private String prodNo;
	private int quantity;
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
