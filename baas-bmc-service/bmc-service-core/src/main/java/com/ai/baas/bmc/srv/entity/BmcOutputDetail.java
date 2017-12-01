package com.ai.baas.bmc.srv.entity;

import java.io.Serializable;

public class BmcOutputDetail implements Serializable {
	private static final long serialVersionUID = -3636361309411366371L;
	private Long detailCode;
	private Long infoCode;
	private String columnName;
	private String paramName;
	private String isKey;
	private Integer displayOrder;

	public Long getDetailCode() {
		return detailCode;
	}

	public void setDetailCode(Long detailCode) {
		this.detailCode = detailCode;
	}

	public Long getInfoCode() {
		return infoCode;
	}

	public void setInfoCode(Long infoCode) {
		this.infoCode = infoCode;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getIsKey() {
		return isKey;
	}

	public void setIsKey(String isKey) {
		this.isKey = isKey;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
}
