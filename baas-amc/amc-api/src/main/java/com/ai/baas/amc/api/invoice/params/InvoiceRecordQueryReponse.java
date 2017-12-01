package com.ai.baas.amc.api.invoice.params;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class InvoiceRecordQueryReponse extends BaseResponse{
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 租户ID
     */
    private String tenantId;
    /**
	 * 用户类型   0:租户管理员   1:运营管理员
	 */
	private String userType;
	/**
	 * 账户id
	 */
	private String acctId;

    private PageInfo<InvoiceRecordVo> records;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public PageInfo<InvoiceRecordVo> getRecords() {
		return records;
	}

	public void setRecords(PageInfo<InvoiceRecordVo> records) {
		this.records = records;
	}
}
