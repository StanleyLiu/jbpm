package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityPublishDao;
import com.zl.bgec.basicapi.commodity.po.CommodityPublish;
@Repository
public class CommodityPublishDaoImpl extends HibernateBaseDao<CommodityPublish> implements ICommodityPublishDao{

}
