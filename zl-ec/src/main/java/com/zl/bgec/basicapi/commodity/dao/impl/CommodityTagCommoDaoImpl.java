package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityTagCommoDao;
import com.zl.bgec.basicapi.commodity.po.CommodityTagCommo;
@Repository
public class CommodityTagCommoDaoImpl extends HibernateBaseDao<CommodityTagCommo> implements ICommodityTagCommoDao{

}
