package com.zl.bgec.basicapi.order.po;


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
 */
@Entity
@Table(name = "tbl_order_return")
public class OrderReturn implements java.io.Serializable {

	private static final long serialVersionUID = -1l;
	private String id;
	private String orderNo;
	private String returnReason;//退货原因
	private Double returnAmount;//退款金额
	private Date returnTime;//申请退货时间
	private String returnRemark;//退货说明
	private String returnPic;//图片
	private String auditReason;//审核不通过原因
	private String auditRemark;//审核说明
	private String checkReason;//验收不通过原因
	private String checkRemark;//验收说明
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "order_no")
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	@Column(name = "return_reason")
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	
	@Column(name = "return_amount")
	public Double getReturnAmount() {
		return returnAmount;
	}
	public void setReturnAmount(Double returnAmount) {
		this.returnAmount = returnAmount;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "return_time")
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	@Column(name = "return_remark")
	public String getReturnRemark() {
		return returnRemark;
	}
	public void setReturnRemark(String returnRemark) {
		this.returnRemark = returnRemark;
	}
	@Column(name = "return_pic")
	public String getReturnPic() {
		return returnPic;
	}
	public void setReturnPic(String returnPic) {
		this.returnPic = returnPic;
	}
	@Column(name = "audit_reason")
	public String getAuditReason() {
		return auditReason;
	}
	public void setAuditReason(String auditReason) {
		this.auditReason = auditReason;
	}
	@Column(name = "audit_remark")
	public String getAuditRemark() {
		return auditRemark;
	}
	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}
	@Column(name = "check_reason")
	public String getCheckReason() {
		return checkReason;
	}
	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}
	@Column(name = "check_remark")
	public String getCheckRemark() {
		return checkRemark;
	}
	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}

	

}
