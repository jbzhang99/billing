package com.ai.baas.ccp.dao.mapper.bo;

import java.sql.Timestamp;

public class OmcAvoidscout {
    private String avoidSeq;

    private String tenantId;

    private String systemId;

    private String ownerId;

    private String ownerType;

    private String speType;

    private Timestamp effDate;

    private Timestamp expDate;

    public String getAvoidSeq() {
        return avoidSeq;
    }

    public void setAvoidSeq(String avoidSeq) {
        this.avoidSeq = avoidSeq == null ? null : avoidSeq.trim();
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

    public String getSpeType() {
        return speType;
    }

    public void setSpeType(String speType) {
        this.speType = speType == null ? null : speType.trim();
    }

    public Timestamp getEffDate() {
        return effDate;
    }

    public void setEffDate(Timestamp effDate) {
        this.effDate = effDate;
    }

    public Timestamp getExpDate() {
        return expDate;
    }

    public void setExpDate(Timestamp expDate) {
        this.expDate = expDate;
    }
}