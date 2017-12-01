package com.ai.baas.bmc.srv.api.entry.params;

import java.io.Serializable;
import java.util.Map;

public class BillingData implements Serializable {
	private static final long serialVersionUID = 7291060132406234457L;
	private String sn;//全局唯一
	private String account_period; // YYYYMM
	private Map<String, String> dataItem;// key:value

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getAccount_period() {
		return account_period;
	}

	public void setAccount_period(String account_period) {
		this.account_period = account_period;
	}

	public Map<String, String> getDataItem() {
		return dataItem;
	}

	public void setDataItem(Map<String, String> dataItem) {
		this.dataItem = dataItem;
	}
}
