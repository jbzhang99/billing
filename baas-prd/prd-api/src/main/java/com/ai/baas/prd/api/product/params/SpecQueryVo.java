package com.ai.baas.prd.api.product.params;

import com.ai.opt.base.vo.BaseInfo;

public class SpecQueryVo extends BaseInfo{
    private String tradeCode;

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }
}
