package com.zl.bgec.basicapi.commodity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_operating_cat_commo")
public class CommodityOperCatCommo {
    
	private String id;
    private String operCatNo;
    private String commoNo;

    public CommodityOperCatCommo() {

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

    @Column(name = "oprt_cat_no", nullable = false, length = 32)
    public String getOperCatNo() {
		return operCatNo;
	}

	public void setOperCatNo(String operCatNo) {
		this.operCatNo = operCatNo;
	}
	
    @Column(name = "commo_no", nullable = false, length = 32)
    public String getCommoNo() {
        return this.commoNo;
    }

	public void setCommoNo(String commoNo) {
        this.commoNo = commoNo;

    }
}
