package com.zl.bgec.basicapi.commodity.vo;

public class ProductChangePriceItemVo {
	
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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChangeNo() {
		return changeNo;
	}
	public void setChangeNo(String changeNo) {
		this.changeNo = changeNo;
	}
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public double getPreSellPrice() {
		return preSellPrice;
	}
	public void setPreSellPrice(double preSellPrice) {
		this.preSellPrice = preSellPrice;
	}
	public double getNewSellPrice() {
		return newSellPrice;
	}
	public void setNewSellPrice(double newSellPrice) {
		this.newSellPrice = newSellPrice;
	}
	
	
}
