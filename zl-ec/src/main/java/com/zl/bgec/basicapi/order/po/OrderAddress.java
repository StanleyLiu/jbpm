package com.zl.bgec.basicapi.order.po;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * TblOrderAddress generated by hbm2java
 */
@Entity
@Table(name = "tbl_order_address")
public class OrderAddress implements java.io.Serializable {

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

	public OrderAddress() {
	}

	public OrderAddress(String id, String orderNo, String buyerNo,
			String realName, String province, String city, String address) {
		this.id = id;
		this.orderNo = orderNo;
		this.buyerNo = buyerNo;
		this.realName = realName;
		this.province = province;
		this.city = city;
		this.address = address;
	}

	public OrderAddress(String id, String orderNo, String buyerNo,
			String realName, String telephone, String cellphone, String email,
			String province, String city, String district, String address,
			String zipcode, Byte deleteFlag, Date createTime, Date updateTime) {
		this.id = id;
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

	/** 
	 * @return addressNo 
	 */
	@Column(name = "address_no", unique = true, nullable = false, length = 32)
	public String getAddressNo() {
		return addressNo;
	}

	/**
	 * @param addressNo the addressNo to set
	 */
	public void setAddressNo(String addressNo) {
		this.addressNo = addressNo;
	}

	@Column(name = "order_no", unique = true, nullable = false, length = 32)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "buyer_no", nullable = false, length = 32)
	public String getBuyerNo() {
		return this.buyerNo;
	}

	public void setBuyerNo(String buyerNo) {
		this.buyerNo = buyerNo;
	}

	@Column(name = "real_name", nullable = false, length = 50)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "telephone", length = 50)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "cellphone", length = 50)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "province", nullable = false, length = 50)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city", nullable = false, length = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "district", length = 50)
	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Column(name = "address", nullable = false, length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "zipcode", length = 50)
	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name = "delete_flag")
	public Byte getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "super_date", length = 32)
	public String getSuperDate() {
		return superDate;
	}

	public void setSuperDate(String superDate) {
		this.superDate = superDate;
	}

	
}
