package com.zl.bgec.basicapi.promotion.rest;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;
import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.ResultVo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;
import com.zl.bgec.basicapi.order.service.IOrderComponent;
import com.zl.bgec.basicapi.promotion.service.IPromotionService;
import com.zl.bgec.basicapi.promotion.vo.PromotionVo;

@Component
@Path("/promotion/promotion")
public class PromotionRest {

	private static Logger log = Logger.getLogger(PromotionRest.class);

	@Resource
	private IPromotionService promotionServiceImpl;
	@Resource
	private IOrderComponent orderComponent;
	
	/**
	 * 查询团购优惠券
	 */
	@POST
    @Path("/queryGroupOrder")
    @Produces({ MediaType.APPLICATION_JSON })
    public String queryGroupOrder(String json)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<PromotionVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<PromotionVo>>(){}.getType());
    	PromotionVo orderVo = baseVo.getBody();
    	PageFinder<Map<String, Object>> pageFinder = orderComponent.queryGroupOrder(orderVo);
    	
    	ResultVo result = new ResultVo(true,pageFinder);
        return result.toString();
    }
	
	/**
	 * 创建优惠活动
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_JSON })
	public String addPromotion(String json,@Context HttpHeaders header)throws Exception
	{
		json = RestUtil.strDecode(json);
		BaseVo<PromotionVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<PromotionVo>>(){}.getType());
		PromotionVo commodityVo = baseVo.getBody();
		String commodityNo= promotionServiceImpl.addPromotion(commodityVo);
		ResultVo result = new ResultVo(true);
		result.put("promotionNo", commodityNo);
		return result.toString();
	}
	/**
	 * 创建优惠活动
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/update")
	@Produces({ MediaType.APPLICATION_JSON })
	public String updatePromotion(String json,@Context HttpHeaders header)throws Exception
	{
		json = RestUtil.strDecode(json);
		BaseVo<PromotionVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<PromotionVo>>(){}.getType());
		PromotionVo commodityVo = baseVo.getBody();
		String commodityNo= promotionServiceImpl.updatePromotion(commodityVo);
		ResultVo result = new ResultVo(true);
		result.put("promotionNo", commodityNo);
		return result.toString();
	}
	/**
	 * 查询优惠券列表
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/query")
	@Produces({ MediaType.APPLICATION_JSON })
	public String getPromotion(String json,@Context HttpHeaders header)throws Exception
	{
		json = RestUtil.strDecode(json);
		BaseVo<PromotionVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<PromotionVo>>(){}.getType());
		PromotionVo promotionVo = baseVo.getBody();
		List<Map<String,Object>> promotion= promotionServiceImpl.getPromotion(promotionVo.getShopNo(),promotionVo.getQueryCondition());
		ResultVo result = new ResultVo(true,promotion);
		return result.toString();
	}
	/**
	 * 查询优惠券列表
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/queryPage")
	@Produces({ MediaType.APPLICATION_JSON })
	public String getPromotionPage(String json,@Context HttpHeaders header)throws Exception
	{
		json = RestUtil.strDecode(json);
		BaseVo<PromotionVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<PromotionVo>>(){}.getType());
		PromotionVo promotionVo = baseVo.getBody();
		PageFinder<Map<String,Object>> promotions= promotionServiceImpl.getPromotionPage(promotionVo.getShopNo(),promotionVo.getQueryCondition(),promotionVo.getPageNo(),promotionVo.getPageSize());
		ResultVo result = new ResultVo(true,promotions);
		return result.toString();
	}
	/**
	 * 查询优惠券列表
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/queryGroupbuyPage")
	@Produces({ MediaType.APPLICATION_JSON })
	public String getPromotionGroupPage(String json,@Context HttpHeaders header)throws Exception
	{
		json = RestUtil.strDecode(json);
		BaseVo<PromotionVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<PromotionVo>>(){}.getType());
		PromotionVo promotionVo = baseVo.getBody();
		PageFinder<Map<String,Object>> promotions= promotionServiceImpl.getGroupBuyPage(promotionVo.getShopNo(),promotionVo.getQueryCondition(),promotionVo.getPageNo(),promotionVo.getPageSize());
		ResultVo result = new ResultVo(true,promotions);
		return result.toString();
	}
	/**
	 * 查询优惠券列表
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/queryGroupbuyDetail")
	@Produces({ MediaType.APPLICATION_JSON })
	public String getPromotionGroupDetail(String json,@Context HttpHeaders header)throws Exception
	{
		json = RestUtil.strDecode(json);
		BaseVo<PromotionVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<PromotionVo>>(){}.getType());
		PromotionVo promotionVo = baseVo.getBody();
		Map<String,Object> promotion= promotionServiceImpl.getGroupBuyDetail(promotionVo.getPromotionNo());
		ResultVo result = new ResultVo(true,promotion);
		return result.toString();
	}
	/**
	 * 查询优惠券列表
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/delete")
	@Produces({ MediaType.APPLICATION_JSON })
	public String deletePromotion(String json,@Context HttpHeaders header)
	{
		json = RestUtil.strDecode(json);
		BaseVo<PromotionVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<PromotionVo>>(){}.getType());
		PromotionVo promotionVo = baseVo.getBody();
		try {
			promotionServiceImpl.delete(promotionVo.getPromotionNo());
			ResultVo result = new ResultVo(true);
			return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
			ResultVo result = new ResultVo(false);
			result.put("errDes", e.getMessage());
			return result.toString();
		}
	}
	/**
	 * 查询优惠券使用状况
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/queryUse")
	@Produces({ MediaType.APPLICATION_JSON })
	public String getPromotionUse(String json,@Context HttpHeaders header)throws Exception
	{
		json = RestUtil.strDecode(json);
		BaseVo<PromotionVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<PromotionVo>>(){}.getType());
		PromotionVo promotionVo = baseVo.getBody();
		List<Map<String, Object>> promotion= promotionServiceImpl.getPromotionUse(promotionVo.getPromotionNo(),promotionVo.getIsUsed());
		ResultVo result = new ResultVo(true,promotion);
		return result.toString();
	}
	/**
	 * 查询优惠券详情
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/queryDetailInfo")
	@Produces({ MediaType.APPLICATION_JSON })
	public String getPromotionDetail(String json,@Context HttpHeaders header)throws Exception
	{
		json = RestUtil.strDecode(json);
		BaseVo<PromotionVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<PromotionVo>>(){}.getType());
		PromotionVo promotionVo = baseVo.getBody();
		PromotionVo promotion= promotionServiceImpl.getPromotionDetail(promotionVo.getPromotionNo(),null);
		ResultVo result = new ResultVo(true,promotion);
		return result.toString();
	}
	
	@POST
	@Path("/getGroupDetail")
	@Produces({ MediaType.APPLICATION_JSON })
	public String getGroupDetail(String json,@Context HttpHeaders header)throws Exception
	{
		json = RestUtil.strDecode(json);
		BaseVo<PromotionVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<PromotionVo>>(){}.getType());
		PromotionVo promotionVo = baseVo.getBody();
		PromotionVo promotion= promotionServiceImpl.getGroupDetail(promotionVo.getPromotionNo());
		ResultVo result = new ResultVo(true,promotion);
		return result.toString();
	}
	
	
	/**
	 * 锁定解锁优惠券
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/lock")
	@Produces({ MediaType.APPLICATION_JSON })
	public String lockPromotion(String json,@Context HttpHeaders header)throws Exception
	{
		json = RestUtil.strDecode(json);
		BaseVo<Map<String,String>> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<Map<String,String>>>(){}.getType());
		Map<String,String> promotionVo = baseVo.getBody();
		ResultVo result = new ResultVo(true);
		try {
			promotionServiceImpl.lockPromotion(promotionVo.get("promotionNo"),promotionVo.get("lockFlag"));
		} catch (Exception e) {
			log.error("锁定优惠活动失败："+e.getMessage(),e);
			result = new ResultVo(false);
		}
		return result.toString();
	}
	/**
	 * 锁定解锁优惠券
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/publish")
	@Produces({ MediaType.APPLICATION_JSON })
	public String publishPromotion(String json,@Context HttpHeaders header)throws Exception
	{
		json = RestUtil.strDecode(json);
		BaseVo<Map<String,String>> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<Map<String,String>>>(){}.getType());
		Map<String,String> promotionVo = baseVo.getBody();
		ResultVo result = new ResultVo(true);
		try {
			promotionServiceImpl.publishPromotion(promotionVo.get("promotionNo"),promotionVo.get("publishFlag"));
		} catch (Exception e) {
			log.error("上架优惠活动失败："+e.getMessage(),e);
			result = new ResultVo(false);
		}
		return result.toString();
	}
	/**
	 * 推荐优惠券
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/recommend")
	@Produces({ MediaType.APPLICATION_JSON })
	public String recommendPromotion(String json,@Context HttpHeaders header)throws Exception
	{
		json = RestUtil.strDecode(json);
		BaseVo<Map<String,String>> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<Map<String,String>>>(){}.getType());
		Map<String,String> promotionVo = baseVo.getBody();
		ResultVo result = new ResultVo(true);
		try {
			promotionServiceImpl.recommendPromotion(promotionVo.get("promotionNo"),promotionVo.get("isRecommend"));
		} catch (Exception e) {
			log.error("推荐优惠活动失败："+e.getMessage(),e);
			result = new ResultVo(false);
		}
		return result.toString();
	}

	@POST
	@Path("/test")
	@Produces({ MediaType.APPLICATION_JSON })
	public void test(String json,@Context HttpHeaders header)throws Exception {
		promotionServiceImpl.groupProductInvalid();
	}


}
