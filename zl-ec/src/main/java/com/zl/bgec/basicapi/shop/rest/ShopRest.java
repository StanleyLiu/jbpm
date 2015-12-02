package com.zl.bgec.basicapi.shop.rest;

import java.util.ArrayList;
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
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.ResultVo;
import com.zl.bgec.basicapi.common.oauth2.SdkConstants;
import com.zl.bgec.basicapi.common.oauth2.Signature;
import com.zl.bgec.basicapi.common.oauth2.UserInfo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;
import com.zl.bgec.basicapi.shop.po.Shop;
import com.zl.bgec.basicapi.shop.po.ShopSettlement;
import com.zl.bgec.basicapi.shop.po.ShopType;
import com.zl.bgec.basicapi.shop.service.IShopCollectService;
import com.zl.bgec.basicapi.shop.service.IShopService;
import com.zl.bgec.basicapi.shop.service.IShopSettlementService;
import com.zl.bgec.basicapi.shop.service.IShopTypeService;
import com.zl.bgec.basicapi.shop.service.ShopTypeManager;
import com.zl.bgec.basicapi.shop.vo.CategoryDTO;
import com.zl.bgec.basicapi.shop.vo.ResponseVo;
import com.zl.bgec.basicapi.shop.vo.ShopVo;

@Component
@Path("/service/shop")
public class ShopRest {
	@Resource
	private IShopService shopService;
	@Resource
	private ShopTypeManager shopTypeManager;
	@Resource
	private IShopTypeService shopTypeService;
	@Resource
	private IShopCollectService shopCollectService;
	@Resource
	private IShopSettlementService shopSettlementService;
	@Resource
	private SdkConstants sdkConstants;
	
	private static Logger log = Logger.getLogger(ShopRest.class);
	
