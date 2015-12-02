package com.zl.bgec.basicapi.commodity.po;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_product_price_change")
public class ProductChangePrice implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 调价表id **/ 
	private String id;
	/** 调价编号  **/ 
	private String changeNo; 
	/** 调价操作人编号 **/ 
	private String operatorName; 
	/** 调价操作时间  **/ 
	private Date operatorTime; 
	/** 调价表审核状态  "-1"无意义  "1"表示未审核 ，"2"表示审核通过 ,"3"表示审核未通过**/ 
	private byte status=1;
	/** 调价表审核意见 **/ 
	private String conserDesc;
	/** 调价描述 **/ 
	private String describe;
	/** 审核人姓名 **/
	private String conserName;
	/** 审核人编号 **/
	private String conserNo;
	/** 审核时间 **/
	private Date conserTime;
	/** 调价人编号 **/
	private String operatorNo;
	
	/** 调价项 **/
	private Set<ProductChangePriceItem> items = new TreeSet<ProductChangePriceItem>();
	
	
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	
	@Column(name = "prod_price_change_no", nullable = false, length = 32)
	public String getChangeNo() {
		return changeNo;
	}
	
	public void setChangeNo(String changeNo) {
		this.changeNo = changeNo;
	}
	
	
	@Column(name = "operator_name", nullable = false ,length = 32)
	public String getOperatorName() {
		return operatorName;
	}
	
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	
	@Column(name = "operator_time", nullable = false )
	public Date getOperatorTime() {
		return operatorTime;
	}
	
	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
	
	
	@Column(name = "conser_status" ,length = 4 , nullable = false )
	public byte getStatus() {
		return status;
	}
	
	public void setStatus(byte status) {
		this.status = status;
	}
	
	
	@Column(name = "conser_desc" ,length = 500 )
	public String getConserDesc() {
		return conserDesc;
	}
	
	public void setConserDesc(String conserDesc) {
		this.conserDesc = conserDesc;
	}
	
	
	@Column(name = "prod_desc" , length = 500 )
	public String getDescribe() {
		return describe;
	}
	
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	@Column(name = "conser_name" , length = 32 )
	public String getConserName() {
		return conserName;
	}

	public void setConserName(String conserName) {
		this.conserName = conserName;
	}
	@Column(name = "conser_no" , length = 32 )
	public String getConserNo() {
		return conserNo;
	}

	public void setConserNo(String conserNo) {
		this.conserNo = conserNo;
	}
	@Column(name = "conser_time" )
	public Date getConserTime() {
		return conserTime;
	}

	public void setConserTime(Date conserTime) {
		this.conserTime = conserTime;
	}
	@Column(name = "operator_no" , length = 32 )
	public String getOperatorNo() {
		return operatorNo;
	}

	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}
	
	@OneToMany(targetEntity = ProductChangePriceItem.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_price_change_no", referencedColumnName = "prod_price_change_no")
	public Set<ProductChangePriceItem> getItems() {
		return items;
	}

	public void setItems(Set<ProductChangePriceItem> items) {
		this.items = items;
	}
	
}
