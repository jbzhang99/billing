package com.ai.runner.center.ctp.rtm.core.persistence.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class RtmOutputLog implements Serializable {

	private static final long serialVersionUID = -7590215688705805654L;
	private String psn;
	private String systemId;
	private String tenantId;
	private String serviceId;
	private Integer totalSend;
	private Integer finished;
	private Timestamp finishedTime;
	private String path;

	public String getPsn() {
		return psn;
	}

	public void setPsn(String psn) {
		this.psn = psn;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getTotalSend() {
		return totalSend;
	}

	public void setTotalSend(Integer totalSend) {
		this.totalSend = totalSend;
	}

	public Integer getFinished() {
		return finished;
	}

	public void setFinished(Integer finished) {
		this.finished = finished;
	}

	public Timestamp getFinishedTime() {
		return finishedTime;
	}

	public void setFinishedTime(Timestamp finishedTime) {
		this.finishedTime = finishedTime;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
