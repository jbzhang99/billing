package com.ai.baas.batch.client.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.ai.baas.batch.client.constants.ClientConstants;
import com.ai.baas.batch.client.service.ICalProcessor;
import com.ai.baas.batch.client.util.HttpClientUtil;
import com.ai.baas.batch.client.util.MsgUtil;
import com.ai.baas.batch.client.util.MyJsonUtil;
import com.ai.baas.batch.client.util.UserIdUtil;
import com.ai.baas.batch.client.util.getUrl;
import com.ai.baas.batch.client.util.lastDayUtil;

public class KvsCalProcessorImpl implements ICalProcessor{
	private static final Logger LOG=LoggerFactory.getLogger(KvsCalProcessorImpl.class);
	@Override
	public void buildCalAdapt(String response, String serviceId) throws Exception {
		String kvs=getUrl.getInstance().getKVS();
		if(!("".equals(kvs))){
			response=kvs;
			LOG.debug("the kvs is    "+response);
		}
		JSONObject [] jsonarray=MyJsonUtil.analyJson(response);
		String rtmUrl=getUrl.getInstance().getRtm();
		for(int i=0;i<jsonarray.length;i++){
			String instanceId=jsonarray[i].getString(ClientConstants.INSTANCE_ID);
			String regionId=jsonarray[i].getString(ClientConstants.REGION_ID);
			List<Map<String, String>> results=UserIdUtil.getUserId(instanceId);
			String userId=null;
			if(results==null){
				System.out.println("KVS instance id "+instanceId +"  not get user_id");	
			}
			else{
				for(Map<String, String> data:results){
					for(Entry<String, String> entry:data.entrySet()){
						String key=entry.getKey();
						if("user_id".equals(key))
							userId=entry.getValue();
					}
				}
				String startTime=jsonarray[i].getString(ClientConstants.START_TIME);
				String storage=jsonarray[i].getString(ClientConstants.KVS_MEMCACHE);
				String endTime=jsonarray[i].getString(ClientConstants.END_TIME);
				String start=lastDayUtil.norTime(startTime);
				String lastTime=lastDayUtil.difHour(startTime, endTime);
				if(!"0".equals(lastTime)){
					StringBuilder storageString=new StringBuilder();
					storageString.append(lastDayUtil.messageHead(userId, ClientConstants.SOURCE_STORAGE,ClientConstants.AKVS_SYSTEM)).append(ClientConstants.HEAD_SPLIT);
					StringBuilder storageMessage= new StringBuilder();
					storageMessage.append(ClientConstants.SOURCE_KVS).append(ClientConstants.MESSAGE_SPLIT).append(userId).append(ClientConstants.MESSAGE_SPLIT).append(instanceId)
					.append(ClientConstants.MESSAGE_SPLIT).append(regionId).append(ClientConstants.MESSAGE_SPLIT).append(lastTime).append(ClientConstants.MESSAGE_SPLIT).append(start).append(ClientConstants.MESSAGE_SPLIT).append("TIME");
					storageString.append(storageMessage.toString());
					StringBuilder sentStorage=new StringBuilder();
					sentStorage.append(ClientConstants.RTMHEAD).append(storageString.toString()).append(ClientConstants.RTMEND);
					//HttpClientUtil.send(rtmUrl, sentStorage.toString());
					MsgUtil.putrtmMsg(sentStorage.toString());
				}
			}
		}
		
	}

}
