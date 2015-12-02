package com.zl.bgec.basicapi.order.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.order.dao.IOrderItemDao;
import com.zl.bgec.basicapi.order.po.OrderItem;
@Repository
public class OrderItemDaoImpl extends HibernateBaseDao<OrderItem> implements IOrderItemDao {

}
