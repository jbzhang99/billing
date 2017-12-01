package com.ai.baas.bmc.srv.entity;

import java.util.Map;

import com.ai.baas.bmc.srv.flow.billing.rule.IBilling;

public class BillingPriceItem implements Comparable<BillingPriceItem> {
	private Integer priority;
	private IBilling billingRule;
	private String chargeType;
	private Map<String, String> priceDetail;

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public IBilling getBillingRule() {
		return billingRule;
	}

	public void setBillingRule(IBilling billingRule) {
		this.billingRule = billingRule;
	}

	public Map<String, String> getPriceDetail() {
		return priceDetail;
	}

	public void setPriceDetail(Map<String, String> priceDetail) {
		this.priceDetail = priceDetail;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	@Override
	public int compareTo(BillingPriceItem o) {
		return this.getPriority().compareTo(o.getPriority());
	}

}
