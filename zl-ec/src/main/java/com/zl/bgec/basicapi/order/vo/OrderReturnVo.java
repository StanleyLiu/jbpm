package com.zl.bgec.basicapi.order.vo;

import java.util.Date;

public class OrderReturnVo {
	private String id;
	private String orderNo;
	private String returnReason;//退货原因
	private Double returnAmount;//退款金额
	private Date returnTime;//申请退货时间
	private String returnRemark;//退货说明
	private String renturnPic;//图片
	private String auditReason;//审核不通过原因
	private String auditRemark;//审核说明
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	public Double getReturnAmount() {
		return returnAmount;
	}
	public void setReturnAmount(Double returnAmount) {
		this.returnAmount = returnAmount;
	}
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	public String getReturnRemark() {
		return returnRemark;
	}
	public void setReturnRemark(String returnRemark) {
		this.returnRemark = returnRemark;
	}
	public String getRenturnPic() {
		return renturnPic;
	}
	public void setRenturnPic(String renturnPic) {
		this.renturnPic = renturnPic;
	}
	public String getAuditReason() {
		return auditReason;
	}
	public void setAuditReason(String auditReason) {
		this.auditReason = auditReason;
	}
	public String getAuditRemark() {
		return auditRemark;
	}
	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}
	
	
}
