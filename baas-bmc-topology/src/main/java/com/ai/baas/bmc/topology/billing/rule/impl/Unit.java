package com.ai.baas.bmc.topology.billing.rule.impl;

import com.ai.baas.bmc.topology.billing.rule.IBilling;
import com.ai.baas.bmc.topology.cache.ChargeCache;
import com.ai.baas.bmc.topology.entity.BillingPriceDetail;
import com.ai.baas.bmc.topology.entity.SubjectAndPrice;
import com.ai.baas.storm.exception.BusinessException;

public class Unit implements IBilling {
	private ChargeCache chargeCache;
	
	public Unit(ChargeCache chargeCache){
		this.chargeCache = chargeCache;
	}
	
	@Override
	public SubjectAndPrice calculate(BillingPriceDetail priceDetail) throws BusinessException {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	

	
	
}
