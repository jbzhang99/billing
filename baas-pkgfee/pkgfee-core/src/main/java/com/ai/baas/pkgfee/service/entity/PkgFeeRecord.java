package com.ai.baas.pkgfee.service.entity;

import com.ai.baas.pkgfee.api.feecal.params.ProductItemVO;

public class PkgFeeRecord {
	private String orderId;
	private String tenantId;
	private String deductMode;
	private ProductItemVO productItem;
	private DstResult dstResult;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getDeductMode() {
		return deductMode;
	}

	public void setDeductMode(String deductMode) {
		this.deductMode = deductMode;
	}

	public ProductItemVO getProductItem() {
		return productItem;
	}

	public void setProductItem(ProductItemVO productItem) {
		this.productItem = productItem;
	}

	public DstResult getDstResult() {
		return dstResult;
	}

	public void setDstResult(DstResult dstResult) {
		this.dstResult = dstResult;
	}
	
}
