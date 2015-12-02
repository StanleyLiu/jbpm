package com.zl.bgec.basicapi.cart.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.cart.dao.ICartItemDao;
import com.zl.bgec.basicapi.cart.po.CartItem;

@Repository
public class CartItemDaoImpl extends HibernateBaseDao<CartItem> implements ICartItemDao{

}
