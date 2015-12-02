package com.zl.bgec.taskmgt.service.model;

import java.io.Serializable;

/**
 * 简单定时任务DO类
 *
 */
public class TaskSimJobDo implements Serializable {
	
	private static final long serialVersionUID = 2244143812694045216L;

	private String jobNo; //job编号
	
	private String jobName; //job名（只能由英文字母组成，命名方法请参照java类成员的命名规范，例如indexJob）
	
	private String jobClassName; //job关联的类的类名（该类必须存在）
	
	private String jobDescription; //job描述
	
	private int intervalInSec; //执行间隔（单位为秒），如果该值小于等于0，则任务只会在启动时执行一次，不会重复执行。
	
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

	public int getIntervalInSec() {
		return intervalInSec;
	}

	public void setIntervalInSec(int intervalInSec) {
		this.intervalInSec = intervalInSec;
	}

	public int getAvailableFlag() {
		return availableFlag;
	}

	public void setAvailableFlag(int availableFlag) {
		this.availableFlag = availableFlag;
	}

	@Override
	public String toString() {
		return "TaskSimJobDo [jobNo=" + jobNo + ", jobName=" + jobName
				+ ", jobClassName=" + jobClassName + ", jobDescription="
				+ jobDescription + ", intervalInSec=" + intervalInSec
				+ ", availableFlag=" + availableFlag + "]";
	}
	
}
