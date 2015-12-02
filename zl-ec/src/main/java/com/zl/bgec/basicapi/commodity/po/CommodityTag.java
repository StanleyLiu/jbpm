package com.zl.bgec.basicapi.commodity.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * @ClassName: CommoTag 商品标签表
 * @author: Hualong
 * @date 2013-4-26 下午04:34:59
 */
@Entity
@Table(name = "tbl_commo_tag")
public class CommodityTag implements Serializable {

    private String id;
    private String sellerNo;// 卖家编号
    private String tagNo;// 标签编号
    private String tagName;// 标签名称
    private String description;// 标签描述
    private byte deleteFlag;// 0表示启用，1表示不启用
    
    private Date createTime;
    private Date updateTime;
    
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

    
    @Column(name = "seller_no", length = 32, nullable = false)
    public String getSellerNo() {
        return this.sellerNo;
    }

    
    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo;
    }

    
    @Column(name = "tag_no", length = 32, nullable = false)
    public String getTagNo() {
        return this.tagNo;
    }

    
    public void setTagNo(String tagNo) {
        this.tagNo = tagNo;
    }

    
    @Column(name = "tag_name", length = 50, nullable = false)
    public String getTagName() {
        return this.tagName;
    }

    
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    
    @Column(name = "description", length = 500, nullable = true)
    public String getDescription() {
        return this.description;
    }

    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name = "delete_flag", nullable = false)
    public byte getDeleteFlag() {
        return this.deleteFlag;
    }

    
    public void setDeleteFlag(byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", length = 19, nullable = false)
    public Date getCreateTime() {
        return this.createTime;
    }

    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", length = 19, nullable = false)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
