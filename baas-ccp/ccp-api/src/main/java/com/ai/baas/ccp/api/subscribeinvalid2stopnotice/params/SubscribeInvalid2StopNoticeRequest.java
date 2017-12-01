package com.ai.baas.ccp.api.subscribeinvalid2stopnotice.params;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseInfo;

public class SubscribeInvalid2StopNoticeRequest extends BaseInfo {

    private static final long serialVersionUID = 1L;

    private String subsProductId;

    private String subsId;

    private String productId;

    private String exctId;

    private Timestamp activeTime;

    private Timestamp inactiveTime;

    public String getSubsProductId() {
        return subsProductId;
    }

    public void setSubsProductId(String subsProductId) {
        this.subsProductId = subsProductId;
    }

    public String getSubsId() {
        return subsId;
    }

    public void setSubsId(String subsId) {
        this.subsId = subsId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getExctId() {
        return exctId;
    }

    public void setExctId(String exctId) {
        this.exctId = exctId;
    }

    public Timestamp getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Timestamp activeTime) {
        this.activeTime = activeTime;
    }

    public Timestamp getInactiveTime() {
        return inactiveTime;
    }

    public void setInactiveTime(Timestamp inactiveTime) {
        this.inactiveTime = inactiveTime;
    }
}
