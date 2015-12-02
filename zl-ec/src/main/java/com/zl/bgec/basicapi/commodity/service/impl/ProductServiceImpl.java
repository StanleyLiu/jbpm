
package com.zl.bgec.basicapi.commodity.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.basecomponent.utils.ObjectConvertUtil;
import com.zl.bgec.basicapi.commodity.dao.ICommodityCollectDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityCommentDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityDetailPicDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityPicDao;
import com.zl.bgec.basicapi.commodity.dao.IProductChangePriceDao;
import com.zl.bgec.basicapi.commodity.dao.IProductChangePriceItemDao;
import com.zl.bgec.basicapi.commodity.dao.IProductDao;
import com.zl.bgec.basicapi.commodity.dao.IProductPropDao;
import com.zl.bgec.basicapi.commodity.dao.IProductStockDao;
import com.zl.bgec.basicapi.commodity.po.Commodity;
import com.zl.bgec.basicapi.commodity.po.CommodityComment;
import com.zl.bgec.basicapi.commodity.po.CommodityDetailPic;
import com.zl.bgec.basicapi.commodity.po.CommodityPic;
import com.zl.bgec.basicapi.commodity.po.Product;
import com.zl.bgec.basicapi.commodity.po.ProductChangePrice;
import com.zl.bgec.basicapi.commodity.po.ProductChangePriceItem;
import com.zl.bgec.basicapi.commodity.po.ProductProp;
import com.zl.bgec.basicapi.commodity.po.ProductStock;
import com.zl.bgec.basicapi.commodity.service.ICommoNoCreatorService;
import com.zl.bgec.basicapi.commodity.service.IProductService;
import com.zl.bgec.basicapi.commodity.vo.CommodityDetailPicVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityVo;
import com.zl.bgec.basicapi.commodity.vo.ProductChangePriceItemVo;
import com.zl.bgec.basicapi.commodity.vo.ProductChangePriceVo;
import com.zl.bgec.basicapi.commodity.vo.ProductPropVo;
import com.zl.bgec.basicapi.commodity.vo.ProductStockVo;
import com.zl.bgec.basicapi.commodity.vo.ProductVo;
import com.zl.bgec.basicapi.common.CommodityConstant;
import com.zl.bgec.basicapi.common.exception.ParamException;
import com.zl.bgec.basicapi.common.util.ExceptionUtil;
import com.zl.bgec.basicapi.common.util.LogicUtil;
import com.zl.bgec.basicapi.order.common.OrderConstants;
import com.zl.bgec.basicapi.order.dao.IOrderDao;
import com.zl.bgec.basicapi.promotion.dao.IPromotionDao;
import com.zl.bgec.basicapi.promotion.po.Promotion;

@Service
public class ProductServiceImpl implements IProductService{

	@Resource
	private ICommoNoCreatorService commoNoCreatorService;
	@Resource
	private IProductDao productDao;
	@Resource
	private IProductChangePriceDao productChangePriceDao;
	@Resource
	private IProductChangePriceItemDao productChangePriceItemDao;
	@Resource
	private IProductPropDao productPropDao;
	@Resource
	private IProductStockDao productStockDao;
	@Resource
	private ICommodityDao commodityDao;
	@Resource
	private ICommodityCollectDao commodityCollectDao;
	@Resource
	private ICommodityCommentDao commodityCommentDao;
	@Resource
	private ICommodityPicDao commodityPicDao;
	@Resource
	private IPromotionDao promotionDao;
	@Resource
	private IOrderDao orderDao;
	@Resource
	private ICommodityDetailPicDao commodityDetailPicDao;	

	@Override
	@Transactional
	public String saveProduct(ProductVo productVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(productVo.getCommoNo());
		ExceptionUtil.checkParamStringNullAndEmpty(productVo.getProdName());

		Product product = new Product();
		ObjectConvertUtil.convertVoToPo(productVo, product);
		String productNo = commoNoCreatorService.createProdNo(productVo.getCommoNo());
		product.setProdNo(productNo);
		product.setCreateTime(new Date());
		product.setUpdateTime(new Date());
		productDao.save(product);
		return productNo;
	}

