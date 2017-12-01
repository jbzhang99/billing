package com.ai.baas.dst.api.coupon.params;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseInfo;


public class OPCouponCodeReq extends BaseInfo{
	private static final long serialVersionUID = 1L;
	/*
	 * 优惠券id
	 * */
	private String couponId;
	/*
	 * 优惠码
	 * */
	private String couponCode;
	/*
	 * 优惠券名称
	 * */
	private String conditionValue;
	/*
	 * 优惠券状态
	 * */
	private String couponStatus;
	/*
	 * 分配对象id
	 * */
	private String serviceId;
	/*
	 * 渠道id
	 * */
	private String channelId;
	/*
	 * 订单id
	 * */
	private String orderId;
	/*
	 * 生效时间
	 * */
	private Timestamp activeTime;
	/*
	 * 失效时间
	 * */
	private Timestamp inactiveTime;
	/*
	 * 更新时间
	 * */
	private Timestamp updateTime;
	/*
	 * 系统时间
	 * */
	private Timestamp sysTime;
	/*
	 * 起始数
	 * */
	private Integer limitStart;
	/*
	 * 页码
	 * */
	private Integer pageNo;
	/*
	 * 每页条数
	 * */
	private Integer pageSize;
	
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getConditionValue() {
		return conditionValue;
	}
	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}
	public String getCouponStatus() {
		return couponStatus;
	}
	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}
	
	public Timestamp getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(Timestamp activeTime) {
		this.activeTime = activeTime;
	}
	public Timestamp getInactiveTime() {
		return inactiveTime;
	}
	public void setInactiveTime(Timestamp inactiveTime) {
		this.inactiveTime = inactiveTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
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
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getLimitStart() {
		return limitStart;
	}
	public void setLimitStart(Integer limitStart) {
		this.limitStart = limitStart;
	}
	public Timestamp getSysTime() {
		return sysTime;
	}
	public void setSysTime(Timestamp sysTime) {
		this.sysTime = sysTime;
	}	
	
}
