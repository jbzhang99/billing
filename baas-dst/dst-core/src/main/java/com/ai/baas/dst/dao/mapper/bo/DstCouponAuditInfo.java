package com.ai.baas.dst.dao.mapper.bo;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseInfo;

public class DstCouponAuditInfo extends BaseInfo {

	
	private static final long serialVersionUID = 1L;
	/**
	 * 优惠券id
	 */
	private String couponId;
	/**
	 * 优惠券名称
	 */
	private String couponName;
	/**
	 * 优惠券状态：全部，已生效，已失效
	 */
	private String status;
	/**
	 * 查询开始时间
	 */
	private Timestamp startTime;
	/**
	 * 查询结束时间
	 */
	private Timestamp endTime;
	
	/**
	 * 页码
	 */
	private Integer limitStart;
	/**
	 * 页大小
	 */
	private Integer limitEnd;
	

	

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

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
