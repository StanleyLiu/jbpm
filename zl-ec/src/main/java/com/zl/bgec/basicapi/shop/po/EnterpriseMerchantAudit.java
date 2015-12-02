/**
 *
 * Project:shop
 * Date:2014-10-13
 * Author: Allen.Z
 * Desc:  企业商户审核表
 *
 */
package com.zl.bgec.basicapi.shop.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@XmlRootElement
@Entity
@Table(name = "tbl_merchant_audit")
public class EnterpriseMerchantAudit {

	public String id;
	public String merchNo; // 商户编号
	public String auditorNo; // 审核人
	public String auditStatus; // 审核状态
	public String auditRemark; // 审核意见
	public Date auditTime; // 审核时间

	public EnterpriseMerchantAudit(){
		auditTime= new Date();
	}
	
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "merch_no", length = 32)
	public String getMerchNo() {
		return merchNo;
	}

	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}

	@Column(name = "auditor_no", length = 32)
	public String getAuditorNo() {
		return auditorNo;
	}

	public void setAuditorNo(String auditorNo) {
		this.auditorNo = auditorNo;
	}

	@Column(name = "auditor_status", length = 5)
	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	@Column(name = "auditor_remark", length = 500)
	public String getAuditRemark() {
		return auditRemark;
	}

	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}

	@Column(name = "auditor_time", length = 20)
	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

}
