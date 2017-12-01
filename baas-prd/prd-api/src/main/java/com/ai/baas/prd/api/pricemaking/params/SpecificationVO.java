package com.ai.baas.prd.api.pricemaking.params;

import java.io.Serializable;
import java.util.List;

public class SpecificationVO implements Serializable {

	private static final long serialVersionUID = 6379628552244286326L;
	private String specTypeId;
	private String specTypeName;
	private String billingCycle;
	private String billingRule;
	private List<PriceInfoVO> priceInfoList;

	public String getSpecTypeId() {
		return specTypeId;
	}

	public void setSpecTypeId(String specTypeId) {
		this.specTypeId = specTypeId;
	}

	public String getSpecTypeName() {
		return specTypeName;
	}

	public void setSpecTypeName(String specTypeName) {
		this.specTypeName = specTypeName;
	}

	public String getBillingCycle() {
		return billingCycle;
	}

	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
	}

	public String getBillingRule() {
		return billingRule;
	}

	public void setBillingRule(String billingRule) {
		this.billingRule = billingRule;
	}

	public List<PriceInfoVO> getPriceInfoList() {
		return priceInfoList;
	}

	public void setPriceInfoList(List<PriceInfoVO> priceInfoList) {
		this.priceInfoList = priceInfoList;
	}
	
}
