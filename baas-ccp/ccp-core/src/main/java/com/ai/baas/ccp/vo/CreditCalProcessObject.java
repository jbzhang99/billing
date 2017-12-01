package com.ai.baas.ccp.vo;

import java.math.BigDecimal;

import com.google.gson.JsonObject;

public final class CreditCalProcessObject extends BaseCalProcessObject {
    private BigDecimal creditline;

    public CreditCalProcessObject(ConfigContainerObject cfg, InformationProcessorObject info,
            JsonObject data) {
        super(cfg, info, data);
    }

    public BigDecimal getCreditline() {
        return (creditline == null) ? new BigDecimal("0.00") : creditline;
    }

    public void setCreditline(BigDecimal creditline) {
        this.creditline = creditline;
    }

}
