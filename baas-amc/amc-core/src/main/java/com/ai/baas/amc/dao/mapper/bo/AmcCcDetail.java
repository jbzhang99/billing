package com.ai.baas.amc.dao.mapper.bo;

import java.sql.Timestamp;

public class AmcCcDetail {
    private Long ccDetailSeq;

    private String tenantId;

    private String costCenterId;

    private Long subjectId;

    private String apportionAcctId;

    private String apportionMethod;

    private String apportionValue;

    private Long amount;

    private String acctId;

    private Long custId;

    private Long subsId;

    private String isApportion;

    private String drKey;

    private Timestamp createDate;

    public Long getCcDetailSeq() {
        return ccDetailSeq;
    }

    public void setCcDetailSeq(Long ccDetailSeq) {
        this.ccDetailSeq = ccDetailSeq;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getCostCenterId() {
        return costCenterId;
    }

    public void setCostCenterId(String costCenterId) {
        this.costCenterId = costCenterId == null ? null : costCenterId.trim();
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getApportionAcctId() {
        return apportionAcctId;
    }

    public void setApportionAcctId(String apportionAcctId) {
        this.apportionAcctId = apportionAcctId == null ? null : apportionAcctId.trim();
    }

    public String getApportionMethod() {
        return apportionMethod;
    }

    public void setApportionMethod(String apportionMethod) {
        this.apportionMethod = apportionMethod == null ? null : apportionMethod.trim();
    }

    public String getApportionValue() {
        return apportionValue;
    }

    public void setApportionValue(String apportionValue) {
        this.apportionValue = apportionValue == null ? null : apportionValue.trim();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId == null ? null : acctId.trim();
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getSubsId() {
        return subsId;
    }

    public void setSubsId(Long subsId) {
        this.subsId = subsId;
    }

    public String getIsApportion() {
        return isApportion;
    }

    public void setIsApportion(String isApportion) {
        this.isApportion = isApportion == null ? null : isApportion.trim();
    }

    public String getDrKey() {
        return drKey;
    }

    public void setDrKey(String drKey) {
        this.drKey = drKey == null ? null : drKey.trim();
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}