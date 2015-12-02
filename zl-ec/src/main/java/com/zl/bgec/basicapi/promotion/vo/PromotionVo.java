package com.zl.bgec.basicapi.promotion.vo;

import java.util.Date;

import com.zl.bgec.basicapi.commodity.po.Commodity;
import com.zl.bgec.basicapi.commodity.po.Product;
import com.zl.bgec.basicapi.common.PageVo;

public class PromotionVo extends PageVo{

	private String id;
	private String promotionName;//名称
	private String promotionType;//类型1，优惠券，2赠品券
	private String promotionPicUrl;//活动图片
	private String description;//描述
	private Double discountAmount;//折扣金额
	private Double leastAmount;//最低消费额
	private Integer coupCount;//券数量
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private String refCommoNo;//赠品 关联商品编号
	private String shopNo;//店铺编号
	private String status;//状态1待上架，2已上架，3锁定
	private Date createTime;//创建时间
	private String promotionNo;//编号
	private String queryCondition;//查询条件
	private Integer fetchedCount;//已领取数
	private Integer usedCount;//已使用数
	private String lockFlag;//锁定优惠券，0未锁定，1已锁定
	private String isUsed;//0未使用，1已使用
	private String isRecommend;//是否推荐，0未推荐，1推荐
	private String memberNo;//会员编号
	private String shopName;//店铺名称
	private String isfetched;//
	private Integer limitCount;//最大购买量
	private Integer alreadyCount;//已参团数量
	private String deleteFlag;//删除状态0未删除，1，已删除
	private Product product;
	private String couponNumber;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPromotionName() {
		return promotionName;
	}
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	public String getPromotionType() {
		return promotionType;
	}
	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}
	public String getPromotionPicUrl() {
		return promotionPicUrl;
	}
	public void setPromotionPicUrl(String promotionPicUrl) {
		this.promotionPicUrl = promotionPicUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Double getLeastAmount() {
		return leastAmount;
	}
	public void setLeastAmount(Double leastAmount) {
		this.leastAmount = leastAmount;
	}
	public Integer getCoupCount() {
		return coupCount;
	}
	public void setCoupCount(Integer coupCount) {
		this.coupCount = coupCount;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getRefCommoNo() {
		return refCommoNo;
	}
	public void setRefCommoNo(String refCommoNo) {
		this.refCommoNo = refCommoNo;
	}
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getPromotionNo() {
		return promotionNo;
	}
	public void setPromotionNo(String promotionNo) {
		this.promotionNo = promotionNo;
	}
	public String getQueryCondition() {
		return queryCondition;
	}
	public void setQueryCondition(String queryCondition) {
		this.queryCondition = queryCondition;
	}
	public Integer getFetchedCount() {
		return fetchedCount;
	}
	public void setFetchedCount(Integer fetchedCount) {
		this.fetchedCount = fetchedCount;
	}
	public Integer getUsedCount() {
		return usedCount;
	}
	public void setUsedCount(Integer usedCount) {
		this.usedCount = usedCount;
	}
	public String getLockFlag() {
		return lockFlag;
	}
	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}
	public String getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	public String getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getIsFetched() {
		return isfetched;
	}
	public void setIsfetched(String isfetched) {
		this.isfetched = isfetched;
	}
	public Integer getLimitCount() {
		return limitCount;
	}
	public void setLimitCount(Integer limitCount) {
		this.limitCount = limitCount;
	}
	public Integer getAlreadyCount() {
		return alreadyCount;
	}
	public void setAlreadyCount(Integer alreadyCount) {
		this.alreadyCount = alreadyCount;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getCouponNumber() {
		return couponNumber;
	}
	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
	}
}
