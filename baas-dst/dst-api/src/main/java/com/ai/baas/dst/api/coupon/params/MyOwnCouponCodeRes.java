package com.ai.baas.dst.api.coupon.params;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

public class MyOwnCouponCodeRes extends BaseResponse{
	private static final long serialVersionUID = 1L;
	
	private List<MyOwnCouponCodeInfo> pageInfo;

	public List<MyOwnCouponCodeInfo> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(List<MyOwnCouponCodeInfo> pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	
}
