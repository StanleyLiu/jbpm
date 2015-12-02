package com.zl.bgec.basicapi.cart.vo;

import java.util.Date;

/**
 * 购物车条目Vo
 * @author Stanley
 *
 */
public class CartItemVo {
	private String id;
	private String cartNo;//购物车编号
	private String itemNo;//购物车条目编号
	private String prodNo;//货品编号
	private Integer quantity;//数量
	private String shopNo;//店铺编号
	private String shopName;//店铺名称
	private String prodName;//商品名称
	private Double prodPrice;//价格
	private Double priceTotal;//总价格
	private String prodPic;//货品图片
	private Date createTime;//创建时间
	private String memberNo;//会员编号
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCartNo() {
		return cartNo;
	}
	public void setCartNo(String cartNo) {
		this.cartNo = cartNo;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	public Double getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(Double prodPrice) {
		this.prodPrice = prodPrice;
	}
	public Double getPriceTotal() {
		return priceTotal;
	}
	public void setPriceTotal(Double priceTotal) {
		this.priceTotal = priceTotal;
	}
	public String getProdPic() {
		return prodPic;
	}
	public void setProdPic(String prodPic) {
		this.prodPic = prodPic;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	
}
