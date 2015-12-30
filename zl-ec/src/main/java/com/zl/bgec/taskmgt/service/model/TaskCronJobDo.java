package com.zl.bgec.taskmgt.service.model;

import java.io.Serializable;

/**
 * cron定时任务DO类
 *
 */
public class TaskCronJobDo implements Serializable {

	private static final long serialVersionUID = 7805282941330693610L;

	private String jobNo; //job编号
	
	private String jobName; //job名（只能由英文字母组成，命名方法请参照java类成员的命名规范，例如indexJob）
	
	private String jobClassName; //job关联的类的类名（该类必须存在）
	
	private String jobDescription; //job描述
	
	private String cronExpr; //cron表达式（必须是有效的cron表达式，而且暂时不支持C字符，即不支持关联calendar）
	
	private int availableFlag; //  可用标识（0，不可用，1可用）

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getCronExpr() {
		return cronExpr;
	}

	public void setCronExpr(String cronExpr) {
		this.cronExpr = cronExpr;
	}

	public int getAvailableFlag() {
		return availableFlag;
	}

	public void setAvailableFlag(int availableFlag) {
		this.availableFlag = availableFlag;
	}

	@Override
	public String toString() {
		
		return "TaskCronJobDo [jobNo=" + jobNo + ", jobName=" + jobName
				+ ", jobClassName=" + jobClassName + ", jobDescription="
				+ jobDescription + ", cronExpr=" + cronExpr
				+ ", availableFlag=" + availableFlag + "]";
	}

	
}
