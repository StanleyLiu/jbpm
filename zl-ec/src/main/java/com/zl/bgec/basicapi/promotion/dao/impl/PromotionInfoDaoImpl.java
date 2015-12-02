package com.zl.bgec.basicapi.promotion.dao.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.promotion.dao.IPromotionInfoDao;
import com.zl.bgec.basicapi.promotion.po.PromotionInfo;
@Repository
public class PromotionInfoDaoImpl extends HibernateBaseDao<PromotionInfo> implements IPromotionInfoDao{

}
