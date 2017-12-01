package com.ai.baas.dst.api.coupon.params;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseResponse;

public class OPSingleCouponRes extends BaseResponse {

	
	private static final long serialVersionUID = 1L;
	/**
	 * 优惠券id
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
	 *指定的产品的名称
	 */
	private String productName;
	/**
	 * 指定的产品id
	 */
	private String productId;
	/**
	 * 优惠券面值，比如8折，8元
	 */
	private String  couponValue;
	/**
	 * 优惠券数量
	 */
	private String couponAmount;
	/**
	 * 创建者角色
	 */
	private String creatorRole;
	
	/**
	 * 可申请数量
	 */
	private String canApplyCount;
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	/**
	 * 剩余数量
	 */
	private String remainCount;
	/**
	 * 已经申请的数量
	 */
	private String appliedCount;
	
	/**
	 * 优惠券标识（公开or仅运营可见）
	 */
	private String couponRule;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 生效时间
	 */
	private Timestamp activeTime;
	/**
	 * 失效时间
	 */
	private  Timestamp inactiveTime;
	/**
	 * 优惠券类型
	 */
	private String couponConType;
	
	/**
	 * 优惠券明细信息
	 */
	private ConditionDetail conditonDetail;
	
	/**
	 * 持续时长
	 */
	private String lastTime;
	/**
	 * 优惠信息
	 */
	private String conditionValue;
	
	private String topMoney;
	
	
	
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
	public String getCouponConType() {
		return couponConType;
	}
	public void setCouponConType(String couponConType) {
		this.couponConType = couponConType;
	}
	public ConditionDetail getConditonDetail() {
		return conditonDetail;
	}
	public void setConditonDetail(ConditionDetail conditonDetail) {
		this.conditonDetail = conditonDetail;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
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
	public String getCouponValue() {
		return couponValue;
	}
	public void setCouponValue(String couponValue) {
		this.couponValue = couponValue;
	}
	public String getCouponAmount() {
		return couponAmount;
	}
	public void setCouponAmount(String couponAmount) {
		this.couponAmount = couponAmount;
	}
	public String getCreatorRole() {
		return creatorRole;
	}
	public void setCreatorRole(String creatorRole) {
		this.creatorRole = creatorRole;
	}
	public String getCanApplyCount() {
		return canApplyCount;
	}
	public void setCanApplyCount(String canApplyCount) {
		this.canApplyCount = canApplyCount;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getRemainCount() {
		return remainCount;
	}
	public void setRemainCount(String remainCount) {
		this.remainCount = remainCount;
	}
	public String getAppliedCount() {
		return appliedCount;
	}
	public void setAppliedCount(String appliedCount) {
		this.appliedCount = appliedCount;
	}
	public String getCouponRule() {
		return couponRule;
	}
	public void setCouponRule(String couponRule) {
		this.couponRule = couponRule;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
