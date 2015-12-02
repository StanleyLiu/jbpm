package com.zl.bgec.basicapi.shop.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.shop.dao.IShopDao;
import com.zl.bgec.basicapi.shop.po.Shop;
@Repository
public class ShopDaoImpl extends HibernateBaseDao<Shop> implements IShopDao{

}
