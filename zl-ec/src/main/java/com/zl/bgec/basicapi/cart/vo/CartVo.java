package com.zl.bgec.basicapi.cart.vo;

import java.util.Date;
/**
 * 购物车Vo
 * @author Stanley
 *
 */
public class CartVo {
	private String id;//id
	private String cartJson;//json
	private String cartNo;//购物车编号
	private Date createTime;//创建时间
	private Date updateTime;//修改时间
	private String memberNo;//会员编号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCartJson() {
		return cartJson;
	}
	public void setCartJson(String cartJson) {
		this.cartJson = cartJson;
	}
	public String getCartNo() {
		return cartNo;
	}
	public void setCartNo(String cartNo) {
		this.cartNo = cartNo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	
	
}
