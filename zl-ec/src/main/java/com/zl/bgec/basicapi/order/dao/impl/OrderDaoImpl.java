package com.zl.bgec.basicapi.order.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.order.dao.IOrderDao;
import com.zl.bgec.basicapi.order.po.Order;
@Repository
public class OrderDaoImpl extends HibernateBaseDao<Order> implements IOrderDao {

}
