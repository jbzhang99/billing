package com.ai.baas.omc.api.rule.params;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class QueryRuleResponse extends BaseResponse {

	private PageInfo<OmcRuleInfoVO> omcRuleInfoVOs;

	public PageInfo<OmcRuleInfoVO> getOmcRuleInfoVOs() {
		return omcRuleInfoVOs;
	}

	public void setOmcRuleInfoVOs(PageInfo<OmcRuleInfoVO> omcRuleInfoVOs) {
		this.omcRuleInfoVOs = omcRuleInfoVOs;
	}

}
