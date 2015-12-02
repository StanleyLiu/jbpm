package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityCourtDao;
import com.zl.bgec.basicapi.commodity.po.CommodityCourt;

@Repository
public class CommodityCourtDaoImpl extends HibernateBaseDao<CommodityCourt> implements ICommodityCourtDao {

}
