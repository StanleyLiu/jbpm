package com.zl.bgec.basicapi.order.rest;

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
import com.zl.bgec.basicapi.order.po.Order;
import com.zl.bgec.basicapi.order.service.IOrderComponent;
import com.zl.bgec.basicapi.order.vo.OrderVo;

@Component
@Path("/order/order")
public class OrderRest {
	
	private static Logger log = Logger.getLogger(OrderRest.class);
	
    @Resource
    private IOrderComponent orderComponent;
    /**
     * 查询订单列表
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/queryPage")
    @Produces({ MediaType.APPLICATION_JSON })
    public String getOrder(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<OrderVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<OrderVo>>(){}.getType());
    	OrderVo orderVo = baseVo.getBody();
    	PageFinder<Map<String,Object>> orders= orderComponent.getOrders(orderVo);
    	ResultVo result = new ResultVo(true,orders);
        return result.toString();
    }
    /**
     * 关闭订单
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/close")
    @Produces({ MediaType.APPLICATION_JSON })
    public String closeOrder(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<OrderVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<OrderVo>>(){}.getType());
    	OrderVo orderVo = baseVo.getBody();
    	orderComponent.closeOrder(orderVo.getOrderNo(),orderVo.getCancelReason());
    	ResultVo result = new ResultVo(true);
    	return result.toString();
    }
    /**
     * 同意订单退货
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/agreeReturn")
    @Produces({ MediaType.APPLICATION_JSON })
    public String agreeOrderReurn(String json,@Context HttpHeaders header)
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<OrderVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<OrderVo>>(){}.getType());
    	OrderVo orderVo = baseVo.getBody();
    	try {
			orderComponent.agreeOrderReurn(orderVo.getOrderNo());
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
     * 不同意订单退货
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/disagreeReturn")
    @Produces({ MediaType.APPLICATION_JSON })
    public String disagreeOrderReurn(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<Map<String,String>> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<Map<String,String>>>(){}.getType());
    	Map<String,String> orderVo = baseVo.getBody();
    	orderComponent.disagreeOrderReurn(orderVo);
    	ResultVo result = new ResultVo(true);
    	return result.toString();
    }
    /**
     * 验收同意订单退货
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/agreeCheckReturn")
    @Produces({ MediaType.APPLICATION_JSON })
    public String agreeCheckOrderReurn(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<OrderVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<OrderVo>>(){}.getType());
    	OrderVo orderVo = baseVo.getBody();
    	orderComponent.agreeCheckOrderReurn(orderVo.getOrderNo());
    	ResultVo result = new ResultVo(true);
    	return result.toString();
    }
    /**
     * 验收不同意订单退货
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/disagreeCheckReturn")
    @Produces({ MediaType.APPLICATION_JSON })
    public String disagreeCheckOrderReurn(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<Map<String,String>> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<Map<String,String>>>(){}.getType());
    	Map<String,String> orderVo = baseVo.getBody();
    	orderComponent.disagreeOrderReurn(orderVo);
    	ResultVo result = new ResultVo(true);
    	return result.toString();
    }
    /**
     * 修改订单金额
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/editPrice")
    @Produces({ MediaType.APPLICATION_JSON })
    public String updateOrderPrice(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<OrderVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<OrderVo>>(){}.getType());
    	OrderVo orderVo = baseVo.getBody();
    	orderComponent.editOrderPrice(orderVo.getOrderNo(),orderVo.getFeeTotal());
    	ResultVo result = new ResultVo(true);
    	return result.toString();
    }
    /**
     * 订单发货
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/delivery")
    @Produces({ MediaType.APPLICATION_JSON })
    public String deliveryOrder(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<OrderVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<OrderVo>>(){}.getType());
    	OrderVo orderVo = baseVo.getBody();
    	orderComponent.deliveryOrder(orderVo);
    	ResultVo result = new ResultVo(true);
    	return result.toString();
    }
    /**
     * 订单批量发货
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/batchDelivery")
    @Produces({ MediaType.APPLICATION_JSON })
    public String batchDeliveryOrder(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<List<OrderVo>> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<List<OrderVo>>>(){}.getType());
    	List<OrderVo> orderVos = baseVo.getBody();
    	if(orderVos!=null&&!orderVos.isEmpty()){
    		for(OrderVo r:orderVos)
    			orderComponent.deliveryOrder(r);
    	}
    	ResultVo result = new ResultVo(true);
    	return result.toString();
    }
    /**
     * 查询订单详情
     * @param json
     * @return
     * @throws Exception
     */
    @POST
    @Path("/queryDetailInfo")
    @Produces({ MediaType.APPLICATION_JSON })
    public String getOrderDetail(String json,@Context HttpHeaders header)throws Exception
    {
    	json = RestUtil.strDecode(json);
    	BaseVo<OrderVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<OrderVo>>(){}.getType());
    	OrderVo orderVo = baseVo.getBody();
    	Map<String, Object> orders= orderComponent.getOrderDetail(orderVo,null);
    	ResultVo result = new ResultVo(true,orders);
    	return result.toString();
    }

    
}
