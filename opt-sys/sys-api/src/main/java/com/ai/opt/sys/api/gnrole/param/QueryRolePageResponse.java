package com.ai.opt.sys.api.gnrole.param;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class QueryRolePageResponse extends BaseResponse{

	private static final long serialVersionUID = 1L;

	private PageInfo<GnRoleData> pageInfo;

	public PageInfo<GnRoleData> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<GnRoleData> pageInfo) {
		this.pageInfo = pageInfo;
	}
}
