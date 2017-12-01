package com.ai.baas.bmc.api.orderinfo.params;

import com.ai.opt.base.vo.BaseInfo;

public class InitCustomerRelatedInfoReq extends BaseInfo{

    /**
     * 外部客户ID, 必填
     */
    private String extCustId;

    /**
     * 服务标识 非必填 VARCHAR(64)
     */
    private String serviceId;

    public String getExtCustId() {
        return extCustId;
    }

    public void setExtCustId(String extCustId) {
        this.extCustId = extCustId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
