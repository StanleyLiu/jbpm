package com.zl.bgec.basicapi.order.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.order.dao.IOrderReturnDao;
import com.zl.bgec.basicapi.order.po.OrderReturn;
@Repository
public class OrderReturnDaoImpl extends HibernateBaseDao<OrderReturn> implements IOrderReturnDao{

}
