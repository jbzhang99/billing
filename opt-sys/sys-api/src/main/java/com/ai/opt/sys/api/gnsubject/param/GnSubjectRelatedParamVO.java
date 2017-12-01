package com.ai.opt.sys.api.gnsubject.param;

import java.io.Serializable;
import java.util.List;

public class GnSubjectRelatedParamVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tenantId;
	private String billSubjectId;
	private List<GnSubjectDrSubjectIdParamVO> drSubjectParamVOList;
	
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
	public List<GnSubjectDrSubjectIdParamVO> getDrSubjectParamVOList() {
		return drSubjectParamVOList;
	}
	public void setDrSubjectParamVOList(List<GnSubjectDrSubjectIdParamVO> drSubjectParamVOList) {
		this.drSubjectParamVOList = drSubjectParamVOList;
	}
}
