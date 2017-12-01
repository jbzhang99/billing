package com.ai.baas.prd.api.strategy.params;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 定价策略保存vo
 * @author wangluyang
 *
 */
public class StrategyShowVO extends BaseInfo {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 策略id
	 */
	private String policyId;
	
	/**
	 * 策略名称
	 */
	private String policyName;
	
	private List<VariableRecordVO> variableRecordVos;
	
	/**
	 * 定价策略详情
	 */
	private PolicyShowVO policyVo;

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

	public PolicyShowVO getPolicyVo() {
		return policyVo;
	}

	public void setPolicyVo(PolicyShowVO policyVo) {
		this.policyVo = policyVo;
	}

	public List<VariableRecordVO> getVariableRecordVos() {
		return variableRecordVos;
	}

	public void setVariableRecordVos(List<VariableRecordVO> variableRecordVos) {
		this.variableRecordVos = variableRecordVos;
	}
}
