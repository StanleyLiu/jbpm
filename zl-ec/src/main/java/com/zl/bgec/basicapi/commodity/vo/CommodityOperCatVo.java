package com.zl.bgec.basicapi.commodity.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.zl.bgec.basicapi.common.PageVo;
/**
 * 
 * @author liuxiaolong
 * 商品运营分类VO对象
 */
public class CommodityOperCatVo extends PageVo implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private String id;
    private String oprtCatNo; // 运营分类编号
    private String oprtCatName; // 运营分类名称
    private String oprtCatPno; // 父分类编号
    private String oprtCatPath; // 父分类编号
    private String oprtCatKey;// 运营分类关键字
    private int sortNo; // 排序号
    private String description;// 描述
    private String seoKey; // SEO关键字
    private String seoTitle;// 标题
    private String seoDescription; // SEO描述
    private byte isEnable; // 是否启用 0启用 1不启用 default 0
    private byte isRecommand;// 是否推荐 0不推荐 1推荐 default 0
    private byte deleteFlag; // 删除标识 0未删除 1已删除 default 0
    private Date createTime;
    private Date updateTime;
    private int level;// 表明是第几级分类（只有3级）
    
    private String brandNo;
    private String commoName;
    
    private String commoNo;
    
    private List<String> commoNos;
    
    private List<String> oprtCatNos;
    
    private byte removeBindFlag;//0删除关联  1不删除关联
    
    private int returnInfo; //0不返回信息，1返回信息
    
    public String getOprtPic() {
		return oprtPic;
	}
	public void setOprtPic(String oprtPic) {
		this.oprtPic = oprtPic;
	}
	/**分类图片*/
    private String oprtPic;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOprtCatNo() {
		return oprtCatNo;
	}
	public void setOprtCatNo(String oprtCatNo) {
		this.oprtCatNo = oprtCatNo;
	}
	public String getOprtCatName() {
		return oprtCatName;
	}
	public void setOprtCatName(String oprtCatName) {
		this.oprtCatName = oprtCatName;
	}
	public String getOprtCatPno() {
		return oprtCatPno;
	}
	public void setOprtCatPno(String oprtCatPno) {
		this.oprtCatPno = oprtCatPno;
	}
	public String getOprtCatPath() {
		return oprtCatPath;
	}
	public void setOprtCatPath(String oprtCatPath) {
		this.oprtCatPath = oprtCatPath;
	}
	public String getOprtCatKey() {
		return oprtCatKey;
	}
	public void setOprtCatKey(String oprtCatKey) {
		this.oprtCatKey = oprtCatKey;
	}
	public int getSortNo() {
		return sortNo;
	}
	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public byte getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(byte isEnable) {
		this.isEnable = isEnable;
	}
	public byte getIsRecommand() {
		return isRecommand;
	}
	public void setIsRecommand(byte isRecommand) {
		this.isRecommand = isRecommand;
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getBrandNo() {
		return brandNo;
	}
	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}
	public String getCommoName() {
		return commoName;
	}
	public void setCommoName(String commoName) {
		this.commoName = commoName;
	}
	public String getCommoNo() {
		return commoNo;
	}
	public void setCommoNo(String commoNo) {
		this.commoNo = commoNo;
	}
	public List<String> getCommoNos() {
		return commoNos;
	}
	public void setCommoNos(List<String> commoNos) {
		this.commoNos = commoNos;
	}
	public byte getRemoveBindFlag() {
		return removeBindFlag;
	}
	public void setRemoveBindFlag(byte removeBindFlag) {
		this.removeBindFlag = removeBindFlag;
	}
	public List<String> getOprtCatNos() {
		return oprtCatNos;
	}
	public void setOperCatNos(List<String> oprtCatNos) {
		this.oprtCatNos = oprtCatNos;
	}
	public int getReturnInfo() {
		return returnInfo;
	}
	public void setReturnInfo(int returnInfo) {
		this.returnInfo = returnInfo;
	}
    
}
