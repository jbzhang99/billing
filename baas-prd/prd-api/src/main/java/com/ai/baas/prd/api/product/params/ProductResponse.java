package com.ai.baas.prd.api.product.params;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 查看某个产品的返回参数
 *
 * Date: 2016年11月9日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
public class ProductResponse extends BaseResponse{

	
	private static final long serialVersionUID = 1L;
	
	private MainProductRes product;

	public MainProductRes getProduct() {
		return product;
	}

	public void setProduct(MainProductRes product) {
		this.product = product;
	}
	
	
	

}
