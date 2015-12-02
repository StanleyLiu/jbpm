package com.zl.bgec.basicapi.commodity.po;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import com.zl.bgec.basicapi.common.CommodityConstant;

@Entity
@Table(name = "tbl_commo_prop")
public class CommodityProp implements java.io.Serializable {
    private static final long serialVersionUID = -2160774174591349966L;

    private String id;
    private String propNo;// 属性编号
    private String propName;// 属性编号
    private byte inputType;// 输入方式：0文本输入 1下拉单选 2平铺单选 3下拉多选 4平铺多选
    private byte isRequired; // 表示是否必须有值：0表示非必须 1表示必须 default 0
    private byte isRelatePic;// 是否关联图片 0不关联 1关联 default 0
    private byte isSpecProp;// 是否为规格属性 0非规格属性 1规格属性 default 0
    private byte screenType;// 筛选性：0不可筛选 1单值筛选 2多值筛选。
    private String description;// 描述
    private byte deleteFlag;// 删除标识 0未删除 1已删除 default 0
    private Date createTime;
    private Date updateTime;

    private Set<CommodityPropVal> commoPropValOptions = new TreeSet<CommodityPropVal>();
    
    public CommodityProp() {

    }

    // 用户HQL查询使用
    public CommodityProp(String propNo, String propName) {
        super();
        this.propNo = propNo;
        this.propName = propName;
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

    
    @Column(name = "prop_no", unique = true, nullable = false, length = 32)
    public String getPropNo() {
        return this.propNo;
    }

    
    public void setPropNo(String propNo) {
        this.propNo = propNo;
    }

    
    @Column(name = "prop_name", nullable = false, length = 50)
    public String getPropName() {
        return this.propName;
    }

    
    public void setPropName(String propName) {
        this.propName = propName;
    }

    /**
     * 输入方式（0:文本输入，1:下拉单选，2:平铺单选，3下拉多选，4平铺多选)
     */
    
    @Column(name = "input_type", nullable = false)
    public byte getInputType() {
        return this.inputType;
    }

    /**
     * 输入方式（0:文本输入，1:下拉单选，2:平铺单选，3下拉多选，4平铺多选)
     */
    
    public void setInputType(byte inputType) {
        this.inputType = inputType;
    }

    
    @Column(name = "is_required", nullable = false)
    public byte getIsRequired() {
        return this.isRequired;
    }

    
    public void setIsRequired(byte isRequired) {
        this.isRequired = isRequired;
    }

    
    @Column(name = "is_relate_pic", nullable = false)
    public byte getIsRelatePic() {
        return this.isRelatePic;
    }

    
    public void setIsRelatePic(byte isRelatePic) {
        this.isRelatePic = isRelatePic;
    }

    
    @Column(name = "is_spec_prop", nullable = false)
    public byte getIsSpecProp() {
        return this.isSpecProp;
    }

    
    public void setIsSpecProp(byte isSpecProp) {
        this.isSpecProp = isSpecProp;
    }

    
    @Column(name = "screen_type", nullable = false)
    public byte getScreenType() {
        return this.screenType;
    }

    
    public void setScreenType(byte screenType) {
        this.screenType = screenType;
    }

    
    @Column(name = "description", length = 500)
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
    @Column(name = "create_time", nullable = false, length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", nullable = false, length = 19)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    
    @OneToMany(targetEntity = CommodityPropVal.class, fetch = FetchType.LAZY)
    @Where(clause = "delete_flag="+CommodityConstant.UNDELETE_FLAG)
    @JoinColumn(name = "prop_no", referencedColumnName = "prop_no", insertable = false, updatable = false)
    public Set<CommodityPropVal> getCommoPropValOptions() {
        return this.commoPropValOptions;
    }

    
    public void setCommoPropValOptions(Set<CommodityPropVal> commoPropValOptions) {
        this.commoPropValOptions = commoPropValOptions;
    }

}
