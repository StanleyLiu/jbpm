package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityCatPropDao;
import com.zl.bgec.basicapi.commodity.po.CommodityCatProp;
@Repository
public class CommodityCatPropDaoImpl extends HibernateBaseDao<CommodityCatProp> implements ICommodityCatPropDao{

}
