package com.zl.bgec.basicapi.commodity.po;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_commo_prop_group")
public class CommodityPropGroup implements java.io.Serializable {

    private static final long serialVersionUID = -1266733326818710364L;
    private String id;
    private String groupNo;// 属性组编号
    private String groupName;// 属性组名称
    private byte isSpecGroup;// 是否为规格 0非规格，1为规格 default 0
    private String description;// 描述
    private byte deleteFlag;// 删除标识 0未删除 1已删除 default 0
    private Date createTime;// 创建时间
    private Date updateTime;// 更新时间

    // 商品属性
    private Set<CommodityPropGroupProp> commoPropGroupProps = new TreeSet<CommodityPropGroupProp>();

    public CommodityPropGroup() {

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

    
    @Column(name = "group_no", unique = true, nullable = false, length = 32)
    public String getGroupNo() {
        return this.groupNo;
    }

    
    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    
    @Column(name = "group_name", nullable = false, length = 50)
    public String getGroupName() {
        return this.groupName;
    }

    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    
    @Column(name = "is_spec_group", nullable = false)
    public byte getIsSpecGroup() {
        return this.isSpecGroup;
    }

    
    public void setIsSpecGroup(byte isSpecGroup) {
        this.isSpecGroup = isSpecGroup;
    }

    
    @Column(name = "description", length = 500)
    public String getDescription() {
        return this.description;
    }

    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name = "delete_flag", nullable = false)
    public byte getDeleteFlag() {
        return this.deleteFlag;
    }

    
    public void setDeleteFlag(byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", length = 19, nullable = false)
    public Date getCreateTime() {
        return this.createTime;
    }

    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", length = 19, nullable = false)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    
    @OneToMany(targetEntity = CommodityPropGroupProp.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_no", referencedColumnName = "group_no")
    public Set<CommodityPropGroupProp> getCommoPropGroupProps() {
        return this.commoPropGroupProps;
    }

    
    public void setCommoPropGroupProps(Set<CommodityPropGroupProp> commoPropGroupProps) {
        this.commoPropGroupProps = commoPropGroupProps;
    }

}
