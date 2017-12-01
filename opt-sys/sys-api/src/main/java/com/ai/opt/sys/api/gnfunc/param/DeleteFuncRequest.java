package com.ai.opt.sys.api.gnfunc.param;

import java.io.Serializable;

public class DeleteFuncRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long funcId;

	public Long getFuncId() {
		return funcId;
	}

	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}
}
