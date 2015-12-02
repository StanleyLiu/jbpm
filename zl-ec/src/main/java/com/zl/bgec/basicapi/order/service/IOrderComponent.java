package com.zl.bgec.basicapi.order.service;

import java.util.List;
import java.util.Map;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.basicapi.cart.vo.CartItemVo;
import com.zl.bgec.basicapi.order.po.Order;
import com.zl.bgec.basicapi.order.vo.OrderReturnVo;
import com.zl.bgec.basicapi.order.vo.OrderVo;
import com.zl.bgec.basicapi.order.vo.TempOrderVo;
import com.zl.bgec.basicapi.promotion.vo.PromotionVo;


public interface IOrderComponent{
	/**
	 * 确认订单
	 * @return
	 * @throws Exception
	 */
	public List<TempOrderVo> confirmOrder(List<CartItemVo> cartItemVos,String memberNo)throws Exception;
	/**
	 * 确认团购订单
	 * @return
	 * @throws Exception
	 */
	public TempOrderVo confirmGroupbuyOrder(CartItemVo cartItemVo,String memberNo)throws Exception;
	
	/**
	 * 分页查询订单列表
	 * @param shopNo
	 * @param queryCondition
	 * @return
	 */
	public PageFinder<Map<String,Object>> getOrders(OrderVo orderVo)throws Exception;
	/**
	 * 查询订单详情
	 * @param orderVo
	 * @return
	 */
	public Map<String, Object> getOrderDetail(OrderVo orderVo,String memberNo)throws Exception;
	/**
	 * 获取24小时未支付订单
	 * @param orderVo
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public void cleanNoPayOrder()throws Exception;
	/**
	 * 订单发货
	 * @param orderVo
	 * @return
	 */
	public void deliveryOrder(OrderVo orderVo)throws Exception;
	/**
	 * 订单发货
	 * @param orderVo
	 * @return
	 */
	public void deleteOrder(String orderNo)throws Exception;
	/**
	 * 订单发货
	 * @param orderVo
	 * @return
	 */
	public void applyReturnOrder(OrderReturnVo orderReturnVo)throws Exception;
	
	/**
	 * 关闭订单
	 * @throws Exception
	 */
	public void closeOrder(String orderNo,String cancelReason)throws Exception;
	/**
	 * 确认订单收货
	 * @throws Exception
	 */
	public void confirmOrder(String orderNo)throws Exception;
	/**
	 * 确认订单收货
	 * @throws Exception
	 */
	public void autoConfirmOrder()throws Exception;
	/**
	 * 
	 * @param orderNo
	 * @throws Exception
	 */
	public void editOrderPrice(String orderNo,Double price)throws Exception;
	/**
	 * 同意订单退货
	 * @throws Exception
	 */
	public void agreeOrderReurn(String orderNo)throws Exception;
	/**
	 * 不同意订单退货
	 * @throws Exception
	 */
	public void disagreeOrderReurn(Map<String,String> orderVo)throws Exception;
	/**
	 * 验收同意订单退货
	 * @throws Exception
	 */
	public void agreeCheckOrderReurn(String orderNo)throws Exception;
	/**
	 * 支付成功回调
	 * @param map
	 * @throws Exception
	 */
	public void payCallBack(Map<String,String> map)throws Exception;
	/**
	 * 支付失败回调
	 * @param map
	 * @throws Exception
	 */
	public void payFail(Map<String,String> map)throws Exception;
	/**
	 * 验收不同意订单退货
	 * @throws Exception
	 */
	public void disagreeCheckOrderReurn(Map<String,String> orderVo)throws Exception;
	/**
	 * 查询我的订单
	 * @param orderVo
	 * @return
	 * @throws Exception
	 */
	public PageFinder<Map<String, Object>> getMyOrders(OrderVo orderVo)throws Exception;
	/**
	 * 查询我的订单
	 * @param orderVo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getOrderAmount(String orderNo)throws Exception;
	/**
	 * 生成订单
	 * @param tempOrderVos
	 * @param buyerNo
	 * @param buyerNickName 
	 * @param phone 
	 * @throws Exception
	 */
	public List<Map<String,Object>> generateOrder(List<TempOrderVo> tempOrderVos, String buyerNo, String buyerNickName, String buyerPhone)
			throws Exception;
	/**
	 * 生成订单
	 * @param tempOrderVos
	 * @param buyerNo
	 * @param buyerNickName 
	 * @param phone 
	 * @throws Exception
	 */
	public Map<String,Object> generateGroupbuyOrder(TempOrderVo tempOrderVo, String buyerNo, String buyerNickName, String buyerPhone)
			throws Exception;
	public void closeFinishedOrder()throws Exception;
	public PageFinder<Map<String, Object>> queryGroupOrder(PromotionVo promVo)throws Exception;
	public void refundCallback(Map<String, String> map)throws Exception;
}
