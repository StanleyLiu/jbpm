package com.zl.bgec.basicapi.order.vo;

public class ReturnOrderRecord{
	private String originalOrderNo;
	private Double refundAmount;
	private String refundmark;
	public String getOriginalOrderNo() {
		return originalOrderNo;
	}
	public void setOriginalOrderNo(String originalOrderNo) {
		this.originalOrderNo = originalOrderNo;
	}
	public Double getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getRefundmark() {
		return refundmark;
	}
	public void setRefundmark(String refundmark) {
		this.refundmark = refundmark;
	}
}
