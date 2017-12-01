package com.ai.baas.job.vo;

public class StatictisInfo {
	private String tenantId;
	private String custId;
	private String prductId;
	private long subsNum;
	private double priceInfo;
	private double amount;
	
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getPrductId() {
		return prductId;
	}
	public void setPrductId(String prductId) {
		this.prductId = prductId;
	}
	public long getSubsNum() {
		return subsNum;
	}
	public void setSubsNum(long subsNum) {
		this.subsNum = subsNum;
	}
	public double getPriceInfo() {
		return priceInfo;
	}
	public void setPriceInfo(double priceInfo) {
		this.priceInfo = priceInfo;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

}
