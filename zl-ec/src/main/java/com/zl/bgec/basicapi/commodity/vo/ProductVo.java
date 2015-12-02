package com.zl.bgec.basicapi.commodity.vo;

import java.io.Serializable;

import com.zl.bgec.basicapi.common.PageVo;


/**
 * 货品vo
 */
public class ProductVo extends PageVo implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private String id;
    private String prodNo; // 货品编号
    private String prodName; // 货品名称
    private byte prodStatus;//货品状态，0启用，1禁用，2作废
    private String commoNo; // 商品编号
    private String sellerNo; // 卖家编号
    private String defaultPic; // 货品默认图片的地址，默认图片是大图的地址
    private long num;// 货品数量
    private double freightage;//货品运费
    private byte deleteFlag;// 删除标识 0未删除 1已删除 default 0
    private double price;
    private String model;//货品型号
    private Integer stock;//库存
    private Integer stockPreemption;//预占库存
    private String shopNo;//店铺编号
	private String memberNo;//会员编号
	private String prodBrand;//品牌
	private String prodProductionAddress;//产地
	private String storage;//储存方法
	private String warranty;//保质期
	private String howToEat;//食用方法
	private String produceDate;//生产日期
	private String tip;//温馨提示
    private String isGroupbuy;//是否团购商品，1普通商品，2团购商品
    private String leaveStock;//剩余库存
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProdNo() {
        return this.prodNo;
    }
    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }
    public String getProdName() {
        return this.prodName;
    }
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
    public String getCommoNo() {
        return this.commoNo;
    }
    public void setCommoNo(String commoNo) {
        this.commoNo = commoNo;
    }
    public String getSellerNo() {
        return this.sellerNo;
    }
    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo;
    }
    public String getDefaultPic() {
        return this.defaultPic;
    }
    public void setDefaultPic(String defaultPic) {
        this.defaultPic = defaultPic;
    }
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	public byte getProdStatus() {
		return prodStatus;
	}
	public void setProdStatus(byte prodStatus) {
		this.prodStatus = prodStatus;
	}
	public double getFreightage() {
		return freightage;
	}
	public void setFreightage(double freightage) {
		this.freightage = freightage;
	}
	public byte getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getStockPreemption() {
		return stockPreemption;
	}
	public void setStockPreemption(Integer stockPreemption) {
		this.stockPreemption = stockPreemption;
	}
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getProdBrand() {
		return prodBrand;
	}
	public void setProdBrand(String prodBrand) {
		this.prodBrand = prodBrand;
	}
	public String getProdProductionAddress() {
		return prodProductionAddress;
	}
	public void setProdProductionAddress(String prodProductionAddress) {
		this.prodProductionAddress = prodProductionAddress;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public String getWarranty() {
		return warranty;
	}
	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}
	public String getHowToEat() {
		return howToEat;
	}
	public void setHowToEat(String howToEat) {
		this.howToEat = howToEat;
	}
	public String getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getIsGroupbuy() {
		return isGroupbuy;
	}
	public void setIsGroupbuy(String isGroupbuy) {
		this.isGroupbuy = isGroupbuy;
	}
	public String getLeaveStock() {
		return leaveStock;
	}
	public void setLeaveStock(String leaveStock) {
		this.leaveStock = leaveStock;
	}
}
