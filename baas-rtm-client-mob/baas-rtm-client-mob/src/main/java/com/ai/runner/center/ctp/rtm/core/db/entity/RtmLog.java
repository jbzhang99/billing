package com.ai.runner.center.ctp.rtm.core.db.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class RtmLog implements Serializable {

	private static final long serialVersionUID = 2858343834597861425L;
	private String service_id;
	private String tenant_id;
	private String protocol;
	private String source;
	private String path;
	private long size = 0L;
	private Timestamp create_date;
	private Timestamp start_time;
	private Timestamp end_time;
	private int total=0;

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

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Timestamp getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}

	public Timestamp getStart_time() {
		return start_time;
	}

	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}

	public Timestamp getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "RtmLog [service_id=" + service_id + ", tenant_id=" + tenant_id
				+ ", protocol=" + protocol + ", source=" + source + ", path="
				+ path + ", size=" + size + ", create_date=" + create_date
				+ ", start_time=" + start_time + ", end_time=" + end_time
				+ ", total=" + total + "]";
	}



	
	

}
