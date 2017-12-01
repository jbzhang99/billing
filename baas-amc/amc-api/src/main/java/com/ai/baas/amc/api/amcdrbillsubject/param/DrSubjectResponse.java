package com.ai.baas.amc.api.amcdrbillsubject.param;

import com.ai.opt.base.vo.BaseResponse;

public class DrSubjectResponse extends BaseResponse {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String drSubjectId;

	public String getDrSubjectId() {
		return drSubjectId;
	}

	public void setDrSubjectId(String drSubjectId) {
		this.drSubjectId = drSubjectId;
	}
}
