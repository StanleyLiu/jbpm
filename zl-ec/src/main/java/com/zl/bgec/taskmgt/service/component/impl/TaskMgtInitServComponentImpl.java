package com.zl.bgec.taskmgt.service.component.impl;

import static org.quartz.CronExpression.isValidExpression;

import java.util.List;
import java.util.Set;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zl.bgec.taskmgt.common.Constants;
import com.zl.bgec.taskmgt.common.StringUtils;
import com.zl.bgec.taskmgt.common.TaskMgtUtils;
import com.zl.bgec.taskmgt.dal.ITaskMgtDal;
import com.zl.bgec.taskmgt.service.component.ITaskMgtInitServComponent;
import com.zl.bgec.taskmgt.service.model.TaskCronJobDo;
import com.zl.bgec.taskmgt.service.model.TaskSimJobDo;

/**
 * 任务管理初始化服务组件
 *
 */
@Service
public class TaskMgtInitServComponentImpl implements ITaskMgtInitServComponent {

	private static final Log log = LogFactory.getLog(TaskMgtInitServComponentImpl.class);
	
	@Resource
	private ITaskMgtDal dal;
	
	@Value("${cron_tasks.enabled}")
	private boolean isCronTasksEnabled;
	
	private boolean isSimTasksEnabled = true; //default true

	@Resource
	private TaskMgtSchedulerFactory schedulerFactory;

