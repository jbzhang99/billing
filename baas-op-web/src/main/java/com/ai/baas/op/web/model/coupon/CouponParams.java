package com.ai.baas.op.web.model.coupon;

import java.io.Serializable;

public class CouponParams implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String couponId;
	private String couponName;
	private String couponType;
	private String status;
	private String startTime;
	private String endTime;
	private Integer pageNO;
	private Integer pageSize;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getPageNO() {
		return pageNO;
	}
	public void setPageNO(Integer pageNO) {
		this.pageNO = pageNO;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
