package com.zl.bgec.basicapi.promotion.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "tbl_promotion_info")
public class PromotionInfo {
	private String id;
	private String promotionNo;
	private String status;//使用状态
	private String memberNo;
	private String coupNo;
	private Date fetchTime;
	private String shopNo;
	private Date useTime;
	private String couponNumber;
	
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
	@Column(name = "promotion_no")
	public String getPromotionNo() {
		return promotionNo;
	}
	public void setPromotionNo(String promotionNo) {
		this.promotionNo = promotionNo;
	}
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "member_no")
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	@Column(name = "coup_no")
	public String getCoupNo() {
		return coupNo;
	}
	public void setCoupNo(String coupNo) {
		this.coupNo = coupNo;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fetch_time")
	public Date getFetchTime() {
		return fetchTime;
	}
	public void setFetchTime(Date fetchTime) {
		this.fetchTime = fetchTime;
	}
	@Column(name = "shop_no")
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "use_time")
	public Date getUseTime() {
		return useTime;
	}
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	@Column(name = "coupon_number")
	public String getCouponNumber() {
		return couponNumber;
	}
	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
	}
	
	
	
	
	
}
