package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityShopDao;
import com.zl.bgec.basicapi.commodity.po.CommodityShop;
@Repository
public class CommodityShopDaoImpl extends HibernateBaseDao<CommodityShop> implements ICommodityShopDao{

}
