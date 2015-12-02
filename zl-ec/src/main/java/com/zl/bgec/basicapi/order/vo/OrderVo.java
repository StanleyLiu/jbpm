package com.zl.bgec.basicapi.order.vo;


import java.util.Date;
import java.util.List;

import com.zl.bgec.basicapi.common.PageVo;
import com.zl.bgec.basicapi.order.po.OrderAddress;
import com.zl.bgec.basicapi.order.po.OrderItem;
import com.zl.bgec.basicapi.order.po.OrderLog;


public class OrderVo extends PageVo implements java.io.Serializable{

	private static final long serialVersionUID = 1;
	
	/**
	 * id
	 */
	private String id;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 买家编号
	 */
	private String buyerNo;
	/**
	 * 卖家编号
	 */
	private String sellerNo;
	/**
	 * 下单人电话
	 */
	private String buyerPhone;
	/**
	 * 总金额
	 */
	private Double priceTotal;
	/**
	 * 优惠总金额
	 */
	private Double discountTotal;
	/**
	 * 配送费用总额
	 */
	private Double deliveryFeeTotal;
	/**
	 * 订单付费总计
	 */
	private Double feeTotal;
	/**
	 * 付款方式
	 */
	private String paymentType;
	/**支付银行或某些支付平台编号(银行，支付宝，财富通)*/
	private String paymentBank;
	/**
	 * 递送方式
	 */
	private String deliveryType;
	/**
	 * 支付时间
	 */
	private Date paymentTime;
	/**
	 * 已付金额
	 */
	private Double paidTotal;
	/**
	 * 订单基本状态
	 */
	private String basicState;
	/**
	 * 订单支付状态
	 */
	private String paymentState;
	/**
	 * 退款状态
	 */
	private String refundState;
	/**
	 * 优惠活动名
	 */
	private String promotions;
	/**
	 * 貨品數量
	 */
	private Integer prodQuantity;
	/**
	 * 备注信息
	 */
	private String remark;
	/**
	 * 订单生成时间戳
	 */
	private Long orderTimestamp;
	/**
	 * 删除状态
	 */
	private String deleteFlag;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 买家留言
	 */
	private String buyerMessage;
	/**
	 * 买家昵称
	 */
	private String buyerNickName;

	/**
	 * 支付流水号
	 */
	private String paymentSequence;
	
	/**订单取消原因**/
	private String cancelReason;
	/**取消时间*/
	private Date cancelTime;
	private String shopNo;
	private String prodName;
	private String phone;
	private String queryCondition;
	private String deliveryName;//送货人名称
	private String deliveryPhone;//送货人手机
	private String deliveryDescription;//送货人描述
	private String expressCompany;//快递公司
	private String expressNo;//快递单号
	private OrderAddress orderAddress;
	
	private List<OrderLog> orderLogs;
	
	private List<OrderItem> orderItems;
	public OrderVo() {
	}

	public OrderVo(String id, String orderNo, String buyerNo, String sellerNo) {
		this.id = id;
		this.orderNo = orderNo;
		this.buyerNo = buyerNo;
		this.sellerNo = sellerNo;
	}

