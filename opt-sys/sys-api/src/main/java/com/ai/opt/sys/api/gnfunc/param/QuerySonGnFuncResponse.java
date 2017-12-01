package com.ai.opt.sys.api.gnfunc.param;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

public class QuerySonGnFuncResponse extends BaseResponse{

	private static final long serialVersionUID = 1L;

	private List<GnFuncData> funcDataList;

	public List<GnFuncData> getFuncDataList() {
		return funcDataList;
	}

	public void setFuncDataList(List<GnFuncData> funcDataList) {
		this.funcDataList = funcDataList;
	}
}
