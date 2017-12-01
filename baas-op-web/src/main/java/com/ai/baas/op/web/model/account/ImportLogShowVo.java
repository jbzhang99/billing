package com.ai.baas.op.web.model.account;

import com.ai.baas.smc.api.queryimportlog.param.ImportLogVo;

public class ImportLogShowVo extends ImportLogVo{

	private static final long serialVersionUID = 1L;
	
	private Integer index;
	
	private String stateName;
	
	private String exceptMsg;
	
	private String dataTypeName;
	
	private String objectIdName;
	
	private String billTimeMsg;

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getExceptMsg() {
		return exceptMsg;
	}

	public void setExceptMsg(String exceptMsg) {
		this.exceptMsg = exceptMsg;
	}

	public String getDataTypeName() {
		return dataTypeName;
	}

	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}

	public String getObjectIdName() {
		return objectIdName;
	}

	public void setObjectIdName(String objectIdName) {
		this.objectIdName = objectIdName;
	}

	public String getBillTimeMsg() {
		return billTimeMsg;
	}

	public void setBillTimeMsg(String billTimeMsg) {
		this.billTimeMsg = billTimeMsg;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

}
