package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;


public class OPCouponListRes extends BaseResponse {
	private static final long serialVersionUID = 1L;
	
	private PageInfo<OPCouponInfo> pageInfo;

	public PageInfo<OPCouponInfo> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<OPCouponInfo> pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	
}
