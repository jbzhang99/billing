package com.ai.baas.ccp.dao.mapper.bo;

public class OmcScoutActionDefine {
    private String tenantId;

    private String scoutType;

    private String businessCode;

    private String scoutRule;

    private String infCommond;

    private Integer smsTemplate;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getScoutType() {
        return scoutType;
    }

    public void setScoutType(String scoutType) {
        this.scoutType = scoutType == null ? null : scoutType.trim();
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode == null ? null : businessCode.trim();
    }

    public String getScoutRule() {
        return scoutRule;
    }

    public void setScoutRule(String scoutRule) {
        this.scoutRule = scoutRule == null ? null : scoutRule.trim();
    }

    public String getInfCommond() {
        return infCommond;
    }

    public void setInfCommond(String infCommond) {
        this.infCommond = infCommond == null ? null : infCommond.trim();
    }

    public Integer getSmsTemplate() {
        return smsTemplate;
    }

    public void setSmsTemplate(Integer smsTemplate) {
        this.smsTemplate = smsTemplate;
    }
}