package com.ai.baas.abm.api.invoice.params;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 发票详情查询入參
 * @author wangluyang
 *
 */
public class InvoiceQueryParam extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 账户id
	 */
	private String acctId;
	/**
	 * 用户类型   0:租户管理员   1:运营管理员
	 */
	private String userType;
	/**
	 * 发票信息id
	 */
	private String invoiceInfoId;
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getInvoiceInfoId() {
		return invoiceInfoId;
	}
	public void setInvoiceInfoId(String invoiceInfoId) {
		this.invoiceInfoId = invoiceInfoId;
	}
}
