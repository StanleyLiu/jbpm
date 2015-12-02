package com.zl.bgec.basicapi.commodity.vo;

import java.util.Date;

public class CommodityCourtProductVo {
	public String id;//id
	public String prodNo;//货品编号
	public String courtInfo;//添加场地信息
	public String courtIntroduction;//场地介绍
	public Double price;//价格
	public Integer num;//数量
	public String unitTime;//日历
	public Date createTime;//创建时间
	public String commoNo;//商品场地编号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public String getCourtInfo() {
		return courtInfo;
	}
	public void setCourtInfo(String courtInfo) {
		this.courtInfo = courtInfo;
	}
	public String getCourtIntroduction() {
		return courtIntroduction;
	}
	public void setCourtIntroduction(String courtIntroduction) {
		this.courtIntroduction = courtIntroduction;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getUnitTime() {
		return unitTime;
	}
	public void setUnitTime(String unitTime) {
		this.unitTime = unitTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCommoNo() {
		return commoNo;
	}
	public void setCommoNo(String commoNo) {
		this.commoNo = commoNo;
	}
	
	
	
}
