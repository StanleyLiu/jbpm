package com.zl.bgec.basicapi.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.zl.bgec.basicapi.common.ResultVo;
import com.zl.bgec.basicapi.common.oauth2.UserInfo;
public class LoginFilter implements ContainerRequestFilter,
		ContainerResponseFilter {
	@Context
	private HttpServletRequest servletRequest;

	@Context
	private HttpServletResponse servletResponse;
	private Logger log = Logger.getLogger(LoginFilter.class);
	@Override
	public ContainerResponse filter(ContainerRequest reqeust,
			ContainerResponse response) {
		String uri = servletRequest.getRequestURI();
		HttpSession session = servletRequest.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		log.info("userInfo:"+userInfo);
		log.info("uri:"+uri);
		if (userInfo == null&&!uri.endsWith("service/front/oauth2")
				&&!uri.endsWith("service/front/gettoken")
				&&!uri.endsWith("service/front/result")
				&&!uri.endsWith("paycallback")
				&&!uri.endsWith("payfail")
				&&!uri.endsWith("test")
				&&!uri.endsWith("refundCallback")) {
			ResultVo result = new ResultVo(false);
			result.put("errorDes", "notLogin");
			Response resp = Response.ok(result,MediaType.APPLICATION_JSON).status(401).build();
			response.setEntity(result);
			response.setResponse(resp);
			throw new WebApplicationException(resp); // Throw new UnAuthorized
		}
		return response;
	}

	@Override
	public ContainerRequest filter(ContainerRequest request) {
		String uri = servletRequest.getRequestURI();
		HttpSession session = servletRequest.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		log.info("userInfo:"+userInfo);
		log.info("uri:"+uri);
		if (userInfo == null&&!uri.endsWith("service/front/oauth2")
				&&!uri.endsWith("service/front/gettoken")
				&&!uri.endsWith("service/front/result")
				&&!uri.endsWith("paycallback")
				&&!uri.endsWith("payfail")
				&&!uri.endsWith("test")
				&&!uri.endsWith("refundCallback")) {
			ResultVo result = new ResultVo(false);
			result.put("errorDes", "notLogin");
			Response resp = Response.ok(result,MediaType.APPLICATION_JSON).status(401).build();
			servletResponse.setHeader("errorDes",  "notLogin");
			throw new WebApplicationException(resp); // Throw new UnAuthorized
		}
		return request;
	}

}
