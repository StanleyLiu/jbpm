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
@Table(name = "tbl_commo_brand")
public class CommodityBrand implements java.io.Serializable {

    private String id;
    private String brandNo;// 品牌编号
    private String brandName;// 品牌中文名称
    private String brandEnName;// 品牌英文名称
    private String brandTitle;// 品牌标语
    private String brandKey;// 品牌关键字
    private String description;// 品牌描述
    private String website;// 品牌网站
    private byte isDisplay;// 是否显示，0显示 1不显示 default 0
    private String logoBigPic;// 品牌logo大图
    private String logoMidPic;// 品牌logo中图
    private String logoSmlPic;// 品牌logo小图
    private String seoKey;// 搜索关键字
    private String seoTitle;// 搜索标题
    private String seoDescription;// 搜索描述
    private byte deleteFlag; // 删除标识 0未删除 1已删除 default 0
    private Date createTime;
    private Date updateTime;
    
    public CommodityBrand() {

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

    
    @Column(name = "brand_no", unique = true, nullable = false, length = 32)
    public String getBrandNo() {
        return this.brandNo;
    }

    
    public void setBrandNo(String brandNo) {
        this.brandNo = brandNo;
    }

    
    @Column(name = "brand_name", nullable = false, length = 100)
    public String getBrandName() {
        return this.brandName;
    }

    
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    
    @Column(name = "brand_en_name", nullable = false, length = 100)
    public String getBrandEnName() {
        return this.brandEnName;
    }

    
    public void setBrandEnName(String brandEnName) {
        this.brandEnName = brandEnName;
    }

    
    @Column(name = "brand_key", length = 200)
    public String getBrandKey() {
        return this.brandKey;
    }

    
    public void setBrandKey(String brandKey) {
        this.brandKey = brandKey;
    }

    
    @Column(name = "brand_title", length = 200)
    public String getBrandTitle() {
        return this.brandTitle;
    }

    
    public void setBrandTitle(String brandTitle) {
        this.brandTitle = brandTitle;
    }

    
    @Column(name = "description", length = 500)
    public String getDescription() {
        return this.description;
    }

    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name = "website", length = 500)
    public String getWebsite() {
        return this.website;
    }

    
    public void setWebsite(String website) {
        this.website = website;
    }

    
    @Column(name = "is_display", nullable = false, length = 500)
    public byte getIsDisplay() {
        return this.isDisplay;
    }

    
    public void setIsDisplay(byte isDisplay) {
        this.isDisplay = isDisplay;
    }

    
    @Column(name = "logo_big_pic", length = 500)
    public String getLogoBigPic() {
        return this.logoBigPic;
    }

    
    public void setLogoBigPic(String logoBigPic) {
        this.logoBigPic = logoBigPic;
    }

    
    @Column(name = "logo_mid_pic", length = 500)
    public String getLogoMidPic() {
        return this.logoMidPic;
    }

    
    public void setLogoMidPic(String logoMidPic) {
        this.logoMidPic = logoMidPic;
    }

    
    @Column(name = "logo_sml_pic", length = 500)
    public String getLogoSmlPic() {
        return this.logoSmlPic;
    }

    
    public void setLogoSmlPic(String logoSmlPic) {
        this.logoSmlPic = logoSmlPic;
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

}
