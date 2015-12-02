package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.IProductStockDao;
import com.zl.bgec.basicapi.commodity.po.ProductStock;
@Repository
public class ProductStockDaoImpl extends HibernateBaseDao<ProductStock> implements IProductStockDao{

}
