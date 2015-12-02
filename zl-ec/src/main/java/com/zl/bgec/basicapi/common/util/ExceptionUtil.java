package com.zl.bgec.basicapi.common.util;

import java.util.Collection;

import com.zl.bgec.basicapi.common.exception.ParamException;

public class ExceptionUtil {
	/**
	 * 验证参数是否为null
	 * @param objs
	 * @throws Exception
	 */
	public static void checkParamStringNull(Object... objs) throws Exception{
		for(Object obj:objs){
			if(obj==null){
				throw new ParamException("00000000", "输入参数有误！");
			}
		}
	}
	/**
	 * 验证参数是否为null或者“”
	 * @param objs
	 * @throws Exception
	 */
	public static void checkParamStringNullAndEmpty(String... objs) throws Exception{
		for(String str:objs){
			if(str==null||"".equals(str.trim())){
				throw new ParamException("00000000", "输入参数有误！");
			}
		}
	}
	/**
	 * 判断集合是否为空或者长度为0
	 * @param collection
	 * @throws Exception
	 */
	public static void checkParamCollection(Collection collection) throws Exception{
		if(collection==null||collection.size()==0){
			throw new ParamException("00000000", "输入参数有误！");
		}
	}
}
