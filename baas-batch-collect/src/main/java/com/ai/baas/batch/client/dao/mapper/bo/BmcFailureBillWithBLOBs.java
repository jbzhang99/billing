package com.ai.baas.batch.client.dao.mapper.bo;

public class BmcFailureBillWithBLOBs extends BmcFailureBill {
    private String failReason;

    private String failPacket;

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason == null ? null : failReason.trim();
    }

    public String getFailPacket() {
        return failPacket;
    }

    public void setFailPacket(String failPacket) {
        this.failPacket = failPacket == null ? null : failPacket.trim();
    }
}