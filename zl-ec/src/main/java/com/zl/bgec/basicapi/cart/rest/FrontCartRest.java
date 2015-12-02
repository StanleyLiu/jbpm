package com.zl.bgec.basicapi.cart.rest;

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
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;
import com.zl.bgec.basicapi.cart.po.Cart;
import com.zl.bgec.basicapi.cart.service.ICartComponent;
import com.zl.bgec.basicapi.cart.service.ICartItemComponent;
import com.zl.bgec.basicapi.cart.vo.CartItemVo;
import com.zl.bgec.basicapi.common.BaseVo;
import com.zl.bgec.basicapi.common.ResultVo;
import com.zl.bgec.basicapi.common.oauth2.UserInfo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;
import com.zl.bgec.basicapi.shop.dao.IShopDao;
import com.zl.bgec.basicapi.shop.dao.impl.ShopDaoImpl;
import com.zl.bgec.basicapi.shop.po.Shop;

@Component
@Path("/service/front/cart")
public class FrontCartRest {
	
	@Resource
	private ICartComponent cartComponent;
	@Resource
	private ICartItemComponent cartItemComponent;
	@Resource IShopDao shopDao;
 
	private static Logger log = Logger.getLogger(FrontCartRest.class);
	/**
	 * 根据店铺分类查询店铺
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Path("/add")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String addCartItem(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception{
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		json = RestUtil.strDecode(json);
		BaseVo<CartItemVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CartItemVo>>(){}.getType());
		CartItemVo cartItemVo = baseVo.getBody();
		cartItemVo.setMemberNo(userInfo.getId()+"");
		cartComponent.addItem(cartItemVo);
		ResultVo result = new ResultVo(true);
		return result.toString();
	}
	/**
	 * 删除购物车条目
	 * @param json
	 * @param header
	 * @return
	 * @throws Exception
	 */
	@Path("/delete")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteCartItems(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception{
		json = RestUtil.strDecode(json);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		BaseVo<List<CartItemVo>> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<List<CartItemVo>>>(){}.getType());
		List<CartItemVo> cartItemVo = baseVo.getBody();
		
		cartComponent.deleteItem(cartItemVo,userInfo.getId()+"");
		ResultVo result = new ResultVo(true);
		return result.toString();
	}
	/**
	 * 修改购物车
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Path("/update")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String updateCartItem(String json,@Context HttpHeaders header,@Context HttpServletRequest request){
		json = RestUtil.strDecode(json);
		BaseVo<CartItemVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CartItemVo>>(){}.getType());
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		CartItemVo cartItemVo = baseVo.getBody();
		cartItemVo.setMemberNo(userInfo.getId()+"");
		try {
			cartComponent.updateItem(cartItemVo);
			ResultVo result = new ResultVo(true);
			return result.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map<String,String> re = new HashMap<>();
			re.put("content", e.getMessage());
			ResultVo result = new ResultVo(false,re);
			return result.toString();
		}
	}
	/**
	 * 查询购物车
	 * @param json
	 * @param header
	 * @return
	 * @throws Exception
	 */
	@Path("/query")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String queryCart(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception{
		json = RestUtil.strDecode(json);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
//		BaseVo<CartItemVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CartItemVo>>(){}.getType());
		Cart cart = cartComponent.getCart(userInfo.getId()+"");//TODO 获取member_no
		Criteria criteria = shopDao.createCriteria(Restrictions.eq("merchNo", String.valueOf(userInfo.getId())));
		criteria.add(Restrictions.eq("status", 2));
		List<Shop> shops = criteria.list();
		if(shops!=null&&!shops.isEmpty()){
			Shop shop = shops.get(0);
			if(cart==null)
				cart = new Cart();
			cart.setShopNo(shop.getShopNo());
		}
		ResultVo result = new ResultVo(true,cart);
		return result.toString();
	}
	/**
	 * 查询购物车
	 * @param json
	 * @param header
	 * @return
	 * @throws Exception
	 */
	@Path("/querycount")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String queryCartCount(String json,@Context HttpHeaders header,@Context HttpServletRequest request)throws Exception{
		json = RestUtil.strDecode(json);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String sessionId = request.getSession().getId();
//		BaseVo<CartItemVo> baseVo = GsonUtil.fromJson(json,new TypeToken<BaseVo<CartItemVo>>(){}.getType());
		Map<String,Integer> cart = cartComponent.getCartCount(userInfo.getId()+"");//TODO 获取member_no
		ResultVo result = new ResultVo(true,cart);
		return result.toString();
	}
	
}
