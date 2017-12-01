package com.ai.baas.rtm.dao;

public class RtmAuthInfo {
	/**
	 * 租户id
	 */
	String tenantId;
	/**
	 * 用户id
	 */
	String userId;
	/**
	 * 用户密码
	 */
	String passWd;
	/**
	 * id
	 */
	String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassWd() {
		return passWd;
	}
	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}
}
