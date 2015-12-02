package com.zl.bgec.basicapi.commodity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 
 * @author zhaoyunlu
 *商品三级分类和属性组和属性的关联
 */
@Entity
@Table(name = "tbl_commo_cat_group_prop")
public class CommodityCatGroupProp {

    private String id;
    private String catNo;
    private String groupNo;
    private String propNo;
    
    private CommodityProp prop;
    
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

    
    @Column(name = "group_no", nullable = false, length = 32)
    public String getGroupNo() {
        return this.groupNo;
    }

    
    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    
    @Column(name = "prop_no", nullable = false, length = 32)
    public String getPropNo() {
        return this.propNo;
    }

    
    public void setPropNo(String propNo) {
        this.propNo = propNo;
    }

    @ManyToOne(targetEntity = CommodityProp.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "prop_no", referencedColumnName = "prop_no", insertable = false, updatable = false)
	public CommodityProp getProp() {
		return prop;
	}
	public void setProp(CommodityProp prop) {
		this.prop = prop;
	}
    
    
}
