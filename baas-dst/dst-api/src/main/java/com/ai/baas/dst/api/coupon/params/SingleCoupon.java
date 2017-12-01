package com.ai.baas.dst.api.coupon.params;

import java.io.Serializable;
import java.sql.Timestamp;

public class SingleCoupon implements Serializable {
	
	private static final long serialVersionUID = 1L;
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
	
	private String couponConType;
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
	
	private String topMoney;
	
	private String productId;
	/**
	 * 
	 */
	private ConditionDetail conditonDetail;
	/**
	 * 优惠券使用条件
	 */
	private String couponConditon;
	/**
	 * 优惠券使用条件
	 */
	private String conditionValue;
	/**
	 * 持续时长
	 */
	private String lastTime;
	
	
	private String productName;
	
	
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
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
	public String getCouponConType() {
		return couponConType;
	}
	public void setCouponConType(String couponConType) {
		this.couponConType = couponConType;
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
	
}
