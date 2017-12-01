package com.ai.baas.bmc.srv.entity;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ai.baas.bmc.srv.message.RecordFmt.RecordFmtKey;
import com.ai.baas.bmc.srv.util.BaasConstants;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class BillingDetailRecord {
	private RecordFmtKey recordFmtKey;
	private Map<String, String> data;    //详单数据
	private Map<String, Object> msgData; //发送消息数据
	private String accountPeriod;
	private List<SubjectAndPrice> fees = Lists.newArrayList();
	private boolean isPriceSuccess = false; //是否批价成功
	//private boolean isInstDr = true; //是否插入详单
	
	public Map<String, String> getData() {
		return data;
	}
	
	public String getDataValue(String key) {
		return data.get(key);
	}
	
	public void setData(Map<String, String> data) {
		this.data = data;
		if (data != null) {
			this.recordFmtKey = new RecordFmtKey(
					data.get(BaasConstants.TENANT_ID),
					data.get(BaasConstants.SERVICE_TYPE),
					"");
			this.accountPeriod = data.get(BaasConstants.ACCOUNT_PERIOD).substring(0, 6);
		}
	}

	public Map<String, Object> getMsgData() {
		return msgData;
	}

	public void setMsgData(Map<String, String> data) {
		this.msgData = Maps.newHashMap();
		for(Entry<String,String> entry:data.entrySet()){
			msgData.put(entry.getKey(), entry.getValue());
		}
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

	public boolean isPriceSuccess() {
		return isPriceSuccess;
	}

	public void setPriceSuccess(boolean isPriceSuccess) {
		this.isPriceSuccess = isPriceSuccess;
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

}
