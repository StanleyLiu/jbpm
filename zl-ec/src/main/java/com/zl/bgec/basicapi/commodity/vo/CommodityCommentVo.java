package com.zl.bgec.basicapi.commodity.vo;

import java.util.Date;

import com.zl.bgec.basicapi.common.PageVo;

public class CommodityCommentVo extends PageVo{
	
	private String id;
	private String commodityNo;//商品编号
	private String productNo;//货品编号
	private String title;//标题
	private String content;//评价内容
	private Date createTime;//评价时间
	private String parentNo;//
	private byte deleteFlag;//
	
	private Date startTime;//
	private String shopNo;//店铺编号
	private Date endTime;//
	private Double serviceGrade;// 服务评分
	private Double deliveryGrade;//发货评分
	private String commodityQualityGrade;//质量评分
	private String memberNo;//
	private String memberName;//
	
	private String commentNo;//
	private String replyContent;//回复内容
	private Date replyTime;//回复时间
	private String replyMember;//回复人
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getParentNo() {
		return parentNo;
	}
	public void setParentNo(String parentNo) {
		this.parentNo = parentNo;
	}
	public byte getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(String commentNo) {
		this.commentNo = commentNo;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	public String getReplyMember() {
		return replyMember;
	}
	public void setReplyMember(String replyMember) {
		this.replyMember = replyMember;
	}
	public Double getServiceGrade() {
		return serviceGrade;
	}
	public void setServiceGrade(Double serviceGrade) {
		this.serviceGrade = serviceGrade;
	}
	public Double getDeliveryGrade() {
		return deliveryGrade;
	}
	public void setDeliveryGrade(Double deliveryGrade) {
		this.deliveryGrade = deliveryGrade;
	}
	public String getCommodityQualityGrade() {
		return commodityQualityGrade;
	}
	public void setCommodityQualityGrade(String commodityQualityGrade) {
		this.commodityQualityGrade = commodityQualityGrade;
	}
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	
	
}
