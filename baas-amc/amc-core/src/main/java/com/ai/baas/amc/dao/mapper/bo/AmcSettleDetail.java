package com.ai.baas.amc.dao.mapper.bo;

import java.sql.Timestamp;

public class AmcSettleDetail {
    private String serialCode;

    private String tenantId;

    private String busiOperCode;

    private Long acctId;

    private Integer settleMode;

    private Integer settleType;

    private Long bookId;

    private Long subsId;

    private Integer svcType;

    private Long fundSubjectId;

    private String cycleMonth;

    private Long invoiceSeq;

    private Long chargeSeq;

    private Long feeSubjectId;

    private Long total;

    private Timestamp createTime;

    private Integer status;

    private Timestamp lastStatusDate;

    private Long settleOrder;

    private String billMonth;
    
    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }
    public String getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode == null ? null : serialCode.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getBusiOperCode() {
        return busiOperCode;
    }

    public void setBusiOperCode(String busiOperCode) {
        this.busiOperCode = busiOperCode == null ? null : busiOperCode.trim();
    }

    public Long getAcctId() {
        return acctId;
    }

    public void setAcctId(Long acctId) {
        this.acctId = acctId;
    }

    public Integer getSettleMode() {
        return settleMode;
    }

    public void setSettleMode(Integer settleMode) {
        this.settleMode = settleMode;
    }

    public Integer getSettleType() {
        return settleType;
    }

    public void setSettleType(Integer settleType) {
        this.settleType = settleType;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getSubsId() {
        return subsId;
    }

    public void setSubsId(Long subsId) {
        this.subsId = subsId;
    }

    public Integer getSvcType() {
        return svcType;
    }

    public void setSvcType(Integer svcType) {
        this.svcType = svcType;
    }

    public Long getFundSubjectId() {
        return fundSubjectId;
    }

    public void setFundSubjectId(Long fundSubjectId) {
        this.fundSubjectId = fundSubjectId;
    }

    public String getCycleMonth() {
        return cycleMonth;
    }

    public void setCycleMonth(String cycleMonth) {
        this.cycleMonth = cycleMonth == null ? null : cycleMonth.trim();
    }

    public Long getInvoiceSeq() {
        return invoiceSeq;
    }

    public void setInvoiceSeq(Long invoiceSeq) {
        this.invoiceSeq = invoiceSeq;
    }

    public Long getChargeSeq() {
        return chargeSeq;
    }

    public void setChargeSeq(Long chargeSeq) {
        this.chargeSeq = chargeSeq;
    }

    public Long getFeeSubjectId() {
        return feeSubjectId;
    }

    public void setFeeSubjectId(Long feeSubjectId) {
        this.feeSubjectId = feeSubjectId;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getLastStatusDate() {
        return lastStatusDate;
    }

    public void setLastStatusDate(Timestamp lastStatusDate) {
        this.lastStatusDate = lastStatusDate;
    }

    public Long getSettleOrder() {
        return settleOrder;
    }

    public void setSettleOrder(Long settleOrder) {
        this.settleOrder = settleOrder;
    }
}