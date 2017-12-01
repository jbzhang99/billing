package com.ai.baas.amc.dao.mapper.bo;

public class AmcDrBillSubjectMap {
    private String tenantId;

    private String drSubject;

    private String billSubject;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getDrSubject() {
        return drSubject;
    }

    public void setDrSubject(String drSubject) {
        this.drSubject = drSubject == null ? null : drSubject.trim();
    }

    public String getBillSubject() {
        return billSubject;
    }

    public void setBillSubject(String billSubject) {
        this.billSubject = billSubject == null ? null : billSubject.trim();
    }
}