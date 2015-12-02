package com.zl.bgec.basicapi.common.oauth2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.zl.bgec.basicapi.common.ResultVo;
import com.zl.bgec.basicapi.common.util.GsonUtil;
import com.zl.bgec.basicapi.common.util.RestUtil;

@Component
@Path("/service/front")
public class LoginRest {
	@Resource
	private AccessTokenManager tokenManager;
	@Resource
	private SdkConstants sdkConstants;

	private Logger log = Logger.getLogger(LoginRest.class);

	@Path("/oauth2")
	@GET
	public String oauth(String json,@Context HttpHeaders header,@Context HttpServletResponse response,@Context HttpServletRequest request) throws IOException{
		String state = request.getSession().getId();
		log.info("sessionId:"+state);
		HttpSession session = request.getSession();
		String sourceUrl = (String) session.getAttribute("sourceUrl");
		if(sourceUrl!=null&&!sourceUrl.equals("")){
			request.getSession().setAttribute("sourceUrl", sourceUrl);
		}

		log.info("token:"+tokenManager.getAccessToken(state));
		if(tokenManager.getAccessToken(state) == null) {
			String uri;
			try {
				uri = String.format("%s?response_type=code&client_id=%s&redirect_uri=%s&scope=basic&state=%s#oauth2_redirect",
						sdkConstants.getAPI_AUTHORIZE_SERVICE_URI(),
						sdkConstants.API_KEY,
						URLEncoder.encode(sdkConstants.getCLIENT_REDIRECT_URI(), "UTF-8"),
						state
						);
				log.info("oauth2-uri:"+uri);
				response.sendRedirect(uri); 
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			response.sendRedirect(sdkConstants.getSERVER_URI()+"/zl-ec/rest/service/front/result");
		}

		return new ResultVo(true).toString();
	}

	@Path("/gettoken")
	@GET
	public void getToken(String json,@Context HttpHeaders header,@Context HttpServletResponse response,@Context HttpServletRequest request)throws Exception{
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		log.info("code:"+code+",state:"+state);
		String url = sdkConstants.getAPI_TOKEN_SERVICE_URI();
		String formData = String.format("grant_type=authorization_code&code=%s&redirect_uri=%s&client_id=%s", code,
				sdkConstants.getCLIENT_REDIRECT_URI(),
				sdkConstants.getAPI_KEY());
		log.info("获取token请求url:"+url);
		String credential = String.format("%s:%s", sdkConstants.getAPI_KEY(), sdkConstants.getAPI_SECRET());
		String responseString = RestUtil.restWanForm(url,formData, "Authorization", String.format("Basic %s", Base64.encodeBase64String(credential.getBytes("UTF-8"))));
		OAuth2AccessTokenResponse tokenResponse = GsonUtil.fromJson(
				responseString, OAuth2AccessTokenResponse.class);
		log.info("responseString:"+responseString);
		String sourceUrl = (String) request.getSession().getAttribute("sourceUrl");
		log.info("getToken接口，sourceUrl："+sourceUrl);
		this.tokenManager
		.setAccessToken(state, tokenResponse.getAccess_token());
		response.sendRedirect(sdkConstants.getSERVER_URI()+"/zl-ec/rest/service/front/result");
	}
	@Path("/result")
	@GET
	public void result(String json,@Context HttpHeaders header,@Context HttpServletResponse response,@Context HttpServletRequest request)throws Exception{
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		if(userInfo==null){
			String sessionId = request.getSession().getId();
			String token = this.tokenManager.getAccessToken(sessionId);
			String url = String.format("%s/getUserInfo", sdkConstants.getAPI_OAUTH2API_URI());
			if(token==null){
				response.sendRedirect(sdkConstants.getSERVER_URI()+"/zl-ec/rest/service/front/gettoken");
			}
			log.info("url:"+url);
			log.info("token:"+token);
			String responseString = RestUtil.restWanForm(url,"", "Authorization", String.format("Bearer %s", Base64.encodeBase64String(token.getBytes("UTF-8"))));
			log.info("responseString:"+responseString);
			GetUserInfoRestResponse userInfoRestResponse = GsonUtil.fromJson(responseString, GetUserInfoRestResponse.class);

			session.setAttribute("userInfo", userInfoRestResponse.getResponse());
			//	 		session.setMaxInactiveInterval(7200);
		}
		String sourceUrl = (String) session.getAttribute("sourceUrl");
		log.info("获取userInfo接口，sourceUrl："+sourceUrl);
		response.sendRedirect(sourceUrl);
	}

}
