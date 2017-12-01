package com.ai.baas.batch.client.dao.mapper.bo;

public class CpCunitpriceDetail {
    private Long id;

    private String cunitPriceCode;

    private String priceProductType;

    private String factorName;

    private String factorValue;

    private Long factorAmount;

    private String factorUnit;

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

    public String getPriceProductType() {
        return priceProductType;
    }

    public void setPriceProductType(String priceProductType) {
        this.priceProductType = priceProductType == null ? null : priceProductType.trim();
    }

    public String getFactorName() {
        return factorName;
    }

    public void setFactorName(String factorName) {
        this.factorName = factorName == null ? null : factorName.trim();
    }

    public String getFactorValue() {
        return factorValue;
    }

    public void setFactorValue(String factorValue) {
        this.factorValue = factorValue == null ? null : factorValue.trim();
    }

    public Long getFactorAmount() {
        return factorAmount;
    }

    public void setFactorAmount(Long factorAmount) {
        this.factorAmount = factorAmount;
    }

    public String getFactorUnit() {
        return factorUnit;
    }

    public void setFactorUnit(String factorUnit) {
        this.factorUnit = factorUnit == null ? null : factorUnit.trim();
    }
}