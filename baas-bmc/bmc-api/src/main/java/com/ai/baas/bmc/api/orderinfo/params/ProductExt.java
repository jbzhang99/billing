package com.ai.baas.bmc.api.orderinfo.params;

import java.io.Serializable;

/**
 * 产品扩展信息 Date: 2016年3月22日 Copyright (c) 2016 asiainfo.com
 * 
 * @author caoyf
 */
public class ProductExt implements Serializable {
    private static final long serialVersionUID = -7022651698883021106L;

    /**
     * 名称 必填 VARCHAR(32)
     */
    private String extName;

    /**
     * 值 必填 VARCHAR(64)
     */
    private String extValue;

    /**
     * 更新标识 取值范围：D：删除，U：更新，N：新增 必填 VARCHAR(1)
     */
    private String updateFlag;

    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName;
    }

    public String getExtValue() {
        return extValue;
    }

    public void setExtValue(String extValue) {
        this.extValue = extValue;
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }
}
