package com.zl.bgec.basicapi.commodity.po;//

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
 * 服务商品
 * @author Stanley
 *
 */
@Entity
@Table(name = "tbl_commodity_service")
public class CommodityService {
	private String id;//id
	private String commoNo;//商品编号
	private String commoName;//商品名称
	private String type;//类型
	private String description;//描述
	private String voiceIntroduction;//语音介绍
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private Date createTime;//创建时间
	private String picUrl;//图片
	private String publishState;//上下架状态
	private String sellerNo;//商家编码
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
    @Column(name = "commo_name")
	public String getCommoName() {
		return commoName;
	}
	public void setCommoName(String commoName) {
		this.commoName = commoName;
	}
    @Column(name = "type")
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
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
