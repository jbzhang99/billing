package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseInfo;

public class ExportExistisCouponReq extends BaseInfo {

	private static final long serialVersionUID = 1L;

	private String channelId;
	
	private String couponId;
	
	private String couponName;
	
	private String getResource;
	
	private String status;

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getGetResource() {
		return getResource;
	}

	public void setGetResource(String getResource) {
		this.getResource = getResource;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
	
}
