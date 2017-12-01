package com.ai.baas.amc.dao.mapper.bo;

public class AmcDeductRuleKey {
    private String tenantId;

    private String fundSubject;

    private String feeSubject;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getFundSubject() {
        return fundSubject;
    }

    public void setFundSubject(String fundSubject) {
        this.fundSubject = fundSubject == null ? null : fundSubject.trim();
    }

    public String getFeeSubject() {
        return feeSubject;
    }

    public void setFeeSubject(String feeSubject) {
        this.feeSubject = feeSubject == null ? null : feeSubject.trim();
    }
}