package com.ai.baas.cust.dao.mapper.bo;

import java.sql.Timestamp;

public class BmcFailureBill {
    private Long id;

    private String tenantId;

    private String serviceType;

    private String source;

    private String bsn;

    private String sn;

    private Timestamp accountPeriod;

    private Timestamp arrivalTime;

    private String failStep;

    private String failCode;

    private Timestamp failDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn == null ? null : bsn.trim();
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public Timestamp getAccountPeriod() {
        return accountPeriod;
    }

    public void setAccountPeriod(Timestamp accountPeriod) {
        this.accountPeriod = accountPeriod;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getFailStep() {
        return failStep;
    }

    public void setFailStep(String failStep) {
        this.failStep = failStep == null ? null : failStep.trim();
    }

    public String getFailCode() {
        return failCode;
    }

    public void setFailCode(String failCode) {
        this.failCode = failCode == null ? null : failCode.trim();
    }

    public Timestamp getFailDate() {
        return failDate;
    }

    public void setFailDate(Timestamp failDate) {
        this.failDate = failDate;
    }
}