package com.zl.bgec.basicapi.shop.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zl.bgec.basicapi.shop.dao.IShopTypeDao;
import com.zl.bgec.basicapi.shop.po.ShopType;
import com.zl.bgec.basicapi.shop.service.IShopTypeService;
/**
 * 店铺分类service
 * @author Stanley
 *
 */
@Service
public class ShopTypeServiceImpl implements IShopTypeService {
	@Resource
	private IShopTypeDao shopTypeDao;
	@Override
	public ShopType saveShopType(ShopType shopType) throws Exception {
		shopType.setCreateTime(new Date());
		Serializable id = shopTypeDao.save(shopType);
		shopType.setId(id.toString());
		return shopType;
	}

	@Override
	public List<ShopType> getShopType() throws Exception {
		return shopTypeDao.getAll(); 
	}

}
