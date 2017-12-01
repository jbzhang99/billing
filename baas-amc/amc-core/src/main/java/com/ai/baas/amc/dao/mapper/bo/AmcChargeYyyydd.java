package com.ai.baas.amc.dao.mapper.bo;

import java.sql.Timestamp;

public class AmcChargeYyyydd {
    private Long chargeSeq;

    private String acctId;

    private Long subsId;

    private String serviceId;

    private Long subjectId;

    private Long totalAmount;

    private Long adjustAfterwards;

    private Long discTotalAmount;

    private Long balance;

    private Long payStatus;

    private Timestamp lastPayDate;

    private Long custId;

    private Long custType;

    private String tenantId;

    private String billMonth;
    
    private Long drTotalAmount;
    
    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }

    public Long getChargeSeq() {
        return chargeSeq;
    }

    public void setChargeSeq(Long chargeSeq) {
        this.chargeSeq = chargeSeq;
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId == null ? null : acctId.trim();
    }

    public Long getSubsId() {
        return subsId;
    }

    public void setSubsId(Long subsId) {
        this.subsId = subsId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public Long getAdjustAfterwards() {
        return adjustAfterwards;
    }

    public void setAdjustAfterwards(Long adjustAfterwards) {
        this.adjustAfterwards = adjustAfterwards;
    }

    public Long getDiscTotalAmount() {
        return discTotalAmount;
    }

    public void setDiscTotalAmount(Long discTotalAmount) {
        this.discTotalAmount = discTotalAmount;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Long payStatus) {
        this.payStatus = payStatus;
    }

    public Timestamp getLastPayDate() {
        return lastPayDate;
    }

    public void setLastPayDate(Timestamp lastPayDate) {
        this.lastPayDate = lastPayDate;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getCustType() {
        return custType;
    }

    public void setCustType(Long custType) {
        this.custType = custType;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public Long getDrTotalAmount() {
        return drTotalAmount;
    }

    public void setDrTotalAmount(Long drTotalAmount) {
        this.drTotalAmount = drTotalAmount;
    }
}