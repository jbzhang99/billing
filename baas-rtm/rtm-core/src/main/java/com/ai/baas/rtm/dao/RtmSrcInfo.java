package com.ai.baas.rtm.dao;

public class RtmSrcInfo {
	/** 
	 * 租户id
	 */
	String tenantId;
	/**
	 * 业务类型
	 */
	String infoType;
	/**
	 * 解析类型编码
	 */
	String infoId;
	/**
	 *报文分隔符  char(3)
	 */
	String infoSplitFlag;
	/**
	 * 消息分割符  char(2)
	 */
	String recordSplitFlag;
	/**
	 * 字段分隔符 char(1)
	 */
	String fieldSplitFlag;
	
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getInfoType() {
		return infoType;
	}
	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	public String getInfoSplitFlag() {
		return infoSplitFlag;
	}
	public void setInfoSplitFlag(String infoSplitFlag) {
		this.infoSplitFlag = infoSplitFlag;
	}
	public String getRecordSplitFlag() {
		return recordSplitFlag;
	}
	public void setRecordSplitFlag(String recordSplitFlag) {
		this.recordSplitFlag = recordSplitFlag;
	}
	public String getFieldSplitFlag() {
		return fieldSplitFlag;
	}
	public void setFieldSplitFlag(String fieldSplitFlag) {
		this.fieldSplitFlag = fieldSplitFlag;
	}
	
}
