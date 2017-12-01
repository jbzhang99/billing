package com.ai.baas.batch.client.dao.mapper.bo;

import java.sql.Timestamp;

public class BmcUsageLogYyyymm {
    private Timestamp createTime;

    private String serviceId;

    private String usageRecord;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    public String getUsageRecord() {
        return usageRecord;
    }

    public void setUsageRecord(String usageRecord) {
        this.usageRecord = usageRecord == null ? null : usageRecord.trim();
    }
}