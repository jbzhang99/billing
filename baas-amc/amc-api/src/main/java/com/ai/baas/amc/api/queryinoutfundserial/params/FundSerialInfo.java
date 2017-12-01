package com.ai.baas.amc.api.queryinoutfundserial.params;

import java.io.Serializable;
import java.sql.Timestamp;

public class FundSerialInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 资金总订单流水
     */
    private String paySerialCode;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 业务订单流水号
     */
    private String peerSerialCode;

    /**
     * 冲正资金总流水号
     */
    private String cancelSerialCode;

    /**
     * 操作类型
     */
    private String optType;

    /**
     * 交易金额
     */
    private Long totalAmount;

    /**
     * 交易摘要
     */
    private String transSummary;

    /**
     * 交易状态
     */
    private String payStatus;

    /**
     * 交易客户ID1
     */
    private String custId1;

    /**
     * 交易账户ID1
     */
    private String acctId1;

    /**
     * 交易账户姓名1
     */
    private String acctName1;

    /**
     * 交易客户ID2
     */
    private String custId2;

    /**
     * 交易账户ID2
     */
    private String acctId2;

    /**
     * 交易账户姓名2
     */
    private String acctName2;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 交易时间
     */
    private Timestamp payTime;

    /**
     * 状态日期
     */
    private Timestamp lastStatusDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 外部客户id（中信租户id）
     */
    private String extCustId;

    /**
     * 租户名称
     */
    private String tenantName;

    /**
     * 银行流水号
     */
    private String bandSerialCode;

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getBandSerialCode() {
        return bandSerialCode;
    }

    public void setBandSerialCode(String bandSerialCode) {
        this.bandSerialCode = bandSerialCode;
    }

    public String getPaySerialCode() {
        return paySerialCode;
    }

    public void setPaySerialCode(String paySerialCode) {
        this.paySerialCode = paySerialCode == null ? null : paySerialCode.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getPeerSerialCode() {
        return peerSerialCode;
    }

    public void setPeerSerialCode(String peerSerialCode) {
        this.peerSerialCode = peerSerialCode == null ? null : peerSerialCode.trim();
    }

    public String getCancelSerialCode() {
        return cancelSerialCode;
    }

    public void setCancelSerialCode(String cancelSerialCode) {
        this.cancelSerialCode = cancelSerialCode == null ? null : cancelSerialCode.trim();
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType == null ? null : optType.trim();
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTransSummary() {
        return transSummary;
    }

    public void setTransSummary(String transSummary) {
        this.transSummary = transSummary == null ? null : transSummary.trim();
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public String getCustId1() {
        return custId1;
    }

    public void setCustId1(String custId1) {
        this.custId1 = custId1 == null ? null : custId1.trim();
    }

    public String getAcctId1() {
        return acctId1;
    }

    public void setAcctId1(String acctId1) {
        this.acctId1 = acctId1 == null ? null : acctId1.trim();
    }

    public String getAcctName1() {
        return acctName1;
    }

    public void setAcctName1(String acctName1) {
        this.acctName1 = acctName1 == null ? null : acctName1.trim();
    }

    public String getCustId2() {
        return custId2;
    }

    public void setCustId2(String custId2) {
        this.custId2 = custId2 == null ? null : custId2.trim();
    }

    public String getAcctId2() {
        return acctId2;
    }

    public void setAcctId2(String acctId2) {
        this.acctId2 = acctId2 == null ? null : acctId2.trim();
    }

    public String getAcctName2() {
        return acctName2;
    }

    public void setAcctName2(String acctName2) {
        this.acctName2 = acctName2 == null ? null : acctName2.trim();
    }

    public Timestamp getCreateTime() {
        return new Timestamp(createTime.getTime());
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = new Timestamp(createTime.getTime());
    }

    public Timestamp getPayTime() {
        return new Timestamp(payTime.getTime());
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = new Timestamp(payTime.getTime());
    }

    public Timestamp getLastStatusDate() {
        return new Timestamp(lastStatusDate.getTime());
    }

    public void setLastStatusDate(Timestamp lastStatusDate) {
        this.lastStatusDate = new Timestamp(lastStatusDate.getTime());
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getExtCustId() {
        return extCustId;
    }

    public void setExtCustId(String extCustId) {
        this.extCustId = extCustId;
    }

}
