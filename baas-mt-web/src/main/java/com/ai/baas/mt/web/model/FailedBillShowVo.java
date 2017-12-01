package com.ai.baas.mt.web.model;

import java.util.Date;
import com.ai.baas.bmc.api.failedbillmaintain.params.FailedBill;

public class FailedBillShowVo extends FailedBill{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String serviceTypeName;
	private Date failBillDate;

	public String getServiceTypeName() {
		return serviceTypeName;
	}

	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}
	public Date getFailBillDate() {
		return new Date(failBillDate.getTime());
	}

	public void setFailBillDate(Date failBillDate) {
		this.failBillDate = new Date(failBillDate.getTime());
	}
	
}
