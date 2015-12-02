package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityPicDao;
import com.zl.bgec.basicapi.commodity.po.CommodityPic;
@Repository
public class CommodityPicDaoImpl extends HibernateBaseDao<CommodityPic> implements ICommodityPicDao{

}
