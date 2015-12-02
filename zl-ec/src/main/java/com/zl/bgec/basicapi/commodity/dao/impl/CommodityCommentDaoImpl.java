package com.zl.bgec.basicapi.commodity.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.commodity.dao.ICommodityCommentDao;
import com.zl.bgec.basicapi.commodity.po.CommodityComment;

@Repository
public class CommodityCommentDaoImpl extends HibernateBaseDao<CommodityComment> implements ICommodityCommentDao{

}
