package com.zl.bgec.basicapi.commodity.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.basecomponent.utils.ObjectConvertUtil;
import com.zl.bgec.basicapi.commodity.dao.ICommodityDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityShopDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityUserDao;
import com.zl.bgec.basicapi.commodity.po.Commodity;
import com.zl.bgec.basicapi.commodity.po.CommodityShop;
import com.zl.bgec.basicapi.commodity.po.CommodityUser;
import com.zl.bgec.basicapi.commodity.service.IOtherSystemService;
import com.zl.bgec.basicapi.commodity.vo.CommodityShopVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityUserVo;
import com.zl.bgec.basicapi.common.util.ExceptionUtil;
import com.zl.bgec.basicapi.common.util.LogicUtil;

@Service
public class OtherSystemServiceImpl implements IOtherSystemService{
	
	@Resource
	private ICommodityUserDao commodityUserDao;
	
	@Resource
	private ICommodityDao commodityDao;
	
	@Resource
	private ICommodityShopDao commodityShopDao;
	
	@Override
	public void saveCommodityUser(CommodityUserVo commodityUserVo)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commodityUserVo.getCommodityNo());
		ExceptionUtil.checkParamStringNullAndEmpty(commodityUserVo.getUserNo());
		
		CommodityUser commodityUser = new CommodityUser();
		ObjectConvertUtil.convertVoToPo(commodityUserVo, commodityUser);
		commodityUser.setCreateTime(new Date());
		commodityUserDao.save(commodityUser);
	}

	@Override
	public void deleteCommodityUser(CommodityUserVo commodityUserVo)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commodityUserVo.getUserNo());
		
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("id", commodityUserVo.getId());
		condition.put("id", commodityUserVo.getIds());
		condition.put("commodityNo", commodityUserVo.getCommodityNo());
		condition.put("userNo", commodityUserVo.getUserNo());
		commodityUserDao.delete(condition);
	}

	@Override
	public List<Commodity> getCommodityUserList(CommodityUserVo commodityUserVo)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		sql.append("select c.* from tbl_commodity c ");
		sql.append("join tbl_commodity_user cu on c.commo_no=cu.commo_no ");
		sql.append(" where 1=1 ");
		if(LogicUtil.isNotNullAndEmpty(commodityUserVo.getUserNo())){
			sql.append(" and cu.user_no = :userNo ");
			values.put("userNo", commodityUserVo.getUserNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commodityUserVo.getCommodityName())){
			sql.append(" and c.commo_name like :commoName ");
			values.put("commoName", "%"+commodityUserVo.getCommodityName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(commodityUserVo.getCommodityNo())){
			sql.append(" and cu.commo_no = :commoNo ");
			values.put("commoNo", commodityUserVo.getCommodityNo());
		}
		if(LogicUtil.isNotNull(commodityUserVo.getStartTime())&&LogicUtil.isNotNull(commodityUserVo.getEndTime())){
			sql.append(" and (cu.create_time between :startTime and :endTime) ");
			values.put("startTime", "%"+commodityUserVo.getStartTime()+"%");
			values.put("endTime", "%"+commodityUserVo.getEndTime()+"%");
		}
		return commodityDao.getListBySql(sql.toString(), values);
	}

	@Override
	public PageFinder<Commodity> pagedCommodityUserList(
			CommodityUserVo commodityUserVo) throws Exception {
		StringBuffer sql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		sql.append("select c.* from tbl_commodity c ");
		sql.append("join tbl_commodity_user cu on c.commo_no=cu.commo_no ");
		sql.append(" where 1=1 ");
		if(LogicUtil.isNotNullAndEmpty(commodityUserVo.getUserNo())){
			sql.append(" and cu.user_no = :userNo ");
			values.put("userNo", commodityUserVo.getUserNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commodityUserVo.getCommodityName())){
			sql.append(" and c.commo_name like :commoName ");
			values.put("commoName", "%"+commodityUserVo.getCommodityName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(commodityUserVo.getCommodityNo())){
			sql.append(" and cu.commo_no = :commoNo ");
			values.put("commoNo", commodityUserVo.getCommodityNo());
		}
		if(LogicUtil.isNotNull(commodityUserVo.getStartTime())&&LogicUtil.isNotNull(commodityUserVo.getEndTime())){
			sql.append(" and (cu.create_time between :startTime and :endTime) ");
			values.put("startTime", "%"+commodityUserVo.getStartTime()+"%");
			values.put("endTime", "%"+commodityUserVo.getEndTime()+"%");
		}
		return commodityDao.pagedBySQL(sql.toString(), commodityUserVo.getPageNo(), commodityUserVo.getPageSize(), values);
	}

	@Override
	public void saveCommodityShop(CommodityShopVo commodityShopVo)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commodityShopVo.getShopNo());
		ExceptionUtil.checkParamStringNullAndEmpty(commodityShopVo.getCommodityNo());
		
		CommodityShop commodityShop = new CommodityShop();
		ObjectConvertUtil.convertVoToPo(commodityShopVo, commodityShop);
		commodityShop.setCreateTime(new Date());
		commodityShopDao.save(commodityShop);
	}

	@Override
	public void deleteCommodityShop(CommodityShopVo commodityShopVo)
			throws Exception {
		ExceptionUtil.checkParamStringNullAndEmpty(commodityShopVo.getShopNo());
		ExceptionUtil.checkParamStringNullAndEmpty(commodityShopVo.getSellerNo());
		
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("id", commodityShopVo.getId());
		condition.put("id", commodityShopVo.getIds());
		condition.put("commodityNo", commodityShopVo.getCommodityNo());
		condition.put("shopNo", commodityShopVo.getShopNo());
		condition.put("sellerNo", commodityShopVo.getSellerNo());
		commodityUserDao.delete(condition);
	}

	@Override
	public List<Commodity> getCommodityShopList(CommodityShopVo commodityShopVo)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		sql.append("select c.* from tbl_commodity c ");
		sql.append("join tbl_commodity_shop cs on c.commo_no=cs.commodity_no ");
		sql.append(" where 1=1 ");
		if(LogicUtil.isNotNullAndEmpty(commodityShopVo.getSellerNo())){
			sql.append(" and cs.seller_no = :sellerNo ");
			values.put("sellerNo", commodityShopVo.getSellerNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commodityShopVo.getShopNo())){
			sql.append(" and cs.shop_no = :shopNo ");
			values.put("shopNo", commodityShopVo.getShopNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commodityShopVo.getCommodityName())){
			sql.append(" and c.commo_name like :commoName ");
			values.put("commoName", "%"+commodityShopVo.getCommodityName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(commodityShopVo.getCommodityNo())){
			sql.append(" and c.commo_no = :commoNo ");
			values.put("commoNo", commodityShopVo.getCommodityNo());
		}
		if(LogicUtil.isNotNull(commodityShopVo.getStartTime())&&LogicUtil.isNotNull(commodityShopVo.getEndTime())){
			sql.append(" and (cs.create_time between :startTime and :endTime) ");
			values.put("startTime", "%"+commodityShopVo.getStartTime()+"%");
			values.put("endTime", "%"+commodityShopVo.getEndTime()+"%");
		}
		return commodityDao.getListBySql(sql.toString(), values);
	}

	@Override
	public PageFinder<Commodity> pagedCommodityShopList(
			CommodityShopVo commodityShopVo) throws Exception {
		StringBuffer sql = new StringBuffer();
		Map<String,Object> values = new HashMap<String, Object>();
		sql.append("select c.* from tbl_commodity c ");
		sql.append("join tbl_commodity_shop cs on c.commo_no=cs.commodity_no ");
		sql.append(" where 1=1 ");
		if(LogicUtil.isNotNullAndEmpty(commodityShopVo.getSellerNo())){
			sql.append(" and cs.seller_no = :sellerNo ");
			values.put("sellerNo", commodityShopVo.getSellerNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commodityShopVo.getShopNo())){
			sql.append(" and cs.shop_no = :shopNo ");
			values.put("shopNo", commodityShopVo.getShopNo());
		}
		if(LogicUtil.isNotNullAndEmpty(commodityShopVo.getCommodityName())){
			sql.append(" and c.commo_name like :commoName ");
			values.put("commoName", "%"+commodityShopVo.getCommodityName()+"%");
		}
		if(LogicUtil.isNotNullAndEmpty(commodityShopVo.getCommodityNo())){
			sql.append(" and c.commo_no = :commoNo ");
			values.put("commoNo", commodityShopVo.getCommodityNo());
		}
		if(LogicUtil.isNotNull(commodityShopVo.getStartTime())&&LogicUtil.isNotNull(commodityShopVo.getEndTime())){
			sql.append(" and (cs.create_time between :startTime and :endTime) ");
			values.put("startTime", "%"+commodityShopVo.getStartTime()+"%");
			values.put("endTime", "%"+commodityShopVo.getEndTime()+"%");
		}
		return commodityDao.pagedBySQL(sql.toString(), commodityShopVo.getPageNo(), commodityShopVo.getPageSize(), values);
	}

}
