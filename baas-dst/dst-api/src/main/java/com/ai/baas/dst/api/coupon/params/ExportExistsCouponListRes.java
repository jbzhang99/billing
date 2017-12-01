package com.ai.baas.dst.api.coupon.params;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class ExportExistsCouponListRes extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	private List<QDcoupon> couponList;

	public List<QDcoupon> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<QDcoupon> couponList) {
		this.couponList = couponList;
	}

	
	
	

}
