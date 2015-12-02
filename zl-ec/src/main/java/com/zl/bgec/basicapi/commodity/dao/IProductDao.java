package com.zl.bgec.basicapi.commodity.dao;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.IHibernateBaseDao;
import com.zl.bgec.basicapi.commodity.po.Product;

public interface IProductDao extends IHibernateBaseDao<Product>{
	public String getPrice(String commoNo) throws Exception;
}
