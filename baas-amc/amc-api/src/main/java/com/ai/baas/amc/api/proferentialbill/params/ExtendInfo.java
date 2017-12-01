package com.ai.baas.amc.api.proferentialbill.params;

import java.io.Serializable;
/**
 * 扩展信息
 *
 * Date: 2016年3月30日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
public class ExtendInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 属性名称
     */
    private String attrName;

    /**
     * 属性类型
     */
    private String attrType;

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

	public String getAttrType() {
		return attrType;
	}

	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
    
    
}
