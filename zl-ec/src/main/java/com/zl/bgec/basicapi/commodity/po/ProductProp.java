package com.zl.bgec.basicapi.commodity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_product_prop")
public class ProductProp implements java.io.Serializable{

    private String id;
    private String commoNo;// 商品编号
    private String prodNo;// 货品编号，当为规格属性时，该字段有值，为非规格属性时，该字段为null
    private String propNo;// 属性编号
    private String optionNo;// 选项值编号
    private String optionName;// 属性值
    private String propName;

    public ProductProp() {

    }

    
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

    
    @Column(name = "commo_no", nullable = false, length = 32)
    public String getCommoNo() {
        return this.commoNo;
    }

    
    public void setCommoNo(String commoNo) {
        this.commoNo = commoNo;
    }

    
    @Column(name = "prod_no", nullable = false, length = 32)
    public String getProdNo() {
        return this.prodNo;
    }

    
    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    
    @Column(name = "prop_no", nullable = false, length = 32)
    public String getPropNo() {
        return this.propNo;
    }

    
    public void setPropNo(String propNo) {
        this.propNo = propNo;
    }

    
    @Column(name = "option_no", length = 32)
    public String getOptionNo() {
        return this.optionNo;
    }

    
    public void setOptionNo(String optionNo) {
        this.optionNo = optionNo;
    }

    
    @Column(name = "option_name", nullable = false, length = 200)
    public String getOptionName() {
        return this.optionName;
    }
    
    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    @Column(name = "prop_name")
	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}
    
    
    
}
