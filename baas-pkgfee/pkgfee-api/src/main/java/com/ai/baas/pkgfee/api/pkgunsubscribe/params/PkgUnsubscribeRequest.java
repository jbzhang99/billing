package com.ai.baas.pkgfee.api.pkgunsubscribe.params;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 包费退订入参
 * 
 * Date: 2017年5月22日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author wangjing19
 */
public class PkgUnsubscribeRequest extends BaseInfo{

	private static final long serialVersionUID = 1L;
	
	private String instanceId;
	
	private String priceCode;
	
	private String unsubTime;

	private String extCustId;
	
	private String busiSerialNo;
	
	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(String priceCode) {
		this.priceCode = priceCode;
	}

	public String getUnsubTime() {
		return unsubTime;
	}

	public void setUnsubTime(String unsubTime) {
		this.unsubTime = unsubTime;
	}

	public String getExtCustId() {
		return extCustId;
	}

	public void setExtCustId(String extCustId) {
		this.extCustId = extCustId;
	}

	public String getBusiSerialNo() {
		return busiSerialNo;
	}

	public void setBusiSerialNo(String busiSerialNo) {
		this.busiSerialNo = busiSerialNo;
	}
	
	
}
