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
import com.zl.bgec.basicapi.commodity.dao.ICommodityOperCatCommoDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityOperCatDao;
import com.zl.bgec.basicapi.commodity.po.Commodity;
import com.zl.bgec.basicapi.commodity.po.CommodityOperCat;
import com.zl.bgec.basicapi.commodity.po.CommodityOperCatCommo;
import com.zl.bgec.basicapi.commodity.po.CommodityTagCommo;
import com.zl.bgec.basicapi.commodity.service.ICommoNoCreatorService;
import com.zl.bgec.basicapi.commodity.service.ICommodityOperCatService;
import com.zl.bgec.basicapi.commodity.vo.CommodityOperCatVo;
import com.zl.bgec.basicapi.common.CommodityConstant;
import com.zl.bgec.basicapi.common.util.ExceptionUtil;
import com.zl.bgec.basicapi.common.util.LogicUtil;
@Service
public class CommodityOperCatServiceImpl implements ICommodityOperCatService{
	
	@Resource
	private ICommodityOperCatDao commodityOperCatDao;
	
	@Resource
	private ICommodityOperCatCommoDao commodityOperCatCommoDao;
	
	@Resource
	private ICommodityDao commodityDao;
	
	@Resource
	private ICommoNoCreatorService commoNoCreatorService;
	

