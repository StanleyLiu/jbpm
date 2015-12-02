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

@Entity
@Table(name = "tbl_commo_prop_group_prop")
public class CommodityPropGroupProp implements java.io.Serializable {
    private static final long serialVersionUID = -5002339528158838482L;

    private String id;
    private String groupNo;
    private String propNo;

    private CommodityPropGroup commoPropGroup;
    private CommodityProp commoProp;

    public CommodityPropGroupProp() {

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
    public CommodityProp getCommoProp() {
        return this.commoProp;
    }

    
    public void setCommoProp(CommodityProp commoProp) {
        this.commoProp = commoProp;
    }

    @ManyToOne(targetEntity = CommodityPropGroup.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_no", referencedColumnName = "group_no", insertable = false, updatable = false)
    public CommodityPropGroup getCommoPropGroup() {
        return this.commoPropGroup;
    }

    
    public void setCommoPropGroup(CommodityPropGroup commoPropGroup) {
        this.commoPropGroup = commoPropGroup;
    }

}
