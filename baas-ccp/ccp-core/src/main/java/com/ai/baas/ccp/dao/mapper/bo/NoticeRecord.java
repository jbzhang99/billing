package com.ai.baas.ccp.dao.mapper.bo;

import java.sql.Timestamp;

public class NoticeRecord {
    private String recordId;

    private String subjectId;

    private String tenantId;

    private String custId;

    private String subsId;

    private String reqBody;

    private String reqBody2;

    private String reqBody3;

    private Timestamp createTime;

    private String remark;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId == null ? null : custId.trim();
    }

    public String getSubsId() {
        return subsId;
    }

    public void setSubsId(String subsId) {
        this.subsId = subsId == null ? null : subsId.trim();
    }

    public String getReqBody() {
        return reqBody;
    }

    public void setReqBody(String reqBody) {
        this.reqBody = reqBody == null ? null : reqBody.trim();
    }

    public String getReqBody2() {
        return reqBody2;
    }

    public void setReqBody2(String reqBody2) {
        this.reqBody2 = reqBody2 == null ? null : reqBody2.trim();
    }

    public String getReqBody3() {
        return reqBody3;
    }

    public void setReqBody3(String reqBody3) {
        this.reqBody3 = reqBody3 == null ? null : reqBody3.trim();
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
}