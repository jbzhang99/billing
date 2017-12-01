package com.ai.baas.dst.api.coupon.params;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

public class ChannelCodeDetailList extends BaseResponse{
	private static final long serialVersionUID = 1L;
	
	private List<ChannelCodeDetailsRes> pageInfo;

	public List<ChannelCodeDetailsRes> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(List<ChannelCodeDetailsRes> pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	
}
