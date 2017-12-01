package com.ai.baas.op.web.model.coupon;

import com.ai.baas.dst.api.coupon.params.CouponInfo;

public class CouponInfoExt extends CouponInfo {

	private static final long serialVersionUID = 1L;
	private String couponTypeName;
	
	private String couponStaName;
	
	
	

	public String getCouponStaName() {
		return couponStaName;
	}

	public void setCouponStaName(String couponStaName) {
		this.couponStaName = couponStaName;
	}

	public String getCouponTypeName() {
		return couponTypeName;
	}

	public void setCouponTypeName(String couponTypeName) {
		this.couponTypeName = couponTypeName;
	}
	
}
