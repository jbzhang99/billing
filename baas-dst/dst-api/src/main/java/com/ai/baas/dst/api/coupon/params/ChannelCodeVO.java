package com.ai.baas.dst.api.coupon.params;

import java.io.Serializable;
import java.sql.Timestamp;

public class ChannelCodeVO implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String couponCode;
	private String serviceId;
	private String couponStatus;
	private Timestamp updateTime;
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	public String getCouponStatus() {
		return couponStatus;
	}
	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
