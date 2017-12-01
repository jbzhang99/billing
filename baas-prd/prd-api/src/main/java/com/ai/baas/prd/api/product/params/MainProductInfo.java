package com.ai.baas.prd.api.product.params;

import java.io.Serializable;

public class MainProductInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String index;
	private String paramType;
	
	private String paramCode;
	
	private String paramName;

	public String getParamType() {
		return paramType;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	
	
}
