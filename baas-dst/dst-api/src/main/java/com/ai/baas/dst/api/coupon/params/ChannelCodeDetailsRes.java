package com.ai.baas.dst.api.coupon.params;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseInfo;

public class ChannelCodeDetailsRes extends BaseInfo{
	private static final long serialVersionUID = 1L;
	
	/*
	 * 优惠码
	 * */
	private String couponCode;
	/*
	 * 优惠码状态
	 * */
	private String couponStatus;
	/*
	 * 分配对象
	 * */
	private String serviceId;
	/*
	 * 更新时间
	 * */
	private Timestamp updateTime;
	/*
	 * 优惠券id
	 * */
	private String couponId;
	/*
	 * 优惠券名称
	 * */
	private String couponName;
	/*
	 * 优惠类型
	 * */
	private String couponType;
	/*
	 * 使用产品
	 * */
	private String productName;
	/*
	 * 绑定的产品id
	 * */
	private String productId;
	/*
	 * 优惠方式
	 * */
	private String conditionValue;
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
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
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
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
	public String getConditionValue() {
		return conditionValue;
	}
	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}
	
	
}
