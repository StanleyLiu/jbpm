package com.zl.bgec.basicapi.commodity.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_operating_category")
public class CommodityOperCat implements java.io.Serializable {
    private static final long serialVersionUID = 5644876309767337382L;

    private String id;
    private String oprtCatNo; // 运营分类编号
    private String oprtCatName; // 运营分类名称
    private String oprtCatPno; // 父分类编号
    private String oprtCatPath; // 分类路径
    private String oprtCatKey;// 运营分类关键字
    private int sortNo; // 排序号
    private String description;// 描述
    private String seoKey; // SEO关键字
    private String seoTitle;// SEO标题
    private String seoDescription; // SEO描述
    private byte isEnable; // 是否启用 0启用 1不启用 default 0
    private byte isRecommand;// 是否推荐 0不推荐 1推荐 default 0
    private byte deleteFlag; // 删除标识 0未删除 1已删除 default 0
    private Date createTime;
    private Date updateTime;
    private String oprtPic;  //分类图片

    public CommodityOperCat() {

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

   
    @Column(name = "oprt_cat_no", unique = true, nullable = false, length = 32)
    public String getOprtCatNo() {
        return this.oprtCatNo;
    }

   
    public void setOprtCatNo(String oprtCatNo) {
        this.oprtCatNo = oprtCatNo;
    }

   
    @Column(name = "oprt_cat_name", nullable = false, length = 50)
    public String getOprtCatName() {
        return this.oprtCatName;
    }

   
    public void setOprtCatName(String oprtCatName) {
        this.oprtCatName = oprtCatName;
    }

   
    @Column(name = "oprt_cat_pno", nullable = false, length = 32)
    public String getOprtCatPno() {
        return this.oprtCatPno;
    }

   
    public void setOprtCatPno(String oprtCatPno) {
        this.oprtCatPno = oprtCatPno;
    }

   
    @Column(name = "oprt_cat_path", nullable = false, length = 500)
    public String getOprtCatPath() {
        return this.oprtCatPath;
    }

   
    public void setOprtCatPath(String oprtCatPath) {
        this.oprtCatPath = oprtCatPath;
    }

   
    @Column(name = "oprt_cat_key", length = 500)
    public String getOprtCatKey() {
        return this.oprtCatKey;
    }

   
    public void setOprtCatKey(String oprtCatKey) {
        this.oprtCatKey = oprtCatKey;
    }

   
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

   
    @Column(name = "seo_key", length = 200)
    public String getSeoKey() {
        return this.seoKey;
    }

   
    public void setSeoKey(String seoKey) {
        this.seoKey = seoKey;
    }

   
    @Column(name = "seo_title", length = 200)
    public String getSeoTitle() {
        return this.seoTitle;
    }

   
    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

   
    @Column(name = "seo_description", length = 500)
    public String getSeoDescription() {
        return this.seoDescription;
    }

   
    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

   
    @Column(name = "is_enable", nullable = false)
    public byte getIsEnable() {
        return this.isEnable;
    }

   
    public void setIsEnable(byte isEnable) {
        this.isEnable = isEnable;
    }

   
    @Column(name = "is_recommand", nullable = false)
    public byte getIsRecommand() {
        return this.isRecommand;
    }

   
    public void setIsRecommand(byte isRecommand) {
        this.isRecommand = isRecommand;
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
    
    @Column(name = "oprt_pic", length = 500)		
    public String getOprtPic() {
		return this.oprtPic;
	}
    
	public void setOprtPic(String oprtPic) {
		this.oprtPic = oprtPic;
	}
}
