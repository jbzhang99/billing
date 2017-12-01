package com.ai.baas.rtm.dao;

public class RtmSrcRecord {
	/**
	 * 解析类型编码
	 */
	String infoId;
	/**
	 * 业务数据编码
	 */
	String fieldId;
	/**
	 * 字段长度
	 */
	String fieldLength;
	/**
	 * 字段类型
	 */
	String fieldType;
	/**
	 * 字段名称
	 */
	String fieldName;
	/**
	 * 是否为sn
	 */
	String isSn;
	
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getFieldLength() {
		return fieldLength;
	}
	public void setFieldLength(String fieldLength) {
		this.fieldLength = fieldLength;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getIsSn() {
		return isSn;
	}
	public void setIsSn(String isSn) {
		this.isSn = isSn;
	}

}
