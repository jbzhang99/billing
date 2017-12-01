package com.ai.baas.bmc.srv.flow.billing.rule.impl;

import java.util.Map;

import com.ai.baas.bmc.srv.entity.BillingPriceParam;
import com.ai.baas.bmc.srv.entity.BillingPriceResult;
import com.ai.baas.bmc.srv.failbill.BusinessException;
import com.ai.baas.bmc.srv.flow.billing.rule.IBilling;

public class Unit implements IBilling {

	
	
	@Override
	public boolean checkPrice(String detailCode, Map<String, String> data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BillingPriceResult calculate(BillingPriceParam priceDetail)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
