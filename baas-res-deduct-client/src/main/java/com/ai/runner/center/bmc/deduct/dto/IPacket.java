package com.ai.runner.center.bmc.deduct.dto;

import java.io.Serializable;

/**
 * Date: 2016年4月19日 <br>
 * @author zhoushanbin
 * Copyright (c) 2016 asiainfo.com <br>
 */
public class IPacket implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6514955250048900948L;
	
	/**
	 * 租户ID
	 */
	private String tenant_id;
	
	/**
	 * 系统ID
	 */
	private String system_id;

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public String getSystem_id() {
		return system_id;
	}

	public void setSystem_id(String system_id) {
		this.system_id = system_id;
	}
	
	
}
