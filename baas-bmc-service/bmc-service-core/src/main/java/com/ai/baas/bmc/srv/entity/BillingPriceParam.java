package com.ai.baas.bmc.srv.entity;

import java.util.List;
import java.util.Map;

public class BillingPriceParam {
	private Map<String, String> data;
	private List<Map<String,String>> priceDetails;
	private String usageAmount;
	
	public Map<String, String> getData() {
		return data;
	}
	
	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public List<Map<String, String>> getPriceDetails() {
		return priceDetails;
	}

	public void setPriceDetails(List<Map<String, String>> priceDetails) {
		this.priceDetails = priceDetails;
	}

	public String getUsageAmount() {
		return usageAmount;
	}

	public void setUsageAmount(String usageAmount) {
		this.usageAmount = usageAmount;
	}

}
