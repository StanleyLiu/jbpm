/**
 *
 * Project:shop
 * Date:2014-10-8
 * Author: Allen.Z
 * Desc: 
 *
 */
package com.zl.bgec.basicapi.shop.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import com.zl.bgec.basicapi.common.GeneratorKey;

@Entity
@Table(name = "tbl_shop_info")
public class Shop extends GeneratorKey implements Serializable {

	private String id;
	private String shopNo;			//店铺编号
	private String merchNo;			//商户编号
	private String shopName;		//店铺名称
	private String shopShortName;	//店铺简称
	private String shopLogo;		//店铺LOGO
	private String shopAddress;		//店铺地址
	private String shopAddressNo;	//所在地编号
	private Date shopCreateTime;	//创建时间
	private String shopSummary;		//店铺简介
	private String shopCoordination;//座标
	private int status;	//店铺状态 0初始化或审核失败;1：待审核 2:审核通过，3关闭
	private String auditRemark;//审核原因
	private String deliveryType;//送货方式
	private int shopIntegral;	//店铺积分
	private String shopSign;		//店招图片URL
	private String shopIndexurl;	//店铺首页URL
	private String shopTypeNo;//店铺分类编号
	private String shopTypeName;//店铺分类编号
	private ShopType shopType;//
	private String shopModel;//店铺类型，1实体店，2个人店，3第三方店
	private Double deliveryFee;//送货费
	private String phone;//联系电话
	private String bankAccount;//收款银行卡号
	private String bankName;//开户行
	private String bankAccountName;//帐号名称
	/**
	 * 实体店 信息
	 */
	private String companyName;//公司名称
	private String licenseRegistNo;//营业执照注册号
	private Date licenseStartTime;//营业执照开始时间
	private Date licenseEndTime;//营业执照结束时间
	private String corporateName;//法人代表姓名
	private String corporateIdcardNo;//法人代表身份证号码
	private String licenseDuplicatePic;//营业执照副本照片
	private String corporateIdcardPicFont;//法人身份证照片正面
	private String corporateIdcardPicBack;//法人身份证照片背面
	/**
	 * 个人店信息
	 */
	private String realName;//真实姓名
	private String idcardNo;//身份证号码
	private String idcardPicFont;//身份证正面照片
	private String idcardPicBack;//身份证背面照片
	private String isRecommend;//是否推荐店铺，0未推荐，1推荐
	private Integer sellScope;//经营范围，单位千米
	private Date beginTime;//开始经营时间
	private Date endTime;//结束经营时间
	public Shop(){
		shopCreateTime = new Date();
		shopNo = createKey();
	}
	public Shop(String shopName){
		this.shopName = shopName;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 64)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "shop_no", length = 32,unique=true,nullable=false)
	public String getShopNo() {
		return shopNo;
	}
	
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	@Column(name = "merch_no", length = 32)
	public String getMerchNo() {
		return merchNo;
	}
	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}
	@Column(name = "shop_name", length = 200)
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	@Column(name = "shop_sort_name", length = 20)
	public String getShopShortName() {
		return shopShortName;
	}
	public void setShopShortName(String shopShortName) {
		this.shopShortName = shopShortName;
	}
	@Column(name = "shop_logo", length = 200)
	public String getShopLogo() {
		return shopLogo;
	}
	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}
	@Column(name = "shop_address", length = 200)
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	@Column(name = "shop_address_no", length = 20)
	public String getShopAddressNo() {
		return shopAddressNo;
	}
	public void setShopAddressNo(String shopAddressNo) {
		this.shopAddressNo = shopAddressNo;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "shop_create_time")
	public Date getShopCreateTime() {
		return shopCreateTime;
	}
	public void setShopCreateTime(Date shopCreateTime) {
		this.shopCreateTime = shopCreateTime;
	}
	@Column(name = "shop_summary", length = 20)
	public String getShopSummary() {
		return shopSummary;
	}
	public void setShopSummary(String shopSummary) {
		this.shopSummary = shopSummary;
	}
	@Column(name = "status", length = 5,columnDefinition="INT default 0")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name = "shop_integral", length = 5,columnDefinition="INT default 0")
	public int getShopIntegral() {
		return shopIntegral;
	}
	public void setShopIntegral(int shopIntegral) {
		this.shopIntegral = shopIntegral;
	}
	@Column(name = "shop_sign", length = 20)
	public String getShopSign() {
		return shopSign;
	}
	public void setShopSign(String shopSign) {
		this.shopSign = shopSign;
	}
	@Column(name = "shop_indexurl", length = 20)
	public String getShopIndexurl() {
		return shopIndexurl;
	}
	public void setShopIndexurl(String shopIndexurl) {
		this.shopIndexurl = shopIndexurl;
	}
	@Column(name = "shop_type_no", length = 20)
	public String getShopTypeNo() {
		return shopTypeNo;
	}
	public void setShopTypeNo(String shopTypeNo) {
		this.shopTypeNo = shopTypeNo;
	}
	@ManyToOne(targetEntity = ShopType.class)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "shop_type_no", referencedColumnName = "shop_type_no", insertable = false, updatable = false)
	public ShopType getShopType() {
		return shopType;
	}
	public void setShopType(ShopType shopType) {
		this.shopType = shopType;
	}
	@Column(name = "shop_coordination")
	public String getShopCoordination() {
		return shopCoordination;
	}
	public void setShopCoordination(String shopCoordination) {
		this.shopCoordination = shopCoordination;
	}
	@Column(name = "shop_model",nullable=false)
	public String getShopModel() {
		return shopModel;
	}
	public void setShopModel(String shopModel) {
		this.shopModel = shopModel;
	}
	@Column(name = "delivery_fee")
	public Double getDeliveryFee() {
		return deliveryFee;
	}
	public void setDeliveryFee(Double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name = "company_name")
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Column(name = "license_Regist_No")
	public String getLicenseRegistNo() {
		return licenseRegistNo;
	}
	public void setLicenseRegistNo(String licenseRegistNo) {
		this.licenseRegistNo = licenseRegistNo;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "license_Start_Time")
	public Date getLicenseStartTime() {
		return licenseStartTime;
	}
	public void setLicenseStartTime(Date licenseStartTime) {
		this.licenseStartTime = licenseStartTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "license_End_Time")
	public Date getLicenseEndTime() {
		return licenseEndTime;
	}
	public void setLicenseEndTime(Date licenseEndTime) {
		this.licenseEndTime = licenseEndTime;
	}
	@Column(name = "corporate_name")
	public String getCorporateName() {
		return corporateName;
	}
	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}
	@Column(name = "corporate_Idcard_No")
	public String getCorporateIdcardNo() {
		return corporateIdcardNo;
	}
	public void setCorporateIdcardNo(String corporateIdcardNo) {
		this.corporateIdcardNo = corporateIdcardNo;
	}
	@Column(name = "corporate_idcard_pic_font")
	public String getCorporateIdcardPicFont() {
		return corporateIdcardPicFont;
	}
	public void setCorporateIdcardPicFont(String corporateIdcardPicFont) {
		this.corporateIdcardPicFont = corporateIdcardPicFont;
	}
	@Column(name = "corporate_Idcard_Pic_Back")
	public String getCorporateIdcardPicBack() {
		return corporateIdcardPicBack;
	}
	public void setCorporateIdcardPicBack(String corporateIdcardPicBack) {
		this.corporateIdcardPicBack = corporateIdcardPicBack;
	}
	@Column(name = "real_name")
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	@Column(name = "idcard_no")
	public String getIdcardNo() {
		return idcardNo;
	}
	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}
	@Column(name = "idcard_Pic_Font")
	public String getIdcardPicFont() {
		return idcardPicFont;
	}
	public void setIdcardPicFont(String idcardPicFont) {
		this.idcardPicFont = idcardPicFont;
	}
	@Column(name = "idcard_Pic_Back")
	public String getIdcardPicBack() {
		return idcardPicBack;
	}
	public void setIdcardPicBack(String idcardPicBack) {
		this.idcardPicBack = idcardPicBack;
	}
	@Column(name = "license_duplicate_pic")
	public String getLicenseDuplicatePic() {
		return licenseDuplicatePic;
	}
	public void setLicenseDuplicatePic(String licenseDuplicatePic) {
		this.licenseDuplicatePic = licenseDuplicatePic;
	}
	@Column(name = "is_recommend")
	public String getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}
	@Column(name = "sell_scope")
	public Integer getSellScope() {
		return sellScope;
	}
	public void setSellScope(Integer sellScope) {
		this.sellScope = sellScope;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "begin_time")
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Column(name = "delivery_type")
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	@Transient
	public String getShopTypeName() {
		return shopTypeName;
	}
	public void setShopTypeName(String shopTypeName) {
		this.shopTypeName = shopTypeName;
	}
	@Column(name="audit_remark")
	public String getAuditRemark() {
		return auditRemark;
	}
	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}
	@Column(name="bank_account")
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	@Column(name="bank_name")
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	@Column(name="bank_account_name")
	public String getBankAccountName() {
		return bankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	
	
}
