package com.ai.baas.amc.api.amcdrbillsubject.param;

import java.io.Serializable;
import java.util.List;

public class AmcDrBillSubjectRelatedParamVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tenantId;
	private String billSubjectId;
	private List<DrSubjectParamVO> drSubjectParamVOList;
	
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
	public List<DrSubjectParamVO> getDrSubjectParamVOList() {
		return drSubjectParamVOList;
	}
	public void setDrSubjectParamVOList(List<DrSubjectParamVO> drSubjectParamVOList) {
		this.drSubjectParamVOList = drSubjectParamVOList;
	}
}
