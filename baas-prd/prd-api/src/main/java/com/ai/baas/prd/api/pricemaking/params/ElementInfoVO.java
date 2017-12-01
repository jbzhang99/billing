package com.ai.baas.prd.api.pricemaking.params;

import java.io.Serializable;

public class ElementInfoVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String varValue;//变量取值
	private String varName; //变量名称
	private String varCode; //变量编码

	public String getVarValue() {
		return varValue;
	}

	public void setVarValue(String varValue) {
		this.varValue = varValue;
	}

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public String getVarCode() {
		return varCode;
	}

	public void setVarCode(String varCode) {
		this.varCode = varCode;
	}
	
}
