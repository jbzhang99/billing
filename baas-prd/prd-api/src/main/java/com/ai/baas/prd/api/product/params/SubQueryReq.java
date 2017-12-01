package com.ai.baas.prd.api.product.params;

import com.ai.opt.base.vo.BaseInfo;

public class SubQueryReq extends BaseInfo{

	
	private static final long serialVersionUID = 1L;

	/**
	 * 子目录id
	 */
	private String productId;
	/**
	 * 子目录名称
	 */
	private String productName;
	
	private Integer pageNO;
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	
}
