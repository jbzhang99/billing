package com.ai.baas.batch.client.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ai.baas.batch.client.constants.ClientConstants;
import com.ai.baas.batch.client.service.ICalProcessor;
import com.ai.baas.batch.client.util.HttpClientUtil;
import com.ai.baas.batch.client.util.MyJsonUtil;
import com.ai.baas.batch.client.util.UserIdUtil;
import com.ai.baas.batch.client.util.getUrl;
import com.ai.baas.batch.client.util.lastDayUtil;
import com.alibaba.fastjson.JSONObject;

public class SmartCalProcessorImpl implements ICalProcessor{

	@Override
	public void buildCalAdapt(String response, String serviceId) throws Exception {
		JSONObject [] jsonarray=MyJsonUtil.analyJson(response);
		String rtmUrl=getUrl.getInstance().getRtm();
		for(int i=0;i<jsonarray.length;i++){
			String instanceId=jsonarray[i].getString(ClientConstants.INSTANCE_ID);
			List<Map<String, String>> results=UserIdUtil.getUserId(instanceId);
			String userId=null;
			for(Map<String, String> data:results){
				for(Entry<String, String> entry:data.entrySet()){
					String key=entry.getKey();
					if("user_id".equals(key))
						userId=entry.getValue();
				}
			}
			String endTime=jsonarray[i].getString(ClientConstants.END_TIME);
			String startTime=jsonarray[i].getString(ClientConstants.START_TIME);
			String lastTime=lastDayUtil.difHour(startTime, endTime);
			String start=lastDayUtil.norTime(startTime);
			//开始进行消息头的拼接
			StringBuilder timeString=new StringBuilder();
			timeString.append(lastDayUtil.messageHead(userId, ClientConstants.SOURCE_TIME,ClientConstants.AECS_SYSTEM)).append(ClientConstants.HEAD_SPLIT);
			StringBuilder timeMessage= new StringBuilder();
			timeMessage.append(ClientConstants.SOURCE_TIME).append(ClientConstants.MESSAGE_SPLIT).append(userId)
			.append(ClientConstants.MESSAGE_SPLIT).append(start).append(ClientConstants.MESSAGE_SPLIT).append(lastTime);
			timeString.append(timeMessage.toString());
			//开始进行时间消息的发送
			StringBuilder sentTime=new StringBuilder();
			sentTime.append(ClientConstants.RTMHEAD).append(timeString.toString()).append(ClientConstants.RTMEND);
			HttpClientUtil.send(rtmUrl, sentTime.toString());
		}
	}

}
