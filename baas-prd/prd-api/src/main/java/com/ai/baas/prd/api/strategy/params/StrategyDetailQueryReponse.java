package com.ai.baas.prd.api.strategy.params;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class StrategyDetailQueryReponse extends BaseResponse{
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 租户ID
     */
    private String tenantId;

    private StrategyShowVO strategyShowVO;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public StrategyShowVO getStrategyShowVO() {
		return strategyShowVO;
	}

	public void setStrategyShowVO(StrategyShowVO strategyShowVO) {
		this.strategyShowVO = strategyShowVO;
	}
}
