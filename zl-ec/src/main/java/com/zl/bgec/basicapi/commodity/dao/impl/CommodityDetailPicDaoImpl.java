package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityDetailPicDao;
import com.zl.bgec.basicapi.commodity.po.CommodityDetailPic;

@Repository
public class CommodityDetailPicDaoImpl extends HibernateBaseDao<CommodityDetailPic> implements ICommodityDetailPicDao{

}
