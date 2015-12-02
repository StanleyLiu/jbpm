package com.zl.bgec.basicapi.commodity.vo;

import java.util.List;

import com.zl.bgec.basicapi.common.PageVo;

public class CommodityPropGroupVo extends PageVo {
	
	private String groupName;
	
	private String groupNo;
	
	private List<String> catNos;
	
	private List<String> propNos;
	
	private List<CommodityPropVo> props;
	
	private String catNo;

	private byte deleteFlag;
	
	private byte removeBindFlag;//主要用于判断是否删除和商品的关联，0删除，1不删除
    
	private byte isSpecGroup;
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public List<CommodityPropVo> getProps() {
		return props;
	}

	public void setProps(List<CommodityPropVo> props) {
		this.props = props;
	}

	public List<String> getCatNos() {
		return catNos;
	}

	public void setCatNos(List<String> catNos) {
		this.catNos = catNos;
	}

	public List<String> getPropNos() {
		return propNos;
	}

	public void setPropNos(List<String> propNos) {
		this.propNos = propNos;
	}

	public String getCatNo() {
		return catNo;
	}

	public void setCatNo(String catNo) {
		this.catNo = catNo;
	}

	public byte getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public byte getRemoveBindFlag() {
		return removeBindFlag;
	}

	public void setRemoveBindFlag(byte removeBindFlag) {
		this.removeBindFlag = removeBindFlag;
	}

	public byte getIsSpecGroup() {
		return isSpecGroup;
	}

	public void setIsSpecGroup(byte isSpecGroup) {
		this.isSpecGroup = isSpecGroup;
	}
	
	
}
