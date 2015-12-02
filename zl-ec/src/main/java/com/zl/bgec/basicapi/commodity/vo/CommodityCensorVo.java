package com.zl.bgec.basicapi.commodity.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.zl.bgec.basicapi.common.PageVo;

/**
 * 商品审核状态DO
 * 
 * @author: Hualong
 * @date 2013-7-4 下午06:38:58
 */
public class CommodityCensorVo extends PageVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private String id;
    /** 商品编号 */
    private String commoNo;
    /** 审核状态：0-暂存状态 ，1-待审核，2-审核通过 3-审核未通过 */
    private byte censorStatus;
    /** 审核未通过的原因 */
    private String reason;
    /** 操作员编号 */
    private String operatorNo;
    /** 操作时间 */
    private Date operateTime;
    
    private String operatorName;
    
    private List<String> commoNoList;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommoNo() {
        return this.commoNo;
    }

    public void setCommoNo(String commoNo) {
        this.commoNo = commoNo;
    }

    /**
     * 
     * // 审核状态：0-暂存状态 ，1-待审核，2-审核通过 3-审核未通过
     * 
     * @return
     * @throws
     * @author: Hualong
     * @date: 2013-3-31下午04:57:44
     */
    public byte getCensorStatus() {
        return this.censorStatus;
    }

    public void setCensorStatus(byte censorStatus) {
        this.censorStatus = censorStatus;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOperatorNo() {
        return this.operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    public Date getOperateTime() {
        return this.operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public List<String> getCommoNoList() {
		return commoNoList;
	}

	public void setCommoNoList(List<String> commoNoList) {
		this.commoNoList = commoNoList;
	}
    
    

}
