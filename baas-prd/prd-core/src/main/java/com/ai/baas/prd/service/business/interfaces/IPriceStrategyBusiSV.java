package com.ai.baas.prd.service.business.interfaces;

import com.ai.baas.prd.api.strategy.params.CheckPolicyParam;
import com.ai.baas.prd.api.strategy.params.DeleteStrategyParam;
import com.ai.baas.prd.api.strategy.params.PriceStrategyQueryReponse;
import com.ai.baas.prd.api.strategy.params.QueryDetailParam;
import com.ai.baas.prd.api.strategy.params.QueryParams;
import com.ai.baas.prd.api.strategy.params.StrategyAddParams;
import com.ai.baas.prd.api.strategy.params.StrategyDetailQueryReponse;
import com.ai.opt.base.vo.BaseResponse;

public interface IPriceStrategyBusiSV {
	
	public void addStrategy(StrategyAddParams vo);

	public PriceStrategyQueryReponse queryStrategy(QueryParams params);
	
	public StrategyDetailQueryReponse getStrategyDetail(QueryDetailParam params);
	
	public BaseResponse deleteStrategy(DeleteStrategyParam vo);
	
	public BaseResponse checkExistPolicyName(CheckPolicyParam vo);
}
