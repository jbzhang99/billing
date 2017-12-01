package com.ai.baas.bmc.srv.api.entry.params;

import java.io.Serializable;
import java.util.List;

public class BillingMessage implements Serializable {
	private static final long serialVersionUID = -2137992017909757910L;
	private String tenant_id;    //租户
	private String service_type; //业务标识
	private String source;       //来源
	private String bsn;          //批次号
	private String arrival_time; //到达时间
	private List<BillingData> dataList; //业务数据

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public String getService_type() {
		return service_type;
	}

	public void setService_type(String service_type) {
		this.service_type = service_type;
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

	public String getArrival_time() {
		return arrival_time;
	}

	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}

	public List<BillingData> getDataList() {
		return dataList;
	}

	public void setDataList(List<BillingData> dataList) {
		this.dataList = dataList;
	}

}
