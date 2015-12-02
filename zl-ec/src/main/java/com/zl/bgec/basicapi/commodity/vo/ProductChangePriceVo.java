package com.zl.bgec.basicapi.commodity.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.zl.bgec.basicapi.common.PageVo;


public class ProductChangePriceVo extends PageVo{
	
	/** 调价表id **/ 
	private String id;
	/** 调价编号  **/ 
	private String changeNo; 
	/** 调价操作人编号 **/ 
	private String operatorName; 
	/** 调价操作时间  **/ 
	private Date operatorTime; 
	/** 调价表审核状态  "-1"无意义  "1"表示未审核 ，"2"表示已审核 **/ 
	private byte status=1;
	/** 调价表审核意见 **/ 
	private String conserDesc;
	/** 调价描述 **/ 
	private String describe;
	/** 审核人姓名 **/
	private String conserName;
	/** 审核人编号 **/
	private String conserNo;
	/** 审核时间 **/
	private Date conserTime;
	/** 调价人编号 **/
	private String operatorNo;
	
	private Date operatorTimeStart;
	
	private Date operatorTimeEnd;
	
	private Date conserTimeStart;
	
	private Date conserTimeEnd;
	
	/** 调价项 **/
	private List<ProductChangePriceItemVo> itemVos = new ArrayList<ProductChangePriceItemVo>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChangeNo() {
		return changeNo;
	}

	public void setChangeNo(String changeNo) {
		this.changeNo = changeNo;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Date getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getConserDesc() {
		return conserDesc;
	}

	public void setConserDesc(String conserDesc) {
		this.conserDesc = conserDesc;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getConserName() {
		return conserName;
	}

	public void setConserName(String conserName) {
		this.conserName = conserName;
	}

	public String getConserNo() {
		return conserNo;
	}

	public void setConserNo(String conserNo) {
		this.conserNo = conserNo;
	}

	public Date getConserTime() {
		return conserTime;
	}

	public void setConserTime(Date conserTime) {
		this.conserTime = conserTime;
	}

	public String getOperatorNo() {
		return operatorNo;
	}

	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}
	
	public List<ProductChangePriceItemVo> getItemVos() {
		return itemVos;
	}

	public void setItemVos(List<ProductChangePriceItemVo> itemVos) {
		this.itemVos = itemVos;
	}

	public Date getOperatorTimeStart() {
		return operatorTimeStart;
	}

	public void setOperatorTimeStart(Date operatorTimeStart) {
		this.operatorTimeStart = operatorTimeStart;
	}

	public Date getOperatorTimeEnd() {
		return operatorTimeEnd;
	}

	public void setOperatorTimeEnd(Date operatorTimeEnd) {
		this.operatorTimeEnd = operatorTimeEnd;
	}

	public Date getConserTimeStart() {
		return conserTimeStart;
	}

	public void setConserTimeStart(Date conserTimeStart) {
		this.conserTimeStart = conserTimeStart;
	}

	public Date getConserTimeEnd() {
		return conserTimeEnd;
	}

	public void setConserTimeEnd(Date conserTimeEnd) {
		this.conserTimeEnd = conserTimeEnd;
	}
	
	
	
}
