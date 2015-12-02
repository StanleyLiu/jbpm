package com.zl.bgec.basicapi.commodity.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.zl.bgec.basicapi.common.PageVo;
/**
 * 
 * @author zhaoyunlu
 * 商品品牌vo对象
 */
public class CommodityBrandVo extends PageVo implements Serializable{
	
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
	    
	    private byte removeBindFlag;
	    
	    private List<String> brandNos;
	    
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getBrandNo() {
			return brandNo;
		}
		public void setBrandNo(String brandNo) {
			this.brandNo = brandNo;
		}
		public String getBrandName() {
			return brandName;
		}
		public void setBrandName(String brandName) {
			this.brandName = brandName;
		}
		public String getBrandEnName() {
			return brandEnName;
		}
		public void setBrandEnName(String brandEnName) {
			this.brandEnName = brandEnName;
		}
		public String getBrandTitle() {
			return brandTitle;
		}
		public void setBrandTitle(String brandTitle) {
			this.brandTitle = brandTitle;
		}
		public String getBrandKey() {
			return brandKey;
		}
		public void setBrandKey(String brandKey) {
			this.brandKey = brandKey;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getWebsite() {
			return website;
		}
		public void setWebsite(String website) {
			this.website = website;
		}
		public byte getIsDisplay() {
			return isDisplay;
		}
		public void setIsDisplay(byte isDisplay) {
			this.isDisplay = isDisplay;
		}
		public String getLogoBigPic() {
			return logoBigPic;
		}
		public void setLogoBigPic(String logoBigPic) {
			this.logoBigPic = logoBigPic;
		}
		public String getLogoMidPic() {
			return logoMidPic;
		}
		public void setLogoMidPic(String logoMidPic) {
			this.logoMidPic = logoMidPic;
		}
		public String getLogoSmlPic() {
			return logoSmlPic;
		}
		public void setLogoSmlPic(String logoSmlPic) {
			this.logoSmlPic = logoSmlPic;
		}
		public String getSeoKey() {
			return seoKey;
		}
		public void setSeoKey(String seoKey) {
			this.seoKey = seoKey;
		}
		public String getSeoTitle() {
			return seoTitle;
		}
		public void setSeoTitle(String seoTitle) {
			this.seoTitle = seoTitle;
		}
		public String getSeoDescription() {
			return seoDescription;
		}
		public void setSeoDescription(String seoDescription) {
			this.seoDescription = seoDescription;
		}
		public byte getDeleteFlag() {
			return deleteFlag;
		}
		public void setDeleteFlag(byte deleteFlag) {
			this.deleteFlag = deleteFlag;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public Date getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}
		public byte getRemoveBindFlag() {
			return removeBindFlag;
		}
		public void setRemoveBindFlag(byte removeBindFlag) {
			this.removeBindFlag = removeBindFlag;
		}
		public List<String> getBrandNos() {
			return brandNos;
		}
		public void setBrandNos(List<String> brandNos) {
			this.brandNos = brandNos;
		}
	    
}
