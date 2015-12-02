package com.zl.bgec.basicapi.promotion.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import com.zl.bgec.basicapi.common.oauth2.SdkConstants;
import com.zl.bgec.basicapi.common.oauth2.Signature;
import com.zl.bgec.basicapi.common.oauth2.UserInfo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;
import com.zl.bgec.basicapi.promotion.service.IPromotionService;
import com.zl.bgec.basicapi.promotion.vo.PromotionVo;

@Component
@Path("/front/promotion")
public class FrontPromotionRest {
	
	private static Logger log = Logger.getLogger(FrontPromotionRest.class);
	@Resource
	private SdkConstants sdkConstants;
    @Resource
    private IPromotionService promotionServiceImpl;
    /**
     * 查询优惠券列表
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/query")
    @Produces({ MediaType.APPLICATION_JSON })
    public String getPromotion(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
    {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    	json = RestUtil.strDecode(json);
    	BaseVo<PromotionVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<PromotionVo>>(){}.getType());
    	PromotionVo promotionVo = baseVo.getBody();
    	List<Map<String,Object>> promotion= promotionServiceImpl.getPromotionByShopNo(promotionVo.getShopNo(),userInfo.getId()+"");
    	ResultVo result = new ResultVo(true,promotion);
        return result.toString();
    }
    @POST
    @Path("/queryall")
    @Produces({ MediaType.APPLICATION_JSON })
    public String getAllPromotion(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
    {
    	UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    	json = RestUtil.strDecode(json);
    	BaseVo<PromotionVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<PromotionVo>>(){}.getType());
    	PromotionVo promotionVo = baseVo.getBody();
    	PageFinder<Map<String,Object>> promotion= promotionServiceImpl.getPromotions(promotionVo.getShopNo(),userInfo.getId()+"",promotionVo.getPageNo(),promotionVo.getPageSize());
    	ResultVo result = new ResultVo(true,promotion);
    	return result.toString();
    }
    @POST
    @Path("/mypromotion")
    @Produces({ MediaType.APPLICATION_JSON })
    public String getMyPromotion(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
    {
    	UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    	json = RestUtil.strDecode(json);
    	BaseVo<PromotionVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<PromotionVo>>(){}.getType());
    	PromotionVo promotionVo = baseVo.getBody();
    	PageFinder<Map<String,Object>> promotion= promotionServiceImpl.getMyPromotion(userInfo.getId()+"", promotionVo.getQueryCondition(), 
    			promotionVo.getPageNo(), promotionVo.getPageSize());
    	ResultVo result = new ResultVo(true,promotion);
    	return result.toString();
    }
    @POST
    @Path("/mypromotiondetail")
    @Produces({ MediaType.APPLICATION_JSON })
    public String getMyPromotionDetail(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<PromotionVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<PromotionVo>>(){}.getType());
    	PromotionVo promotionVo = baseVo.getBody();
    	UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    	Map<String,Object> promotion= promotionServiceImpl.getDetail(promotionVo.getPromotionNo(),userInfo.getId()+"");
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
    @Path("/detail")
    @Produces({ MediaType.APPLICATION_JSON })
    public String getPromotionDetail(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<PromotionVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<PromotionVo>>(){}.getType());
    	PromotionVo promotionVo = baseVo.getBody();
    	UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    	PromotionVo promotion= promotionServiceImpl.getPromotionDetail(promotionVo.getPromotionNo(),userInfo.getId()+"");
    	ResultVo result = new ResultVo(true,promotion);
    	return result.toString();
    }
    /**
     * 领取优惠券
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/fetch")
    @Produces({ MediaType.APPLICATION_JSON })
    public String fetchPromotion(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<PromotionVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<PromotionVo>>(){}.getType());
    	PromotionVo promotionVo = baseVo.getBody();
    	UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    	promotionServiceImpl.fetchPromotion(promotionVo.getPromotionNo(),userInfo.getId()+"");
    	Map<String,String> params = new HashMap<String, String>();
		params.put("appKey",  RestUtil.strEncoder(sdkConstants.getAPI_KEY()));
		params.put("userId", userInfo.getId()+"");
        String signature = Signature.computeSignature(params, sdkConstants.getAPI_SECRET());
        params.put("signature", signature);
        String param = String.format("%s?appKey=%s&signature=%s&userId=%s",sdkConstants.getCLIENT_RECEIVECOUPON_URI(),
        		 RestUtil.strEncoder(sdkConstants.API_KEY), RestUtil.strEncoder(signature),userInfo.getId());
        
        log.info("shoprest-close-param="+param);
		String responseJson = RestUtil.restPostForm(param);
		log.info("shoprest-close-responseJson="+responseJson);
		
    	ResultVo result = new ResultVo(true);
    	return result.toString();
    }

    
}
