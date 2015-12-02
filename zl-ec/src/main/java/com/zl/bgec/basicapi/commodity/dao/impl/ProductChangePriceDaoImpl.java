package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.IProductChangePriceDao;
import com.zl.bgec.basicapi.commodity.po.ProductChangePrice;

@Repository
public class ProductChangePriceDaoImpl extends HibernateBaseDao<ProductChangePrice> implements IProductChangePriceDao{

}
