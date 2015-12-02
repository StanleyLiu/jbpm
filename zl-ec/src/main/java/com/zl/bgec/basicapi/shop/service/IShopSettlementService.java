package com.zl.bgec.basicapi.shop.service;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.shop.po.ShopSettlement;

public interface IShopSettlementService {
	public PageFinder<ShopSettlement> getMyIncome(String shopNo,int pageNo,int pageSize)throws Exception;
}
