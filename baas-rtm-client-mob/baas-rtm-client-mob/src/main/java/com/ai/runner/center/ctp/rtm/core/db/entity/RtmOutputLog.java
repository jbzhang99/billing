package com.ai.runner.center.ctp.rtm.core.db.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class RtmOutputLog implements Serializable {

	private static final long serialVersionUID = -835041821618981662L;
	private String psn;
	private String system_id;
	private String service_id;
	private String tenant_id;
	private Integer total_send;
	private Integer finished;
	private Integer total_deal;
	private Integer total_failed;
	private Timestamp finished_time;
	private Integer audited;
	private Timestamp audit_time;
	private String path;

	public String getPsn() {
		return psn;
	}

	public void setPsn(String psn) {
		this.psn = psn;
	}

	public String getSystem_id() {
		return system_id;
	}

	public void setSystem_id(String system_id) {
		this.system_id = system_id;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public Integer getTotal_send() {
		return total_send;
	}

	public void setTotal_send(Integer total_send) {
		this.total_send = total_send;
	}

	public Integer getFinished() {
		return finished;
	}

	public void setFinished(Integer finished) {
		this.finished = finished;
	}

	public Integer getTotal_deal() {
		return total_deal;
	}

	public void setTotal_deal(Integer total_deal) {
		this.total_deal = total_deal;
	}

	public Integer getTotal_failed() {
		return total_failed;
	}

	public void setTotal_failed(Integer total_failed) {
		this.total_failed = total_failed;
	}

	public Timestamp getFinished_time() {
		return finished_time;
	}

	public void setFinished_time(Timestamp finished_time) {
		this.finished_time = finished_time;
	}

	public Integer getAudited() {
		return audited;
	}

	public void setAudited(Integer audited) {
		this.audited = audited;
	}

	public Timestamp getAudit_time() {
		return audit_time;
	}

	public void setAudit_time(Timestamp audit_time) {
		this.audit_time = audit_time;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
