package com.ai.baas.prd.api.convert.params;

import com.ai.opt.base.vo.BaseInfo;

public class PolicyParams extends BaseInfo{
    private String policyId;

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }
}
