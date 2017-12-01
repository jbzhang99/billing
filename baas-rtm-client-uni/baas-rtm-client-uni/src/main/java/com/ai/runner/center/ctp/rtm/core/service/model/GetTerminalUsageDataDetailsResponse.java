package com.ai.runner.center.ctp.rtm.core.service.model;

import java.io.Serializable;
import java.util.List;

public class GetTerminalUsageDataDetailsResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5399318615961914036L;
	private int totalPages;
	private String timestamp;
	private String build;
	private String version;
	private String correlationId;
	private List<GetTerminalUsageDataDetail> getTerminalUsageDataDetails;
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getBuild() {
		return build;
	}
	public void setBuild(String build) {
		this.build = build;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	public List<GetTerminalUsageDataDetail> getGetTerminalUsageDataDetails() {
		return getTerminalUsageDataDetails;
	}
	public void setGetTerminalUsageDataDetails(
			List<GetTerminalUsageDataDetail> getTerminalUsageDataDetails) {
		this.getTerminalUsageDataDetails = getTerminalUsageDataDetails;
	}
	
	
	
	
}
