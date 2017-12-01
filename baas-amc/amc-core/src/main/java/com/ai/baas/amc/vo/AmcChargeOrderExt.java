package com.ai.baas.amc.vo;

import com.ai.baas.amc.dao.mapper.bo.AmcCharge;

/**
 * 对账单明细的扩展
 * 包括了账单明细的科目优先级
 * Created by jackieliu on 16/4/1.
 */
public class AmcChargeOrderExt extends AmcCharge{
    /**
     * 明细所属科目优先级,默认为最小值0
     */
    public long feeSettlePri = 0;

    public long getFeeSettlePri() {
        return feeSettlePri;
    }

    public void setFeeSettlePri(long feeSettlePri) {
        this.feeSettlePri = feeSettlePri;
    }
}
