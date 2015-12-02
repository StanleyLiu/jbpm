package com.zl.bgec.basicapi.order.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zl.bgec.basicapi.common.utils.SpringContextUtils;
import com.zl.bgec.basicapi.order.service.IOrderComponent;
import com.zl.bgec.basicapi.order.service.impl.OrderComponentImpl;

public class OrderCloseJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			IOrderComponent orderComponent = SpringContextUtils.getBean(OrderComponentImpl.class);
			orderComponent.closeFinishedOrder();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
