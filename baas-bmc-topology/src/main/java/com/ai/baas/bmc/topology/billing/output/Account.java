package com.ai.baas.bmc.topology.billing.output;

import com.ai.baas.bmc.topology.entity.BillingDetailRecord;
import com.ai.baas.bmc.topology.entity.SubjectAndPrice;
import com.ai.baas.storm.util.BaseConstants;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class Account extends AbstractMDSOutput {
	private static Logger logger = LoggerFactory.getLogger(Account.class);
	private Gson jsonConverter = new Gson();


//	public Account(Map<String,String> config){
//		String topic = config.get("billing.output.mds.account.topic");
//		String broker = config.get(BROKER_LIST);
//		String partitionStr = config.get("billing.output.mds.account.partition");
//		super.init(topic, broker, partitionStr);
//	}
	public Account(Map<String,String> config){
        String topic = config.get("billing.output.mds.account.topic");
        String broker = config.get(BROKER_LIST);
        String partitionStr = config.get("billing.output.mds.account.partition");
        String authAddr = config.get("billing.output.mds.authAddr");
        String pId = config.get("billing.output.mds.authPid");
        String password = config.get("billing.output.mds.servicePasswd");
        String srvId = config.get("billing.output.mds.account.srvId");
        super.init(topic, broker, partitionStr, authAddr , pId, password , srvId);
    }


	@Override
	public String assembleData(BillingDetailRecord detailRecord) {
		StringBuilder out = new StringBuilder();
		Map<String, String> data = detailRecord.getData();
		out.append(data.get(BaseConstants.TENANT_ID)).append(BaseConstants.FIELD_SPLIT);
		out.append(data.get(BaseConstants.SERVICE_TYPE)).append(BaseConstants.FIELD_SPLIT);
		out.append(data.get(BaseConstants.SOURCE)).append(BaseConstants.FIELD_SPLIT);
		out.append(data.get(BaseConstants.BATCH_SERIAL_NUMBER)).append(BaseConstants.FIELD_SPLIT);
		out.append(data.get(BaseConstants.SERIAL_NUMBER)).append(BaseConstants.FIELD_SPLIT);
		out.append(data.get(BaseConstants.CUST_ID)).append(BaseConstants.FIELD_SPLIT);
		out.append(data.get(BaseConstants.SUBS_ID)).append(BaseConstants.FIELD_SPLIT);
		out.append(data.get(BaseConstants.ACCT_ID)).append(BaseConstants.FIELD_SPLIT);
		out.append(data.get(BaseConstants.START_TIME)).append(BaseConstants.FIELD_SPLIT);
		out.append("").append(BaseConstants.FIELD_SPLIT);//TRADE_SEQ
		List<SubjectAndPrice> fees = detailRecord.getFees();
		for (int i=0; i<3; i++) {
			if (i < fees.size()) {
				out.append(fees.get(i).getPrice()).append(BaseConstants.FIELD_SPLIT);
				out.append(fees.get(i).getSubjectCode()).append(BaseConstants.FIELD_SPLIT);
			} else {
				out.append("").append(BaseConstants.FIELD_SPLIT);
				out.append("").append(BaseConstants.FIELD_SPLIT);
			}
		}
		return out.deleteCharAt(out.length()-1).toString();
	}


}
