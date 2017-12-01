package com.ai.baas.pkgfee.dao.mapper.bo;

import java.sql.Timestamp;

public class DstCouponInfo {
    private String couponId;

    private String tenantId;

    private String couponName;

    private String couponType;

    private String couponValue;

    private String couponAmount;

    private Timestamp createTime;

    private Timestamp activeTime;

    private Timestamp inactiveTime;

    private String conditionValue;

    private String couponCondition;

    private String status;

    private String couponConType;

    private String productId;

    private String topMoney;

    private String productName;

    private String canApplyCount;

    private String applyCount;

    private String appliedCount;

    private String remainCount;

    private String couponRule;

    private String creatorRole;

    private String creatorName;

    private String creatorId;

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId == null ? null : couponId.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType == null ? null : couponType.trim();
    }

    public String getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(String couponValue) {
        this.couponValue = couponValue == null ? null : couponValue.trim();
    }

    public String getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(String couponAmount) {
        this.couponAmount = couponAmount == null ? null : couponAmount.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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

    public String getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue == null ? null : conditionValue.trim();
    }

    public String getCouponCondition() {
        return couponCondition;
    }

    public void setCouponCondition(String couponCondition) {
        this.couponCondition = couponCondition == null ? null : couponCondition.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCouponConType() {
        return couponConType;
    }

    public void setCouponConType(String couponConType) {
        this.couponConType = couponConType == null ? null : couponConType.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getTopMoney() {
        return topMoney;
    }

    public void setTopMoney(String topMoney) {
        this.topMoney = topMoney == null ? null : topMoney.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getCanApplyCount() {
        return canApplyCount;
    }

    public void setCanApplyCount(String canApplyCount) {
        this.canApplyCount = canApplyCount == null ? null : canApplyCount.trim();
    }

    public String getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(String applyCount) {
        this.applyCount = applyCount == null ? null : applyCount.trim();
    }

    public String getAppliedCount() {
        return appliedCount;
    }

    public void setAppliedCount(String appliedCount) {
        this.appliedCount = appliedCount == null ? null : appliedCount.trim();
    }

    public String getRemainCount() {
        return remainCount;
    }

    public void setRemainCount(String remainCount) {
        this.remainCount = remainCount == null ? null : remainCount.trim();
    }

    public String getCouponRule() {
        return couponRule;
    }

    public void setCouponRule(String couponRule) {
        this.couponRule = couponRule == null ? null : couponRule.trim();
    }

    public String getCreatorRole() {
        return creatorRole;
    }

    public void setCreatorRole(String creatorRole) {
        this.creatorRole = creatorRole == null ? null : creatorRole.trim();
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }
}