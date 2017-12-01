package com.ai.baas.amc.api.billmanage.params;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 账单科目添加出参
 *
 * Date: 2016年3月30日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
public class BillResponse extends BaseResponse{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 租户Id
	 */
	private String tenantId;
	/**
     * 消息流水<br>
     * 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号<br>
     */
	private String tradeSeq;
	/**
	 * 账单Id
	 */
	private String billId;
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getTradeSeq() {
		return tradeSeq;
	}
	public void setTradeSeq(String tradeSeq) {
		this.tradeSeq = tradeSeq;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	
}
