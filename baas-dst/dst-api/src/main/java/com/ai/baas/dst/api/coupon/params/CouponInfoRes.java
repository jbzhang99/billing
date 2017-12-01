package com.ai.baas.dst.api.coupon.params;

import java.io.Serializable;
import java.sql.Timestamp;

public class CouponInfoRes implements Serializable {

	/**
	 * 优惠券Id
	 */
	private String couponId;
	/**
	 * 优惠券的序号
	 */
	private String couponIndex;
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
	 * 优惠券面值
	 */
	private String couponValue;
	/**
	 * 建券时间
	 */
	private Timestamp createTime;
	/**
	 * 生效时间
	 */
	private Timestamp activeTime;
	/**
	 * 失效时间
	 */
	private Timestamp inactiveTime;
	/**
	 * 
	 */
	private ConditionDetail conditonDetail;
	/**
	 * 优惠券使用条件
	 */
	private String couponConditon;
	/**
	 * 优惠券使用类型
	 */
	private String conponConType;
	/**
	 * 优惠券使用条件描述
	 */
	private String conditionValue;
	/**
	 * 产品Id
	 */
	private String productId;

	/**
	 * 最高减免金额
	 */
	private String topMoney;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 优惠券标识
	 */
	private String couponRule;
	/**
	 * 渠道商Id
	 */
	private String creatorId;
	/**
	 * 创建者账号
	 */
	private String creatorAccount;
	/**
	 * 创建者名称
	 */
	private String creatorName;
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getCouponIndex() {
		return couponIndex;
	}
	public void setCouponIndex(String couponIndex) {
		this.couponIndex = couponIndex;
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
	public String getCouponValue() {
		return couponValue;
	}
	public void setCouponValue(String couponValue) {
		this.couponValue = couponValue;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
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
	public ConditionDetail getConditonDetail() {
		return conditonDetail;
	}
	public void setConditonDetail(ConditionDetail conditonDetail) {
		this.conditonDetail = conditonDetail;
	}
	public String getCouponConditon() {
		return couponConditon;
	}
	public void setCouponConditon(String couponConditon) {
		this.couponConditon = couponConditon;
	}
	public String getConponConType() {
		return conponConType;
	}
	public void setConponConType(String conponConType) {
		this.conponConType = conponConType;
	}
	public String getConditionValue() {
		return conditionValue;
	}
	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getTopMoney() {
		return topMoney;
	}
	public void setTopMoney(String topMoney) {
		this.topMoney = topMoney;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCouponRule() {
		return couponRule;
	}
	public void setCouponRule(String couponRule) {
		this.couponRule = couponRule;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreatorAccount() {
		return creatorAccount;
	}
	public void setCreatorAccount(String creatorAccount) {
		this.creatorAccount = creatorAccount;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	
	
	
}
