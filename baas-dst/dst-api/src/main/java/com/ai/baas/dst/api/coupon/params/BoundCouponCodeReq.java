package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseInfo;

public class BoundCouponCodeReq extends BaseInfo{
	public static final long serialVersionUID = 1L;
	
	private String serviceId;
	
	private String couponCode;

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	
}
