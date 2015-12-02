package com.zl.bgec.basicapi.commodity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 
 * 商品标签与商品的关系表
 * @author: zhaoyunlu
 * @date 2013-4-26 下午04:34:40
 * 
 */
@Entity
@Table(name = "tbl_commo_tag_commo")
public class CommodityTagCommo {

    private String id;
    private String commoNo;
    private String tagNo;

    
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

    
    @Column(name = "commo_no", length = 32, nullable = false)
    public String getCommoNo() {
        return this.commoNo;
    }

    
    public void setCommoNo(String commoNo) {
        this.commoNo = commoNo;
    }

    
    @Column(name = "tag_no", length = 32, nullable = false)
    public String getTagNo() {
        return this.tagNo;
    }

    
    public void setTagNo(String tagNo) {
        this.tagNo = tagNo;
    }
}
