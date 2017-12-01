package com.ai.opt.sys.api.tenant.param;

import com.ai.opt.base.vo.BaseInfo;

public class TenantInfoVo extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 公司名称(必填)
	 */
	private String tenantName;
	
	/**
	 * 公司类型(必填)
	 */
	private String industryCode;

	/**
	 * 狀態
	 */
	private String state;

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
