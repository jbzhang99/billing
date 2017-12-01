package com.ai.baas.dst.dao.mapper.bo;

public class QDCouponCode {

	private String couponId;
	
	private String couponName;
	
	private String getResource;
	
	private String channelId;
	
	private String status;

	/**
	 * 页码
	 */
	private Integer limitStart;
	/**
	 * 页大小
	 */
	private Integer limitEnd;
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
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
	
}
