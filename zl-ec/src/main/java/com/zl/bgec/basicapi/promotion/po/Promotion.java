package com.zl.bgec.basicapi.promotion.po;

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
 * 优惠活动信息
 * @author Stanley
 *
 */
@Entity
@Table(name="tbl_promotion")
public class Promotion implements Serializable{
	private String id;
	private String promotionName;//名称
	private String promotionNo;//编号
	private String promotionType;//类型1，优惠券，2团购
	private String promotionPicUrl;//活动图片
	private String description;//描述
	private Double discountAmount;//折扣金额/团购价
	private Double leastAmount;//最低消费额
	private Integer coupCount;//券数量
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private String refCommoNo;//团购商品编号
	private String shopNo;//店铺编号
	private String shopName;//店铺名称
	private String status;//状态1待上架，2已上架，3锁定
	private Date createTime;//创建时间
	private String lockFlag;//锁定优惠券，0未锁定，1已锁定
	private String isRecommend;//是否推荐，0未推荐，1推荐
	private Integer limitCount;//最大购买量
	private Integer alreadyCount;//已参团数量
	private String deleteFlag;//删除状态0未删除，1，已删除
	private String exeJob;//是否执行团购任务,0-否,1-是
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
	@Column(name = "promotion_name")
	public String getPromotionName() {
		return promotionName;
	}
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	@Column(name = "promotion_type")
	public String getPromotionType() {
		return promotionType;
	}
	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}
	@Column(name = "promotion_pic_url")
	public String getPromotionPicUrl() {
		return promotionPicUrl;
	}
	public void setPromotionPicUrl(String promotionPicUrl) {
		this.promotionPicUrl = promotionPicUrl;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "discount_amount")
	public Double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	@Column(name = "least_amount")
	public Double getLeastAmount() {
		return leastAmount;
	}
	public void setLeastAmount(Double leastAmount) {
		this.leastAmount = leastAmount;
	}
	@Column(name = "coup_count")
	public Integer getCoupCount() {
		return coupCount;
	}
	public void setCoupCount(Integer coupCount) {
		this.coupCount = coupCount;
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
	@Column(name = "ref_commo_no")
	public String getRefCommoNo() {
		return refCommoNo;
	}
	public void setRefCommoNo(String refCommoNo) {
		this.refCommoNo = refCommoNo;
	}
	@Column(name = "shop_no")
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "promotion_no")
	public String getPromotionNo() {
		return promotionNo;
	}
	public void setPromotionNo(String promotionNo) {
		this.promotionNo = promotionNo;
	}
	@Column(name = "lock_flag")
	public String getLockFlag() {
		return lockFlag;
	}
	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}
	@Column(name="is_recommend")
	public String getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}
	@Column(name = "shop_name")
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	@Column(name = "limit_count")
	public Integer getLimitCount() {
		return limitCount;
	}
	public void setLimitCount(Integer limitCount) {
		this.limitCount = limitCount;
	}
	@Column(name = "already_count")
	public Integer getAlreadyCount() {
		return alreadyCount;
	}
	public void setAlreadyCount(Integer alreadyCount) {
		this.alreadyCount = alreadyCount;
	}
	@Column(name = "delete_flag")
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	@Column(name = "exe_job")
	public String getExeJob() {
		return exeJob;
	}
	public void setExeJob(String exeJob) {
		this.exeJob = exeJob;
	}
	
	
	
}
