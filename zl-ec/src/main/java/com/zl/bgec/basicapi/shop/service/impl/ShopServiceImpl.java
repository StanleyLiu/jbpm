package com.zl.bgec.basicapi.shop.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.reflect.TypeToken;
import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.basecomponent.utils.ObjectConvertUtil;
import com.zl.bgec.basicapi.cart.dao.ICartItemDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityCatDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityDao;
import com.zl.bgec.basicapi.commodity.dao.IProductDao;
import com.zl.bgec.basicapi.common.util.ExceptionUtil;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.LogicUtil;
import com.zl.bgec.basicapi.common.utils.ImageUtil;
import com.zl.bgec.basicapi.order.common.OrderConstants;
import com.zl.bgec.basicapi.order.dao.IOrderDao;
import com.zl.bgec.basicapi.order.po.Order;
import com.zl.bgec.basicapi.order.rest.FrontOrderRest;
import com.zl.bgec.basicapi.promotion.dao.IPromotionDao;
import com.zl.bgec.basicapi.promotion.service.IPromotionService;
import com.zl.bgec.basicapi.shop.dao.IShopCollectDao;
import com.zl.bgec.basicapi.shop.dao.IShopDao;
import com.zl.bgec.basicapi.shop.po.Shop;
import com.zl.bgec.basicapi.shop.service.IShopService;
import com.zl.bgec.basicapi.shop.vo.ShopVo;

@Service
public class ShopServiceImpl implements IShopService {
	private static Logger log = Logger.getLogger(ShopServiceImpl.class);

	@Resource
	private IShopCollectDao shopCollectDao;
	@Resource
	private IShopDao shopDao;
	@Resource
	private ImageUtil imageUtil;
	@Resource
	private ICommodityDao commodityDao;
	@Resource
	private IPromotionDao promotionDao;
	@Resource
	private IProductDao productDao;
	@Resource
	private IOrderDao orderDao;
	@Resource
	private ICommodityCatDao commodityCatDao;
	@Resource
	IPromotionService promotionService;
	@Resource
	private ICartItemDao cartItemDao;
	@Override
	@Transactional
	/**
	 * 保存店铺信息
	 */
	public Shop saveShop(ShopVo shopVo) throws Exception {
		Shop shop = new Shop();
		ObjectConvertUtil.convertVoToPo(shopVo, shop);
		shop.setShopCreateTime(new Date());
		shop.setStatus(0);
		shop.setShopNo(shop.createKey());
		String shopLog = shop.getShopLogo();
		// 保存店铺标志
		if(shopLog!=null&&shopLog.startsWith("data:")){
			String shopLogo = imageUtil.saveImg(shopLog, shop.getShopTypeNo(),
					"shop");
			shop.setShopLogo(shopLogo);
		}
		String json = shop.getShopSign();
		List<Map<String, String>> list = GsonUtil.fromJson(json,
				new TypeToken<List<Map<String, String>>>() {
				}.getType());
		if (list != null && !list.isEmpty()) {// 保存店铺图片
			for (Map<String, String> map : list) {
				String img = map.get("url");
				if(img!=null&&img.startsWith("data:")){
					String url = imageUtil.saveImg(img, shop.getShopTypeNo(),
							"shop");
					map.put("url", url);
				}
			}
			shop.setShopSign(GsonUtil.toJson(list));
		}
		String licenseDuplicatePic = shop.getLicenseDuplicatePic();
		// 保存营业执照副本照片
		if(licenseDuplicatePic!=null&&licenseDuplicatePic.startsWith("data:")){
			shop.setLicenseDuplicatePic(imageUtil.saveImg(licenseDuplicatePic,
					shop.getShopTypeNo(), "shop"));
		}
		// 保存法人身份证照片正面
		if(shop.getCorporateIdcardPicFont()!=null&&shop.getCorporateIdcardPicFont().startsWith("data:")){
			shop.setCorporateIdcardPicFont(imageUtil.saveImg(
			shop.getCorporateIdcardPicFont(), shop.getShopTypeNo(), "shop"));
		}
		// 保存法人身份证照片背面
		if(shop.getCorporateIdcardPicBack()!=null&&shop.getCorporateIdcardPicBack().startsWith("data:")){
			shop.setCorporateIdcardPicBack(imageUtil.saveImg(
			shop.getCorporateIdcardPicBack(), shop.getShopTypeNo(), "shop"));
		}
		// 保存身份证照片正面
		if(shop.getIdcardPicFont()!=null&&shop.getIdcardPicFont().startsWith("data:")){
			shop.setIdcardPicFont(imageUtil.saveImg(shop.getIdcardPicFont(),
			shop.getShopTypeNo(), "shop"));
		}
		// 保存身份证背面
		if(shop.getIdcardPicBack()!=null&&shop.getIdcardPicBack().startsWith("data:")){
			shop.setIdcardPicBack(imageUtil.saveImg(shop.getIdcardPicBack(),
			shop.getShopTypeNo(), "shop"));
		}
		String id = shopDao.save(shop).toString();

		shop.setId(id);
		return shop;
	}

	@Override
	@Transactional(readOnly = true)
	public Shop getShopInfo(String shopNo) throws Exception {
		return shopDao.get("shopNo", shopNo);
	}

