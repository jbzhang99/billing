package com.ai.baas.bmc.topology.billing.rule;

import com.ai.baas.bmc.topology.entity.BillingPriceDetail;
import com.ai.baas.bmc.topology.entity.SubjectAndPrice;
import com.ai.baas.storm.exception.BusinessException;

public interface IBilling {

	public SubjectAndPrice calculate(BillingPriceDetail priceDetail) throws BusinessException;
	
}

