package com.zl.bgec.basicapi.shop.rest;

import java.util.Date;
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
import com.zl.bgec.basicapi.commodity.service.ICommodityCatService;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.ResultVo;
import com.zl.bgec.basicapi.common.oauth2.SdkConstants;
import com.zl.bgec.basicapi.common.oauth2.Signature;
import com.zl.bgec.basicapi.common.oauth2.UserInfo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;
import com.zl.bgec.basicapi.shop.po.Shop;
import com.zl.bgec.basicapi.shop.po.ShopType;
import com.zl.bgec.basicapi.shop.service.IShopCollectService;
import com.zl.bgec.basicapi.shop.service.IShopService;
import com.zl.bgec.basicapi.shop.service.IShopTypeService;
import com.zl.bgec.basicapi.shop.service.ShopTypeManager;
import com.zl.bgec.basicapi.shop.vo.CategoryDTO;
import com.zl.bgec.basicapi.shop.vo.ResponseVo;
import com.zl.bgec.basicapi.shop.vo.ShopCollectVo;
import com.zl.bgec.basicapi.shop.vo.ShopVo;

@Component
@Path("/service/front/shop")
public class FrontShopRest {
	@Resource
	private SdkConstants sdkConstants;
	@Resource
	private IShopService shopService;
	@Resource
	private IShopTypeService shopTypeService;
	@Resource
	private IShopCollectService shopCollectService;
	@Resource
	private ICommodityCatService commodityCatService;
	@Resource
	private ShopTypeManager shopTypeManager;

