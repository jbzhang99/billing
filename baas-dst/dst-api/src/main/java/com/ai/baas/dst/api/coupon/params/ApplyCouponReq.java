package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseInfo;

public class ApplyCouponReq extends BaseInfo {

	private static final long serialVersionUID = 1L;
	/**
	 * 渠道商id
	 */
	private String channelId;
	/**
	 * 渠道商名称
	 */
	private String channelName;
	/**
	 * 渠道商账号
	 */
	private String channelAccount;
	/**
	 * 申请数量
	 */
	private Integer applyCount;
	/**
	 * 优惠券id
	 */
	private String couponId;
	/**
	 * 备注
	 */
	private String comments;
	
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelAccount() {
		return channelAccount;
	}
	public void setChannelAccount(String channelAccount) {
		this.channelAccount = channelAccount;
	}
	public Integer getApplyCount() {
		return applyCount;
	}
	public void setApplyCount(Integer applyCount) {
		this.applyCount = applyCount;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
	
}