	@Override
	@Transactional
	public void saveProductProp(ProductPropVo productPropVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(productPropVo.getProdNo());
		ExceptionUtil.checkParamStringNullAndEmpty(productPropVo.getPropNo());
		ExceptionUtil.checkParamStringNullAndEmpty(productPropVo.getPropName());

		ProductProp prop = new ProductProp();
		ObjectConvertUtil.convertVoToPo(productPropVo, prop);
		productPropDao.save(prop);
	}

	@Override
	@Transactional
	public void saveBatchProductProp(List<ProductPropVo> productPropVos)
			throws Exception {
		ExceptionUtil.checkParamCollection(productPropVos);
		for(ProductPropVo propVo : productPropVos){
			ExceptionUtil.checkParamStringNullAndEmpty(propVo.getProdNo());
			ExceptionUtil.checkParamStringNullAndEmpty(propVo.getPropNo());
			ExceptionUtil.checkParamStringNullAndEmpty(propVo.getPropName());
			ProductProp prop = new ProductProp();
			ObjectConvertUtil.convertVoToPo(propVo, prop);
			productPropDao.save(prop);
		}
	}

	@Override
	@Transactional
	public void updateProduct(ProductVo productVo) throws Exception {
		ExceptionUtil.checkParamStringNull(productVo.getProdNo());
		Product product = productDao.get("prodNo", productVo.getProdNo());
		ObjectConvertUtil.convertVoToPo(productVo, product);
		product.setUpdateTime(new Date());
		productDao.update(product);
	}

