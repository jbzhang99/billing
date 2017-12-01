package com.ai.baas.bmc.srv.entity;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ai.baas.bmc.srv.flow.billing.rule.IBilling;
import com.google.common.collect.Lists;

public class BillingPriceItemGroup {
	private String chargeType;
	private IBilling billingRule;
	private List<BillingPriceItem> billPriceItems = Lists.newArrayList();

	public String getChargeType() {
		return chargeType;
	}

//	public void setChargeType(String chargeType) {
//		this.chargeType = chargeType;
//	}
	
	public void addChargeType(String chargeType){
		if(this.chargeType == null){
			this.chargeType = chargeType;
		}
	}
	
	
	public IBilling getBillingRule() {
		return billingRule;
	}

	public void addBillingRule(IBilling billing){
		if (billingRule == null) {
			billingRule = billing;
		}
	}
	
//	public void setBillingRule(IBilling billingRule) {
//		this.billingRule = billingRule;
//	}

	public List<BillingPriceItem> getBillPriceItems() {
		return billPriceItems;
	}

	public void setBillPriceItems(List<BillingPriceItem> billPriceItems) {
		this.billPriceItems = billPriceItems;
	}
	
	public void addBillPriceItem(BillingPriceItem billPriceItem){
		billPriceItems.add(billPriceItem);
	}
	
	public List<Map<String,String>> getPriceDetailAll(){
		List<Map<String,String>> priceDetails = Lists.newArrayList();
		for(BillingPriceItem priceItem:billPriceItems){
			priceDetails.add(priceItem.getPriceDetail());
		}
		return priceDetails;
	}
	
}
