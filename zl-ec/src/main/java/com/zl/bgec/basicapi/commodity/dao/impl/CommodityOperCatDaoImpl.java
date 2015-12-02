package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityOperCatDao;
import com.zl.bgec.basicapi.commodity.po.CommodityOperCat;
@Repository
public class CommodityOperCatDaoImpl extends HibernateBaseDao<CommodityOperCat> implements ICommodityOperCatDao{

}
