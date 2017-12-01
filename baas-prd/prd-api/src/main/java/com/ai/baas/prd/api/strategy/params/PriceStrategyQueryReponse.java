package com.ai.baas.prd.api.strategy.params;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class PriceStrategyQueryReponse extends BaseResponse{
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 租户ID
     */
    private String tenantId;

    private PageInfo<StartegyRecordVO> records;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public PageInfo<StartegyRecordVO> getRecords() {
		return records;
	}

	public void setRecords(PageInfo<StartegyRecordVO> records) {
		this.records = records;
	}
}
