package com.ai.baas.smc.api.billdetail.param;

import com.ai.opt.base.vo.BaseInfo;

public class CheckResultQueryRequest extends BaseInfo {
    private static final long serialVersionUID = 1L;

    /**
     * 账期月份,yyyymm
     */
    private String billMonth;

    /**
     * 账单ID
     */
    private Long billId;

    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

}
