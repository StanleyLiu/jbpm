package com.zl.bgec.basicapi.cart.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.cart.dao.ICartDao;
import com.zl.bgec.basicapi.cart.po.Cart;
@Repository
public class CartDaoImpl extends HibernateBaseDao<Cart> implements ICartDao{

}
