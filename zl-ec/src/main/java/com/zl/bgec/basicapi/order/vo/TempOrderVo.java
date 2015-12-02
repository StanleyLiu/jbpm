package com.zl.bgec.basicapi.order.vo;

import java.util.List;
import java.util.Map;

import com.zl.bgec.basicapi.cart.po.CartItem;
import com.zl.bgec.basicapi.order.po.OrderAddress;
import com.zl.bgec.basicapi.order.po.OrderItem;

public class TempOrderVo {
	private String shopNo;
	private String shopName;
	private List<CartItem> cartItems;
	private List<Map<String,Object>> promotionInfos;
	private double priceTotal;
	private double deliveryFee;
	private double feeTotal;
	private String promotionNo;//优惠券
	private Double discountAmount;//优惠金额
	private List<OrderItem> orderItems;//订单条目
	private OrderAddress orderAddress;//订单地址
	private String buyerMessage;
	private String deliveryType;
	private String shopAddress;
	private String shopPhone;
	private String leaveStock;
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	public List<Map<String,Object>> getPromotionInfos() {
		return promotionInfos;
	}
	public void setPromotionInfos(List<Map<String,Object>> promotionInfos) {
		this.promotionInfos = promotionInfos;
	}
	public double getPriceTotal() {
		return priceTotal;
	}
	public void setPriceTotal(double priceTotal) {
		this.priceTotal = priceTotal;
	}
	public double getDeliveryFee() {
		return deliveryFee;
	}
	public void setDeliveryFee(double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	public double getFeeTotal() {
		return feeTotal;
	}
	public void setFeeTotal(double feeTotal) {
		this.feeTotal = feeTotal;
	}
	public String getPromotionNo() {
		return promotionNo;
	}
	public void setPromotionNo(String promotionNo) {
		this.promotionNo = promotionNo;
	}
	public Double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public OrderAddress getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(OrderAddress orderAddress) {
		this.orderAddress = orderAddress;
	}
	public String getBuyerMessage() {
		return buyerMessage;
	}
	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public String getShopPhone() {
		return shopPhone;
	}
	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}
	public String getLeaveStock() {
		return leaveStock;
	}
	public void setLeaveStock(String leaveStock) {
		this.leaveStock = leaveStock;
	}
}
