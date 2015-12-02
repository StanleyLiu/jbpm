package com.zl.bgec.basicapi.commodity.service;

import java.util.List;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.commodity.po.Commodity;
import com.zl.bgec.basicapi.commodity.vo.CommodityShopVo;
import com.zl.bgec.basicapi.commodity.vo.CommodityUserVo;

public interface IOtherSystemService {
	/**
	 * 收藏商品
	 * @param commodityUserVo
	 * @throws Exception
	 */
	public void saveCommodityUser(CommodityUserVo commodityUserVo)throws Exception;
	/**
	 * 取消收藏
	 * @param commodityUserVo
	 * @throws Exception
	 */
	public void deleteCommodityUser(CommodityUserVo commodityUserVo)throws Exception;
	/**
	 * 获取用户收藏商品列表
	 * @param commodityUserVo
	 * @return
	 * @throws Exception
	 */
	public List<Commodity> getCommodityUserList(CommodityUserVo commodityUserVo)throws Exception;
	/**
	 * 获取用户收藏商品列表(分页)
	 * @param commodityUserVo
	 * @return
	 * @throws Exception
	 */
	public PageFinder<Commodity> pagedCommodityUserList(CommodityUserVo commodityUserVo)throws Exception;
	
	/**
	 * 店铺商品绑定
	 * @throws Exception
	 */
	public void saveCommodityShop(CommodityShopVo commodityShopVo)throws Exception;
	/**
	 * 取消店铺商品绑定
	 * @throws Exception
	 */
	public void deleteCommodityShop(CommodityShopVo commodityShopVo)throws Exception;
	/**
	 * 获取店铺商品
	 * @return
	 * @throws Exception
	 */
	public List<Commodity> getCommodityShopList(CommodityShopVo commodityShopVo)throws Exception;
	/**
	 * 获取店铺商品(分页)
	 * @return
	 * @throws Exception
	 */
	public PageFinder<Commodity> pagedCommodityShopList(CommodityShopVo commodityShopVo)throws Exception;
}
