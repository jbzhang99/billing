package com.ai.baas.prd.api.element.params;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

public class BaseSpecResponse extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tenantId;
	
	private List<PmSpecTypeRes> specTypes;

	public List<PmSpecTypeRes> getSpecTypes() {
		return specTypes;
	}

	public void setSpecTypes(List<PmSpecTypeRes> specTypes) {
		this.specTypes = specTypes;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
}
