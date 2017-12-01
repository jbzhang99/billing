package com.ai.baas.dst.api.coupon.params;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseInfo;

public class CouponCodeReq extends BaseInfo {

	private static final long serialVersionUID = 1L;

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
	private String channnelAccount;
	/**
	 * 申请时间
	 */
	private Timestamp applyTime;
	/**
	 * 页码
	 */
	private Integer pageNO;
	/**
	 * 页大小
	 */
	private Integer pageSize;

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

	

	public String getChannnelAccount() {
		return channnelAccount;
	}

	public void setChannnelAccount(String channnelAccount) {
		this.channnelAccount = channnelAccount;
	}

	public Timestamp getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
	}

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
	
	
}
