package com.ai.baas.pkgfee.api.pkgunsubscribe.params;

import com.ai.opt.base.vo.BaseResponse;

public class PkgUnsubscribeResponse extends BaseResponse {
	
	private static final long serialVersionUID = 1L;
	
	private String unfrozenAmount;

	public String getUnfrozenAmount() {
		return unfrozenAmount;
	}

	public void setUnfrozenAmount(String unfrozenAmount) {
		this.unfrozenAmount = unfrozenAmount;
	}
}
