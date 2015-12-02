package com.zl.bgec.basicapi.commodity.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_commodity")
public class Commodity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String commoNo;// 商品编号
	private String commoName;// 商品名称
	private String commoPyName;// 商品拼音（只能输入字母）
	private String commoTitle;// 商品标题
	private String sellerNo;// 卖家编号
	private String brandNo;// 品牌编号
	private String catNo;// 商品分类
	private String catName;// 商品分类
	private String unit;// 商品单位（件、瓶、包、套）
	private String defaultPic;// 商品默认图片的地址，默认图片是大图的地址
	private String description;// 商品描述
	private byte isRecommend; // 是否被推荐 0为不推荐 1为推荐 default 0
	private byte deleteFlag;// 删除标识 0未删除 1已删除 default 0
	private Date createTime;// 创建时间
	private Date updateTime;// 更新时间
	private String publishState;// 上下架状态0默认 待上下架，1上架，2下架
	//private CommodityBrand brand;// 商品品牌
	private CommodityCat cat;// 商品分类
	private Double deliveryFee;// 运费
	private List<Product> products = new ArrayList<Product>();
	private Integer sellNum;//销量
	private List<CommodityPic> pics = new ArrayList<CommodityPic>();
	private Date publishTime;//上架时间
	private List<CommodityComment> commodityComments;
    private Integer collectNum;//被收藏数
    private String price;//价格
    private int commentNum;//评价数
    private String isCollect;//是否收藏
    private List<CommodityDetailPic> commoDetailPics;
    
	public Commodity() {

	}

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "commo_no", unique = true, nullable = false, length = 32)
	public String getCommoNo() {
		return this.commoNo;
	}

	public void setCommoNo(String commoNo) {
		this.commoNo = commoNo;
	}

	@Column(name = "commo_name", nullable = false, length = 200)
	public String getCommoName() {
		return this.commoName;
	}

	public void setCommoName(String commoName) {
		this.commoName = commoName;
	}

	@Column(name = "commo_py_name", length = 200)
	public String getCommoPyName() {
		return this.commoPyName;
	}

	public void setCommoPyName(String commoPyName) {
		this.commoPyName = commoPyName;
	}

	@Column(name = "commo_title", length = 200)
	public String getCommoTitle() {
		return this.commoTitle;
	}

	public void setCommoTitle(String commoTitle) {
		this.commoTitle = commoTitle;
	}

	@Column(name = "seller_no", length = 32)
	public String getSellerNo() {
		return this.sellerNo;
	}

	public void setSellerNo(String sellerNo) {
		this.sellerNo = sellerNo;
	}

	@Column(name = "brand_no", nullable = false, length = 32)
	public String getBrandNo() {
		return this.brandNo;
	}

	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}

	@Column(name = "cat_no", nullable = false, length = 32)
	public String getCatNo() {
		return this.catNo;
	}

	public void setCatNo(String catNo) {
		this.catNo = catNo;
	}

	@Column(name = "cat_name")
	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	@Column(name = "unit", length = 10)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}


	@Column(name = "default_pic", length = 500)
	public String getDefaultPic() {
		return this.defaultPic;
	}

	public void setDefaultPic(String defaultPic) {
		this.defaultPic = defaultPic;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setIsRecommend(byte isRecommend) {
		this.isRecommend = isRecommend;
	}

	@Column(name = "is_recommend")
	public byte getIsRecommend() {
		return this.isRecommend;
	}

	@Column(name = "delete_flag")
	public byte getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	// @OneToOne(targetEntity = CommodityCensor.class)
	// @Fetch(FetchMode.JOIN)
	// @JoinColumn(name = "commo_no",unique=true, referencedColumnName =
	// "commo_no", insertable = false, updatable = false)
	// public CommodityCensor getCommoCensor() {
	// return this.commoCensor;
	// }
	//
	//
	// public void setCommoCensor(CommodityCensor commoCensor) {
	// this.commoCensor = commoCensor;
	// }

	// @OneToOne(targetEntity = CommodityPublish.class)
	// @Fetch(FetchMode.JOIN)
	// @JoinColumn(name = "commo_no",unique=true, referencedColumnName =
	// "commo_no", insertable = false, updatable = false)
	// public CommodityPublish getCommoPublish() {
	// return this.commoPublish;
	// }
	//
	//
	// public void setCommoPublish(CommodityPublish commoPublish) {
	// this.commoPublish = commoPublish;
	// }

	//
	// @Column(name = "commo_type", length = 4)
	// public byte getCommoType() {
	// return commoType;
	// }
	//
	//
	// public void setCommoType(byte commoType) {
	// this.commoType = commoType;
	// }

//	@ManyToOne(targetEntity = CommodityBrand.class)
//	@Fetch(FetchMode.JOIN)
//	@JoinColumn(name = "brand_no", referencedColumnName = "brand_no", insertable = false, updatable = false)
//	public CommodityBrand getBrand() {
//		return brand;
//	}
//
//	public void setBrand(CommodityBrand brand) {
//		this.brand = brand;
//	}
//
	@ManyToOne(targetEntity = CommodityCat.class)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "cat_no", referencedColumnName = "cat_no", insertable = false, updatable = false)
	public CommodityCat getCat() {
		return cat;
	}

	public void setCat(CommodityCat cat) {
		this.cat = cat;
	}

	@Transient
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Transient
	public List<CommodityPic> getPics() {
		return pics;
	}

	public void setPics(List<CommodityPic> pics) {
		this.pics = pics;
	}

	@Column(name = "publish_state", length = 19)
	public String getPublishState() {
		return publishState;
	}

	public void setPublishState(String publishState) {
		this.publishState = publishState;
	}

	@Column(name = "delivery_fee", length = 19)
	public Double getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(Double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "publish_time")
	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	@Column(name = "sell_num")
	public Integer getSellNum() {
		return sellNum;
	}

	public void setSellNum(Integer sellNum) {
		this.sellNum = sellNum;
	}
	@Transient
	public List<CommodityComment> getCommodityComments() {
		return commodityComments;
	}

	public void setCommodityComments(List<CommodityComment> commodityComments) {
		this.commodityComments = commodityComments;
	}
	@Transient
	public Integer getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}
	@Transient
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	@Transient
	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	@Transient
	public String getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(String isCollect) {
		this.isCollect = isCollect;
	}

	@Transient
	public List<CommodityDetailPic> getCommoDetailPics() {
		return commoDetailPics;
	}

	public void setCommoDetailPics(List<CommodityDetailPic> commoDetailPics) {
		this.commoDetailPics = commoDetailPics;
	}
	
	

}
