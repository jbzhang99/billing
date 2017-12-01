package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseInfo;

public class ExPortReq extends BaseInfo {

	private static final long serialVersionUID = 1L;
	/**
	 * 优惠券id
	 */
	private String couponId;

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	
}
