package com.ai.citic.billing.web.model.strategy;

public class StrategyAddVo {

	private String policyId;
	private String policyName;
	private String policyType;
	private String detailListStr;
	
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
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public String getDetailListStr() {
		return detailListStr;
	}
	public void setDetailListStr(String detailListStr) {
		this.detailListStr = detailListStr;
	}
}
