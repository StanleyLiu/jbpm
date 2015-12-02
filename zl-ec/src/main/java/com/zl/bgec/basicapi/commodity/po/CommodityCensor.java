package com.zl.bgec.basicapi.commodity.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 
 * @author zhaoyunlu
 *	商品审核表
 */
@Entity
@Table(name = "tbl_commo_censor")
public class CommodityCensor implements java.io.Serializable{

    private String id; // 主键id
    private String commoNo; // 商品编号
    private byte censorStatus; // 审核状态：0-暂存状态 ，1-待审核，2-审核通过 3-审核未通过
    private String reason;// 审核未通过的原因
    private String operatorNo; // 操作员编号
    private Date operateTime; // 操作时间
    private String operatorName;
   
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 32)
    public String getId() {
        return this.id;
    }

   
    public void setId(String id) {
        this.id = id;
    }

   
    @Column(name = "commo_no", unique = true, nullable = false, length = 32)
    public String getCommoNo() {
        return this.commoNo;
    }

   
    public void setCommoNo(String commoNo) {
        this.commoNo = commoNo;
    }

   
    @Column(name = "censor_status")
    public byte getCensorStatus() {
        return this.censorStatus;
    }

   
    public void setCensorStatus(byte censorStatus) {
        this.censorStatus = censorStatus;
    }

   
    @Column(name = "reason")
    public String getReason() {
        return this.reason;
    }

   
    public void setReason(String reason) {
        this.reason = reason;
    }

   
    @Column(name = "operator_no")
    public String getOperatorNo() {
        return this.operatorNo;
    }

   
    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

   
    @Column(name = "operate_time")
    public Date getOperateTime() {
        return this.operateTime;
    }

   
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    @Column(name = "operator_name")
	public String getOperatorName() {
		return operatorName;
	}


	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
    
}
