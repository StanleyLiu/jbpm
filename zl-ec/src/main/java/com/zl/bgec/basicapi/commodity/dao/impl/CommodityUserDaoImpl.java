package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityUserDao;
import com.zl.bgec.basicapi.commodity.po.CommodityUser;
@Repository
public class CommodityUserDaoImpl extends HibernateBaseDao<CommodityUser> implements ICommodityUserDao{

}
