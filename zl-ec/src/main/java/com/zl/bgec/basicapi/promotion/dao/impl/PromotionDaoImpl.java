package com.zl.bgec.basicapi.promotion.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.promotion.dao.IPromotionDao;
import com.zl.bgec.basicapi.promotion.po.Promotion;
import com.zl.bgec.basicapi.promotion.vo.PromotionVo;

@Repository
public class PromotionDaoImpl  extends HibernateBaseDao<Promotion>  implements IPromotionDao {

	@Override
	public PageFinder<Promotion> pageQueryPromotion(PromotionVo promotionVo, int pageNo, int pageSize) {
		Criteria criteria = this.createCriteria();
		criteria.add(Restrictions.like("name", promotionVo.getPromotionName()));
		return this.pagedByCriteria(criteria, pageNo, pageSize);
	}
}
