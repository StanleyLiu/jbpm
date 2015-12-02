package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityTagDao;
import com.zl.bgec.basicapi.commodity.po.CommodityTag;
@Repository
public class CommodityTagDaoImpl extends HibernateBaseDao<CommodityTag> implements ICommodityTagDao{

}
