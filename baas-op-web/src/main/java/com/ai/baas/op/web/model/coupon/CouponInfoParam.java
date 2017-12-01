package com.ai.baas.op.web.model.coupon;

import java.io.Serializable;

public class CouponInfoParam implements Serializable {

	private static final long serialVersionUID = 1L;
	private String couponType;
	private String couponName;
	private String couponAmount;
	private String couponValue;
	private String activeTime;
	private String inactiveTime;
	private String couponConType;
	private String reachAmount;
	private String reachUnit;
	private String dstTypeUnit;
	private String dstValue;
	private String dstUnit;
	private String conditionValue;
	private String topMoney;
	private String productId;
	private String productName;
	
	
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTopMoney() {
		return topMoney;
	}

	public void setTopMoney(String topMoney) {
		this.topMoney = topMoney;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getConditionValue() {
		return conditionValue;
	}

	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(String couponAmount) {
		this.couponAmount = couponAmount;
	}

	public String getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(String couponValue) {
		this.couponValue = couponValue;
	}

	public String getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}

	public String getInactiveTime() {
		return inactiveTime;
	}

	public void setInactiveTime(String inactiveTime) {
		this.inactiveTime = inactiveTime;
	}

	public String getCouponConType() {
		return couponConType;
	}

	public void setCouponConType(String couponConType) {
		this.couponConType = couponConType;
	}

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

	public String getDstTypeUnit() {
		return dstTypeUnit;
	}

	public void setDstTypeUnit(String dstTypeUnit) {
		this.dstTypeUnit = dstTypeUnit;
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

}
