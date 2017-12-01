package com.ai.baas.dst.api.coupon.params;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class ChannelCodeListRes extends BaseResponse {

	private static final long serialVersionUID = 1L;
	private PageInfo<ChannelCodeVO> pageInfo;
	public PageInfo<ChannelCodeVO> getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo<ChannelCodeVO> pageInfo) {
		this.pageInfo = pageInfo;
	}

}
