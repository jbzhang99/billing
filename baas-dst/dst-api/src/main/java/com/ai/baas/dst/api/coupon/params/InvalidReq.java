package com.ai.baas.dst.api.coupon.params;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseInfo;

public class InvalidReq extends BaseInfo {

	
	private static final long serialVersionUID = 1L;

	private String couponId;
	 
	private Timestamp time;

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
}
