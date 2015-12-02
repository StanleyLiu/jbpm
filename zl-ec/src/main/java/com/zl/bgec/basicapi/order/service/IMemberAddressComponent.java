package com.zl.bgec.basicapi.order.service;

import java.util.List;

import com.zl.bgec.basicapi.order.po.MemberAddress;

public interface IMemberAddressComponent {
	
	public void addMemberAddress(MemberAddress memberAddress) throws Exception;

	public void deleteMemberAddress(MemberAddress memberAddress) throws Exception;
	
	public List<MemberAddress> getMemberAdresses(String memberNo)throws Exception;
	
}
