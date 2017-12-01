package com.ai.baas.dst.api.coupon.params;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseInfo;

public class CouponReq extends BaseInfo {

	
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
	 * 优惠券类型
	 */
	private String couponType;
	/**
	 * 优惠类型（满减/立减）
	 */
	private String couponConType;
	/**
	 * 优惠券装填：全部，已生效，已失效
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
	private Integer pageNO;
	/**
	 * 每页条数
	 */
	private Integer pageSize;
	

	public Integer getPageNO() {
		return pageNO;
	}

	public void setPageNO(Integer pageNO) {
		this.pageNO = pageNO;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
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

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getCouponConType() {
		return couponConType;
	}

	public void setCouponConType(String couponConType) {
		this.couponConType = couponConType;
	}
	
	
	
}
