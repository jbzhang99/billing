package com.ai.baas.pkgfee.service.entity;

import com.ai.baas.pkgfee.api.feecal.params.ProductItemVO;

public class DstActivityGroup {
	private String calcType;
	private ProductItemVO productItem;

	public String getCalcType() {
		return calcType;
	}

	public void setCalcType(String calcType) {
		this.calcType = calcType;
	}

	public ProductItemVO getProductItem() {
		return productItem;
	}

	public void setProductItem(ProductItemVO productItem) {
		this.productItem = productItem;
	}

}
