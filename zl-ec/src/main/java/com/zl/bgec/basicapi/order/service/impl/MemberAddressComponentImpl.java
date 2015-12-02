package com.zl.bgec.basicapi.order.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zl.bgec.basicapi.order.common.OrderUtils;
import com.zl.bgec.basicapi.order.dao.IMemberAddressDao;
import com.zl.bgec.basicapi.order.po.MemberAddress;
import com.zl.bgec.basicapi.order.service.IMemberAddressComponent;
@Service
public class MemberAddressComponentImpl implements IMemberAddressComponent {
	@Resource
	private IMemberAddressDao memberAddressDao;
	
	@Override
	@Transactional
	public void addMemberAddress(MemberAddress memberAddress) throws Exception {
		memberAddress.setAddressNo(OrderUtils.genOrderItemNo());
		memberAddress.setCreateTime(new Date());
		memberAddressDao.save(memberAddress);
		
	}

	@Override
	@Transactional
	public void deleteMemberAddress(MemberAddress memberAddress)
			throws Exception {
		MemberAddress memberAddressPo = memberAddressDao.get("addressNo",memberAddress.getAddressNo());
		memberAddressDao.delete(memberAddressPo);
	}

	@Override
	@Transactional(readOnly=true)
	public List<MemberAddress> getMemberAdresses(String memberNo) throws Exception {
		return memberAddressDao.getList("memberNo",memberNo);
	}

}
