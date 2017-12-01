package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseResponse;

public class CouponResponse extends BaseResponse {
	
	private static final long serialVersionUID = 1L;
	
	private SingleCoupon singleCoupon;

	public SingleCoupon getSingleCoupon() {
		return singleCoupon;
	}

	public void setSingleCoupon(SingleCoupon singleCoupon) {
		this.singleCoupon = singleCoupon;
	}
	
	
}
