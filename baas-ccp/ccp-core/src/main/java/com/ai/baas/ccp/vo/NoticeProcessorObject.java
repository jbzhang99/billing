package com.ai.baas.ccp.vo;

import com.ai.baas.ccp.topoligy.core.pojo.RealTimeBalance;

public final class NoticeProcessorObject extends BaseProcessObject {

    private InformationProcessorObject info;

    private RealTimeBalance realBalance;

    private ScoutActBmsObject scoutActBmsExt;

    public RealTimeBalance getRealBalance() {
        return realBalance;
    }

    public void setRealBalance(RealTimeBalance realBalance) {
        this.realBalance = realBalance;
    }

    public InformationProcessorObject getInfo() {
        return info;
    }

    public void setInfo(InformationProcessorObject info) {
        this.info = info;
    }

    public ScoutActBmsObject getScoutActBmsExt() {
        return scoutActBmsExt;
    }

    public void setScoutActBmsExt(ScoutActBmsObject scoutActBmsExt) {
        this.scoutActBmsExt = scoutActBmsExt;
    }

}
