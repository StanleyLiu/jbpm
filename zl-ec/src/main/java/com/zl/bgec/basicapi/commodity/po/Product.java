package com.zl.bgec.basicapi.commodity.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_product")
public class Product implements java.io.Serializable{

    private String id;
    private String prodNo; // 货品编号
    private String prodName;// 货品名称：由 商品名称+上规格值 组成
    private byte prodStatus; //货品状态，0启用，1禁用，2作废
    private String commoNo;// 商品编号
    private String sellerNo; // 卖家编号
    private String defaultPic;// 货品默认图片的地址，默认图片是大图的地址
    private byte deleteFlag;// 删除标识 0未删除 1已删除 default 0
    private Date createTime;// 货品创建时间
    private Date updateTime;// 货品更新时间
    private double freightage;//货品运费 liuxiaolong
    private double price;
    private String model;//货品型号
    private Integer stock;//库存
    private Integer stockPreemption;//预占库存
    private String shopNo;//店铺编号
    private Integer sellNum;//销量
    private String isGroupbuy;//是否团购商品，1普通商品，2团购商品
	private String prodBrand;//品牌
	private String prodProductionAddress;//产地
	private String storage;//储存方法
	private String warranty;//保质期
	private String howToEat;//食用方法
	private String produceDate;//生产日期
	private String tip;//温馨提示
	private Integer sort;//排序字段
    public Product() {

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

    
    @Column(name = "prod_no", unique = true, nullable = false, length = 32)
    public String getProdNo() {
        return this.prodNo;
    }

    
    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    
    @Column(name = "prod_name", nullable = false, length = 200)
    public String getProdName() {
        return this.prodName;
    }

    
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
    
    
    @Column(name = "prod_status", nullable = false)
    public byte getProdStatus() {
        return this.prodStatus;
    }

    
    public void setProdStatus(byte prodStatus) {
        this.prodStatus = prodStatus;
    }

    
    @Column(name = "commo_no", nullable = false, length = 32)
    public String getCommoNo() {
        return this.commoNo;
    }

    
    public void setCommoNo(String commoNo) {
        this.commoNo = commoNo;
    }

    
    @Column(name = "seller_no", length = 32)
    public String getSellerNo() {
        return this.sellerNo;
    }

    
    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo;
    }

    @Column(name = "default_pic", length = 500)
    public String getDefaultPic() {
        return this.defaultPic;
    }

    
    public void setDefaultPic(String defaultPic) {
        this.defaultPic = defaultPic;
    }

    @Column(name = "delete_flag", nullable = false)
    public byte getDeleteFlag() {
        return this.deleteFlag;
    }

    
    public void setDeleteFlag(byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", nullable = false, length = 19)
    public Date getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    
    @Column(name = "freightage")
	public double getFreightage() {
		return freightage;
	}
    
	public void setFreightage(double freightage) {
		this.freightage = freightage;
	}
    
	@Column(name = "price")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name = "model")
	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "stock")
	public Integer getStock() {
		return stock;
	}


	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Column(name = "stock_preemption")
	public Integer getStockPreemption() {
		return stockPreemption;
	}


	public void setStockPreemption(Integer stockPreemption) {
		this.stockPreemption = stockPreemption;
	}

	@Column(name = "shop_no")
	public String getShopNo() {
		return shopNo;
	}


	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	@Column(name = "sell_num")
	public Integer getSellNum() {
		return sellNum;
	}


	public void setSellNum(Integer sellNum) {
		this.sellNum = sellNum;
	}

	@Column(name = "prod_brand")
	public String getProdBrand() {
		return prodBrand;
	}


	public void setProdBrand(String prodBrand) {
		this.prodBrand = prodBrand;
	}

	@Column(name = "prod_production_address")
	public String getProdProductionAddress() {
		return prodProductionAddress;
	}


	public void setProdProductionAddress(String prodProductionAddress) {
		this.prodProductionAddress = prodProductionAddress;
	}

	@Column(name = "storage")
	public String getStorage() {
		return storage;
	}


	public void setStorage(String storage) {
		this.storage = storage;
	}

	@Column(name = "warranty")
	public String getWarranty() {
		return warranty;
	}


	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	@Column(name = "how_to_eat")
	public String getHowToEat() {
		return howToEat;
	}


	public void setHowToEat(String howToEat) {
		this.howToEat = howToEat;
	}

	@Column(name = "produce_date")
	public String getProduceDate() {
		return produceDate;
	}


	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
	}

	@Column(name = "tip")
	public String getTip() {
		return tip;
	}


	public void setTip(String tip) {
		this.tip = tip;
	}

	@Column(name = "is_groupbuy")
	public String getIsGroupbuy() {
		return isGroupbuy;
	}


	public void setIsGroupbuy(String isGroupbuy) {
		this.isGroupbuy = isGroupbuy;
	}

	@Column(name = "sort")
	public Integer getSort() {
		return sort;
	}


	public void setSort(Integer sort) {
		this.sort = sort;
	}

	
	
}
