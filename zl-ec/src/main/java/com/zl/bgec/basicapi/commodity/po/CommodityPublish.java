package com.zl.bgec.basicapi.commodity.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_commo_publish")
public class CommodityPublish implements java.io.Serializable {

	private String id; // 主键id
	private String commoNo; // 商品编号
	private byte publishStatus; // 上下架状态： 0-待上架(默认)，1-上架，2-下架
	// private String channelNo; // 多渠道编号
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

	@Column(name = "publish_status")
	public byte getPublishStatus() {
		return this.publishStatus;
	}

	public void setPublishStatus(byte publishStatus) {
		this.publishStatus = publishStatus;
	}

	// @Column(name = "channel_no")
	// public String getChannelNo() {
	// return this.channelNo;
	// }
	//
	//
	// public void setChannelNo(String channelNo) {
	// this.channelNo = channelNo;
	// }

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
