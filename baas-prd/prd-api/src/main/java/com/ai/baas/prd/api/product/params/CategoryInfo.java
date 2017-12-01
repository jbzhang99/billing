package com.ai.baas.prd.api.product.params;

import java.io.Serializable;

public class CategoryInfo implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private String categoryName;
	private String index;
	private String categoryId;
	
	private String categoryLevel;
	
	private String members;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

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

	public String getMembers() {
		return members;
	}

	public void setMembers(String members) {
		this.members = members;
	}
	
	
	
}
