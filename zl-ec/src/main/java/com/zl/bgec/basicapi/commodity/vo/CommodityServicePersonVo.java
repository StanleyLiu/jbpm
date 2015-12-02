package com.zl.bgec.basicapi.commodity.vo;

import java.util.Date;

public class CommodityServicePersonVo {
	private String id;
	private String commoNo;//服务商品编号
	private String personInfo;//服务人员信息
	private Double price;//价格
	private String introduction;//介绍
	private Date createTime;//创建时间
	private String personNo;//人员编号
	private String picUrl;//图片
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
	public String getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(String personInfo) {
		this.personInfo = personInfo;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getPersonNo() {
		return personNo;
	}
	public void setPersonNo(String personNo) {
		this.personNo = personNo;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	
}
