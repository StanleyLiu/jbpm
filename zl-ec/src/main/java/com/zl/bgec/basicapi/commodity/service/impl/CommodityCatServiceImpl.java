package com.zl.bgec.basicapi.commodity.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.basecomponent.utils.ObjectConvertUtil;
import com.zl.bgec.basicapi.commodity.dao.ICommodityCatDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityCatGroupPropDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityPropDao;
import com.zl.bgec.basicapi.commodity.po.CommodityCat;
import com.zl.bgec.basicapi.commodity.po.CommodityCatGroupProp;
import com.zl.bgec.basicapi.commodity.po.CommodityProp;
import com.zl.bgec.basicapi.commodity.po.CommodityPropGroup;
import com.zl.bgec.basicapi.commodity.po.CommodityPropGroupProp;
import com.zl.bgec.basicapi.commodity.service.ICommoNoCreatorService;
import com.zl.bgec.basicapi.commodity.service.ICommodityCatService;
import com.zl.bgec.basicapi.commodity.vo.CommodityCatVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityPropGroupPropVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityPropGroupVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityPropVo;
import com.zl.bgec.basicapi.common.CommodityConstant;
import com.zl.bgec.basicapi.common.util.ExceptionUtil;
import com.zl.bgec.basicapi.common.util.LogicUtil;

@Service
public class CommodityCatServiceImpl implements ICommodityCatService{
	
	
	@Resource
	private ICommoNoCreatorService commoNoCreatorService;

	@Resource
	private ICommodityCatDao commodityCatDao;
	
	@Resource
	private ICommodityCatGroupPropDao commodityCatGroupPropDao;
	
	@Resource
	private ICommodityPropDao commodityPropDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<CommodityCat> getCommodityCatList(CommodityCatVo commodityCatVo) throws Exception {
		StringBuffer hql = new StringBuffer();
		Map<String,Object> values = new  HashMap<String, Object>();
		hql.append(" from CommodityCat cat where 1=1 ");
		hql.append(" and cat.deleteFlag = :deleteFlag ");
		values.put("deleteFlag", commodityCatVo.getDeleteFlag());
		if(LogicUtil.isNotNullAndEmpty(commodityCatVo.getCatName())){
			hql.append(" and cat.catName like :catName ");
			values.put("catName", "%"+commodityCatVo.getCatName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(commodityCatVo.getCatNo())){
			hql.append(" and cat.catNo = :catNo ");
			values.put("catNo", commodityCatVo.getCatNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commodityCatVo.getCatPno())){
			hql.append(" and cat.catPno = :catPno ");
			values.put("catPno", commodityCatVo.getCatPno());
		}
		return commodityCatDao.createQuery(hql.toString(), values).list();
	}
	
	
	

	@Override
	@Transactional(readOnly = true)
	public PageFinder<CommodityCat> queryCommodityCatList(CommodityCatVo commodityCatVo)
			throws Exception {
		StringBuffer hql = new StringBuffer();
		Map<String,Object> values = new  HashMap<String, Object>();
		hql.append(" from CommodityCat cat where 1=1 ");
		hql.append(" and cat.deleteFlag = :deleteFlag ");
		values.put("deleteFlag", commodityCatVo.getDeleteFlag());
		if(LogicUtil.isNotNullAndEmpty(commodityCatVo.getCatName())){
			hql.append(" and cat.catName like :catName ");
			values.put("catName", "%"+commodityCatVo.getCatName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(commodityCatVo.getCatNo())){
			hql.append(" and cat.catNo = :catNo ");
			values.put("catNo", commodityCatVo.getCatNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commodityCatVo.getCatPno())){
			hql.append(" and cat.catPno = :catPno ");
			values.put("catPno", commodityCatVo.getCatPno());
		}
		return commodityCatDao.pagedByHQL(hql.toString(), commodityCatVo.getPageNo(), commodityCatVo.getPageSize(), values);
	}




	@Override
	@Transactional
	public String saveCommodityCat(CommodityCatVo commodityCatVo)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commodityCatVo.getCatName());
		CommodityCat cat = new CommodityCat();
		ObjectConvertUtil.convertVoToPoNoNull(commodityCatVo, cat);
		String catNo ="";
	    if("0".equals(commodityCatVo.getCatPno())){
			catNo = commoNoCreatorService.createCatNo(CommodityConstant.CAT_NO_LEVEL_1, "0");
		}else{
			catNo = commoNoCreatorService.createCatNo(CommodityConstant.CAT_NO_LEVEL+commodityCatVo.getLevel()/2+1, commodityCatVo.getCatPno());
		}
		cat.setCatNo(catNo);
		cat.setCreateTime(new Date());
		cat.setUpdateTime(new Date());
		commodityCatDao.save(cat);
		return catNo;
	}

