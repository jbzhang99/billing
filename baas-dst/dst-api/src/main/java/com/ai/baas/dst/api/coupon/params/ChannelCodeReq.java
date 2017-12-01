package com.ai.baas.dst.api.coupon.params;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseInfo;
/**
 * 渠道下优惠码的查询
 * @author scorpion
 *
 */
public class ChannelCodeReq extends BaseInfo {

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
	
	private Integer pageSize;
	
	private Integer pageNO;
	
	private String channelId;
	/*
	 * 系统时间
	 * */
	private Timestamp sysTime;

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
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

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNO() {
		return pageNO;
	}

	public void setPageNO(Integer pageNO) {
		this.pageNO = pageNO;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public Timestamp getSysTime() {
		return sysTime;
	}

	public void setSysTime(Timestamp sysTime) {
		this.sysTime = sysTime;
	}
	
	
}
