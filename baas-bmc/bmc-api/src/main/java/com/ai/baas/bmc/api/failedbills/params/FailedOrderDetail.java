package com.ai.baas.bmc.api.failedbills.params;

import com.ai.opt.base.vo.BaseResponse;

public class FailedOrderDetail extends BaseResponse{
    private FailedOrderVo failedOrderVo;

    public FailedOrderVo getFailedOrderVo() {
        return failedOrderVo;
    }

    public void setFailedOrderVo(FailedOrderVo failedOrderVo) {
        this.failedOrderVo = failedOrderVo;
    }
}
