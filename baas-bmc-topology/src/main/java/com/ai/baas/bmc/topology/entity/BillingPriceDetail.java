package com.ai.baas.bmc.topology.entity;

import java.util.Map;

public class BillingPriceDetail {
	
	private Map<String, String> data;
	private Map<String,String> priceDetail;
	//private String detailCode;
	
	public Map<String, String> getData() {
		return data;
	}
	
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	
	public Map<String, String> getPriceDetail() {
		return priceDetail;
	}
	
	public void setPriceDetail(Map<String, String> priceDetail) {
		this.priceDetail = priceDetail;
	}

//	public String getDetailCode() {
//		return detailCode;
//	}
//
//	public void setDetailCode(String detailCode) {
//		this.detailCode = detailCode;
//	}
	
}
