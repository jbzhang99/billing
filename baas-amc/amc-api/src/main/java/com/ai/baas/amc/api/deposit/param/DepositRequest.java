package com.ai.baas.amc.api.deposit.param;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 现金存入请求参数 <br>
 * Title: MVNO-CRM <br>
 * Description: <br>
 * Date: 2016年3月23日 <br>
 * Copyright (c) 2016 AILK <br>
 * 
 * @author fanpw
 */
public class DepositRequest extends BaseInfo {

    private static final long serialVersionUID = -1619209563716857225L;
    
    /**
     * 充值时间
     */
    private String depositTime;
    
    /**
     * 系统ID，必填
     */
    private String systemId;

    /**
     * 账户ID,必填
     */
    private String accountId;
    
    /**
     * 客户ID
     */
    private String custId;

    /**
     * 业务订单流水号，必填
     */
    private String busiSerialNo;

    /**
     * 交易摘要，至少1个
     */
    private List<TransSummary> transSummary;
    
    /**
     * 专款用户ID，默认0非专款，可选
     */
    private long subsId;

    /**
     * 业务描述
     */
    private String busiDesc;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getBusiSerialNo() {
        return busiSerialNo;
    }

    public void setBusiSerialNo(String busiSerialNo) {
        this.busiSerialNo = busiSerialNo;
    }

    public List<TransSummary> getTransSummary() {
        return transSummary;
    }

    public void setTransSummary(List<TransSummary> transSummary) {
        this.transSummary = transSummary;
    }

    public String getBusiDesc() {
        return busiDesc;
    }

    public void setBusiDesc(String busiDesc) {
        this.busiDesc = busiDesc;
    }

    public long getSubsId() {
        return subsId;
    }

    public void setSubsId(long subsId) {
        this.subsId = subsId;
    }

	public String getDepositTime() {
		return depositTime;
	}

	public void setDepositTime(String depositTime) {
		this.depositTime = depositTime;
	}
    

}
