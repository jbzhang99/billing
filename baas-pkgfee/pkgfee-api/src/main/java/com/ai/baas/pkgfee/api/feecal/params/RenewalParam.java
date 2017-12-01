package com.ai.baas.pkgfee.api.feecal.params;

import java.io.Serializable;

public class RenewalParam implements Serializable {
	private static final long serialVersionUID = 376300462597948025L;
	private String tenantId;
	private String custId;
	private String subsId;
	private String productId;
	private String activeTime;
	private String inactiveTime;
	private String renewalTime;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getSubsId() {
		return subsId;
	}

	public void setSubsId(String subsId) {
		this.subsId = subsId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}

	public String getInactiveTime() {
		return inactiveTime;
	}

	public void setInactiveTime(String inactiveTime) {
		this.inactiveTime = inactiveTime;
	}

	public String getRenewalTime() {
		return renewalTime;
	}

	public void setRenewalTime(String renewalTime) {
		this.renewalTime = renewalTime;
	}

}
