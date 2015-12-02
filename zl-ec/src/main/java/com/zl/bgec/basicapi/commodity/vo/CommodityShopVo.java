package com.zl.bgec.basicapi.commodity.vo;

import java.util.Date;
import java.util.List;

import com.zl.bgec.basicapi.commodity.po.Commodity;
import com.zl.bgec.basicapi.common.PageVo;

public class CommodityShopVo extends PageVo{
	
	private String id;
	private String commodityNo;
	private Commodity commodity;
	private String sellerNo;
	private String shopNo;
	private Date createTime;
	
	private Date startTime;
	
	private Date endTime;

	private List<String> ids;
	
	private String commodityName;
	
	private int returnInfo;//0不返回信息，1返回信息
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommodityNo() {
		return commodityNo;
	}

	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public String getSellerNo() {
		return sellerNo;
	}

	public void setSellerNo(String sellerNo) {
		this.sellerNo = sellerNo;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public int getReturnInfo() {
		return returnInfo;
	}

	public void setReturnInfo(int returnInfo) {
		this.returnInfo = returnInfo;
	}
	
	
}
