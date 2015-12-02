package com.zl.bgec.basicapi.shop.service;

import java.util.List;
import java.util.Map;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.shop.po.Shop;
import com.zl.bgec.basicapi.shop.vo.ShopVo;
/**
 * 
 * @author Stanley
 *
 */
public interface IShopService {
	/**
	 * 新增店铺
	 * @param shop
	 * @return
	 * @throws Exception
	 */
	public Shop saveShop(ShopVo shopVo) throws Exception;
	/**
	 * 我的银行卡
	 * @param shop
	 * @return
	 * @throws Exception
	 */
	public Shop getMycard(String shopNo) throws Exception;
	/**
	 * 绑定银行卡
	 * @param shopNo
	 * @return
	 * @throws Exception
	 */
	public void bindCard(ShopVo shopVo) throws Exception;
	
	
	public List<ShopVo> getMyShop(String memberNo)throws Exception;
	/**
	 * 
	 * @param shopNo
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getDeliveryType(String shopNo) throws Exception;
	/**
	 * 编辑店铺
	 * @param shopVo
	 * @return
	 * @throws Exception
	 */
	public Shop updateShop(ShopVo shopVo,String flag) throws Exception;
	/**
	 * 查询店铺详情
	 * @return
	 * @throws Exception
	 */
	public Shop getShopInfo(String shopNo)throws Exception;
	/**
	 * 查询店铺
	 * @return
	 * @throws Exception
	 */
	public Shop getShopNo(String memberNo)throws Exception;
	
	/**
	 * 查询店铺简介
	 * @param shopVo
	 * @return
	 * @throws Exception
	 */
	public PageFinder<Map<String,Object>> queryShopSummary(ShopVo shopVo)throws Exception;
	/**
	 * 查询店铺商品
	 * @return
	 * @throws Exception
	 */
	public PageFinder<Map<String,Object>> pagedProduct(Map<String,String> map)throws Exception;
	/**
	 * 查询店铺团购商品
	 * @return
	 * @throws Exception
	 */
	public PageFinder<Map<String,Object>> pagedGroupBuyProduct(Map<String,String> map)throws Exception;
	/**
	 * 查询店铺详情
	 * @return
	 * @throws Exception
	 */
	public String closeShop(Shop shop)throws Exception;
	/**
	 * 获取店铺首页信息
	 * @param shopNo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getShopIndexInfo(String shopNo)throws Exception;
	
	/**
	 * 根据分类编号查询店铺
	 * @param shopNo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getShop(String shpName,String shopTypeNo,String memberNo) throws Exception;
	
	/**
	 * 
	 */
	public Map<String,Object> getShopDetail(String shopNo,String memberNo)throws Exception;

	public List<Shop> getTestShopInfo(String column,String value);
	public Map<String, Object> getShopDetailNoUserId(String shopNo,String memberNo) throws Exception;
}