	/**
	 * 初始化
	 */
	//@PostConstruct
	public void init() {
		log.info(System.currentTimeMillis()+"TaskMgtInitServComponentImpl.init ================================================");
		if(isCronTasksEnabled || isSimTasksEnabled) {
			Scheduler scheduler = schedulerFactory.getScheduler();
			if(scheduler == null) {
				log.error("Scheduler is null!!!");
				return;
			}
			
			if(isCronTasksEnabled) {
				try {
					initCronJobs(scheduler); //init jobs with cron triggers
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			}
			
			if(isSimTasksEnabled) {
				try {
					initSimJobs(scheduler); //init jobs with simple triggers
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			}
			
			try {
				log.info("The scheduler is starting...");
				scheduler.start(); //start the scheduler
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}

		}
	}
	
	/**
	 * 初始化任务（基于cron触发器）
	 * @param scheduler
	 * @throws Exception
	 */
	private void initCronJobs(Scheduler scheduler) throws Exception {
		List<TaskCronJobDo> jobList = this.getTaskCronJobsFromDal();
		log.info("initCronJobs, jobList = " + jobList);
		if(jobList != null) {
			this.deleteUnwantedCronJobsFromScheduler(jobList, scheduler); //delete unwanted jobs from scheduler.
			
			for(TaskCronJobDo job : jobList) { //iterate the job list,and schedule job to scheduler.
				scheduleCronJob(job, scheduler);
			}
		}
	}
	
	/**
	 * 初始化任务（基于simple触发器）
	 * @param scheduler
	 * @throws Exception
	 */
	private void initSimJobs(Scheduler scheduler) throws Exception {
		List<TaskSimJobDo> jobList = this.getTaskSimJobsFromDal();
		log.info("initSimJobs, jobList = " + jobList);
		if(jobList != null) {
			this.deleteUnwantedSimJobsFromScheduler(jobList, scheduler); //delete unwanted jobs from scheduler.
			
			for(TaskSimJobDo job : jobList) { //iterate the job list,and schedule job to scheduler.
				scheduleSimJob(job, scheduler);
			}
		}
	}
	
	/**
	 * 从dal获得所有的cron任务
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	private List<TaskCronJobDo> getTaskCronJobsFromDal() throws Exception {
		return dal.getTaskCronJobs();
	}
	
	/**
	 * 安排任务(基于cron)
	 * @param job
	 * @param scheduler
	 */
	private void scheduleCronJob(TaskCronJobDo job, Scheduler scheduler) {
		if(job != null && !StringUtils.isBlank(job.getJobName()) && !StringUtils.isBlank(job.getJobClassName()) && !StringUtils.isBlank(job.getCronExpr())
				&& scheduler != null) {
			
			if(job.getAvailableFlag() != 1) { 
				return;
			}
			
			try {
				JobKey jobKey = TaskMgtUtils.genCronJobKey(job);
				
				if(!scheduler.checkExists(jobKey)) { //This job doesn't exist, then add it to scheduler.
					log.info("Add new cron job, job = " + job);
					this.newJobAndNewCronTrigger(job, scheduler, jobKey);
				} else {
					log.info("Update cron job, job = " + job);
					this.updateCronTriggerOfJob(job, scheduler, jobKey);
				}
				
			} catch (Exception e) {
				log.error("job="+job);
				log.error(e.getMessage(), e);
			}
			
		} else {
			log.info("Method scheduleCronJob arguments are invalid.");
			log.info("job="+job);
			log.info("scheduler="+scheduler);
		}
	}
	
	
	/**
	 * 从scheduler中删除那些已经不再需要的基于cron调度的job及其相关trigger。所谓的"不再需要"，是指那些目前还在scheduler中存在，但已经不存在于jobDoList中的。
	 * @param jobDoList
	 * @param scheduler
	 * @return
	 * @throws SchedulerException
	 */
	private void deleteUnwantedCronJobsFromScheduler(List<TaskCronJobDo> jobDoList, Scheduler scheduler) {
		Set<JobKey> jobKeys = null;
		try {
			jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(Constants.CRON_JOB_GROUP_NAME)); //get jobkeys with the special group name
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		if(jobKeys != null && jobDoList != null) {
			for(JobKey jobKey : jobKeys) {
				try {
					JobDetail jobDetail = scheduler.getJobDetail(jobKey);
					String jobName = jobKey.getName();
					String jobClassName = jobDetail.getJobClass().getName();
					
					boolean hit = false;
					for(TaskCronJobDo jobDo : jobDoList) {
						if(jobName.equals(jobDo.getJobName()) && jobClassName.equals(jobDo.getJobClassName())
								&& jobDo.getAvailableFlag() == 1) {
							hit = true;
							break;
						}
					}
					
					if(!hit) {
						scheduler.deleteJob(jobKey); //delete this job from scheduler
					}
					
				} catch (Exception e) {
					log.error("jobKey=" + jobKey);
					log.error(e.getMessage(), e);
				}
			}
		}
		
	}
	
	/**
	 * 新建job和trigger到scheduler(基于cron)
	 * @param job
	 * @param scheduler
	 * @param jobKey
	 * @throws SchedulerException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void newJobAndNewCronTrigger(TaskCronJobDo job, Scheduler scheduler, JobKey jobKey) throws SchedulerException, ClassNotFoundException {
		TriggerKey triggerKey = TaskMgtUtils.genCronTriggerKey(job);

		String cronExpr = job.getCronExpr();
		if(!isValidExpression(cronExpr)) 
			return;
			
		Class jobClass = Class.forName(job.getJobClassName().trim()); // get a Class object by string class name of job;
		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey).withDescription(job.getJobDescription()).build();
		
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).forJob(jobKey)
				.withSchedule(CronScheduleBuilder.cronSchedule(cronExpr).withMisfireHandlingInstructionDoNothing()).build();
		
		scheduler.scheduleJob(jobDetail, trigger);
	}
	
	/**
	 * 更新job的trigger(基于cron)
	 * @param job
	 * @param scheduler
	 * @param jobKey
	 * @throws SchedulerException
	 */
	private void updateCronTriggerOfJob(TaskCronJobDo job, Scheduler scheduler, JobKey jobKey) throws SchedulerException {
		TriggerKey triggerKey = TaskMgtUtils.genCronTriggerKey(job);
		String cronExpr = job.getCronExpr().trim();
			
		List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
		
		for(int i = 0 ; triggers != null && i < triggers.size(); i++) {
			Trigger trigger = triggers.get(i);
			TriggerKey curTriggerKey = trigger.getKey();
			
			if(TaskMgtUtils.isTriggerKeyEqual(triggerKey, curTriggerKey)) {
				if(trigger instanceof CronTrigger && cronExpr.equalsIgnoreCase(((CronTrigger)trigger).getCronExpression()) ) {
					// Don't need to do anything.
				} else {
					if(isValidExpression(job.getCronExpr())) { // Cron expression is valid, build a new trigger and replace the old one.
						CronTrigger newTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).forJob(jobKey)
								.withSchedule(CronScheduleBuilder.cronSchedule(cronExpr).withMisfireHandlingInstructionDoNothing()).build();
						scheduler.rescheduleJob(curTriggerKey, newTrigger);
					}
				}
				
			} else { //different trigger key
				scheduler.unscheduleJob(curTriggerKey); //The trigger key is illegal, unschedule this trigger
			}
			
		}
		
	}
	
	/**
	 * 从dal获得所有的simple任务
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	private List<TaskSimJobDo> getTaskSimJobsFromDal() throws Exception {
		return dal.getTaskSimJobs();
	}
	
	/**
	 * 安排任务(基于simple触发器)
	 * @param job
	 * @param scheduler
	 */
	private void scheduleSimJob(TaskSimJobDo job, Scheduler scheduler) {
		if(job != null && !StringUtils.isBlank(job.getJobName()) && !StringUtils.isBlank(job.getJobClassName()) && scheduler != null) {
			
			if(job.getAvailableFlag() != 1) { 
				return;
			}
			
			try {
				JobKey jobKey = TaskMgtUtils.genSimJobKey(job);
				
				if(!scheduler.checkExists(jobKey)) { //This job doesn't exist, then add it to scheduler.
					log.info("Add new simple job, job = " + job);
					this.newJobAndNewSimTrigger(job, scheduler, jobKey);
				} else {
					log.info("Update simple job, job = " + job);
					this.updateSimTriggerOfJob(job, scheduler, jobKey);
				}
				
			} catch (Exception e) {
				log.error("job="+job);
				log.error(e.getMessage(), e);
			}
			
		} else {
			log.info("Method scheduleSimJob arguments are invalid.");
			log.info("job="+job);
			log.info("scheduler="+scheduler);
		}
	}
	
	/**
	 * 从scheduler中删除那些已经不再需要的基于simple调度的job及其相关trigger。所谓的"不再需要"，是指那些目前还在scheduler中存在，但已经不存在于jobDoList中的。
	 * @param jobDoList
	 * @param scheduler
	 * @return
	 * @throws SchedulerException
	 */
	private void deleteUnwantedSimJobsFromScheduler(List<TaskSimJobDo> jobDoList, Scheduler scheduler) {
		Set<JobKey> jobKeys = null;
		try {
			jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(Constants.SIM_JOB_GROUP_NAME)); //get jobkeys with the special group name
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		if(jobKeys != null && jobDoList != null) {
			for(JobKey jobKey : jobKeys) {
				try {
					JobDetail jobDetail = scheduler.getJobDetail(jobKey);
					String jobName = jobKey.getName();
					String jobClassName = jobDetail.getJobClass().getName();
					
					boolean hit = false;
					for(TaskSimJobDo jobDo : jobDoList) {
						if(jobName.equals(jobDo.getJobName()) && jobClassName.equals(jobDo.getJobClassName())
								&& jobDo.getAvailableFlag() == 1) {
							hit = true;
							break;
						}
					}
					
					if(!hit) {
						scheduler.deleteJob(jobKey); //delete this job from scheduler
					}
					
				} catch (Exception e) {
					log.error("jobKey=" + jobKey);
					log.error(e.getMessage(), e);
				}
			}
		}
		
	}
	
	/**
	 * 新建job和trigger到scheduler(基于simple触发器)
	 * @param job
	 * @param scheduler
	 * @param jobKey
	 * @throws SchedulerException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void newJobAndNewSimTrigger(TaskSimJobDo job, Scheduler scheduler, JobKey jobKey) throws SchedulerException, ClassNotFoundException {
		TriggerKey triggerKey = TaskMgtUtils.genSimTriggerKey(job);

		
		Class jobClass = Class.forName(job.getJobClassName().trim()); // get a Class object by string class name of job;
		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey).withDescription(job.getJobDescription()).build();
		
		SimpleTrigger trigger = null;		
		int intervalInSec = job.getIntervalInSec();
		if(intervalInSec > 0) {
			trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).forJob(jobKey).startNow()
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(intervalInSec)).build(); //repeat the job every interval seconds.
			
		} else {
			trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).forJob(jobKey).startNow()
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(1)).build(); //totally execute the job once.
		}

		scheduler.scheduleJob(jobDetail, trigger);
	}
	
	/**
	 * 更新job的trigger(基于simple触发器)
	 * @param job
	 * @param scheduler
	 * @param jobKey
	 * @throws SchedulerException
	 */
	private void updateSimTriggerOfJob(TaskSimJobDo job, Scheduler scheduler, JobKey jobKey) throws SchedulerException {
		TriggerKey triggerKey = TaskMgtUtils.genSimTriggerKey(job);
		int intervalInSec = job.getIntervalInSec();	
		
		List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
		
		for(int i = 0 ; triggers != null && i < triggers.size(); i++) {
			Trigger trigger = triggers.get(i);
			TriggerKey curTriggerKey = trigger.getKey();
			
			if(TaskMgtUtils.isTriggerKeyEqual(triggerKey, curTriggerKey)) {
//				if(trigger instanceof SimpleTrigger && intervalInSec == ((SimpleTrigger)trigger).getRepeatInterval() / 1000 ) {
//					log.info("...... trigger key:" + trigger.getKey() + " , next fire time = " + trigger.getNextFireTime());
					// Don't need to do anything.
//				} else {
					SimpleTrigger newTrigger = null;
					if(intervalInSec > 0) {
						newTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).forJob(jobKey).startNow()
							.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(intervalInSec)).build(); //repeat the job every interval seconds.
					} else {
						newTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).forJob(jobKey).startNow()
							.withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(1)).build(); //totally execute the job once.
					}
					scheduler.rescheduleJob(curTriggerKey, newTrigger);
//				}
				
			} else { //different trigger key
				scheduler.unscheduleJob(curTriggerKey); //The trigger key is illegal, unschedule this trigger
			}
			
		}
		
	}
	
	/**
	 * bean销毁前，清理资源
	 */
	@SuppressWarnings("unused")
	@PreDestroy
	private void destroy() {
		log.info("TaskMgtInitServComponentImpl is destroying...");
		Scheduler scheduler = schedulerFactory.getScheduler();
		if(scheduler != null) {
			try {
				scheduler.shutdown(true);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
	}
}
