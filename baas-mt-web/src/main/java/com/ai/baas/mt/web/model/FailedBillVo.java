package com.ai.baas.mt.web.model;

import java.util.Map;

import com.ai.opt.base.vo.BaseInfo;

public class FailedBillVo extends BaseInfo{

	private String serviceId;
    private String source;
    private String bsn;
    private String failedCode;
    private String sn;
    private String failStep;
    private String failDate;
    private String accountPeriod;
    private String arrivalTime;
    private Map failPacket;
    private String billkeysStr;
    private String billValuesStr;
    
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getBsn() {
		return bsn;
	}
	public void setBsn(String bsn) {
		this.bsn = bsn;
	}
	public String getFailedCode() {
		return failedCode;
	}
	public void setFailedCode(String failedCode) {
		this.failedCode = failedCode;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getFailStep() {
		return failStep;
	}
	public void setFailStep(String failStep) {
		this.failStep = failStep;
	}
	public String getFailDate() {
		return failDate;
	}
	public void setFailDate(String failDate) {
		this.failDate = failDate;
	}
	public String getAccountPeriod() {
		return accountPeriod;
	}
	public void setAccountPeriod(String accountPeriod) {
		this.accountPeriod = accountPeriod;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Map getFailPacket() {
		return failPacket;
	}
	public void setFailPacket(Map failPacket) {
		this.failPacket = failPacket;
	}
	public String getBillkeysStr() {
		return billkeysStr;
	}
	public void setBillkeysStr(String billkeysStr) {
		this.billkeysStr = billkeysStr;
	}
	public String getBillValuesStr() {
		return billValuesStr;
	}
	public void setBillValuesStr(String billValuesStr) {
		this.billValuesStr = billValuesStr;
	}
	
}
