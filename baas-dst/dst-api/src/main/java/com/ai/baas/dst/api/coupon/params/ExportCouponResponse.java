package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseResponse;

public class ExportCouponResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;

	private CouponExport coupon;

	public CouponExport getCoupon() {
		return coupon;
	}

	public void setCoupon(CouponExport coupon) {
		this.coupon = coupon;
	}
	
	
	
}