	@Override
	@Transactional
	public Shop updateShop(ShopVo shopVo,String flag) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(shopVo.getShopNo());
		Shop shop = shopDao.get("shopNo", shopVo.getShopNo());
		if (StringUtils.isNotBlank(shopVo.getShopLogo())) {
			if(shopVo.getShopLogo()!=null&&shopVo.getShopLogo().startsWith("data:")){
				shop.setShopLogo(imageUtil.saveImg(shopVo.getShopLogo(),
						shop.getShopTypeNo(), "shop"));
			}
		}
		if (LogicUtil.isNotNullAndEmpty(shopVo.getShopTypeNo())) {
			shop.setShopTypeNo(shopVo.getShopTypeNo());
		}
		if (LogicUtil.isNotNullAndEmpty(shopVo.getShopSummary())) {
			shop.setShopSummary(shopVo.getShopSummary());
		}
		if(flag!=null&&flag.equals("2")){
			shop.setStatus(1);//新增认证信息，状态改为待审核
			String shopModel = shopVo.getShopModel();
			shop.setShopModel(shopModel);
			ExceptionUtil.checkParamStringNullAndEmpty(shopVo.getShopModel());
		}
		if (null != shopVo.getDeliveryFee()) {
			shop.setDeliveryFee(shopVo.getDeliveryFee());
		}
		if (LogicUtil.isNotNullAndEmpty(shopVo.getShopName())) {
			shop.setShopName(shopVo.getShopName());
		}
		if (LogicUtil.isNotNullAndEmpty(shopVo.getShopAddress())) {
			shop.setShopAddress(shopVo.getShopAddress());
		}
		if(StringUtils.isNotBlank(shopVo.getShopCoordination())){
			shop.setShopCoordination(shopVo.getShopCoordination());
		}
		if (LogicUtil.isNotNullAndEmpty(shopVo.getPhone())) {
			shop.setPhone(shopVo.getPhone());
		}
		if (shopVo.getSellScope()!=null&&!shopVo.getSellScope().equals(0)) {
			shop.setSellScope(shopVo.getSellScope());
		}
		if (null!=shopVo.getBeginTime()) {
			shop.setBeginTime(shopVo.getBeginTime());
		}
		if (null!=shopVo.getEndTime()) {
			shop.setEndTime(shopVo.getEndTime());
		}
		if (null!=shopVo.getDeliveryFee()) {
			shop.setDeliveryFee(shopVo.getDeliveryFee());
		}
		if (LogicUtil.isNotNullAndEmpty(shopVo.getDeliveryType())) {
			shop.setDeliveryType(shopVo.getDeliveryType());
		}
		if (StringUtils.isNotBlank(shopVo.getShopSign())) {
			List<Map<String, String>> list = GsonUtil.fromJson(
					shopVo.getShopSign(),
					new TypeToken<List<Map<String, String>>>() {
					}.getType());
			if (list != null && !list.isEmpty()) {
				for (Map<String, String> map : list) {
					String url = map.get("url");
					if (url != null && url.startsWith("data")) {
						url = imageUtil.saveImg(url, shop.getShopTypeNo(),
								"shop");
					}
					map.put("url", url);
				}
			}
			shop.setShopSign(GsonUtil.toJson(list));
		}
		if(flag!=null&&flag.equals("2")){
			if (shop.getStatus() != 2) {// 状态为 审核不通过 才能修改认证信息
				String shopModel = shop.getShopModel();
				String bankAccount = shopVo.getBankAccount();
				String bankName = shopVo.getBankName();
				String bankAccountName = shopVo.getBankAccountName();
				if (StringUtils.isNotBlank(bankAccount)) {
					shop.setBankAccount(bankAccount);//收款银行卡号
				}
				if (StringUtils.isNotBlank(bankName)) {
					shop.setBankName(bankName);//开户行
				}
				if (StringUtils.isNotBlank(bankAccountName)) {
					shop.setBankAccountName(bankAccountName);//帐号名称
				}
				if (shopModel!=null&&shopModel.equals("1")) {// 实体店 认证信息
					String licenseDuplicatePic = shopVo.getLicenseDuplicatePic();
					// 保存营业执照副本照片
					if (StringUtils.isNotBlank(licenseDuplicatePic)) {
						if (licenseDuplicatePic != null && licenseDuplicatePic.startsWith("data")) {
							shop.setLicenseDuplicatePic(imageUtil.saveImg(licenseDuplicatePic, shopVo.getShopTypeNo(), "shop"));
						}
					}
					String corporateIdcardPicFont = shopVo
							.getCorporateIdcardPicFont();
					// 保存法人身份证照片正面
					if (StringUtils.isNotBlank(corporateIdcardPicFont)) {
						if (corporateIdcardPicFont != null && corporateIdcardPicFont.startsWith("data")) {
							shop.setCorporateIdcardPicFont(imageUtil.saveImg(
								corporateIdcardPicFont, shop.getShopTypeNo(),
								"shop"));
						}
					}
					String corporateIdcardPicBack = shopVo
							.getCorporateIdcardPicBack();
					// 保存法人身份证照片背面
					if (StringUtils.isNotBlank(corporateIdcardPicBack)) {
						if (corporateIdcardPicBack != null && corporateIdcardPicBack.startsWith("data")) {
						shop.setCorporateIdcardPicBack(imageUtil.saveImg(
								corporateIdcardPicBack, shop.getShopTypeNo(),
								"shop"));
						}
					}
					if (LogicUtil.isNotNullAndEmpty(shopVo.getCompanyName())) {
						shop.setCompanyName(shopVo.getCompanyName());
					}
					if (LogicUtil.isNotNullAndEmpty(shopVo.getLicenseRegistNo())) {
						shop.setLicenseRegistNo(shopVo.getLicenseRegistNo());
					}
					if (null != shopVo.getLicenseStartTime()) {
						shop.setLicenseStartTime(shopVo.getLicenseStartTime());
					}
					if (null != shopVo.getLicenseEndTime()) {
						shop.setLicenseEndTime(shopVo.getLicenseEndTime());
					}
					if (LogicUtil.isNotNullAndEmpty(shopVo.getCorporateName())) {
						shop.setCorporateName(shopVo.getCorporateName());
					}
					if (LogicUtil.isNotNullAndEmpty(shopVo.getCorporateIdcardNo())) {
						shop.setCorporateIdcardNo(shopVo.getCorporateIdcardNo());
					}
				} else if (shopModel!=null&&shopModel.equals("2")) {// 个人店认证信息
					if (LogicUtil.isNotNullAndEmpty(shopVo.getRealName())) {
						shop.setRealName(shopVo.getRealName());
					}
					if (LogicUtil.isNotNullAndEmpty(shopVo.getIdcardNo())) {
						shop.setIdcardNo(shopVo.getIdcardNo());
					}
					String idcardPicBack = shopVo.getIdcardPicBack();
					if (StringUtils.isNotBlank(idcardPicBack )) {
						if (idcardPicBack != null && idcardPicBack.startsWith("data")) {
							shop.setIdcardPicBack(imageUtil.saveImg(idcardPicBack,
								shop.getShopTypeNo(), "shop"));
						}
					}
					String idcardPicFont = shopVo.getIdcardPicFont();
					if (StringUtils.isNotBlank(idcardPicFont )) {
						if (idcardPicFont != null && idcardPicFont.startsWith("data")) {
						shop.setIdcardPicFont(imageUtil.saveImg(idcardPicFont,
								shop.getShopTypeNo(), "shop"));
						}
					}
				}
			}
		}
		
