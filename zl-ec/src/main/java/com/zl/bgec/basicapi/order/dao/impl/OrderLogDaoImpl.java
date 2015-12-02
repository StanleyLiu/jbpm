package com.zl.bgec.basicapi.order.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.order.dao.IOrderLogDao;
import com.zl.bgec.basicapi.order.po.OrderLog;
@Repository
public class OrderLogDaoImpl extends HibernateBaseDao<OrderLog> implements IOrderLogDao {

}
