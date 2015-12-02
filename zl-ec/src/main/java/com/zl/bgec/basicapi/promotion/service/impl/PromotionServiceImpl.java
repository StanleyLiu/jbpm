package com.zl.bgec.basicapi.promotion.service.impl;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
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

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.basecomponent.utils.ObjectConvertUtil;
import com.zl.bgec.basicapi.commodity.dao.ICommodityDao;
import com.zl.bgec.basicapi.commodity.dao.IProductDao;
import com.zl.bgec.basicapi.commodity.po.Commodity;
import com.zl.bgec.basicapi.commodity.po.Product;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.ResultVo;
import com.zl.bgec.basicapi.common.SendMessage;
import com.zl.bgec.basicapi.common.oauth2.CommonResponse;
import com.zl.bgec.basicapi.common.oauth2.SdkConstants;
import com.zl.bgec.basicapi.common.oauth2.Signature;
import com.zl.bgec.basicapi.common.oauth2.UserInfoFroBiz;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;
import com.zl.bgec.basicapi.common.utils.CreatorNoUtil;
import com.zl.bgec.basicapi.common.utils.ImageUtil;
import com.zl.bgec.basicapi.order.common.OrderConstants;
import com.zl.bgec.basicapi.order.dao.IOrderDao;
import com.zl.bgec.basicapi.order.dao.IOrderItemDao;
import com.zl.bgec.basicapi.order.po.Order;
import com.zl.bgec.basicapi.order.po.OrderItem;
import com.zl.bgec.basicapi.order.vo.ReturnOrderRecord;
import com.zl.bgec.basicapi.promotion.dao.IPromotionDao;
import com.zl.bgec.basicapi.promotion.dao.IPromotionInfoDao;
import com.zl.bgec.basicapi.promotion.po.Promotion;
import com.zl.bgec.basicapi.promotion.po.PromotionInfo;
import com.zl.bgec.basicapi.promotion.service.IPromotionService;
import com.zl.bgec.basicapi.promotion.vo.PromotionVo;
import com.zl.bgec.basicapi.shop.dao.IShopDao;
import com.zl.bgec.basicapi.shop.po.Shop;
import com.zl.bgec.basicapi.shop.vo.ResponseVo;

@Service
public class PromotionServiceImpl implements IPromotionService {

	private static Logger log = Logger.getLogger(PromotionServiceImpl.class);

	@Resource
	private SdkConstants sdkConstants;
	@Resource
	private IPromotionDao promotionDao;
	@Resource
	private ICommodityDao commodityDao;
	@Resource
	private IProductDao productDao;
	@Resource
	private IPromotionInfoDao promotionInfoDao;
	@Resource
	private IShopDao shopDao;
	@Resource
	private ImageUtil imageutil;
	@Resource
	private IOrderDao orderDao;
	@Resource
	private IOrderItemDao orderItemDao;
	@Resource
	private SendMessage sendMessage;
	@Value("${hua_wei_shop_no}")
	private String huaWeiShopNo;

	@Override
	@Transactional
	public String addPromotion(PromotionVo promotionVo) throws Exception {
		Promotion promotion = new Promotion();
		ObjectConvertUtil.convertVoToPo(promotionVo, promotion);
		promotion.setCreateTime(new Date());
		//log.info("addPromotion-promotionVo="+GsonUtil.toJson(promotionVo));
		//log.info("addPromotion-promotion="+GsonUtil.toJson(promotion));

		if(promotionVo.getPromotionType().equals("1")){//普通优惠券时，开始时间00:00,结束时间23:59,团购(2)时间不做处理
			this.setStartTime(promotion,promotionVo.getStartTime());
			this.setEndTime(promotion,promotionVo.getEndTime());
		}
		String commoNo = promotion.getRefCommoNo();
		if(commoNo!=null&&!commoNo.isEmpty()){
			Commodity commodity = commodityDao.get("commoNo",commoNo);
			if(commodity.getPublishState()!=null&&!commodity.getPublishState().equals("1")){
				commodity.setPublishState("1");
				commodity.setPublishTime(new Date());
				commodityDao.update(commodity);
			}
			Product product = productDao.get("commoNo",commoNo);
			product.setIsGroupbuy("2");
			productDao.update(product);
			promotion.setPromotionName(commodity.getCommoName());
		}

		String promotionNo = System.currentTimeMillis() + getRandomNumberStr(4);
		Shop shop = shopDao.get("shopNo",promotion.getShopNo());
		promotion.setPromotionNo(promotionNo);
		promotion.setStatus("2");//已上架
		promotion.setLockFlag("0");//未锁定
		promotion.setDeleteFlag("0");//未删除
		promotion.setIsRecommend("0");//未推荐
		promotion.setShopName(shop==null?"123123":shop.getShopName());
		Integer count = promotion.getCoupCount(); 
		promotion.setShopNo(promotionVo.getShopNo()==null?"123213":promotionVo.getShopNo());
		if(promotion.getPromotionType()!=null&&promotion.getPromotionType().equals("1")){//优惠券需要生成券
			for (int i = 0; i < count; i++) {
				PromotionInfo promotionInfo = new PromotionInfo();
				promotionInfo.setPromotionNo(promotionNo);
				promotionInfo.setStatus("0");//使用状态，0未使用
				promotionInfo.setShopNo(promotionVo.getShopNo()==null?"123213":promotionVo.getShopNo());
				promotionInfo.setCoupNo(CreatorNoUtil.getCode());
				promotionInfoDao.save(promotionInfo);
			}
		}
		String promotionType = promotionVo.getPromotionType();
		if(promotionType.equals("1")){//优惠券
			String promotionPicUrl = promotion.getPromotionPicUrl();
			if(promotionPicUrl!=null&&promotionPicUrl.startsWith("data:")){
				promotion.setPromotionPicUrl(imageutil.saveImg(promotionPicUrl, "01", "promotion"));
			}
		}else if(promotionType.equals("2")){//团购
			//TODO
		}

		promotionDao.save(promotion);
		return promotion.getPromotionNo();
	}

