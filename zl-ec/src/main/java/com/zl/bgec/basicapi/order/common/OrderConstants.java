package com.zl.bgec.basicapi.order.common;
/**
 * 
 * @author Stanley
 *
 */
public class OrderConstants {
	
	/***********************************基本状态******************************************/
	/**1待付款*/
	public static final String BASIC_STATE_WAITING_PAY="1";
	/**2待发货*/
	public static final String BASIC_STATE_WAITING_DELIVERY="2";
	/**3已发货*/
	public static final String BASIC_STATE_ALREADY_DELIVERY="3";
	/**4退款申请中*/
	public static final String BASIC_STATE_REFUND_APPLY="4";
	/**5已退款 */
	public static final String BASIC_STATE_ALREADY_REFUND="5";
	/**6已完成 */
	public static final String BASIC_STATE_COMPLETED="6";
	/**7已关闭*/
	public static final String BASIC_STATE_CLOSED="7";
	/**8组团中*/
	public static final String BASIC_STATE_BOOKING="8";
	
}
