package com.zl.bgec.basicapi.commodity.vo;

public class ProductStockVo {
	private String id;
	private String productNo;
	private int handleTarget;//0实库，1虚库
	private int handleType;//0增加，1减少
	private int stock;
	private int virtualStock;
	private int handleNum;//操作数量
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public int getHandleType() {
		return handleType;
	}
	public void setHandleType(int handleType) {
		this.handleType = handleType;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getVirtualStock() {
		return virtualStock;
	}
	public void setVirtualStock(int virtualStock) {
		this.virtualStock = virtualStock;
	}
	public int getHandleNum() {
		return handleNum;
	}
	public void setHandleNum(int handleNum) {
		this.handleNum = handleNum;
	}
	public int getHandleTarget() {
		return handleTarget;
	}
	public void setHandleTarget(int handleTarget) {
		this.handleTarget = handleTarget;
	}
	
	
	
}
