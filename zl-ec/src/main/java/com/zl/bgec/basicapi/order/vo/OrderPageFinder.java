package com.zl.bgec.basicapi.order.vo;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;

public class OrderPageFinder<T> extends PageFinder<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1965836677749880530L;
	
	private Integer orderCount1;
	private Integer orderCount2;
	private Integer orderCount3;
	private Integer orderCount4;

	public OrderPageFinder(int pageNo, int rowCount) {
		super(pageNo, rowCount);
	}

	public OrderPageFinder(int pageNo, int pageSize, int totalRows) {
		super(pageNo,pageSize,totalRows);
	}

	public Integer getOrderCount1() {
		return orderCount1;
	}

	public void setOrderCount1(Integer orderCount1) {
		this.orderCount1 = orderCount1;
	}

	public Integer getOrderCount2() {
		return orderCount2;
	}

	public void setOrderCount2(Integer orderCount2) {
		this.orderCount2 = orderCount2;
	}

	public Integer getOrderCount3() {
		return orderCount3;
	}

	public void setOrderCount3(Integer orderCount3) {
		this.orderCount3 = orderCount3;
	}

	public Integer getOrderCount4() {
		return orderCount4;
	}

	public void setOrderCount4(Integer orderCount4) {
		this.orderCount4 = orderCount4;
	}
}
