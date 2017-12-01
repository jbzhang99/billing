package com.ai.baas.bmc.srv.flow.billing.rule;

import java.util.Map;

import com.ai.baas.bmc.srv.entity.BillingPriceParam;
import com.ai.baas.bmc.srv.entity.BillingPriceResult;
import com.ai.baas.bmc.srv.failbill.BusinessException;

public interface IBilling {

	public BillingPriceResult calculate(BillingPriceParam priceParam) throws BusinessException,Exception;
	
	public boolean checkPrice(String detailCode, Map<String, String> data);
	
}
