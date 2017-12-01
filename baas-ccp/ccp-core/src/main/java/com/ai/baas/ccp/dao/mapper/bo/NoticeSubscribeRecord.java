package com.ai.baas.ccp.dao.mapper.bo;

import java.sql.Timestamp;

public class NoticeSubscribeRecord {
    private String seqId;

    private String tenantId;

    private String subscribeId;

    private String recordId;

    private Timestamp reqTime;

    private Timestamp reqAckTime;

    private String reqAckBody;

    private String reqAckStatus;

    private String reqAckMessage;

    private String state;

    private String stateDesc;

    private Timestamp stateChgTime;

    private Timestamp createTime;

    private String remark;

    private Integer sendTimes;

    private String relaSeq;

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId == null ? null : seqId.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getSubscribeId() {
        return subscribeId;
    }

    public void setSubscribeId(String subscribeId) {
        this.subscribeId = subscribeId == null ? null : subscribeId.trim();
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    public Timestamp getReqTime() {
        return reqTime;
    }

    public void setReqTime(Timestamp reqTime) {
        this.reqTime = reqTime;
    }

    public Timestamp getReqAckTime() {
        return reqAckTime;
    }

    public void setReqAckTime(Timestamp reqAckTime) {
        this.reqAckTime = reqAckTime;
    }

    public String getReqAckBody() {
        return reqAckBody;
    }

    public void setReqAckBody(String reqAckBody) {
        this.reqAckBody = reqAckBody == null ? null : reqAckBody.trim();
    }

    public String getReqAckStatus() {
        return reqAckStatus;
    }

    public void setReqAckStatus(String reqAckStatus) {
        this.reqAckStatus = reqAckStatus == null ? null : reqAckStatus.trim();
    }

    public String getReqAckMessage() {
        return reqAckMessage;
    }

    public void setReqAckMessage(String reqAckMessage) {
        this.reqAckMessage = reqAckMessage == null ? null : reqAckMessage.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getStateDesc() {
        return stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc == null ? null : stateDesc.trim();
    }

    public Timestamp getStateChgTime() {
        return stateChgTime;
    }

    public void setStateChgTime(Timestamp stateChgTime) {
        this.stateChgTime = stateChgTime;
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

    public Integer getSendTimes() {
        return sendTimes;
    }

    public void setSendTimes(Integer sendTimes) {
        this.sendTimes = sendTimes;
    }

    public String getRelaSeq() {
        return relaSeq;
    }

    public void setRelaSeq(String relaSeq) {
        this.relaSeq = relaSeq == null ? null : relaSeq.trim();
    }
}