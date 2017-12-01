package com.ai.runner.center.mmp.dao.mapper.bo;

import java.sql.Timestamp;

public class UmsMsgService {
    private Long serviceId;

    private String tenantId;

    private String systemId;

    private String servicename;

    private String servicespecification;

    private Integer status;

    private Integer priorityLevel;

    private Timestamp insertTime;

    private Timestamp updateTime;

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
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

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename == null ? null : servicename.trim();
    }

    public String getServicespecification() {
        return servicespecification;
    }

    public void setServicespecification(String servicespecification) {
        this.servicespecification = servicespecification == null ? null : servicespecification.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public Timestamp getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Timestamp insertTime) {
        this.insertTime = insertTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

	@Override
	public String toString() {
		return "UmsMsgService [serviceId=" + serviceId + ", tenantId="
				+ tenantId + ", systemId=" + systemId + ", servicename="
				+ servicename + ", servicespecification="
				+ servicespecification + ", status=" + status
				+ ", priorityLevel=" + priorityLevel + ", insertTime="
				+ insertTime + ", updateTime=" + updateTime + "]";
	}
    
}