	private void setEndTime(Promotion promotion, Date endTime) {
		log.info("setEndTime-before="+endTime.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(endTime);
		cal.set(Calendar.HOUR_OF_DAY,cal.getActualMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE,cal.getActualMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND,cal.getActualMaximum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND,cal.getActualMaximum(Calendar.MILLISECOND));
		log.info("setEndTime-after="+cal.getTime().getTime());
		promotion.setEndTime(cal.getTime());
	}
	private void setStartTime(Promotion promotion, Date startTime) {
		log.info("setStartTime-before="+startTime.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(startTime);
		cal.set(Calendar.HOUR_OF_DAY,cal.getActualMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE,cal.getActualMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND,cal.getActualMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND,cal.getActualMinimum(Calendar.MILLISECOND));
		log.info("setStartTime-after="+cal.getTime().getTime());
		promotion.setEndTime(cal.getTime());
	}

	@Override
	@Transactional
	public String updatePromotion(PromotionVo promotionVo) throws Exception {
		Promotion promotion = promotionDao.get(promotionVo.getPromotionNo());
		Integer count = promotion.getCoupCount();

		for (int i = 0; i < promotionVo.getCoupCount()-count; i++) {
			PromotionInfo promotionInfo = new PromotionInfo();
			promotionInfo.setPromotionNo(promotionVo.getPromotionNo());
			promotionInfo.setStatus("0");//使用状态，0未使用
			promotionInfo.setShopNo(promotionVo.getShopNo());
			promotionInfo.setCoupNo(CreatorNoUtil.getCode());
			promotionInfoDao.save(promotionInfo);
		}
		promotion.setCoupCount(promotionVo.getCoupCount());
		promotion.setDescription(promotionVo.getDescription());
		promotion.setDiscountAmount(promotionVo.getDiscountAmount());
		promotion.setEndTime(promotionVo.getEndTime());
		promotion.setLeastAmount(promotionVo.getLeastAmount());
		promotion.setPromotionName(promotionVo.getPromotionName());
		if(promotionVo.getPromotionPicUrl()!=null&&promotionVo.getPromotionPicUrl().startsWith("data")){
			promotion.setPromotionPicUrl(imageutil.saveImg(promotionVo.getPromotionPicUrl(), "01", "promotion"));
		}
		promotion.setStartTime(promotionVo.getStartTime());
		promotion.setRefCommoNo(promotionVo.getRefCommoNo());

		promotionDao.update(promotion);
		return promotion.getPromotionNo();
	}

	private String getRandomNumberStr(int length) {
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(r.nextInt(10));
		}
		return sb.toString();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getPromotion(String shopNo,String type) throws Exception {
		String sql = "select tp.coup_count coupCount,"
				+ " tp.promotion_pic_url promotionPicUrl,"
				+ " tp.promotion_no promotionNo,"
				+ " tp.promotion_name promotionName,"
				+ " tp.least_amount leastAmount,"
				+ " tp.discount_amount discountAmount,"
				+ " tp.is_recommend isRecommend,"
				+ " tp.promotion_type promotionType,"
				+ " tp.start_time startTime,"
				+ " tp.end_time endTime,"
				+ " tt.counts usedCount,"
				+ " tpi1.counts fetchedCount,"
				+ " tp.lock_flag lockFlag"
				+ "  from tbl_promotion tp left join "
				+ "  (select count(*) counts,tpi.promotion_no promotionNo from tbl_promotion_info tpi where"
				+ " tpi.status = '1' and  tpi.shop_no =:shopNo1 group by tpi.promotion_no) as tt on tp.promotion_no = tt.promotionNo"
				+ "  left join (select count(*) counts,tpi.promotion_no promotionNo from tbl_promotion_info tpi where"
				+ " tpi.member_no is not null and  tpi.shop_no =:shopNo2 group by tpi.promotion_no) as tpi1 on tp.promotion_no = tpi1.promotionNo"
				+ "  where tp.shop_no = :shopNo3 ";
		if(type!=null&&!type.equals("")){
			if(type.equals("1")){//未上架
				sql = sql +" and tp.status = '1'  and tp.end_time>now() ";
			}else if(type.equals("2")){//已上架
				sql = sql +" and tp.status = '2'  and tp.end_time>now() ";
			}else if(type.equals("3")){//已过期
				sql = sql +" and tp.end_time<now()";
			}

		}
		Query query = promotionDao.createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setParameter("shopNo1", shopNo);
		query.setParameter("shopNo2", shopNo);
		query.setParameter("shopNo3", shopNo);
		List<Map<String, Object>> result = query.list();
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public PromotionVo getPromotionDetail(String promotionNo,String memberNo) throws Exception {
		Promotion promotion = promotionDao.get("promotionNo", promotionNo);
		PromotionVo promotionVo = new PromotionVo();
		ObjectConvertUtil.convertVoToPo(promotion, promotionVo);
		Criteria criteria = promotionInfoDao.createCriteria(Restrictions.eq("promotionNo", promotionNo));
		criteria.add(Restrictions.eq("status", "1"));//已使用
		int usedCount = promotionInfoDao.getRowCount(criteria);
		promotionVo.setUsedCount(usedCount);
		criteria = promotionInfoDao.createCriteria(Restrictions.eq("promotionNo", promotionNo));
		criteria.add(Restrictions.isNotNull("memberNo"));//已领取
		int fetchedCount = promotionInfoDao.getRowCount(criteria);
		if(memberNo!=null&&!memberNo.equals("")){
			criteria = promotionInfoDao.createCriteria(Restrictions.eq("promotionNo", promotionNo));
			criteria.add(Restrictions.eq("memberNo", memberNo));//
			criteria.add(Restrictions.eq("promotionNo", promotionNo));//
			List<PromotionInfo> list = promotionInfoDao.getListByCriteria(criteria);
			promotionVo.setIsfetched(list==null||list.isEmpty()?"0":"1");
		}
		if(promotionNo!=null&&!promotionNo.equals("")&&memberNo!=null&&!memberNo.equals("")){
			Map<String,Object> values = new HashMap<String, Object>();
			values.put("promotionNo", promotionNo);
			values.put("memberNo", memberNo);
			PromotionInfo promoInfo = promotionInfoDao.get(values);
			if(promoInfo!=null)
				promotionVo.setCouponNumber(promoInfo.getCouponNumber());
		}
		promotionVo.setFetchedCount(fetchedCount);
		return promotionVo;
	}
	@Transactional(readOnly = true)
	@Override
	public Map<String,Object> getDetail(String promotionNo,String memberNo) throws Exception {
		String sql = "select "
				+" tp.promotion_pic_url promotionUrl, "
				+" tp.promotion_name promotionName, "
				+" tp.start_time startTime, "
				+" tp.end_time endTime, "
				+" tp.least_amount leastAmount, "
				+" tp.discount_amount discountAmount, "
				+" tp.ref_commo_no prodNo, "
				+" tp.shop_no shopNo, "
				+" tp.shop_name shopName, "
				+" tpi.status status, "
				+" tsi.shop_logo shopPic "
				+" from tbl_promotion_info tpi left join  "
				+" tbl_promotion tp on tpi.promotion_no=tp.promotion_no  "
				+ " left join tbl_shop_info tsi on tsi.shop_no = tp.shop_no "
				+" where tpi.coup_no = :coupNo";
		Query query = promotionDao.createSQLQuery(sql);
		query.setParameter("coupNo", promotionNo);
		query.setMaxResults(1);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map<String,Object> result = query.list()!=null&&!query.list().isEmpty()?(Map<String,Object>)query.list().get(0):null;
		if(memberNo!=null&&!memberNo.equals("")){
			Criteria criteria = promotionInfoDao.createCriteria(Restrictions.eq("memberNo", memberNo));
			criteria.add(Restrictions.eq("promotionNo", promotionNo));
			int i = promotionInfoDao.getRowCount(criteria);
			result.put("isfetched", i>0?"1":"0");
		}
		return result;
	}

	@Override
	@Transactional
	public void lockPromotion(String promotionNo, String lockFlag)
			throws Exception {
		Promotion promotion = promotionDao.get("promotionNo",promotionNo);
		promotion.setLockFlag(lockFlag);
		promotionDao.update(promotion);
	}

	@Override
	@Transactional
	public void publishPromotion(String promotionNo,String publishFlag) throws Exception {
		Promotion promotion = promotionDao.get("promotionNo",promotionNo);
		promotion.setStatus(publishFlag);
		promotionDao.update(promotion);
	}
	@Override
	@Transactional
	public void recommendPromotion(String promotionNo,String isRecommend) throws Exception {
		Promotion promotion = promotionDao.get("promotionNo",promotionNo);
		promotion.setIsRecommend(isRecommend);;
		promotionDao.update(promotion);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Map<String, Object>> getPromotionUse(String promotionNo,String isUsed)
			throws Exception {
		String sql = "select "
				+ " tp.coup_no coupNo,"
				+ " tp.member_no memberNo,"
				+ " tp.fetch_time fetchTime,"
				+ " tp.use_time usedTime from tbl_promotion_info tp"
				+ "  where tp.promotion_no = :promotionNo and tp.member_no is not null and tp.status = :status";
		Query query = promotionDao.createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setParameter("status", isUsed);
		query.setParameter("promotionNo", promotionNo);
		return query.list();
	}
	@Override
	@Transactional(readOnly=true)
	public List<Map<String, Object>> getPromotionByShopNo(String shopNo,String memberNo)
			throws Exception {
		String sql = "select "
				+ " tp.promotion_pic_url promotionPicUrl,"
				+ " tp.promotion_no promotionNo,"
				+ " tp.promotion_name promotionName,"
				+ " tp.least_amount leastAmount,"
				+ " tp.discount_amount discountAmount,"
				+ " tp.is_recommend isRecommend,"
				+ " tp.promotion_type promotionType,"
				+ " tp.start_time startTime,"
				+ " tp.end_time endTime,"
				+ " if(tpi.isfetched is null,'0','1') isfetched"
				+ "  from tbl_promotion tp  "
				+ "  left join (select tpi.member_no isfetched,tpi.promotion_no promotionNo  from tbl_promotion_info tpi where member_no=:memberNo) tpi "
				+ " on tp.promotion_no = tpi.promotionNo"
				+ "  where tp.shop_no = :shopNo"
				+ "  and tp.status = '2'  and tp.end_time>now() "
				+ "  and tp.lock_flag='0' ";
		Query query = promotionDao.createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setParameter("shopNo", shopNo);
		query.setParameter("memberNo", memberNo);
		List<Map<String, Object>> result = query.list();
		return result;
	}
	@Override
	@Transactional
	public void fetchPromotion(String promotionNo, String memberNo)
			throws Exception {
		Criteria criteria = promotionInfoDao.createCriteria(Restrictions.eq("promotionNo", promotionNo));
		criteria.add(Restrictions.isNull("memberNo"));
		criteria.setMaxResults(1);
		List<PromotionInfo> list = criteria.list();
		PromotionInfo promotionInfo;
		if(list!=null&&!list.isEmpty()){
			promotionInfo = list.get(0);
			promotionInfo.setMemberNo(memberNo);
			promotionInfo.setFetchTime(new Date());
			promotionInfoDao.update(promotionInfo);
		}else{
			throw new Exception("优惠券已被领完");
		}
	}
	@Override
	@Transactional(readOnly=true)
	public PageFinder<Map<String,Object>> getMyPromotion(String memberNo,String queryCondition,int pageNo,int pageSize){
		String sql = "select tp.promotion_name promotionName,"
				+" tp.least_amount leastAmount,"
				+ "tpi.coupon_number couponNumber,"
				+" tp.promotion_pic_url promotionPic,"
				+" tp.promotion_no promotionNo,"
				+" tp.start_time startTime,"
				+" tp.end_time endTime,"
				+" tp.promotion_type promotionType,"
				+" tp.discount_amount discountAmount,"
				+" tp.shop_no shopNo,"
				+" if(tp.end_time<now() and tpi.status='0','2',tpi.status) promotionStatus,"
				+" tp.shop_name shopName"
				+" from tbl_promotion tp left join"
				+" tbl_promotion_info tpi on tp.promotion_no=tpi.promotion_no where tpi.member_no=:memberNo ";
		String countSql = "select count(*) from tbl_promotion tp left join"
				+" tbl_promotion_info tpi on tp.promotion_no=tpi.promotion_no where tpi.member_no=:memberNo ";
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("memberNo", memberNo);
		if(queryCondition==null||queryCondition.equals("")){
			sql = sql +" order by promotionStatus asc";
			//查全部
		}else
			if(queryCondition.equals("1")){//未使用
				sql = sql +"and tpi.status = :status  order by promotionStatus asc,tp.end_time";
				countSql = countSql +"and tpi.status = :status";
				values.put("status", "0");
			}else if(queryCondition.equals("2")){//已使用
				sql = sql +"and tpi.status = :status  order by tp.end_time";
				countSql = countSql +"and tpi.status = :status";
				values.put("status", "1");			
			}else if(queryCondition.equals("3")){ //已过期
				sql = sql +"and tp.end_time < now()  and tpi.status='0'  order by tp.end_time";
				countSql = countSql +"and tp.end_time < now() ";
			}
		Query query = promotionDao.createSQLQuery(sql,values);
		Query queryCount = promotionDao.createSQLQuery(countSql, values);
		int totalRows = ((BigInteger)queryCount.uniqueResult()).intValue();
		PageFinder<Map<String,Object>> pageFinder= new PageFinder<Map<String,Object>>(pageNo, pageSize, totalRows);
		query.setMaxResults(pageFinder.getPageSize());
		query.setFirstResult(pageFinder.getStartOfPage());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		pageFinder.setData(query.list());

		return pageFinder;

	}
	@Override
	@Transactional(readOnly=true)
	public PageFinder<Map<String, Object>> getPromotionPage(String shopNo,
			String type, int pageNo, int pageSize) throws Exception {
		Map<String,Object> values = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer("select tp.coup_count coupCount,"
				+ " tp.promotion_pic_url promotionPicUrl,"
				+ " tp.promotion_no promotionNo,"
				+ " tp.promotion_name promotionName,"
				+ " tp.least_amount leastAmount,"
				+ " tp.discount_amount discountAmount,"
				+ " tp.is_recommend isRecommend,"
				+ " tp.promotion_type promotionType,"
				+ " (CASE WHEN tp.lock_flag = '1' and tp.end_time>now() THEN '2' WHEN tp.end_time<now()  or tp.coup_count=IFNULL(tt.counts,0) then '1' when"
				+  " tp.end_time>now() and tp.lock_flag='0' and tp.coup_count>IFNULL(tt.counts,0) then '3' END"
				+  " ) promotionState,"
				+ " tp.start_time startTime,"
				+ " tp.end_time endTime,"
				+ " IFNULL(tt.counts,0) usedCount,"
				+ " IFNULL(tpi1.counts,0) fetchedCount,"
				+ " tp.lock_flag lockFlag"
				+ "  from tbl_promotion tp left join "
				+ "  (select count(*) counts,tpi.promotion_no promotionNo from tbl_promotion_info tpi where"
				+ " tpi.member_no is not null and tpi.status=1 ");
		if(StringUtils.isNotBlank(shopNo)){
			sql.append(" and  tpi.shop_no =:shopNo1");
			values.put("shopNo1", shopNo);
		}
		sql.append(" group by tpi.promotion_no) as tt on tp.promotion_no = tt.promotionNo"
				+ "  left join (select count(*) counts,tpi.promotion_no promotionNo from tbl_promotion_info tpi where"
				+ " tpi.member_no is not null");
		if(StringUtils.isNotBlank(shopNo)){
			sql.append(" and  tpi.shop_no =:shopNo2");
			values.put("shopNo2", shopNo);
		}
		sql.append(" group by tpi.promotion_no) as tpi1 on tp.promotion_no = tpi1.promotionNo where 1=1 and tp.promotion_type='1'  and tp.delete_flag='0'");
		if(StringUtils.isNotBlank(shopNo)){
			sql.append(" and  tp.shop_no = :shopNo3 ");
			values.put("shopNo3", shopNo);
		}
		Map<String,Object> valueCounts = new HashMap<String, Object>();
		StringBuffer sqlCount =new StringBuffer( "select count(*) "
				+ "  from tbl_promotion tp left join "
				+ "  (select count(*) counts,tpi.promotion_no promotionNo from tbl_promotion_info tpi where"
				+ " 1=1 and tpi.status=1 ");
		if(StringUtils.isNotBlank(shopNo)){
			sqlCount.append(" and  tpi.shop_no =:shopNo1");
			valueCounts.put("shopNo1", shopNo);
		}
		sqlCount.append(" group by tpi.promotion_no) as tt on tp.promotion_no = tt.promotionNo"
				+ "  left join (select count(*) counts,tpi.promotion_no promotionNo from tbl_promotion_info tpi where"
				+ " tpi.member_no is not null ");
		if(StringUtils.isNotBlank(shopNo)){
			sqlCount.append(" and  tpi.shop_no =:shopNo2");
			valueCounts.put("shopNo2", shopNo);
		}
		sqlCount.append(" group by tpi.promotion_no) as tpi1 on tp.promotion_no = tpi1.promotionNo where 1=1 and tp.promotion_type='1'  and tp.delete_flag='0'");

		if(StringUtils.isNotBlank(shopNo)){
			sqlCount.append(" and  tp.shop_no = :shopNo3");
			valueCounts.put("shopNo3", shopNo);
		}
		if(type!=null&&!type.equals("")){
			if(type.equals("1")){//全部
				//				sql .append(" and tp.status = '1'  and tp.end_time>now() ");
				//				sqlCount.append(" and tp.status = '1'  and tp.end_time>now() ");

			}else if(type.equals("2")){//已锁定
				sql.append(" and tp.lock_flag = '1' and tp.end_time>now() ");
				sqlCount.append(" and tp.lock_flag = '1' and tp.end_time>now()  ");
			}else if(type.equals("3")){//已失效
				sql .append(" and tp.end_time<now()  or tp.coup_count=IFNULL(tpi1.counts,0) ");
				sqlCount.append(" and tp.end_time<now() or tp.coup_count=IFNULL(tpi1.counts,0) ");
			}else if(type.equals("4")){//1全部、2已锁定、3已失效、4发布中
				sql .append(" and tp.end_time>now() and tp.lock_flag='0' and tp.coup_count>IFNULL(tpi1.counts,0)");
				sqlCount.append(" and tp.end_time>now() and tp.lock_flag='0' and tp.coup_count>IFNULL(tpi1.counts,0)  ");
			}
			sql.append(" order by promotionState desc, tp.end_time desc");
		}
		Query query = promotionDao.createSQLQuery(sql.toString(),values);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		//query.setParameter("shopNo1", shopNo);
		//query.setParameter("shopNo2", shopNo);
		//query.setParameter("shopNo3", shopNo);
		Query queryCount = promotionDao.createSQLQuery(sqlCount.toString(),valueCounts);
		//queryCount.setParameter("shopNo1", shopNo);
		//queryCount.setParameter("shopNo2", shopNo);
		//queryCount.setParameter("shopNo3", shopNo);
		BigInteger totalRows = (BigInteger)queryCount.uniqueResult();
		PageFinder<Map<String, Object>> pageFinder = new PageFinder<Map<String, Object>>(
				pageNo, pageSize, totalRows.intValue());
		query.setMaxResults(pageFinder.getPageSize());
		query.setFirstResult(pageFinder.getStartOfPage());
		List<Map<String, Object>> list = query.list();
		pageFinder.setData(list);
		return pageFinder;
	}
	@Override
	@Transactional(readOnly=true)
	public PageFinder<Map<String, Object>> getPromotions(String shopNo,String memberNo,int pageNo,int pageSize)
			throws Exception {
		//change: tsi.status != 3 -> (tsi.status != 3 or tsi.status is null),电商后台发的优惠券无tbl_shop_info,status=null
		String sql = "select  tp.promotion_pic_url promotionPicUrl, tp.promotion_no promotionNo, tp.promotion_name promotionName,"
				+ " tp.least_amount leastAmount, tp.discount_amount discountAmount, tp.is_recommend isRecommend, tp.promotion_type promotionType,"
				+ " tp.start_time startTime, tp.end_time endTime, "
				+ " (case when tpi.isfetched is null then '1' when tpi.isfetched is not null and tpi.promotionStatus='0' then '2'"
				+ " when tpi.isfetched is not null and tpi.promotionStatus='1' then '3' end) promotionStatus  from tbl_promotion tp left join "
				+ " (select tpi.member_no isfetched,tpi.promotion_no promotionNo,tpi.status promotionStatus  "
				+ " from tbl_promotion_info tpi where member_no=:memberNo group by tpi.promotion_no) tpi  on tp.promotion_no = tpi.promotionNo "
				+ " left join (select count(*) counts,tpi.promotion_no promotionNo from tbl_promotion_info tpi "
				+ " where tpi.member_no is not null group by tpi.promotion_no) as tpi1 on tp.promotion_no = tpi1.promotionNo"
				+ " left join tbl_shop_info tsi on tsi.shop_no = tp.shop_no"
				+ " where tp.promotion_type!='2' and tp.status = '2' and tp.end_time>now() and tp.lock_flag='0' and (tp.delete_flag='0' or tp.delete_flag is null) and tp.coup_count>=IFNULL(tpi1.counts,0) and (tsi.status != 3 or tp.shop_no=0000000) and (tpi.isfetched is not null or tp.coup_count != IFNULL(tpi1.counts,0))";
		String sqlCount = "select count(*)"
				+ " from tbl_promotion tp left join "
				+ " (select tpi.member_no isfetched,tpi.promotion_no promotionNo,tpi.status promotionStatus  "
				+ " from tbl_promotion_info tpi where member_no=:memberNo group by tpi.promotion_no) tpi  on tp.promotion_no = tpi.promotionNo "
				+ " left join (select count(*) counts,tpi.promotion_no promotionNo from tbl_promotion_info tpi "
				+ " where tpi.member_no is not null group by tpi.promotion_no) as tpi1 on tp.promotion_no = tpi1.promotionNo"
				+ " left join tbl_shop_info tsi on tsi.shop_no = tp.shop_no"
				+ " where tp.promotion_type!='2' and tp.status = '2' and tp.end_time>now() and tp.lock_flag='0'  and (tp.delete_flag='0' or tp.delete_flag is null) and tp.coup_count>=IFNULL(tpi1.counts,0) and (tsi.status != 3 or tp.shop_no=0000000) and (tpi.isfetched is not null or tp.coup_count != IFNULL(tpi1.counts,0))";
		
		/*UserInfoFroBiz bizuser = this.getBizUserInfo(memberNo);
		log.info("getPromotions-bizuser="+GsonUtil.toJson(bizuser));
		if(bizuser!=null&&bizuser.getNamespaceId()!=null&&bizuser.getNamespaceId().intValue() == 999998){
			shopNo=huaWeiShopNo;
		}
		else {
			sql = sql + " and tp.shop_no!='"+huaWeiShopNo+"' ";
			sqlCount = sqlCount + " and tp.shop_no!='"+huaWeiShopNo+"' ";
		}*/
		
		if(shopNo!=null&&!shopNo.equals("")){
			sql = sql + " and tp.shop_no=:shopNo ";
			sqlCount = sqlCount + " and tp.shop_no=:shopNo ";
		}
		sql = sql +" order by tp.create_time desc";
		Query query = promotionDao.createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setParameter("memberNo", memberNo);
		Query queryCount = promotionDao.createSQLQuery(sqlCount.toString());
		queryCount.setParameter("memberNo", memberNo);
		if(shopNo!=null&&!shopNo.equals("")){
			query.setParameter("shopNo", shopNo);
			queryCount.setParameter("shopNo", shopNo);
		}
		BigInteger totalRows = (BigInteger)queryCount.uniqueResult();
		PageFinder<Map<String, Object>> pageFinder = new PageFinder<Map<String, Object>>(
				pageNo, pageSize, totalRows.intValue());
		query.setMaxResults(pageFinder.getPageSize());
		query.setFirstResult(pageFinder.getStartOfPage());
		List<Map<String, Object>> list = query.list();
		if(list!=null&&!list.isEmpty()&&memberNo!=null&&!memberNo.equals("")){
			for(Map<String, Object> r:list){
				String promotionNo = (String) r.get("promotionNo");
				Map<String,Object> values = new HashMap<String,Object>();
				values.put("promotionNo", promotionNo);
				values.put("memberNo", memberNo);
				PromotionInfo promInfo = this.promotionInfoDao.get(values);
				if(promInfo!=null)
					r.put("couponNumber", promInfo.getCouponNumber());
			}
		}
			
		pageFinder.setData(list);
		return pageFinder;
	}

	private UserInfoFroBiz getBizUserInfo(String memberNo) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("appKey", sdkConstants.getAPI_KEY());
		params.put("id", memberNo);
        String signature = Signature.computeSignature(params, sdkConstants.getAPI_SECRET());
        
        String param = String.format("%s?id=%s&appKey=%s&signature=%s",sdkConstants.getCLIENT_GET_USER_INFO(), memberNo,sdkConstants.getAPI_KEY(),RestUtil.strEncoder(signature));
		String responseJson = RestUtil.restPostForm(param);
		if(responseJson!=null){
			CommonResponse response = GsonUtil.fromJson(responseJson, CommonResponse.class);
			UserInfoFroBiz userInfo = GsonUtil.fromJson(GsonUtil.toJson(response.getResponse()),UserInfoFroBiz.class);
			return userInfo;
		}
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public PageFinder<Map<String, Object>> getGroupBuyPage(String shopNo,
			String type, int pageNo, int pageSize) throws Exception {
		String sql = "select tp.promotion_name promtionName,"+
				" tp.promotion_no promotionNo,"
				+ " tp.ref_commo_no refCommoNo,"+
				" tp.coup_count coupCount,"+
				" tp.start_time startTime,"+
				" tp.end_time endTime,"+
				" tp.discount_amount groupPrice,"+
				" tpr.price price,"+
				" tpr.prod_no prodNo,"+
				" tpr.prod_name prodName,"
				+ " tpr.stock stock,"
				+ " tpr.stock_preemption stockPreemption,"+
				" tp.limit_count limitCount,"+
				" ifnull(tp.already_count,0) alreadyCount,"+
				" ifnull(tpr.default_pic,'') prodPic"+
				"  from tbl_promotion tp "+
				" left join tbl_product tpr on tp.ref_commo_no = tpr.commo_no"+
				" where tp.promotion_type='2' and tp.delete_flag='0' and tp.shop_no=:shopNo";
		String sqlCount = "select count(*) "+
				"  from tbl_promotion tp "+
				" left join tbl_product tpr on tp.ref_commo_no = tpr.commo_no"+
				" where tp.promotion_type='2' and tp.delete_flag='0' and tp.shop_no=:shopNo";
		if(type!=null&&type.equals("1")){
			sql = sql +" and :curentTime <= tp.end_time ";
			sqlCount = sqlCount +" and :curentTime <= tp.end_time ";
		}else{
			sql = sql +" and :curentTime >tp.end_time ";
			sqlCount = sqlCount +" and :curentTime >tp.end_time ";
		}

		Query query = promotionDao.createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Query queryCount = promotionDao.createSQLQuery(sqlCount.toString());
		if(shopNo!=null&&!shopNo.equals("")){
			query.setParameter("shopNo", shopNo);
			queryCount.setParameter("shopNo", shopNo);
		}
		query.setParameter("curentTime", new Date());
		queryCount.setParameter("curentTime", new Date());
		BigInteger totalRows = (BigInteger)queryCount.uniqueResult();
		PageFinder<Map<String, Object>> pageFinder = new PageFinder<Map<String, Object>>(
				pageNo, pageSize, totalRows.intValue());
		query.setMaxResults(pageFinder.getPageSize());
		query.setFirstResult(pageFinder.getStartOfPage());
		List<Map<String, Object>> list = query.list();
		pageFinder.setData(list);
		return pageFinder;
	}

	@Override
	@Transactional
	public void delete(String promotionNo) throws Exception {
		Promotion promotion = promotionDao.get("promotionNo",promotionNo);
		promotion.setDeleteFlag("1");
		int count = promotion.getAlreadyCount()==null?0:promotion.getAlreadyCount();
		if(count!=0){
			throw new Exception("该团购报名人数不为0，无法删除");
		}
		promotionDao.update(promotion);
		if(promotion.getPromotionType().equals("2"))//团购
			this.changeToCommonAndNoPublish(promotion.getRefCommoNo());
	}

	private void changeToCommonAndNoPublish(String refCommoNo) {
		if(refCommoNo == null || refCommoNo.equals(""))
			return ;
		Commodity commodity = commodityDao.get("commoNo",refCommoNo);
		//log.info("changeToCommonAndNoPublish-commodity="+GsonUtil.toJson(commodity));
		if(commodity != null){
			List<Product> products = productDao.getList("commoNo",refCommoNo);
			if(products != null && !products.isEmpty()){
				for(Product r:products){
					r.setIsGroupbuy("1");//改为普通商品
					productDao.update(r);
				}
			}
			commodity.setPublishState("2");//下架
			commodityDao.update(commodity);
		}

	}

	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> getGroupBuyDetail(String promotionNo)
			throws Exception {
		String sql = "select tp.promotion_name promtionName,"+
				" tp.promotion_no promotionNo,"+
				" tp.coup_count coupCount,"+
				" tp.start_time startTime,"+
				" tp.end_time endTime,"+
				" tp.discount_amount groupPrice,"+
				" tp.limit_count limitCount,"+
				" tpr.price price,"+
				" tpr.prod_no prodNo,"+
				" ifnull(tpr.prod_name,'') prodName,"+
				" ifnull(tpr.stock,0)-ifnull(tpr.stock_preemption,0) stock,"+
				" tp.shop_no shopNo,"+
				" ifnull(tp.already_count,0) alreadyCount,"+
				" ifnull(tpr.default_pic,'') prodPic "+
				"  from tbl_promotion tp "+
				" left join tbl_product tpr on tp.ref_commo_no = tpr.commo_no "+
				" where tp.promotion_no=:promotionNo";
		Query query = promotionDao.createSQLQuery(sql);
		query.setParameter("promotionNo", promotionNo);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map<String,Object> result = query.list()!=null&&!query.list().isEmpty()?(Map<String,Object>)query.list().get(0):null;
		return result;
	}

	@Override
	@Transactional
	public void groupProductInvalid() throws Exception {
		Long nowTime = System.currentTimeMillis();
		Date now = new Date(nowTime);
		Criteria criteria = promotionDao.createCriteria(Restrictions.eq("promotionType", "2"));
		criteria.add(Restrictions.lt("endTime", now));
		criteria.add(Restrictions.or(Restrictions.isNull("exeJob"),Restrictions.eq("exeJob", "0")));

		Set<String> commoNos = new HashSet<String>();
		List<Promotion> inValidPros = criteria.list();
		log.info("groupProductInvalid-inValidPros="+GsonUtil.toJson(inValidPros));
		if(inValidPros != null && !inValidPros.isEmpty()){
			for(Promotion r:inValidPros){
				//设置商品状态
				if(r.getRefCommoNo() != null && !r.getRefCommoNo().equals("")){
					Criteria criteria2 = promotionDao.createCriteria(Restrictions.eq("deleteFlag", "0"));
					criteria2.add(Restrictions.eq("refCommoNo", r.getRefCommoNo()));
					criteria2.add(Restrictions.eq("promotionType", "2"));
					criteria2.add(Restrictions.le("startTime", now));
					criteria2.add(Restrictions.ge("endTime", now));
					List<Promotion> validPros = criteria2.list();
					log.info("groupProductInvalid-validPros="+GsonUtil.toJson(validPros));
					if(validPros == null || validPros.isEmpty()){
						this.changeToCommonAndNoPublish(r.getRefCommoNo());
					}
				}
				//组团不成功
				int alreadCount = r.getAlreadyCount()==null?0:r.getAlreadyCount();
				int coupCount = r.getCoupCount()==null?0:r.getCoupCount();
				if(coupCount<=alreadCount){//组团成功
					//给商家发消息
					Shop shop = shopDao.get("shopNo",r.getShopNo());
					String refCommNo = r.getRefCommoNo();
					Commodity commodity = commodityDao.get("commoNo",refCommNo);
					String shopMsg = "您发起的"+shop.getShopName()+"店铺"+commodity.getCommoName()+"商品团购, 组团成功，共计"+alreadCount+"件，请及时安排发货";
					sendMessage.sendMessage(shop.getMerchNo(), System.currentTimeMillis(), RandomUtils.nextInt(2), "text",shopMsg);

					Criteria orderCriteria = orderDao.createCriteria(Restrictions.eq("orderCouponsNo", r.getPromotionNo()));
					List<Order> orders = orderCriteria.list();
					log.debug("groupProductInvalid-backorders="+GsonUtil.toJson(orders));
					if(orders!=null&&!orders.isEmpty()){
						for (Order order : orders) {
							if(order.getBasicState().equals(OrderConstants.BASIC_STATE_WAITING_DELIVERY)){
								//给用户发消息
								String userMsg = "您已参加的 "+shop.getShopName()+"店铺"+commodity.getCommoName()+"商品团购，组团成功，我们将通知卖家及时发货";
								sendMessage.sendMessage(order.getBuyerNo(), System.currentTimeMillis(), RandomUtils.nextInt(2), "text", userMsg);
							}
						}
					}
				}
				else if(coupCount>alreadCount){//组团失败
					//给商家发消息
					Shop shop = shopDao.get("shopNo",r.getShopNo());
					String refCommNo = r.getRefCommoNo();
					Commodity commodity = commodityDao.get("commoNo",refCommNo);
					String shopMsg =null;
					if(alreadCount!=0)
						shopMsg = "您发起的"+shop.getShopName()+"店铺"+commodity.getCommoName()+"商品团购, 组团失败，共计"+alreadCount+"件，系统已自动退款至买家";
					else
						shopMsg = "您发起的"+shop.getShopName()+"店铺"+commodity.getCommoName()+"商品团购, 组团失败，共计0件";
					sendMessage.sendMessage(shop.getMerchNo(), System.currentTimeMillis(), RandomUtils.nextInt(2), "text",shopMsg);

					Criteria orderCriteria = orderDao.createCriteria(Restrictions.eq("orderCouponsNo", r.getPromotionNo()));
					//Criteria orderCriteria = orderDao.createCriteria(Restrictions.eq("orderCouponsNo", "14479327941071228"));
					List<Order> orders = orderCriteria.list();
					log.debug("groupProductInvalid-backorders="+GsonUtil.toJson(orders));
					if(orders!=null&&!orders.isEmpty()){
						for (Order order : orders) {
							if(order.getBasicState().equals(OrderConstants.BASIC_STATE_WAITING_PAY)){
								/*this.returnProdStockPreemption(order.getOrderNo(),false);
								order.setBasicState(OrderConstants.BASIC_STATE_CLOSED);
								order.setCancelReason("团购失败，取消订单");
								order.setCancelTime(new Date());
								orderDao.update(order);*/
							}else if(order.getBasicState().equals(OrderConstants.BASIC_STATE_WAITING_DELIVERY)){
								//给用户发消息
								String userMsg = "您已参加的 "+shop.getShopName()+"店铺"+commodity.getCommoName()+"商品团购，组团失败，系统已自动退款";
								sendMessage.sendMessage(order.getBuyerNo(), System.currentTimeMillis(), RandomUtils.nextInt(2), "text", userMsg);
								//退款
								this.returnProdStockPreemption(order.getOrderNo(),true);
								this.returnComAndProdSellNum(order.getOrderNo());
								order.setBasicState(OrderConstants.BASIC_STATE_REFUND_APPLY);
								order.setCancelReason("团购失败，取消订单,等待退款");
								order.setCancelTime(new Date());

								String url = sdkConstants.getCLIENT_CREATE_REFUND_RECORD();//"http://192.168.10.117:8087/EDS_PAY/pay_common/refund/save_refundInfo_record";
								log.info("groupProductInvalid-url="+url);
								ReturnOrderRecord map = new ReturnOrderRecord();
								map.setOriginalOrderNo(order.getOrderNo());
								map.setRefundAmount(order.getFeeTotal());
								map.setRefundmark("团购失败,等待退款");
								BaseVo<ReturnOrderRecord> baseVo = new BaseVo<ReturnOrderRecord>();
								baseVo.setBody(map);
								log.info("groupProductInvalid-baseVo="+GsonUtil.toJson(baseVo));
								String json = RestUtil.restWan(GsonUtil.toJson(baseVo),url);
								if(json!=null){
									log.debug("groupProductInvalid-restWanJson="+json);
									ResultVo resultVo = GsonUtil.fromJson(json, ResultVo.class);
									if(resultVo.isResult()&&order.getPaymentBank()!=null&&order.getPaymentBank().equals("wechat")){//微信退款成功
										order.setBasicState(OrderConstants.BASIC_STATE_ALREADY_REFUND);
									}
									if(!resultVo.isResult()){
										throw new Exception("生成退款单失败。");
									}
								}
								
								orderDao.update(order);
							}

						}
					}
				}
				//改优惠券已执行过任务
				r.setExeJob("1");
				promotionDao.update(r);
			}
		}
	}

	private void returnComAndProdSellNum(String orderNo) {
		List<OrderItem> orderItems = orderItemDao.getList("orderNo", orderNo);
		if(orderItems!=null&&!orderItems.isEmpty()){
			for (OrderItem orderItem : orderItems) {
				String prodNo = orderItem.getProdNo();
				String commoNo = orderItem.getCommodityNo();
				int quantity = orderItem.getQuantity();
				Product prod = productDao.get("prodNo",prodNo);
				Commodity com = commodityDao.get("commoNo",commoNo);
				int prodSellNum = prod.getSellNum()==null?0:prod.getSellNum();
				int comSellNum = com.getSellNum()==null?0:com.getSellNum();
				prod.setSellNum(prodSellNum-quantity);
				com.setSellNum(comSellNum-quantity);
				productDao.update(prod);
				commodityDao.update(com);
			}
		}

	}

	private void returnProdStockPreemption(String orderNo, boolean returnStock) {
		List<OrderItem> orderItems = orderItemDao.getList("orderNo", orderNo);
		if(orderItems!=null&&!orderItems.isEmpty()){
			for (OrderItem orderItem : orderItems) {
				String prodNo = orderItem.getProdNo();
				String commoNo = orderItem.getOrderNo();
				int quantity = orderItem.getQuantity();
				Product product = productDao.get("prodNo",prodNo);
				int stockPreemption = product.getStockPreemption()==null?0:product.getStockPreemption();
				if(stockPreemption!=0&&stockPreemption>=quantity){
					product.setStockPreemption(stockPreemption-quantity);//订单减扣预占库存
					if(returnStock){
						int stock = product.getStock()==null?0:product.getStock();
						product.setStock(stock+quantity);//订单返回库存
					}
					productDao.update(product);
				}
			}
		}
	}

	@Override
	public PromotionVo getGroupDetail(String promotionNo) throws Exception {
		Promotion prom = promotionDao.get("promotionNo",promotionNo);
		PromotionVo promVo = new PromotionVo();
		ObjectConvertUtil.convertPoToVo(prom, promVo);
		String commoNo = prom.getRefCommoNo();
		if(prom!=null&&commoNo!=null){
			List<Product> prods = productDao.getList("commoNo",commoNo);
			if(prods!=null&&!prods.isEmpty()){
				promVo.setProduct(prods.get(0));
			}
			return promVo;
		}
		return null;
	}
}
