package com.ai.baas.pkgfee.dao.mapper.bo;

import java.sql.Timestamp;

public class CpPackageFee {
    private Long packageId;

    private String tenantId;

    private String priceCode;

    private Double priceValue;

    private Timestamp activeTime;

    private Timestamp inactiveTime;

    private String purchaseNum;

    private String purchaseUnit;

    private String cycleType;

    private Long cycleInterval;

    private String calExpr;

    private String factorCode;

    private String extCode;

    private String subjectCode;

    private Timestamp createType;

    private String autoRenew;

    private Timestamp updateDate;

    private String deductMode;

    private String feeList;

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(String priceCode) {
        this.priceCode = priceCode == null ? null : priceCode.trim();
    }

    public Double getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(Double priceValue) {
        this.priceValue = priceValue;
    }

    public Timestamp getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Timestamp activeTime) {
        this.activeTime = activeTime;
    }

    public Timestamp getInactiveTime() {
        return inactiveTime;
    }

    public void setInactiveTime(Timestamp inactiveTime) {
        this.inactiveTime = inactiveTime;
    }

    public String getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(String purchaseNum) {
        this.purchaseNum = purchaseNum == null ? null : purchaseNum.trim();
    }

    public String getPurchaseUnit() {
        return purchaseUnit;
    }

    public void setPurchaseUnit(String purchaseUnit) {
        this.purchaseUnit = purchaseUnit == null ? null : purchaseUnit.trim();
    }

    public String getCycleType() {
        return cycleType;
    }

    public void setCycleType(String cycleType) {
        this.cycleType = cycleType == null ? null : cycleType.trim();
    }

    public Long getCycleInterval() {
        return cycleInterval;
    }

    public void setCycleInterval(Long cycleInterval) {
        this.cycleInterval = cycleInterval;
    }

    public String getCalExpr() {
        return calExpr;
    }

    public void setCalExpr(String calExpr) {
        this.calExpr = calExpr == null ? null : calExpr.trim();
    }

    public String getFactorCode() {
        return factorCode;
    }

    public void setFactorCode(String factorCode) {
        this.factorCode = factorCode == null ? null : factorCode.trim();
    }

    public String getExtCode() {
        return extCode;
    }

    public void setExtCode(String extCode) {
        this.extCode = extCode == null ? null : extCode.trim();
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode == null ? null : subjectCode.trim();
    }

    public Timestamp getCreateType() {
        return createType;
    }

    public void setCreateType(Timestamp createType) {
        this.createType = createType;
    }

    public String getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(String autoRenew) {
        this.autoRenew = autoRenew == null ? null : autoRenew.trim();
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public String getDeductMode() {
        return deductMode;
    }

    public void setDeductMode(String deductMode) {
        this.deductMode = deductMode == null ? null : deductMode.trim();
    }

    public String getFeeList() {
        return feeList;
    }

    public void setFeeList(String feeList) {
        this.feeList = feeList == null ? null : feeList.trim();
    }
}