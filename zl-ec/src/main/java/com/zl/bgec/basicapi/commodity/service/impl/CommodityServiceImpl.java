package com.zl.bgec.basicapi.commodity.service.impl;

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

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.basecomponent.utils.ObjectConvertUtil;
import com.zl.bgec.basicapi.commodity.dao.ICommodityCensorDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityCollectDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityCommentDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityDetailPicDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityExpandPropDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityPicDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityPublishDao;
import com.zl.bgec.basicapi.commodity.dao.IProductDao;
import com.zl.bgec.basicapi.commodity.po.Commodity;
import com.zl.bgec.basicapi.commodity.po.CommodityCensor;
import com.zl.bgec.basicapi.commodity.po.CommodityCollect;
import com.zl.bgec.basicapi.commodity.po.CommodityComment;
import com.zl.bgec.basicapi.commodity.po.CommodityDetailPic;
import com.zl.bgec.basicapi.commodity.po.CommodityPic;
import com.zl.bgec.basicapi.commodity.po.CommodityPublish;
import com.zl.bgec.basicapi.commodity.po.Product;
import com.zl.bgec.basicapi.commodity.service.ICommoNoCreatorService;
import com.zl.bgec.basicapi.commodity.service.ICommodityService;
import com.zl.bgec.basicapi.commodity.vo.CommodityCensorVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityCommentVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityDetailPicVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityPicVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityPublishVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityVo;
import com.zl.bgec.basicapi.common.CommodityConstant;
import com.zl.bgec.basicapi.common.exception.ParamException;
import com.zl.bgec.basicapi.common.util.ExceptionUtil;
import com.zl.bgec.basicapi.common.util.LogicUtil;
import com.zl.bgec.basicapi.common.utils.ImageUtil;
import com.zl.bgec.basicapi.order.dao.IOrderDao;
import com.zl.bgec.basicapi.promotion.dao.IPromotionDao;
import com.zl.bgec.basicapi.promotion.po.Promotion;

@Service
public class CommodityServiceImpl implements ICommodityService{

	@Resource
	private ICommodityDao commodityDao;
	@Resource
	private ICommodityExpandPropDao commodityExpandPropDao;
	@Resource
	private ICommodityCensorDao commodityCensorDao;
	@Resource
	private ICommodityPublishDao commodityPubilshDao;
	@Resource
	private ICommoNoCreatorService commoNoCreatorService;
	@Resource
	private ICommodityCommentDao commodityCommentDao;
	@Resource
	private ICommodityPicDao commodityPicDao;
	@Resource
	private IProductDao productDao;
	@Resource
	private IPromotionDao promotionDao;
	@Resource
	private ICommodityCollectDao commodityCollectDao;
	@Resource
	private ImageUtil imageUtil;
	@Resource
	private IOrderDao orderDao;
	@Resource ICommodityDetailPicDao commodityDetailPicDao;
	@Override
	@Transactional
	public String saveCommodity(CommodityVo commodityVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commodityVo.getCommoName());
		//保存商品信息
		Commodity commodity = new Commodity();
		ObjectConvertUtil.convertVoToPo(commodityVo, commodity);
		String commodityNo =commoNoCreatorService.createCommoNo(commodity.getCatNo().substring(0, 2));
		commodity.setCommoNo(commodityNo);
		commodity.setCreateTime(new Date());
		commodity.setPublishState(commodityVo.getPublishState());//默认待上架;
		commodity.setPublishTime(new Date());
		//添加商品图片
		List<CommodityPic> commodityPics = commodityVo.getPics();
		if(commodityPics!=null&&!commodityPics.isEmpty()){
			for (int i=0;i<commodityPics.size();i++) {
				CommodityPic commodityPic = commodityPics.get(i);
				commodityPic.setCommoNo(commodityNo);
				commodityPic.setCreateTime(new Date());
				String url =commodityPic.getUrl();
				if(url!=null&&url.startsWith("data:")){
					url = imageUtil.saveImg(commodityPic.getUrl(), commodity.getCatNo(),"commodity");
					commodityPic.setUrl(url);
					if(i==0){
						commodity.setDefaultPic(url);
					}
				}
				commodityPicDao.save(commodityPic);
			}
		}

