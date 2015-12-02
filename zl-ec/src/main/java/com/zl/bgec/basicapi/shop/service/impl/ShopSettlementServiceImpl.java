package com.zl.bgec.basicapi.shop.service.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.shop.dao.IShopSettlementDao;
import com.zl.bgec.basicapi.shop.po.ShopSettlement;
import com.zl.bgec.basicapi.shop.service.IShopSettlementService;
@Service
public class ShopSettlementServiceImpl implements IShopSettlementService {
	@Resource
	private IShopSettlementDao shopSettlementDao;
	@Override
	@Transactional(readOnly=true)
	public PageFinder<ShopSettlement> getMyIncome(String shopNo,int pageNo,int pageSize)
			throws Exception {
		Criteria criteria = shopSettlementDao.createCriteria(Restrictions.eq("shopNo", shopNo));
		criteria.addOrder(Order.desc("settlementTime"));
		return shopSettlementDao.pagedByCriteria(criteria, pageNo, pageSize);
	}

}
