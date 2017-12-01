package com.ai.baas.ccp.action.notice.param.base;

public abstract class AbstractBaseNoticeBusiVO {
    private String tenantId;
    
    private String subjectId;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
}
