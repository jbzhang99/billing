package com.ai.baas.amc.api.oweinfoquery.param;

import java.io.Serializable;

/**
 * 欠费信息实体类
 *
 * Date: 2016年4月13日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class OweInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 租户Id
     */
    private String tenantId;
    
    /**
     * 客户ID
     */
    private String custId;
    
    /**
     * 客户名称
     */
    private String custName;
    
    /**
     * 客户等级
     */
    private String custGrade;
    
    /**
     * 欠费开始时间
     */
    private String unsettledMonth;
    
    /**
     * 欠费金额，单位为厘
     */
    private long balance;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustGrade() {
        return custGrade;
    }

    public void setCustGrade(String custGrade) {
        this.custGrade = custGrade;
    }

    public String getUnsettledMonth() {
        return unsettledMonth;
    }

    public void setUnsettledMonth(String unsettledMonth) {
        this.unsettledMonth = unsettledMonth;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
