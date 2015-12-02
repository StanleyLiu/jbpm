package com.zl.bgec.basicapi.common.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestUtil {
	
	//执行调用接口post方法
	public static String restWan(String json,String restUrl){
		try {
			Client client = Client.create();
			WebResource r = client.resource(restUrl);
			json=strEncoder(json);
			
			ClientResponse response = r.post(ClientResponse.class, json);
			String result = response.getEntity(String.class);
			return strDecode(result);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	//执行调用接口post方法
	public static String restPostForm(String restUrl){
		try {
			Client client = Client.create();
			
			WebResource r = client.resource(restUrl);
			ClientResponse response = r.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class);
			String result = response.getEntity(String.class);
			return strDecode(result);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	public static String restWanForm(String restUrl,String param,String name,String value){
		try {
			Client client = Client.create();
			WebResource r = client.resource(restUrl);
			ClientResponse response = r.type(MediaType.APPLICATION_FORM_URLENCODED).header(name, value).post(ClientResponse.class,param);
			String result = response.getEntity(String.class);
			return strDecode(result);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	//执行调用接口put方法
	public static String restPostForm(String restUrl,String param){
		try {
			Client client = Client.create();
			WebResource r = client.resource(restUrl);
			ClientResponse response = r.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class,param);
			String result = response.getEntity(String.class);
			return strDecode(result);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * json参数中文编码
	 * @param json
	 * @return
	 */
	public static String strEncoder(String json){
		String newJson="";
		try {
			newJson=URLEncoder.encode(json,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newJson;
	}
	
	/**
	 * json参数中文编码
	 * @param json
	 * @return
	 */
	public static String strDecode(String json){
		String newJson="";
		try {
			newJson=URLDecoder.decode(json,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newJson;
	}
}
