package com.ai.citic.billing.web.model.policy;

import java.util.List;

public class PolicyAddVo{
	
	private String ruleId;
	private String ruleName;
	private List<String> custIds;
    private String pressPayment;
    private String description;
    
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public List<String> getCustIds() {
		return custIds;
	}
	public void setCustIds(List<String> custIds) {
		this.custIds = custIds;
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
