package com.zl.bgec.basicapi.commodity.vo;

import com.zl.bgec.basicapi.common.PageVo;

public class CommodityPropValueVo extends PageVo {

	private String optionName;
	
	private String optionNo;
	
	private String checked;
	
	private String optionPic;//属性值的图片

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getOptionNo() {
		return optionNo;
	}

	public void setOptionNo(String optionNo) {
		this.optionNo = optionNo;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getOptionPic() {
		return optionPic;
	}

	public void setOptionPic(String optionPic) {
		this.optionPic = optionPic;
	}
	
}
