package com.zl.bgec.basicapi.order.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_order_coupons")
public class OrderCoupons implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "coupon_no")
	public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}
	@Column(name = "coupon_value")
	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	@Column(name = "order_no")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	@Column(name = "face_value")
	public Double getLeastAmount() {
		return leastAmount;
	}

	public void setLeastAmount(Double leastAmount) {
		this.leastAmount = leastAmount;
	}
	@Column(name = "coupon_type")
	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	@Column(name = "coupon_name")
	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	@Column(name = "order_coupons_no")
	public String getOrderCouponsNo() {
		return orderCouponsNo;
	}

	public void setOrderCouponsNo(String orderCouponsNo) {
		this.orderCouponsNo = orderCouponsNo;
	}
	@Column(name = "coupon_pic")
	public String getCouponPic() {
		return couponPic;
	}

	public void setCouponPic(String couponPic) {
		this.couponPic = couponPic;
	}

	@Column(name = "promotion_type")
	public String getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}

	
}
