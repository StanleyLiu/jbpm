package com.zl.bgec.basicapi.commodity.vo;

import java.util.Date;
import java.util.List;

import com.zl.bgec.basicapi.commodity.po.CommodityCat;
import com.zl.bgec.basicapi.commodity.po.CommodityComment;
import com.zl.bgec.basicapi.commodity.po.CommodityPic;
import com.zl.bgec.basicapi.commodity.po.Product;
import com.zl.bgec.basicapi.common.PageVo;


public class CommodityVo extends PageVo{
	
	private String id;
    private String commoNo;// 商品编号
    private String commoName;// 商品名称
    private String commoPyName;// 商品拼音（只能输入字母）
    private String commoTitle;// 商品标题
    private String sellerNo;// 运营编号
    private String brandNo;// 品牌编号
    private String catNo;// 商品分类
    private String unit;// 商品单位（件、瓶、包、套）
    private String defaultPic;// 商品默认图片
    private String description;// 商品描述
    private byte deleteFlag;// 删除标识 0未删除 1已删除 default 0
    private Date createTime;// 创建时间
    private Date updateTime;// 更新时间
    private List<Product> products;
    private CommodityCensorVo commoCensorVo;// 商品审核状态
    private CommodityPublishVo commoPublishVo;// 商品上下架状态
    private String publishState;//上下架状态0默认 待上下架，1上架，2下架
	private CommodityCat cat;// 商品分类
    private Date publishTime;// 上架时间
    private List<String> commoNos;
    private byte isRecommend;//是否推荐0否，1推荐
    private double deliveryFee;//运费
    private String brandName;
    private String catName;//分类名称
    private byte censorStatus;
    private List<CommodityPic> pics;//商品图片
    private Date censorStartTime;
    private Date censorEndTime;
    private Integer sellNum;//销量
    private Integer collectNum;//被收藏数
    List<CommodityComment> commodityComments;
    private String isGroupBuy;//是否团购
    private String sortField;//排序字段
    private String sortType;// 排序类型
    private String price;//价格
    private int commentNum;//评价数
    private String isCollect;//是否收藏
    private String buyed;//团购商品-是否已参加团购
    private String orderNo;
    private String basicStatus;
    
    private double groupPrice;
    private int alreadyBuy;
    private int coupCount;
    private Date endTime;
    private int limitCount;
    
    private List<CommodityDetailPicVo> commoDetailPics;
    
	public String getBuyed() {
		return buyed;
	}
	public void setBuyed(String buyed) {
		this.buyed = buyed;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommoNo() {
		return commoNo;
	}
	public void setCommoNo(String commoNo) {
		this.commoNo = commoNo;
	}
	public String getCommoName() {
		return commoName;
	}
	public void setCommoName(String commoName) {
		this.commoName = commoName;
	}
	public String getCommoPyName() {
		return commoPyName;
	}
	public void setCommoPyName(String commoPyName) {
		this.commoPyName = commoPyName;
	}
	public String getCommoTitle() {
		return commoTitle;
	}
	public void setCommoTitle(String commoTitle) {
		this.commoTitle = commoTitle;
	}
	public String getSellerNo() {
		return sellerNo;
	}
	public void setSellerNo(String sellerNo) {
		this.sellerNo = sellerNo;
	}
	public String getBrandNo() {
		return brandNo;
	}
	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}
	public String getCatNo() {
		return catNo;
	}
	public void setCatNo(String catNo) {
		this.catNo = catNo;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDefaultPic() {
		return defaultPic;
	}
	public void setDefaultPic(String defaultPic) {
		this.defaultPic = defaultPic;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public CommodityCensorVo getCommoCensorVo() {
		return commoCensorVo;
	}
	public void setCommoCensorVo(CommodityCensorVo commoCensorVo) {
		this.commoCensorVo = commoCensorVo;
	}
	public CommodityPublishVo getCommoPublishVo() {
		return commoPublishVo;
	}
	public void setCommoPublishVo(CommodityPublishVo commoPublishVo) {
		this.commoPublishVo = commoPublishVo;
	}
	public List<String> getCommoNos() {
		return commoNos;
	}
	public void setCommoNos(List<String> commoNos) {
		this.commoNos = commoNos;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public byte getCensorStatus() {
		return censorStatus;
	}
	public void setCensorStatus(byte censorStatus) {
		this.censorStatus = censorStatus;
	}
	public Date getCensorStartTime() {
		return censorStartTime;
	}
	public void setCensorStartTime(Date censorStartTime) {
		this.censorStartTime = censorStartTime;
	}
	public Date getCensorEndTime() {
		return censorEndTime;
	}
	public void setCensorEndTime(Date censorEndTime) {
		this.censorEndTime = censorEndTime;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public String getPublishState() {
		return publishState;
	}
	public void setPublishState(String publishState) {
		this.publishState = publishState;
	}
	public byte getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(byte isRecommend) {
		this.isRecommend = isRecommend;
	}
	public double getDeliveryFee() {
		return deliveryFee;
	}
	public void setDeliveryFee(double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Integer getSellNum() {
		return sellNum;
	}
	public void setSellNum(Integer sellNum) {
		this.sellNum = sellNum;
	}
	public List<CommodityPic> getPics() {
		return pics;
	}
	public void setPics(List<CommodityPic> pics) {
		this.pics = pics;
	}
	public List<CommodityComment> getCommodityComments() {
		return commodityComments;
	}
	public void setCommodityComments(List<CommodityComment> commodityComments) {
		this.commodityComments = commodityComments;
	}
	public Integer getCollectNum() {
		return collectNum;
	}
	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}
	public CommodityCat getCat() {
		return cat;
	}
	public void setCat(CommodityCat cat) {
		this.cat = cat;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public String getIsGroupBuy() {
		return isGroupBuy;
	}
	public void setIsGroupBuy(String isGroupBuy) {
		this.isGroupBuy = isGroupBuy;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public String getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(String isCollect) {
		this.isCollect = isCollect;
	}
	public double getGroupPrice() {
		return groupPrice;
	}
	public void setGroupPrice(double groupPrice) {
		this.groupPrice = groupPrice;
	}
	public int getAlreadyBuy() {
		return alreadyBuy;
	}
	public void setAlreadyBuy(int alreadyBuy) {
		this.alreadyBuy = alreadyBuy;
	}
	public int getCoupCount() {
		return coupCount;
	}
	public void setCoupCount(int coupCount) {
		this.coupCount = coupCount;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getLimitCount() {
		return limitCount;
	}
	public void setLimitCount(int limitCount) {
		this.limitCount = limitCount;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getBasicStatus() {
		return basicStatus;
	}
	public void setBasicStatus(String basicStatus) {
		this.basicStatus = basicStatus;
	}
	public List<CommodityDetailPicVo> getCommoDetailPics() {
		return commoDetailPics;
	}
	public void setCommoDetailPics(List<CommodityDetailPicVo> commoDetailPics) {
		this.commoDetailPics = commoDetailPics;
	}
	
	
}
