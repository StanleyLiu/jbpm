package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityPurchaseDao;
import com.zl.bgec.basicapi.commodity.po.CommodityPurchase;

@Repository
public class CommodityPurchaseDaoImpl extends HibernateBaseDao<CommodityPurchase> implements ICommodityPurchaseDao {

}
