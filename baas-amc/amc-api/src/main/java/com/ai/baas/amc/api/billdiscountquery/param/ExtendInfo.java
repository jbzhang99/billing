package com.ai.baas.amc.api.billdiscountquery.param;

import java.io.Serializable;

/**
 * 扩展信息
 *
 * Date: 2016年4月7日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class ExtendInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 属性名称
     */
    private String attrName;

    /**
     * 属性值
     */
    private String attrValue;

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

}
