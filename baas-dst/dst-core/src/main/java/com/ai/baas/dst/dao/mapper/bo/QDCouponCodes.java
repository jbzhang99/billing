package com.ai.baas.dst.dao.mapper.bo;

import java.sql.Timestamp;

public class QDCouponCodes {
   private String couponId;
	
	private String couponName;
	
	private String couponType;
	
	private String productId;
	
	private String productName;
	
	private String conditionValue;
	
	private Integer couponCount;
	
	private String usedCount;
	
	private Timestamp activeTime;
	
	private Timestamp inactiveTime;
	
	private  String getResource;
	
	private String  status;
	
	private String comments;
	
	private String couponConType = "dd";

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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getConditionValue() {
		return conditionValue;
	}

	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}

	

	public Integer getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(Integer couponCount) {
		this.couponCount = couponCount;
	}

	public String getUsedCount() {
		return usedCount;
	}

	public void setUsedCount(String usedCount) {
		this.usedCount = usedCount;
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

	public String getGetResource() {
		return getResource;
	}

	public void setGetResource(String getResource) {
		this.getResource = getResource;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCouponConType() {
		return couponConType;
	}

	public void setCouponConType(String couponConType) {
		this.couponConType = couponConType;
	}
	
	
}
