package com.ai.baas.mt.web.model;


import com.ai.baas.bmc.api.failedbills.params.FailedOrderVo;

public class FailedOrderShowVo extends FailedOrderVo{
    private Integer index;
    private String failCodeDesc;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getFailCodeDesc() {
        return failCodeDesc;
    }

    public void setFailCodeDesc(String failCodeDesc) {
        this.failCodeDesc = failCodeDesc;
    }
}
