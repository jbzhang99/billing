package com.ai.baas.amc.dao.mapper.bo;

public class AmcProductDetail {
    private String tenantId;

    private String productId;

    private String priority;

    private String billSubjectId;

    private String refSubjectId;

    private String newSubjectId;

    private String calcCondition;

    private String calcType;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority == null ? null : priority.trim();
    }

    public String getBillSubjectId() {
        return billSubjectId;
    }

    public void setBillSubjectId(String billSubjectId) {
        this.billSubjectId = billSubjectId == null ? null : billSubjectId.trim();
    }

    public String getRefSubjectId() {
        return refSubjectId;
    }

    public void setRefSubjectId(String refSubjectId) {
        this.refSubjectId = refSubjectId == null ? null : refSubjectId.trim();
    }

    public String getNewSubjectId() {
        return newSubjectId;
    }

    public void setNewSubjectId(String newSubjectId) {
        this.newSubjectId = newSubjectId == null ? null : newSubjectId.trim();
    }

    public String getCalcCondition() {
        return calcCondition;
    }

    public void setCalcCondition(String calcCondition) {
        this.calcCondition = calcCondition == null ? null : calcCondition.trim();
    }

    public String getCalcType() {
        return calcType;
    }

    public void setCalcType(String calcType) {
        this.calcType = calcType == null ? null : calcType.trim();
    }
}