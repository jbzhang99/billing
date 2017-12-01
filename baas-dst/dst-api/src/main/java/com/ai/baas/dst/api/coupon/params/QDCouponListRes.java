package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class QDCouponListRes extends BaseResponse {

	PageInfo<CouponInfoRes> pageInfo;

	public PageInfo<CouponInfoRes> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<CouponInfoRes> pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	
}
