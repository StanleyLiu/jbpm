package com.zl.bgec.basicapi.commodity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tbl_product_no")
public class ProductNo {
	 private static final long serialVersionUID = 1L;

	    private String id;
	    /** 商品编号 */
	    private String commoNo;
	    /** 增长序列，从1开始 */
	    private int seq;
	    /** 程序每次取值个数 */
	    private int step;

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

	    @Column(name = "commo_no", length = 11)
	    public String getCommoNo() {
	        return this.commoNo;
	    }

	    public void setCommoNo(String commoNo) {
	        this.commoNo = commoNo;
	    }

	    @Column(name = "seq", length = 2)
	    public int getSeq() {
	        return this.seq;
	    }

	    public void setSeq(int seq) {
	        this.seq = seq;
	    }

	    @Column(name = "step", length = 2)
	    public int getStep() {
	        return this.step;
	    }

	    public void setStep(int step) {
	        this.step = step;
	    }	
}
