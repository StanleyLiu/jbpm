package com.zl.bgec.basicapi.promotion.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.promotion.vo.PromotionVo;

@Service
public interface IPromotionService {
	/**
	 * 发布活动
	 * @param promotion
	 * @return
	 * @throws Exception
	 */
	public String addPromotion(PromotionVo promotion)  throws Exception;	
	/**
	 * 发布活动
	 * @param promotion
	 * @return
	 * @throws Exception
	 */
	public String updatePromotion(PromotionVo promotionVo)  throws Exception;	
	/**
	 * 查询优惠券使用状况
	 * @param promotionNo
	 * @param isUsed 0未使用，1已使用
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getPromotionUse(String promotionNo,String isUsed)  throws Exception;	
	/**
	 * 查询优惠活动列表
	 * @param shopNo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getPromotion(String shopNo,String type)  throws Exception;	
	/**
	 * 删除团购
	 * @param shopNo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public void delete(String promotionNo)  throws Exception;	
	/**
	 * 查询团购详情
	 * @param shopNo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getGroupBuyDetail(String promotionNo)  throws Exception;	
	
	public PageFinder<Map<String, Object>> getPromotions(String shopNo,String memberNo,int pageSize,int PageNo)  throws Exception;	
	/**
	 * 查询优惠活动列表
	 * @param shopNo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public PageFinder<Map<String, Object>> getPromotionPage(String shopNo,String type,int pageNo,int pageSize)  throws Exception;	
	/**
	 * 查询团购列表
	 * @param shopNo
	 * @param type
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageFinder<Map<String, Object>> getGroupBuyPage(String shopNo,String type,int pageNo,int pageSize)  throws Exception;	
	/**
	 * 查询优惠活动列表
	 * @param shopNo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getPromotionByShopNo(String shopNo,String memberNo)
			throws Exception;
	/**
	 * 查询优惠券详情
	 * @param promotionNo
	 * @return
	 * @throws Exception
	 */
	public PromotionVo getPromotionDetail(String promotionNo,String memberNo)throws Exception;
	/**
	 * 领取优惠券
	 * @param promotionNo
	 * @return
	 * @throws Exception
	 */
	public void fetchPromotion(String promotionNo,String memberNo)throws Exception;
	/**
	 * 锁定解锁优惠券
	 * @param promotionNo
	 * @return
	 * @throws Exception
	 */
	public void lockPromotion(String promotionNo,String lockFlag)throws Exception;
	/**
	 *  上架优惠券
	 * @param promotionNo
	 * @return
	 * @throws Exception
	 */
	public void publishPromotion(String promotionNo,String publishFlag)throws Exception;
	/**
	 * 推荐优惠
	 * @param promotionNo
	 * @param isRecommend
	 * @throws Exception
	 */
	public void recommendPromotion(String promotionNo, String isRecommend)
			throws Exception;
	/**
	 * 查询我的券
	 * @return
	 */
	public PageFinder<Map<String, Object>> getMyPromotion(String memberNo,String queryCondition,int pageNo,int pageSize);
	//前端获取优惠券详情
	public Map<String, Object> getDetail(String promotionNo,String memberNo) throws Exception;
	public void groupProductInvalid() throws Exception;
	public PromotionVo getGroupDetail(String promotionNo) throws Exception;
	
}