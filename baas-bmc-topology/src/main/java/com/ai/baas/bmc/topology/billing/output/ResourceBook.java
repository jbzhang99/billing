package com.ai.baas.bmc.topology.billing.output;

import java.math.BigDecimal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.topology.entity.BillingDetailRecord;
import com.ai.baas.bmc.topology.util.BmcConstants;
import com.ai.baas.storm.util.BaseConstants;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

public class ResourceBook extends AbstractMDSOutput {
	private static Logger logger = LoggerFactory.getLogger(CreditControl.class);
	private Gson jsonConverter = new Gson();
	
	public ResourceBook(Map<String,String> config){
		String topic = config.get("billing.output.mds.resourcebook.topic");
        String broker = config.get(BROKER_LIST);
        String partitionStr = config.get("billing.output.mds.resourcebook.partition");
        String authAddr = config.get("billing.output.mds.authAddr");
        String pId = config.get("billing.output.mds.authPid");
        String password = config.get("billing.output.mds.servicePasswd");
        String srvId = config.get("billing.output.mds.resourcebook.srvId");
        super.init(topic, broker, partitionStr, authAddr , pId, password , srvId);
		//super.init(topic, broker, partitionStr);
	}
	
	@Override
	public String assembleData(BillingDetailRecord detailRecord) {
		Map<String, String> sendData = Maps.newHashMap();
		Map<String, String> data = detailRecord.getData();
		sendData.put(BmcConstants.EVENT_ID, data.get(BaseConstants.SERIAL_NUMBER));
		sendData.put("business_id", "");
		sendData.put(BaseConstants.TENANT_ID, data.get(BaseConstants.TENANT_ID));
		sendData.put(BaseConstants.ACCT_ID, data.get(BaseConstants.ACCT_ID));
		sendData.put(BaseConstants.SUBS_ID, data.get(BaseConstants.SUBS_ID));
		//新增话单开始时间
		sendData.put(BmcConstants.START_TIME, data.get(BmcConstants.START_TIME));
		BigDecimal upStream = new BigDecimal(data.get("gprs_up"));
		logger.info("get down_stream................" + data.get("gprs_down"));
		//BigDecimal downStream = new BigDecimal(Double.parseDouble(fields.get("down_stream")));
		BigDecimal downStream = new BigDecimal(data.get("gprs_down"));
		sendData.put(BmcConstants.AMOUNT, upStream.add(downStream).setScale(0, BigDecimal.ROUND_UP).toPlainString());
		sendData.put(BmcConstants.AMOUNT_TYPE, BmcConstants.AMOUNT_TYPE_DATA);
		String sendStr = jsonConverter.toJson(sendData);
		logger.debug("send ResourceBook data="+sendStr);
		System.out.println("send ResourceBook data="+sendStr);
		return jsonConverter.toJson(sendData);
	}
	

}
