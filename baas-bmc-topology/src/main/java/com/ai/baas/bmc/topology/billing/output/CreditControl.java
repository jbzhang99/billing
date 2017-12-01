package com.ai.baas.bmc.topology.billing.output;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.topology.entity.BillingDetailRecord;
import com.ai.baas.bmc.topology.util.BmcConstants;
import com.ai.baas.storm.util.BaseConstants;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

public class CreditControl extends AbstractMDSOutput {
	private static Logger logger = LoggerFactory.getLogger(CreditControl.class);
	private Gson jsonConverter = new Gson();
	
//	public CreditControl(Map<String,String> config){
//		String topic = config.get("billing.output.mds.credit.control.topic");
//		String broker = config.get(BROKER_LIST);
//		String partitionStr = config.get("billing.output.mds.credit.control.partition");
//	    super.init(topic, broker, partitionStr);
//	}
	
	   public CreditControl(Map<String,String> config){
	        String topic = config.get("billing.output.mds.credit.control.topic");
	        String broker = config.get(BROKER_LIST);
	        String partitionStr = config.get("billing.output.mds.credit.control.partition");
	        String authAddr = config.get("billing.output.mds.authAddr");
	        String pId = config.get("billing.output.mds.authPid");
	        String password = config.get("billing.output.mds.servicePasswd");
	        String srvId = config.get("billing.output.mds.credit.control.srvId");
	        super.init(topic, broker, partitionStr, authAddr , pId, password , srvId);
	    }

	@Override
	public String assembleData(BillingDetailRecord detailRecord) {
		Map<String, String> sendData = Maps.newHashMap();
		Map<String, String> data = detailRecord.getData();
		sendData.put(BmcConstants.EVENT_ID, data.get(BaseConstants.SERIAL_NUMBER));
		sendData.put(BmcConstants.SYSTEM_ID, data.get(BmcConstants.SYSTEM_ID));
		sendData.put(BaseConstants.TENANT_ID, data.get(BaseConstants.TENANT_ID));
		sendData.put(BmcConstants.SOURCE_TYPE, BmcConstants.SOURCE_TYPE_VALUE);
		sendData.put(BmcConstants.OWNER_TYPE, BmcConstants.OWNER_TYPE_SERV);
		sendData.put(BmcConstants.OWNER_ID, data.get(BaseConstants.SUBS_ID));
		sendData.put(BmcConstants.EVENT_TYPE, BmcConstants.EVENT_TYPE_SUB_DATA);
		sendData.put(BmcConstants.AMOUNT, "0");
		sendData.put(BmcConstants.AMOUNT_MARK, BmcConstants.AMOUNT_MARK_MINUS);
		sendData.put(BmcConstants.AMOUNT_TYPE, BmcConstants.AMOUNT_TYPE_DATA);
		sendData.put(BmcConstants.EXPANDED_INFO, "{}");
		String sendStr = jsonConverter.toJson(sendData);
		logger.debug("send CreditControl data="+sendStr);
		System.out.println("send CreditControl data="+sendStr);
		return jsonConverter.toJson(sendData);
	}
	

}
