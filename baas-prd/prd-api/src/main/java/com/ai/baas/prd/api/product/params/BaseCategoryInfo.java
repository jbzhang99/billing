package com.ai.baas.prd.api.product.params;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

public class BaseCategoryInfo extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tenantId;
	
	private List<CategoryInfoVO> categoryInfos;

	public List<CategoryInfoVO> getCategoryInfos() {
		return categoryInfos;
	}

	public void setCategoryInfos(List<CategoryInfoVO> categoryInfos) {
		this.categoryInfos = categoryInfos;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
}
