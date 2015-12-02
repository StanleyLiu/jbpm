package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.google.gson.reflect.TypeToken;
import com.zl.bgec.basicapi.cart.po.CartItem;
import com.zl.bgec.basicapi.cart.vo.CartItemVo;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;
import com.zl.bgec.basicapi.order.po.MemberAddress;
import com.zl.bgec.basicapi.order.po.OrderAddress;
import com.zl.bgec.basicapi.order.vo.OrderVo;
import com.zl.bgec.basicapi.order.vo.TempOrderVo;

public class TestOrder {
	@Test
	public void testQuery(){
		BaseVo<OrderVo> baseVo = new BaseVo<OrderVo>();
		OrderVo orderVo = new OrderVo();
		orderVo.setPageNo(1);
		orderVo.setPageSize(5);
		orderVo.setShopNo("14420606524766915070");
		baseVo.setBody(orderVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/order/order/queryPage");
		System.out.println(json);
	}
	@Test
	public void testQueryMemberAddress(){
		BaseVo<MemberAddress> baseVo = new BaseVo<MemberAddress>();
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/front/memberaddress/query");
		System.out.println(json);
	}
	@Test
	public void testaddMemberAddress(){
		BaseVo<MemberAddress> baseVo = new BaseVo<MemberAddress>();
		MemberAddress memberAddress = new MemberAddress();
		memberAddress.setAddress("xx区xx路xx号");
		memberAddress.setCellPhone("13332323232");
		memberAddress.setName("王老七");
		baseVo.setBody(memberAddress);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/front/memberaddress/add");
		System.out.println(json);
	}
	@Test
	public void testConfirm(){
		BaseVo<List<CartItemVo>> baseVo = new BaseVo<List<CartItemVo>>();
		List<CartItemVo> list = new ArrayList<CartItemVo>();
		CartItemVo cartItemVo = new CartItemVo();
		cartItemVo.setItemNo("14358301251173028727");
		cartItemVo.setShopNo("14197770082962049040");
		list.add(cartItemVo);
		cartItemVo.setItemNo("14358897001885629603");
		cartItemVo.setShopNo("14197770082962049040");
		list.add(cartItemVo);
		baseVo.setBody(list);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/front/order/confirm");
		System.out.println(json);
	}
	@Test
	public void testCreate(){
		BaseVo<List<TempOrderVo>> baseVo = new BaseVo<List<TempOrderVo>>();
		List<TempOrderVo> list = new ArrayList<TempOrderVo>();
		TempOrderVo tempOrderVo = new TempOrderVo();
		tempOrderVo.setBuyerMessage("请尽快发货");
		List<CartItem> cartItems = new ArrayList<CartItem>();
		CartItem cartItem = new CartItem();
		cartItem.setItemNo("14368550319748439833");
		cartItems.add(cartItem);
		
		tempOrderVo.setCartItems(cartItems);
		OrderAddress orderAddress = new OrderAddress();
		orderAddress.setAddress("xxxxxxxx");
		orderAddress.setCellphone("13444343423");
		orderAddress.setRealName("里斯");
		orderAddress.setSuperDate("xxxxx");
		tempOrderVo.setOrderAddress(orderAddress);
		tempOrderVo.setPromotionNo("150608201532701104");
		tempOrderVo.setDeliveryType("1");
		list.add(tempOrderVo);
		baseVo.setBody(list);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/front/order/create");
		System.out.println(json);
	}
	@Test
	public void testCreateGroupBuy(){
		String json = "{\"body\":{\"cartItems\":[{\"prodNo\":\"0100000014301\",\"quantity\":2}],\"promotionNo\":\"14462818148798903\",\"orderAddress\":{\"realName\":\"3fuyu\",\"cellphone\":\"13043479040\",\"address\":\"广东16-17A\",\"superDate\":\"10月31日07：00 - 08：00\"},\"buyerMessage\":\"sdfsdf\",\"deliveryType\":\"1\"}}";
    	BaseVo<TempOrderVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<TempOrderVo>>(){}.getType());
    	TempOrderVo tov = baseVo.getBody();
    	System.out.println(tov.getBuyerMessage());
	}
	
	@Test
	public void testQueryMyOrder(){
		BaseVo<OrderVo> baseVo = new BaseVo<OrderVo>();
		OrderVo orderVo = new OrderVo();
		orderVo.setPageNo(1);
		orderVo.setPageSize(2);
		orderVo.setQueryCondition("5");
		baseVo.setBody(orderVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/front/order/query");
		System.out.println(json);
	}
	@Test
	public void testQueryDetail(){
		BaseVo<OrderVo> baseVo = new BaseVo<OrderVo>();
		OrderVo orderVo = new OrderVo();
		orderVo.setOrderNo("1436856963020477037");
		baseVo.setBody(orderVo);
		System.out.println(GsonUtil.toJson(baseVo));
		String json = RestUtil.restWan(GsonUtil.toJson(baseVo),"http://localhost:8088/zl-ec/rest/front/order/detail");
		System.out.println(json);
	}
	public static void main(String[] args) {
		long days = 60*60*24*1000;
		Date now = new Date(System.currentTimeMillis()-days);
		System.out.println(now);
	}
}
