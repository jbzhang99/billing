package com.ai.citic.billing.web.model.policy;

public class PolicyInfoVo{
	
	private String policyId;
	private String policyName;
	private String extCustId;
    private String pressPayment;
    private String description;
    
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getExtCustId() {
		return extCustId;
	}
	public void setExtCustId(String extCustId) {
		this.extCustId = extCustId;
	}
	public String getPressPayment() {
		return pressPayment;
	}
	public void setPressPayment(String pressPayment) {
		this.pressPayment = pressPayment;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
