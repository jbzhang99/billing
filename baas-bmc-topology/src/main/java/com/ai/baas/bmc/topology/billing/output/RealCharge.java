package com.ai.baas.bmc.topology.billing.output;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.topology.cache.CacheProxy;
import com.ai.baas.bmc.topology.entity.BillingDetailRecord;
import com.ai.baas.bmc.topology.entity.SubjectAndPrice;
import com.ai.baas.storm.util.BaseConstants;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;

public class RealCharge implements IOutput {
	private static Logger logger = LoggerFactory.getLogger(RealCharge.class);
	//private ICacheClient cacheClient = CacheProxy.getInstance().getCacheClient();
	
	
	@Override
	public void send(BillingDetailRecord detailRecord) {
		String tableName = assembleRealChargeTableName(detailRecord.getAccountPeriod());
		String columnPrefix = assembleColunmPrefix(detailRecord);
		for(SubjectAndPrice fee:detailRecord.getFees()){
			StringBuilder colunm = new StringBuilder(columnPrefix);
			colunm.append(fee.getSubjectCode());
			CacheProxy.getInstance().getCacheClient().hincrByFloat(tableName, colunm.toString(), fee.getPrice().doubleValue());
		}
	}
	
	
	private String assembleRealChargeTableName(String accountPeriod){
		StringBuilder tableName = new StringBuilder();
		tableName.append("real_charge_");
		tableName.append(accountPeriod.substring(0, 6));
		return tableName.toString();
	}
	
	
	private String assembleColunmPrefix(BillingDetailRecord detailRecord){
		StringBuilder colunmPrefix = new StringBuilder();
		colunmPrefix.append(detailRecord.getRecordFmtKey().getTenantId()).append(":");
		colunmPrefix.append(detailRecord.getData().get(BaseConstants.ACCT_ID)).append(":");
		colunmPrefix.append(detailRecord.getData().get(BaseConstants.SUBS_ID)).append(":");
		colunmPrefix.append(detailRecord.getData().get("service_num")).append(":");
		return colunmPrefix.toString();
	}

}
