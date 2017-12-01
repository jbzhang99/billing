package com.ai.citic.billing.web.model.deposit;

import com.ai.baas.amc.api.queryinoutfundserial.params.FundSerialInfo;

public class FundSerialShowInfo {

	private String index;
	private FundSerialInfo record;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public FundSerialInfo getRecord() {
		return record;
	}
	public void setRecord(FundSerialInfo record) {
		this.record = record;
	}
}
