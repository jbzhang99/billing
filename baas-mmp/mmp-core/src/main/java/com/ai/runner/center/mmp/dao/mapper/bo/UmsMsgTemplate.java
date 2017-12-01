package com.ai.runner.center.mmp.dao.mapper.bo;

import java.sql.Timestamp;

public class UmsMsgTemplate {
    private Long sequenceId;

    private Long serviceId;

    private String tenantId;

    private String systemId;

    private Long templateId;

    private String templateName;

    private String templateText;

    private String sbeginTime;

    private String scloseTime;

    private Integer retryTimes;

    private Timestamp insertTime;

    private Timestamp updateTime;

    public Long getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(Long sequenceId) {
        this.sequenceId = sequenceId;
    }

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

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getTemplateText() {
        return templateText;
    }

    public void setTemplateText(String templateText) {
        this.templateText = templateText == null ? null : templateText.trim();
    }

    public String getSbeginTime() {
        return sbeginTime;
    }

    public void setSbeginTime(String sbeginTime) {
        this.sbeginTime = sbeginTime == null ? null : sbeginTime.trim();
    }

    public String getScloseTime() {
        return scloseTime;
    }

    public void setScloseTime(String scloseTime) {
        this.scloseTime = scloseTime == null ? null : scloseTime.trim();
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
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
}