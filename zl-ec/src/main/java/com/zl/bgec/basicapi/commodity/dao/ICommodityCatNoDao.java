package com.zl.bgec.basicapi.commodity.dao;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.IHibernateBaseDao;
import com.zl.bgec.basicapi.commodity.po.CommodityCatNo;

public interface ICommodityCatNoDao extends IHibernateBaseDao<CommodityCatNo>{
	
    public CommodityCatNo getCommoCategoryNo(String noType, String catPno)throws Exception;


    public int updateCommoCategoryNo(String noType, String catPno, int seq)throws Exception;
}
