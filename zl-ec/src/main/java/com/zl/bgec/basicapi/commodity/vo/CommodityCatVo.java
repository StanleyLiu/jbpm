package com.zl.bgec.basicapi.commodity.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.zl.bgec.basicapi.common.PageVo;

/**
 * 商品分类VO zhaoyunlu 
 */
public class CommodityCatVo extends PageVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String catNo;// 分类编号
    private String catName;// 分类名称
    private String catPno;// 分类父类编号
    private String catPath;// 分类的路径
    private int sortNo;// 树形同一级的节点排序编号
    private String description;// 描述
    private byte deleteFlag; // 删除标识 0未删除 1已删除 default 0
    private int level; // 分类等级
    private Date createTime;
    private Date updateTime;
    private String userName; // 最近更新人
    private List<CommodityPropGroupPropVo> propGroupPropVos;
    
    private byte isSpecProp; //是否为规格属性 0非规格属性 1规格属性 default 0
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatNo() {
        return this.catNo;
    }

    public void setCatNo(String catNo) {
        this.catNo = catNo;
    }

    public String getCatName() {
        return this.catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatPno() {
        return this.catPno;
    }

    public void setCatPno(String catPno) {
        this.catPno = catPno;
    }

    public String getCatPath() {
        return this.catPath;
    }

    public void setCatPath(String catPath) {
        this.catPath = catPath;
    }

    public int getSortNo() {
        return this.sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getDeleteFlag() {
        return this.deleteFlag;
    }

    public void setDeleteFlag(byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public List<CommodityPropGroupPropVo> getPropGroupPropVos() {
		return propGroupPropVos;
	}

	public void setPropGroupPropVos(List<CommodityPropGroupPropVo> propGroupPropVos) {
		this.propGroupPropVos = propGroupPropVos;
	}

	public byte getIsSpecProp() {
		return isSpecProp;
	}

	public void setIsSpecProp(byte isSpecProp) {
		this.isSpecProp = isSpecProp;
	}
    
}
