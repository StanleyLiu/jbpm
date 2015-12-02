package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityNoDao;
import com.zl.bgec.basicapi.commodity.po.CommodityNo;

@Repository
public class CommodityNoDaoImpl extends HibernateBaseDao<CommodityNo> implements ICommodityNoDao{

}
