package com.ai.baas.prd.api.strategy.params;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 定价策略保存vo
 * @author wangluyang
 *
 */
public class StrategyAddParams extends BaseInfo {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 策略id
	 */
	private String policyId;
	
	/**
	 * 策略名称
	 */
	private String policyName;
	
	/**
	 * 定价策略详情
	 */
	private PolicyAddVO policyVo;

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

	public PolicyAddVO getPolicyVo() {
		return policyVo;
	}

	public void setPolicyVo(PolicyAddVO policyVo) {
		this.policyVo = policyVo;
	}
}
