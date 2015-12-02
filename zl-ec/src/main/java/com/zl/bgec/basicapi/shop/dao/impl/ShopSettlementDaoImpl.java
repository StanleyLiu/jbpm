package com.zl.bgec.basicapi.shop.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.shop.dao.IShopSettlementDao;
import com.zl.bgec.basicapi.shop.po.ShopSettlement;

@Repository
public class ShopSettlementDaoImpl extends HibernateBaseDao<ShopSettlement> implements IShopSettlementDao {

}
