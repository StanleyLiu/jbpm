package com.zl.bgec.basicapi.common.util;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

 

/**
 * @author 
 * 
 */
public class ContextUtil extends ContextLoaderListener {

	/**
	 * spring
	 */
	private static ApplicationContext applicationContext;

	/**
	 * servlet
	 */
	private static ServletContext servletContext;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		servletContext = event.getServletContext();
		applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	}

	/**
	 * @return the applicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @param applicationContext
	 *            the applicationContext to set
	 */
	public static void setApplicationContext(ApplicationContext applicationContext) {
		ContextUtil.applicationContext = applicationContext;
	}

	/**
	 * @return the servletContext
	 */
	public static ServletContext getServletContext() {
		return servletContext;
	}

	/**
	 * @param servletContext
	 *            the servletContext to set
	 */
	public static void setServletContext(ServletContext servletContext) {
		ContextUtil.servletContext = servletContext;
	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}
}
