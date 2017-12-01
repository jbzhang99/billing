package com.ai.baas.prd.api.product.params;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class SubProResponse extends BaseResponse {
	private static final long serialVersionUID = 4340475520543052805L;
	private PageInfo<CategoryInfo> pageInfo;

	public PageInfo<CategoryInfo> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<CategoryInfo> pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	
}
