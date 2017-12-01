package com.ai.baas.dst.api.coupon.params;

import java.io.Serializable;
/**
 * 优惠条件的详情
 *
 * Date: 2017年2月16日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * @author gaogang
 */
public class ConditionDetail implements Serializable {

	
	private static final long serialVersionUID = 1L;
	/**
	 * 优惠达到的金额
	 */
	private String reachAmount;
	/**
	 * 金额单位
	 */
	private String reachUnit;
	/**
	 * 打折的类型单位
	 */
	private String dstTypeUnit;
	/**
	 * 打折的值
	 */
	private String dstValue;
	/**
	 * 打折的单位
	 */
	private String dstUnit;
	public String getReachAmount() {
		return reachAmount;
	}
	public void setReachAmount(String reachAmount) {
		this.reachAmount = reachAmount;
	}
	public String getReachUnit() {
		return reachUnit;
	}
	public void setReachUnit(String reachUnit) {
		this.reachUnit = reachUnit;
	}
	
	public String getDstValue() {
		return dstValue;
	}
	public void setDstValue(String dstValue) {
		this.dstValue = dstValue;
	}
	public String getDstUnit() {
		return dstUnit;
	}
	public void setDstUnit(String dstUnit) {
		this.dstUnit = dstUnit;
	}
	public String getDstTypeUnit() {
		return dstTypeUnit;
	}
	public void setDstTypeUnit(String dstTypeUnit) {
		this.dstTypeUnit = dstTypeUnit;
	}
	
}
