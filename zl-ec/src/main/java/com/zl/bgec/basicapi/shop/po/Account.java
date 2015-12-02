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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import com.zl.bgec.basicapi.common.GeneratorKey;

@XmlRootElement
@Entity
@Table(name = "tbl_account")
public class Account extends GeneratorKey implements Serializable {

	private String id;
	private String accountNo;		//帐户编号
	private String userName;		//用户名
	private String password;		//密码
	private String email;			//邮箱
	private String mobile;			//手机
	private String merchantNo;		//商户编号
	private int accountLevel;		//帐户级别  是否子帐户
	private int accountType;		//帐户类型  1:企业 2个人
	private int status;				//状态 0:禁用 1:正常
	private Date createTime;
	
	
	public Account(){
		this.accountNo = super.createKey();
		this.createTime = new Date();
		
	}
	public Account(String userName){
		this.userName = userName;
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

	@Column(name = "username", length = 20)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", length = 20)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "mobile", length = 20)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "account_level", length = 2,columnDefinition="INT default 0")
	public int getAccountLevel() {
		return accountLevel;
	}

	public void setAccountLevel(int accountLevel) {
		this.accountLevel = accountLevel;
	}

	@Column(name = "account_type", length = 5 )
	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	
	@Column(name = "merchant_no", length = 30 )
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	@Column(name = "status", length = 5 ,columnDefinition="INT default 1")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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
