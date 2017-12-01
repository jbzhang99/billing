package com.ai.baas.job.vo;

public class StepIdInfo {
	private double sectionA;
	private double sectionB;
	private double priceValue;
	private double totalPriceValue;
	private String detailCode;

	public String getDetailCode() {
		return detailCode;
	}
	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}
	public double getSectionA() {
		return sectionA;
	}
	public void setSectionA(double sectionA) {
		this.sectionA = sectionA;
	}
	public double getSectionB() {
		return sectionB;
	}
	public void setSectionB(double sectionB) {
		this.sectionB = sectionB;
	}
	public double getPriceValue() {
		return priceValue;
	}
	public void setPriceValue(double priceValue) {
		this.priceValue = priceValue;
	}
	public double getTotalPriceValue() {
		return totalPriceValue;
	}
	public void setTotalPriceValue(double totalPriceValue) {
		this.totalPriceValue = totalPriceValue;
	}
	
}
