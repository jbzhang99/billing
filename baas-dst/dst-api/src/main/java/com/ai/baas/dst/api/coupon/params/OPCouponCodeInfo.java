package com.ai.baas.dst.api.coupon.params;

import java.io.Serializable;
import java.sql.Timestamp;

public class OPCouponCodeInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	/*
	 * 优惠券id
	 * */
	private String couponId;
	/*
	 * 优惠码
	 * */
	private String couponCode;
	/*
	 * 优惠券名称
	 * */
	private String couponName;
	/*
	 * 优惠方式
	 * */
	private String conditionValue;
	/*
	 * 优惠券状态
	 * */
	private String couponStatus;
	/*
	 * 优惠券类型
	 * */
	private String couponType;
	/*
	 * 分配对象
	 * */
	private String serviceId;
	/*
	 * 订单id
	 * */
	private String orderId;
	/*
	 * 更新时间
	 * */
	private Timestamp updateTime;
	/*
	 * 生效时间
	 * */
	private Timestamp activeTime;
	/*
	 * 失效时间
	 * */
	private Timestamp inactiveTime;
	
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getConditionValue() {
		return conditionValue;
	}
	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}
	public String getCouponStatus() {
		return couponStatus;
	}
	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	public Timestamp getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(Timestamp activeTime) {
		this.activeTime = activeTime;
	}
	public Timestamp getInactiveTime() {
		return inactiveTime;
	}
	public void setInactiveTime(Timestamp inactiveTime) {
		this.inactiveTime = inactiveTime;
	}
	public String getCouponType() {
		return couponType;
	}
	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
