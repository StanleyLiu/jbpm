package com.zl.bgec.basicapi.order.vo;


import java.util.Date;

import com.zl.bgec.basicapi.common.PageVo;

public class OrderLogVo extends PageVo implements java.io.Serializable {

	private static final long serialVersionUID = -5876826994147425264L;
	private String id;
	private String orderNo;
	private String operatorNo;
	private String operationType;
	private String operationRemark;
	private Date operationTime;

	public OrderLogVo() {
	}

	public OrderLogVo(String id, String orderNo, String operatorNo,
			String operationType, String operationRemark, Date operationTime) {
		this.id = id;
		this.orderNo = orderNo;
		this.operatorNo = operatorNo;
		this.operationType = operationType;
		this.operationRemark = operationRemark;
		this.operationTime = operationTime;
	}

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

	public String getOperatorNo() {
		return operatorNo;
	}

	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getOperationRemark() {
		return operationRemark;
	}

	public void setOperationRemark(String operationRemark) {
		this.operationRemark = operationRemark;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}


}
