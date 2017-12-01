package com.ai.baas.ccp.dao.mapper.bo;

import java.sql.Timestamp;

public class OmcCreditfee {
    private String systemId;

    private String tenantId;

    private String creditSeq;

    private String ownerId;

    private String ownerType;

    private String creditType;

    private Long creditLine;

    private Timestamp effTime;

    private Timestamp expTime;

    private String resourceCode;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getCreditSeq() {
        return creditSeq;
    }

    public void setCreditSeq(String creditSeq) {
        this.creditSeq = creditSeq == null ? null : creditSeq.trim();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId == null ? null : ownerId.trim();
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType == null ? null : ownerType.trim();
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType == null ? null : creditType.trim();
    }

    public Long getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(Long creditLine) {
        this.creditLine = creditLine;
    }

    public Timestamp getEffTime() {
        return effTime;
    }

    public void setEffTime(Timestamp effTime) {
        this.effTime = effTime;
    }

    public Timestamp getExpTime() {
        return expTime;
    }

    public void setExpTime(Timestamp expTime) {
        this.expTime = expTime;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode == null ? null : resourceCode.trim();
    }
}