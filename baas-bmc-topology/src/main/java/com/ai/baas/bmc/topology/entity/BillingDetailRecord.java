package com.ai.baas.bmc.topology.entity;

import java.util.List;
import java.util.Map;

import com.ai.baas.storm.message.RecordFmt.RecordFmtKey;
import com.ai.baas.storm.util.BaseConstants;
import com.google.common.collect.Lists;

/**
 * 计费详单信息
 * @author majun
 *
 */
public class BillingDetailRecord {
	private RecordFmtKey recordFmtKey;
	private Map<String, String> data;
	private String accountPeriod;
	private List<SubjectAndPrice> fees = Lists.newArrayList();
	
	public Map<String, String> getData() {
		return data;
	}
	
	public String getDataValue(String key) {
		return data.get(key);
	}
	
	public void setData(Map<String, String> data) {
		this.data = data;
		if (data != null) {
			/*this.recordFmtKey = new RecordFmtKey(
					data.get(BaseConstants.TENANT_ID),
					data.get(BaseConstants.SERVICE_TYPE),
					data.get(BaseConstants.SOURCE));*/
			this.recordFmtKey = new RecordFmtKey(
					data.get(BaseConstants.TENANT_ID),
					data.get(BaseConstants.SERVICE_TYPE),
					"");
			this.accountPeriod = data.get(BaseConstants.ACCOUNT_PERIOD).substring(0, 6);
		}
	}
	
	public List<SubjectAndPrice> getFees() {
		return fees;
	}
	
	public void setFees(List<SubjectAndPrice> fees) {
		this.fees = fees;
	}
	
	public void addFee(SubjectAndPrice subjectAndPrice){
		fees.add(subjectAndPrice);
	}

	public RecordFmtKey getRecordFmtKey() {
		return recordFmtKey;
	}

	public void setRecordFmtKey(RecordFmtKey recordFmtKey) {
		this.recordFmtKey = recordFmtKey;
	}

	public String getAccountPeriod() {
		return accountPeriod;
	}

	public void setAccountPeriod(String accountPeriod) {
		this.accountPeriod = accountPeriod;
	}
	
}
