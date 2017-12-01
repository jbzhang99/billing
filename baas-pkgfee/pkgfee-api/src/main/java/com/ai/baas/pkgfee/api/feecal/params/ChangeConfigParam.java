package com.ai.baas.pkgfee.api.feecal.params;

import java.io.Serializable;

public class ChangeConfigParam implements Serializable {
	private static final long serialVersionUID = 4186034493569596258L;
	private String tenantId;
	private String custId;
	private String subsId;
	private String productId;
	private String instanceId;
	private String changeAfterPrice;
	private String changeTime;

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

	public String getChangeAfterPrice() {
		return changeAfterPrice;
	}

	public void setChangeAfterPrice(String changeAfterPrice) {
		this.changeAfterPrice = changeAfterPrice;
	}

	public String getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

}
