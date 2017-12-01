package com.ai.baas.amc.api.amcdrbillsubject.param;

import java.io.Serializable;

public class DrSubjectParamVO implements Serializable {

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
