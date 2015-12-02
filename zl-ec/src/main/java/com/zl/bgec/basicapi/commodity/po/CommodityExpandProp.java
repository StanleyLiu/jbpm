package com.zl.bgec.basicapi.commodity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_commodity_expand_prop")
public class CommodityExpandProp {
	
	private String id;
    private String commoNo;// 商品编号
    private String propNo;// 属性编号
    private String optionNo;// 选项值编号
    private String optionName;// 属性值
    private String propName;
    
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "commo_no", nullable = false, length = 32)
	public String getCommoNo() {
		return commoNo;
	}
	public void setCommoNo(String commoNo) {
		this.commoNo = commoNo;
	}
	@Column(name = "prop_no", nullable = false, length = 32)
	public String getPropNo() {
		return propNo;
	}
	public void setPropNo(String propNo) {
		this.propNo = propNo;
	}
	@Column(name = "option_no", nullable = false, length = 32)
	public String getOptionNo() {
		return optionNo;
	}
	public void setOptionNo(String optionNo) {
		this.optionNo = optionNo;
	}
	@Column(name = "option_name", nullable = false, length = 32)
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	@Column(name = "prop_name", nullable = false, length = 32)
	public String getPropName() {
		return propName;
	}
	public void setPropName(String propName) {
		this.propName = propName;
	}
    
    
    
}
