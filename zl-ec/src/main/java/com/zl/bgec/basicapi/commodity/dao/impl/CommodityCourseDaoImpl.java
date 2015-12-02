package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityCourseDao;
import com.zl.bgec.basicapi.commodity.po.CommodityCourse;

@Repository
public class CommodityCourseDaoImpl extends HibernateBaseDao<CommodityCourse> implements ICommodityCourseDao {

}
