package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class OPCodeListRes extends BaseResponse {
	
	private static final long serialVersionUID = 1L;
	private PageInfo<CouponCodeVO> pageInfo;

	public PageInfo<CouponCodeVO> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<CouponCodeVO> pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	
}
