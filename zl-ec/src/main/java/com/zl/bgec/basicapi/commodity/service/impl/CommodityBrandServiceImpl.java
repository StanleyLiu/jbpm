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
import com.zl.bgec.basicapi.commodity.dao.ICommodityBrandDao;
import com.zl.bgec.basicapi.commodity.po.CommodityBrand;
import com.zl.bgec.basicapi.commodity.service.ICommoNoCreatorService;
import com.zl.bgec.basicapi.commodity.service.ICommodityBrandService;
import com.zl.bgec.basicapi.commodity.vo.CommodityBrandVo;
import com.zl.bgec.basicapi.common.CommodityConstant;
import com.zl.bgec.basicapi.common.util.ExceptionUtil;
import com.zl.bgec.basicapi.common.util.LogicUtil;

@Service
public class CommodityBrandServiceImpl implements ICommodityBrandService {

	@Resource
	private ICommodityBrandDao commodityBrandDao;
	@Resource
	private ICommoNoCreatorService commoNoCreatorService;

	@Override
	@Transactional
	public String saveBrand(CommodityBrandVo brandVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(brandVo.getBrandName());
		
		CommodityBrand brand = new CommodityBrand();
		ObjectConvertUtil.convertVoToPo(brandVo, brand);
		String brandNo = commoNoCreatorService.createBrandNo();
		brand.setBrandNo(brandNo);
		brand.setCreateTime(new Date());
		brand.setUpdateTime(new Date());
		brand.setDeleteFlag(CommodityConstant.UNDELETE_FLAG);
		commodityBrandDao.save(brand).toString();
		return brandNo;
	}

	@Override
	@Transactional
	public void deleteBrand(CommodityBrandVo brandVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(brandVo.getBrandNo());
		
		CommodityBrand brand = commodityBrandDao.get("brandNo", brandVo.getBrandNo());
		brand.setDeleteFlag(CommodityConstant.DEFAULT_FLAG);
		brand.setUpdateTime(new Date());
		commodityBrandDao.update(brand);
	}

	@Override
	@Transactional
	public void updateBrand(CommodityBrandVo brandVo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(brandVo.getBrandNo());
		
		CommodityBrand brand = commodityBrandDao.get("brandNo", brandVo.getBrandNo());
		ObjectConvertUtil.convertVoToPoNoNull(brandVo, brand);
		brand.setUpdateTime(new Date());
		commodityBrandDao.update(brand);
	}

	@Override
	@Transactional
	public void batchDisplayBrand(List<String> brandNos) throws Exception {
		ExceptionUtil.checkParamCollection(brandNos);
		
		commodityBrandDao.updateBrandDisplayStatus(brandNos,
				CommodityConstant.DISPLAY_FLAG);
	}

	@Override
	@Transactional
	public void batchHiddenBrand(List<String> brandNos) throws Exception {
		ExceptionUtil.checkParamCollection(brandNos);
		
		commodityBrandDao.updateBrandDisplayStatus(brandNos,
				CommodityConstant.HIDDEN_FLAG);
	}

