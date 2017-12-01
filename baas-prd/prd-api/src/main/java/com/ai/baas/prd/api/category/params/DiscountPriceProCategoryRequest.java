package com.ai.baas.prd.api.category.params;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;
/**
 * 活动的类目ID查询产品入参
 *
 * Date: 2017年3月13日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * @author wangjing19
 */
public class DiscountPriceProCategoryRequest extends BaseInfo{

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 活动的产品ID
	 */
	private List<String> priceProductIDs;

	public List<String> getPriceProductIDs() {
		return priceProductIDs;
	}

	public void setPriceProductIDs(List<String> priceProductIDs) {
		this.priceProductIDs = priceProductIDs;
	}
	

}
