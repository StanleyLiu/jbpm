/**
 *
 * Project:shop
 * Date:2014-10-13
 * Author: Allen.Z
 * Desc: 个人商户审核表
 *
 */
package com.zl.bgec.basicapi.shop.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@XmlRootElement
@Entity
@Table(name = "tbl_person_audit")
public class PersonMerchantAudit {

	public String id;
	public String perNo; //个人商户编号
	public String auditorNo; // 审核人
	public String auditStatus; // 审核状态
	public String auditRemark; // 审核意见
	public String auditTime; //审核时间

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
	
	@Column(name = "per_no", length = 32)
	public String getPerNo() {
		return perNo;
	}

	public void setPerNo(String perNo) {
		this.perNo = perNo;
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
	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

}
