package com.ai.baas.prd.api.category.params;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 活动的类目ID的返回参数
 *
 * Date: 2017年3月13日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * @author wangjing19
 */
public class DiscountPriceProCategoryResponse extends BaseResponse{

	private static final long serialVersionUID = 1L;
	
	private String categoryID;

	public String getDisProsCategoryID() {
		return categoryID;
	}

	public void setDisProsCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	
}
