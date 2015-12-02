package com.zl.bgec.basicapi.order.rest;

import java.util.ArrayList;
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
import com.zl.bgec.basicapi.cart.vo.CartItemVo;
import com.zl.bgec.basicapi.commodity.service.ICommodityService;
import com.zl.bgec.basicapi.commodity.vo.CommodityCommentVo;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.ResultVo;
import com.zl.bgec.basicapi.common.oauth2.SdkConstants;
import com.zl.bgec.basicapi.common.oauth2.Signature;
import com.zl.bgec.basicapi.common.oauth2.UserDefaultAddressRestResponse;
import com.zl.bgec.basicapi.common.oauth2.UserInfo;
import com.zl.bgec.basicapi.common.oauth2.UserServiceAddressDTO;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;
import com.zl.bgec.basicapi.order.service.IOrderComponent;
import com.zl.bgec.basicapi.order.vo.ConfirmOrderObj;
import com.zl.bgec.basicapi.order.vo.OrderReturnVo;
import com.zl.bgec.basicapi.order.vo.OrderVo;
import com.zl.bgec.basicapi.order.vo.TempOrderVo;
import com.zl.bgec.basicapi.shop.service.IShopService;

@Component
@Path("/front/order")
public class FrontOrderRest {
	private static Logger log = Logger.getLogger(FrontOrderRest.class);
	@Resource
	private IOrderComponent orderComponent;
	@Resource
	private ICommodityService commodityService;
	@Resource
	private IShopService shopService;
	@Resource
	private SdkConstants sdkConstants;
	
