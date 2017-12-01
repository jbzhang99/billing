package com.ai.baas.prd.api.strategy.params;

import java.io.Serializable;
import java.util.List;

public class PolicyAddVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 策略类型
	 */
	private String policyType;
	
	/**
	 * 详情列表
	 */
	private List<DetailAddVO> variableVOs;

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public List<DetailAddVO> getVariableVOs() {
		return variableVOs;
	}

	public void setVariableVOs(List<DetailAddVO> variableVOs) {
		this.variableVOs = variableVOs;
	}
}