	private static Logger log = Logger.getLogger(FrontShopRest.class);
	/**
	 * 查询店铺分类接口
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Path("/queryShop")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String queryShopNo(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception{
		json = RestUtil.strDecode(json);
		BaseVo<ShopVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ShopVo>>(){}.getType());
		ShopVo shopVo = baseVo.getBody();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		Shop shop = shopService.getShopNo(userInfo.getId()+"");
		ResultVo result = new ResultVo(true,shop);
		return result.toString();
	}
	/**
	 * 查询店铺简介接口
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Path("/queryshopsummary")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String queryShopSummary(String json,@Context HttpHeaders header)throws Exception{
		json = RestUtil.strDecode(json);
		BaseVo<ShopVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ShopVo>>(){}.getType());
		ShopVo shopVo = baseVo.getBody();
		PageFinder<Map<String,Object>> shop = shopService.queryShopSummary(shopVo);
		ResultVo result = new ResultVo(true,shop);
		return result.toString();
	}
	
	@Path("/queryMyShop")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String queryMyShop(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception{
		json = RestUtil.strDecode(json);
		BaseVo<ShopVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ShopVo>>(){}.getType());
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		List<ShopVo> shop= shopService.getMyShop(userInfo.getId()+"");
		ResultVo result = new ResultVo(true,shop);
		return result.toString();
	}
	/**
	 * 查询店铺分类接口
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Path("/queryShopType")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String queryShopType(String json,@Context HttpHeaders header)throws Exception{
		json = RestUtil.strDecode(json);
		BaseVo<ShopVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ShopVo>>(){}.getType());
		List<ShopType> shop= shopTypeService.getShopType();
		ResultVo result = new ResultVo(true,shop);
		return result.toString();
	}
	/**
	 * 查询店铺商品接口
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Path("/queryProduct")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String queryProduct(String json,@Context HttpHeaders header)throws Exception{
		json = RestUtil.strDecode(json);
		BaseVo<Map<String,String>> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<Map<String,String>>>(){}.getType());
		PageFinder<Map<String,Object>> shop= shopService.pagedProduct(baseVo.getBody());
		ResultVo result = new ResultVo(true,shop);
		return result.toString();
	}
	/**
	 * 查询店铺团购商品接口
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Path("/queryGroupBuyProduct")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String queryGroupBuyProduct(String json,@Context HttpHeaders header)throws Exception{
		json = RestUtil.strDecode(json);
		BaseVo<Map<String,String>> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<Map<String,String>>>(){}.getType());
		PageFinder<Map<String,Object>> shop= shopService.pagedGroupBuyProduct(baseVo.getBody());
		ResultVo result = new ResultVo(true,shop);
		return result.toString();
	}
	/**
	 * 查询店铺详情
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Path("/queryShopDetail")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String queryShopDetail(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception{
		json = RestUtil.strDecode(json);
		BaseVo<ShopVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ShopVo>>(){}.getType());
		ShopVo shopVo = baseVo.getBody();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		Map<String,Object> shop= shopService.getShopDetail(shopVo.getShopNo(),userInfo.getId()+"");
		Map<String,String> params = new HashMap<String, String>();
		params.put("appKey",  RestUtil.strEncoder(sdkConstants.getAPI_KEY()));
		params.put("userId", userInfo.getId()+"");
		params.put("id", shopVo.getShopNo());
        String signature = Signature.computeSignature(params, sdkConstants.getAPI_SECRET());
        String url = String.format("%s?appKey=%s&signature=%s&userId=%s&id=%s",sdkConstants.getCLIENT_FINDBUSINESSFAVORITESTATUS_URI(),
        		 RestUtil.strEncoder(sdkConstants.getAPI_KEY()), RestUtil.strEncoder(signature),userInfo.getId()+"",shopVo.getShopNo());
        String resultJson = RestUtil.restPostForm(url);
        Map<String,String> resultmap = GsonUtil.fromJson(resultJson, new TypeToken<Map<String,Object>>(){}.getType());
        if(resultmap!=null&&resultmap.get("response")!=null){
        	Object object = resultmap.get("response");
        	int status = (int)(double)object;
        	shop.put("isCollect", status + "");
        }
        //店铺类型
        List<ShopType> shopTypes = shopTypeManager.getShopTypes();
    	if(shopTypes!=null&&!shopTypes.isEmpty()){
    		for (ShopType shopType : shopTypes) {
				String shopTypeNo = shopType.getShopTypeNo();
				if(shopTypeNo!=null&&shopTypeNo.equals(shop.get("shopTypeNo"))){
					shop.put("shopTypeName",shopType.getTypeName());
				}
			}
    	}else{
    		Map<String,String> params2 = new HashMap<String, String>();
    		params2.put("appKey", sdkConstants.getAPI_KEY());
            String signature2 = Signature.computeSignature(params2, sdkConstants.getAPI_SECRET());
            params2.put("signature", signature2);
            String param2 = String.format("%s?appKey=%s&signature=%s",sdkConstants.getCLIENT_LISTBIZCATEGORIES_URI(), sdkConstants.getAPI_KEY(),RestUtil.strEncoder(signature2));
    		String responseJson2 = RestUtil.restPostForm(param2);
    		ResponseVo map = GsonUtil.fromJson(responseJson2, ResponseVo.class);
    		log.info("queryShopDetail-shopTypes="+GsonUtil.toJson(map));
    		int errorCode = map.getErrorCode();
    		if(errorCode==200){
    			List<CategoryDTO> cd = map.getResponse();
    			if(cd!=null&&!cd.isEmpty()){
    				for (CategoryDTO categoryDTO : cd) {
    					ShopType shopType = new ShopType();
    					shopType.setCreateTime(new Date(categoryDTO.getCreateTime()));
    					shopType.setTypeName(categoryDTO.getName());
    					shopType.setShopTypeNo(categoryDTO.getId()+"");
    					shopTypes.add(shopType);
    					String shopTypeNo = shopType.getShopTypeNo();
    					if(shopTypeNo.equals(shop.get("shopTypeNo"))){
    						shop.put("shopTypeName",shopType.getTypeName());
    					}
    				}
    			}
    		}else{
    			shop.put("shopTypeName","");
    			//throw new Exception("获取店铺类型失败，错误编码："+errorCode);
    		}
    		shopTypeManager.setShopTypes(shopTypes);
    	}
		ResultVo result = new ResultVo(true,shop);
		return result.toString();
	}
	
	/**
	 * 根据店铺分类查询店铺
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Path("/query")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String queryShop(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception{
		json = RestUtil.strDecode(json);
		BaseVo<ShopVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ShopVo>>(){}.getType());
		ShopVo shopVo = baseVo.getBody();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		List<Map<String,Object>> shop= shopService.getShop(shopVo.getShopName(),shopVo.getShopTypeNo(),userInfo.getId()+"");//TODO memberNo
		ResultVo result = new ResultVo(true,shop);
		return result.toString();
	}
	
	/**
	 *收藏店铺
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Path("/collect")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String addShopCollect(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception{
		json = RestUtil.strDecode(json);
		BaseVo<ShopCollectVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ShopCollectVo>>(){}.getType());
		ResultVo result = new ResultVo(true);
		try {
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
			ShopCollectVo shopCollect = baseVo.getBody();
			shopCollect.setMemberNo(userInfo.getId()+"");
			shopCollectService.saveShopCollect(shopCollect);
			
			Map<String,String> params = new HashMap<String, String>();
			params.put("appKey",  RestUtil.strEncoder(sdkConstants.getAPI_KEY()));
			params.put("userId", userInfo.getId()+"");
			params.put("id", shopCollect.getShopNo());
	        String signature = Signature.computeSignature(params, sdkConstants.getAPI_SECRET());
	        String param = String.format("%s?appKey=%s&signature=%s&userId=%s&id=%s",sdkConstants.getCLIENT_SYNCUSERFAVORITE_URI(),
	        		 RestUtil.strEncoder(sdkConstants.getAPI_KEY()), RestUtil.strEncoder(signature),userInfo.getId()+"",shopCollect.getShopNo());
			String responseJson = RestUtil.restPostForm(param);
			System.out.println(responseJson);
			log.debug("收藏店铺成功店铺编号："+baseVo.getBody().getShopNo());
		} catch (Exception e) {
			result = new ResultVo(false);
			log.error("收藏店铺失败："+e.getMessage(),e);
		}
		
		return result.toString();
	}
	
	@POST
	@Path("/queryCats")
	@Produces({ MediaType.APPLICATION_JSON })
	public String queryCatByShoId(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
	{
		json = RestUtil.strDecode(json);
		BaseVo<Map<String,String>> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<Map<String,String>>>(){}.getType());
		List<Map<String, String>> cats= commodityCatService.getCommodityCatList(baseVo.getBody());
		ResultVo result = new ResultVo(true,cats);
		return result.toString();
	}
	/**
	 *取消收藏店铺
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Path("/cancelCollect")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteShopCollect(String json,@Context HttpHeaders header,@Context HttpServletRequest request){
		json = RestUtil.strDecode(json);
		BaseVo<ShopCollectVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ShopCollectVo>>(){}.getType());
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		ResultVo result = new ResultVo(true);
		ShopCollectVo shopCollectVo = baseVo.getBody();
		try {
			shopCollectService.deleteShopCollect(shopCollectVo.getShopNo(), userInfo.getId()+"");
			Map<String,String> params = new HashMap<String, String>();
			params.put("appKey", sdkConstants.getAPI_KEY());
			params.put("userId", userInfo.getId()+"");
			params.put("id", shopCollectVo.getShopNo());
	        String signature = Signature.computeSignature(params, sdkConstants.getAPI_SECRET());
	        params.put("signature", signature);
	        String param = String.format("%s?appKey=%s&signature=%s&userId=%s&id=%s",sdkConstants.getCLIENT_SYNCUSERCANCELFAVORITE_URI(),
	        		sdkConstants.getAPI_KEY(),RestUtil.strEncoder(signature),userInfo.getId()+"",shopCollectVo.getShopNo());
			String responseJson = RestUtil.restPostForm(param);
			System.out.println(responseJson);
			log.debug("收藏店铺成功店铺编号："+baseVo.getBody().getShopNo());
		} catch (Exception e) {
			result = new ResultVo(false);
			log.error("收藏店铺失败："+e.getMessage(),e);
		}
		
		return result.toString();
	}
	public static void main(String[] args) {
		System.out.println(Math.pow(1.1, 20));
	}
}
