package com.zl.bgec.basicapi.promotion.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zl.bgec.basicapi.common.utils.SpringContextUtils;
import com.zl.bgec.basicapi.promotion.service.IPromotionService;
import com.zl.bgec.basicapi.promotion.service.impl.PromotionServiceImpl;

public class GroupProductInvalidJob implements Job{
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			IPromotionService promotionService = SpringContextUtils.getBean(PromotionServiceImpl.class);
			promotionService.groupProductInvalid();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