	/**
	 * 查询店铺详情
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Path("/detailInfo")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getDetailInfo(String json,@Context HttpHeaders header)throws Exception{
		json = RestUtil.strDecode(json);
    	BaseVo<ShopVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ShopVo>>(){}.getType());
    	Shop shop= shopService.getShopInfo(baseVo.getBody().getShopNo());
    	List<ShopType> shopTypes = shopTypeManager.getShopTypes();
    	if(shopTypes!=null&&!shopTypes.isEmpty()){
    		for (ShopType shopType : shopTypes) {
				String shopTypeNo = shopType.getShopTypeNo();
				if(shopTypeNo!=null&&shopTypeNo.equals(shop.getShopTypeNo())){
					shop.setShopTypeName(shopType.getTypeName());
				}
			}
    	}else{
    		Map<String,String> params = new HashMap<String, String>();
    		params.put("appKey", sdkConstants.getAPI_KEY());
            String signature = Signature.computeSignature(params, sdkConstants.getAPI_SECRET());
            params.put("signature", signature);
            String param = String.format("%s?appKey=%s&signature=%s",sdkConstants.getCLIENT_LISTBIZCATEGORIES_URI(), sdkConstants.getAPI_KEY(),RestUtil.strEncoder(signature));
    		String responseJson = RestUtil.restPostForm(param);
    		ResponseVo map = GsonUtil.fromJson(responseJson, ResponseVo.class);
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
    					if(shopTypeNo.equals(shop.getShopTypeNo())){
    						shop.setShopTypeName(shopType.getTypeName());
    					}
    				}
    			}
    		}else{
    			throw new Exception("获取店铺类型失败，错误编码："+errorCode);
    		}
    		shopTypeManager.setShopTypes(shopTypes);
    	}
    	ResultVo result = new ResultVo(true,shop);
    	return result.toString();
	}
	/**
	 * 查询店铺首页信息
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Path("/queryShopIndexInfo")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getShopIndex(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception{
		json = RestUtil.strDecode(json);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		BaseVo<ShopVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ShopVo>>(){}.getType());
		log.info("用户信息userInfo:"+GsonUtil.toJson(userInfo));
		if(userInfo!=null){
			log.info("用户编号userId："+userInfo.getId());
			Map<String,Object> shop= shopService.getShopIndexInfo(userInfo.getId()+"");
	    	ResultVo result = new ResultVo(true,shop);
	    	return result.toString();
		}else{
			ResultVo result = new ResultVo(true);
			return result.toString();
		}
        
	}
	/**
	 * 创建店铺
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Path("/add")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String addShop(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception{
		json = RestUtil.strDecode(json);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		BaseVo<ShopVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ShopVo>>(){}.getType());
		ShopVo shopVo = baseVo.getBody();
		shopVo.setMerchNo(userInfo.getId()+"");
		Shop shop= shopService.saveShop(shopVo);
		ResultVo result = new ResultVo(true);
    	result.put("shopNo", shop.getShopNo());
    	
    	Map<String,String> params = new HashMap<String, String>();
		params.put("appKey", sdkConstants.getAPI_KEY());
		params.put("userId", userInfo.getId()+"");
        String signature = Signature.computeSignature(params, sdkConstants.getAPI_SECRET());
        params.put("signature",  RestUtil.strEncoder(signature));
        String param = String.format("%s?appKey=%s&signature=%s&userId=%s",
        		sdkConstants.getCLIENT_SYNCUSERADDSHOPSTATUS_URI(),
        		 RestUtil.strEncoder(sdkConstants.getAPI_KEY()),RestUtil.strEncoder(signature),userInfo.getId()+"");
		String responseJson = RestUtil.restPostForm(param);
		System.out.println(responseJson);
		ResponseVo map = GsonUtil.fromJson(responseJson, ResponseVo.class);
		
        return result.toString();
	}
	
	/**
	 * 编辑店铺信息
	 * @param json
	 * @return
	 */
	@Path("/update")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String updateShop(String json,@Context HttpHeaders header,@Context HttpServletRequest request){
		json = RestUtil.strDecode(json);
		BaseVo<ShopVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ShopVo>>(){}.getType());
		ResultVo result=null;
		try {
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
			ShopVo shopVo = baseVo.getBody();
			Shop shop = shopService.updateShop(shopVo,shopVo.getFlag());
			if(shopVo.getStatus()==2){//如果审核已通过同步到左邻
				SynchShop synchShop = new SynchShop();
		    	synchShop.userid=userInfo.getId()+"";
		    	synchShop.shop = shop;
		    	synchShop.run();
			}
			result = new ResultVo(true);
			log.debug("编辑店铺成功，店铺编号："+baseVo.getBody().getShopNo());
		} catch (Exception e) {
			result = new ResultVo(false);
			log.error("编辑店铺失败："+e.getMessage(),e);
		}
		return result.toString();
	}
	/**
	 * 根据店铺编号判断是否关闭
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Path("/isclosed")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String queryShopIsClosed(String json,@Context HttpHeaders header)throws Exception{
		json = RestUtil.strDecode(json);
		BaseVo<ShopVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ShopVo>>(){}.getType());
		Shop shop= shopService.getShopInfo(baseVo.getBody().getShopNo());
		
		ResultVo result = new ResultVo(true);
		result.put("isclosed", shop.getStatus()==3);
		return result.toString();
	}
	/**
	 * 根据店铺编号查询我的银行卡
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Path("/mycard")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getMyCard(String json,@Context HttpHeaders header)throws Exception{
		json = RestUtil.strDecode(json);
		BaseVo<ShopVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ShopVo>>(){}.getType());
		Shop shop= shopService.getMycard(baseVo.getBody().getShopNo());
		Map<String,String> map = new HashMap<String, String>();
		map.put("bankAccount", shop.getBankAccount());
		map.put("bankAccountName", shop.getBankAccountName());
		map.put("bankName", shop.getBankName());
		map.put("shopNo", shop.getShopNo());
		ResultVo result = new ResultVo(true,map);
		return result.toString();
	}
	/**
	 * 根据店铺编号查询我的银行卡
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Path("/bindcard")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String bindCard(String json,@Context HttpHeaders header)throws Exception{
		json = RestUtil.strDecode(json);
		BaseVo<ShopVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ShopVo>>(){}.getType());
		shopService.bindCard(baseVo.getBody());
		ResultVo result = new ResultVo(true);
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
		BaseVo<Shop> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<Shop>>(){}.getType());
		
		
		List<ShopType> shopTypes= new ArrayList<ShopType>();
		Map<String,String> params = new HashMap<String, String>();
		params.put("appKey", sdkConstants.getAPI_KEY());
        String signature = Signature.computeSignature(params, sdkConstants.getAPI_SECRET());
        params.put("signature", signature);
        String param = String.format("%s?appKey=%s&signature=%s",sdkConstants.getCLIENT_LISTBIZCATEGORIES_URI(),  RestUtil.strEncoder(sdkConstants.getAPI_KEY()), RestUtil.strEncoder(signature));
		String responseJson = RestUtil.restPostForm(param);
		ResponseVo map = GsonUtil.fromJson(responseJson, ResponseVo.class);
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
				}
			}
		}else{
			throw new Exception("获取店铺类型失败，错误编码："+errorCode);
		}
		shopTypeManager.setShopTypes(shopTypes);
		ResultVo result = new ResultVo(true,shopTypes);
		return result.toString();
	}
	@Path("/close")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String closeShop(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception{
		json = RestUtil.strDecode(json);
		BaseVo<Shop> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<Shop>>(){}.getType());
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		Shop shop = baseVo.getBody();
		String closeResult = shopService.closeShop(shop);
		if(closeResult.equals("hasOrder")){
			Map<String,String> map = new HashMap<String, String>();
			map.put("code", "4");
			map.put("content", "关闭店铺失败，存在未完成交易的订单");
			
			ResultVo result = new ResultVo(false,map);
			return result.toString();
		}
		Map<String,String> params = new HashMap<String, String>();
		params.put("appKey",  RestUtil.strEncoder(sdkConstants.getAPI_KEY()));
		params.put("id", shop.getShopNo());
		params.put("userId", userInfo.getId()+"");
        String signature = Signature.computeSignature(params, sdkConstants.getAPI_SECRET());
        params.put("signature", signature);
        String param = String.format("%s?appKey=%s&signature=%s&id=%s&userId=%s",sdkConstants.getCLIENT_SYNCDELETEBUSINESS_URI(),
        		 RestUtil.strEncoder(sdkConstants.API_KEY), RestUtil.strEncoder(signature),shop.getShopNo(),userInfo.getId());
        
        log.info("shoprest-close-param="+param);
		String responseJson = RestUtil.restPostForm(param);
		log.info("shoprest-close-responseJson="+responseJson);
		
		ResultVo result = new ResultVo(true);
		return result.toString();
	}
	/**
	 * 保存店铺分类
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Path("/addShopType")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String addShopType(String json,@Context HttpHeaders header)throws Exception{
		json = RestUtil.strDecode(json);
		BaseVo<ShopType> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ShopType>>(){}.getType());
		ShopType shop= shopTypeService.saveShopType(baseVo.getBody());
		ResultVo result = new ResultVo(true,shop);
		return result.toString();
	}
	/**
	 * 保存店铺分类
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Path("/queryIncome")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String queryIncome(String json,@Context HttpHeaders header)throws Exception{
		json = RestUtil.strDecode(json);
		BaseVo<ShopVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<ShopVo>>(){}.getType());
		ShopVo shopVo = baseVo.getBody();
		String shopNo = shopVo.getShopNo();
		int pageNo = shopVo.getPageNo();
		int pageSize = shopVo.getPageSize();
		PageFinder<ShopSettlement> shop= shopSettlementService.getMyIncome(shopNo, pageNo, pageSize);
		ResultVo result = new ResultVo(true,shop);
		return result.toString();
	}
}
