package com.ai.baas.amc.api.custbalancequery.param;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 可用余额查询实体.<br>
 *
 * Date: 2016年4月14日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class UsableBalanceVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
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
     * 可用余额，单位为元
     */
    private double usableAmount;
    
    /**
     * 查询时间
     */
    private Timestamp queryTime;

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

    public double getUsableAmount() {
        return usableAmount;
    }

    public void setUsableAmount(double usableAmount) {
        this.usableAmount = usableAmount;
    }

    public Timestamp getQueryTime() {
        if (queryTime == null) {
            return null;
        }

        return new Timestamp(queryTime.getTime());
    }

    public void setQueryTime(Timestamp queryTime) {
        if (queryTime != null) {
            this.queryTime = new Timestamp(queryTime.getTime());
        }
    }
}
