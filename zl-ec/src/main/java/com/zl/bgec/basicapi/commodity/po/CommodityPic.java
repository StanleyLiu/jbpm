package com.zl.bgec.basicapi.commodity.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_commodity_pic")
public class CommodityPic implements java.io.Serializable {

	private String id;
	private String commoNo;
	private String productNo;
	private String url;
	private Date createTime;

	public CommodityPic() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	@Column(name = "commo_no", nullable = false, length = 32)
	public String getCommoNo() {
		return commoNo;
	}

	public void setCommoNo(String commoNo) {
		this.commoNo = commoNo;
	}

	@Column(name = "product_no", length = 32)
	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
