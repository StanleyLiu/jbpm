package com.zl.bgec.basicapi.commodity.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
/**
 * 课程商品
 * @author Stanley
 *
 */
@Entity
@Table(name = "tbl_commodity_course")
public class CommodityCourse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//id
	private String commoNo;//商品编号
	private String commoName;//名称
	private String type;//类型
	private String description;//描述
	private String voiceIntroduction;//语音介绍
	private Double totalFee;//每人总费用
	private Integer totalNum;//限招总人数
	private Date firstStartTime;//首次上课时间
	private Date firstEndTime;//首次下课时间
	private String address;//上课地点
	private String teacher;//选择老师
	private String phone;//联系电话
	private Integer repeatNum;//重复次数
	private String isNeedConfirm;//是否需要确认
	private String isCreateCommunication;//是否创建交流圈
	private String picUrl;//图片
	private String publishState;//上下架状态
	private String sellerNo;//商家编号
	private String isRecommend;//是否推荐
	private String unit;//单位
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "commo_no")
	public String getCommoNo() {
		return commoNo;
	}
	public void setCommoNo(String commoNo) {
		this.commoNo = commoNo;
	}
	@Column(name = "commo_name", unique = true, nullable = false, length = 32)
	public String getCommoName() {
		return commoName;
	}
	public void setCommoName(String commoName) {
		this.commoName = commoName;
	}
	@Column(name = "type", nullable = false, length = 2)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "voice_introduction")
	public String getVoiceIntroduction() {
		return voiceIntroduction;
	}
	public void setVoiceIntroduction(String voiceIntroduction) {
		this.voiceIntroduction = voiceIntroduction;
	}
	@Column(name = "total_fee")
	public Double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}
	@Column(name = "total_num")
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "first_start_time")
	public Date getFirstStartTime() {
		return firstStartTime;
	}
	public void setFirstStartTime(Date firstStartTime) {
		this.firstStartTime = firstStartTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "first_end_time")
	public Date getFirstEndTime() {
		return firstEndTime;
	}
	public void setFirstEndTime(Date firstEndTime) {
		this.firstEndTime = firstEndTime;
	}
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "teacher")
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name = "repeat_num")
	public Integer getRepeatNum() {
		return repeatNum;
	}
	public void setRepeatNum(Integer repeatNum) {
		this.repeatNum = repeatNum;
	}
	@Column(name = "is_need_confirm")
	public String getIsNeedConfirm() {
		return isNeedConfirm;
	}
	public void setIsNeedConfirm(String isNeedConfirm) {
		this.isNeedConfirm = isNeedConfirm;
	}
	@Column(name = "is_create_communication")
	public String getIsCreateCommunication() {
		return isCreateCommunication;
	}
	public void setIsCreateCommunication(String isCreateCommunication) {
		this.isCreateCommunication = isCreateCommunication;
	}
	@Column(name = "pic_url")
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	@Column(name = "publish_state")
	public String getPublishState() {
		return publishState;
	}
	public void setPublishState(String publishState) {
		this.publishState = publishState;
	}
	@Column(name = "seller_no")
	public String getSellerNo() {
		return sellerNo;
	}
	public void setSellerNo(String sellerNo) {
		this.sellerNo = sellerNo;
	}
	@Column(name = "is_recommend")
	public String getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}
	@Column(name = "unit")
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
}
