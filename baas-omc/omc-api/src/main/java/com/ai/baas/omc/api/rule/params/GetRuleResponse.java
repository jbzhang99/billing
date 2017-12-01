package com.ai.baas.omc.api.rule.params;

import com.ai.opt.base.vo.BaseResponse;

public class GetRuleResponse extends BaseResponse {

	private OmcRuleInfoVO omcRuleInfoVO;

	public OmcRuleInfoVO getOmcRuleInfoVO() {
		return omcRuleInfoVO;
	}

	public void setOmcRuleInfoVO(OmcRuleInfoVO omcRuleInfoVO) {
		this.omcRuleInfoVO = omcRuleInfoVO;
	}

}
