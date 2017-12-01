package com.ai.baas.batch.client.dao.mapper.bo;

import java.sql.Timestamp;

public class BmcOrderLogYyyymm {
    private Timestamp createTime;

    private String userId;

    private String orderRecord;

    private String orderId;

    private String remark;

    private String tag;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getOrderRecord() {
        return orderRecord;
    }

    public void setOrderRecord(String orderRecord) {
        this.orderRecord = orderRecord == null ? null : orderRecord.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }
}