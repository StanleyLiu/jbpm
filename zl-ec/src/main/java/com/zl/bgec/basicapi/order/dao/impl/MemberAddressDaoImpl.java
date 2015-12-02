package com.zl.bgec.basicapi.order.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.order.dao.IMemberAddressDao;
import com.zl.bgec.basicapi.order.po.MemberAddress;
@Repository
public class MemberAddressDaoImpl extends HibernateBaseDao<MemberAddress> implements IMemberAddressDao{

}
