package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityOperCatCommoDao;
import com.zl.bgec.basicapi.commodity.po.CommodityOperCatCommo;
@Repository
public class CommodityOperCatCommoDaoImpl extends HibernateBaseDao<CommodityOperCatCommo> implements ICommodityOperCatCommoDao{

}
