package com.zl.bgec.taskmgt.dal.db.impl;

import org.springframework.stereotype.Repository;

import com.zl.bgec.basicapi.basecomponent.orm.hibernate.impl.HibernateBaseDao;
import com.zl.bgec.taskmgt.dal.db.ITaskSimJobDao;
import com.zl.bgec.taskmgt.dal.po.TaskSimJob;

/**
 * 简单定时任务dao的实现
 *
 */
@Repository
public class TaskSimJobDaoImpl extends HibernateBaseDao<TaskSimJob> implements ITaskSimJobDao {

}
