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
import com.zl.bgec.basicapi.commodity.dao.ICommodityCatPropDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityPropDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityPropValDao;
import com.zl.bgec.basicapi.commodity.po.CommodityCat;
import com.zl.bgec.basicapi.commodity.po.CommodityCatProp;
import com.zl.bgec.basicapi.commodity.po.CommodityProp;
import com.zl.bgec.basicapi.commodity.po.CommodityPropVal;
import com.zl.bgec.basicapi.commodity.service.ICommoNoCreatorService;
import com.zl.bgec.basicapi.commodity.service.ICommodityPropService;
import com.zl.bgec.basicapi.commodity.vo.CommodityPropValueVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityPropVo;
import com.zl.bgec.basicapi.common.CommodityConstant;
import com.zl.bgec.basicapi.common.util.ExceptionUtil;
import com.zl.bgec.basicapi.common.util.LogicUtil;
@Service
public class CommodityPropServiceImpl implements ICommodityPropService{

	@Resource
	private ICommodityPropDao commodityPropDao;
	@Resource
	private ICommodityPropValDao commodityPropValDao;
	@Resource
	private ICommodityCatPropDao commodityCatPropDao;
	@Resource
	private ICommodityCatPropDao commodityCatDao;
	@Resource
	private ICommoNoCreatorService commoNoCreatorService;
	
	@Override
	@Transactional(readOnly = true)
	public PageFinder<CommodityProp> pagedCommoPropList(CommodityPropVo propVo)
			throws Exception {
		StringBuffer hql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		hql.append(" select distinct p.* from tbl_commo_prop p left join tbl_commo_cat_prop cp on p.prop_no=cp.prop_no left join tbl_commo_prop_group_prop pgp on pgp.prop_no=p.prop_no  where p.delete_flag=:deleteFlag ");
		values.put("deleteFlag", propVo.getDeleteFlag());
		if(LogicUtil.isNotNullAndEmpty(propVo.getPropName())){
			hql.append(" and p.prop_name like :propName ");
			values.put("propName","%"+ propVo.getPropName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(propVo.getPropNo())){
			hql.append(" and p.prop_no = :propNo ");
			values.put("propNo", propVo.getPropNo());
		}
		if(LogicUtil.isNotNullAndEmpty(propVo.getPropNos())){
			hql.append(" and p.prop_no in (:propNos) ");
			values.put("propNos", propVo.getPropNos());
		}
		if(LogicUtil.isNotNullAndEmpty(propVo.getCommodityCatNo())){
			hql.append(" and cp.cat_no = :catNo ");
			values.put("catNo", propVo.getCommodityCatNo());
		}
		if(LogicUtil.isNotNullAndEmpty(propVo.getPropGroupNo())){
			hql.append(" and pgp.group_no = :groupNo ");
			values.put("groupNo", propVo.getPropGroupNo());
		}
		if(propVo.getIsSpecProp()>-1){
			hql.append(" and p.is_spec_prop = :isSpecProp ");
			values.put("isSpecProp", propVo.getIsSpecProp());
		}
		return commodityPropDao.pagedBySQL(hql.toString(), propVo.getPageNo(), propVo.getPageSize(), values);
	}

	@Override
	@Transactional(readOnly = true)
	public CommodityProp getCommoProp(String propNo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(propNo);
		return commodityPropDao.get("propNo", propNo);
	}

	@Override
	@Transactional
	public void deleteCommoProp(String propNo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(propNo);
		CommodityProp prop = commodityPropDao.get("propNo", propNo);
		prop.setDeleteFlag(CommodityConstant.DELETE_FLAG);
		prop.setUpdateTime(new Date());
		commodityPropDao.update(prop);
	}

	@Override
	@Transactional
	public String saveCommoProp(CommodityPropVo commoProVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commoProVo.getPropName());
		CommodityProp prop = new CommodityProp();
		ObjectConvertUtil.convertVoToPoNoNull(commoProVo, prop);
		prop.setCreateTime(new Date());
		prop.setUpdateTime(new Date());
		String propNo = commoNoCreatorService.createPropNo();
		prop.setPropNo(propNo);
		commodityPropDao.save(prop);
		
		//保存商品属性和分类的关联
		for(String commoCatNo:commoProVo.getCommodityCatNos()){
			CommodityCatProp catProp = new CommodityCatProp();
			catProp.setCatNo(commoCatNo);
			catProp.setPropNo(propNo);
			commodityCatPropDao.save(catProp);
		}
		//保存商品属性值
		for(CommodityPropValueVo propValVo: commoProVo.getPropValues()){
			CommodityPropVal propVal = new CommodityPropVal();
			ObjectConvertUtil.convertVoToPoNoNull(propValVo, propVal);
			propVal.setCreateTime(new Date());
			propVal.setUpdateTime(new Date());
			propVal.setPropNo(propNo);
			propVal.setOptionNo(commoNoCreatorService.createValOptionNo());
			commodityPropValDao.save(propVal);
		}
		return propNo;
	}

	@Override
	@Transactional
	public void updateCommoProp(CommodityPropVo commoProVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commoProVo.getPropNo());
		CommodityProp prop = commodityPropDao.get("propNo", commoProVo.getPropNo());
		ObjectConvertUtil.convertVoToPoNoNull(commoProVo, prop);
		prop.setUpdateTime(new Date());
		commodityPropDao.update(prop);
		