		shopDao.update(shop);
		return shop;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getShopIndexInfo(String memberNo) throws Exception {
		String sql = "select tsi.shop_name shopName,"
				+ "tsi.shop_address shopAddress,"
				+ "tsi.shop_logo shopLogo,"
				+ "tsi.status status, "
				+ "tsi.shop_no shopNo "
				+ "from tbl_shop_info tsi where tsi.merch_no=:shopNo and tsi.status!='3'";
		Query query = shopDao.createSQLQuery(sql);
		query.setParameter("shopNo", memberNo);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> results = query.list();
		Map<String, Object> result = new HashMap<String, Object>();
		if (results != null && !results.isEmpty()) {
			result = results.get(0);
		}else{
			return null;
		}
		String shopNo = String.valueOf(result.get("shopNo"));
		Criteria criteria = commodityDao.createCriteria(Restrictions.eq(
				"sellerNo", shopNo));
		criteria.add(Restrictions.eq("deleteFlag", (byte)0));//未删除
		criteria.add(Restrictions.eq("publishState", "1"));//已上架
		int commodityCount = commodityDao.getRowCount(criteria);// 查询商品数量
		result.put("commodityCount", String.valueOf(commodityCount));
		criteria = shopCollectDao.createCriteria(Restrictions.eq("shopNo",
				shopNo));
		int collectCount = shopCollectDao.getRowCount(criteria);
		result.put("collectCount", String.valueOf(collectCount));
		Criteria promotionCriteria = promotionDao.createCriteria(Restrictions.eq("shopNo", shopNo));
		promotionCriteria.add(Restrictions.ge("endTime", new Date()));//未过期
		promotionCriteria.add(Restrictions.eq("status", "2"));//已上架
		promotionCriteria.add(Restrictions.eq("lockFlag","0"));//未锁定
		promotionCriteria.add(Restrictions.ne("promotionType","2"));//未锁定
		int count = promotionDao.getRowCount(promotionCriteria);
		result.put("promotionCount", count);
		List<String> values = new ArrayList<String>();
//		values.add(OrderConstants.BASIC_STATE_REFUND);
//		values.add(OrderConstants.BASIC_STATE_ALREADY_RECEIVE);
//		values.add(OrderConstants.BASIC_STATE_REFUND_APPLY);
		values.add(OrderConstants.BASIC_STATE_WAITING_DELIVERY);
//		values.add(OrderConstants.BASIC_STATE_WAITING_RETURN);
//		values.add(OrderConstants.BASIC_STATE_WAITING_PAY);
//		values.add(OrderConstants.BASIC_STATE_ALREADY_DELIVERY);
		Criteria criteriaOrder = orderDao.createCriteria(Restrictions.in("basicState", values));
		criteriaOrder.add(Restrictions.eq("deleteFlag", (byte)0));
		criteriaOrder.add(Restrictions.eq("shopNo", shopNo));
		String sqlGroupBuy = "select count(*) "+
				"  from tbl_promotion tp "+
				" left join tbl_product tpr on tp.ref_commo_no = tpr.commo_no"+
				" where tp.promotion_type='2' and tp.delete_flag='0' and tp.shop_no=:shopNo  and :curentTime between tp.start_time and tp.end_time ";
		Query groupBuyQuery = promotionDao.createSQLQuery(sqlGroupBuy);
		groupBuyQuery.setParameter("shopNo", shopNo);
		groupBuyQuery.setParameter("curentTime", new Date());
		BigInteger totalRows = (BigInteger)groupBuyQuery.uniqueResult();
		result.put("groupBuyCount",totalRows.intValue() );
		result.put("orderCount", orderDao.getRowCount(criteriaOrder));
		String gradeSql = "select  avg(tcc.service_grade) serviceGrade,"
						+" avg(tcc.delivery_grade) deliveryGrade"
						+" from tbl_commodity_comment tcc "
						+" where tcc.shop_no = :shopNo "
						+" group by(tcc.shop_no) ";
		Query queryGrade = shopDao.createSQLQuery(gradeSql);
		queryGrade.setParameter("shopNo", shopNo);
		queryGrade.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> list = queryGrade.list();
		if(list!=null&&!list.isEmpty()){
			Double servGrade = list.get(0).get("serviceGrade")==null?0:Double.valueOf(list.get(0).get("serviceGrade").toString());
			BigDecimal serviceGrade = new BigDecimal(servGrade);
			serviceGrade = serviceGrade.setScale(1,BigDecimal.ROUND_HALF_EVEN);
			result.put("serviceGrade", serviceGrade.doubleValue());
			Double delGrade = list.get(0).get("deliveryGrade")==null?0:Double.valueOf(list.get(0).get("deliveryGrade").toString());
			BigDecimal deliveryGrade = new BigDecimal(delGrade);
			deliveryGrade = deliveryGrade.setScale(1,BigDecimal.ROUND_HALF_EVEN);
			result.put("deliveryGrade", deliveryGrade.doubleValue());
		}else{
			result.put("serviceGrade", "0");
			result.put("deliveryGrade", "0");
		}
		
		return result;
	}

