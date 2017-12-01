package com.ai.baas.dst.api.coupon.params;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class CouponAuditListResponse extends BaseResponse {
	
	private static final long serialVersionUID = 1L;
	
	private List<CouponAuditInfo> list;

	public List<CouponAuditInfo> getList() {
		return list;
	}

	public void setList(List<CouponAuditInfo> list) {
		this.list = list;
	}

}
