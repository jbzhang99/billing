package com.ai.baas.bmc.api.failedbills.params;

import com.ai.opt.base.vo.BaseResponse;

public class FailedBillDetail extends BaseResponse{
    private FailedBillVo failedBillVo;

    public FailedBillVo getFailedBillVo() {
        return failedBillVo;
    }

    public void setFailedBillVo(FailedBillVo failedBillVo) {
        this.failedBillVo = failedBillVo;
    }
}
