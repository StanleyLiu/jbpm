package com.zl.bgec.basicapi.commodity.vo;

public class CommodityExpandPropVo {
	
	private String id;
    private String commoNo;// 商品编号
    private String propNo;// 属性编号
    private String optionNo;// 选项值编号
    private String optionName;// 属性值
    private String propName;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommoNo() {
		return commoNo;
	}
	public void setCommoNo(String commoNo) {
		this.commoNo = commoNo;
	}
	public String getPropNo() {
		return propNo;
	}
	public void setPropNo(String propNo) {
		this.propNo = propNo;
	}
	public String getOptionNo() {
		return optionNo;
	}
	public void setOptionNo(String optionNo) {
		this.optionNo = optionNo;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public String getPropName() {
		return propName;
	}
	public void setPropName(String propName) {
		this.propName = propName;
	}
    
    
}
