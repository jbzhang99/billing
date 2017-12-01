package com.ai.baas.pkgfee.dao.mapper.bo;

import java.sql.Timestamp;

public class DstGiftInfoDetail {
    private String discountId;

    private String tenantId;

    private String activeMode;

    private String businessPeriod;

    private Timestamp effectDate;

    private String cashAmount;

    private String virtualCoinsNum;

    private String status;

    private Timestamp createTime;

    private String operatorId;

    private String giftType;

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId == null ? null : discountId.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getActiveMode() {
        return activeMode;
    }

    public void setActiveMode(String activeMode) {
        this.activeMode = activeMode == null ? null : activeMode.trim();
    }

    public String getBusinessPeriod() {
        return businessPeriod;
    }

    public void setBusinessPeriod(String businessPeriod) {
        this.businessPeriod = businessPeriod == null ? null : businessPeriod.trim();
    }

    public Timestamp getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Timestamp effectDate) {
        this.effectDate = effectDate;
    }

    public String getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(String cashAmount) {
        this.cashAmount = cashAmount == null ? null : cashAmount.trim();
    }

    public String getVirtualCoinsNum() {
        return virtualCoinsNum;
    }

    public void setVirtualCoinsNum(String virtualCoinsNum) {
        this.virtualCoinsNum = virtualCoinsNum == null ? null : virtualCoinsNum.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    public String getGiftType() {
        return giftType;
    }

    public void setGiftType(String giftType) {
        this.giftType = giftType == null ? null : giftType.trim();
    }
}