	public OrderVo(String id, String orderNo, String buyerNo, String sellerNo,
			Double priceTotal, Double discountTotal, Double deliveryFeeTotal,
			Double feeTotal,
			String paymentType, String deliveryType, Date paymentTime,
			Double paidTotal, String basicState, String paymentState,
			 String refundState, String promotions,
			Integer prodQuantity,  String remark, Long orderTimestamp,
			String deleteFlag, Date createTime, Date updateTime) {
		this.id = id;
		this.orderNo = orderNo;
		this.buyerNo = buyerNo;
		this.sellerNo = sellerNo;
		this.priceTotal = priceTotal;
		this.discountTotal = discountTotal;
		this.deliveryFeeTotal = deliveryFeeTotal;
		this.feeTotal = feeTotal;
		this.paymentType = paymentType;
		this.deliveryType = deliveryType;
		this.paymentTime = paymentTime;
		this.paidTotal = paidTotal;
		this.basicState = basicState;
		this.paymentState = paymentState;
		this.refundState = refundState;
		this.promotions = promotions;
		this.prodQuantity = prodQuantity;
		this.remark = remark;
		this.orderTimestamp = orderTimestamp;
		this.deleteFlag = deleteFlag;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getBuyerNo() {
		return this.buyerNo;
	}

	public void setBuyerNo(String buyerNo) {
		this.buyerNo = buyerNo;
	}

	public String getSellerNo() {
		return this.sellerNo;
	}

	public void setSellerNo(String sellerNo) {
		this.sellerNo = sellerNo;
	}

	public Double getPriceTotal() {
		return this.priceTotal;
	}

	public void setPriceTotal(Double priceTotal) {
		this.priceTotal = priceTotal;
	}

	public Double getDiscountTotal() {
		return this.discountTotal;
	}

	public void setDiscountTotal(Double discountTotal) {
		this.discountTotal = discountTotal;
	}

	public Double getDeliveryFeeTotal() {
		return this.deliveryFeeTotal;
	}

	public void setDeliveryFeeTotal(Double deliveryFeeTotal) {
		this.deliveryFeeTotal = deliveryFeeTotal;
	}

	public Double getFeeTotal() {
		return this.feeTotal;
	}

	public void setFeeTotal(Double feeTotal) {
		this.feeTotal = feeTotal;
	}

	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getDeliveryType() {
		return this.deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Date getPaymentTime() {
		return this.paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public Double getPaidTotal() {
		return this.paidTotal;
	}

	public void setPaidTotal(Double paidTotal) {
		this.paidTotal = paidTotal;
	}

	public String getBasicState() {
		return this.basicState;
	}

	public void setBasicState(String basicState) {
		this.basicState = basicState;
	}

	public String getPaymentState() {
		return this.paymentState;
	}

	public void setPaymentState(String paymentState) {
		this.paymentState = paymentState;
	}

	public String getRefundState() {
		return this.refundState;
	}

	public void setRefundState(String refundState) {
		this.refundState = refundState;
	}

	public String getPromotions() {
		return this.promotions;
	}

	public void setPromotions(String promotions) {
		this.promotions = promotions;
	}

	public Integer getProdQuantity() {
		return this.prodQuantity;
	}

	public void setProdQuantity(Integer prodQuantity) {
		this.prodQuantity = prodQuantity;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getOrderTimestamp() {
		return this.orderTimestamp;
	}

	public void setOrderTimestamp(Long orderTimestamp) {
		this.orderTimestamp = orderTimestamp;
	}

	public String getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getBuyerMessage() {
		return buyerMessage;
	}

	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}

	/** 
	 * @return paymentSequence 
	 */
	public String getPaymentSequence() {
		return paymentSequence;
	}
	
	/** 
	 * @return paymentSequence 
	 */
	public void setPaymentSequence(String paymentSequence) {
		this.paymentSequence = paymentSequence;
	}
	
	public String getBuyerNickName() {
		return buyerNickName;
	}
	
	public void setBuyerNickName(String buyerNickName) {
		this.buyerNickName = buyerNickName;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getPaymentBank() {
		return paymentBank;
	}

	public void setPaymentBank(String paymentBank) {
		this.paymentBank = paymentBank;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getQueryCondition() {
		return queryCondition;
	}

	public void setQueryCondition(String queryCondition) {
		this.queryCondition = queryCondition;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public OrderAddress getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(OrderAddress orderAddress) {
		this.orderAddress = orderAddress;
	}

	public List<OrderLog> getOrderLogs() {
		return orderLogs;
	}

	public void setOrderLogs(List<OrderLog> orderLogs) {
		this.orderLogs = orderLogs;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	public String getDeliveryPhone() {
		return deliveryPhone;
	}

	public void setDeliveryPhone(String deliveryPhone) {
		this.deliveryPhone = deliveryPhone;
	}

	public String getDeliveryDescription() {
		return deliveryDescription;
	}

	public void setDeliveryDescription(String deliveryDescription) {
		this.deliveryDescription = deliveryDescription;
	}

	public String getBuyerPhone() {
		return buyerPhone;
	}

	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	public String getExpressCompany() {
		return expressCompany;
	}

	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	
	
}
