package com.zl.bgec.basicapi.commodity.po;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 商品评论
 * @author Administrator
 *
 */
@Entity
@Table(name = "tbl_commodity_comment")
public class CommodityComment {
	
	private String id;
	private String commodityNo;//商品编号
	private String productNo;//货品编号
	private String title;//标题
	private String content;//评价内容
	private Date createTime;//评价时间
	private String parentNo;
	private byte deleteFlag;
	private Double serviceGrade;// 服务评分
	private Double deliveryGrade;//发货评分
	private String commodityQualityGrade;//质量评分
	private String memberNo;//评价人编号
	private String memberName;//评价人
	private String shopNo;//店铺编号
	private String replyContent;//回复内容
	private Date replyTime;//回复时间
	private String replyMember;//回复人
	
	@Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "commodity_no")
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
	@Column(name = "product_no")
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "parent_id")
	public String getParentNo() {
		return parentNo;
	}
	public void setParentNo(String parentNo) {
		this.parentNo = parentNo;
	}
	@Column(name = "delete_flag")
	public byte getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	@Column(name = "member_no")
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	@Column(name = "member_name")
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	@Column(name = "reply_content")
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reply_time")
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	@Column(name = "reply_member")
	public String getReplyMember() {
		return replyMember;
	}
	public void setReplyMember(String replyMember) {
		this.replyMember = replyMember;
	}
	@Column(name = "service_grade")
	public Double getServiceGrade() {
		return serviceGrade;
	}
	public void setServiceGrade(Double serviceGrade) {
		this.serviceGrade = serviceGrade;
	}
	@Column(name = "delivery_grade")
	public Double getDeliveryGrade() {
		return deliveryGrade;
	}
	public void setDeliveryGrade(Double deliveryGrade) {
		this.deliveryGrade = deliveryGrade;
	}
	@Column(name = "commodity_quality_grade")
	public String getCommodityQualityGrade() {
		return commodityQualityGrade;
	}
	public void setCommodityQualityGrade(String commodityQualityGrade) {
		this.commodityQualityGrade = commodityQualityGrade;
	}
	@Column(name = "shop_no")
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	
	
	
}
