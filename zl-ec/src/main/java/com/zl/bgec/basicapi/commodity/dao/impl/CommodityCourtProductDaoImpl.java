package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityCourtProductDao;
import com.zl.bgec.basicapi.commodity.po.CommodityCourtProduct;

@Repository
public class CommodityCourtProductDaoImpl extends HibernateBaseDao<CommodityCourtProduct> implements ICommodityCourtProductDao {

}
