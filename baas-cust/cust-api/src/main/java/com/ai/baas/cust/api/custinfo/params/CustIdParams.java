package com.ai.baas.cust.api.custinfo.params;

import com.ai.opt.base.vo.BaseInfo;

public class CustIdParams extends BaseInfo{

    private String custId;

    private String extCustId;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getExtCustId() {
        return extCustId;
    }

    public void setExtCustId(String extCustId) {
        this.extCustId = extCustId;
    }
}
