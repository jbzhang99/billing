package com.ai.citic.billing.web.model.invoice;

import com.ai.baas.amc.api.invoice.params.InvoiceRecordVo;

public class InvoiceRecordShowVo {

	/**
	 * 序号
	 */
	private String index;
	private InvoiceRecordVo record;

	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public InvoiceRecordVo getRecord() {
		return record;
	}
	public void setRecord(InvoiceRecordVo record) {
		this.record = record;
	}
}
