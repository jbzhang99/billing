package com.ai.baas.ccp.vo;

import com.ai.baas.ccp.topoligy.core.pojo.RealTimeBalance;
import com.google.gson.JsonObject;

/**
 * 余额计算模式
 * 
 * @author jackieliu
 * 
 */
public final class BalanceCalProcessorObject extends BaseCalProcessObject {
    private RealTimeBalance realBalance = null;

    public BalanceCalProcessorObject(ConfigContainerObject cfg, InformationProcessorObject info,
            JsonObject data) {
        super(cfg, info, data);
    }

    public RealTimeBalance getBalance() {
        return realBalance;
    }

    public RealTimeBalance getRealBalance() {
        return realBalance;
    }

    public void setRealBalance(RealTimeBalance realBalance) {
        this.realBalance = realBalance;
    }

}
