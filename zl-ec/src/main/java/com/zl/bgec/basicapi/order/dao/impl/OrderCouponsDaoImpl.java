package com.zl.bgec.basicapi.order.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.order.dao.IOrderCouponsDao;
import com.zl.bgec.basicapi.order.po.OrderCoupons;
@Repository
public class OrderCouponsDaoImpl extends HibernateBaseDao<OrderCoupons> implements
		IOrderCouponsDao {

}
