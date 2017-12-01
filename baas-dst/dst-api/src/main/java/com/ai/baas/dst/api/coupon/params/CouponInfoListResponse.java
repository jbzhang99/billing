package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class CouponInfoListResponse extends BaseResponse {
	
	private static final long serialVersionUID = 1L;
	

	
	private PageInfo<CouponResInfo> pageInfo;

	public PageInfo<CouponResInfo> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<CouponResInfo> pageInfo) {
		this.pageInfo = pageInfo;
	}
	
}
