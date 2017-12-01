package com.ai.baas.prd.api.product.params;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
/**
 * 查询主产品返回参数
 *
 * Date: 2016年11月15日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
public class PmCategoryInfoRes extends BaseResponse {

	private static final long serialVersionUID = 1L;
	private PageInfo<MainProduct> pageInfo;

	public PageInfo<MainProduct> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<MainProduct> pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	
}
