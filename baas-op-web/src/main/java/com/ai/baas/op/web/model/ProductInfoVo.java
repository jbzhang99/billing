package com.ai.baas.op.web.model;

import com.ai.baas.bmc.api.marktableproduct.params.ProductInfo;

import java.util.List;

/**
 * Created by wangyongxin on 2016/4/19.
 */
public class ProductInfoVo extends ProductInfo{
    private String billingTypeName;
    private List<ServiceInfo> serviceInfoList;

    public List<ServiceInfo> getServiceInfoList() {
        return serviceInfoList;
    }

    public void setServiceInfoList(List<ServiceInfo> serviceInfoList) {
        this.serviceInfoList = serviceInfoList;
    }

    public String getBillingTypeName() {
        return billingTypeName;
    }

    public void setBillingTypeName(String billingTypeName) {
        this.billingTypeName = billingTypeName;
    }

}
