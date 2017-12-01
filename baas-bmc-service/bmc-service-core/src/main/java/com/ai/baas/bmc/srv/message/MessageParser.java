package com.ai.baas.bmc.srv.message;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.srv.failbill.BusinessException;
import com.ai.baas.bmc.srv.message.RecordFmt.RecordFmtKey;
import com.ai.baas.bmc.srv.util.BaasConstants;

public class MessageParser {
	private static Logger logger = LoggerFactory.getLogger(MessageParser.class);
	private Map<String, String> data = new HashMap<String, String>();
	private static String[] headerKeys;
	
	static{
		headerKeys = new String[] { BaasConstants.TENANT_ID,
				BaasConstants.SERVICE_TYPE, BaasConstants.SOURCE,
				BaasConstants.BATCH_SERIAL_NUMBER, BaasConstants.SERIAL_NUMBER,
				BaasConstants.ARRIVAL_TIME, BaasConstants.ACCOUNT_PERIOD };
	}
	
	private void build(String original) throws BusinessException{
		String[] inputParams = StringUtils.splitPreserveAllTokens(original,BaasConstants.FIELD_SPLIT);
		if (inputParams.length < 7) {
			throw new BusinessException("BMC-B0004","不正确的数据格式!"); 
		}
		for(int i=0;i<headerKeys.length;i++){
			data.put(headerKeys[i], inputParams[i]);
		}
		RecordFmtKey recordFmtKey = new RecordFmtKey(data.get(BaasConstants.TENANT_ID),data.get(BaasConstants.SERVICE_TYPE),data.get(BaasConstants.SOURCE));
		Map<String, Integer> mappingRule = MappingRule.getIndexes(recordFmtKey);
		if (mappingRule == null) {
			throw new BusinessException("BMC-B0005","bmc_record_fmt表中没有配置报文格式!");
		}
		int dataBeginPosi = headerKeys.length;
		String[] inputDatas = new String[mappingRule.size()];
		System.arraycopy(inputParams, dataBeginPosi, inputDatas, 0, inputParams.length - dataBeginPosi);
		
		for (Entry<String, Integer> entry : mappingRule.entrySet()) {
			data.put(entry.getKey(), inputDatas[entry.getValue()]);
		}
	}
	
	public Map<String, String> getData() {
		return data;
	}
	
	public static Map<String,String> parseObject(String original) throws BusinessException{
		if(StringUtils.isBlank(original)){
			throw new BusinessException("BMC-B0003","input String is null!");
		}
		MessageParser messageParser = new MessageParser();
		messageParser.build(original);
		return messageParser.getData();
	}
	
}
