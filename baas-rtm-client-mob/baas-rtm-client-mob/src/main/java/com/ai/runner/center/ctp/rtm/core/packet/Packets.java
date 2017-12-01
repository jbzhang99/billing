package com.ai.runner.center.ctp.rtm.core.packet;


public abstract class Packets {
	
	protected String system_id;
	protected String tenant_id;

	public String getSystem_id() {
		return system_id;
	}

	public void setSystem_id(String system_id) {
		this.system_id = system_id;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}
	
}
