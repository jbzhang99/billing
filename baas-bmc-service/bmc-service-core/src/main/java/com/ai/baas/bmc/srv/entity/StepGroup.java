package com.ai.baas.bmc.srv.entity;

import java.util.List;
import java.util.Map;

public class StepGroup {
	private String detailCode;
	private String stepGroup;
	private String factorCode;
	private String extCode;
	private String calType;
	private List<Map<String, String>> stepDatas;

	public String getDetailCode() {
		return detailCode;
	}

	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}

	public String getStepGroup() {
		return stepGroup;
	}

	public void setStepGroup(String stepGroup) {
		this.stepGroup = stepGroup;
	}

	public String getFactorCode() {
		return factorCode;
	}

	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}

	public String getExtCode() {
		return extCode;
	}

	public void setExtCode(String extCode) {
		this.extCode = extCode;
	}

	public String getCalType() {
		return calType;
	}

	public void setCalType(String calType) {
		this.calType = calType;
	}

	public List<Map<String, String>> getStepDatas() {
		return stepDatas;
	}

	public void setStepDatas(List<Map<String, String>> stepDatas) {
		this.stepDatas = stepDatas;
	}
	
}
