package com.ai.baas.dst.api.coupon.params;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

public class OPChannelInfoListRes extends BaseResponse {
	
	private static final long serialVersionUID = 1L;
	private List<CouponCodeInfoVO> channelList;
	
	public List<CouponCodeInfoVO> getChannelList() {
		return channelList;
	}
	public void setChannelList(List<CouponCodeInfoVO> channelList) {
		this.channelList = channelList;
	}

	
	
	
}
