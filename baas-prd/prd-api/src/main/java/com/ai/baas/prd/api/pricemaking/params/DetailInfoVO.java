package com.ai.baas.prd.api.pricemaking.params;

import java.io.Serializable;

public class DetailInfoVO implements Serializable {
	private static final long serialVersionUID = -8848795456817924586L;
	private String tenantId;
	private String detailId;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

}
