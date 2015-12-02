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
 *商品二级分类和商品属性组的关联
 */
@Entity
@Table(name = "tbl_commo_cat_prop_group")
public class CommodityCatPropGroup implements java.io.Serializable {
    private static final long serialVersionUID = -765217527344695006L;

    private String id;
    private String catNo;
    private String groupNo;

    private CommodityCat commoCategory;
    private CommodityPropGroup commoPropGroup;

    public CommodityCatPropGroup() {
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

    
    @Column(name = "cat_no", length = 32, nullable = false)
    public String getCatNo() {
        return this.catNo;
    }

    
    public void setCatNo(String catNo) {
        this.catNo = catNo;
    }

    
    @Column(name = "group_no", length = 32, nullable = false)
    public String getGroupNo() {
        return this.groupNo;
    }

    
    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    @ManyToOne(targetEntity = CommodityPropGroup.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "group_no", referencedColumnName = "group_no", insertable = false, updatable = false)
    public CommodityPropGroup getCommoPropGroup() {
        return this.commoPropGroup;
    }

    public void setCommoPropGroup(CommodityPropGroup commoPropGroup) {
        this.commoPropGroup = commoPropGroup;
    }

    @ManyToOne(targetEntity = CommodityCat.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_no", referencedColumnName = "cat_no", insertable = false, updatable = false)
    public CommodityCat getCommoCategory() {
        return this.commoCategory;
    }

    
    public void setCommoCategory(CommodityCat commoCategory) {
        this.commoCategory = commoCategory;
    }
}
