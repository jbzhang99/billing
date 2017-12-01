package com.ai.baas.amc.api.paymentquery.param;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 缴费流水记录实体.<br>
 *
 * Date: 2016年4月14日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class PaymentLog implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 充值流水号
     */
    private String paySerialCode;
    
    /**
     * 业务流水号
     */
    private String peerSerialCode;
    
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
     * 操作类型
     */
    private String optType;

    /**
     * 交易金额，单位为厘
     */
    private long totalAmount;
    
    /**
     * 充值时间
     */
    private Timestamp payTime;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getPaySerialCode() {
        return paySerialCode;
    }

    public void setPaySerialCode(String paySerialCode) {
        this.paySerialCode = paySerialCode;
    }

    public String getPeerSerialCode() {
        return peerSerialCode;
    }

    public void setPeerSerialCode(String peerSerialCode) {
        this.peerSerialCode = peerSerialCode;
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

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Timestamp getPayTime() {
        if (payTime == null) {
            return null;
        }

        return new Timestamp(payTime.getTime());
    }

    public void setPayTime(Timestamp payTime) {
        if (payTime != null) {
            this.payTime = new Timestamp(payTime.getTime());
        }
    }
    
}
