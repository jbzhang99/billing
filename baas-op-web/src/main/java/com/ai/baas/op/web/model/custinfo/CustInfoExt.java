package com.ai.baas.op.web.model.custinfo;

import com.ai.baas.bmc.api.custInfo.params.CustInfo;

public class CustInfoExt extends CustInfo{

	
	private static final long serialVersionUID = 1L;
	private String CustGradeVal;

	public String getCustGradeVal() {
		return CustGradeVal;
	}

	public void setCustGradeVal(String custGradeVal) {
		CustGradeVal = custGradeVal;
	}
	
}
