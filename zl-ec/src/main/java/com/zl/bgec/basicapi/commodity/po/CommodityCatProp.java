package com.zl.bgec.basicapi.commodity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 
 * @author zhaoyunlu
 *商品二级分类和商品属性的关联
 */
@Entity
@Table(name = "tbl_commo_cat_prop")
public class CommodityCatProp implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String catNo;
    private String propNo;

    
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

    
    @Column(name = "cat_no", nullable = false, length = 32)
    public String getCatNo() {
        return this.catNo;
    }

    
    public void setCatNo(String catNo) {
        this.catNo = catNo;
    }

    
    @Column(name = "prop_no", nullable = false, length = 32)
    public String getPropNo() {
        return this.propNo;
    }

    
    public void setPropNo(String propNo) {
        this.propNo = propNo;
    }

}
