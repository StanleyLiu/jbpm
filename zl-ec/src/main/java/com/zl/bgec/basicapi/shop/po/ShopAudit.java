/**
 *
 * Project:shop
 * Date:2014-10-13
 * Author: Allen.Z
 * Desc: 
 *
 */
package com.zl.bgec.basicapi.shop.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@XmlRootElement
@Entity
@Table(name = "tbl_shop_audit")
public class ShopAudit {

	public String id;			//主键ID
	public String shopNo;		//店铺编号
	public String auditorNo;	//审核人
	public String auditStatus;	//审核状态
	public String auditRemark;	//审核意见
	public Date auditTime;	//审核时间
	
	public ShopAudit(){
		auditTime = new Date();
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
	@Column(name = "shop_no", length = 32)
	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	@Column(name = "auditor_no", length = 32)
	public String getAuditorNo() {
		return auditorNo;
	}

	public void setAuditorNo(String auditorNo) {
		this.auditorNo = auditorNo;
	}

	@Column(name = "audit_status", length = 32)
	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	@Column(name = "audit_remark", length = 32)
	public String getAuditRemark() {
		return auditRemark;
	}

	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "audit_time")
	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}


}
