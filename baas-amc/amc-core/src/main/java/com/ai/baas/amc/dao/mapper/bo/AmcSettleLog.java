package com.ai.baas.amc.dao.mapper.bo;

import java.sql.Timestamp;

public class AmcSettleLog extends AmcSettleLogKey {
    private String busiOperCode;

    private String acctId;

    private Integer settleMode;

    private Integer settleType;

    private Long total;

    private Integer status;

    private Timestamp lastStatusDate;

    private String cancelSerialCode;

    private Timestamp createTime;
    private String billMonth;
    
    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }
    public String getBusiOperCode() {
        return busiOperCode;
    }

    public void setBusiOperCode(String busiOperCode) {
        this.busiOperCode = busiOperCode == null ? null : busiOperCode.trim();
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId == null ? null : acctId.trim();
    }

    public Integer getSettleMode() {
        return settleMode;
    }

    public void setSettleMode(Integer settleMode) {
        this.settleMode = settleMode;
    }

    public Integer getSettleType() {
        return settleType;
    }

    public void setSettleType(Integer settleType) {
        this.settleType = settleType;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getLastStatusDate() {
        return lastStatusDate;
    }

    public void setLastStatusDate(Timestamp lastStatusDate) {
        this.lastStatusDate = lastStatusDate;
    }

    public String getCancelSerialCode() {
        return cancelSerialCode;
    }

    public void setCancelSerialCode(String cancelSerialCode) {
        this.cancelSerialCode = cancelSerialCode == null ? null : cancelSerialCode.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}