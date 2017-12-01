package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseInfo;

public class ExportCodeReq extends BaseInfo {

	private static final long serialVersionUID = 1L;
	/**
	 * 优惠券Id
	 */
	private String couponId;
	/**
	 * 优惠券编码
	 */
	private String couponCode;
	/**
	 * 优惠码状态
	 */
	private String status;
	/**
	 * 服务号
	 */
	private String serviceId;

	/**
	 * 渠道商id
	 * 
	 */
	private String channelId;

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
	
}
