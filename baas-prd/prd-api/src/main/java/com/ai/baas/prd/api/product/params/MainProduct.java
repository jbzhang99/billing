package com.ai.baas.prd.api.product.params;

import java.io.Serializable;
import java.util.List;

public class MainProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tenantId;
	
	private String mainProductCode;
	
	private String mainProductName;
	
	private String categoryLevel;

	private String index;
	private List<ChildProduct> childProducts;

	public List<ChildProduct> getChildProducts() {
		return childProducts;
	}

	public void setChildProducts(List<ChildProduct> childProducts) {
		this.childProducts = childProducts;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getMainProductCode() {
		return mainProductCode;
	}

	public void setMainProductCode(String mainProductCode) {
		this.mainProductCode = mainProductCode;
	}

	public String getMainProductName() {
		return mainProductName;
	}

	public void setMainProductName(String mainProductName) {
		this.mainProductName = mainProductName;
	}

	public String getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(String categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
	
}
