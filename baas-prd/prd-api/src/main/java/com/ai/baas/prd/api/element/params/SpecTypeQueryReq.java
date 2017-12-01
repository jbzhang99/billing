package com.ai.baas.prd.api.element.params;

import com.ai.opt.base.vo.BaseInfo;

public class SpecTypeQueryReq extends BaseInfo {
	
	private static final long serialVersionUID = 1L;
	
	private String mainProductId;

	public String getMainProductId() {
		return mainProductId;
	}

	public void setMainProductId(String mainProductId) {
		this.mainProductId = mainProductId;
	}

}
