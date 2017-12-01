package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseInfo;

public class OPCouponUsedReq extends BaseInfo{
	private static final long serialVersionUID = 1L;
	
	/*
	 * 客户id
	 * */
	private String serviceId;
	/*
	 * 优惠券id
	 * */
	private String couponId;
	/*
	 *订单id
	 * */
	private String orderId;
	/*
	 * 优惠码
	 * */
	private String couponCode;
	/*
	 * 优惠码状态
	 * */
	private String couponStatus;
	
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getCouponStatus() {
		return couponStatus;
	}
	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}
	
	
}
