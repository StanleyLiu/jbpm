package com.zl.bgec.basicapi.commodity.vo;

import java.io.Serializable;
import java.util.List;

import com.zl.bgec.basicapi.common.PageVo;

/**
 * 
 * @author liuxiaolong
 * 标签的VO对象
 *
 */
public class CommodityTagVo extends PageVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
    private String tagNo;
    private String tagName;
    private String sellerNo;
    private String description;
    private byte deleteFlag;// 0表示不启用，1表示启用
    
    private String commodityNo;
    private List<String> commodityNos;
    
    private List<String> tagNos;
    
    private String commoName;
    private String catNo;
    private String brandName;
    private String brandNo;
    
    private byte removeBindFlag;//主要用于判断是否删除和商品的关联，0删除，1不删除
    
    private int returnInfo;//0不返回信息，1返回信息
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTagNo() {
		return tagNo;
	}
	public void setTagNo(String tagNo) {
		this.tagNo = tagNo;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getSellerNo() {
		return sellerNo;
	}
	public void setSellerNo(String sellerNo) {
		this.sellerNo = sellerNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
	public List<String> getCommodityNos() {
		return commodityNos;
	}
	public void setCommodityNos(List<String> commodityNos) {
		this.commodityNos = commodityNos;
	}
	public String getCommoName() {
		return commoName;
	}
	public void setCommoName(String commoName) {
		this.commoName = commoName;
	}
	public String getCatNo() {
		return catNo;
	}
	public void setCatNo(String catNo) {
		this.catNo = catNo;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandNo() {
		return brandNo;
	}
	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}
	public byte getRemoveBindFlag() {
		return removeBindFlag;
	}
	public void setRemoveBindFlag(byte removeBindFlag) {
		this.removeBindFlag = removeBindFlag;
	}
	public List<String> getTagNos() {
		return tagNos;
	}
	public void setTagNos(List<String> tagNos) {
		this.tagNos = tagNos;
	}
	public int getReturnInfo() {
		return returnInfo;
	}
	public void setReturnInfo(int returnInfo) {
		this.returnInfo = returnInfo;
	}
    
}