		//保存商品属性和分类的关联
		//先删除之前的关联
		commodityCatPropDao.delete("propNo", prop.getPropNo());
		//保存新的关联
		for(String commoCatNo:commoProVo.getCommodityCatNos()){
			CommodityCatProp catProp = new CommodityCatProp();
			catProp.setCatNo(commoCatNo);
			catProp.setPropNo(prop.getPropNo());
			commodityCatPropDao.save(catProp);
		}
		//保存商品属性值
		for(CommodityPropValueVo propValVo: commoProVo.getPropValues()){
			CommodityPropVal propVal = new CommodityPropVal();
			if(LogicUtil.isNotNullAndEmpty(propValVo.getOptionNo())){
				Map<String,Object> values = new HashMap<String, Object>();
				values.put("optionNo", propValVo.getOptionNo());
				values.put("propNo", prop.getPropNo());
				propVal=commodityPropValDao.get(values);
			}
			ExceptionUtil.checkParamStringNull(propVal);
			ObjectConvertUtil.convertVoToPoNoNull(propValVo, propVal);
			if(LogicUtil.isNotNullAndEmpty(propValVo.getOptionNo())){
				propVal.setUpdateTime(new Date());
			}else{
				propVal.setCreateTime(new Date());
				propVal.setOptionNo(commoNoCreatorService.createValOptionNo());
			}
			propVal.setPropNo(prop.getPropNo());
			commodityPropValDao.merge(propVal);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommodityCat> getBaseCommoCategoryListByPropNo(String propNo)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(propNo);
		StringBuffer hql = new StringBuffer();
		hql.append(" select c from CommodityCat c,CommodityCatProp p where c.catNo=p.catNo and p.propNo=? ");
		return commodityCatDao.createQuery(hql.toString(), propNo).list();
	}

	@Override
	@Transactional
	public void saveCommoCatProp(CommodityPropVo commoProVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commoProVo.getPropNo());
		ExceptionUtil.checkParamCollection(commoProVo.getCommodityCatNos());
		for(String catNo:commoProVo.getCommodityCatNos()){
			CommodityCatProp catProp = new CommodityCatProp();
			catProp.setCatNo(catNo);
			catProp.setPropNo(commoProVo.getPropNo());
			commodityCatPropDao.save(catProp);
		}
	}

	@Override
	@Transactional
	public void deleteCommoCatProp(CommodityPropVo commoProVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commoProVo.getPropNo());
		ExceptionUtil.checkParamCollection(commoProVo.getCommodityCatNos());
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("propNo", commoProVo.getPropNo());
		conditions.put("catNo", commoProVo.getCommodityCatNos());
		commodityCatPropDao.delete(conditions);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommodityPropVal> getCommoPropValOptionList(String propNo)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(propNo);
		String hql = " from CommodityPropVal where propNo=? and deleteFlag=?";
		return commodityPropValDao.createQuery(hql, propNo,CommodityConstant.UNDELETE_FLAG).list();
	}

	@Override
	@Transactional
	public void deleteCommoPropVal(String propNo, List<String> propValNos)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(propNo);
		ExceptionUtil.checkParamCollection(propValNos);
		for(String propValNo:propValNos){
			Map<String,Object> conditions = new HashMap<String, Object>();
			conditions.put("propNo", propNo);
			conditions.put("optionNo", propValNo);
			CommodityPropVal propVal = commodityPropValDao.get(conditions);
			ExceptionUtil.checkParamStringNull(propVal);
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("updateTime", new Date());
		params.put("deleteFlag", CommodityConstant.DELETE_FLAG);
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("propNo", propNo);
		conditions.put("optionNo", propValNos);
		commodityPropValDao.updateList(params, conditions);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommodityProp> getCommoPropList(CommodityPropVo propVo)
			throws Exception {
		StringBuffer hql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		hql.append(" select distinct p.* from tbl_commo_prop p left join tbl_commo_cat_prop cp on p.prop_no=cp.prop_no left join tbl_commo_prop_group_prop pgp on pgp.prop_no=p.prop_no  where p.delete_flag=:deleteFlag ");
		values.put("deleteFlag", propVo.getDeleteFlag());
		if(LogicUtil.isNotNullAndEmpty(propVo.getPropName())){
			hql.append(" and p.prop_name like :propName ");
			values.put("propName","%"+ propVo.getPropName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(propVo.getPropNo())){
			hql.append(" and p.prop_no = :propNo ");
			values.put("propNo", propVo.getPropNo());
		}
		if(LogicUtil.isNotNullAndEmpty(propVo.getPropNos())){
			hql.append(" and p.prop_no in (:propNos) ");
			values.put("propNos", propVo.getPropNos());
		}
		if(LogicUtil.isNotNullAndEmpty(propVo.getCommodityCatNo())){
			hql.append(" and cp.cat_no = :catNo ");
			values.put("catNo", propVo.getCommodityCatNo());
		}
		if(LogicUtil.isNotNullAndEmpty(propVo.getPropGroupNo())){
			hql.append(" and pgp.group_no = :groupNo ");
			values.put("groupNo", propVo.getPropGroupNo());
		}
		if(propVo.getIsSpecProp()>-1){
			hql.append(" and p.is_spec_prop = :isSpecProp ");
			values.put("isSpecProp", propVo.getIsSpecProp());
		}
		return commodityPropDao.getListBySql(hql.toString(), values);
	}
	
}
