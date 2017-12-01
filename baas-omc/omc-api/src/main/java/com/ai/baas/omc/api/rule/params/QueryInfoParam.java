package com.ai.baas.omc.api.rule.params;

import javax.validation.constraints.NotNull;

import com.ai.opt.base.vo.BaseInfo;

public class QueryInfoParam extends BaseInfo {
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
	 * 信控规则名称
	 */
	private String ruleName;

	/**
	 * 当前第几页,必填
	 */
	@NotNull(message = "页码不能为空")
	private Integer pageNo;

	/**
	 * 每页数据条数,必填
	 */
	@NotNull(message = "分页大小不能为空")
	private Integer pageSize;

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

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