	@Override
	@Transactional(readOnly = true)
	public CommodityBrand getBrand(String brandNo) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(brandNo);
		return commodityBrandDao.get(brandNo);
	}

	@Override
	@Transactional(readOnly = true)
	public CommodityBrand getBrandByName(String brandName) throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(brandName);
		return commodityBrandDao.getObjectByHql(
				"from CommodityBrand where brandName=? and deleteFlag=? ",
				brandName, CommodityConstant.UNDELETE_FLAG);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommodityBrand> getBrandListByName(String brandName)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(brandName);
		return commodityBrandDao.getListByHql(
				"from CommodityBrand where brandName like ? and deleteFlag=? ",
				brandName, CommodityConstant.UNDELETE_FLAG);
	}

	@Override
	@Transactional(readOnly = true)
	public PageFinder<CommodityBrand> pagedBrandList(CommodityBrandVo brandVo)
			throws Exception {
		StringBuffer hql = new StringBuffer();
		Map<String, Object> map = new HashMap<String, Object>();
		hql.append("from CommodityBrand b where 1=1 ");
		if (LogicUtil.isNotNullAndEmpty(brandVo.getBrandEnName())) {
			hql.append(" and b.brandEnName like :brandEnName ");
			map.put("brandEnName", "%" + brandVo.getBrandEnName() + "%");
		}
		if (LogicUtil.isNotNullAndEmpty(brandVo.getBrandNo())) {
			hql.append(" and b.brandNo=:brandNo  ");
			map.put("brandNo", brandVo.getBrandNo());
		}
		if (LogicUtil.isNotNullAndEmpty(brandVo.getBrandNos())) {
			hql.append(" and b.brandNo in (:brandNos)  ");
			map.put("brandNos", brandVo.getBrandNos());
		}
		if (LogicUtil.isNotNullAndEmpty(brandVo.getBrandName())) {
			hql.append(" and b.brandName LIKE :brandName  ");
			map.put("brandName", "%" + brandVo.getBrandName() + "%");
		}
		if (LogicUtil.isNotNullAndEmpty(brandVo.getBrandTitle())) {
			hql.append(" and b.brandTitle LIKE :brandTitle  ");
			map.put("brandTitle", "%" + brandVo.getBrandTitle() + "%");
		}
		if (brandVo.getIsDisplay() > -1) {
			hql.append(" and b.isDisplay=:isDisplay  ");
			map.put("isDisplay", brandVo.getIsDisplay());
		}
		hql.append(" and b.deleteFlag=:deleteFlag  ");
		map.put("deleteFlag", brandVo.getDeleteFlag());
		hql.append(" order by b.updateTime desc");
		return commodityBrandDao.pagedByHQL(hql.toString(),
				brandVo.getPageNo(), brandVo.getPageSize(), map);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommodityBrand> getBrandList(CommodityBrandVo brandVo)
			throws Exception {
		StringBuffer hql = new StringBuffer();
		Map<String, Object> map = new HashMap<String, Object>();
		hql.append("from CommodityBrand b where 1=1 ");
		if (LogicUtil.isNotNullAndEmpty(brandVo.getBrandEnName())) {
			hql.append(" and b.brandEnName like :brandEnName ");
			map.put("brandEnName", "%" + brandVo.getBrandEnName() + "%");
		}
		if (LogicUtil.isNotNullAndEmpty(brandVo.getBrandNo())) {
			hql.append(" and b.brandNo=:brandNo  ");
			map.put("brandNo", brandVo.getBrandNo());
		}
		if (LogicUtil.isNotNullAndEmpty(brandVo.getBrandNos())) {
			hql.append(" and b.brandNo in (:brandNos)  ");
			map.put("brandNos", brandVo.getBrandNos());
		}
		if (LogicUtil.isNotNullAndEmpty(brandVo.getBrandName())) {
			hql.append(" and b.brandName LIKE :brandName  ");
			map.put("brandName", "%" + brandVo.getBrandName() + "%");
		}
		if (LogicUtil.isNotNullAndEmpty(brandVo.getBrandTitle())) {
			hql.append(" and b.brandTitle LIKE :brandTitle  ");
			map.put("brandTitle", "%" + brandVo.getBrandTitle() + "%");
		}
		if (brandVo.getIsDisplay() > -1) {
			hql.append(" and b.isDisplay=:isDisplay  ");
			map.put("isDisplay", brandVo.getIsDisplay());
		}
		hql.append(" and b.deleteFlag=:deleteFlag  ");
		map.put("deleteFlag", brandVo.getDeleteFlag());
		hql.append(" order by b.updateTime desc");
		return commodityBrandDao.getListByHql(hql.toString(), map);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommodityBrand> getBrandListByBrandNos(List<String> brandNos) {
		return commodityBrandDao.getList("brandNo", brandNos.toArray());
	}

	@Override
	public PageFinder<CommodityBrand> pagedBrandListByOprtCatNo(
			String oprtCatNo, int pageNo, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Boolean> hasCommodity(List<String> brandNos)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommodityBrand> getBrandListForSearch(String word)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
