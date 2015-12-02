package com.zl.bgec.basicapi.cart.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zl.bgec.basicapi.basecomponent.utils.ObjectConvertUtil;
import com.zl.bgec.basicapi.cart.dao.ICartDao;
import com.zl.bgec.basicapi.cart.dao.ICartItemDao;
import com.zl.bgec.basicapi.cart.po.Cart;
import com.zl.bgec.basicapi.cart.po.CartItem;
import com.zl.bgec.basicapi.cart.service.ICartComponent;
import com.zl.bgec.basicapi.cart.vo.CartItemVo;
import com.zl.bgec.basicapi.commodity.dao.IProductDao;
import com.zl.bgec.basicapi.commodity.po.Product;
import com.zl.bgec.basicapi.common.exception.CartException;
import com.zl.bgec.basicapi.shop.dao.IShopDao;
import com.zl.bgec.basicapi.shop.po.Shop;

@Service
public class CartComponentImpl implements ICartComponent{
	@Resource
	private ICartDao cartDao;
	@Resource
	private ICartItemDao cartItemDao;
	@Resource
	private IProductDao productDao;
	@Resource
	private IShopDao shopDao;
	@Override
	@Transactional
	public void addItem(CartItemVo cartItemVo) throws Exception {
		CartItem cartItemNew = new CartItem();
		ObjectConvertUtil.convertVoToPo(cartItemVo, cartItemNew);
		Product product = productDao.get("prodNo", cartItemNew.getProdNo());
		int stock = product.getStock()==null?0:product.getStock();
		int stockPreemption = product.getStockPreemption()==null?0:product.getStockPreemption();
		int leaveStock = stock-stockPreemption;
		
		if(product==null){
			throw new Exception("无此商品");
		}else{
			Shop shop = shopDao.get("shopNo",product.getSellerNo());
			cartItemNew.setProdPic(product.getDefaultPic());
			cartItemNew.setProdName(product.getProdName());
			cartItemNew.setShopName(shop.getShopName());
			cartItemNew.setShopNo(product.getSellerNo());
			cartItemNew.setProdPrice(product.getPrice());
			BigDecimal b1 = new BigDecimal(product.getPrice());
			BigDecimal b2 = new BigDecimal(cartItemNew.getQuantity());
			cartItemNew.setPriceTotal(b1.multiply(b2).doubleValue());
		}
		String memberNo = cartItemVo.getMemberNo();
		Cart cart = cartDao.get("memberNo", memberNo);
		String prodNo = cartItemVo.getProdNo();
		if(cart!=null){//购物车已存在，修改
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("prodNo", prodNo);
			map.put("cartNo", cart.getCartNo());
			CartItem cartItem = cartItemDao.get(map);
			
			if(cartItem!=null){//购物车已存在该商品，修改数量
				int quantity = cartItem.getQuantity()+cartItemVo.getQuantity();
				if(leaveStock>=0 && leaveStock<quantity){
					throw new CartException("30001","库存不足,库存为"+leaveStock);
				}
				cartItem.setQuantity(quantity);
				Double price = product.getPrice();
				BigDecimal b1 = new BigDecimal(price);
				BigDecimal b2 = new BigDecimal(quantity);
				Double priceTotal = b1.multiply(b2).doubleValue();
				cartItem.setPriceTotal(priceTotal);
				cartItemDao.update(cartItem);
			}else{//购物车不存在该商品，新增
				if(leaveStock>=0 && leaveStock<cartItemNew.getQuantity()){
					throw new CartException("30001","库存不足,库存为"+leaveStock);
				}
				cartItemNew.setCartNo(cart.getCartNo());
				cartItemNew.setCreateTime(new Date());
				cartItemNew.setItemNo(cartItemNew.createKey());
				cartItemDao.save(cartItemNew);
			}
			cart.setUpdateTime(new Date());
			cartDao.update(cart);
		}else{//购物车不存在，新增
			Cart cartPo = new Cart();
			cartPo.setCartNo(cartPo.createKey());
			cartPo.setCreateTime(new Date());
			cartPo.setMemberNo(cartItemVo.getMemberNo());
			cartDao.save(cartPo);
			cartItemNew.setCartNo(cartPo.getCartNo());
			cartItemNew.setItemNo(cartItemNew.createKey());
			cartItemNew.setCreateTime(new Date());
			BigDecimal b1 = new BigDecimal(product.getPrice());
			BigDecimal b2 = new BigDecimal(cartItemNew.getQuantity());
			cartItemNew.setPriceTotal(b1.multiply(b2).doubleValue());
			cartItemDao.save(cartItemNew);
		}
	}