		List<Product> products = commodity.getProducts();
		if(products!=null&&!products.isEmpty()){
			for (Product product : products) {
				String productNo = commoNoCreatorService.createProdNo(commodityNo);
				product.setProdNo(productNo);
				product.setProdName(commodity.getCommoName()+" "+(product.getModel()==null?"":product.getModel()));
				product.setCreateTime(new Date());
				product.setModel(product.getModel()==null?"":product.getModel());
				product.setCommoNo(commodityNo);
				product.setDefaultPic(commodity.getDefaultPic());
				product.setIsGroupbuy("1");
				product.setSellerNo(commodity.getSellerNo());
				productDao.save(product);
			}
		}
		//新增图文详情
		if(commodityVo.getCommoDetailPics()!=null&&!commodityVo.getCommoDetailPics().isEmpty())
			this.updateCommoDetailPics(commodityNo,commodityVo.getCommoDetailPics(),commodity.getCatNo());

		commodityDao.save(commodity);
		return commodityNo;
	}

	private void updateCommoDetailPics(String commoNo,List<CommodityDetailPicVo> comDtlPics,String catNo) throws Exception {
		if(comDtlPics==null||comDtlPics.isEmpty())
			return ;

		List<CommodityDetailPic> commoDetlPics = commodityDetailPicDao.getList("commoNo", commoNo);
		//删除多余图片路径
		if(commoDetlPics!=null&&!commoDetlPics.isEmpty()){
			for(CommodityDetailPic r:commoDetlPics){
				String url = r.getUrl();
				boolean deleteFlag = true;
				for(CommodityDetailPicVo picVo:comDtlPics){
					String voUrl = picVo.getUrl();
					if(voUrl.equals(url)){
						deleteFlag=false;
						break;
					}
				}
				if(deleteFlag)
					commodityDetailPicDao.delete(r.getId());
			}
		}
		//新增路径
		for(CommodityDetailPicVo picVo:comDtlPics){
			String imgStr = picVo.getUrl();
			if(imgStr.startsWith("data:image")){
				String url =  imageUtil.saveImg(imgStr, catNo,"commodity");
				CommodityDetailPic pic = new CommodityDetailPic();
				pic.setCommoNo(commoNo);
				pic.setCreateTime(new Date());
				pic.setUrl(url);
				commodityDetailPicDao.save(pic);
			}
		}

	}

	@Override
	@Transactional
	public void updateCommodity(CommodityVo commodityVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commodityVo.getCommoNo());
		ExceptionUtil.checkParamStringNullAndEmpty(commodityVo.getCommoName());
		//保存商品信息
		Commodity commodity = commodityDao.get("commoNo", commodityVo.getCommoNo());
		ObjectConvertUtil.convertVoToPoNoNull(commodityVo, commodity);
		commodity.setUpdateTime(new Date());
		//更新图片详情
		if(commodityVo.getCommoDetailPics()!=null&&!commodityVo.getCommoDetailPics().isEmpty())
			this.updateCommoDetailPics(commodityVo.getCommoNo(),commodityVo.getCommoDetailPics(),commodity.getCatNo());

		//删除商品图片
		List<CommodityPic> commodityPics = commodityVo.getPics();
		List<CommodityPic> list = commodityPicDao.getList("commoNo",commodity.getCommoNo());
		if(commodityPics!=null&&!commodityPics.isEmpty()){
			if(list!=null&&!list.isEmpty()){
				for (CommodityPic pic:list) {
					boolean res = true;
					for (int i=0;i<commodityPics.size();i++) {
						CommodityPic commodityPic = commodityPics.get(i);
						if(pic.getId().equals(commodityPic.getId())){
							res = false;
							break;
						}
					}
					if(res){
						commodityPicDao.delete(pic.getId());
					}
				}
			}

			//添加新商品图片
			for (int i=0;i<commodityPics.size();i++) {
				CommodityPic commodityPic = commodityPics.get(i);
				if(commodityPic.getId()==null||"".equals(commodityPic.getId())){
					String url =  imageUtil.saveImg(commodityPic.getUrl(), commodity.getCatNo(),"commodity");
					commodityPic.setUrl(url);
					if(i==0){
						commodity.setDefaultPic(url);
					}
					commodityPic.setCreateTime(new Date());
					commodityPic.setCommoNo(commodity.getCommoNo());
					commodityPicDao.save(commodityPic);
				}
			}
			//设置第一张图片为商品默认图片
			commodity.setDefaultPic(commodityPics.get(0).getUrl().substring(commodityPics.get(0).getUrl().indexOf("commodity")));
		}
		//删除商品product
		List<Product> products = commodityVo.getProducts();
		List<Product> prods = productDao.getList("commoNo", commodityVo.getCommoNo());
		if(prods!=null&&!prods.isEmpty()){
			for (Product prod : prods) {
				boolean res = true;
				if(products!=null&&!products.isEmpty()){
					for (Product product : products) {
						if(prod.getProdNo().equals(product.getProdNo())){
							res = false;
							break;
						}
					}
				}
				if(res){
					productDao.delete(prod);
				}
			}
		}
		//新增商品product
		if(products!=null&&!products.isEmpty()){
			for (Product product : products) {
				if(product.getId()==null||"".equals(product.getId())){
					String productNo = commoNoCreatorService.createProdNo(commodity.getCommoNo());
					product.setProdNo(productNo);
					product.setCreateTime(new Date());
					product.setCommoNo(commodity.getCommoNo());
					product.setDefaultPic(commodity.getDefaultPic());
					product.setSellerNo(commodity.getSellerNo());
					product.setWarranty(product.getWarranty());
					product.setModel(product.getModel()==null?"":product.getModel());
					productDao.save(product);
					continue;
				}
				//更新商品
				Product productPo= productDao.get(product.getId());
				productPo.setUpdateTime(new Date());
				productPo.setProdName(commodity.getCommoName());
				productPo.setDefaultPic(commodity.getDefaultPic());
				productPo.setPrice(product.getPrice());
				productPo.setModel(product.getModel()==null?"":product.getModel());
				productPo.setStock(product.getStock());
				productPo.setProdBrand(product.getProdBrand());
				productPo.setProdProductionAddress(product.getProdProductionAddress());
				productPo.setProduceDate(product.getProduceDate());
				productPo.setTip(product.getTip());
				productPo.setWarranty(product.getWarranty());
				productPo.setHowToEat(product.getHowToEat());
				productPo.setStorage(product.getStorage());
				productDao.update(productPo);
			}
		}
		//更新货品commondity
		Commodity commo = commodityDao.get(commodity.getId());
		commo.setCommoName(commodity.getCommoName());
		commo.setCatName(commodity.getCatName());
		commo.setCatNo(commodity.getCatNo());
		commo.setDescription(commodity.getDescription());
		commo.setDeliveryFee(commodity.getDeliveryFee());
		commodityDao.update(commo);
		//清除之前的商品扩展信息
		//commodityExpandPropDao.delete("commoNo", commodity.getCommoNo());
	}

	@Override
	@Transactional(readOnly = true)
	public PageFinder<Map<String,Object>> pagedCommodityList(CommodityVo commodityVo)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		StringBuffer countSql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		//hql.append("from Commodity c where c.deleteFlag = :deleteFlag ");
		sql.append("select "
				+" tp.is_groupbuy isGroupbuy,"
				+ "tc.commo_name commoName,"
				+" tc.commo_no commoNo,"
				+" tc.default_pic defaultPic, "
				+" tc.sell_num sellNum,"
				+" tc.is_recommend isRecommend,"
				+" tc.publish_state publishState,"
				+" IF(count(tp.price)>1,CONCAT(CONCAT(MIN(tp.price),'~'),MAX(tp.price)),tp.price) as price,"
				+" sum(tp.stock) stock,"
				+" sum(tp.sort) sort,"
				+" sum(tp.stock_preemption) stockPreemption,"
				+" tc.publish_time publishTime "
				+" from tbl_commodity tc left join "
				+" tbl_product tp on tc.commo_no = tp.commo_no "
				+" where tc.delete_flag = :deleteFlag ");
		countSql.append("select count(1) "
				+" from tbl_commodity tc left join "
				+" tbl_product tp on tc.commo_no = tp.commo_no "
				+" where tc.delete_flag = :deleteFlag ");
		values.put("deleteFlag", commodityVo.getDeleteFlag());
		if(LogicUtil.isNotNullAndEmpty(commodityVo.getCommoNo())){
			sql.append(" and tc.commo_no = :commoNo ");
			countSql.append(" and tc.commo_no = :commoNo ");
			values.put("commoNo", commodityVo.getCommoNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commodityVo.getCommoNos())){
			sql.append(" and tc.commo_no in (:commoNos) ");
			countSql.append(" and tc.commo_no in (:commoNos) ");
			values.put("commoNos", commodityVo.getCommoNos());
		}
		if(LogicUtil.isNotNullAndEmpty(commodityVo.getCommoName())){
			sql.append(" and tc.commo_name like :commoName ");
			countSql.append(" and tc.commo_name like :commoName ");
			values.put("commoName","%"+commodityVo.getCommoName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(commodityVo.getSellerNo())){
			sql.append(" and tc.seller_no = :sellerNo ");
			countSql.append(" and tc.seller_no = :sellerNo ");
			values.put("sellerNo",commodityVo.getSellerNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commodityVo.getCatNo())){
			sql.append(" and tc.cat_no = :catNo ");
			countSql.append(" and tc.cat_no = :catNo ");
			values.put("catNo",commodityVo.getCatNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commodityVo.getPublishState())){
			if(commodityVo.getPublishState().equals("1")){
				sql.append(" and tc.publish_state = :publishState ");
				countSql.append(" and tc.publish_state = :publishState ");
				values.put("publishState",commodityVo.getPublishState());
			}else{
				sql.append(" and tc.publish_state != :publishState ");
				countSql.append(" and tc.publish_state != :publishState ");
				values.put("publishState","1");
			}
		}
		PageFinder<Map<String, Object>> pageFinder = getProducts(commodityVo,
				sql.toString(), countSql.toString(), values,commodityVo.getIsGroupBuy());//普通商品
		return pageFinder;
	}

	private PageFinder<Map<String, Object>> getProducts(
			CommodityVo commodityVo, String sql, String countSql,
			Map<String, Object> values,String isGroupBuy) {
		if(isGroupBuy==null||isGroupBuy.isEmpty()){
			sql =sql+("  group by(tc.commo_no) ");
			countSql=countSql+("  group by(tc.commo_no) ");
		}else if(isGroupBuy.equals("1")){
			sql =sql+(" and (tp.is_groupbuy is null or tp.is_groupbuy=:isGroupBuy) group by(tc.commo_no) ");
			countSql=countSql+(" and (tp.is_groupbuy is null or tp.is_groupbuy=:isGroupBuy) group by(tc.commo_no) ");
			values.put("isGroupBuy", isGroupBuy);
		}
		else if(isGroupBuy.equals("2")){
			sql =sql+(" and tp.is_groupbuy=:isGroupBuy group by(tc.commo_no) ");
			countSql=countSql+(" and tp.is_groupbuy=:isGroupBuy group by(tc.commo_no) ");
			values.put("isGroupBuy", isGroupBuy);
		}
		if(LogicUtil.isNotNullAndEmpty(commodityVo.getSortField())&&LogicUtil.isNotNullAndEmpty(commodityVo.getSortType())){
			String sortField = commodityVo.getSortField();
			sortField = sortField.equals("sell_num")?"tc."+sortField:sortField;
			sql = sql +(" order by ifnull(tp.sort,2147483647) asc, "+sortField+" "+commodityVo.getSortType()+"");
		}else{
			sql = sql +(" order by ifnull(tp.sort,2147483647) asc,tc.publish_time desc");
		}
		Query query = commodityDao.createSQLQuery(sql.toString(), values);
		Query queryCount = commodityDao.createSQLQuery(countSql.toString(), values);
		int totalRows = queryCount.list().size();
		PageFinder<Map<String,Object>> pageFinder= new PageFinder<Map<String,Object>>(commodityVo.getPageNo(), commodityVo.getPageSize(), totalRows);
		query.setMaxResults(pageFinder.getPageSize());
		query.setFirstResult(pageFinder.getStartOfPage());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		pageFinder.setData(query.list());
		return pageFinder;
	}

	@Override
	@Transactional
	public void deleteCommodity(CommodityVo commodityVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commodityVo.getCommoNo());
		Commodity commodity = commodityDao.get("commoNo", commodityVo.getCommoNo());
		commodity.setDeleteFlag(CommodityConstant.DELETE_FLAG);
		commodity.setUpdateTime(new Date());
		commodityDao.update(commodity);
	}

	@Override
	@Transactional(readOnly = true)
	public Commodity getCommodityDetail(String commoNo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commoNo);
		Commodity commodity = commodityDao.get("commoNo", commoNo);
		List<CommodityDetailPic>  commoDetailPics = this.commodityDetailPicDao.getList("commoNo", commoNo);
		List<Product> products = productDao.getList("commoNo",commoNo);
		List<CommodityPic> commodityPics = commodityPicDao.getList("commoNo",commoNo);
		Criteria criteriaComment = commodityCommentDao.createCriteria(Restrictions.eq("commodityNo", commoNo));
		criteriaComment.setMaxResults(1);//取一条
		criteriaComment.addOrder(Order.desc("createTime"));
		List<CommodityComment> commodityComments = criteriaComment.list();
		Criteria criteria = commodityCollectDao.createCriteria(Restrictions.eq("commodityNo", commoNo));
		int collectNum = commodityCollectDao.getRowCount(criteria);
		if(commodity!=null){
			commodity.setCommoDetailPics(commoDetailPics);
			commodity.setProducts(products);
			commodity.setPics(commodityPics);
			commodity.setCommodityComments(commodityComments);
			commodity.setCollectNum(collectNum);
		}
		return commodity;
	}

	@Override
	@Transactional
	public void initCommoCensor(CommodityCensorVo censorVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(censorVo.getCommoNo());
		if(commodityCensorDao.get("commoNo", censorVo.getCommoNo())!=null){
			throw new ParamException("00000000", "此商品已经初始过审核信息");
		}
		CommodityCensor censor = new CommodityCensor();
		censor.setCensorStatus(CommodityConstant.SUBMIT_CENSOR);
		censor.setCommoNo(censorVo.getCommoNo());
		commodityCensorDao.save(censor);
	}

	@Override
	@Transactional
	public void commoCensor(CommodityCensorVo censorVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(censorVo.getCommoNo());
		CommodityCensor censor = commodityCensorDao.get("commoNo", censorVo.getCommoNo());
		censor.setOperatorNo(censorVo.getOperatorNo());
		censor.setOperatorName(censorVo.getOperatorName());
		censor.setOperateTime(new Date());
		censor.setReason(censorVo.getReason());
		censor.setCensorStatus(censorVo.getCensorStatus());
		commodityCensorDao.update(censor);
	}

	@Override
	@Transactional
	public void batchCommoCensor(CommodityCensorVo censorVo) throws Exception {
		ExceptionUtil.checkParamCollection(censorVo.getCommoNoList());
		for(String commoNo:censorVo.getCommoNoList()){
			CommodityCensor censor = commodityCensorDao.get("commoNo", commoNo);
			censor.setOperatorNo(censorVo.getOperatorNo());
			censor.setOperatorName(censorVo.getOperatorName());
			censor.setOperateTime(new Date());
			censor.setReason(censorVo.getReason());
			censor.setCensorStatus(censorVo.getCensorStatus());
			commodityCensorDao.update(censor);
		}
	}

	@Override
	@Transactional
	public void initCommodityPublish(CommodityPublishVo publishVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(publishVo.getCommoNo());
		if(commodityPubilshDao.get("commoNo", publishVo.getCommoNo())!=null){
			throw new ParamException("00000000", "此商品已经初始过上下架信息");
		}
		CommodityPublish publish = new CommodityPublish();
		publish.setPublishStatus(CommodityConstant.COMMO_DELAY_PUBLISH);
		publish.setCommoNo(publishVo.getCommoNo());
		commodityPubilshDao.save(publish);
	}
	/**
	 * 商品上下架
	 */
	@Override
	@Transactional
	public void commodityPublish(CommodityVo commodityVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commodityVo.getCommoNo());
		Product product = productDao.get("commoNo",commodityVo.getCommoNo());
		String isGroupbuy = product.getIsGroupbuy();
		if(isGroupbuy!=null&&isGroupbuy.equals("1")&&commodityVo.getPublishState().equals("2")){//下架，普通商品判断商品是否还有未完成的订单
			String sql = "select count(*) from tbl_order tor "+
					" left join tbl_order_item toi  "+
					" on tor.order_no = toi.order_no  "+
					" where toi.commodity_no=:commodityNo "+ 
					" and basic_state not in ('6','7') and delete_flag!='1' ";
			Query query = orderDao.createSQLQuery(sql);
			query.setParameter("commodityNo", commodityVo.getCommoNo());
			Object obj = query.uniqueResult();
			int count = Integer.valueOf(String.valueOf(obj));
			if(count>0){
				throw new ParamException("00000001", "该商品还有未完成的订单，无法下架。");
			}
			else{
				//存在未关闭的订单，不能删除商品
				String sql2 = "select count(*) from tbl_order tor "+
						" left join tbl_order_item toi  "+
						" on tor.order_no = toi.order_no  "+
						" where toi.commodity_no=:commodityNo "+ 
						" and basic_state in ('6') and delete_flag!='1' ";
				Query query2 = orderDao.createSQLQuery(sql2);
				query2.setParameter("commodityNo", commodityVo.getCommoNo());
				Object obj2 = query2.uniqueResult();
				int count2 = Integer.valueOf(String.valueOf(obj2));
				if(count2>0){
					throw new ParamException("00000002", "该商品还有未结算的订单，无法下架。");
				}
			}
		}
		if(isGroupbuy!=null&&isGroupbuy.equals("2")){//团购商品，
			List<Promotion> list = promotionDao.getList("refCommoNo", commodityVo.getCommoNo());
			if(list!=null&&!list.isEmpty()){
				for (Promotion promotion : list) {
					String status = promotion.getStatus();
					Long startTime = promotion.getStartTime().getTime();
					Long endTime = promotion.getEndTime().getTime();
					Long currentTime = System.currentTimeMillis();
					if(status!=null&&promotion.getDeleteFlag().equals("0")&&status.equals("2")&&(currentTime>=startTime&&currentTime<=endTime)){
						throw new ParamException("00000001", "该商品还有团购活动，无法下架。");
					}
				}
			}
		}

		Commodity commodity = commodityDao.get("commoNo",commodityVo.getCommoNo());
		commodity.setPublishState(commodityVo.getPublishState());
		commodity.setPublishTime(new Date());
		commodityDao.update(commodity);
	}

	@Override
	@Transactional
	public void batchCommodityPublish(CommodityPublishVo publishVo)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(publishVo.getCommoNo());
		ExceptionUtil.checkParamCollection(publishVo.getCommoNoList());
		for(String commoNo:publishVo.getCommoNoList()){
			CommodityPublish publish = commodityPubilshDao.get("commoNo", commoNo);
			publish.setPublishStatus(publishVo.getPublishStatus());
			publish.setCommoNo(publishVo.getCommoNo());
			publish.setOperateTime(new Date());
			publish.setOperatorName(publishVo.getOperatorName());
			publish.setOperatorNo(publishVo.getOperatorNo());
			commodityPubilshDao.update(publish);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Commodity> getCommodityList(CommodityVo commodityVo)
			throws Exception {
		StringBuffer hql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		hql.append("from Commodity c where c.deleteFlag = :deleteFlag ");
		values.put("deleteFlag", commodityVo.getDeleteFlag());
		if(LogicUtil.isNotNullAndEmpty(commodityVo.getCommoNo())){
			hql.append(" and c.commoNo = :commoNo ");
			values.put("commoNo", commodityVo.getCommoNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commodityVo.getCommoNos())){
			hql.append(" and c.commoNo in (:commoNos) ");
			values.put("commoNos", commodityVo.getCommoNos());
		}
		if(LogicUtil.isNotNullAndEmpty(commodityVo.getCommoName())){
			hql.append(" and c.commoName like :commoName ");
			values.put("commoName","%"+commodityVo.getCommoName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(commodityVo.getSellerNo())){
			hql.append(" and c.sellerNo = :sellerNo ");
			values.put("sellerNo",commodityVo.getSellerNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commodityVo.getCatNo())){
			hql.append(" and c.catNo = :catNo ");
			values.put("catNo",commodityVo.getCatNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commodityVo.getPublishState())){
			hql.append(" and c.publishState = :publishState ");
			values.put("publishState",commodityVo.getPublishState());
		}
		List<Commodity> commodities = commodityDao.createQuery(hql.toString(), values).list();
		for (Commodity commodity : commodities) {
			commodity.setPrice(productDao.getPrice(commodity.getCommoNo()));
		}
		return commodities;
	}

	@Override
	@Transactional
	public void createCommdityComment(CommodityCommentVo commentVo)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commentVo.getCommodityNo());
		ExceptionUtil.checkParamStringNullAndEmpty(commentVo.getProductNo());
		//		ExceptionUtil.checkParamStringNullAndEmpty(commentVo.getTitle());
		ExceptionUtil.checkParamStringNullAndEmpty(commentVo.getContent());

		CommodityComment comment = new CommodityComment();
		ObjectConvertUtil.convertVoToPo(commentVo, comment);
		comment.setCreateTime(new Date());
		comment.setDeleteFlag(CommodityConstant.UNDELETE_FLAG);
		commodityCommentDao.save(comment);
	}

	@Override
	@Transactional
	public void deleteCommdityComment(CommodityCommentVo commentVo)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commentVo.getCommentNo());
		CommodityComment comment = commodityCommentDao.get(commentVo.getCommentNo());
		comment.setDeleteFlag(CommodityConstant.DELETE_FLAG);
		commodityCommentDao.update(comment);
	}

	@Override
	@Transactional(readOnly=true)
	public PageFinder<CommodityComment> pageCommdityCommentList(CommodityCommentVo commentVo)
			throws Exception {
		StringBuffer hql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		hql.append(" from CommodityComment cc where cc.deleteFlag= :deleteFlag ");
		values.put("deleteFlag", commentVo.getDeleteFlag());
		if(LogicUtil.isNotNullAndEmpty(commentVo.getCommodityNo())){
			hql.append(" and cc.commodityNo= :commodityNo ");
			values.put("commodityNo", commentVo.getCommodityNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commentVo.getProductNo())){
			hql.append(" and cc.productNo= :productNo ");
			values.put("productNo", commentVo.getProductNo());
		}
		if(LogicUtil.isNotNull(commentVo.getStartTime())&&LogicUtil.isNotNull(commentVo.getEndTime())){
			hql.append(" and (cc.createTime between :startTime and :endTime ) ");
			values.put("startTime", commentVo.getStartTime());
			values.put("endTime", commentVo.getEndTime());
		}
		if(LogicUtil.isNotNullAndEmpty(commentVo.getCommentNo())){
			hql.append(" and cc.id = :id ");
			values.put("id", commentVo.getCommentNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commentVo.getParentNo())){
			hql.append(" and cc.parentNo = :parentNo ");
			values.put("parentNo", commentVo.getParentNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commentVo.getMemberNo())){
			hql.append(" and cc.memberNo = :memberNo ");
			values.put("memberNo", commentVo.getMemberNo());
		}
		return commodityCommentDao.pagedByHQL(hql.toString(), commentVo.getPageNo(), commentVo.getPageSize(), values);
	}

	@Override
	@Transactional(readOnly=true)
	public List<CommodityComment> getCommdityCommentList(CommodityCommentVo commentVo)
			throws Exception {
		StringBuffer hql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		hql.append(" from CommodityComment cc where cc.deleteFlag= :deleteFlag ");
		values.put("deleteFlag", commentVo.getDeleteFlag());
		if(LogicUtil.isNotNullAndEmpty(commentVo.getCommodityNo())){
			hql.append(" and cc.commodityNo= :commodityNo ");
			values.put("commodityNo", commentVo.getCommodityNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commentVo.getProductNo())){
			hql.append(" and cc.productNo= :productNo ");
			values.put("productNo", commentVo.getProductNo());
		}
		if(LogicUtil.isNotNull(commentVo.getStartTime())&&LogicUtil.isNotNull(commentVo.getEndTime())){
			hql.append(" and (cc.createTime between :startTime and :endTime ) ");
			values.put("startTime", commentVo.getStartTime());
			values.put("endTime", commentVo.getEndTime());
		}
		if(LogicUtil.isNotNullAndEmpty(commentVo.getCommentNo())){
			hql.append(" and cc.id = :id ");
			values.put("id", commentVo.getCommentNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commentVo.getParentNo())){
			hql.append(" and cc.parentNo = :parentNo ");
			values.put("parentNo", commentVo.getParentNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commentVo.getMemberNo())){
			hql.append(" and cc.memberNo = :memberNo ");
			values.put("memberNo", commentVo.getMemberNo());
		}
		return commodityCommentDao.createQuery(hql.toString(), values).list();
	}

	@Override
	public PageFinder<Commodity> pagedCommodityListSql(CommodityVo commodityVo)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Commodity> getCommodityListSql(CommodityVo commodityVo)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void savePics(List<CommodityPicVo> picVos) throws Exception {
		ExceptionUtil.checkParamCollection(picVos);
		for(CommodityPicVo picVo:picVos){
			ExceptionUtil.checkParamStringNullAndEmpty(picVo.getCommoNo());
			CommodityPic pic = new CommodityPic();
			ObjectConvertUtil.convertVoToPo(picVo, pic);
			pic.setCreateTime(new Date());
			commodityPicDao.save(pic);
		}
	}

	@Override
	@Transactional
	public void deletePics(CommodityPicVo picVo) throws Exception {
		Map<String,Object> conditions = new HashMap<String, Object>();
		if(LogicUtil.isNotNullAndEmpty(picVo.getIds())){
			conditions.put("id", picVo.getIds());
		}
		if(LogicUtil.isNotNullAndEmpty(picVo.getCommoNo())){
			conditions.put("commoNo", picVo.getCommoNo());
		}
		if(LogicUtil.isNotNullAndEmpty(picVo.getProductNo())){
			conditions.put("productNo", picVo.getProductNo());
		}
		commodityPicDao.delete(conditions);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommodityPic> queryPics(CommodityPicVo picVo) throws Exception {
		StringBuffer hql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		hql.append(" from CommodityPic where 1=1 ");
		if(LogicUtil.isNotNullAndEmpty(picVo.getCommoNo())){
			hql.append(" and commoNo = :commoNo");
			values.put("commoNo", picVo.getCommoNo());
		}
		if(LogicUtil.isNotNullAndEmpty(picVo.getProductNo())){
			hql.append(" and productNo = :productNo");
			values.put("productNo", picVo.getProductNo());
		}
		return commodityPicDao.getListByHql(hql.toString(), values);
	}

	@Override
	@Transactional()
	public void recommendCommodity(CommodityVo commodityVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commodityVo.getCommoNo());
		Commodity commodity = commodityDao.get("commoNo",commodityVo.getCommoNo());
		commodity.setIsRecommend(commodityVo.getIsRecommend());
		commodityDao.update(commodity);
	}

	@Override
	@Transactional()
	public void replyComment(Map<String, String> map) throws Exception {
		String id = map.get("id");
		ExceptionUtil.checkParamStringNullAndEmpty(id);
		String replyContent = map.get("replyContent");
		String sql = " update tbl_commodity_comment set reply_content = :replyContent,reply_time = :replyTime where id = :id";
		Query query = commodityCommentDao.createSQLQuery(sql);
		query.setParameter("replyContent", replyContent);
		query.setParameter("replyTime", new Date());
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	@Transactional
	public void collectCommodity(String prodNo,String memberNo) throws Exception {
		CommodityCollect commodityCollect = new CommodityCollect();
		String commodityId = prodNo.substring(0, prodNo.length()-2);
		commodityCollect.setCommodityNo(commodityId);
		commodityCollect.setProdNo(prodNo);
		commodityCollect.setCreateTime(new Date());
		commodityCollect.setMemberNo(memberNo);
		commodityCollectDao.addCommodityCollect(commodityCollect);
	}

	@Override
	@Transactional
	public void cancelCollectCommodity(String prodNo, String memberNo)
			throws Exception {
		CommodityCollect commodityCollect = new CommodityCollect();
		String commodityId = prodNo.substring(0, prodNo.length()-2);
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("prodNo", prodNo);
		values.put("memberNo", memberNo);
		commodityCollectDao.get(values);
		if(commodityCollect!=null){
			commodityCollectDao.delete(commodityCollect);
		}
	}

	@Override
	@Transactional
	public void sortCommodity(CommodityVo commodityVo) throws Exception {
		String commoNo = commodityVo.getCommoNo();
		Product product = productDao.get("commoNo",commoNo);
		String shopNo = product.getSellerNo();
		Criteria criteria = productDao.createCriteria(Restrictions.eq("sellerNo", shopNo));
		if(product.getSort()!=null&&product.getSort().intValue()!=0){//如果该商品已经置顶过
			criteria.add(Restrictions.lt("sort",product.getSort()));//查询出当前置顶商品的前面的商品
		}else{//如果该商品没有置顶过，查询出所有置顶过的商品
			criteria.add(Restrictions.isNotNull("sort"));
		}
		List<Product> products = criteria.list();
		if(products!=null&&!products.isEmpty()){
			for (Product product2 : products) {
				int sort = product2.getSort();
				product2.setSort(sort+1);
				productDao.update(product2);
			}
		}
		product.setSort(1);
		productDao.update(product);
	}
	@Override
	@Transactional
	public void cancelSortCommodity(CommodityVo commodityVo) throws Exception {
		String commoNo = commodityVo.getCommoNo();
		Product product = productDao.get("commoNo",commoNo);
		String shopNo = product.getSellerNo();
		Criteria criteria = productDao.createCriteria(Restrictions.eq("sellerNo", shopNo));
		criteria.add(Restrictions.gt("sort",product.getSort()));//查询出当前置顶商品的后面的商品
		List<Product> products = criteria.list();
		if(products!=null&&!products.isEmpty()){
			for (Product product2 : products) {
				int sort = product2.getSort();
				product2.setSort(sort-1);
				productDao.update(product2);
			}
		}
		product.setSort(null);
		productDao.update(product);
	}

	@Override
	@Transactional
	public String updateCommoDetailPics(CommodityVo commodityVo)
			throws Exception {
		List<CommodityDetailPicVo> comDtlPics = commodityVo.getCommoDetailPics();
		if(comDtlPics==null||comDtlPics.isEmpty())
			return null;

		String commoNo =  commodityVo.getCommoNo();
		Commodity commodity = commodityDao.get("commoNo",commoNo);
		if(commodity==null)
			return null;

		List<CommodityDetailPic> commoDetlPics = commodityDetailPicDao.getList("commoNo", commoNo);
		//删除多余图片路径
		if(commoDetlPics!=null&&!commoDetlPics.isEmpty()){
			for(CommodityDetailPic r:commoDetlPics){
				String url = r.getUrl();
				boolean deleteFlag = true;
				for(CommodityDetailPicVo picVo:comDtlPics){
					String voUrl = picVo.getUrl();
					if(voUrl.equals(url)){
						deleteFlag=false;
						break;
					}
				}
				if(deleteFlag)
					commodityDetailPicDao.delete(r.getId());
			}
		}
		//新增路径
		for(CommodityDetailPicVo picVo:comDtlPics){
			String imgStr = picVo.getUrl();
			if(imgStr.startsWith("data:image")){
				String url =  imageUtil.saveImg(imgStr, commodity.getCatNo(),"commodity");
				CommodityDetailPic pic = new CommodityDetailPic();
				pic.setCommoNo(commodity.getCommoNo());
				pic.setCreateTime(new Date());
				pic.setUrl(url);
				commodityDetailPicDao.save(pic);
			}
		}
		return commoNo;
	}

	@Override
	@Transactional(readOnly=true)
	public List<CommodityDetailPic> queryCommoDetailPics(String commoNo) throws Exception {
		List<CommodityDetailPic> pics = commodityDetailPicDao.getList("commoNo", commoNo);
		return pics;
	}
}
