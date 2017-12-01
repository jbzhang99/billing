package com.ai.baas.smc.api.sysparamcache.param;

import com.ai.opt.base.vo.BaseInfo;

public class GetSysParamListRequest extends BaseInfo {
    private static final long serialVersionUID = 1L;

    private String typeCode;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    private String paramCode;
}
