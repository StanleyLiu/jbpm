package com.zl.bgec.basicapi.order.vo;

import java.util.List;

import com.zl.bgec.basicapi.common.oauth2.UserServiceAddressDTO;

public class ConfirmOrderObj {
	private List<TempOrderVo> orders;
	private UserServiceAddressDTO address;
	public List<TempOrderVo> getOrders() {
		return orders;
	}
	public void setOrders(List<TempOrderVo> orders) {
		this.orders = orders;
	}
	public UserServiceAddressDTO getAddress() {
		return address;
	}
	public void setAddress(UserServiceAddressDTO address) {
		this.address = address;
	}
	

}
