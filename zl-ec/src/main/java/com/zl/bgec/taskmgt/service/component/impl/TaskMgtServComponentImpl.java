package com.zl.bgec.taskmgt.service.component.impl;

import static org.quartz.CronExpression.isValidExpression;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.taskmgt.common.StringUtils;
import com.zl.bgec.taskmgt.common.TaskMgtUtils;
import com.zl.bgec.taskmgt.dal.ITaskMgtDal;
import com.zl.bgec.taskmgt.service.component.ITaskMgtServComponent;
import com.zl.bgec.taskmgt.service.model.TaskCronJobDo;

/**
 * 定时任务管理service组件实现类
 *
 */
@Service
public class TaskMgtServComponentImpl implements ITaskMgtServComponent {
	
	private static final Log log = LogFactory.getLog(TaskMgtServComponentImpl.class);
	
	@Resource
	private ITaskMgtDal dal;
	
	@Resource
	private TaskMgtSchedulerFactory schedulerFactory;
	
	/* (non-Javadoc)
	 * @see org.lazicats.ecos.taskmgt.service.ITaskMgtServComponent#getPagedTaskCronJobs(java.lang.String, int, int)
	 */
	@Override
	public PageFinder<TaskCronJobDo> getPagedTaskCronJobs(String jobName, int pageNo, int pageSize) throws Exception {
		return dal.getPagedTaskCronJobs(jobName, pageNo, pageSize);
	}
	
	/* (non-Javadoc)
	 * @see org.lazicats.ecos.taskmgt.service.ITaskMgtServComponent#getTaskCronJob(java.lang.String)
	 */
	@Override
	public TaskCronJobDo getTaskCronJob(String jobNo) throws Exception {
		return dal.getTaskCronJob(jobNo);
	}

	/* (non-Javadoc)
	 * @see org.lazicats.ecos.taskmgt.service.ITaskMgtServComponent#updateTask(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public int updateTask(String jobNo, String cronExpr, String jobDescription) throws Exception {
		int res = 0;
		if(!(StringUtils.isNotBlank(jobNo) && StringUtils.isNotBlank(cronExpr))) {
			res = -1;
			return res;
		}
		
		TaskCronJobDo job = dal.getTaskCronJob(jobNo);
		if(job == null || job.getAvailableFlag() != 1) {
			res = -2;
			return res;
		}
		
		JobKey jobKey = TaskMgtUtils.genCronJobKey(job);
		TriggerKey triggerKey = TaskMgtUtils.genCronTriggerKey(job);
		Scheduler scheduler = schedulerFactory.getScheduler();
		if(!(scheduler.checkExists(jobKey) && scheduler.checkExists(triggerKey))) {
			res = -3;
			return res;
		}

		cronExpr = cronExpr.trim();
		if(!isValidExpression(cronExpr)) {
			res = -4;
			return res;
		}
	
		try {
			CronTrigger trigger = (CronTrigger)scheduler.getTrigger(triggerKey);
			if(cronExpr.equalsIgnoreCase(trigger.getCronExpression())) {
				//do nothing
			} else { 
				CronTrigger newTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).forJob(jobKey)
						.withSchedule(CronScheduleBuilder.cronSchedule(cronExpr).withMisfireHandlingInstructionDoNothing()).build();
				scheduler.rescheduleJob(triggerKey, newTrigger);
			}
			
			job.setCronExpr(cronExpr);
			job.setJobDescription(jobDescription);
			dal.updateTaskCronJob(job);
			
			res = 1;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return res;
	}
	
	/* (non-Javadoc)
	 * @see org.lazicats.ecos.taskmgt.service.ITaskMgtServComponent#executeTask(java.lang.String)
	 */
	@Override
	public int executeTask(String jobNo) throws Exception {
		int res = 0;
		if(!StringUtils.isNotBlank(jobNo)) {
			res = -1;
			return res;
		}
		
		TaskCronJobDo job = dal.getTaskCronJob(jobNo);
		if(job == null || job.getAvailableFlag() != 1) {
			res = -2;
			return res;
		}
		
		JobKey jobKey = TaskMgtUtils.genCronJobKey(job);
		Scheduler scheduler = schedulerFactory.getScheduler();
		if(!scheduler.checkExists(jobKey)) {
			res = -3;
			return res;
		}
		try {
			scheduler.triggerJob(jobKey); //execute this job now.
			res = 1;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return res;
	}

	
}
