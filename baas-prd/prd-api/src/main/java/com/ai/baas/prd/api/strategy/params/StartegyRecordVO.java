package com.ai.baas.prd.api.strategy.params;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;

public class StartegyRecordVO extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String index;
	private String policyId;
	private String policyName;
	private String policyType;
	private List<VariableRecordVO> variableRecordVos;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public List<VariableRecordVO> getVariableRecordVos() {
		return variableRecordVos;
	}
	public void setVariableRecordVos(List<VariableRecordVO> variableRecordVos) {
		this.variableRecordVos = variableRecordVos;
	}
}
