package com.zl.bgec.taskmgt.common;

import org.quartz.JobKey;
import org.quartz.TriggerKey;

import com.zl.bgec.taskmgt.service.model.TaskCronJobDo;
import com.zl.bgec.taskmgt.service.model.TaskSimJobDo;

/**
 * 任务管理模块的工具类
 *
 */
public class TaskMgtUtils {

	/**
	 * 产生JobKey
	 * @param job
	 * @return
	 */
	public static JobKey genCronJobKey(TaskCronJobDo job) {
		return new JobKey(job.getJobName().trim(), Constants.CRON_JOB_GROUP_NAME);
	}
	
	/**
	 * 产生TriggerKey
	 * @param job
	 * @return
	 */
	public static TriggerKey genCronTriggerKey(TaskCronJobDo job) {
		return new TriggerKey("trigger_" + job.getJobName().trim(), Constants.CRON_JOB_GROUP_NAME);
	}
	
	/**
	 * 产生JobKey
	 * @param job
	 * @return
	 */
	public static JobKey genSimJobKey(TaskSimJobDo job) {
		return new JobKey(job.getJobName().trim(), Constants.SIM_JOB_GROUP_NAME);
	}
	
	/**
	 * 产生TriggerKey
	 * @param job
	 * @return
	 */
	public static TriggerKey genSimTriggerKey(TaskSimJobDo job) {
		return new TriggerKey("trigger_" + job.getJobName().trim(), Constants.SIM_JOB_GROUP_NAME);
	}
	
	/**
	 * 判断是否两个trigger key是否相等
	 * @param tk1
	 * @param tk2
	 * @return
	 */
	public static boolean isTriggerKeyEqual(TriggerKey tk1, TriggerKey tk2) {
//		return tk1.getName().equals(tk2.getName()) && (tk1.getGroup() == tk2.getGroup() || (tk1.getGroup() != null && tk1.getGroup().equals(tk2.getGroup())));
		return tk1.getName().equals(tk2.getName()) && ((tk1.getGroup() == null && tk2.getGroup() == null) || (tk1.getGroup() != null && tk1.getGroup().equals(tk2.getGroup())));
	}
}
