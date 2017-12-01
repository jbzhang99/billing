package com.ai.baas.ccp.dao.mapper.bo;

import java.sql.Timestamp;

public class OmcBmsInterface {
    private Long serialNo;

    private String acctId;

    private String subsId;

    private String scoutType;

    private String bmsData;

    private String interfaceData;

    private Integer serviceType;

    private Integer priority;

    private Integer channel;

    private Timestamp insertTime;

    private Integer dealFlag;

    private Timestamp dealTime;

    private String remark;

    private Integer retryTimes;

    private String tenantId;

    private String systemId;

    public Long getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Long serialNo) {
        this.serialNo = serialNo;
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId == null ? null : acctId.trim();
    }

    public String getSubsId() {
        return subsId;
    }

    public void setSubsId(String subsId) {
        this.subsId = subsId == null ? null : subsId.trim();
    }

    public String getScoutType() {
        return scoutType;
    }

    public void setScoutType(String scoutType) {
        this.scoutType = scoutType == null ? null : scoutType.trim();
    }

    public String getBmsData() {
        return bmsData;
    }

    public void setBmsData(String bmsData) {
        this.bmsData = bmsData == null ? null : bmsData.trim();
    }

    public String getInterfaceData() {
        return interfaceData;
    }

    public void setInterfaceData(String interfaceData) {
        this.interfaceData = interfaceData == null ? null : interfaceData.trim();
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Timestamp getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Timestamp insertTime) {
        this.insertTime = insertTime;
    }

    public Integer getDealFlag() {
        return dealFlag;
    }

    public void setDealFlag(Integer dealFlag) {
        this.dealFlag = dealFlag;
    }

    public Timestamp getDealTime() {
        return dealTime;
    }

    public void setDealTime(Timestamp dealTime) {
        this.dealTime = dealTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }
}