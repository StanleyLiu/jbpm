package com.zl.bgec.basicapi.shop.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 店铺收藏
 * @author Stanley
 *
 */
@Entity
@Table(name = "tbl_shop_collect")
public class ShopCollectVo implements Serializable{
	private String id;
	private String shopNo;//店铺编号
	private Date createTime;//收藏时间
	private String memberNo;//收藏人编号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	
	
	
	
}
