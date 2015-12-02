package com.zl.bgec.basicapi.order.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.order.dao.IOrderAddressDao;
import com.zl.bgec.basicapi.order.po.OrderAddress;
@Repository
public class OrderDaoAddressImpl extends HibernateBaseDao<OrderAddress> implements IOrderAddressDao {

}