	@Override
	@Transactional
	public void updateItem(CartItemVo cartItemVo) throws Exception {
		String prodNo = cartItemVo.getProdNo();
		Integer quantity = cartItemVo.getQuantity();
		String memberNo = cartItemVo.getMemberNo();
		Cart cart = cartDao.get("memberNo", memberNo);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("prodNo", prodNo);
		map.put("cartNo", cart.getCartNo());
		CartItem cartItem = cartItemDao.get(map);
		Product product = productDao.get("prodNo",prodNo);
		Integer stock = product.getStock();
		stock = stock==null?0:stock;
		Integer stockPreemption = product.getStockPreemption();
		stockPreemption = stockPreemption==null?0:stockPreemption;
		int i = stock-stockPreemption;
		if(quantity>i){
			throw new Exception("商品库存不足");
		}
		cartItem.setQuantity(quantity);//修改数量
		BigDecimal b1 = new BigDecimal(cartItem.getProdPrice());
		BigDecimal b2 = new BigDecimal(cartItem.getQuantity());
		cartItem.setPriceTotal(b1.multiply(b2).doubleValue());
		cartItemDao.update(cartItem);
		cart.setUpdateTime(new Date());
		cartDao.update(cart);
	}

	@Override
	@Transactional(readOnly=true)
	public Cart getCart(String memberNo) throws Exception {
		Cart cart = cartDao.get("memberNo",memberNo);
		if(cart!=null){
			Criteria criteria = cartItemDao.createCriteria(Restrictions.eq("cartNo", cart.getCartNo()));
			criteria.addOrder(Order.desc("createTime"));
			List<CartItem> cartItems = cartItemDao.getListByCriteria(criteria);
			cart.setCartItems(cartItems);
		}
		return cart;
	}
	@Override
	@Transactional(readOnly=true)
	public Map<String,Integer> getCartCount(String memberNo) throws Exception {
		Cart cart = cartDao.get("memberNo",memberNo);
		if(cart!=null){
			String sql = "select if(sum(tci.quantity) is null,0,sum(tci.quantity) ) count from tbl_cart_item tci where tci.cart_no=:cartNo";
			Query query = cartItemDao.createSQLQuery(sql);
			query.setParameter("cartNo", cart.getCartNo());
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			return (Map<String,Integer>)query.list().get(0);
		}
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("count", 0);
		return map;
	}

	@Override
	@Transactional
	public void deleteItem(List<CartItemVo> cartItemVos,String memberNo) throws Exception {
		if(cartItemVos!=null&&!cartItemVos.isEmpty()){
			for (CartItemVo cartItemVo : cartItemVos) {
				String prodNo = cartItemVo.getProdNo();
				Cart cart = cartDao.get("memberNo", memberNo);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("prodNo", prodNo);
				map.put("cartNo", cart.getCartNo());
				CartItem cartItem = cartItemDao.get(map);
				cart.setUpdateTime(new Date());
				cartItemDao.delete(cartItem);
				cartDao.update(cart);
			}
		}
	}

	@Override
	@Transactional
	public void deleteCart(CartItemVo cartItemVo) throws Exception {
		String memberNo = cartItemVo.getMemberNo();
		Cart cart = cartDao.get("memberNo", memberNo);
		cartItemDao.delete("cartNo", cart.getCartNo());
		cartDao.delete(cart);
	}

}
