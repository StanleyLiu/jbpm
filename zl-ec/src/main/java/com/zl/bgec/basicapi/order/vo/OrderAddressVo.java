package com.zl.bgec.basicapi.order.vo;


import java.util.Date;

import com.zl.bgec.basicapi.common.PageVo;

/**
 */
public class OrderAddressVo extends PageVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String addressNo;//地址编号
	private String orderNo;//订单编号
	private String buyerNo;//买家编号
	private String realName;//真是姓名
	private String telephone;//电话
	private String cellphone;//手机
	private String email;//email
	private String province;// 省
	private String city;//市
	private String district;//区
	private String address;//详细地址
	private String zipcode;//邮编
	private Byte deleteFlag;
	private Date createTime;
	private Date updateTime;
	private String superDate;
	
	
	
	public OrderAddressVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderAddressVo(String id, String addressNo, String orderNo,
			String buyerNo, String realName, String telephone,
			String cellphone, String email, String province, String city,
			String district, String address, String zipcode, Byte deleteFlag,
			Date createTime, Date updateTime, String superDate) {
		super();
		this.id = id;
		this.addressNo = addressNo;
		this.orderNo = orderNo;
		this.buyerNo = buyerNo;
		this.realName = realName;
		this.telephone = telephone;
		this.cellphone = cellphone;
		this.email = email;
		this.province = province;
		this.city = city;
		this.district = district;
		this.address = address;
		this.zipcode = zipcode;
		this.deleteFlag = deleteFlag;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.superDate = superDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddressNo() {
		return addressNo;
	}
	public void setAddressNo(String addressNo) {
		this.addressNo = addressNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getBuyerNo() {
		return buyerNo;
	}
	public void setBuyerNo(String buyerNo) {
		this.buyerNo = buyerNo;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Byte getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Byte deleteFlag) {
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
	public String getSuperDate() {
		return superDate;
	}
	public void setSuperDate(String superDate) {
		this.superDate = superDate;
	}

	
}
