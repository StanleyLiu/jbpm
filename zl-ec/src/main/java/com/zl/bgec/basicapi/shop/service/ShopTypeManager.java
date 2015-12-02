package com.zl.bgec.basicapi.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.zl.bgec.basicapi.shop.po.ShopType;
@Component
public class ShopTypeManager {
	public List<ShopType> shopTypes=new ArrayList<ShopType>();
	public List<ShopType> getShopTypes(){
		return shopTypes;
	}
	public void setShopTypes(List<ShopType> list){
		shopTypes.clear();
		shopTypes.addAll(list);
	}
}
