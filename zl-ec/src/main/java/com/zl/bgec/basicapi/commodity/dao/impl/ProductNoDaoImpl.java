package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.IProductNoDao;
import com.zl.bgec.basicapi.commodity.po.ProductNo;

@Repository
public class ProductNoDaoImpl extends HibernateBaseDao<ProductNo> implements IProductNoDao{

}
