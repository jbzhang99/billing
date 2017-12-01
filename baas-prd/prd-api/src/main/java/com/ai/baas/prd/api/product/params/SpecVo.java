package com.ai.baas.prd.api.product.params;

import java.io.Serializable;

public class SpecVo implements Serializable{
    private String specTypeId;
    private String specTypeName;
    private String specDetailId;
    private String tradeCode;

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public String getSpecTypeId() {
        return specTypeId;
    }

    public void setSpecTypeId(String specTypeId) {
        this.specTypeId = specTypeId;
    }

    public String getSpecTypeName() {
        return specTypeName;
    }

    public void setSpecTypeName(String specTypeName) {
        this.specTypeName = specTypeName;
    }

    public String getSpecDetailId() {
        return specDetailId;
    }

    public void setSpecDetailId(String specDetailId) {
        this.specDetailId = specDetailId;
    }
}
