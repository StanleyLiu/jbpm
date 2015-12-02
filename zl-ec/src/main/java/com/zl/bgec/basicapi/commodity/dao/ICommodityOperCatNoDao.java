package com.zl.bgec.basicapi.commodity.dao;


import com.zl.bgec.basicapi.basecomponent.orm.hibernate.IHibernateBaseDao;
import com.zl.bgec.basicapi.commodity.po.CommodityOperCatNo;

public interface ICommodityOperCatNoDao extends IHibernateBaseDao<CommodityOperCatNo>{
	
	public CommodityOperCatNo getOperatingCategoryNo(String noType, String oprtCatPno);


	public int updateOperatingCategoryNo(String noType, String catPno, int seq);
}
