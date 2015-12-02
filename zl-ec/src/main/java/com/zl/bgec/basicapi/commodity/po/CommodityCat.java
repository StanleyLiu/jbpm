package com.zl.bgec.basicapi.commodity.po;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
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
@Table(name = "tbl_commo_category")
public class CommodityCat implements java.io.Serializable {

    private String id;
    private String catNo;// 分类编号
    private String catName;// 分类名称
    private String catPno;// 分类父类编号
    //private String catPath; 分类的路径：保存编号路径
    private int sortNo;// 树形同一级的节点排序编号
    private String description;// 描述
    private byte deleteFlag; // 删除标识 0未删除 1已删除 default 0
    private Date createTime;
    private Date updateTime;
    // private byte catType;商品分类，用于区别广百分类和金立分类

    // 属性组与商品二级分类的关系
    private Set<CommodityCatPropGroup> commoCatPropGroups = new TreeSet<CommodityCatPropGroup>();

    public CommodityCat() {

    }

    public CommodityCat(String catNo, String catName) {
        super();
        this.catNo = catNo;
        this.catName = catName;
    }

    public CommodityCat(String id, String catNo, String catName, String catPno, String catPath, Integer sortNo,
            String description, byte deleteFlag, Date createTime, Date updateTime,byte catType) {
        super();
        this.id = id;
        this.catNo = catNo;
        this.catName = catName;
        this.catPno = catPno;
//        this.catPath = catPath;
        this.sortNo = sortNo;
        this.description = description;
        this.deleteFlag = deleteFlag;
        this.createTime = createTime;
        this.updateTime = updateTime;
//        this.catType = catType;
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

    
    @Column(name = "cat_no", unique = true, nullable = false, length = 32)
    public String getCatNo() {
        return this.catNo;
    }

    
    public void setCatNo(String catNo) {
        this.catNo = catNo;
    }

    
    @Column(name = "cat_name", nullable = false, length = 100)
    public String getCatName() {
        return this.catName;
    }

    
    public void setCatName(String catName) {
        this.catName = catName;
    }

    
    @Column(name = "cat_pno", nullable = false, length = 32)
    public String getCatPno() {
        return this.catPno;
    }

    
    public void setCatPno(String catPno) {
        this.catPno = catPno;
    }

    
//    @Column(name = "cat_path", nullable = false, length = 32)
//    public String getCatPath() {
//        return this.catPath;
//    }
//
//    
//    public void setCatPath(String catPath) {
//        this.catPath = catPath;
//    }

    
    @Column(name = "sort_no")
    public int getSortNo() {
        return this.sortNo;
    }

    
    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
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

    @OneToMany(targetEntity = CommodityCatPropGroup.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cat_no", referencedColumnName = "cat_no")
    public Set<CommodityCatPropGroup> getCommoCatPropGroups() {
        return this.commoCatPropGroups;
    }

    
    public void setCommoCatPropGroups(Set<CommodityCatPropGroup> commoCatPropGroups) {
        this.commoCatPropGroups = commoCatPropGroups;
    }
    
    
//    @Column(name = "cat_type", length = 4, nullable = false)
//	public byte getCatType() {
//		return catType;
//	}
//    
//	public void setCatType(byte catType) {
//		this.catType = catType;
//	}

}
