package com.ai.baas.amc.dao.mapper.bo;

import java.sql.Timestamp;

public class AmcCcChargeYyyydd {
    private Long ccChargeSeq;

    private String tenantId;

    private String costCenterId;

    private Long subjectId;

    private String apportionAcctId;

    private Long amount;

    private Timestamp lastDate;
    
    private String billMonth;

    public String getBillMonth() {
		return billMonth;
	}

	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}

	public Long getCcChargeSeq() {
        return ccChargeSeq;
    }

    public void setCcChargeSeq(Long ccChargeSeq) {
        this.ccChargeSeq = ccChargeSeq;
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Timestamp getLastDate() {
        return lastDate;
    }

    public void setLastDate(Timestamp lastDate) {
        this.lastDate = lastDate;
    }
}