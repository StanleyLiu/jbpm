<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.zl.bgec.basicapi.common.oauth2.UserInfo"%>
<%@ page import="com.zl.bgec.basicapi.common.oauth2.SdkConstants"%>
<%@ page import="com.zl.bgec.basicapi.common.utils.SpringContextUtils"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="com.zl.bgec.basicapi.common.util.GsonUtil"%>
<%@ page import="java.util.*"%>
<%@ page import="com.zl.bgec.basicapi.common.oauth2.Signature"%>

<%
	Logger log = Logger.getLogger(this.getClass());

	long beginTime =  System.currentTimeMillis();
	log.info("mainPage-beginTime="+beginTime);
	
	String sourceUrl = request.getParameter("sourceUrl");
	UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
	SdkConstants sdkConstants = SpringContextUtils.getBean(SdkConstants.class);
	log.info("mainPage-sourceUrl="+sourceUrl);
	log.info("mainPage-userInfo="+GsonUtil.toJson(userInfo));
	
	//新授权
	String signature = request.getParameter("signature");
	if(signature!=null&&!signature.equals("")){
		//request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		if(name != null)
			name=new String(name.getBytes("ISO8859-1"),"UTF-8");
		String appKey = request.getParameter("appKey");
		String timeStamp = request.getParameter("timeStamp");
		String randomNum = request.getParameter("randomNum");
			
		log.info("mainPage-id="+id);
		log.info("mainPage-name="+name);
		log.info("mainPage-signature="+signature);
		log.info("mainPage-appKey="+appKey);
		log.info("mainPage-timeStamp="+timeStamp);
		log.info("mainPage-randomNum="+randomNum);
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("name", name);
		map.put("appKey", appKey);
		map.put("timeStamp", timeStamp);
		map.put("randomNum", randomNum);
		String nsignature = Signature.computeSignature(map, sdkConstants.getAPI_SECRET());
		if(nsignature.equals(signature)){
			UserInfo user = new UserInfo();
			user.setId(Long.valueOf(id));
			user.setNickName(name);
			session.setAttribute("userInfo", user);
			response.sendRedirect(sourceUrl);
		}
		else{
			%>
			<p>签名失败</p>
			<%
		}
	}else{//旧授权
		if(userInfo!=null){
			response.sendRedirect(sourceUrl);
		}else{
			session.setAttribute("sourceUrl", sourceUrl);
			String url = sdkConstants.getSERVER_URI()+"/zl-ec/rest/service/front/oauth2";
			response.sendRedirect(url);
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
</body>
</html>