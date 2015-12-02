package com.zl.bgec.basicapi.order.vo;
public class OrderCoupons {
	private String id;//id
	private String couponNo;//优惠券编号
	private Double discountAmount;//优惠金额
	private String orderNo;//订单编号
	private Double leastAmount;//最低消费金额
	private String couponType;//优惠券类型
	private String couponName;//优惠券名称
	private String orderCouponsNo;//编号
	private String couponPic;//优惠券图片
	private String promotionType;//优惠类型，1店铺优惠，2平台优惠
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}
	public Double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Double getLeastAmount() {
		return leastAmount;
	}
	public void setLeastAmount(Double leastAmount) {
		this.leastAmount = leastAmount;
	}
	public String getCouponType() {
		return couponType;
	}
	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getOrderCouponsNo() {
		return orderCouponsNo;
	}
	public void setOrderCouponsNo(String orderCouponsNo) {
		this.orderCouponsNo = orderCouponsNo;
	}
	public String getCouponPic() {
		return couponPic;
	}
	public void setCouponPic(String couponPic) {
		this.couponPic = couponPic;
	}
	public String getPromotionType() {
		return promotionType;
	}
	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}
	
}