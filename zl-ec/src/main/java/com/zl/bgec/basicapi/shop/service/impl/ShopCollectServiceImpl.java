package com.zl.bgec.basicapi.shop.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zl.bgec.basicapi.basecomponent.utils.ObjectConvertUtil;
import com.zl.bgec.basicapi.shop.dao.IShopCollectDao;
import com.zl.bgec.basicapi.shop.po.ShopCollect;
import com.zl.bgec.basicapi.shop.service.IShopCollectService;
import com.zl.bgec.basicapi.shop.vo.ShopCollectVo;
@Service
public class ShopCollectServiceImpl implements IShopCollectService {

	@Resource
	private IShopCollectDao shopCollectDao;
	@Override
	@Transactional
	public void saveShopCollect(ShopCollectVo shopCollectVo) throws Exception {
		ShopCollect shopCollect = new ShopCollect();
		ObjectConvertUtil.convertVoToPo(shopCollectVo, shopCollect);
		shopCollect.setCreateTime(new Date());
		shopCollectDao.save(shopCollect);
	}

	@Override
	@Transactional
	public void deleteShopCollect(String shopNo, String memberNo) throws Exception {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("shopNo", shopNo);
		condition.put("memberNo", memberNo);
		shopCollectDao.delete(condition);
	}

}
