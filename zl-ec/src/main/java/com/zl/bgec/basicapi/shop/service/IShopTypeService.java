package com.zl.bgec.basicapi.shop.service;

import java.util.List;

import com.zl.bgec.basicapi.shop.po.ShopType;
/**
 * 
 * @author Stanley
 *
 */
public interface IShopTypeService {
	/**
	 * 新增店铺分类
	 * @param shop
	 * @return
	 * @throws Exception
	 */
	public ShopType saveShopType(ShopType shop) throws Exception;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ShopType> getShopType()throws Exception;
	
	
}
