package com.zl.bgec.basicapi.order.vo;

import com.zl.bgec.basicapi.common.PageVo;



/**
 */
public class OrderItemVo extends PageVo implements java.io.Serializable{

	private static final long serialVersionUID = 2096194183086811518L;
	private String id;
	private String itemNo;
	private String orderNo;
	private String prodNo;
	private Integer quantity;
	private String category;      //分类
	private String commodityNo;   //商品编号
	private String prodName;      //商品的名称+SKU规格（比如 XXX商品 红色 XL）
	private Double price;           //促销后价
	private Double couponsFee;		//优惠券金额
	private Double saleTotalFee;	//销售总价(促销价 × 商品的数量 )
	private Double disCountTotalFee;//优惠总金额(商品单个的优惠金额×商品的数量)
	private String prodPic;		    //产品图片
	
	private String buyerNo;     //买家编号

	public OrderItemVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItemVo(String id, String itemNo, String orderNo, String prodNo,
			Integer quantity, String category, String commodityNo,
			String prodName, Double price, Double couponsFee,
			Double saleTotalFee, Double disCountTotalFee, String prodPic,
			String buyerNo) {
		super();
		this.id = id;
		this.itemNo = itemNo;
		this.orderNo = orderNo;
		this.prodNo = prodNo;
		this.quantity = quantity;
		this.category = category;
		this.commodityNo = commodityNo;
		this.prodName = prodName;
		this.price = price;
		this.couponsFee = couponsFee;
		this.saleTotalFee = saleTotalFee;
		this.disCountTotalFee = disCountTotalFee;
		this.prodPic = prodPic;
		this.buyerNo = buyerNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCommodityNo() {
		return commodityNo;
	}

	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getCouponsFee() {
		return couponsFee;
	}

	public void setCouponsFee(Double couponsFee) {
		this.couponsFee = couponsFee;
	}

	public Double getSaleTotalFee() {
		return saleTotalFee;
	}

	public void setSaleTotalFee(Double saleTotalFee) {
		this.saleTotalFee = saleTotalFee;
	}

	public Double getDisCountTotalFee() {
		return disCountTotalFee;
	}

	public void setDisCountTotalFee(Double disCountTotalFee) {
		this.disCountTotalFee = disCountTotalFee;
	}

	public String getProdPic() {
		return prodPic;
	}

	public void setProdPic(String prodPic) {
		this.prodPic = prodPic;
	}

	public String getBuyerNo() {
		return buyerNo;
	}

	public void setBuyerNo(String buyerNo) {
		this.buyerNo = buyerNo;
	}
	
}
