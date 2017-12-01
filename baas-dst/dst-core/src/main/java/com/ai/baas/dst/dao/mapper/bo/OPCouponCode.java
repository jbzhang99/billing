package com.ai.baas.dst.dao.mapper.bo;

import java.sql.Timestamp;

public class OPCouponCode {
	/**
	 * 租户id
	 */
	private String tenantId;
	/**
	 * 优惠券id
	 */
	private String couponId;
	/**
	 * 渠道id
	 */
	private String channelId;
	/**
	 * 渠道账号
	 */
	private String channelAccount;
	/**
	 * 申请时间
	 */
	private Timestamp applyTime;
	/**
	 * 页码
	 */
	private Integer limitStart;
	/**
	 * 页大小
	 */
	private Integer limitEnd;
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannelAccount() {
		return channelAccount;
	}
	public void setChannnelAccount(String channelAccount) {
		this.channelAccount = channelAccount;
	}
	public Timestamp getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
	}
	public Integer getLimitStart() {
		return limitStart;
	}
	public void setLimitStart(Integer limitStart) {
		this.limitStart = limitStart;
	}
	public Integer getLimitEnd() {
		return limitEnd;
	}
	public void setLimitEnd(Integer limitEnd) {
		this.limitEnd = limitEnd;
	}
	
	
	
}
