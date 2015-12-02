package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityServicePersonDao;
import com.zl.bgec.basicapi.commodity.po.CommodityServicePerson;

@Repository
public class CommodityServicePersonDaoImpl extends HibernateBaseDao<CommodityServicePerson> implements ICommodityServicePersonDao {

}
