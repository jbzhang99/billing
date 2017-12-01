package com.ai.baas.job.vo;

import java.util.List;

public class PriceBasisInfo {
	private String tenantId;
	private String serviceType;
	private String addUpSubject;
	private String measureWordCode;
	private List<StepIdInfo> stepIdInfos;
	private String priceCode;
	
	public String getPriceCode() {
		return priceCode;
	}
	public void setPriceCode(String priceCode) {
		this.priceCode = priceCode;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public List<StepIdInfo> getStepIdInfos() {
		return stepIdInfos;
	}
	public void setStepIdInfos(List<StepIdInfo> stepIdInfos) {
		this.stepIdInfos = stepIdInfos;
	}
	public String getAddUpSubject() {
		return addUpSubject;
	}
	public void setAddUpSubject(String addUpSubject) {
		this.addUpSubject = addUpSubject;
	}
	public String getMeasureWordCode() {
		return measureWordCode;
	}
	public void setMeasureWordCode(String measureWordCode) {
		this.measureWordCode = measureWordCode;
	}
	
}
