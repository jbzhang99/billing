package com.ai.opt.sys.api.gnfunc.param;

import com.ai.opt.base.vo.BaseResponse;

public class InsertGnFuncResponse extends BaseResponse {
    private static final long serialVersionUID = 1L;

    /**
     * 功能ID
     */
    private long funcId;

    public long getFuncId() {
        return funcId;
    }

    public void setFuncId(long funcId) {
        this.funcId = funcId;
    }

}
