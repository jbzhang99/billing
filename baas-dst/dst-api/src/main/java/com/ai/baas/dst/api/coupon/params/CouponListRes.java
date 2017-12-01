package com.ai.baas.dst.api.coupon.params;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

public class CouponListRes extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	private List<QDcoupon> list;

	public List<QDcoupon> getList() {
		return list;
	}

	public void setList(List<QDcoupon> list) {
		this.list = list;
	}

}
