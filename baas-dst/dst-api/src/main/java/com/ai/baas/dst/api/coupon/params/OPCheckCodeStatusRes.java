package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseResponse;

public class OPCheckCodeStatusRes extends BaseResponse{
	private static final long serialVersionUID = 1L;
	
	private int result;
	
	private String couponId;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	
}
