package com.ai.baas.omc.api.rule.params;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ai.baas.omc.api.rule.interfaces.IOmcRuleSV;
import com.ai.opt.base.vo.BaseInfo;

/**
 * 信控规则管理入参
 *
 * Date: 2016年3月30日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author bixy
 */
public class OmcRuleInfoVO extends BaseInfo {

	private static final long serialVersionUID = 1L;
	/**
	 * 消息流水<br>
	 * 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号<br>
	 * 必填<br>
	 * VARCHAR(32)
	 */
	@NotNull(message = "消息流水号不能为空")
	private String tradeSeq;

	/**
	 * 信控规则id
	 */
	@NotNull(message = "信控规则id不能为空", groups = { IOmcRuleSV.updateRule.class, IOmcRuleSV.delRule.class })
	private Long ruleId;

	/**
	 * 信控规则名称
	 */
	@NotNull(message = "信控规则名称不能为空", groups = { IOmcRuleSV.addRule.class, IOmcRuleSV.updateRule.class })
	@Size(max = 40, message = "信控规则名称最大长度不能超过40", groups = { IOmcRuleSV.addRule.class, IOmcRuleSV.updateRule.class })
	private String ruleName;

	/**
	 * 催缴金额
	 */
	@NotNull(message = "催缴值金额不能为空", groups = { IOmcRuleSV.addRule.class, IOmcRuleSV.updateRule.class })
	private Long pressPayment;

	/**
	 * 信控规则描述
	 */
	@Size(max = 128, message = "信控规则描述最大长度不能超过128", groups = { IOmcRuleSV.addRule.class, IOmcRuleSV.updateRule.class })
	private String description;

	public String getTradeSeq() {
		return tradeSeq;
	}

	public void setTradeSeq(String tradeSeq) {
		this.tradeSeq = tradeSeq;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public Long getPressPayment() {
		return pressPayment;
	}

	public void setPressPayment(Long pressPayment) {
		this.pressPayment = pressPayment;
	}

}
