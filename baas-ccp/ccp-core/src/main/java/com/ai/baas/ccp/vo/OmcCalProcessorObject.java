package com.ai.baas.ccp.vo;

import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.pojo.OmcObj;
import com.ai.baas.ccp.topoligy.core.pojo.RealTimeBalance;
import com.google.gson.JsonObject;

public final class OmcCalProcessorObject extends BaseProcessObject {

    private RealTimeBalance realTimeBalance;

    private InformationProcessorObject info;

    public OmcCalProcessorObject(ConfigContainerObject cfg, OmcObj obj, JsonObject data)
            throws OmcException {
        super(cfg, obj, data);
    }

    public RealTimeBalance getRealTimeBalance() {
        return realTimeBalance;
    }

    public void setRealTimeBalance(RealTimeBalance realTimeBalance) {
        this.realTimeBalance = realTimeBalance;
    }

    public InformationProcessorObject getInfo() {
        return info;
    }

    public void setInfo(InformationProcessorObject info) {
        this.info = info;
    }

}
