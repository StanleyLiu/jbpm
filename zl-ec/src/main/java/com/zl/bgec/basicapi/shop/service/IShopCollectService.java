package com.zl.bgec.basicapi.shop.service;

import com.zl.bgec.basicapi.shop.vo.ShopCollectVo;

/**
 * 店铺收藏
 * @author Stanley
 *
 */
public interface IShopCollectService {
	/**
	 * 收藏店铺
	 * @param shopCollectVo
	 */
	public void saveShopCollect(ShopCollectVo shopCollectVo)throws Exception;
	/**
	 * 删除收藏
	 * @param shopNo
	 * @param memberNo
	 */
	public void deleteShopCollect(String shopNo,String memberNo)throws Exception;

}
