package com.zl.bgec.basicapi.commodity.vo;

import java.util.List;

import com.zl.bgec.basicapi.common.PageVo;

public class CommodityPropVo extends PageVo {

	private String propName;
	
	private String propNo;
	
	private byte isRelatePic;// 是否关联图片 0不关联 1关联 default 0
	
	private byte isSpecProp;
	
	private String description;
	
	/** 输入方式：0文本输入 1下拉单选 2平铺单选 3下拉多选 4平铺多选 default 0 */
    private byte inputType;
    
    private byte deleteFlag;
    
    private byte isShow = 0;//修改时，是否显示，如果属性值没有选择则不显示  0不显示，1显示
	
	private List<CommodityPropValueVo> propValues;
	
	private List<String> commodityCatNos;
	
	private String commodityCatNo;
	
	private List<String> propValueNos;
	
	private String propGroupNo;
	
	private String propGroupName;
	
	private String propNos;

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getPropNo() {
		return propNo;
	}

	public void setPropNo(String propNo) {
		this.propNo = propNo;
	}

	public List<CommodityPropValueVo> getPropValues() {
		return propValues;
	}

	public void setPropValues(List<CommodityPropValueVo> propValues) {
		this.propValues = propValues;
	}

	public byte getIsRelatePic() {
		return isRelatePic;
	}

	public void setIsRelatePic(byte isRelatePic) {
		this.isRelatePic = isRelatePic;
	}

	public byte getInputType() {
		return inputType;
	}

	public void setInputType(byte inputType) {
		this.inputType = inputType;
	}

	public byte getIsShow() {
		return isShow;
	}

	public void setIsShow(byte isShow) {
		this.isShow = isShow;
	}

	public List<String> getCommodityCatNos() {
		return commodityCatNos;
	}

	public void setCommodityCatNos(List<String> commodityCatNos) {
		this.commodityCatNos = commodityCatNos;
	}

	public String getCommodityCatNo() {
		return commodityCatNo;
	}

	public void setCommodityCatNo(String commodityCatNo) {
		this.commodityCatNo = commodityCatNo;
	}

	public byte getIsSpecProp() {
		return isSpecProp;
	}

	public void setIsSpecProp(byte isSpecProp) {
		this.isSpecProp = isSpecProp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public List<String> getPropValueNos() {
		return propValueNos;
	}

	public void setPropValueNos(List<String> propValueNos) {
		this.propValueNos = propValueNos;
	}

	public String getPropGroupNo() {
		return propGroupNo;
	}

	public void setPropGroupNo(String propGroupNo) {
		this.propGroupNo = propGroupNo;
	}

	public String getPropGroupName() {
		return propGroupName;
	}

	public void setPropGroupName(String propGroupName) {
		this.propGroupName = propGroupName;
	}

	public String getPropNos() {
		return propNos;
	}

	public void setPropNos(String propNos) {
		this.propNos = propNos;
	}
	
	
}
