package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.IProductChangePriceItemDao;
import com.zl.bgec.basicapi.commodity.po.ProductChangePriceItem;

@Repository
public class ProductChangePriceItemDaoImpl extends HibernateBaseDao<ProductChangePriceItem> implements IProductChangePriceItemDao{

}
