package com.ai.baas.bmc.api.orderinfo.params;

import java.io.Serializable;

public class ExtInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 扩展信息的类型
     */
    private String extType;

    /**
     * 子信息列表
     */
    private String extValue;

    public String getExtType() {
        return extType;
    }

    public void setExtType(String extType) {
        this.extType = extType;
    }

    public String getExtValue() {
        return extValue;
    }

    public void setExtValue(String extValue) {
        this.extValue = extValue;
    }

}
