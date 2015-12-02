package com.zl.bgec.basicapi.commodity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_product_stock")
public class ProductStock implements java.io.Serializable {
	
	private String id;
	private String productNo;
	private int stock;
	private int virtualStock;
	
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
	@Column(name = "product_no", length = 32)
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	@Column(name = "stock")
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@Column(name = "virtual_stock")
	public Integer getVirtualStock() {
		return virtualStock;
	}
	public void setVirtualStock(Integer virtualStock) {
		this.virtualStock = virtualStock;
	}
	
	
}
