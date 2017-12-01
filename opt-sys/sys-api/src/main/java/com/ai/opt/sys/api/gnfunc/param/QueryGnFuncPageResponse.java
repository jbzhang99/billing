package com.ai.opt.sys.api.gnfunc.param;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class QueryGnFuncPageResponse extends BaseResponse {
    private static final long serialVersionUID = 1L;
    
    private PageInfo<GnFuncData> pageInfo;

	public PageInfo<GnFuncData> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<GnFuncData> pageInfo) {
		this.pageInfo = pageInfo;
	}
}
