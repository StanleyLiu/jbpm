package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityServiceDao;
import com.zl.bgec.basicapi.commodity.po.CommodityService;

@Repository
public class CommodityServiceDaoImpl extends HibernateBaseDao<CommodityService> implements ICommodityServiceDao {

}
