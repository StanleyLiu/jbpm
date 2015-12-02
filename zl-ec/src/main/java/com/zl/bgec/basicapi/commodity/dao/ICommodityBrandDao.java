package com.zl.bgec.basicapi.commodity.dao;


import java.util.List;



import com.zl.bgec.basicapi.basecomponent.orm.hibernate.IHibernateBaseDao;
import com.zl.bgec.basicapi.commodity.po.CommodityBrand;

public interface ICommodityBrandDao  extends IHibernateBaseDao<CommodityBrand>{
	
	public void updateBrandDisplayStatus(List<String> brandNos,byte isDisplay)throws Exception;
	
}
