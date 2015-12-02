package com.zl.bgec.basicapi.promotion.dao;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.IHibernateBaseDao;
import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.promotion.po.Promotion;
import com.zl.bgec.basicapi.promotion.vo.PromotionVo;

public interface IPromotionDao extends IHibernateBaseDao<Promotion>{

	public PageFinder<Promotion> pageQueryPromotion(PromotionVo promotionVo, int pageNo, int pageSize);
	
}
