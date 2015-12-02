package com.zl.bgec.taskmgt.dal.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * tbl_task_sim_job表（简单定时任务表）的实体类
 *
 */
@Entity
@Table(name = "tbl_task_sim_job")
public class TaskSimJob implements Serializable {
	
	private static final long serialVersionUID = -7755427333292768699L;

	private String id; //id
	
	private String jobNo; //job编号
	
	private String jobName; //job名（只能由英文字母组成，命名方法请参照java类成员的命名规范，例如indexJob）
	
	private String jobClassName; //job关联的类的类名（该类必须存在）
	
	private String jobDescription; //job描述
	
	private int intervalInSec; //执行间隔（单位为秒），如果该值小于等于0，则任务只会在启动时执行一次，不会重复执行。
	
	private int availableFlag; //  可用标识（0，不可用，1可用）
	
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "JOB_NO")
	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	@Column(name = "JOB_NAME")
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@Column(name = "JOB_CLASS_NAME")
	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	@Column(name = "JOB_DESCRIPTION")
	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	@Column(name = "INTERVAL_IN_SEC")
	public int getIntervalInSec() {
		return intervalInSec;
	}

	public void setIntervalInSec(int intervalInSec) {
		this.intervalInSec = intervalInSec;
	}

	@Column(name = "AVAILABLE_FLAG")
	public int getAvailableFlag() {
		return availableFlag;
	}

	public void setAvailableFlag(int availableFlag) {
		this.availableFlag = availableFlag;
	}
}
