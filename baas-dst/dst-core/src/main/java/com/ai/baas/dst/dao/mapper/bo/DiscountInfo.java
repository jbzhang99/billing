package com.ai.baas.dst.dao.mapper.bo;

import java.sql.Timestamp;

public class DiscountInfo {
    private String discountId;

    private String tenantId;

    private String discountName;

    private String priority;

    private Timestamp effectDate;

    private Timestamp expireDate;

    private String status;

    private Timestamp createTime;

    private String remark;

    private String calcType;

    private String allPrdDiscount;

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

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName == null ? null : discountName.trim();
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority == null ? null : priority.trim();
    }

    public Timestamp getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Timestamp effectDate) {
        this.effectDate = effectDate;
    }

    public Timestamp getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Timestamp expireDate) {
        this.expireDate = expireDate;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCalcType() {
        return calcType;
    }

    public void setCalcType(String calcType) {
        this.calcType = calcType == null ? null : calcType.trim();
    }

    public String getAllPrdDiscount() {
        return allPrdDiscount;
    }

    public void setAllPrdDiscount(String allPrdDiscount) {
        this.allPrdDiscount = allPrdDiscount == null ? null : allPrdDiscount.trim();
    }
}