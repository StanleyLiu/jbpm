package com.zl.bgec.basicapi.commodity.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.basecomponent.utils.ObjectConvertUtil;
import com.zl.bgec.basicapi.commodity.dao.ICommodityDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityTagCommoDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityTagDao;
import com.zl.bgec.basicapi.commodity.po.Commodity;
import com.zl.bgec.basicapi.commodity.po.CommodityTag;
import com.zl.bgec.basicapi.commodity.po.CommodityTagCommo;
import com.zl.bgec.basicapi.commodity.service.ICommoNoCreatorService;
import com.zl.bgec.basicapi.commodity.service.ICommodityTagService;
import com.zl.bgec.basicapi.commodity.vo.CommodityTagVo;
import com.zl.bgec.basicapi.common.CommodityConstant;
import com.zl.bgec.basicapi.common.util.ExceptionUtil;
import com.zl.bgec.basicapi.common.util.LogicUtil;

@Service
public class CommodityTagServiceImpl implements ICommodityTagService{
	
	@Resource
	private ICommodityTagDao commodityTagDao;
	
	@Resource
	private ICommodityTagCommoDao commodityTagCommoDao;
	
	@Resource
	private ICommodityDao commodityDao;
	
	@Resource
	private ICommoNoCreatorService commoNoCreatorService;
	
	@Override
	@Transactional
	public String saveCommoTag(CommodityTagVo commoTagvo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commoTagvo.getTagName(),commoTagvo.getSellerNo());
		
