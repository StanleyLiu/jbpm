package com.zl.bgec.basicapi.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zl.bgec.basicapi.common.oauth2.SdkConstants;
import com.zl.bgec.basicapi.common.oauth2.Signature;
import com.zl.bgec.basicapi.common.util.RestUtil;

@Service
public class SendMessage {
	@Resource
	private SdkConstants sdkConstants;
	/**
	 * 
	 * @param userId 用户编号
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @param contentType 类型
	 * @param content 内容
	 * @return
	 */
	public String sendMessage(String userId,Long timestamp,Integer nonce,String contentType,String content){
		System.out.println("sendMessage-content="+content);
		Map<String,String> params = new HashMap<String, String>();
		params.put("appKey", sdkConstants.getAPI_KEY());
		params.put("userId", userId);
		params.put("timestamp", timestamp+"");
		params.put("nonce", nonce+"");
		params.put("contentType", contentType);
		params.put("content", content);
        String signature = null;
		try {
			content = URLEncoder.encode(content,"UTF-8");
			signature = URLEncoder.encode(Signature.computeSignature(params, sdkConstants.getAPI_SECRET()),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        params.put("signature", signature);
        String param = String.format("%s?appKey=%s&signature=%s&userId=%s&timestamp=%s&nonce=%s&contentType=%s&content=%s",
        		sdkConstants.getCLIENT_SENDMESSAGETOUSER_URI(),
        		sdkConstants.getAPI_KEY(),
        		signature,userId,timestamp,nonce,contentType,content);
        System.out.println(param);
		String responseJson = RestUtil.restPostForm(param);
		System.out.println(responseJson);
		
		return responseJson;
	}
	
}
