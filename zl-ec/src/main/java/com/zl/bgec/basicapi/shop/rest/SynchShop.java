package com.zl.bgec.basicapi.shop.rest;

import java.util.HashMap;
import java.util.Map;

import com.zl.bgec.basicapi.common.oauth2.SdkConstants;
import com.zl.bgec.basicapi.common.oauth2.Signature;
import com.zl.bgec.basicapi.common.util.RestUtil;
import com.zl.bgec.basicapi.common.utils.SpringContextUtils;
import com.zl.bgec.basicapi.shop.po.Shop;

public class SynchShop implements Runnable{
	public static Shop shop;
	public static String userid;
	@Override
	public void run() {
	    String coordination = shop.getShopCoordination();
	    SdkConstants sdkConstants = SpringContextUtils.getBean(SdkConstants.class);
	    double longitude=0;
	    double latitude=0;
	    if(coordination!=null&&!coordination.equals("")){
	    	String[] coor = coordination.split(",");
	    	longitude = Double.valueOf(coor[0]);
	    	latitude = Double.valueOf(coor[1]);
	    }
		Map<String,String> params = new HashMap<String, String>();
	        
		params.put("appKey",  RestUtil.strEncoder(sdkConstants.getAPI_KEY()));
		params.put("userId", userid);
		params.put("targetType", "1");
		params.put("targetId", shop.getShopNo());
		params.put("bizOwnerUid", shop.getMerchNo());
		params.put("name", shop.getShopName());
		params.put("displayName", shop.getShopName());
		params.put("logoUri", shop.getShopLogo());
		params.put("url", "");
		params.put("contact", "");
		params.put("phone", shop.getPhone());
		params.put("longitude", longitude+"");
		params.put("latitude", latitude+"");
		params.put("scopes", "0");
		params.put("categroies[0]", shop.getShopTypeNo());
		params.put("address", shop.getShopAddress());
		params.put("description", shop.getShopSummary());
		params.put("errDesc", "");
        String signature = Signature.computeSignature(params, sdkConstants.getAPI_SECRET());
        params.put("signature",  RestUtil.strEncoder(signature));
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : params.entrySet()) {
        	if(entry.getValue() == null) {
        		sb.append(entry.getKey()+"=&");
        	}else{
        		sb.append(entry.getKey()+"="+entry.getValue()+"&");
        	}
		}
        sb.deleteCharAt(sb.length()-1);
        
		String json = RestUtil.restPostForm(sdkConstants.getCLIENT_SYNCBUSINESS_URI(),sb.toString());
		System.out.println(json);
	}
}
