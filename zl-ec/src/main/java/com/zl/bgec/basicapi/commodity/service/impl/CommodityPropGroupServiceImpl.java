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
import com.zl.bgec.basicapi.commodity.dao.ICommodityCatDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityCatPropGroupDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityPropGroupDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityPropGroupPropDao;
import com.zl.bgec.basicapi.commodity.po.CommodityCat;
import com.zl.bgec.basicapi.commodity.po.CommodityCatPropGroup;
import com.zl.bgec.basicapi.commodity.po.CommodityPropGroup;
import com.zl.bgec.basicapi.commodity.po.CommodityPropGroupProp;
import com.zl.bgec.basicapi.commodity.service.ICommoNoCreatorService;
import com.zl.bgec.basicapi.commodity.service.ICommodityPropGroupService;
import com.zl.bgec.basicapi.commodity.vo.CommodityPropGroupVo;
import com.zl.bgec.basicapi.common.CommodityConstant;
import com.zl.bgec.basicapi.common.util.ExceptionUtil;
import com.zl.bgec.basicapi.common.util.LogicUtil;

@Service
public class CommodityPropGroupServiceImpl implements ICommodityPropGroupService{
	
	@Resource
	private ICommodityPropGroupDao commodityPropGroupDao;
	
	@Resource
	private ICommodityPropGroupPropDao commodityPropGroupPropDao;
	
	@Resource
	private ICommodityCatPropGroupDao commodityCatPropGroupDao;
	
	@Resource
	private ICommodityCatDao commodityCatDao;
	
	@Resource
	private ICommoNoCreatorService commoNoCreatorService;
	
