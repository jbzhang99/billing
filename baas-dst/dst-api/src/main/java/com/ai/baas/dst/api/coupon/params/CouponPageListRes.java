package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class CouponPageListRes extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	private PageInfo<QDcoupon> pageInfo;

	public PageInfo<QDcoupon> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<QDcoupon> pageInfo) {
		this.pageInfo = pageInfo;
	}


}
