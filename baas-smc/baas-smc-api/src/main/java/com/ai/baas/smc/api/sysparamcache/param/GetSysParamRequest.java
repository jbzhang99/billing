package com.ai.baas.smc.api.sysparamcache.param;

import com.ai.opt.base.vo.BaseInfo;

public class GetSysParamRequest extends BaseInfo {
    private static final long serialVersionUID = 1L;

    private String typeCode;

    private String paramCode;

    private String columnValue;

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

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }
}
