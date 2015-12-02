package com.zl.bgec.taskmgt.dal.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zl.bgec.basicapi.basecomponent.page.PageFinder;
import com.zl.bgec.taskmgt.common.StringUtils;
import com.zl.bgec.taskmgt.common.TaskMgtConvertor;
import com.zl.bgec.taskmgt.dal.ITaskMgtDal;
import com.zl.bgec.taskmgt.dal.db.ITaskCronJobDao;
import com.zl.bgec.taskmgt.dal.db.ITaskSimJobDao;
import com.zl.bgec.taskmgt.dal.po.TaskCronJob;
import com.zl.bgec.taskmgt.dal.po.TaskSimJob;
import com.zl.bgec.taskmgt.service.model.TaskCronJobDo;
import com.zl.bgec.taskmgt.service.model.TaskSimJobDo;

/**
 * 定时任务管理dal的实现类
 *
 */
@Repository
public class TaskMgtDalImpl implements ITaskMgtDal {
	
	@Resource 
	private ITaskCronJobDao cronJobDao;
	
	@Resource
	private ITaskSimJobDao simJobDao;
	
	/* (non-Javadoc)
	 * @see org.lazicats.ecos.taskmgt.dal.ITaskMgtDal#getTaskCronJobs()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<TaskCronJobDo> getTaskCronJobs() throws Exception {
		Criteria criteria = cronJobDao.createCriteria();
		criteria.addOrder(Order.asc("jobName"));
		
		List<TaskCronJob> list = cronJobDao.getListByCriteria(criteria);
		return TaskMgtConvertor.convertTaskCronJobPoList2TaskCronJobDoList(list);
	}

	/* (non-Javadoc)
	 * @see org.lazicats.ecos.taskmgt.dal.ITaskMgtDal#getPagedTaskCronJobs(java.lang.String, int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public PageFinder<TaskCronJobDo> getPagedTaskCronJobs(String jobName, int pageNo, int pageSize) throws Exception {
		Criteria criteria = cronJobDao.createCriteria();
		if(!StringUtils.isBlank(jobName)) {
			criteria.add(Restrictions.like("jobName", "%" + jobName + "%").ignoreCase());
		}
		criteria.addOrder(Order.asc("jobName"));
		
		PageFinder<TaskCronJob> page = cronJobDao.pagedByCriteria(criteria, pageNo, pageSize);
		PageFinder<TaskCronJobDo> retPage = new PageFinder<TaskCronJobDo>(page.getPageNo(), page.getPageSize(), page.getPageCount(),
																			TaskMgtConvertor.convertTaskCronJobPoList2TaskCronJobDoList(page.getData()));
		
		return retPage;
	}
	
	/* (non-Javadoc)
	 * @see org.lazicats.ecos.taskmgt.dal.ITaskMgtDal#getTaskCronJob(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public TaskCronJobDo getTaskCronJob(String jobNo) throws Exception {
		TaskCronJob po = cronJobDao.get("jobNo", jobNo);
		return TaskMgtConvertor.convertTaskCronJobPo2TaskCronJobDo(po);
	}
	
	/* (non-Javadoc)
	 * @see org.lazicats.ecos.taskmgt.dal.ITaskMgtDal#updateTaskCronJob(org.lazicats.ecos.taskmgt.model.TaskCronJobDo)
	 */
	@Override
	@Transactional
	public void updateTaskCronJob(TaskCronJobDo o) throws Exception {
		TaskCronJob po = cronJobDao.get("jobNo", o.getJobNo());
		TaskMgtConvertor.copyTaskCronJobDo2TaskCronJobPo(o, po);
	
		cronJobDao.update(po);
	}

	/* (non-Javadoc)
	 * @see org.lazicats.ecos.taskmgt.dal.ITaskMgtDal#getTaskSimJobs()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<TaskSimJobDo> getTaskSimJobs() throws Exception {
		Criteria criteria = simJobDao.createCriteria();
		criteria.addOrder(Order.asc("jobName"));
		
		List<TaskSimJob> list = simJobDao.getListByCriteria(criteria);
		return TaskMgtConvertor.convertTaskSimJobPoList2TaskSimJobDoList(list);
	}
}
