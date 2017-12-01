package com.ai.baas.amc.dao.mapper.bo;

import java.sql.Timestamp;

public class AmcInvoice {
    private Long invoiceSeq;

    private String acctId;

    private Long subsId;

    private String serviceId;

    private Long totalAmount;

    private Long adjustAfterwards;

    private Long discTotalAmount;

    private Long balance;

    private Long payStatus;

    private Timestamp lastPayDate;

    private Long printTimes;

    private Long custId;

    private Long custType;

    private String tenantId;

    public Long getInvoiceSeq() {
        return invoiceSeq;
    }

    public void setInvoiceSeq(Long invoiceSeq) {
        this.invoiceSeq = invoiceSeq;
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

    public Long getPrintTimes() {
        return printTimes;
    }

    public void setPrintTimes(Long printTimes) {
        this.printTimes = printTimes;
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

	@Override
	public String toString() {
		return "AmcInvoice [invoiceSeq=" + invoiceSeq + ", acctId=" + acctId
				+ ", subsId=" + subsId + ", serviceId=" + serviceId
				+ ", totalAmount=" + totalAmount + ", adjustAfterwards="
				+ adjustAfterwards + ", discTotalAmount=" + discTotalAmount
				+ ", balance=" + balance + ", payStatus=" + payStatus
				+ ", lastPayDate=" + lastPayDate + ", printTimes=" + printTimes
				+ ", custId=" + custId + ", custType=" + custType
				+ ", tenantId=" + tenantId + "]";
	}
    
    
}