package com.ai.baas.smc.api.billdata.param;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.HBasePager;

public class QueryBillDetailResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /**
     * 账单详单信息查询结果
     */
    private HBasePager<QueryBillDetail> hBasePager;

    public HBasePager<QueryBillDetail> gethBasePager() {
        return hBasePager;
    }

    public void sethBasePager(HBasePager<QueryBillDetail> hBasePager) {
        this.hBasePager = hBasePager;
    }

}
