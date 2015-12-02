package com.zl.bgec.basicapi.cart.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.zl.bgec.basicapi.common.GeneratorKey;

@Entity
@Table(name = "tbl_cart_item")
public class CartItem extends GeneratorKey{
	private String id;
	private String cartNo;// 购物车编号
	private String itemNo;// 购物车条目编号
	private String prodNo;// 货品编号
	private Integer quantity;// 数量
	private String shopNo;// 店铺编号
	private String shopName;//店铺名称
	private String prodName;//商品名称
	private Double prodPrice;// 价格
	private Double priceTotal;// 总价格
	private String prodPic;// 货品图片
	private Date createTime;// 创建时间
	private String model;//型号
	private Double gourpbuyPrice;//团购价
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

	@Column(name = "cart_no")
	public String getCartNo() {
		return cartNo;
	}

	public void setCartNo(String cartNo) {
		this.cartNo = cartNo;
	}

	@Column(name = "item_no")
	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	@Column(name = "prod_no")
	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "shop_no")
	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	@Column(name = "prod_price")
	public Double getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(Double prodPrice) {
		this.prodPrice = prodPrice;
	}

	@Column(name = "price_total")
	public Double getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(Double priceTotal) {
		this.priceTotal = priceTotal;
	}

	@Column(name = "prod_pic")
	public String getProdPic() {
		return prodPic;
	}

	public void setProdPic(String prodPic) {
		this.prodPic = prodPic;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name="shop_name")
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Column(name="prod_name")
	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	@Transient
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Transient
	public Double getGourpbuyPrice() {
		return gourpbuyPrice;
	}

	public void setGourpbuyPrice(Double gourpbuyPrice) {
		this.gourpbuyPrice = gourpbuyPrice;
	}
	
}