	@Override
	@Transactional
	public String saveCommodityOperCat(CommodityOperCatVo commodityOperCatVo)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commodityOperCatVo.getOprtCatName());
		CommodityOperCat operCat = new CommodityOperCat();
		ObjectConvertUtil.convertVoToPoNoNull(commodityOperCatVo, operCat);
		String operCatNo ="";
		if(commodityOperCatVo.getLevel()<1){
			ExceptionUtil.checkParamStringNull(null);
		}else if(commodityOperCatVo.getLevel()==1){
			operCatNo = commoNoCreatorService.createOprtCatNo(CommodityConstant.OPRT_CAT_NO_LEVEL_1, "0");
		}else{
			operCatNo = commoNoCreatorService.createOprtCatNo(CommodityConstant.OPRT_CAT_NO_LEVEL+commodityOperCatVo.getLevel(), operCat.getOprtCatPno());
		}
		operCat.setOprtCatNo(operCatNo);
		operCat.setCreateTime(new Date());
		operCat.setUpdateTime(new Date());
		commodityOperCatDao.save(operCat);
		return operCatNo;
	}

	@Override
	@Transactional
	public void deleteCommodityOper(String operCatNo) throws Exception {
		//先清楚和商品的关联
		ExceptionUtil.checkParamStringNullAndEmpty(operCatNo);
		commodityOperCatCommoDao.delete("operCatNo", operCatNo);
		commodityOperCatDao.delete("oprtCatNo", operCatNo);
	}

	@Override
	@Transactional
	public void updateCommodityOper(CommodityOperCatVo commodityOperCatVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commodityOperCatVo.getOprtCatNo());
		CommodityOperCat operCat = commodityOperCatDao.get("oprtCatNo", commodityOperCatVo.getOprtCatNo());
		ObjectConvertUtil.convertVoToPoNoNull(commodityOperCatVo, operCat);
		operCat.setUpdateTime(new Date());
		commodityOperCatDao.update(operCat);
	}


	@Override
	@Transactional(readOnly = true)
	public List<CommodityOperCat> getOperatorCats(CommodityOperCatVo commodityOperCatVo) throws Exception {
		StringBuffer hql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		hql.append(" from CommodityOperCat where deleteFlag = :deleteFlag ");
		values.put("deleteFlag", commodityOperCatVo.getDeleteFlag());
		if(LogicUtil.isNotNullAndEmpty(commodityOperCatVo.getOprtCatNo())){
			hql.append(" and oprtCatNo = :operCatNo ");
			values.put("operCatNo", commodityOperCatVo.getOprtCatNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commodityOperCatVo.getOprtCatNos())){
			hql.append(" and oprtCatNo in (:operCatNos) ");
			values.put("operCatNos", commodityOperCatVo.getOprtCatNos());
		}
		if(LogicUtil.isNotNullAndEmpty(commodityOperCatVo.getOprtCatName())){
			hql.append(" and oprtCatName like :oprtCatName ");
			values.put("oprtCatName","%"+commodityOperCatVo.getOprtCatName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(commodityOperCatVo.getOprtCatPno())){
			hql.append(" and oprtCatPno = :oprtCatPno ");
			values.put("oprtCatPno", commodityOperCatVo.getOprtCatPno());
		}
		if(commodityOperCatVo.getIsEnable()>-1){
			hql.append(" and isEnable = :isEnable ");
			values.put("isEnable", commodityOperCatVo.getIsEnable());
		}
		return commodityOperCatDao.getListByHql(hql.toString(), values);
	}



	@Override
	@Transactional(readOnly = true)
	public PageFinder<Commodity> pagedOprtCatRelateCommodityList(
			CommodityOperCatVo operCatVo) throws Exception {
		StringBuffer sql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		sql.append("select c.* from tbl_commodity c ");
		sql.append("join tbl_operating_cat_commo cc on c.commo_no=cc.commo_no ");
		sql.append(" where cc.oprt_cat_no = :operCatNo ");
		values.put("operCatNo", operCatVo.getOprtCatNo());
		if(LogicUtil.isNotNullAndEmpty(operCatVo.getBrandNo())){
			sql.append(" and c.brand_no=:brandNo");
			values.put("brandNo", operCatVo.getBrandNo());
		}
		if(LogicUtil.isNotNullAndEmpty(operCatVo.getCommoName())){
			sql.append(" and c.commo_name like :commoName");
			values.put("commoName", "%"+operCatVo.getCommoName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(operCatVo.getCommoNos())){
			sql.append(" and c.commo_no in (:commoNos)");
			values.put("commoNos", operCatVo.getCommoNos());
		}
		return commodityDao.pagedBySQL(sql.toString(), operCatVo.getPageNo(), operCatVo.getPageSize(), values);
	}

	@Override
	@Transactional(readOnly = true)
	public PageFinder<Commodity> pagedOprtCatNotRelateCommodityList(
			CommodityOperCatVo operCatVo) throws Exception {
		StringBuffer sql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		sql.append("select c.* from tbl_commodity c where c.commo_no not in ");
		sql.append(" (select commo_no from tbl_operating_cat_commo where oprt_cat_no = :operCatNo)");
		values.put("operCatNo", operCatVo.getOprtCatNo());
		if(LogicUtil.isNotNullAndEmpty(operCatVo.getBrandNo())){
			sql.append(" and c.brand_no=:brandNo");
			values.put("brandNo", operCatVo.getBrandNo());
		}
		if(LogicUtil.isNotNullAndEmpty(operCatVo.getCommoName())){
			sql.append(" and c.commo_name like :commoName");
			values.put("commoName", "%"+operCatVo.getCommoName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(operCatVo.getCommoNos())){
			sql.append(" and c.commo_no in (:commoNos)");
			values.put("commoNos", operCatVo.getCommoNos());
		}
		return commodityDao.pagedBySQL(sql.toString(), operCatVo.getPageNo(), operCatVo.getPageSize(), values);
	}

	@Override
	@Transactional
	public void updateOperatingCatCommo(CommodityOperCatVo operCatVo)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(operCatVo.getOprtCatNo());
		if(operCatVo.getRemoveBindFlag()==0){
			commodityOperCatCommoDao.delete("operCatNo", operCatVo.getOprtCatNo());
		}
		//新建关联
		for(String commoNo:operCatVo.getCommoNos()){
			CommodityOperCatCommo operCatCommo = new CommodityOperCatCommo();
			operCatCommo.setCommoNo(commoNo);
			operCatCommo.setOperCatNo(operCatVo.getOprtCatNo());
			commodityOperCatCommoDao.save(operCatCommo);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public void deleteOperatingCatCommo(String operCatNo,
			List<String> commoNoList) throws Exception {
		ExceptionUtil.checkParamCollection(commoNoList);
		ExceptionUtil.checkParamStringNullAndEmpty(operCatNo);
		
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("operCatNo", operCatNo);
		conditions.put("commoNo", commoNoList);
		commodityOperCatCommoDao.delete(conditions);
	}

	@Override
	@Transactional(readOnly = true)
	public PageFinder<CommodityOperCat> getRealateOprtCatByCommono(
			CommodityOperCatVo operCatVo) throws Exception {
		StringBuffer sql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		sql.append("select c.* from tbl_operating_category c ");
		sql.append("join tbl_operating_cat_commo cc on c.oprt_cat_no=cc.oprt_cat_no ");
		sql.append(" where cc.commo_no = :commoNo ");
		values.put("commoNo", operCatVo.getCommoNo());
		if(LogicUtil.isNotNullAndEmpty(operCatVo.getOprtCatName())){
			sql.append(" and c.oprt_cat_name like :oprtCatName");
			values.put("oprtCatName", "%"+operCatVo.getOprtCatName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(operCatVo.getOprtCatNo())){
			sql.append(" and c.oprt_cat_no = :oprtCatNo");
			values.put("oprtCatNo", "%"+operCatVo.getOprtCatNo()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(operCatVo.getOprtCatNos())){
			sql.append(" and c.oprt_cat_no (:oprtCatNos)");
			values.put("oprtCatNos", operCatVo.getOprtCatNos());
		}
		return commodityOperCatDao.pagedBySQL(sql.toString(), operCatVo.getPageNo(), operCatVo.getPageSize(), values);
	}

	@Override
	@Transactional(readOnly = true)
	public PageFinder<CommodityOperCat> getNotRealateOprtCatByCommono(
			CommodityOperCatVo operCatVo) throws Exception {
		StringBuffer sql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		sql.append("select c.* from tbl_operating_category c ");
		sql.append(" (select commo_no from tbl_operating_cat_commo where commo_no = :commoNo)");
		values.put("commoNo", operCatVo.getCommoNo());
		if(LogicUtil.isNotNullAndEmpty(operCatVo.getOprtCatName())){
			sql.append(" and c.oprt_cat_name like :oprtCatName");
			values.put("oprtCatName", "%"+operCatVo.getOprtCatName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(operCatVo.getOprtCatNo())){
			sql.append(" and c.oprt_cat_no = :oprtCatNo");
			values.put("oprtCatNo", "%"+operCatVo.getOprtCatNo()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(operCatVo.getOprtCatNos())){
			sql.append(" and c.oprt_cat_no (:oprtCatNos)");
			values.put("oprtCatNos", operCatVo.getOprtCatNos());
		}
		return commodityOperCatDao.pagedBySQL(sql.toString(), operCatVo.getPageNo(), operCatVo.getPageSize(), values);
	}

	@Override
	@Transactional
	public void saveOperatingCatCommo(CommodityOperCatVo operCatVo)
			throws Exception {
		ExceptionUtil.checkParamCollection(operCatVo.getOprtCatNos());
		ExceptionUtil.checkParamStringNullAndEmpty(operCatVo.getCommoNo());
		
		if(operCatVo.getRemoveBindFlag()==0){
			commodityOperCatCommoDao.delete("commoNo", operCatVo.getCommoNo());
		}
		//新建关联
		for(String oprtCatNo:operCatVo.getOprtCatNos()){
			CommodityOperCatCommo operCatCommo = new CommodityOperCatCommo();
			operCatCommo.setCommoNo(operCatVo.getCommoNo());
			operCatCommo.setOperCatNo(oprtCatNo);
			commodityOperCatCommoDao.save(operCatCommo);
		}
	}

	@Override
	@Transactional
	public void delOperatingCatCommo(List<String> oprtCatNoList, String commoNo)
			throws Exception {
		ExceptionUtil.checkParamCollection(oprtCatNoList);
		ExceptionUtil.checkParamStringNullAndEmpty(commoNo);
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("operCatNo", oprtCatNoList);
		conditions.put("commoNo", commoNo);
		commodityOperCatCommoDao.delete(conditions);
	}

	@Override
	@Transactional(readOnly = true)
	public PageFinder<CommodityOperCat> pagedOperatorCats(
			CommodityOperCatVo commodityOperCatVo) throws Exception {
		StringBuffer hql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		hql.append(" from CommodityOperCat where deleteFlag = :deleteFlag ");
		values.put("deleteFlag", commodityOperCatVo.getDeleteFlag());
		if(LogicUtil.isNotNullAndEmpty(commodityOperCatVo.getOprtCatNo())){
			hql.append(" and oprtCatNo = :operCatNo ");
			values.put("operCatNo", commodityOperCatVo.getOprtCatNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commodityOperCatVo.getOprtCatNos())){
			hql.append(" and oprtCatNo in (:operCatNos) ");
			values.put("operCatNos", commodityOperCatVo.getOprtCatNos());
		}
		if(LogicUtil.isNotNullAndEmpty(commodityOperCatVo.getOprtCatName())){
			hql.append(" and oprtCatName like :oprtCatName ");
			values.put("oprtCatName","%"+commodityOperCatVo.getOprtCatName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(commodityOperCatVo.getOprtCatPno())){
			hql.append(" and oprtCatPno = :oprtCatPno ");
			values.put("oprtCatPno", commodityOperCatVo.getOprtCatPno());
		}
		if(commodityOperCatVo.getIsEnable()>-1){
			hql.append(" and isEnable = :isEnable ");
			values.put("isEnable", commodityOperCatVo.getIsEnable());
		}
		return commodityOperCatDao.pagedByHQL(hql.toString(), commodityOperCatVo.getPageNo(), commodityOperCatVo.getPageSize(), values);
	}


}
