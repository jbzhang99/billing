package com.ai.baas.amc.api.amcdrbillsubject.param;

import java.io.Serializable;

public class QueryDrSubjectParamVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tenantId;

	private String billSubjectId;
	
	
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getBillSubjectId() {
		return billSubjectId;
	}

	public void setBillSubjectId(String billSubjectId) {
		this.billSubjectId = billSubjectId;
	}

}
