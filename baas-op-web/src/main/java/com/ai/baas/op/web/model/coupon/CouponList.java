package com.ai.baas.op.web.model.coupon;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class CouponList implements Serializable {
	private static final long serialVersionUID = 1L;
	private String couponId;
	private String couponName;
	private String couponType;
	private String couponTypeName;
	private String couponAmount;
	private String couponValue;
	
	private String effectTime;
	private String couponStatus;
	private String couponStaName;
	private String couponConditon;
	private String couponCode;
	private String conditionValue;
	private String condition;
	private String topMoney;
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
	public String getConditionValue() {
		return conditionValue;
	}
	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getCouponType() {
		return couponType;
	}
	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	public String getCouponTypeName() {
		return couponTypeName;
	}
	public void setCouponTypeName(String couponTypeName) {
		this.couponTypeName = couponTypeName;
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
	
	public String getEffectTime() {
		return effectTime;
	}
	public void setEffectTime(String effectTime) {
		this.effectTime = effectTime;
	}
	public String getCouponStatus() {
		return couponStatus;
	}
	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}
	public String getCouponStaName() {
		return couponStaName;
	}
	public void setCouponStaName(String couponStaName) {
		this.couponStaName = couponStaName;
	}
	public String getCouponConditon() {
		return couponConditon;
	}
	public void setCouponConditon(String couponConditon) {
		this.couponConditon = couponConditon;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
}
