package com.zl.bgec.basicapi.commodity.vo;

import java.util.Date;
/**
 * 课程商品
 * @author Stanley
 *
 */
public class CommodityCourseVo {
	private String id;//id
	private String commoNo;//商品编号
	private String commoName;//名称
	private String type;//类型
	private String description;//描述
	private String voiceIntroduction;//语音介绍
	private Double totalFee;//每人总费用
	private Integer totalNum;//限招总人数
	private Date firstStartTime;//首次上课时间
	private Date firstEndTime;//首次下课时间
	private String address;//上课地点
	private String teacher;//选择老师
	private String phone;//联系电话
	private Integer repeatNum;//重复次数
	private String isNeedConfirm;//是否需要确认
	private String isCreateCommunication;//是否创建交流圈
	private String picUrl;//图片
	private String publishState;//上下架状态
	private String sellerNo;//商家编号
	private String isRecommand;//是否推荐
	private String unit;//单位
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommoNo() {
		return commoNo;
	}
	public void setCommoNo(String commoNo) {
		this.commoNo = commoNo;
	}
	public String getCommoName() {
		return commoName;
	}
	public void setCommoName(String commoName) {
		this.commoName = commoName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVoiceIntroduction() {
		return voiceIntroduction;
	}
	public void setVoiceIntroduction(String voiceIntroduction) {
		this.voiceIntroduction = voiceIntroduction;
	}
	public Double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Date getFirstStartTime() {
		return firstStartTime;
	}
	public void setFirstStartTime(Date firstStartTime) {
		this.firstStartTime = firstStartTime;
	}
	public Date getFirstEndTime() {
		return firstEndTime;
	}
	public void setFirstEndTime(Date firstEndTime) {
		this.firstEndTime = firstEndTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getRepeatNum() {
		return repeatNum;
	}
	public void setRepeatNum(Integer repeatNum) {
		this.repeatNum = repeatNum;
	}
	public String getIsNeedConfirm() {
		return isNeedConfirm;
	}
	public void setIsNeedConfirm(String isNeedConfirm) {
		this.isNeedConfirm = isNeedConfirm;
	}
	public String getIsCreateCommunication() {
		return isCreateCommunication;
	}
	public void setIsCreateCommunication(String isCreateCommunication) {
		this.isCreateCommunication = isCreateCommunication;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getPublishState() {
		return publishState;
	}
	public void setPublishState(String publishState) {
		this.publishState = publishState;
	}
	public String getSellerNo() {
		return sellerNo;
	}
	public void setSellerNo(String sellerNo) {
		this.sellerNo = sellerNo;
	}
	public String getIsRecommand() {
		return isRecommand;
	}
	public void setIsRecommand(String isRecommand) {
		this.isRecommand = isRecommand;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
}
