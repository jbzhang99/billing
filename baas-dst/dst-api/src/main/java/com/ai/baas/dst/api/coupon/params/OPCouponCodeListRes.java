package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class OPCouponCodeListRes extends BaseResponse{
	private static final long serialVersionUID = 1L;
	
	private PageInfo<OPCouponCodeInfo> pageInfo;

	public PageInfo<OPCouponCodeInfo> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<OPCouponCodeInfo> pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	
}
