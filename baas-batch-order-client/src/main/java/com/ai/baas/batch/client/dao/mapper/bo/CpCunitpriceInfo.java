package com.ai.baas.batch.client.dao.mapper.bo;

public class CpCunitpriceInfo {
    private Long id;

    private String cunitPriceCode;

    private String priceName;

    private String priceProductType;

    private String unitType;

    private Double priceValue;

    private String factorCode;

    private String subjectCode;

    private String extCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCunitPriceCode() {
        return cunitPriceCode;
    }

    public void setCunitPriceCode(String cunitPriceCode) {
        this.cunitPriceCode = cunitPriceCode == null ? null : cunitPriceCode.trim();
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName == null ? null : priceName.trim();
    }

    public String getPriceProductType() {
        return priceProductType;
    }

    public void setPriceProductType(String priceProductType) {
        this.priceProductType = priceProductType == null ? null : priceProductType.trim();
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType == null ? null : unitType.trim();
    }

    public Double getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(Double priceValue) {
        this.priceValue = priceValue;
    }

    public String getFactorCode() {
        return factorCode;
    }

    public void setFactorCode(String factorCode) {
        this.factorCode = factorCode == null ? null : factorCode.trim();
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode == null ? null : subjectCode.trim();
    }

    public String getExtCode() {
        return extCode;
    }

    public void setExtCode(String extCode) {
        this.extCode = extCode == null ? null : extCode.trim();
    }
}