	@Override
	@Transactional
	public void deleteProduct(ProductVo productVo) throws Exception {
		ExceptionUtil.checkParamStringNull(productVo.getProdNo());
		Product product = productDao.get("prodNo", productVo.getProdNo());
		product.setUpdateTime(new Date());
		product.setDeleteFlag(CommodityConstant.DELETE_FLAG);
		productDao.update(product);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> getProductList(ProductVo productVo) throws Exception {
		StringBuffer hql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		hql.append(" from Product p where p.deleteFlag = :deleteFlag ");
		values.put("deleteFlag", productVo.getDeleteFlag());
		if(LogicUtil.isNotNullAndEmpty(productVo.getCommoNo())){
			hql.append(" and p.commoNo = :commoNo ");
			values.put("commoNo", productVo.getCommoNo());
		}
		if(LogicUtil.isNotNullAndEmpty(productVo.getProdNo())){
			hql.append(" and p.prodNo = :prodNo ");
			values.put("prodNo", productVo.getProdNo());
		}
		if(LogicUtil.isNotNullAndEmpty(productVo.getProdName())){
			hql.append(" and p.prodName like :prodName ");
			values.put("prodName", "%"+productVo.getProdName()+"%");
		}
		return productDao.createQuery(hql.toString(), values).list();
	}

	@Override
	@Transactional(readOnly = true)
	public PageFinder<Product> pagedProductList(ProductVo productVo) throws Exception {
		Criteria criteria = productDao.createCriteria(Restrictions.eq("deleteFlag", productVo.getDeleteFlag()));
		if(LogicUtil.isNotNullAndEmpty(productVo.getCommoNo())){
			criteria.add(Restrictions.eq("commoNo", productVo.getCommoNo()));
		}
		if(LogicUtil.isNotNullAndEmpty(productVo.getProdNo())){
			criteria.add(Restrictions.eq("prodNo", productVo.getProdNo()));
		}
		if(LogicUtil.isNotNullAndEmpty(productVo.getProdName())){
			criteria.add(Restrictions.like("prodName", "%"+productVo.getProdName()+"%"));
		}
		if(LogicUtil.isNotNullAndEmpty(productVo.getShopNo())){
			criteria.add(Restrictions.like("sellerNo", productVo.getShopNo()));
		}
		return productDao.pagedByCriteria(criteria, productVo.getPageNo(), productVo.getPageSize());
	}

	@Override
	@Transactional
	public String createProductChangePrice(
			ProductChangePriceVo productChangePriceVo) throws Exception {
		ExceptionUtil.checkParamStringNull(productChangePriceVo.getOperatorName());
		//保存货品调价单
		ProductChangePrice productChangePrice = new ProductChangePrice();
		ObjectConvertUtil.convertVoToPo(productChangePriceVo, productChangePrice);
		String changeNo = commoNoCreatorService.createPriceChangeNo();
		productChangePrice.setChangeNo(changeNo);
		productChangePrice.setOperatorTime(new Date());
		productChangePriceDao.save(productChangePrice);
		//保存货品调价项
		for(ProductChangePriceItemVo itemVo:productChangePriceVo.getItemVos()){
			ExceptionUtil.checkParamStringNull(itemVo.getProdNo());
			ExceptionUtil.checkParamStringNull(itemVo.getNewSellPrice());
			ExceptionUtil.checkParamStringNull(itemVo.getPreSellPrice());

			ProductChangePriceItem item = new ProductChangePriceItem();
			ObjectConvertUtil.convertVoToPo(itemVo, item);
			item.setChangeNo(changeNo);
			productChangePriceItemDao.save(item);
		}
		return changeNo;
	}

	@Override
	@Transactional
	public void conserProductChangePrice(
			ProductChangePriceVo productChangePriceVo) throws Exception {
		ExceptionUtil.checkParamStringNull(productChangePriceVo.getChangeNo());
		ExceptionUtil.checkParamStringNull(productChangePriceVo.getConserName());

		ProductChangePrice productChangePrice = productChangePriceDao.get("changeNo", productChangePriceVo.getChangeNo());
		productChangePrice.setConserDesc(productChangePriceVo.getConserDesc());
		productChangePrice.setConserName(productChangePriceVo.getConserName());
		productChangePrice.setConserNo(productChangePriceVo.getChangeNo());
		productChangePrice.setConserTime(new Date());
		productChangePrice.setStatus(productChangePriceVo.getStatus());
		if(productChangePrice.getStatus()==CommodityConstant.CENSOR_PASS){
			for(ProductChangePriceItem item:productChangePrice.getItems()){
				Product product = productDao.get("prodNo", item.getProdNo());
				product.setPrice(item.getNewSellPrice());
				productDao.update(product);
			}
		}
		productChangePriceDao.update(productChangePrice);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductChangePrice> getProductChangePriceList(
			ProductChangePriceVo productChangePriceVo) throws Exception {
		StringBuffer hql = new StringBuffer();
		Map<String,Object> values = new HashMap<String,Object>();
		hql.append(" from ProductChangePrice pcp where 1=1 ");
		if(LogicUtil.isNotNullAndEmpty(productChangePriceVo.getChangeNo())){
			hql.append(" and pcp.changeNo = :changeNo ");
			values.put("changeNo", productChangePriceVo.getChangeNo());
		}
		if(LogicUtil.isNotNullAndEmpty(productChangePriceVo.getConserNo())){
			hql.append(" and pcp.conserNo = :conserNo ");
			values.put("conserNo", productChangePriceVo.getConserNo());
		}
		if(LogicUtil.isNotNullAndEmpty(productChangePriceVo.getOperatorNo())){
			hql.append(" and pcp.operatorNo = :operatorNo ");
			values.put("operatorNo", productChangePriceVo.getOperatorNo());
		}
		if(LogicUtil.isNotNullAndEmpty(productChangePriceVo.getConserName())){
			hql.append(" and pcp.conserName like :conserName ");
			values.put("conserName","%"+ productChangePriceVo.getConserName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(productChangePriceVo.getOperatorName())){
			hql.append(" and pcp.operatorName like :operatorName ");
			values.put("operatorName","%"+ productChangePriceVo.getOperatorName()+"%");
		}
		if(productChangePriceVo.getStatus()>0){
			hql.append(" and pcp.status = :status ");
			values.put("status", productChangePriceVo.getStatus());
		}
		if(LogicUtil.isNotNull(productChangePriceVo.getConserTimeStart())&&
				LogicUtil.isNotNull(productChangePriceVo.getConserTimeStart())){
			hql.append(" and (pcp.conserTime between :conserTimeStart and :conserTimeEnd ");
			values.put("conserTimeStart", productChangePriceVo.getConserTimeStart());
			values.put("conserTimeEnd", productChangePriceVo.getConserTimeEnd());
		}
		if(LogicUtil.isNotNull(productChangePriceVo.getOperatorTimeStart())&&
				LogicUtil.isNotNull(productChangePriceVo.getOperatorTimeStart())){
			hql.append(" and (pcp.operatorTime between :operatorTimeStart and :operatorTimeEnd ");
			values.put("operatorTimeStart", productChangePriceVo.getOperatorTimeStart());
			values.put("operatorTimeEnd", productChangePriceVo.getOperatorTimeEnd());
		}
		return productChangePriceDao.createQuery(hql.toString(), values).list();
	}

	@Override
	@Transactional(readOnly = true)
	public PageFinder<ProductChangePrice> pagedProductChangePriceList(
			ProductChangePriceVo productChangePriceVo) throws Exception {
		StringBuffer hql = new StringBuffer();
		Map<String,Object> values = new HashMap<String,Object>();
		hql.append(" from ProductChangePrice pcp where 1=1 ");
		if(LogicUtil.isNotNullAndEmpty(productChangePriceVo.getChangeNo())){
			hql.append(" and pcp.changeNo = :changeNo ");
			values.put("changeNo", productChangePriceVo.getChangeNo());
		}
		if(LogicUtil.isNotNullAndEmpty(productChangePriceVo.getConserNo())){
			hql.append(" and pcp.conserNo = :conserNo ");
			values.put("conserNo", productChangePriceVo.getConserNo());
		}
		if(LogicUtil.isNotNullAndEmpty(productChangePriceVo.getOperatorNo())){
			hql.append(" and pcp.operatorNo = :operatorNo ");
			values.put("operatorNo", productChangePriceVo.getOperatorNo());
		}
		if(LogicUtil.isNotNullAndEmpty(productChangePriceVo.getConserName())){
			hql.append(" and pcp.conserName like :conserName ");
			values.put("conserName","%"+ productChangePriceVo.getConserName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(productChangePriceVo.getOperatorName())){
			hql.append(" and pcp.operatorName like :operatorName ");
			values.put("operatorName","%"+ productChangePriceVo.getOperatorName()+"%");
		}
		if(productChangePriceVo.getStatus()>0){
			hql.append(" and pcp.status = :status ");
			values.put("status", productChangePriceVo.getStatus());
		}
		if(LogicUtil.isNotNull(productChangePriceVo.getConserTimeStart())&&
				LogicUtil.isNotNull(productChangePriceVo.getConserTimeStart())){
			hql.append(" and (pcp.conserTime between :conserTimeStart and :conserTimeEnd) ");
			values.put("conserTimeStart", productChangePriceVo.getConserTimeStart());
			values.put("conserTimeEnd", productChangePriceVo.getConserTimeEnd());
		}
		if(LogicUtil.isNotNull(productChangePriceVo.getOperatorTimeStart())&&
				LogicUtil.isNotNull(productChangePriceVo.getOperatorTimeStart())){
			hql.append(" and (pcp.operatorTime between :operatorTimeStart and :operatorTimeEnd) ");
			values.put("operatorTimeStart", productChangePriceVo.getOperatorTimeStart());
			values.put("operatorTimeEnd", productChangePriceVo.getOperatorTimeEnd());
		}
		return productChangePriceDao.pagedByHQL(hql.toString(), productChangePriceVo.getPageNo(), productChangePriceVo.getPageSize(), values);
	}

	@Override
	@Transactional
	public void handleStock(ProductStockVo stockVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(stockVo.getProductNo());
		ProductStock stock = productStockDao.get("productNo", stockVo.getProductNo());
		if(stockVo.getHandleTarget()==0){
			//操作实库
			if(stockVo.getHandleType()==0){
				//加
				stock.setStock(stock.getStock()+stockVo.getHandleNum());
			}else if(stockVo.getHandleType()==1){
				//减
				stock.setStock(stock.getStock()-stockVo.getHandleNum());
			}
			if(stock.getStock()<0){
				throw new ParamException("00000000", "库存不足");
			}
		}else if(stockVo.getHandleTarget()==1){
			//操作虚库
			if(stockVo.getHandleType()==0){
				//加
				stock.setVirtualStock(stock.getVirtualStock()+stockVo.getHandleNum());
			}else if(stockVo.getHandleType()==1){
				//减
				stock.setVirtualStock(stock.getVirtualStock()-stockVo.getHandleNum());
			}
			if(stock.getVirtualStock()<0){
				throw new ParamException("00000000", "库存不足");
			}
		}
		productStockDao.update(stock);
	}

	@Override
	@Transactional
	public void initStock(ProductStockVo stockVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(stockVo.getProductNo());
		ProductStock stock = new ProductStock();
		ObjectConvertUtil.convertVoToPo(stockVo, stock);
		productStockDao.save(stock);
	}

	@Override
	@Transactional(readOnly = true)
	public ProductStock getStock(ProductStockVo stockVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(stockVo.getProductNo());
		return productStockDao.get("productNo", stockVo.getProductNo());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Commodity> queryCommodity(ProductVo productVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(productVo.getProdNo());
		StringBuffer sql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		sql.append("select c.* from tbl_product p join tbl_commodity c on p.commo_no=c.commo_no where 1=1 ");
		sql.append(" and prod_no = :prodNo ");
		values.put("prodNo", productVo.getProdNo());
		return commodityDao.getListBySql(sql.toString(), values);
	}

	@Override
	@Transactional
	public void batchHandleStock(List<ProductStockVo> stockVos) throws Exception {
		ExceptionUtil.checkParamCollection(stockVos);
		for(ProductStockVo stockVo:stockVos){
			ExceptionUtil.checkParamStringNullAndEmpty(stockVo.getProductNo());
			ProductStock stock = productStockDao.get("productNo", stockVo.getProductNo());
			if(stockVo.getHandleTarget()==0){
				//操作实库
				if(stockVo.getHandleType()==0){
					//加
					stock.setStock(stock.getStock()+stockVo.getHandleNum());
				}else if(stockVo.getHandleType()==1){
					//减
					stock.setStock(stock.getStock()-stockVo.getHandleNum());
				}
				if(stock.getStock()<0){
					throw new ParamException("00000000", "库存不足");
				}
			}else if(stockVo.getHandleTarget()==1){
				//操作虚库
				if(stockVo.getHandleType()==0){
					//加
					stock.setVirtualStock(stock.getVirtualStock()+stockVo.getHandleNum());
				}else if(stockVo.getHandleType()==1){
					//减
					stock.setVirtualStock(stock.getVirtualStock()-stockVo.getHandleNum());
				}
				if(stock.getVirtualStock()<0){
					throw new ParamException("00000000", "库存不足");
				}
			}
			productStockDao.update(stock);
		}
	}
	@Override
	@Transactional(readOnly = true)
	public CommodityVo getCommodityDetail(String productNo,String memberNo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(productNo);
		String commoNo = productNo.substring(0, productNo.length()-2);
		Commodity commodity = commodityDao.get("commoNo", commoNo);
		List<Product> products = productDao.getList("prodNo",productNo);
		if(products!=null&&!products.isEmpty()){
			for(Product prod:products){
				if(prod.getStockPreemption()==null)
					prod.setStockPreemption(0);
			}
		}
		List<CommodityPic> commodityPics = commodityPicDao.getList("commoNo",commoNo);
		Criteria criteriaComment = commodityCommentDao.createCriteria(Restrictions.eq("productNo", productNo));
		criteriaComment.add(Restrictions.eq("deleteFlag", (byte)0));
		int commentNum = commodityCommentDao.getRowCount(criteriaComment);
		criteriaComment = commodityCommentDao.createCriteria(Restrictions.eq("productNo", productNo));
		criteriaComment.setMaxResults(1);//取一条
		criteriaComment.addOrder(Order.desc("createTime"));
		List<CommodityComment> commodityComments = criteriaComment.list();
		Criteria criteria = commodityCollectDao.createCriteria(Restrictions.eq("commodityNo", commoNo));
		int collectNum = commodityCollectDao.getRowCount(criteria);
		criteria = commodityCollectDao.createCriteria(Restrictions.eq("commodityNo", commoNo));
		criteria.add(Restrictions.eq("memberNo", memberNo));
		if(commodity!=null){
			commodity.setIsCollect(commodityCollectDao.getRowCount(criteria)==0?"0":"1");
			commodity.setProducts(products);
			commodity.setPics(commodityPics);
			commodity.setCommodityComments(commodityComments);
			commodity.setCollectNum(collectNum);
			commodity.setCommentNum(commentNum);
		}
		CommodityVo commodityVo = new CommodityVo();
		ObjectConvertUtil.convertPoToVo(commodity, commodityVo);
		
		//图文详情
		List<CommodityDetailPic> commodityDetailPics = commodityDetailPicDao.getList("commoNo", commoNo);
		if(commodityDetailPics!=null&&!commodityDetailPics.isEmpty()){
			List<CommodityDetailPicVo> comDetailVoPics = new ArrayList<CommodityDetailPicVo>();
			for(CommodityDetailPic r:commodityDetailPics){
				CommodityDetailPicVo comDetailVoPic = new CommodityDetailPicVo();
				ObjectConvertUtil.convertPoToVo(r, comDetailVoPic);
				comDetailVoPics.add(comDetailVoPic);
			}
			commodityVo.setCommoDetailPics(comDetailVoPics);
		}
		
		commodityVo.setProducts(products);
		commodityVo.setCommodityComments(commodityComments);
		commodityVo.setPics(commodityPics);
		String isGroupBuy = products.get(0).getIsGroupbuy();
		if(isGroupBuy!=null&&isGroupBuy.equals("2")){//团购商品
			Map<String,Object> values = new HashMap<String, Object>();
			values.put("refCommoNo", commoNo);
			values.put("shopNo", commodity.getSellerNo());
			Criteria criteriaPromotion = promotionDao.createCriteria(Restrictions.eq("refCommoNo", commoNo));
			criteriaPromotion.add(Restrictions.eq("shopNo", commodity.getSellerNo()));
			criteriaPromotion.add(Restrictions.ge("endTime", new Date()));
			criteriaPromotion.add(Restrictions.le("startTime",  new Date()));
			criteriaPromotion.add(Restrictions.eq("deleteFlag",  "0"));
			Promotion promotion =  (criteriaPromotion.uniqueResult()==null?null:(Promotion)criteriaPromotion.uniqueResult());
			if(promotion!=null){
				commodityVo.setAlreadyBuy(promotion.getAlreadyCount()==null?0:promotion.getAlreadyCount());//已参团数
				commodityVo.setGroupPrice(promotion.getDiscountAmount()==null?0d:promotion.getDiscountAmount());//团购价
				commodityVo.setCoupCount(promotion.getCoupCount());//最小成团数
				commodityVo.setEndTime(promotion.getEndTime());
				commodityVo.setLimitCount(promotion.getLimitCount());//每人限购数
				//是否已抢购
				String promotionNo = promotion.getPromotionNo();
				com.zl.bgec.basicapi.order.po.Order order = this.getOrderByBuyer(promotionNo,memberNo);
				String buyed = this.getBuyedStatus(order);
				commodityVo.setBuyed(buyed);
				if(order!=null){
					commodityVo.setBasicStatus(order.getBasicState());
					commodityVo.setOrderNo(order.getOrderNo());
				}else{
					commodityVo.setBasicStatus("0");
					commodityVo.setOrderNo("0");
				}
			}
		}
		commodityVo.setSellNum(commodityVo.getSellNum()==null?0:commodityVo.getSellNum());
		return commodityVo;
	}

	private com.zl.bgec.basicapi.order.po.Order getOrderByBuyer(String promotionNo, String buyerNo) {
		Criteria criteria = orderDao.createCriteria(Restrictions.eq("orderCouponsNo", promotionNo));
		criteria.add(Restrictions.eq("buyerNo", buyerNo));
		criteria.add(Restrictions.ne("deleteFlag", (byte)1));
		criteria.add(Restrictions.ne("basicState", OrderConstants.BASIC_STATE_CLOSED));
		List<com.zl.bgec.basicapi.order.po.Order> orders = criteria.list();
		if(orders != null && !orders.isEmpty())
			return orders.get(0);
		return null;
	}

	private String getBuyedStatus(com.zl.bgec.basicapi.order.po.Order order) {
		if(order==null)
			return "0";
		if(order.getBasicState().equals(OrderConstants.BASIC_STATE_WAITING_PAY))
			return "1";
		else
			return "2";
	}

	@Override
	@Transactional
	public CommodityVo getCommodityDetailNoUserId(String productNo,String memberNo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(productNo);
		String commoNo = productNo.substring(0, productNo.length()-2);
		Commodity commodity = commodityDao.get("commoNo", commoNo);
		List<Product> products = productDao.getList("prodNo",productNo);
		List<CommodityPic> commodityPics = commodityPicDao.getList("commoNo",commoNo);
		Criteria criteriaComment = commodityCommentDao.createCriteria(Restrictions.eq("productNo", productNo));
		criteriaComment.add(Restrictions.eq("deleteFlag", (byte)0));
		int commentNum = commodityCommentDao.getRowCount(criteriaComment);
		criteriaComment = commodityCommentDao.createCriteria(Restrictions.eq("productNo", productNo));
		criteriaComment.setMaxResults(1);//取一条
		criteriaComment.addOrder(Order.desc("createTime"));
		List<CommodityComment> commodityComments = criteriaComment.list();
		Criteria criteria = commodityCollectDao.createCriteria(Restrictions.eq("commodityNo", commoNo));
		int collectNum = commodityCollectDao.getRowCount(criteria);
		if(commodity!=null){
			commodity.setIsCollect("0");
			commodity.setProducts(products);
			commodity.setPics(commodityPics);
			commodity.setCommodityComments(commodityComments);
			commodity.setCollectNum(collectNum);
			commodity.setCommentNum(commentNum);
		}
		CommodityVo commodityVo = new CommodityVo();
		ObjectConvertUtil.convertPoToVo(commodity, commodityVo);
		commodityVo.setProducts(products);
		commodityVo.setCommodityComments(commodityComments);
		commodityVo.setPics(commodityPics);
		String isGroupBuy = products.get(0).getIsGroupbuy();
		if(isGroupBuy!=null&&isGroupBuy.equals("2")){//团购商品
			Map<String,Object> values = new HashMap<String, Object>();
			values.put("refCommoNo", commoNo);
			values.put("shopNo", commodity.getSellerNo());
			Promotion promotion = promotionDao.get(values);
			if(promotion!=null){
				commodityVo.setAlreadyBuy(promotion.getAlreadyCount()==null?0:promotion.getAlreadyCount());
				commodityVo.setGroupPrice(promotion.getDiscountAmount()==null?0d:promotion.getDiscountAmount());
				commodityVo.setCoupCount(promotion.getCoupCount());
				commodityVo.setEndTime(promotion.getEndTime());
				commodityVo.setLimitCount(promotion.getLimitCount());
			}
		}
		commodityVo.setSellNum(commodityVo.getSellNum()==null?0:commodityVo.getSellNum());
		return commodityVo;
	}

	@Override
	@Transactional(readOnly=true)
	public PageFinder<CommodityComment> pageCommdityCommentList(String productNo,int pageNo,int pageSize)
			throws Exception {
		StringBuffer hql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		hql.append(" from CommodityComment cc where cc.deleteFlag = :deleteFlag ");
		values.put("deleteFlag", (byte)0);
		if(LogicUtil.isNotNullAndEmpty(productNo)){
			hql.append(" and cc.productNo= :productNo ");
			values.put("productNo", productNo);
		}
		return commodityCommentDao.pagedByHQL(hql.toString(),pageNo, pageSize, values);
	}

	@Override
	public ProductVo checkProdStock(String prodNo) throws Exception {
		Product prod = productDao.get("prodNo",prodNo);
		if(prod != null){
			ProductVo prodVo = new ProductVo();
			ObjectConvertUtil.convertPoToVo(prod, prodVo);
			int stock = this.getStock(prod.getStock());
			int stockPreemption = this.getStockPreemption(prod.getStockPreemption());
			int leaveStock = stock-stockPreemption;
			prodVo.setStock(stock);
			prodVo.setStockPreemption(stockPreemption);
			prodVo.setLeaveStock(String.valueOf(leaveStock));
			return prodVo;
		}
		return null;
	}

	private int getStockPreemption(Integer stockPreemption) {
		return stockPreemption==null?0:stockPreemption;
	}
	private int getStock(Integer stock) {
		return stock==null?0:stock;
	}
	
}
