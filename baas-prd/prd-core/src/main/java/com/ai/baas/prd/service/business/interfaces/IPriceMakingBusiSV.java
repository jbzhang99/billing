package com.ai.baas.prd.service.business.interfaces;

import com.ai.baas.prd.api.pricemaking.params.PriceMakingAddParam;
import com.ai.baas.prd.api.pricemaking.params.PriceMakingDelParam;

public interface IPriceMakingBusiSV {

	void addPriceMaking(PriceMakingAddParam param);
	
	void modifyPriceMaking(PriceMakingAddParam param);
	
	void deletePriceMaking(PriceMakingDelParam param);
	
}
