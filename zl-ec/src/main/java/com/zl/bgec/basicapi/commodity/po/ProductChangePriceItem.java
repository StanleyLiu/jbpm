package com.zl.bgec.basicapi.commodity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_product_price_change_item")
public class ProductChangePriceItem implements java.io.Serializable {
	
	/** 调价表id **/ 
	private String id;
	/** 调价编号  **/ 
	private String changeNo; 
	/** 货品编号  **/ 
	private String prodNo; 
	/** 调价前  **/ 
	private double preSellPrice; 
	/** 调价后 **/ 
	private double newSellPrice;
	
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
	
	@Column(name = "prod_no", length = 32)
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	
	@Column(name = "pre_sell_price", length = 32)
	public double getPreSellPrice() {
		return preSellPrice;
	}
	public void setPreSellPrice(double preSellPrice) {
		this.preSellPrice = preSellPrice;
	}
	
	@Column(name = "new_sell_price", length = 32)
	public double getNewSellPrice() {
		return newSellPrice;
	}
	public void setNewSellPrice(double newSellPrice) {
		this.newSellPrice = newSellPrice;
	} 
	
	
	
}
