package com.ai.baas.omc.api.rule.params;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ai.opt.base.vo.BaseInfo;

public class GetRuleInfoParam extends BaseInfo {
	private static final long serialVersionUID = 37L;

	/**
	 * 消息流水<br>
	 * 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号<br>
	 * 必填<br>
	 * VARCHAR(32)
	 */
	@NotNull(message = "消息流水不能为空")
	private String tradeSeq;

	/**
	 * 信控规则id
	 */
	@NotNull(message = "信控规则id不能为空")
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
