package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class CouponListResponse extends BaseResponse {
	
	private static final long serialVersionUID = 1L;
	

	
	private PageInfo<CouponInfo> pageInfo;

	public PageInfo<CouponInfo> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<CouponInfo> pageInfo) {
		this.pageInfo = pageInfo;
	}
	
}
