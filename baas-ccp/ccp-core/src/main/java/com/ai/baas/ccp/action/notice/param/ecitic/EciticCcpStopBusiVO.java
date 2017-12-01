package com.ai.baas.ccp.action.notice.param.ecitic;

import com.ai.baas.ccp.action.notice.param.base.AbstractBaseNoticeBusiVO;

public class EciticCcpStopBusiVO extends AbstractBaseNoticeBusiVO {
    private String subsId;

    public String getSubsId() {
        return subsId;
    }

    public void setSubsId(String subsId) {
        this.subsId = subsId;
    }
}
