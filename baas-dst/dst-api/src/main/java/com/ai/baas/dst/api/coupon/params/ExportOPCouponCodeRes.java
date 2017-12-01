package com.ai.baas.dst.api.coupon.params;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

public class ExportOPCouponCodeRes extends BaseResponse{
	private static final long serialVersionUID = 1L;
	
	private List<OPCouponCodeInfo> pageInfo;

	public List<OPCouponCodeInfo> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(List<OPCouponCodeInfo> pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	
}
