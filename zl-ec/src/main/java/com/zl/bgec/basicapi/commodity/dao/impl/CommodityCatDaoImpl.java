package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityCatDao;
import com.zl.bgec.basicapi.commodity.po.CommodityCat;
@Repository
public class CommodityCatDaoImpl extends HibernateBaseDao<CommodityCat> implements ICommodityCatDao{

}
