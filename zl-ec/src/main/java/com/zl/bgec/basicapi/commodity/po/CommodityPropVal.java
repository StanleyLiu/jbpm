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
@Table(name = "tbl_commo_prop_val")
public class CommodityPropVal implements java.io.Serializable {
    private static final long serialVersionUID = 3258453831599862417L;

    private String id;
    private String optionNo;// 值选项编号
    private String optionName;// 值选项名称
    private String optionPic;// 值选项指示图片，例如颜色中的红色
    private String propNo;// 属性编号
    private byte deleteFlag;// 删除标志
    private Date createTime;// 创建时间
    private Date updateTime;// 更新时间

    public CommodityPropVal() {

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

    
    @Column(name = "option_no", nullable = false, length = 32)
    public String getOptionNo() {
        return this.optionNo;
    }

    
    public void setOptionNo(String optionNo) {
        this.optionNo = optionNo;
    }

    
    @Column(name = "option_name", nullable = false, length = 200)
    public String getOptionName() {
        return this.optionName;
    }

    
    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    
    @Column(name = "option_pic", length = 500)
    public String getOptionPic() {
        return this.optionPic;
    }

    
    public void setOptionPic(String optionPic) {
        this.optionPic = optionPic;
    }

    
    @Column(name = "prop_no", length = 32)
    public String getPropNo() {
        return this.propNo;
    }

    
    public void setPropNo(String propNo) {
        this.propNo = propNo;
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
