package com.ai.baas.amc.dao.mapper.bo;

/**
 * 客户欠费查询参数
 *
 * Date: 2016年4月15日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class OweInfoQueryParam {

    private String tenantId;
    
    private String custId;
    
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
}
