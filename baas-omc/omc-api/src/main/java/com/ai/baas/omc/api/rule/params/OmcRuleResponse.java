package com.ai.baas.omc.api.rule.params;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 信控规则管理响应
 *
 * Date: 2016年3月30日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author bixy
 */
public class OmcRuleResponse extends BaseResponse {
	/**
	 * 消息流水<br>
	 * 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号<br>
	 */
	private String tradeSeq;
	/**
	 * 信控规则Id
	 */
	private Long ruleId;

	public String getTradeSeq() {
		return tradeSeq;
	}

	public void setTradeSeq(String tradeSeq) {
		this.tradeSeq = tradeSeq;
	}

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

}
