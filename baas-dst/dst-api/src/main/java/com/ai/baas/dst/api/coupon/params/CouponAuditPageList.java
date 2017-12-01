package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class CouponAuditPageList extends BaseResponse {
	
	private static final long serialVersionUID = 1L;
	
	private PageInfo<CouponAuditInfo> pageInfo;

	public PageInfo<CouponAuditInfo> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<CouponAuditInfo> pageInfo) {
		this.pageInfo = pageInfo;
	}

	

	
}
