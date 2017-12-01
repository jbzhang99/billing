package com.ai.baas.prd.api.product.params;

import java.io.Serializable;
import java.math.BigDecimal;

public class PriceVO implements Serializable{
    private String detailId;
    private BigDecimal price;
    private String specName;

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }
}
