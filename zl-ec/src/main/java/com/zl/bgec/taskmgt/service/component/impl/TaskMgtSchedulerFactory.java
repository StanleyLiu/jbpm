package com.zl.bgec.taskmgt.service.component.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

/**
 * 调度器工厂类，应当在Spring中将该类配置为单例
 *
 */
@Component
public class TaskMgtSchedulerFactory {

	private static final Log log = LogFactory.getLog(TaskMgtSchedulerFactory.class);
	
	private volatile Scheduler scheduler;
	
	/**
	 * 获得scheduler实例
	 * @return
	 */
	public Scheduler getScheduler() {
		Scheduler s = scheduler;
		if(s == null) {
			synchronized(this) {
				s = scheduler;
				if(s == null) { //double check
					try {
						SchedulerFactory sf = new StdSchedulerFactory();
						s = scheduler = sf.getScheduler();
					} catch (Exception e) {
						log.error(e.getMessage(), e);
					}
				}
			}
		}
		return s;
	}
}
