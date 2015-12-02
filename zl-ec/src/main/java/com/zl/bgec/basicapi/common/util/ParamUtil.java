package com.zl.bgec.basicapi.common.util;

public class ParamUtil {
	
	public static final String[] COMMODITY_NO_SKIP=new String[]{"commoNo","commoName","sellerNo","unit","defaultPic","sellNum","isRecommend",
		"products","pics","createTime","publishState","publishTime","updateTime","price","commoPublish","commodityCensor.censorStatus","commodityPublish.publishStatus","cat","commodityCat.catName","commodityCat.catNo"};
	
	public static final String[] COMMODITY_BIG_INFO = new String[]{"packageInfo","warrantyInfo","description"};
	
	public static String[] getCommodityFilter(String[] params){
		String[] strs = new String[params.length];
		for(int i =0 ;i<params.length;i++){
			if(!params[i].contains(".")){
				strs[i]="commodity."+params[i];
			}else{
				strs[i]=params[i];
			}
		}
		return strs;
	}
	
	public static String[] getFilter(String parentName,String[] params){
		String[] strs = new String[params.length];
		for(int i =0 ;i<params.length;i++){
			if(!params[i].contains(".")){
				strs[i]=parentName+"."+params[i];
			}else{
				strs[i]=params[i];
			}
		}
		return strs;
	}
	
}
