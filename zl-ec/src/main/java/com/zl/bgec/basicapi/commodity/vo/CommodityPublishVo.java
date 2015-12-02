package com.zl.bgec.basicapi.commodity.vo;

import java.util.Date;
import java.util.List;

public class CommodityPublishVo {
	
	private String id; // 主键id
	private String commoNo; // 商品编号
	private byte publishStatus; // 上下架状态： 0-待上架(默认)，1-上架，2-下架
	private String operatorNo; // 操作员编号
	private Date operateTime; // 操作时间
	private String operatorName;
	
	private List<String> commoNoList;
	
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
	public byte getPublishStatus() {
		return publishStatus;
	}
	public void setPublishStatus(byte publishStatus) {
		this.publishStatus = publishStatus;
	}
	public String getOperatorNo() {
		return operatorNo;
	}
	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public List<String> getCommoNoList() {
		return commoNoList;
	}
	public void setCommoNoList(List<String> commoNoList) {
		this.commoNoList = commoNoList;
	}
	 
	 
	 
}
