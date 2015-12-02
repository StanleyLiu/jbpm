/**
 *
 * Project:shop
 * Date:2014-10-13
 * Author: Allen.Z
 * Desc: 个人商户表
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
@Table(name = "tbl_person_extinfo")
public class PersonMerchant extends GeneratorKey {

	public String id;
	public String perNo;	 //个人商户编号
	public String accountNo; // 帐户编号
	public String realName; //真实姓名
	public String cardNo; // 身份证号
	public String cardPhotoA; // 身份证正面
	public String cardPhotoB; // 身份证反面
	public String personPhoto; // 个人全身照
	public Date createTime; //创建时间
	public String status;		//状态
	
	
	public PersonMerchant(){
		this.perNo = createKey();
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
	
	
	@Column(name = "per_no", length = 32)
	public String getPerNo() {
		return perNo;
	}

	public void setPerNo(String perNo) {
		this.perNo = perNo;
	}

	@Column(name = "account_no", length = 32)
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	@Column(name = "real_name", length = 32)
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "card_no", length = 18)
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Column(name = "card_photo_a", length = 200)
	public String getCardPhotoA() {
		return cardPhotoA;
	}

	public void setCardPhotoA(String cardPhotoA) {
		this.cardPhotoA = cardPhotoA;
	}

	@Column(name = "card_photo_b", length = 200)
	public String getCardPhotoB() {
		return cardPhotoB;
	}

	public void setCardPhotoB(String cardPhotoB) {
		this.cardPhotoB = cardPhotoB;
	}

	@Column(name = "person_photo", length = 200)
	public String getPersonPhoto() {
		return personPhoto;
	}

	public void setPersonPhoto(String personPhoto) {
		this.personPhoto = personPhoto;
	}

	@Column(name = "status", columnDefinition="VARCHAR(5) default '0'")
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
