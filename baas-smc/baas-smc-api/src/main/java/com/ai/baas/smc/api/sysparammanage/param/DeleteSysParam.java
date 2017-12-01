package com.ai.baas.smc.api.sysparammanage.param;

import com.ai.opt.base.vo.BaseInfo;

public class DeleteSysParam extends BaseInfo {

    private static final long serialVersionUID = 1L;

    /**
     * 流水主键 必填
     */
    private String guidkey;

    public String getGuidkey() {
        return guidkey;
    }

    public void setGuidkey(String guidkey) {
        this.guidkey = guidkey == null ? null : guidkey.trim();
    }
}
