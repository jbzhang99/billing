package com.ai.baas.prd.api.pricemaking.params;

import java.io.Serializable;
import java.util.List;

public class PriceMakingAddParam implements Serializable {

	private static final long serialVersionUID = -811173475962758867L;
	private String mainProductId;
	private String mainProductName;
	private List<PriceFactorVO> priceFactorList;
	private String tenantId;

	public String getMainProductId() {
		return mainProductId;
	}

	public void setMainProductId(String mainProductId) {
		this.mainProductId = mainProductId;
	}

	public String getMainProductName() {
		return mainProductName;
	}

	public void setMainProductName(String mainProductName) {
		this.mainProductName = mainProductName;
	}

	public List<PriceFactorVO> getPriceFactorList() {
		return priceFactorList;
	}

	public void setPriceFactorList(List<PriceFactorVO> priceFactorList) {
		this.priceFactorList = priceFactorList;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
}
