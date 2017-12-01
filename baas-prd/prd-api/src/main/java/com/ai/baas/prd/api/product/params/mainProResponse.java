package com.ai.baas.prd.api.product.params;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class mainProResponse extends BaseResponse {

	private PageInfo<MainProductInfo> pageInfo;

	public PageInfo<MainProductInfo> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<MainProductInfo> pageInfo) {
		this.pageInfo = pageInfo;
	}

	
	
}
