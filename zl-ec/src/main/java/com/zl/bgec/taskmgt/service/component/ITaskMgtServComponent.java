package com.zl.bgec.taskmgt.service.component;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.taskmgt.service.model.TaskCronJobDo;

/**
 * 定时任务管理service组件
 *
 */
public interface ITaskMgtServComponent {
	
	/**
	 * 根据参数，分页查询cron定时任务
	 * @param jobName 任务job的名称（模糊查询，为null或""时，则查询全部）
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
	 * 更新一个任务的设置
	 * @param jobNo 任务号
	 * @param cronExpr 任务的cron表达式
	 * @param jobDescription 任务描述
	 * @return 操作结果代码 (1:成功，0：失败，-1：传入参数有误，-2：找不到对应的Task定义或该Task已无效，-3：调度器中无对应的TaskJob或者Trigger, -4:传入的cron表达式无效
	 * @throws Exception
	 */
	public int updateTask(String jobNo, String cronExpr, String jobDescription) throws Exception;
	
	/**
	 * 根据编号，执行一个任务
	 * @param jobNo 任务号
	 * @return 操作结果代码(1:成功，0：失败，-1:传入参数有误，-2：找不到对应的Task定义或该Task已无效，-3：调度器中无对应的TaskJob）
	 * @throws Exception
	 */
	public int executeTask(String jobNo) throws Exception;
}