	@Override
	@Transactional
	public String closeShop(Shop shop) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(shop.getShopNo());
		Shop shopPo = shopDao.get("shopNo", shop.getShopNo());
		Criteria criteria = orderDao.createCriteria(Restrictions.eq("shopNo", shop.getShopNo()));
		criteria.add(Restrictions.not(Restrictions.in("basicState", new String[]{OrderConstants.BASIC_STATE_WAITING_PAY,OrderConstants.BASIC_STATE_COMPLETED,OrderConstants.BASIC_STATE_CLOSED,OrderConstants.BASIC_STATE_ALREADY_REFUND})));
		List<Order> orders = criteria.list();
		if(orders!=null&&!orders.isEmpty()){
			return "hasOrder";
		}else{
			shopPo.setStatus(3);
			shopDao.update(shopPo);
			cartItemDao.delete("shopNo",shop.getShopNo());
			return "success";
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getShop(String shopName,String shopTypeNo,String memberNo)
			throws Exception {
		String sql = "select "
				+ " tsi.shop_no shopNo, "
				+ " tsi.shop_name shopName, "
				+ " tsi.shop_logo shopLogo, "
				+ " tsi.shop_summary shopSummary, "
				+ " tsi.is_recommend isRecommend, "
				+ " tcc.serviceGrade serviceGrade, "
				+ " tcc.deliveryGrade deliveryGrade, "
				+ " if(tcs.shop_no is null,'0','1') isCollect "
				+ " from tbl_shop_info tsi left join   "
				+ " (select avg(tcc.service_grade) serviceGrade,avg(tcc.delivery_grade) deliveryGrade , "
				+ " tcc.shop_no from tbl_commodity_comment tcc group by(tcc.shop_no) ) tcc on tsi.shop_no = tcc.shop_no "
				+ " left join (select * from tbl_shop_collect tcs where tcs.member_no = :memberNo) tcs on tsi.shop_no = tcs.shop_no "
				+ "where tsi.status=2 ";
		if(shopTypeNo!=null&&!shopTypeNo.equals("")){
			sql = sql + "and tsi.shop_type_no=:shopTypeNo ";
		}
		if(shopName!=null&&!shopName.equals("")){
			sql = sql + "and tsi.shop_name like :shopName ";
		}
		Query query = shopDao.createSQLQuery(sql);
		if(shopTypeNo!=null&&!shopTypeNo.equals("")){
			query.setParameter("shopTypeNo", shopTypeNo);
		}
		if(shopName!=null&&!shopName.equals("")){
			query.setParameter("shopName", "%"+shopName+"%");
		}
		query.setParameter("memberNo", memberNo);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> results = query.list();
		if(results!=null&&!results.isEmpty()){
			for (Map<String, Object> result : results) {
				Double servGrade = result.get("serviceGrade")==null?0:Double.valueOf(result.get("serviceGrade").toString());
				BigDecimal serviceGrade = new BigDecimal(servGrade);
				serviceGrade = serviceGrade.setScale(1,BigDecimal.ROUND_HALF_EVEN);
				result.put("serviceGrade", serviceGrade.doubleValue());
				Double delGrade = result.get("deliveryGrade")==null?0:Double.valueOf(result.get("deliveryGrade").toString());
				BigDecimal deliveryGrade = new BigDecimal(delGrade);
				deliveryGrade = deliveryGrade.setScale(1,BigDecimal.ROUND_HALF_EVEN);
				result.put("deliveryGrade", deliveryGrade.doubleValue());
			}
		}
		return results;
	}

	private double get_distance(double $lat1, double $lng1, double $lat2, double $lng2) 
	{ 
		int $len_type = 1, $decimal = 2;
	    double $PI = 3.1415926;
	    double $EARTH_RADIUS = 6378.137;
	     
	    double $radLat1 = $lat1 * $PI / 180.0; 
	    double $radLat2 = $lat2 * $PI / 180.0;
	     
	    double $a = $radLat1 - $radLat2; 
	    double $b = ($lng1 * $PI / 180.0) - ($lng2 * $PI / 180.0);
	     
	    double $s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin($a/2),2) + Math.cos($radLat1) * Math.cos($radLat2) * Math.pow(Math.sin($b/2),2)));
	    
	    $s = $s * $EARTH_RADIUS;
	    $s = Math.round($s * 1000);
	     
	    if ($len_type > 1) { $s /= 1000; }
	     
	    return Math.round($s);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getShopDetail(String shopNo,String memberNo) throws Exception {
		String sql = "select " + " tsi.shop_name shopName,"
				+ " tsi.status status,"
				+ " tsi.shop_logo shopLogo,"
				+ " tsi.shop_type_no shopTypeNo," 
				+ " tsi.shop_address address,"
				+ " tsi.shop_summary summary,"//简介
				+ " tsi.phone phone,"//电话
				+ " tsi.merch_no memberNo,"//商家编号
				+ " tsi.begin_time beginTime,"//营业开始时间
				+ " tsi.end_time endTime,"//营业结束
				+ " tsi.shop_sign shopSign,"//背景图
				+ " tsi.sell_scope sellScope,"//经营范围
				+ " tsi.delivery_type deliveryType,"//送货方式
				+ " ifnull(tsi.delivery_fee,0) deliveryFee,"//送货费
				+ " tsi.company_name companyName,"//公司名
				+ " tsi.license_regist_no licenseRegistNo,"//营业执照注册号
				+ " if(tsi.is_recommend is null,'0',tsi.is_recommend) isRecommend "
				+ " from tbl_shop_info tsi where tsi.shop_no = :shopNo"; 
		Query query = shopDao.createSQLQuery(sql);
		query.setParameter("shopNo", shopNo);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map<String, Object> map = (Map<String, Object>) query.uniqueResult();
		String sqlComment = "select avg(tcc.service_grade) serviceGrade,avg(tcc.delivery_grade) deliveryGrade "
				+ " from tbl_commodity_comment tcc where tcc.shop_no=:shopNo";
		query = shopDao.createSQLQuery(sqlComment);
		query.setParameter("shopNo", shopNo);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (map != null) {
			Map<String, Object> mapComment = (Map<String, Object>) query
					.uniqueResult();
			if (mapComment != null) {
				Double servGrade = mapComment.get("serviceGrade")==null?0:Double.valueOf(mapComment.get("serviceGrade").toString());
				BigDecimal serviceGrade = new BigDecimal(servGrade);
				serviceGrade = serviceGrade.setScale(1,BigDecimal.ROUND_HALF_EVEN);
				map.put("serviceGrade", serviceGrade.doubleValue());
				Double delGrade = mapComment.get("deliveryGrade")==null?0:Double.valueOf(mapComment.get("deliveryGrade").toString());
				BigDecimal deliveryGrade = new BigDecimal(delGrade);
				deliveryGrade = deliveryGrade.setScale(1,BigDecimal.ROUND_HALF_EVEN);
				map.put("deliveryGrade", deliveryGrade.doubleValue());
			} else {
				map.put("serviceGrade", 0);
				map.put("deliveryGrade", 0);
			}
		}
		
		if (map != null) {
			map.put("promotions", promotionService.getPromotionByShopNo(shopNo, memberNo));
		}
		String sqlProduct = "select IF(tp.prod_name is null,'',tp.prod_name) prodName,"
				+ "tp.model model,"
				+ " IF(tp.default_pic is null,'',tp.default_pic) prodPic,"
				+ " tp.price prodPrice,"
				+ " tp.is_groupbuy isGroupbuy,"
				+ " tp.prod_no prodNo,"
				+ " tp.sort sort,"
				+ " ifnull(tp.stock,0) stock,"
				+" ifnull(tp.stock_preemption,0) stockPreemption,"
				+ " IF(tp.sell_num is null,0,tp.sell_num) sellNum "
				+ " from tbl_product tp left join tbl_commodity tc on tc.commo_no = tp.commo_no"
				+ " where tc.seller_no = :shopNo and tc.publish_state ='1' and (tp.is_groupbuy is null or tp.is_groupbuy='1') and tp.delete_flag ='0' order by ifnull(tp.sort,2147483647) asc,tc.publish_time desc";
		query = productDao.createSQLQuery(sqlProduct);
//		query.setMaxResults(10);
		query.setParameter("shopNo",shopNo);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> prods = query.list();
		if(prods!=null&&!prods.isEmpty()){
			for(Map<String,Object> r:prods){
				//处理商品名+规格
				String prodName = (String) (r.get("prodName")==null?"":r.get("prodName"));
				String model = (String) (r.get("model")==null?"":r.get("model"));
				if(model!=null&&!model.equals("")){
					r.put("prodName", prodName+"("+model+")");
				}
			}
		}
			
		if(map!=null){
			map.put("products",prods);
		}
		String sqlGroupBuyProduct = "select IF(tp.prod_name is null,'',tp.prod_name) prodName,"
				+ "tp.model model,"
				+ " IF(tp.default_pic is null,'',tp.default_pic) prodPic,"
				+ " tp.price prodPrice,"
				+ " tp.is_groupbuy isGroupbuy,"
				+ " tp.prod_no prodNo,"
				+ " tp.sort sort,"
				+ " ifnull(tp.stock,0) stock,"
				+ " tpromotion.promotion_no promotionNo,"
				+ " tpromotion.discount_amount groupbuyPrice,"
				+ " tpromotion.end_time endTime,"
				+ " ifnull(tp.stock_preemption,0) stockPreemption,"
				+ " IF(tp.sell_num is null,0,tp.sell_num) sellNum "
				+ " from tbl_product tp left join tbl_commodity tc on tc.commo_no = tp.commo_no"
				+ " left join tbl_promotion tpromotion on tpromotion.ref_commo_no = tc.commo_no"
				+ " where tc.seller_no = :shopNo and tc.publish_state ='1'  and tp.is_groupbuy='2' and tpromotion.status='2' "
				+ " and now() between tpromotion.start_time and tpromotion.end_time "
				+ " and tpromotion.delete_flag ='0' and tp.delete_flag ='0' order by ifnull(tp.sort,2147483647) asc,tc.publish_time desc";
		query = productDao.createSQLQuery(sqlGroupBuyProduct);
//		query.setMaxResults(10);
		query.setParameter("shopNo",shopNo);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> groupBuyProds = query.list();
		if(groupBuyProds != null && !groupBuyProds.isEmpty()){
			for(Map<String,Object> gpd:groupBuyProds){
				//处理商品名+规格
				String prodName = (String) (gpd.get("prodName")==null?"":gpd.get("prodName"));
				String model = (String) (gpd.get("model")==null?"":gpd.get("model"));
				if(model!=null&&!model.equals("")){
					gpd.put("prodName", prodName+"("+model+")");
				}
				
				String promotionNo = (String) gpd.get("promotionNo");
				Order buyerOrder = this.getOrderByBuyer(promotionNo,memberNo);
				if(buyerOrder != null){
					if(buyerOrder.getBasicState().equals(OrderConstants.BASIC_STATE_WAITING_PAY))
						gpd.put("buyed", "1");//已抢购，未支付
					else
						gpd.put("buyed", "2");//已支付
				}
				else{
					gpd.put("buyed", "0");
				}
			}
		}
		if(map!=null){
			map.put("groupBuyProds",groupBuyProds);
		}
		
		String sqlCollect ="select * from tbl_shop_collect tsc where tsc.shop_no = :shopNo ";
		Query queryC =  shopCollectDao.createSQLQuery(sqlCollect);
		queryC.setParameter("shopNo", shopNo);
		List listCollect = queryC.list();
		if(map!=null){
			map.put("collectNum",listCollect==null?0:listCollect.size());
		}
//		Query queryCollect = shopCollectDao.createSQLQuery(sqlCollect+"and tsc.member_no = :memberNo");
//		queryCollect.setParameter("shopNo", shopNo);
//		queryCollect.setParameter("memberNo", memberNo);
//		List list = queryCollect.list();
//		map.put("isCollect", list!=null&&!list.isEmpty()?"1":"0");
		String sqlCat = "select tc.cat_no catNo,"
		+" IF(tcat.cat_name is null,'',tcat.cat_name) catName "
		+" from tbl_commodity tc "
		+" left join tbl_commo_category tcat "
		+" on tc.cat_no = tcat.cat_no where seller_no = :shopNo group by(tcat.cat_no)";
		query = commodityCatDao.createSQLQuery(sqlCat);
		query.setParameter("shopNo",shopNo);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> cats = query.list();
		if(map!=null){
			map.put("cats",cats);
		}
		if(map!=null&&map.get("shopSign") == null){
			map.put("shopSign", "");
		}
		return map;
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
	
	@Override
	@Transactional
	public Map<String, Object> getShopDetailNoUserId(String shopNo,String memberNo) throws Exception{
		String sql = "select " + " tsi.shop_name shopName,"
				+ " tsi.status status,"
				+ " tsi.shop_logo shopLogo," 
				+ " tsi.shop_address address,"
				+ " tsi.shop_summary summary,"//简介
				+ " tsi.phone phone,"//电话
				+ " tsi.merch_no memberNo,"//商家编号
				+ " tsi.begin_time beginTime,"//营业开始时间
				+ " tsi.end_time endTime,"//营业结束
				+ " tsi.shop_sign shopSign,"//背景图
				+ " tsi.sell_scope sellScope,"//经营范围
				+ " tsi.delivery_type deliveryType,"//送货方式
				+ " tsi.company_name companyName,"//公司名
				+ " tsi.license_regist_no licenseRegistNo,"//营业执照注册号
				+ " if(tsi.is_recommend is null,'0',tsi.is_recommend) isRecommend "
				+ " from tbl_shop_info tsi where tsi.shop_no = :shopNo"; 
		Query query = shopDao.createSQLQuery(sql);
		query.setParameter("shopNo", shopNo);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map<String, Object> map = (Map<String, Object>) query.uniqueResult();
		String sqlComment = "select avg(tcc.service_grade) serviceGrade,avg(tcc.delivery_grade) deliveryGrade "
				+ " from tbl_commodity_comment tcc where tcc.shop_no=:shopNo";
		query = shopDao.createSQLQuery(sqlComment);
		query.setParameter("shopNo", shopNo);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (map != null) {
			Map<String, Object> mapComment = (Map<String, Object>) query
					.uniqueResult();
			if (mapComment != null) {
				Double servGrade = mapComment.get("serviceGrade")==null?0:Double.valueOf(mapComment.get("serviceGrade").toString());
				BigDecimal serviceGrade = new BigDecimal(servGrade);
				serviceGrade = serviceGrade.setScale(1,BigDecimal.ROUND_HALF_EVEN);
				map.put("serviceGrade", serviceGrade.doubleValue());
				Double delGrade = mapComment.get("deliveryGrade")==null?0:Double.valueOf(mapComment.get("deliveryGrade").toString());
				BigDecimal deliveryGrade = new BigDecimal(delGrade);
				deliveryGrade = deliveryGrade.setScale(1,BigDecimal.ROUND_HALF_EVEN);
				map.put("deliveryGrade", deliveryGrade.doubleValue());
			} else {
				map.put("serviceGrade", 0);
				map.put("deliveryGrade", 0);
			}
		}
		
		if (map != null) {
			map.put("promotions", "");
		}
		String sqlProduct = "select IF(tp.prod_name is null,'',tp.prod_name) prodName,"
				+ " IF(tp.default_pic is null,'',tp.default_pic) prodPic,"
				+ " tp.price prodPrice,"
				+ " tp.prod_no prodNo,"
				+ " tp.sort sort,"
				+ " ifnull(tp.stock,0) stock,"
				+" ifnull(tp.stock_preemption,0) stockPreemption,"
				+ " IF(tp.sell_num is null,0,tp.sell_num) sellNum "
				+ " from tbl_product tp left join tbl_commodity tc on tc.commo_no = tp.commo_no"
				+ " where tc.seller_no = :shopNo and tc.publish_state ='1' and (tp.is_groupbuy is null or tp.is_groupbuy='1') and tp.delete_flag ='0' order by ifnull(tp.sort,2147483647) asc,tc.publish_time desc";
		query = productDao.createSQLQuery(sqlProduct);
//		query.setMaxResults(10);
		query.setParameter("shopNo",shopNo);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> prods = query.list();
		if(map!=null){
			map.put("products",prods);
		}
		String sqlGroupBuyProduct = "select IF(tp.prod_name is null,'',tp.prod_name) prodName,"
				+ " IF(tp.default_pic is null,'',tp.default_pic) prodPic,"
				+ " tp.price prodPrice,"
				+ " tp.prod_no prodNo,"
				+ " tp.sort sort,"
				+ " ifnull(tp.stock,0) stock,"
				+ " tpromotion.discount_amount groupbuyPrice,"
				+ " tpromotion.end_time endTime,"
				+" ifnull(tp.stock_preemption,0) stockPreemption,"
				+ " IF(tp.sell_num is null,0,tp.sell_num) sellNum "
				+ " from tbl_product tp left join tbl_commodity tc on tc.commo_no = tp.commo_no"
				+ " left join tbl_promotion tpromotion on tpromotion.ref_commo_no = tc.commo_no"
				+ " where tc.seller_no = :shopNo and tc.publish_state ='1'  and tp.is_groupbuy='2' and tpromotion.status='2' "
				+ " and now() between tpromotion.start_time and tpromotion.end_time "
				+ " and tpromotion.delete_flag ='0' and tp.delete_flag ='0' order by ifnull(tp.sort,2147483647) asc,tc.publish_time desc";
		query = productDao.createSQLQuery(sqlGroupBuyProduct);
//		query.setMaxResults(10);
		query.setParameter("shopNo",shopNo);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> groupBuyProds = query.list();
		if(map!=null){
			map.put("groupBuyProds",groupBuyProds);
		}
		String sqlCollect ="select * from tbl_shop_collect tsc where tsc.shop_no = :shopNo ";
		Query queryC =  shopCollectDao.createSQLQuery(sqlCollect);
		queryC.setParameter("shopNo", shopNo);
		List listCollect = queryC.list();
		if(map!=null){
			map.put("collectNum",listCollect==null?0:listCollect.size());
		}
//		Query queryCollect = shopCollectDao.createSQLQuery(sqlCollect+"and tsc.member_no = :memberNo");
//		queryCollect.setParameter("shopNo", shopNo);
//		queryCollect.setParameter("memberNo", memberNo);
//		List list = queryCollect.list();
//		map.put("isCollect", list!=null&&!list.isEmpty()?"1":"0");
		String sqlCat = "select tc.cat_no catNo,"
		+" IF(tcat.cat_name is null,'',tcat.cat_name) catName "
		+" from tbl_commodity tc "
		+" left join tbl_commo_category tcat "
		+" on tc.cat_no = tcat.cat_no where seller_no = :shopNo group by(tcat.cat_no)";
		query = commodityCatDao.createSQLQuery(sqlCat);
		query.setParameter("shopNo",shopNo);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> cats = query.list();
		if(map!=null){
			map.put("cats",cats);
		}
		return map;
	}

	@Override
	@Transactional(readOnly=true)
	public Shop getShopNo(String memberNo) throws Exception {
		Criteria criteria = shopDao.createCriteria(Restrictions.eq("memberNo", memberNo));
		criteria.add(Restrictions.ne("status", "3"));//未关闭
		return (Shop) criteria.uniqueResult();
	}

	@Override
	@Transactional(readOnly=true)
	public PageFinder<Map<String, Object>> pagedProduct(Map<String, String> map)
			throws Exception {
		String shopNo = String.valueOf(map.get("shopNo"));
		int pageNo = Integer.parseInt(map.get("pageNo"));
		int pageSize = Integer.parseInt(map.get("pageSize"));
		String catNo = String.valueOf(map.get("catNo")==null?"":map.get("catNo"));
		String sqlProduct = "select IF(tp.prod_name is null,'',tp.prod_name) prodName,"
				+ " IF(tp.default_pic is null,'',tp.default_pic) prodPic,"
				+ " tp.price prodPrice,"
				+ " tp.prod_no prodNo,"
				+ " ifnull(tp.stock,0) stock,"
				+" ifnull(tp.stock_preemption,0) stockPreemption,"
				+ " IF(tp.sell_num is null,0,tp.sell_num) sellNum ,"
				+" tc.is_recommend isRecommend "
				+ " from tbl_product tp left join tbl_commodity tc on tc.commo_no = tp.commo_no"
				+ " where tc.seller_no = :shopNo and tc.publish_state ='1' and tp.is_groupbuy !='2' " ;
		if(catNo!=null&&!catNo.isEmpty()){
			sqlProduct = sqlProduct+" and tc.cat_no=:catNo ";
		}
		sqlProduct = sqlProduct+" and tp.delete_flag ='0' order by ifnull(tp.sort,2147483647) asc, tp.sell_num desc,tc.publish_time desc";
		String countSql = "select count(*) "
				+ " from tbl_product tp left join tbl_commodity tc on tc.commo_no = tp.commo_no"
				+ " where tc.seller_no = :shopNo and tc.publish_state ='1' and tp.delete_flag ='0' ";
		if(catNo!=null&&!catNo.isEmpty()){
			countSql = countSql+" and tc.cat_no=:catNo ";
		}
		Query query = shopDao.createSQLQuery(sqlProduct);
		query = productDao.createSQLQuery(sqlProduct);
		query.setParameter("shopNo",shopNo);
		if(catNo!=null&&!catNo.isEmpty()){
			query.setParameter("catNo",catNo);
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Query queryCount = shopDao.createSQLQuery(countSql.toString());
		queryCount.setParameter("shopNo",shopNo);
		if(catNo!=null&&!catNo.isEmpty()){
			queryCount.setParameter("catNo",catNo);
		}
		BigInteger totalRows = (BigInteger)queryCount.uniqueResult();
		if(totalRows.intValue()==0){
			return new PageFinder<Map<String, Object>>(pageNo,pageSize,0);
		}
		PageFinder<Map<String, Object>> pageFinder = new PageFinder<Map<String, Object>>(pageNo,pageSize, totalRows.intValue());
		query.setMaxResults(pageFinder.getPageSize());
		query.setFirstResult(pageFinder.getStartOfPage());
		pageFinder.setData(query.list());
		return pageFinder;
	}
	@Override
	@Transactional(readOnly=true)
	public PageFinder<Map<String, Object>> pagedGroupBuyProduct(Map<String, String> map)
			throws Exception {
		String shopNo = String.valueOf(map.get("shopNo"));
		int pageNo = Integer.parseInt(map.get("pageNo"));
		int pageSize = Integer.parseInt(map.get("pageSize"));
		String catNo = String.valueOf(map.get("catNo")==null?"":map.get("catNo"));
		String sqlProduct = "select IF(tp.prod_name is null,'',tp.prod_name) prodName,"
				+ " IF(tp.default_pic is null,'',tp.default_pic) prodPic,"
				+ " tp.price prodPrice,"
				+ " tp.prod_no prodNo,"
				+ " tpromotion.start_time startTime,"
				+ " tpromotion.end_time endTime,"
				+ " tpromotion.discount_amount groupPrice,"
				+ " ifnull(tp.stock,0) stock,"
				+ " ifnull(tp.stock_preemption,0) stockPreemption,"
				+ " IF(tp.sell_num is null,0,tp.sell_num) sellNum ,"
				+ " tc.is_recommend isRecommend "
				+ " from tbl_product tp left join tbl_commodity tc on tc.commo_no = tp.commo_no"
				+ " left join tbl_promotion tpromotion on tpromotion.ref_commo_no = tc.commo_no"
				+ " where tc.seller_no = :shopNo and tc.publish_state ='1' and tp.is_groupbuy ='2' ";
		if(catNo!=null&&!catNo.isEmpty()){
			sqlProduct = sqlProduct+" and tc.cat_no=:catNo ";
		}
		sqlProduct = sqlProduct+"and tp.delete_flag ='0' "
				+ " and tpromotion.status='2' "
				+ " and now() between tpromotion.start_time and tpromotion.end_time "
				+ " and tp.delete_flag ='0' "
				+ " order by ifnull(tp.sort,2147483647) asc, tp.sell_num desc,tc.publish_time desc";
		String countSql = "select count(*) "
				+ " from tbl_product tp left join tbl_commodity tc on tc.commo_no = tp.commo_no"
				+ " left join tbl_promotion tpromotion on tpromotion.ref_commo_no = tc.commo_no"
				+ " where tc.seller_no = :shopNo"
				+ " and tpromotion.status='2' "
				+ " and now() between tpromotion.start_time and tpromotion.end_time "
				+ " and tc.publish_state ='1' and tp.delete_flag ='0' ";
		if(catNo!=null&&!catNo.isEmpty()){
			sqlProduct = sqlProduct + " and tc.cat_no=:catNo ";
		}
		Query query = shopDao.createSQLQuery(sqlProduct);
		query = productDao.createSQLQuery(sqlProduct);
		query.setParameter("shopNo",shopNo);
		if(catNo!=null&&!catNo.isEmpty()){
			query.setParameter("catNo",catNo);
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Query queryCount = shopDao.createSQLQuery(countSql.toString());
		queryCount.setParameter("shopNo",shopNo);
		if(catNo!=null&&!catNo.isEmpty()){
			queryCount.setParameter("catNo",catNo);
		}
		BigInteger totalRows = (BigInteger)queryCount.uniqueResult();
		if(totalRows.intValue()==0){
			return new PageFinder<Map<String, Object>>(pageNo,pageSize,0);
		}
		PageFinder<Map<String, Object>> pageFinder = new PageFinder<Map<String, Object>>(pageNo,pageSize, totalRows.intValue());
		query.setMaxResults(pageFinder.getPageSize());
		query.setFirstResult(pageFinder.getStartOfPage());
		pageFinder.setData(query.list());
		return pageFinder;
	}

	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> getDeliveryType(String shopNo) throws Exception {
		Shop shop = shopDao.get("shopNo",shopNo);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("deliveryType", shop.getDeliveryType());
		return map;
	}

	@Override
	@Transactional(readOnly=true)
	public PageFinder<Map<String, Object>> queryShopSummary(ShopVo shopVo)
			throws Exception {
		int pageNo = shopVo.getPageNo();
		int pageSize = shopVo.getPageSize();
		String shopType = shopVo.getShopTypeNo();
		String shopNo = shopVo.getShopNo();
		String sql = "select tsi.shop_no shopNo,tsi.shop_summary shopSummary,tsi.shop_name shopName from tbl_shop_info tsi where 1=1 ";
		String sqlCount = "select count(*) from tbl_shop_info tsi where 1=1 ";
		if(shopType!=null&&!shopType.equals("")){
			sql = sql +" and tsi.shop_Type_No=:shopTypeNo ";
			sqlCount = sqlCount +" and tsi.shop_Type_No=:shopTypeNo ";
		}
		if(shopNo!=null&&!shopNo.equals("")){
			sql = sql +" and tsi.shop_No=:shopNo ";
			sqlCount = sqlCount +" and tsi.shop_No=:shopNo ";
		}
		Query query = shopDao.createSQLQuery(sql);
		Query queryCount = shopDao.createSQLQuery(sqlCount.toString());
		if(shopType!=null&&!shopType.equals("")){
			query.setParameter("shopTypeNo", shopType);
			queryCount.setParameter("shopTypeNo", shopType);
		}
		if(shopNo!=null&&!shopNo.equals("")){
			query.setParameter("shopNo", shopNo);
			queryCount.setParameter("shopNo", shopNo);
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		BigInteger totalRows = (BigInteger)queryCount.uniqueResult();
		if(totalRows.intValue()==0){
			return new PageFinder<Map<String, Object>>(pageNo,pageSize,0);
		}
		PageFinder<Map<String, Object>> pageFinder = new PageFinder<Map<String, Object>>(pageNo,pageSize, totalRows.intValue());
		query.setMaxResults(pageFinder.getPageSize());
		query.setFirstResult(pageFinder.getStartOfPage());
		pageFinder.setData(query.list());
		return pageFinder;
	}

	@Override
	@Transactional(readOnly=true)
	public List<ShopVo> getMyShop(String memberNo) throws Exception {
		Criteria criteria = shopDao.createCriteria(Restrictions.eq("merchNo", memberNo));
		List<Shop> shops = criteria.list();
		List<ShopVo> shopVos = new ArrayList<ShopVo>();
		if(shops!=null&&!shops.isEmpty()){
			for (Shop shop : shops) {
				ShopVo shopVo = new ShopVo();
				ObjectConvertUtil.convertPoToVo(shop, shopVo);
				shopVos.add(shopVo);
			}
		}
		return shopVos;
	}

	@Override
	public List<Shop> getTestShopInfo(String column,String value) {
		List<Shop> shops = shopDao.getList(column,value);
		return shops;
	}

	@Override
	@Transactional(readOnly=true)
	public Shop getMycard(String memberNo) throws Exception {
		Criteria criteria = shopDao.createCriteria(Restrictions.eq("merchNo", memberNo));
		List<Shop> shops = criteria.list();
		if(shops!=null&&!shops.isEmpty()){
			for (Shop shop : shops) {
				if(shop.getStatus()!=3){
					return shop;
				}
			}
		}
		return null;
	}

	@Override
	@Transactional
	public void bindCard(ShopVo shopVo) throws Exception {
		Shop shop = shopDao.get("shopNo",shopVo.getShopNo());
		shop.setBankAccount(shopVo.getBankAccount());
		shop.setBankAccountName(shopVo.getBankAccountName());
		shop.setBankName(shopVo.getBankName());
		shopDao.update(shop);
	}
}
