package com.ai.baas.prd.api.element.params;

import com.ai.opt.base.vo.BaseInfo;

public class CheckPolicyParam extends BaseInfo {
    private static final long serialVersionUID = 1L;
    private String policyId;
	
    public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
}
