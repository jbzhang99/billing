package com.ai.baas.op.web.model;

import com.ai.baas.bmc.api.marktableproduct.params.ServiceVO;

/**
 * Created by wangyongxin on 2016/4/19.
 */
public class ServiceInfo extends ServiceVO{
    
    private static final long serialVersionUID = 3016888011372651392L;
    
    private String usageAmountName;
    private String serviceDetailDesc;
    private String serviceTypeDesc;

    public String getUsageAmountName() {
        return usageAmountName;
    }

    public void setUsageAmountName(String usageAmountName) {
        this.usageAmountName = usageAmountName;
    }

    public String getServiceDetailDesc() {
        return serviceDetailDesc;
    }

    public void setServiceDetailDesc(String serviceDetailDesc) {
        this.serviceDetailDesc = serviceDetailDesc;
    }

    public String getServiceTypeDesc() {
        return serviceTypeDesc;
    }

    public void setServiceTypeDesc(String serviceTypeDesc) {
        this.serviceTypeDesc = serviceTypeDesc;
    }
}
