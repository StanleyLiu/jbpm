/**
 *
 * Project:shop
 * Date:2014-10-13
 * Author: Allen.Z
 * Desc: 企业商户表
 *
 */
package com.zl.bgec.basicapi.shop.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.annotations.GenericGenerator;

import com.zl.bgec.basicapi.common.GeneratorKey;

@XmlRootElement
@Entity
@Table(name = "tbl_merchant_extinfo")
public class EnterpriseMerchant extends GeneratorKey {

	public String id;				//主键
	public String accountNo;		//帐户编号
	public String merchNo; 			// 商户编号
	public String merchName; 		//企业名称
	public String merchIndustry; 	// 主营行业
	public String merchProduct; 	// 主营产品
	public String merchModel; 		//经营模式
	public String merchWebsite; 	//会员公司网址
	public String merchLicenseurl; 	//营业执照图片
	public String merchTax; 		//税务登记证
	public String merchqq; 			//客服qq
	public String merchCardId; 		//法人身份证
	public String merchOtherCertificate; // 其他证件
	public String merchMarketAreaNo; // 所属市场区域
	public String merchMarketAreaDetail; //所属市场区域详情
	public String merchType; 		// 商家类型
	public int merchIntegral; 		// 商家积分
	public int merchLevel; 			// 商户级别
	public String status;			//商户状态
	public Date createTime; 		//创建时间

	public EnterpriseMerchant(){
		this.merchNo= createKey();
		createTime = new Date();
		
	}
	
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "account_no", length = 20)
	public String getAccountNo() {
		return accountNo;
	}

	
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	@Column(name = "merch_no", length = 50)
	public String getMerchNo() {
		return merchNo;
	}

	public void setMerchNo(String merchNo) {
		this.merchNo = merchNo;
	}

	@Column(name = "merch_name", length = 50)
	public String getMerchName() {
		return merchName;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	@Column(name = "merch_industry", length = 50)
	public String getMerchIndustry() {
		return merchIndustry;
	}

	public void setMerchIndustry(String merchIndustry) {
		this.merchIndustry = merchIndustry;
	}

	@Column(name = "merch_product", length = 50)
	public String getMerchProduct() {
		return merchProduct;
	}

	public void setMerchProduct(String merchProduct) {
		this.merchProduct = merchProduct;
	}

	@Column(name = "merch_model", length = 50)
	public String getMerchModel() {
		return merchModel;
	}

	public void setMerchModel(String merchModel) {
		this.merchModel = merchModel;
	}

	@Column(name = "merch_website", length = 50)
	public String getMerchWebsite() {
		return merchWebsite;
	}

	public void setMerchWebsite(String merchWebsite) {
		this.merchWebsite = merchWebsite;
	}

	@Column(name = "merch_licenseurl", length = 50)
	public String getMerchLicenseurl() {
		return merchLicenseurl;
	}

	public void setMerchLicenseurl(String merchLicenseurl) {
		this.merchLicenseurl = merchLicenseurl;
	}

	@Column(name = "merch_tax", length = 50)
	public String getMerchTax() {
		return merchTax;
	}

	public void setMerchTax(String merchTax) {
		this.merchTax = merchTax;
	}

	@Column(name = "merch_qq", length = 50)
	public String getMerchqq() {
		return merchqq;
	}

	public void setMerchqq(String merchqq) {
		this.merchqq = merchqq;
	}

	@Column(name = "merch_card_id", length = 50)
	public String getMerchCardId() {
		return merchCardId;
	}

	public void setMerchCardId(String merchCardId) {
		this.merchCardId = merchCardId;
	}

	@Column(name = "merch_other_certificate", length = 50)
	public String getMerchOtherCertificate() {
		return merchOtherCertificate;
	}

	public void setMerchOtherCertificate(String merchOtherCertificate) {
		this.merchOtherCertificate = merchOtherCertificate;
	}

	@Column(name = "merch_market_area_no", length = 50)
	public String getMerchMarketAreaNo() {
		return merchMarketAreaNo;
	}

	public void setMerchMarketAreaNo(String merchMarketAreaNo) {
		this.merchMarketAreaNo = merchMarketAreaNo;
	}

	@Column(name = "merch_market_area_detail", length = 50)
	public String getMerchMarketAreaDetail() {
		return merchMarketAreaDetail;
	}

	public void setMerchMarketAreaDetail(String merchMarketAreaDetail) {
		this.merchMarketAreaDetail = merchMarketAreaDetail;
	}

	@Column(name = "merch_type", length = 5)
	public String getMerchType() {
		return merchType;
	}

	public void setMerchType(String merchType) {
		this.merchType = merchType;
	}

	@Column(name = "merch_integral", length = 5,columnDefinition="INT default 0")
	public int getMerchIntegral() {
		return merchIntegral;
	}

	public void setMerchIntegral(int merchIntegral) {
		this.merchIntegral = merchIntegral;
	}

	@Column(name = "merch_level", length = 5,columnDefinition="INT default 1")
	public int getMerchLevel() {
		return merchLevel;
	}

	public void setMerchLevel(int merchLevel) {
		this.merchLevel = merchLevel;
	}
	
	@Column(name = "status",columnDefinition="VARCHAR(5) default '0'" )
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "create_time", length = 20)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
