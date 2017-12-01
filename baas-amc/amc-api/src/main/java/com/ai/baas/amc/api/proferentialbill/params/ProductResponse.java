package com.ai.baas.amc.api.proferentialbill.params;

import com.ai.opt.base.vo.BaseResponse;

public class ProductResponse extends BaseResponse {
	
	private static final long serialVersionUID = 1L;
	/**
     * 消息流水<br>
     * 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号<br>
     */
	private String tradeSeq;
	/**
	 * 租户Id
	 */
	private String tenantId;
	/**
	 * 账单Id
	 */
	private String BillId;
	public String getTradeSeq() {
		return tradeSeq;
	}
	public void setTradeSeq(String tradeSeq) {
		this.tradeSeq = tradeSeq;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getBillId() {
		return BillId;
	}
	public void setBillId(String billId) {
		BillId = billId;
	}
	
}