	@Override
	@Transactional
	public String saveCommoPropGroup(CommodityPropGroupVo commoPropGroupVo)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commoPropGroupVo.getGroupName());
		//保存商品属性组
		CommodityPropGroup group = new CommodityPropGroup();
		ObjectConvertUtil.convertVoToPoNoNull(commoPropGroupVo,group);
		String groupNo = commoNoCreatorService.createGroupNo();
		group.setCreateTime(new Date());
		group.setUpdateTime(new Date());
		group.setGroupNo(groupNo);
		commodityPropGroupDao.save(group);
		
		//保存商品属性组和商品二级分类的关联
		for(String catNo:commoPropGroupVo.getCatNos()){
			CommodityCatPropGroup catPropGroup = new CommodityCatPropGroup();
			catPropGroup.setCatNo(catNo);
			catPropGroup.setGroupNo(groupNo);
			commodityCatPropGroupDao.save(catPropGroup);
		}
		//保存商品属性组和商品属性的关联
		for(String propNo:commoPropGroupVo.getPropNos()){
			CommodityPropGroupProp propGroupProp = new CommodityPropGroupProp();
			propGroupProp.setGroupNo(groupNo);
			propGroupProp.setPropNo(propNo);
			commodityPropGroupPropDao.save(propGroupProp);
		}
		return groupNo;
	}

	@Override
	@Transactional
	public void saveCommoCatPropGroup(CommodityPropGroupVo groupVo)
			throws Exception {
		ExceptionUtil.checkParamCollection(groupVo.getCatNos());
		ExceptionUtil.checkParamStringNullAndEmpty(groupVo.getGroupNo());
		if(groupVo.getRemoveBindFlag()==0){
			commodityCatPropGroupDao.delete("groupNo", groupVo.getGroupNo());
		}
		for(String catNo:groupVo.getCatNos()){
			CommodityCatPropGroup catPropGroup = new CommodityCatPropGroup();
			catPropGroup.setCatNo(catNo);
			catPropGroup.setGroupNo(groupVo.getGroupNo());
			commodityCatPropGroupDao.save(catPropGroup);
		}
	}

	@Override
	@Transactional
	public void deleteCommoPropGroup(String groupNo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(groupNo);
		CommodityPropGroup group = commodityPropGroupDao.get("groupNo", groupNo);
		group.setUpdateTime(new Date());
		group.setDeleteFlag(CommodityConstant.DELETE_FLAG);
		commodityPropGroupDao.update(group);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommodityPropGroup> getCommoPropGroupList(
			CommodityPropGroupVo groupVo) throws Exception {
		StringBuffer hql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		hql.append(" select distinct g.* from tbl_commo_prop_group g left join tbl_commo_cat_prop_group c on c.group_no=g.group_no where 1=1 ");
		if(groupVo.getDeleteFlag()>-1){
			hql.append(" and g.delete_flag = :deleteFlag ");
			values.put("deleteFlag", groupVo.getDeleteFlag());
		}
		if(LogicUtil.isNotNullAndEmpty(groupVo.getGroupName())){
			hql.append(" and g.group_name like  :groupName ");
			values.put("groupName","%"+ groupVo.getGroupName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(groupVo.getGroupNo())){
			hql.append(" and g.group_no =  :groupNo ");
			values.put("groupNo",groupVo.getGroupNo());
		}
		if(LogicUtil.isNotNullAndEmpty(groupVo.getCatNo())){
			hql.append(" and c.cat_no =  :catNo ");
			values.put("catNo",groupVo.getCatNo());
		}
		return commodityPropGroupDao.getListBySql(hql.toString(), values);
	}

	@Override
	@Transactional(readOnly = true)
	public PageFinder<CommodityPropGroup> pagedCommoPropGroupList(
			CommodityPropGroupVo groupVo) throws Exception {
		StringBuffer hql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		hql.append(" select distinct g.* from tbl_commo_prop_group g left join tbl_commo_cat_prop_group c on c.group_no=g.group_no where 1=1 ");
		if(groupVo.getDeleteFlag()>-1){
			hql.append(" and g.delete_flag = :deleteFlag ");
			values.put("deleteFlag", groupVo.getDeleteFlag());
		}
		if(LogicUtil.isNotNullAndEmpty(groupVo.getGroupName())){
			hql.append(" and g.group_name like  :groupName ");
			values.put("groupName","%"+ groupVo.getGroupName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(groupVo.getGroupNo())){
			hql.append(" and g.group_no =  :groupNo ");
			values.put("groupNo",groupVo.getGroupNo());
		}
		if(LogicUtil.isNotNullAndEmpty(groupVo.getCatNo())){
			hql.append(" and c.cat_no =  :catNo ");
			values.put("catNo",groupVo.getCatNo());
		}
		return commodityPropGroupDao.pagedBySQL(hql.toString(), groupVo.getPageNo(), groupVo.getPageSize(), values);
	}

	@Override
	@Transactional
	public void updateCommoPropGroup(CommodityPropGroupVo commoPropGroupVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commoPropGroupVo.getGroupNo());
		//更新商品属性组
		CommodityPropGroup group = commodityPropGroupDao.get("groupNo", commoPropGroupVo.getGroupNo());
		ObjectConvertUtil.convertVoToPoNoNull(commoPropGroupVo,group);
		group.setUpdateTime(new Date());
		commodityPropGroupDao.update(group);
		
		//保存商品属性组和商品二级分类的关联(先删除之前的关联)
		commodityCatPropGroupDao.delete("groupNo", group.getGroupNo());
		for(String catNo:commoPropGroupVo.getCatNos()){
			CommodityCatPropGroup catPropGroup = new CommodityCatPropGroup();
			catPropGroup.setCatNo(catNo);
			catPropGroup.setGroupNo(group.getGroupNo());
			commodityCatPropGroupDao.save(catPropGroup);
		}
		//保存商品属性组和商品属性的关联(先删除之前的关联)
		commodityPropGroupPropDao.delete("groupNo", group.getGroupNo());
		for(String propNo:commoPropGroupVo.getPropNos()){
			CommodityPropGroupProp propGroupProp = new CommodityPropGroupProp();
			propGroupProp.setGroupNo(group.getGroupNo());
			propGroupProp.setPropNo(propNo);
			commodityPropGroupPropDao.save(propGroupProp);
		}
		
	}

	@Override
	@Transactional
	public void updateCommoCatPropGroup(List<String> catNoList, String groupNo)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(groupNo);
		ExceptionUtil.checkParamCollection(catNoList);
		commodityCatPropGroupDao.delete("groupNo", groupNo);
		for(String catNo:catNoList){
			CommodityCatPropGroup catPropGroup = new CommodityCatPropGroup();
			catPropGroup.setCatNo(catNo);
			catPropGroup.setGroupNo(groupNo);
			commodityCatPropGroupDao.save(catPropGroup);
		}
	}

	@Override
	@Transactional
	public void saveCommoPropGroupProp(CommodityPropGroupVo groupVo)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(groupVo.getGroupNo());
		ExceptionUtil.checkParamCollection(groupVo.getPropNos());
		if(groupVo.getRemoveBindFlag()==0){
			commodityPropGroupPropDao.delete("groupNo", groupVo.getGroupNo());
		}
		for(String propNo:groupVo.getPropNos()){
			CommodityPropGroupProp propGroupProp = new CommodityPropGroupProp();
			propGroupProp.setGroupNo(groupVo.getGroupNo());
			propGroupProp.setPropNo(propNo);
			commodityPropGroupPropDao.save(propGroupProp);
		}
	}


	@Override
	@Transactional(readOnly = true)
	public List<CommodityCat> getCommoCategoryListByGroupNo(String groupNo)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(groupNo);
		String hql = "select cpg.commoCategory from CommodityCatPropGroup cpg where cpg.groupNo=?";
		return commodityCatDao.createQuery(hql, groupNo).list();
	}

	@Override
	@Transactional
	public void updateCommoPropGroupProp(String groupNo, List<String> propNoList)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(groupNo);
		ExceptionUtil.checkParamCollection(propNoList);
		//保存商品属性组和商品属性的关联(先删除之前的关联)
		commodityPropGroupPropDao.delete("groupNo", groupNo);
		for(String propNo:propNoList){
			CommodityPropGroupProp propGroupProp = new CommodityPropGroupProp();
			propGroupProp.setGroupNo(groupNo);
			propGroupProp.setPropNo(propNo);
			commodityPropGroupPropDao.save(propGroupProp);
		}
		
	}

}
