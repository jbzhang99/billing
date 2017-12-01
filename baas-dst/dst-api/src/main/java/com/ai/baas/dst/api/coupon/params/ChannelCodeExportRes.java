package com.ai.baas.dst.api.coupon.params;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;
/**
 * 优惠码详情导出
 * @author scorpion
 *
 */
public class ChannelCodeExportRes extends BaseResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ChannelCodeVO> codes;
	public List<ChannelCodeVO> getCodes() {
		return codes;
	}
	public void setCodes(List<ChannelCodeVO> codes) {
		this.codes = codes;
	}

	
	
	
}
