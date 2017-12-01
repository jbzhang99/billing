package com.ai.baas.prd.api.strategy.params;

import com.ai.opt.base.vo.BaseInfo;

public class CheckPolicyParam extends BaseInfo {
    private static final long serialVersionUID = 1L;
    private String policyName;
	
    public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
}
