package com.ai.baas.prd.api.product.params;

import com.ai.opt.base.vo.BaseInfo;

public class BaseRequest extends BaseInfo{

	
	private static final long serialVersionUID = 1L;
	/**
	 * 主产品Id
	 */
	private String mainProductCode;

	public String getMainProductCode() {
		return mainProductCode;
	}

	public void setMainProductCode(String mainProductCode) {
		this.mainProductCode = mainProductCode;
	}
	
}
