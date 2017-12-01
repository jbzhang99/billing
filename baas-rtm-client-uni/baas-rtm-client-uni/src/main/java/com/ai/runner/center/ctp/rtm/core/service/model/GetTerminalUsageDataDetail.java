package com.ai.runner.center.ctp.rtm.core.service.model;

import java.io.Serializable;

public class GetTerminalUsageDataDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4190924549772203653L;
	
	private String iccid;
	private String cycleStartDate;
	private String billable;
	private String zone;
	private String sessionStartTime;
	private String duration;
	private String dataVolume;
	private String countryCode;
	private String serviceType;
	
	public String getIccid() {
		return iccid;
	}
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
	public String getCycleStartDate() {
		return cycleStartDate;
	}
	public void setCycleStartDate(String cycleStartDate) {
		this.cycleStartDate = cycleStartDate;
	}
	public String getBillable() {
		return billable;
	}
	public void setBillable(String billable) {
		this.billable = billable;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getSessionStartTime() {
		return sessionStartTime;
	}
	public void setSessionStartTime(String sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDataVolume() {
		return dataVolume;
	}
	public void setDataVolume(String dataVolume) {
		this.dataVolume = dataVolume;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	
		
}
