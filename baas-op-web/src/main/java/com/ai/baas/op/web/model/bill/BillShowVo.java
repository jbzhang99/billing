package com.ai.baas.op.web.model.bill;

import com.ai.baas.amc.api.billQuery.params.Bill;

public class BillShowVo extends Bill {
	
	private static final long serialVersionUID = 1L;
	
	private String orgFeeD;
    private String disFeeD;
    private String totalFeeD;
	public String getOrgFeeD() {
		return orgFeeD;
	}
	public void setOrgFeeD(String orgFeeD) {
		this.orgFeeD = orgFeeD;
	}
	public String getDisFeeD() {
		return disFeeD;
	}
	public void setDisFeeD(String disFeeD) {
		this.disFeeD = disFeeD;
	}
	public String getTotalFeeD() {
		return totalFeeD;
	}
	public void setTotalFeeD(String totalFeeD) {
		this.totalFeeD = totalFeeD;
	}
    
}