	@Override
	@Transactional
	public void deleteCommodityCat(String catNo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(catNo);
		CommodityCat cat = commodityCatDao.get("catNo", catNo);
		cat.setDeleteFlag(CommodityConstant.DELETE_FLAG);
		cat.setUpdateTime(new Date());
		commodityCatDao.update(cat);
	}

	@Override
	@Transactional
	public void eidtCommodityCat(CommodityCatVo commodityCatVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commodityCatVo.getCatNo());
		CommodityCat cat =commodityCatDao.get("catNo", commodityCatVo.getCatNo());
		ObjectConvertUtil.convertVoToPoNoNull(commodityCatVo, cat);
		cat.setUpdateTime(new Date());
		commodityCatDao.update(cat);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommodityPropGroupVo> commonCatRealateProp(String catNo)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(catNo);
		List<CommodityPropGroupVo> groupVoList = new ArrayList<CommodityPropGroupVo>();
		StringBuffer hql = new StringBuffer();
		Map<String,Object> values = new  HashMap<String, Object>();
		hql.append(" select cpg.commoPropGroup from CommodityCatPropGroup cpg where cpg.catNo = :catNo");
		values.put("catNo", catNo);
		List<CommodityPropGroup> groupList = commodityCatDao.createQuery(hql.toString(), values).list();
		for(CommodityPropGroup group:groupList){
			CommodityPropGroupVo groupVo = new CommodityPropGroupVo();
			groupVo.setGroupName(group.getGroupName());
			groupVo.setGroupNo(group.getGroupNo());
			List<CommodityPropVo> propVoList = new ArrayList<CommodityPropVo>();
			for(CommodityPropGroupProp propGroupProp:group.getCommoPropGroupProps()){
				CommodityPropVo propVo = new CommodityPropVo();
				propVo.setPropName(propGroupProp.getCommoProp().getPropName());
				propVo.setPropNo(propGroupProp.getCommoProp().getPropNo());
				propVoList.add(propVo);
			}
			groupVo.setProps(propVoList);
			groupVoList.add(groupVo);
		}
		return groupVoList;
	}

	@Override
	@Transactional
	public void saveCommoCatGroupProp(CommodityCatVo commodityCatVo)
			throws Exception {
		String catNo = commodityCatVo.getCatNo();
		ExceptionUtil.checkParamStringNullAndEmpty(catNo);
		for(CommodityPropGroupPropVo vo :commodityCatVo.getPropGroupPropVos()){
			CommodityCatGroupProp catGroupProp = new CommodityCatGroupProp();
			catGroupProp.setCatNo(catNo);
			catGroupProp.setGroupNo(vo.getPropGroupNo());
			catGroupProp.setPropNo(vo.getPropNo());
			commodityCatGroupPropDao.save(catGroupProp);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommodityCatGroupProp> getCommoCatGroupPropList(String catNo)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(catNo);
		return commodityCatGroupPropDao.getList("catNo", catNo);
	}




	@Override
	@Transactional(readOnly = true)
	public List<CommodityProp> getCommoCatPropList(CommodityCatVo catVo)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(catVo.getCatNo());
		return commodityPropDao.createQuery("select ccgp.prop from CommodityCatGroupProp ccgp where ccgp.catNo =? and ccgp.prop.deleteFlag=? and ccgp.prop.isSpecProp=?", catVo.getCatNo(),CommodityConstant.UNDELETE_FLAG,catVo.getIsSpecProp()).list();
	}




	@Override
	public List<Map<String, String>> getCommodityCatList(Map<String, String> map)
			throws Exception {
		String shopNo = map.get("shopNo");
		ExceptionUtil.checkParamStringNullAndEmpty(shopNo);
		String sql = "select distinct(tc.cat_no) catNo,tcc.cat_name catName from tbl_commodity tc left join tbl_commo_category tcc on tc.cat_no = tcc.cat_no where tc.seller_no = :sellerNo";
		Query query = commodityCatDao.createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setParameter("sellerNo", shopNo);
		List<Map<String, String>> result = query.list();
		return result;
	}
	
	

}
