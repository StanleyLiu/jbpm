package com.zl.bgec.basicapi.commodity.vo;

import com.zl.bgec.basicapi.common.PageVo;

public class ProductPropVo extends PageVo {
	 private static final long serialVersionUID = 1L;

	    private String id;
	    /** 商品编号 */
	    private String commoNo;
	    /** 货品编号，当is_spec_prop为规格属性时，该字段有值，为非规格属性时，该字段为null */
	    private String prodNo;
	    /** 属性组编号 */
	    private String groupNo;
	    /** 属性组名称 */
	    private String groupName;
	    /** 属性编号 */
	    private String propNo;
	    /** 属性名称 */
	    private String propName;
	    /** 是否为规格属性，0为非规格属性 1为规格属性 default 0 */
	    private byte isSpecProp;
	    /** 是否关联图片，0未关联 1已关联 default 0 */
	    private byte isRelatedPic;
	    /** 筛选性：0不可筛选 1单值筛选 2多值筛选。 */
	    private byte screenType;
	    /** 选项值编号 */
	    private String optionNo;
	    /** 选项值 */
	    private String value;
	    /** 选项值名称 */
	    private String optionName;

	    public ProductPropVo() {

	    }

	    // 用户HQL查询
	    public ProductPropVo(String id, String commoNo, String prodNo, String groupNo, String groupName, String propNo,
	            String propName, byte isSpecProp, byte isRelatedPic, String optionNo, String value) {
	        super();
	        this.id = id;
	        this.commoNo = commoNo;
	        this.prodNo = prodNo;
	        this.groupNo = groupNo;
	        this.groupName = groupName;
	        this.propNo = propNo;
	        this.propName = propName;
	        this.isSpecProp = isSpecProp;
	        this.isRelatedPic = isRelatedPic;
	        this.optionNo = optionNo;
	        this.value = value;
	    }

	    public String getId() {
	        return this.id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getCommoNo() {
	        return this.commoNo;
	    }

	    public void setCommoNo(String commoNo) {
	        this.commoNo = commoNo;
	    }

	    public String getProdNo() {
	        return this.prodNo;
	    }

	    public void setProdNo(String prodNo) {
	        this.prodNo = prodNo;
	    }

	    public String getGroupNo() {
	        return this.groupNo;
	    }

	    public void setGroupNo(String groupNo) {
	        this.groupNo = groupNo;
	    }

	    public String getGroupName() {
	        return this.groupName;
	    }

	    public void setGroupName(String groupName) {
	        this.groupName = groupName;
	    }

	    public String getPropNo() {
	        return this.propNo;
	    }

	    public void setPropNo(String propNo) {
	        this.propNo = propNo;
	    }

	    public String getPropName() {
	        return this.propName;
	    }

	    public void setPropName(String propName) {
	        this.propName = propName;
	    }

	    public byte getIsSpecProp() {
	        return this.isSpecProp;
	    }

	    public void setIsSpecProp(byte isSpecProp) {
	        this.isSpecProp = isSpecProp;
	    }

	    public byte getIsRelatedPic() {
	        return this.isRelatedPic;
	    }

	    public void setIsRelatedPic(byte isRelatedPic) {
	        this.isRelatedPic = isRelatedPic;
	    }

	    public byte getScreenType() {
	        return this.screenType;
	    }

	    public void setScreenType(byte screenType) {
	        this.screenType = screenType;
	    }

	    public String getOptionNo() {
	        return this.optionNo;
	    }

	    public void setOptionNo(String optionNo) {
	        this.optionNo = optionNo;
	    }

	    public String getValue() {
	        return this.value;
	    }

	    public void setValue(String value) {
	        this.value = value;
	    }

		public String getOptionName() {
			return optionName;
		}

		public void setOptionName(String optionName) {
			this.optionName = optionName;
		}
	    
}
