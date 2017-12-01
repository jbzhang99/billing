package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseResponse;

public class DistrResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;
	private CouponAtom coupon;
	public CouponAtom getCoupon() {
		return coupon;
	}
	public void setCoupon(CouponAtom coupon) {
		this.coupon = coupon;
	}
	
	
	
}
