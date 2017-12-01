package com.ai.citic.billing.web.model.element;

import com.ai.baas.prd.api.element.params.Product;

public class ProductVo {
	/**
	 * 序号
	 */
	private String index;
	
	private Product product;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
