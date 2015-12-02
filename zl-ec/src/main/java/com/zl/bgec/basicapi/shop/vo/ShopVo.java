package com.zl.bgec.basicapi.shop.vo;

import java.util.Date;

import com.zl.bgec.basicapi.common.PageVo;
import com.zl.bgec.basicapi.shop.po.ShopType;

public class ShopVo extends PageVo {

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
	private int status;	//店铺状态 0初始化;1：待审核 2:审核通过，3关闭
	private int shopIntegral;	//店铺积分
	private String shopSign;		//店招图片URL
	private String shopIndexurl;	//店铺首页URL
	private String shopTypeNo;//店铺分类编号
	private ShopType shopType;//
	private String shopModel;//店铺类型，1实体店，2个人店，3第三方店
	private Double deliveryFee;//送货费
	private String phone;//联系电话
	private String deliveryType;//送货方式
	private String flag;//1，修改基本信息，2新增认证信息
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
	private String memberNo;//登录会员编号
	private Integer sellScope;//经营范围，单位千米
	private Date beginTime;//开始经营时间
	private Date endTime;//结束经营时间
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
	public String getMerchNo() {
		return merchNo;
	}
	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopShortName() {
		return shopShortName;
	}
	public void setShopShortName(String shopShortName) {
		this.shopShortName = shopShortName;
	}
	public String getShopLogo() {
		return shopLogo;
	}
	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public String getShopAddressNo() {
		return shopAddressNo;
	}
	public void setShopAddressNo(String shopAddressNo) {
		this.shopAddressNo = shopAddressNo;
	}
	public Date getShopCreateTime() {
		return shopCreateTime;
	}
	public void setShopCreateTime(Date shopCreateTime) {
		this.shopCreateTime = shopCreateTime;
	}
	public String getShopSummary() {
		return shopSummary;
	}
	public void setShopSummary(String shopSummary) {
		this.shopSummary = shopSummary;
	}
	public String getShopCoordination() {
		return shopCoordination;
	}
	public void setShopCoordination(String shopCoordination) {
		this.shopCoordination = shopCoordination;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getShopIntegral() {
		return shopIntegral;
	}
	public void setShopIntegral(int shopIntegral) {
		this.shopIntegral = shopIntegral;
	}
	public String getShopSign() {
		return shopSign;
	}
	public void setShopSign(String shopSign) {
		this.shopSign = shopSign;
	}
	public String getShopIndexurl() {
		return shopIndexurl;
	}
	public void setShopIndexurl(String shopIndexurl) {
		this.shopIndexurl = shopIndexurl;
	}
	public String getShopTypeNo() {
		return shopTypeNo;
	}
	public void setShopTypeNo(String shopTypeNo) {
		this.shopTypeNo = shopTypeNo;
	}
	public ShopType getShopType() {
		return shopType;
	}
	public void setShopType(ShopType shopType) {
		this.shopType = shopType;
	}
	public String getShopModel() {
		return shopModel;
	}
	public void setShopModel(String shopModel) {
		this.shopModel = shopModel;
	}
	public Double getDeliveryFee() {
		return deliveryFee;
	}
	public void setDeliveryFee(Double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLicenseRegistNo() {
		return licenseRegistNo;
	}
	public void setLicenseRegistNo(String licenseRegistNo) {
		this.licenseRegistNo = licenseRegistNo;
	}
	public Date getLicenseStartTime() {
		return licenseStartTime;
	}
	public void setLicenseStartTime(Date licenseStartTime) {
		this.licenseStartTime = licenseStartTime;
	}
	public Date getLicenseEndTime() {
		return licenseEndTime;
	}
	public void setLicenseEndTime(Date licenseEndTime) {
		this.licenseEndTime = licenseEndTime;
	}
	public String getCorporateName() {
		return corporateName;
	}
	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}
	public String getCorporateIdcardNo() {
		return corporateIdcardNo;
	}
	public void setCorporateIdcardNo(String corporateIdcardNo) {
		this.corporateIdcardNo = corporateIdcardNo;
	}
	public String getLicenseDuplicatePic() {
		return licenseDuplicatePic;
	}
	public void setLicenseDuplicatePic(String licenseDuplicatePic) {
		this.licenseDuplicatePic = licenseDuplicatePic;
	}
	public String getCorporateIdcardPicFont() {
		return corporateIdcardPicFont;
	}
	public void setCorporateIdcardPicFont(String corporateIdcardPicFont) {
		this.corporateIdcardPicFont = corporateIdcardPicFont;
	}
	public String getCorporateIdcardPicBack() {
		return corporateIdcardPicBack;
	}
	public void setCorporateIdcardPicBack(String corporateIdcardPicBack) {
		this.corporateIdcardPicBack = corporateIdcardPicBack;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdcardNo() {
		return idcardNo;
	}
	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}
	public String getIdcardPicFont() {
		return idcardPicFont;
	}
	public void setIdcardPicFont(String idcardPicFont) {
		this.idcardPicFont = idcardPicFont;
	}
	public String getIdcardPicBack() {
		return idcardPicBack;
	}
	public void setIdcardPicBack(String idcardPicBack) {
		this.idcardPicBack = idcardPicBack;
	}
	public String getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public Integer getSellScope() {
		return sellScope;
	}
	public void setSellScope(Integer sellScope) {
		this.sellScope = sellScope;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAccountName() {
		return bankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	
	
}
