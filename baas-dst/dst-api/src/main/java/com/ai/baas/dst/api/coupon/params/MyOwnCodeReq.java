package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseInfo;

public class MyOwnCodeReq extends BaseInfo{
	private static final long serialVersionUID = 1L;
	/*
	 * 客户id
	 * */
	private String serviceId;
	
	/*
	 * 页码
	 * */
	private Integer pageNo;
	/*
	 * 每页条数
	 * */
	private Integer pageSize;

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
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
	
	
}
