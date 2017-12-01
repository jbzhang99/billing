package com.ai.baas.dst.api.coupon.params;

import java.io.Serializable;

public class CouponAtom implements Serializable {

	private static final long serialVersionUID = 1L;
	private String couponId;
	private String couponCode;
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
}