		CommodityTag tag = new CommodityTag();
		ObjectConvertUtil.convertVoToPo(commoTagvo, tag);
		String tagNo =commoNoCreatorService.createTagNo();
		tag.setTagNo(tagNo);
		tag.setDeleteFlag(CommodityConstant.UNDELETE_FLAG);
		tag.setCreateTime(new Date());
		tag.setUpdateTime(new Date());
		commodityTagDao.save(tag);
		return tagNo;
	}

	@Override
	@Transactional
	public void saveCommoTagCommo(CommodityTagVo tagVo) throws Exception {
		
		ExceptionUtil.checkParamStringNullAndEmpty(tagVo.getCommodityNo());
		ExceptionUtil.checkParamCollection(tagVo.getCommodityNos());
		
		String tagNo = tagVo.getTagNo();
		for(String commodityNo:tagVo.getCommodityNos()){
			CommodityTagCommo ctc = new CommodityTagCommo();
			ctc.setCommoNo(commodityNo);
			ctc.setTagNo(tagNo);
			commodityTagCommoDao.save(ctc);
		}
		
	}

	@Override
	@Transactional
	public void deleteCommoTag(CommodityTagVo tagVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(tagVo.getTagNo());
		CommodityTag tag = commodityTagDao.get("tagNo", tagVo.getTagNo());
		tag.setUpdateTime(new Date());
		tag.setDeleteFlag(CommodityConstant.DELETE_FLAG);
		commodityTagDao.update(tag);
	}

	@Override
	@Transactional
	public void deleteCommoTagCommo(String tagNo, List<String> commoNoList)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(tagNo);
		ExceptionUtil.checkParamCollection(commoNoList);
		
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("tagNo", tagNo);
		condition.put("commoNo", commoNoList);
		commodityTagCommoDao.delete(condition);
	}

	@Override
	@Transactional(readOnly = true)
	public CommodityTag getCommoTag(String tagNo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(tagNo);
		
		return commodityTagDao.get("tagNo", tagNo);
	}

	@Override
	@Transactional(readOnly = true)
	public PageFinder<CommodityTag> pagedCommoTagList(CommodityTagVo tagVo)
			throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append(" from CommodityTag t where 1=1 ");
		Map<String, Object> map = new HashMap<String, Object>();
		if(LogicUtil.isNotNullAndEmpty(tagVo.getSellerNo())){
			hql.append(" and t.sellerNo = :sellerNo ");
			map.put("sellerNo", tagVo.getSellerNo());
		}
		if(LogicUtil.isNotNullAndEmpty(tagVo.getTagName())){
			hql.append(" and t.tagName like :tagName ");
			map.put("tagName", "%"+tagVo.getTagName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(tagVo.getTagNo())){
			hql.append(" and t.tagNo like :tagNo ");
			map.put("tagNo", tagVo.getTagNo());
		}
		if(LogicUtil.isNotNullAndEmpty(tagVo.getTagNos())){
			hql.append(" and t.tagNo in (:tagNos) ");
			map.put("tagNos", tagVo.getTagNos());
		}
		if(tagVo.getDeleteFlag()>-1){
			hql.append(" and t.deleteFlag = :deleteFlag ");
			map.put("deleteFlag", tagVo.getDeleteFlag());
		}
		hql.append(" order by t.updateTime desc");
		return commodityTagDao.pagedByHQL(hql.toString(), tagVo.getPageNo(), tagVo.getPageSize(), map);
	}

	@Override
	@Transactional
	public void updateCommoTag(CommodityTagVo tagVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(tagVo.getTagNo());
		CommodityTag tag = commodityTagDao.get("tagNo", tagVo.getTagNo());
		ObjectConvertUtil.convertVoToPoNoNull(tagVo, tag);
		tag.setUpdateTime(new Date());
		commodityTagDao.update(tag);
	}

	@Override
	@Transactional
	public void updateCommoTagCommo(CommodityTagVo tagVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(tagVo.getTagNo());
		ExceptionUtil.checkParamCollection(tagVo.getCommodityNos());
		
		//删除之前的关联
		if(tagVo.getRemoveBindFlag()==0){
			commodityTagCommoDao.delete("tagNo", tagVo.getTagNo());
		}
		//新建关联
		for(String commoNo:tagVo.getCommodityNos()){
			CommodityTagCommo ctc = new CommodityTagCommo();
			ctc.setCommoNo(commoNo);
			ctc.setTagNo(tagVo.getTagNo());
			commodityTagCommoDao.save(ctc);
		}
	}

	@Override
	@Transactional
	public void deleteCommoTagCommoByTagNo(List<String> tagNoList,
			String commoNo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commoNo);
		ExceptionUtil.checkParamCollection(tagNoList);
		
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("tagNo", tagNoList);
		condition.put("commoNo", commoNo);
		commodityTagCommoDao.delete(condition);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommodityTag> getCommoTagList() throws Exception {
		return commodityTagDao.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public PageFinder<Commodity> pagedCommoTagRelateCommodityList(
			CommodityTagVo tagVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(tagVo.getTagNo());
		Map<String,Object> values =new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("select c.* from tbl_commodity c ");
		sql.append("join tbl_commo_tag_commo tc on c.commo_no=tc.commo_no ");
		sql.append("join tbl_commo_tag t on tc.tag_no=t.tag_no ");
		sql.append("where t.tag_no= :tagNo ");
		values.put("tagNo", tagVo.getTagNo());
		if(LogicUtil.isNotNullAndEmpty(tagVo.getBrandNo())){
			sql.append(" and c.brand_no=:brandNo");
			values.put("brandNo", tagVo.getBrandNo());
		}
		if(LogicUtil.isNotNullAndEmpty(tagVo.getCatNo())){
			sql.append(" and c.cat_no like :catNo");
			values.put("catNo", tagVo.getCatNo()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(tagVo.getCommoName())){
			sql.append(" and c.commo_name like :commoName");
			values.put("commoName", "%"+tagVo.getCommoName()+"%");
		}
		sql.append("order by c.update_time desc ");
		return commodityDao.pagedBySQL(sql.toString(),tagVo.getPageNo(),tagVo.getPageSize(),values);
	}

	@Override
	@Transactional(readOnly = true)
	public PageFinder<Commodity> pagedCommoTagNotRelateCommodityList(
			CommodityTagVo tagVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(tagVo.getTagNo());
		Map<String,Object> values =new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("select c.* from tbl_commodity c where c.commo_no not in ");
		sql.append("(select tc.commo_no from tbl_commo_tag_commo tc where tc.tag_no= :tagNo) ");
		values.put("tagNo", tagVo.getTagNo());
		if(LogicUtil.isNotNullAndEmpty(tagVo.getBrandNo())){
			sql.append(" and c.brand_no=:brandNo");
			values.put("brandNo", tagVo.getBrandNo());
		}
		if(LogicUtil.isNotNullAndEmpty(tagVo.getCatNo())){
			sql.append(" and c.cat_no like :catNo");
			values.put("catNo", tagVo.getCatNo()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(tagVo.getCommoName())){
			sql.append(" and c.commo_name like :commoName");
			values.put("commoName", "%"+tagVo.getCommoName()+"%");
		}
		sql.append("order by c.update_time desc ");
		return commodityDao.pagedBySQL(sql.toString(),tagVo.getPageNo(),tagVo.getPageSize(),values);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Commodity> getCommodityListByTag(CommodityTagVo tagVo) throws Exception {
		Map<String,Object> values =new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("select c.* from tbl_commodity c ");
		sql.append("join tbl_commo_tag_commo tc on c.commo_no=tc.commo_no ");
		sql.append("join tbl_commo_tag t on tc.tag_no=t.tag_no ");
		sql.append("where t.tag_no= :tagNo ");
		values.put("tagNo", tagVo.getTagNo());
		if(LogicUtil.isNotNullAndEmpty(tagVo.getBrandNo())){
			sql.append(" and c.brand_no=:brandNo");
			values.put("brandNo", tagVo.getBrandNo());
		}
		if(LogicUtil.isNotNullAndEmpty(tagVo.getCatNo())){
			sql.append(" and c.cat_no like :catNo");
			values.put("catNo", tagVo.getCatNo()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(tagVo.getCommoName())){
			sql.append(" and c.commo_name like :commoName");
			values.put("commoName", "%"+tagVo.getCommoName()+"%");
		}
		sql.append("order by c.update_time desc ");
		return commodityDao.getListBySql(sql.toString(),values);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommodityTag> getCommoTagList(CommodityTagVo tagVo) throws Exception  {
		StringBuffer hql = new StringBuffer();
		hql.append(" from CommodityTag t where 1=1 ");
		Map<String, Object> values = new HashMap<String, Object>();
		if(LogicUtil.isNotNullAndEmpty(tagVo.getSellerNo())){
			hql.append(" and t.sellerNo = :sellerNo ");
			values.put("sellerNo", tagVo.getSellerNo());
		}
		if(LogicUtil.isNotNullAndEmpty(tagVo.getTagNo())){
			hql.append(" and t.tagNo = :tagNo ");
			values.put("tagNo", tagVo.getTagNo());
		}
		if(LogicUtil.isNotNullAndEmpty(tagVo.getTagName())){
			hql.append(" and t.tagName like :tagName ");
			values.put("tagName", "%"+tagVo.getTagName()+"%");
		}
		if(tagVo.getDeleteFlag()>-1){
			hql.append(" and t.deleteFlag = :deleteFlag ");
			values.put("deleteFlag", tagVo.getDeleteFlag());
		}
		hql.append(" order by t.updateTime desc");
		return commodityTagDao.getListByHql(hql.toString(), values);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Commodity> getCommoTagRelateCommodityList(CommodityTagVo tagVo)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(tagVo.getTagNo());
		Map<String,Object> values =new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("select c.* from tbl_commodity c ");
		sql.append("join tbl_commo_tag_commo tc on c.commo_no=tc.commo_no ");
		sql.append("join tbl_commo_tag t on tc.tag_no=t.tag_no ");
		sql.append("where t.tag_no= :tagNo ");
		values.put("tagNo", tagVo.getTagNo());
		if(LogicUtil.isNotNullAndEmpty(tagVo.getBrandNo())){
			sql.append(" and c.brand_no=:brandNo");
			values.put("brandNo", tagVo.getBrandNo());
		}
		if(LogicUtil.isNotNullAndEmpty(tagVo.getCatNo())){
			sql.append(" and c.cat_no like :catNo");
			values.put("catNo", tagVo.getCatNo()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(tagVo.getCommoName())){
			sql.append(" and c.commo_name like :commoName");
			values.put("commoName", "%"+tagVo.getCommoName()+"%");
		}
		sql.append("order by c.update_time desc ");
		return commodityDao.getListBySql(sql.toString(),values);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Commodity> getCommoTagNotRelateCommodityList(
			CommodityTagVo tagVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(tagVo.getTagNo());
		Map<String,Object> values =new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("select c.* from tbl_commodity c where c.commo_no not in ");
		sql.append("(select tc.commo_no from tbl_commo_tag_commo tc where tc.tag_no= :tagNo) ");
		values.put("tagNo", tagVo.getTagNo());
		if(LogicUtil.isNotNullAndEmpty(tagVo.getBrandNo())){
			sql.append(" and c.brand_no=:brandNo");
			values.put("brandNo", tagVo.getBrandNo());
		}
		if(LogicUtil.isNotNullAndEmpty(tagVo.getCatNo())){
			sql.append(" and c.cat_no like :catNo");
			values.put("catNo", tagVo.getCatNo()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(tagVo.getCommoName())){
			sql.append(" and c.commo_name like :commoName");
			values.put("commoName", "%"+tagVo.getCommoName()+"%");
		}
		sql.append("order by c.update_time desc ");
		return commodityDao.getListBySql(sql.toString(),values);
	}

}
