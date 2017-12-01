package com.ai.baas.prd.dao.mapper.bo;

public class PmCatalogInfo {
    private Long id;

    private String tenantId;

    private String mainProductId;

    private String mainProductName;

    private String categoryId;

    private String billingCycle;

    private String specTypeName;

    private String specTypeId;

    private String specDetailId;

    private String pricePolicy;

    private String tradeCode;

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

    public String getMainProductId() {
        return mainProductId;
    }

    public void setMainProductId(String mainProductId) {
        this.mainProductId = mainProductId == null ? null : mainProductId.trim();
    }

    public String getMainProductName() {
        return mainProductName;
    }

    public void setMainProductName(String mainProductName) {
        this.mainProductName = mainProductName == null ? null : mainProductName.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getBillingCycle() {
        return billingCycle;
    }

    public void setBillingCycle(String billingCycle) {
        this.billingCycle = billingCycle == null ? null : billingCycle.trim();
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

    public String getSpecDetailId() {
        return specDetailId;
    }

    public void setSpecDetailId(String specDetailId) {
        this.specDetailId = specDetailId == null ? null : specDetailId.trim();
    }

    public String getPricePolicy() {
        return pricePolicy;
    }

    public void setPricePolicy(String pricePolicy) {
        this.pricePolicy = pricePolicy == null ? null : pricePolicy.trim();
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode == null ? null : tradeCode.trim();
    }
}