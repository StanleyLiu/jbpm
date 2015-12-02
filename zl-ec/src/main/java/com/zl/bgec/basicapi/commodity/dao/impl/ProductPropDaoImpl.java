package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.IProductPropDao;
import com.zl.bgec.basicapi.commodity.po.ProductProp;
@Repository
public class ProductPropDaoImpl extends HibernateBaseDao<ProductProp> implements IProductPropDao{
	
}
