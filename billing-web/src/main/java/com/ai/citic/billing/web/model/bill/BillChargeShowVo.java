package com.ai.citic.billing.web.model.bill;

import com.ai.baas.amc.api.bill.params.BillChargeVo;

public class BillChargeShowVo {
	/**
	 * 序号
	 */
	private String index;
	private BillChargeVo billChargeVo;
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public BillChargeVo getBillChargeVo() {
		return billChargeVo;
	}
	public void setBillChargeVo(BillChargeVo billChargeVo) {
		this.billChargeVo = billChargeVo;
	}
}
