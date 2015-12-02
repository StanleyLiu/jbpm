package com.zl.bgec.taskmgt.common;

import java.util.ArrayList;
import java.util.List;

import com.zl.bgec.taskmgt.dal.po.TaskCronJob;
import com.zl.bgec.taskmgt.dal.po.TaskSimJob;
import com.zl.bgec.taskmgt.service.model.TaskCronJobDo;
import com.zl.bgec.taskmgt.service.model.TaskSimJobDo;

/**
 * 定时任务管理相关的对象转换器类
 *
 */
public class TaskMgtConvertor {

	/**
	 * 将TaskCronJob对象转为TaskCronJobDo对象
	 * @param po
	 * @return
	 */
	public static TaskCronJobDo convertTaskCronJobPo2TaskCronJobDo(TaskCronJob po) {
		if(po != null) {
			TaskCronJobDo o = new TaskCronJobDo();
			o.setJobNo(po.getJobNo());
			o.setJobName(po.getJobName());
			o.setJobClassName(po.getJobClassName());
			o.setJobDescription(po.getJobDescription());
			o.setCronExpr(po.getCronExpr());
			o.setAvailableFlag(po.getAvailableFlag());
			
			return o;
		} else {
			return null;
		}
	}
	
	/**
	 * 将TaskCronJob对象列表转为TaskCronJobDo对象列表
	 * @param poList
	 * @return
	 */
	public static List<TaskCronJobDo> convertTaskCronJobPoList2TaskCronJobDoList(List<TaskCronJob> poList) {
		if(poList != null) {
			List<TaskCronJobDo> oList = new ArrayList<TaskCronJobDo>(poList.size());
			for(TaskCronJob po : poList) {
				TaskCronJobDo o = convertTaskCronJobPo2TaskCronJobDo(po);
				if(o != null)
					oList.add(o);
			}
			
			return oList;
		} else {
			return new ArrayList<TaskCronJobDo>(0);
		}
	}
	
	/**
	 * 拷贝TaskCronJobDo对象的属性到TaskCronJob对象
	 * @param o
	 * @param po
	 */
	public static void copyTaskCronJobDo2TaskCronJobPo(TaskCronJobDo o, TaskCronJob po) {
		if(o != null && po != null) {
			po.setJobNo(o.getJobNo());
			po.setJobName(o.getJobName());
			po.setJobClassName(o.getJobClassName());
			po.setCronExpr(o.getCronExpr());
			po.setAvailableFlag(o.getAvailableFlag());
		}
	}
	
	/**
	 * 将TaskSimJob对象转为TaskSimJobDo对象
	 * @param po
	 * @return
	 */
	public static TaskSimJobDo convertTaskSimJobPo2TaskSimJobDo(TaskSimJob po) {
		if(po != null) {
			TaskSimJobDo o = new TaskSimJobDo();
			o.setJobNo(po.getJobNo());
			o.setJobName(po.getJobName());
			o.setJobClassName(po.getJobClassName());
			o.setJobDescription(po.getJobDescription());
			o.setIntervalInSec(po.getIntervalInSec());
			o.setAvailableFlag(po.getAvailableFlag());
			
			return o;
		} else {
			return null;
		}
	}
	
	/**
	 * 将TaskSimJob对象列表转为TaskSimJobDo对象列表
	 * @param poList
	 * @return
	 */
	public static List<TaskSimJobDo> convertTaskSimJobPoList2TaskSimJobDoList(List<TaskSimJob> poList) {
		if(poList != null) {
			List<TaskSimJobDo> oList = new ArrayList<TaskSimJobDo>(poList.size());
			for(TaskSimJob po : poList) {
				TaskSimJobDo o = convertTaskSimJobPo2TaskSimJobDo(po);
				if(o != null)
					oList.add(o);
			}
			
			return oList;
		} else {
			return new ArrayList<TaskSimJobDo>(0);
		}
	}
}
