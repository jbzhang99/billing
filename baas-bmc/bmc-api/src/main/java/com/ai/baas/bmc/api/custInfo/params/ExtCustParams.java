package com.ai.baas.bmc.api.custInfo.params;

import com.ai.opt.base.vo.BaseInfo;

public class ExtCustParams extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String custId;
	
	private String extCustId;

    private String custType;
    
    private String policyId;

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

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
}
