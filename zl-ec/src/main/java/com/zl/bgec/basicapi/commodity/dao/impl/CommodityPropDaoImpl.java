package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityPropDao;
import com.zl.bgec.basicapi.commodity.po.CommodityProp;
@Repository
public class CommodityPropDaoImpl extends HibernateBaseDao<CommodityProp> implements ICommodityPropDao{

}
