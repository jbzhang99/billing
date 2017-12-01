package com.ai.baas.prd.dao.mapper.bo;

public class PmSpecType {
    private Long id;

    private String tenantId;

    private String categoryId;

    private String specTypeName;

    private String specTypeId;

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getSpecTypeName() {
        return specTypeName;
    }

    public void setSpecTypeName(String specTypeName) {
        this.specTypeName = specTypeName == null ? null : specTypeName.trim();
    }

    public String getSpecTypeId() {
        return specTypeId;
    }

    public void setSpecTypeId(String specTypeId) {
        this.specTypeId = specTypeId == null ? null : specTypeId.trim();
    }
}