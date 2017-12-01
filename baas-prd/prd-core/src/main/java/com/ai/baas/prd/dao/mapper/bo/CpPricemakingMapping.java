package com.ai.baas.prd.dao.mapper.bo;

public class CpPricemakingMapping {
    private Long id;

    private String tenantId;

    private String mappingBefore;

    private String mappingAfter;

    private String isValid;

    private String comment;

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

    public String getMappingBefore() {
        return mappingBefore;
    }

    public void setMappingBefore(String mappingBefore) {
        this.mappingBefore = mappingBefore == null ? null : mappingBefore.trim();
    }

    public String getMappingAfter() {
        return mappingAfter;
    }

    public void setMappingAfter(String mappingAfter) {
        this.mappingAfter = mappingAfter == null ? null : mappingAfter.trim();
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}