package com.zl.bgec.taskmgt.dal;

import java.util.List;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.taskmgt.service.model.TaskCronJobDo;
import com.zl.bgec.taskmgt.service.model.TaskSimJobDo;

/**
 * 定时任务管理的dal接口
 *
 */
public interface ITaskMgtDal {

	/**
	 * 取回所有的cron定时任务
	 * @return
	 * @throws Exception
	 */
	public List<TaskCronJobDo> getTaskCronJobs() throws Exception;
	
	/**
	 * 根据参数，分页查询cron定时任务
	 * @param jobName 任务job的名称
	 * @param pageNo 分页号
	 * @param pageSize 分页大小
	 * @return
	 * @throws Exception
	 */
	public PageFinder<TaskCronJobDo> getPagedTaskCronJobs(String jobName, int pageNo, int pageSize) throws Exception;
	
	/**
	 * 根据job编号，取得一个cron定时任务的信息
	 * @param jobNo
	 * @return
	 * @throws Exception
	 */
	public TaskCronJobDo getTaskCronJob(String jobNo) throws Exception;
	
	/**
	 * 更新一个cron定时任务
	 * @param o
	 * @throws Exception
	 */
	public void updateTaskCronJob(TaskCronJobDo o) throws Exception;
	
	/**
	 * 取回所有的简单定时任务
	 * @return
	 * @throws Exception
	 */
	public List<TaskSimJobDo> getTaskSimJobs() throws Exception;
}
