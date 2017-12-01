package com.ai.baas.prd.api.product.params;

import com.ai.opt.base.vo.BaseInfo;

public class mainProReq extends BaseInfo {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主产品id
	 */
	private String productId;
	/**
	 * 主产品名称
	 */
	private String productName;
	
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
