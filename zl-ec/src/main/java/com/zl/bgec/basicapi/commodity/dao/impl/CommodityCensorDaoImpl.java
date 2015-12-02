package com.zl.bgec.basicapi.commodity.dao.impl;


import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityCensorDao;
import com.zl.bgec.basicapi.commodity.po.CommodityCensor;
@Repository
public class CommodityCensorDaoImpl extends HibernateBaseDao<CommodityCensor> implements ICommodityCensorDao{


}
