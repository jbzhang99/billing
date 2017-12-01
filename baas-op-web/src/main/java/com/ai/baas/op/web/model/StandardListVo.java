package com.ai.baas.op.web.model;

import java.text.DecimalFormat;
import java.text.Format;

import com.ai.baas.bmc.api.priceinfo.params.StandardList;

public class StandardListVo{

	/**
	 * 序号
	 */
	private String index;
	
	private StandardList standardList;
	
	private String serviceTypeName;
	private String subServiceTypeName;

	public String getIndex() {
		return index;
	}

	/**
	 * 转为001格式
	 * @param index
	 */
	public void setIndex(int index) {
		Format f1 = new DecimalFormat("000");
		this.index = f1.format(index);
	}

	public StandardList getStandardList() {
		return standardList;
	}

	public void setStandardList(StandardList standardList) {
		this.standardList = standardList;
	}

	public String getServiceTypeName() {
		return serviceTypeName;
	}

	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}

	public String getSubServiceTypeName() {
		return subServiceTypeName;
	}

	public void setSubServiceTypeName(String subServiceTypeName) {
		this.subServiceTypeName = subServiceTypeName;
	}
}
