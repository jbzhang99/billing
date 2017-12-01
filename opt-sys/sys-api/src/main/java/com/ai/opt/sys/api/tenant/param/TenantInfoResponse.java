package com.ai.opt.sys.api.tenant.param;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

public class TenantInfoResponse extends BaseResponse{

	private static final long serialVersionUID = 1L;
	
	private List<TenantInfoVo> tenantInfoVo;

	public List<TenantInfoVo> getTenantInfoVo() {
		return tenantInfoVo;
	}

	public void setTenantInfoVo(List<TenantInfoVo> tenantInfoVo) {
		this.tenantInfoVo = tenantInfoVo;
	}
}