	  /**
     * 查询我的订单列表
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/query")
    @Produces({ MediaType.APPLICATION_JSON })
    public String getMyOrders(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<OrderVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<OrderVo>>(){}.getType());
    	OrderVo orderVo = baseVo.getBody();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    	orderVo.setBuyerNo(userInfo.getId()+"");
    	PageFinder<Map<String,Object>> pageFinder = orderComponent.getMyOrders(orderVo);
    	
    	ResultVo result = new ResultVo(true,pageFinder);
        return result.toString();
    }
    @POST
    @Path("/querydeliverytype")
    @Produces({ MediaType.APPLICATION_JSON })
    public String getDeliveryType(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<com.zl.bgec.basicapi.shop.vo.ShopVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<com.zl.bgec.basicapi.shop.vo.ShopVo>>(){}.getType());
    	com.zl.bgec.basicapi.shop.vo.ShopVo shopVo = baseVo.getBody();
    	Map<String,Object> pageFinder = shopService.getDeliveryType(shopVo.getShopNo());
    	
    	ResultVo result = new ResultVo(true,pageFinder);
    	return result.toString();
    }
    @POST
    @Path("/queryAmount")
    @Produces({ MediaType.APPLICATION_JSON })
    public String getOrderAmount(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
    {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    	json = RestUtil.strDecode(json);
    	BaseVo<OrderVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<OrderVo>>(){}.getType());
    	OrderVo orderVo = baseVo.getBody();
    	orderVo.setBuyerNo(userInfo.getId()+"");
    	Map<String,Object> map = orderComponent.getOrderAmount(orderVo.getOrderNo());
    	
    	ResultVo result = new ResultVo(true,map);
    	return result.toString();
    }
    /**
     * 查询订单详情
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/detail")
    @Produces({ MediaType.APPLICATION_JSON })
    public String getOrderDetail(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
    {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    	json = RestUtil.strDecode(json);
    	BaseVo<OrderVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<OrderVo>>(){}.getType());
    	OrderVo orderVo = baseVo.getBody();
    	Map<String, Object> order = orderComponent.getOrderDetail(orderVo,userInfo.getId()+"");
    	ResultVo result = new ResultVo(true,order);
    	return result.toString();
    }
    /**
     * 关闭订单
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/cancel")
    @Produces({ MediaType.APPLICATION_JSON })
    public String closeOrder(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<OrderVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<OrderVo>>(){}.getType());
    	OrderVo orderVo = baseVo.getBody();
    	orderComponent.closeOrder(orderVo.getOrderNo(),orderVo.getCancelReason());
    	ResultVo result = new ResultVo(true);
    	return result.toString();
    }
    /**
     * 确认订单收货
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/receive")
    @Produces({ MediaType.APPLICATION_JSON })
    public String orderReceive(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<OrderVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<OrderVo>>(){}.getType());
    	OrderVo orderVo = baseVo.getBody();
    	orderComponent.confirmOrder(orderVo.getOrderNo());
    	ResultVo result = new ResultVo(true);
    	return result.toString();
    }
    /**
     * 申请退货
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/applyReturn")
    @Produces({ MediaType.APPLICATION_JSON })
    public String applyReturnOrder(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<OrderReturnVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<OrderReturnVo>>(){}.getType());
    	OrderReturnVo orderReturnVo = baseVo.getBody();
    	
    	orderComponent.applyReturnOrder(orderReturnVo);
    	ResultVo result = new ResultVo(true);
    	return result.toString();
    }
    /**
     * 删除订单
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/delete")
    @Produces({ MediaType.APPLICATION_JSON })
    public String deleteOrder(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<OrderVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<OrderVo>>(){}.getType());
    	OrderVo orderVO = baseVo.getBody();
    	orderComponent.deleteOrder(orderVO.getOrderNo());
    	ResultVo result = new ResultVo(true);
    	return result.toString();
    }
    /**
     * 评价
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/comment")
    @Produces({ MediaType.APPLICATION_JSON })
    public String commentCommodity(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
    {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    	json = RestUtil.strDecode(json);
    	BaseVo<CommodityCommentVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CommodityCommentVo>>(){}.getType());
    	CommodityCommentVo commentVo = baseVo.getBody();
    	commentVo.setMemberNo(userInfo.getId()+"");
    	commodityService.createCommdityComment(commentVo);
    	ResultVo result = new ResultVo(true);
    	return result.toString();
    }
    /**
     * 确认
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/confirm")
    @Produces({ MediaType.APPLICATION_JSON })
    public String confirmOrder(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
    {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    	json = RestUtil.strDecode(json);
    	BaseVo<List<CartItemVo>> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<List<CartItemVo>>>(){}.getType());
    	List<CartItemVo> cartItemVos = baseVo.getBody();
    	List<TempOrderVo> orders = orderComponent.confirmOrder(cartItemVos,userInfo.getId()+"");
    	
    	Map<String,String> params = new HashMap<String, String>();
		params.put("appKey",  RestUtil.strEncoder(sdkConstants.getAPI_KEY()));
		params.put("userId", userInfo.getId()+"");
        String signature = Signature.computeSignature(params, sdkConstants.getAPI_SECRET());
        String url = String.format("%s?appKey=%s&signature=%s&userId=%s",sdkConstants.getCLIENT_USER_DEFAULT_SERVERADDRESS_URI(),
        		 RestUtil.strEncoder(sdkConstants.getAPI_KEY()), RestUtil.strEncoder(signature),userInfo.getId()+"");
        
        log.info("confirm-query-url="+url);
        String resultJson = RestUtil.restPostForm(url);
        log.info("confirm-query-resultJson="+resultJson);
        
        UserDefaultAddressRestResponse response = GsonUtil.fromJson(resultJson,UserDefaultAddressRestResponse.class);
        UserServiceAddressDTO address = response.getResponse();
        
        ConfirmOrderObj obj = new ConfirmOrderObj();
        obj.setOrders(orders);
        obj.setAddress(address);
    	ResultVo result = new ResultVo(true,obj);
    	return result.toString();
    }
    
    /**
     * 团购立即购买
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/groupbuy")
    @Produces({ MediaType.APPLICATION_JSON })
    public String groupbuyConfirmOrder(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception
    {
    	UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    	json = RestUtil.strDecode(json);
    	BaseVo<CartItemVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CartItemVo>>(){}.getType());
    	CartItemVo cartItemVos = baseVo.getBody();
    	TempOrderVo order = orderComponent.confirmGroupbuyOrder(cartItemVos,userInfo.getId()+"");
    	
    	Map<String,String> params = new HashMap<String, String>();
    	params.put("appKey",  RestUtil.strEncoder(sdkConstants.getAPI_KEY()));
    	params.put("userId", userInfo.getId()+"");
    	String signature = Signature.computeSignature(params, sdkConstants.getAPI_SECRET());
    	String url = String.format("%s?appKey=%s&signature=%s&userId=%s",sdkConstants.getCLIENT_USER_DEFAULT_SERVERADDRESS_URI(),
    			RestUtil.strEncoder(sdkConstants.getAPI_KEY()), RestUtil.strEncoder(signature),userInfo.getId()+"");
    	
    	log.info("confirm-query-url="+url);
    	String resultJson = RestUtil.restPostForm(url);
    	log.info("confirm-query-resultJson="+resultJson);
    	
    	UserDefaultAddressRestResponse response = GsonUtil.fromJson(resultJson,UserDefaultAddressRestResponse.class);
    	UserServiceAddressDTO address = response.getResponse();
    	
    	List<TempOrderVo> orders = new ArrayList<TempOrderVo>();
    	orders.add(order);
    	ConfirmOrderObj obj = new ConfirmOrderObj();
    	obj.setOrders(orders);
    	obj.setAddress(address);
    	ResultVo result = new ResultVo(true,obj);
    	return result.toString();
    }
    /**
     * 生成订单
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/createGroupbuy")
    @Produces({ MediaType.APPLICATION_JSON })
    public String createGroupbuyOrder(String json,@Context HttpHeaders header,@Context HttpServletRequest request)
    {
    	UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    	json = RestUtil.strDecode(json);
    	BaseVo<TempOrderVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<TempOrderVo>>(){}.getType());
    	TempOrderVo tempOrderVo = baseVo.getBody();
    	String buyerPhone = null;
    	if(userInfo.getPhones() != null)
    		buyerPhone = userInfo.getPhones().get(0);
    	Map<String, Object> results;
		try {
			results = orderComponent.generateGroupbuyOrder(tempOrderVo,userInfo.getId()+"",userInfo.getNickName(),buyerPhone);
			ResultVo result = new ResultVo(true,results);
			return result.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map<String,String> re = new HashMap<String,String>();
			re.put("content", e.getMessage());
			ResultVo result = new ResultVo(false,re);
			return result.toString();
		}
    }
    /**
     * 生成订单
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/create")
    @Produces({ MediaType.APPLICATION_JSON })
    public String createOrder(String json,@Context HttpHeaders header,@Context HttpServletRequest request)
    {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    	json = RestUtil.strDecode(json);
    	BaseVo<List<TempOrderVo>> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<List<TempOrderVo>>>(){}.getType());
    	List<TempOrderVo> tempOrderVos = baseVo.getBody();
    	String buyerPhone = null;
    	if(userInfo.getPhones() != null)
    		buyerPhone = userInfo.getPhones().get(0);
    	List<Map<String, Object>> results;
		try {
			results = orderComponent.generateOrder(tempOrderVos,userInfo.getId()+"",userInfo.getNickName(),buyerPhone);
			ResultVo result = new ResultVo(true,results);
			return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> re = new HashMap<String,String>();
			re.put("content", e.getMessage());
			ResultVo result = new ResultVo(false,re);
			return result.toString();
		}
    }
    @POST
    @Path("/paycallback")
    @Produces({ MediaType.APPLICATION_JSON })
    public String payOrder(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<Map<String,String>> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<Map<String,String>>>(){}.getType());
    	Map<String,String> map = baseVo.getBody();
    	orderComponent.payCallBack(map);
    	ResultVo result = new ResultVo(true);
    	return result.toString();
    }
    @POST
    @Path("/payfail")
    @Produces({ MediaType.APPLICATION_JSON })
    public String payOrderFail(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<Map<String,String>> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<Map<String,String>>>(){}.getType());
    	Map<String,String> map = baseVo.getBody();
    	orderComponent.payFail(map);
    	ResultVo result = new ResultVo(true);
    	return result.toString();
    }
    
    @POST
    @Path("/refundCallback")
    @Produces({ MediaType.APPLICATION_JSON })
    public String refundCallback(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<Map<String,String>> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<Map<String,String>>>(){}.getType());
    	Map<String,String> map = baseVo.getBody();
    	orderComponent.refundCallback(map);
    	ResultVo result = new ResultVo(true);
    	return result.toString();
    }
    
}
