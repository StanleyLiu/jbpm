package com.zl.bgec.basicapi.commodity.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "tbl_prdod_court")
public class CommodityCourtProduct {
	public String id;//id
	public String prodNo;//货品编号
	public String courtInfo;//添加场地信息
	public String courtIntroduction;//场地介绍
	public Double price;//价格
	public Integer num;//数量
	public String unitTime;//日历
	public Date createTime;//创建时间
	public String commoNo;//商品场地编号
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
    @Column(name = "prod_no")
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
    @Column(name = "court_info")
	public String getCourtInfo() {
		return courtInfo;
	}
	public void setCourtInfo(String courtInfo) {
		this.courtInfo = courtInfo;
	}
    @Column(name = "court_introduction")
	public String getCourtIntroduction() {
		return courtIntroduction;
	}
	public void setCourtIntroduction(String courtIntroduction) {
		this.courtIntroduction = courtIntroduction;
	}
    @Column(name = "price")
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
    @Column(name = "num")
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
    @Column(name = "unit_time")
	public String getUnitTime() {
		return unitTime;
	}
	public void setUnitTime(String unitTime) {
		this.unitTime = unitTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    @Column(name = "commo_no")
	public String getCommoNo() {
		return commoNo;
	}
	public void setCommoNo(String commoNo) {
		this.commoNo = commoNo;
	}
	
	
	
}
