package com.zl.bgec.basicapi.cart.service;

import java.util.List;
import java.util.Map;

import com.zl.bgec.basicapi.cart.po.Cart;
import com.zl.bgec.basicapi.cart.vo.CartItemVo;

/**
 * 
 * @author Stanley
 *
 */
public interface ICartComponent {
	/**
	 * 加入购物车
	 * @param cartItemVo
	 * @throws Exception
	 */
	public void addItem(CartItemVo cartItemVo) throws Exception;
	/**
	 * 修改购物车数量
	 * @param cartItemVo
	 * @throws Exception
	 */
	public void updateItem(CartItemVo cartItemVo) throws Exception;
	/**
	 * 删除条目
	 * @param cartItemVo
	 * @throws Exception
	 */
	public void deleteItem(List<CartItemVo> cartItemVo,String memberNo) throws Exception;
	/**
	 * 清空购物车
	 * @param cartItemVo
	 * @throws Exception
	 */
	public void deleteCart(CartItemVo cartItemVo) throws Exception;
	/**
	 * 查询购物车
	 * @param memberNo
	 * @throws Exception
	 */
	public Cart getCart(String memberNo)throws Exception;
	/**
	 * 查询购物车
	 * @param memberNo
	 * @throws Exception
	 */
	public Map<String,Integer> getCartCount(String memberNo)throws Exception;
}
