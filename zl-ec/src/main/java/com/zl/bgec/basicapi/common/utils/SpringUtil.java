package com.zl.bgec.basicapi.common.utils;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * 工具类 - Spring
 * 
 * @ClassName: SpringUtil
 * @Description: TODO
 * @author: MyLife
 * @date 2012-11-26 下午4:50:42
 * 
 */
public class SpringUtil implements ApplicationContextAware, DisposableBean {
	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringUtil.applicationContext = applicationContext;
	}

	@Override
	public void destroy() throws Exception {
		applicationContext = null;
	}

	/**
	 * 获取applicationContext
	 * 
	 * @Title: getApplicationContext
	 * @Description: TODO
	 * @return
	 * @author: MyLife
	 * @date: 2012-11-26下午4:50:55
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 根据Bean名称获取实例
	 * 
	 * @Title: getBean
	 * @Description: TODO
	 * @param name
	 *            Bean注册名称
	 * @return
	 * @throws BeansException
	 * @author: MyLife
	 * @date: 2012-11-26下午4:51:13
	 */
	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}

	/**
	 * 根据接口或超类获取示例
	 * 
	 * @Title: getBean
	 * @Description: TODO
	 * @param requiredType
	 *            接口或超类
	 * @return
	 * @throws BeansException
	 * @author: MyLife
	 * @date: 2012-11-26下午4:53:35
	 */
	public static <T> T getBean(Class<T> requiredType) throws BeansException {
		return applicationContext.getBean(requiredType);
	}

	/**
	 * 获取容器的上下文
	 * 
	 * @Title: getServletContext
	 * @Description: TODO
	 * @return
	 * @throws BeansException
	 * @author: MyLife
	 * @date: 2012-11-26下午4:54:22
	 */
	public static ServletContext getServletContext() throws BeansException {
		WebApplicationContext wac = ContextLoader
				.getCurrentWebApplicationContext();
		if (null != wac) {
			return wac.getServletContext();
		}
		return null;
	}

	/**
	 * 当前工程的相对路径
	 * 
	 * @Title: getServletContextPath
	 * @Description: TODO
	 * @return
	 * @author: MyLife
	 * @date: 2012-11-26下午4:54:32
	 */
	public static String getServletContextPath() {
		File rootFile = null;
		try {
			String configFilePath = ContextLoader
					.getCurrentWebApplicationContext().getServletContext()
					.getRealPath("/")
					+ "/WEB-INF/jsp/";
			rootFile = new File(new File(configFilePath).getParent());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rootFile.getParent();
	}

	/**
	 * 当前工程的上下文的URL
	 * 
	 * @Title: getServletContextURL
	 * @Description: TODO
	 * @return
	 * @author: MyLife
	 * @date: 2012-11-26下午4:55:09
	 */
	public static String getServletContextURL() {
		WebApplicationContext wac = ContextLoader
				.getCurrentWebApplicationContext();
		return wac.getServletContext().getContextPath();
	}

	/**
	 * 模板文件的相对路径
	 * 
	 * @Title: getDirectoryForTemplateLoading
	 * @Description: TODO
	 * @return
	 * @author: MyLife
	 * @date: 2012-11-26下午4:55:16
	 */
	public static File getDirectoryForTemplateLoading() {
		File rootFile = new File(getServletContextPath());
		return rootFile;
	}
}
