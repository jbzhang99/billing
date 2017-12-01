package com.ai.baas.dst.api.coupon.params;

import java.io.Serializable;

public class CouponCodeList implements Serializable {

	private static final long serialVersionUID = 1L;
	private String couponCode;
	private String singleStatus;
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getSingleStatus() {
		return singleStatus;
	}
	public void setSingleStatus(String singleStatus) {
		this.singleStatus = singleStatus;
	}
	
	
}
