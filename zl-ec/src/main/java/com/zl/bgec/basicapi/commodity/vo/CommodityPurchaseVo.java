package com.zl.bgec.basicapi.commodity.vo;

import java.util.Date;

/**
 * 
 * @author Stanley
 *
 */
public class CommodityPurchaseVo {
	private String id;//id
	private String commoNo;//商品编号
	private String commoName;//名称
	private String type;//类型
	private String description;//描述
	private String voiceIntroduction;//语音介绍
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private String model;//型号
	private Double price;//价格
	private Integer leastNum;//最少代购量
	private Date createTime;//创建时间
	private String picUrl;//商品图片
	private String publishState;//上下架状态
	private String sellerNo;//商家编号
	private String isRecommend;//是否推荐
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getLeastNum() {
		return leastNum;
	}
	public void setLeastNum(Integer leastNum) {
		this.leastNum = leastNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public String getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
	
}
