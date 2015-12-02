package com.zl.bgec.basicapi.common;

import java.io.Serializable;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.alibaba.fastjson.JSONObject;
import com.zl.bgec.basicapi.common.util.EDSGsonFilter;
import com.zl.bgec.basicapi.common.util.GsonUtil;
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","operations","roles","menus"})
public class ResultVo implements Serializable{
	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	private boolean result;
	private Object body;
	
	public ResultVo(boolean result){
		this.result=result;
		this.body="";
	}
	
	public ResultVo(boolean result,Object body){
		this.result=result;
		this.body=body;
	}
	
	public void put(String key,Object value){
		if(body!=null&&!(body instanceof JSONObject)&&!"".equals(body)){
			throw new RuntimeException("返回结果封装异常！");
		}else{
			if(body==null||"".equals(body)){
				body= new JSONObject();
			}
			((JSONObject)body).put(key, value);
		}
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this);
	}
	
	public String toStringSkip(Set<String> skips) {
		return GsonUtil.toJson(this, new EDSGsonFilter(true,skips));
	}
	
	public String toStringSkip(String... skips) {
		return GsonUtil.toJson(this, new EDSGsonFilter(true,skips));
	}
	
	public String toStringNoSkip(Set<String> skips) {
		return GsonUtil.toJson(this, new EDSGsonFilter(false,skips));
	}
	
	public String toStringNoSkip(String... skips) {
		return GsonUtil.toJson(this, new EDSGsonFilter(false,skips));
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}
	
}

