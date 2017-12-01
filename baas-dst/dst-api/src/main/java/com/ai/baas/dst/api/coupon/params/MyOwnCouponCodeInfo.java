package com.ai.baas.dst.api.coupon.params;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseInfo;

public class MyOwnCouponCodeInfo extends BaseInfo{
	private static final long serialVersionUID = 1L;
	/*
	 * 优惠码id
	 * */
	private String codeId;
	/*
	 * 租户id
	 * */
	private String tenantId;
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
	 * 订单id
	 * */
	private String orderId;
	/*
	 * 渠道商id
	 * */
	private String channelId;
	/*
	 * 渠道商名称 
	 * */
	private String channelName;
	/*
	 * 渠道商账号
	 * */
	private String channelAccount;
	/*
	 * 分配对象
	 * */
	private String serviceId;
	/*
	 * 生效时间
	 * */
	private Timestamp activeTime;
	/*
	 * 失效时间
	 * */
	private Timestamp inactiveTime;
	
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
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
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelAccount() {
		return channelAccount;
	}
	public void setChannelAccount(String channelAccount) {
		this.channelAccount = channelAccount;
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
	
}
