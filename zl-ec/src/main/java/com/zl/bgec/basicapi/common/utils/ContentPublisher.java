package com.zl.bgec.basicapi.common.utils;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 发布线程
 * 
 * @author ccl
 * 
 */
@Service
public class ContentPublisher {

	/**
	 * 主机列表
	 */
	protected List<WebServerConfig> host;
	/**
	 * html文件主机
	 */
	protected List<WebServerConfig> htmlHost;

	public void setHost(List<WebServerConfig> host) {
		this.host = host;
	}

	public void setHtmlHost(List<WebServerConfig> htmlHost) {
		this.htmlHost = htmlHost;
	}

	public ContentPublisher() {
		super();
	}

	/**
	 * 同步文件到资源服务器
	 * 
	 * @param localpath
	 *          本地文件绝对路径，如：d:/html
	 * @param htmlFiles
	 *          相对路径文件列表，如：/img/a.jpg
	 */
	public void publish(String localpath, List<String> htmlFiles) {
		for (int i = 0; i < host.size(); i++) {
			if (host.get(i).isSync()) {
				new HostPublisher(host.get(i), localpath, htmlFiles).start();
			}
		}
	}

	/**
	 * 同步文件到资源服务器
	 * 
	 * @param localpath
	 *          本地文件绝对路径，如：d:/html
	 * @param htmlFiles
	 *          相对路径文件列表，如：/img/a.jpg
	 */
	public void publishHtml(String localpath, List<String> htmlFiles) {
		if (htmlHost == null) {
			this.htmlHost = null;
		}
		for (int i = 0; i < htmlHost.size(); i++) {
			if (htmlHost.get(i).isSync()) {
				new HostPublisher(htmlHost.get(i), localpath, htmlFiles).start();
			}
		}
	}

	/**
	 * 发布器
	 * 
	 * @author ccl
	 * 
	 */
	private class HostPublisher extends Thread {
		private WebServerConfig server;
		/**
		 * 相对路径文件
		 */
		private List<String> htmlFiles;
		/**
		 * 本地目录
		 */
		private String localpath;

		public HostPublisher(WebServerConfig server, String localpath,
				List<String> htmlFiles) {
			super();
			this.server = server;
			this.localpath = localpath;
			this.htmlFiles = htmlFiles;
		}

		@Override
		public void run() {
			Ssh2FilePublisher filePublisher = null;
			try {
				filePublisher = new Ssh2FilePublisher(server.getHost(),
						server.getPort(), server.getUsername(), server.getPasswd(),
						server.getSitepath());
				filePublisher.getConnection();
				// 装载文件
				for (int i = 0; i < htmlFiles.size(); i++) {
					System.out.println("要上传的文件"+localpath + htmlFiles.get(i));
					System.out.println("上传到服务器上要保存的完整文件名路径"+server.getSitepath() + htmlFiles.get(i));
					filePublisher.upload(new File(localpath + htmlFiles.get(i)),
							server.getSitepath() + htmlFiles.get(i));
				}
				filePublisher.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			} catch (InternalError e) {
				e.printStackTrace();
			} finally {
				if (filePublisher != null) {
					filePublisher.closeConnection();
				}
			}
		}
	}
}
