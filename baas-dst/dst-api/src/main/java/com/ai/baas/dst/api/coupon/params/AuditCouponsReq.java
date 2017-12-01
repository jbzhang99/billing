package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseInfo;

public class AuditCouponsReq extends BaseInfo {

	
	private static final long serialVersionUID = 1L;
	/**
	 * 优惠券id
	 */
	private String couponId;
	/**
	 * 优惠券状态：全部，已生效，已失效
	 */
	private String status;
	/**
	 * 原因
	 */
	private String comments;
	
	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
