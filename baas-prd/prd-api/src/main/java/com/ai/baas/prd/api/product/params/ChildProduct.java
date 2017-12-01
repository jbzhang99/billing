package com.ai.baas.prd.api.product.params;

import java.io.Serializable;

public class ChildProduct implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private String categoryName;
	
	private String categoryId;
	
	private String categoryLevel;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(String categoryLevel) {
		this.categoryLevel = categoryLevel;
	}
	
	
}
