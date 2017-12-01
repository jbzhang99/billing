package com.ai.baas.dst.api.coupon.params;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseInfo;
/**
 * 添加优惠产品入参
 *
 * Date: 2017年2月16日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * @author gaogang
 */
public class CouponInfoReq extends BaseInfo {

	
	private static final long serialVersionUID = 1L;
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
	 * 优惠券使用条件详情
	 */
	private ConditionDetail conditonDetail;
	/**
	 * 优惠券使用条件
	 */
	private String conditionValue;
	/**
	 * 优惠券使用条件类型
	 */
	private String couponConType;
	
	/**
	 * 产品id
	 */
	private String productId;
	/**
	 * 最高减免金额
	 */
	private String topMoney;
	/**
	 * 产品名称
	 */
	private String productName;
	
	
	
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
	public String getTopMoney() {
		return topMoney;
	}
	public void setTopMoney(String topMoney) {
		this.topMoney = topMoney;
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
	
	
}
