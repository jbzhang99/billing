package com.ai.baas.dst.api.billsdiscount.params;

import java.io.Serializable;

/**
 * 扩展信息
 * @author wangluyang
 *
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
