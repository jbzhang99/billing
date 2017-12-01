package com.ai.baas.ccp.action.notice.vo;

import java.sql.Timestamp;

public class NoticeResult {

    private Timestamp reqAckTime;

    private String reqAckBody;

    private String reqAckStatus;

    private String reqAckMessage;

    private String state;

    private String stateDesc;

    public String getReqAckBody() {
        return reqAckBody;
    }

    public void setReqAckBody(String reqAckBody) {
        this.reqAckBody = reqAckBody;
    }

    public String getReqAckStatus() {
        return reqAckStatus;
    }

    public void setReqAckStatus(String reqAckStatus) {
        this.reqAckStatus = reqAckStatus;
    }

    public String getReqAckMessage() {
        return reqAckMessage;
    }

    public void setReqAckMessage(String reqAckMessage) {
        this.reqAckMessage = reqAckMessage;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateDesc() {
        return stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }

    public Timestamp getReqAckTime() {
        return reqAckTime;
    }

    public void setReqAckTime(Timestamp reqAckTime) {
        this.reqAckTime = reqAckTime;
    }

}
