package com.ai.runner.center.ctp.rtm.core.db.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class RtmFailureBill implements Serializable {

	private static final long serialVersionUID = 8764829056689251954L;
	private String service_id;
	private String tenant_id;
	private String source;
	private String error_txt;
	private int error_row_num;
	private String error_msg;
	private Timestamp inst_time;
	private String recycle_flag;
	private Timestamp recycle_time;

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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getError_txt() {
		return error_txt;
	}

	public void setError_txt(String error_txt) {
		this.error_txt = error_txt;
	}

	public int getError_row_num() {
		return error_row_num;
	}

	public void setError_row_num(int error_row_num) {
		this.error_row_num = error_row_num;
	}

	public Timestamp getInst_time() {
		return inst_time;
	}

	public void setInst_time(Timestamp inst_time) {
		this.inst_time = inst_time;
	}

	public String getRecycle_flag() {
		return recycle_flag;
	}

	public void setRecycle_flag(String recycle_flag) {
		this.recycle_flag = recycle_flag;
	}

	public Timestamp getRecycle_time() {
		return recycle_time;
	}

	public void setRecycle_time(Timestamp recycle_time) {
		this.recycle_time = recycle_time;
	}

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

}
