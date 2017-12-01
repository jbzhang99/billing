package com.ai.opt.sys.api.gnfunc.param;

import java.io.Serializable;

public class QueryGnFuncPageRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 功能名称
     */
    private String funcName;

    /**
     * 功能类型
     */
    private String funcType;
    /**
     * 分页信息
     */
    private Integer pageNo;
    
    private Integer pageSize;

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getFuncType() {
        return funcType;
    }

    public void setFuncType(String funcType) {
        this.funcType = funcType;
    }

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
