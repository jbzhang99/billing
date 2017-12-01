package com.ai.baas.dst.dao.mapper.bo;

import java.io.Serializable;
import java.sql.Timestamp;

public class CouponAuditInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 优惠券Id
	 */
	private String couponId;
	/**
	 * 优惠券名称
	 */
	private String couponName;
	/**
	 * 优惠券类型
	 */
	private String couponType;
	/**
	 * 优惠券数量
	 */
	private String couponAmount;
	/**
	 * 生效时间
	 */
	private Timestamp activeTime;
	/**
	 * 失效时间
	 */
	private Timestamp inactiveTime;
	/**
	 * 优惠券使用条件描述
	 */
	private String conditionValue;
	/**
	 * 优惠券状态
	 */
	private String status;
	/**
	 * 渠道ID
	 */
	private String channelId;
	/**
	 * 渠道账户
	 */
	private String channelAccount;
	
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 产品ID
	 */
	private String productId;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getCouponAmount() {
		return couponAmount;
	}
	public void setCouponAmount(String couponAmount) {
		this.couponAmount = couponAmount;
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
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannelAccount() {
		return channelAccount;
	}
	public void setChannelAccount(String channelAccount) {
		this.channelAccount = channelAccount;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
}
