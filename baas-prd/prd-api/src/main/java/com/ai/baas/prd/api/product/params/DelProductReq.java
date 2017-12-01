package com.ai.baas.prd.api.product.params;

import com.ai.opt.base.vo.BaseInfo;

public class DelProductReq extends BaseInfo {

	
	private static final long serialVersionUID = 1L;
	
	
	private String mainProCode;


	public String getMainProCode() {
		return mainProCode;
	}


	public void setMainProCode(String mainProCode) {
		this.mainProCode = mainProCode;
	}
	
	

}
