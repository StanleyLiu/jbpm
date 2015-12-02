package com.zl.bgec.taskmgt.dal.db.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.taskmgt.dal.db.ITaskCronJobDao;
import com.zl.bgec.taskmgt.dal.po.TaskCronJob;

/**
 * cron定时任务dao的实现
 *
 */
@Repository
public class TaskCronJobDaoImpl extends HibernateBaseDao<TaskCronJob> implements ITaskCronJobDao {

}
