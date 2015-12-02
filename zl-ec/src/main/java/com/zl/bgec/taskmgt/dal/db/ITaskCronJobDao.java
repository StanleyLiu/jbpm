package com.zl.bgec.taskmgt.dal.db;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.IHibernateBaseDao;
import com.zl.bgec.taskmgt.dal.po.TaskCronJob;

/**
 * cron定时任务的dao接口
 *
 */
public interface ITaskCronJobDao extends IHibernateBaseDao<TaskCronJob> {

}
