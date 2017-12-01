package com.ai.baas.batch.client.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.ai.baas.batch.client.constants.ClientConstants;
import com.ai.baas.batch.client.core.GetRecordDetail;
import com.ai.baas.batch.client.service.ICalProcessor;
import com.ai.baas.batch.client.util.HttpClientUtil;
import com.ai.baas.batch.client.util.MyJsonUtil;
import com.ai.baas.batch.client.util.UserIdUtil;
import com.ai.baas.batch.client.util.getUrl;
import com.ai.baas.batch.client.util.lastDayUtil;


public class SlbCalProcessorImpl implements ICalProcessor{
	private static final Logger LOG=LoggerFactory.getLogger(SlbCalProcessorImpl.class);
	@Override
	public void buildCalAdapt(String response,String serviceId) throws Exception {
		String slb=getUrl.getInstance().getSlb();
		if(!("".equals(slb))){
			response=slb;
			LOG.debug("the slb is  "+slb);
		}
		JSONObject [] jsonarray=MyJsonUtil.analyJson(response);
		String rtmUrl=getUrl.getInstance().getRtm();
		LOG.debug("the jsonarray "+jsonarray.toString());
		for(int i=0;i<jsonarray.length;i++){
			String instanceId=jsonarray[i].getString(ClientConstants.INSTANCE_ID);
			System.out.println("the instance id is "+instanceId);
			List<Map<String, String>> results=UserIdUtil.getUserId(instanceId);
			String userId=null;
			if(results==null){
				System.out.println("SLB instance id "+instanceId +"  not get user_id");	
			}
			else{
				for(Map<String, String> data:results){
					for(Entry<String, String> entry:data.entrySet()){
						String key=entry.getKey();
						if("user_id".equals(key))
							userId=entry.getValue();
					}
				}
			
				//String userId="1234";
				String endTime=jsonarray[i].getString(ClientConstants.END_TIME);
				String startTime=jsonarray[i].getString(ClientConstants.START_TIME);
				String regionId=jsonarray[i].getString(ClientConstants.REGION_ID);
				String lastTime=lastDayUtil.difHour(startTime, endTime);
				String start=lastDayUtil.norTime(startTime);
				//开始进行消息头的拼接
				StringBuilder timeString=new StringBuilder();
				StringBuilder streamString=new StringBuilder();
				timeString.append(lastDayUtil.messageHead(userId, ClientConstants.SOURCE_TIME,ClientConstants.ASLB_SYSTEM)).append(ClientConstants.HEAD_SPLIT);
				StringBuilder timeMessage= new StringBuilder();
				timeMessage.append(ClientConstants.SOURCE_SLB).append(ClientConstants.MESSAGE_SPLIT).append(userId).append(ClientConstants.MESSAGE_SPLIT).append(instanceId)
				.append(ClientConstants.MESSAGE_SPLIT).append(regionId).append(ClientConstants.MESSAGE_SPLIT).append(lastTime).append(ClientConstants.MESSAGE_SPLIT).append(start).append(ClientConstants.MESSAGE_SPLIT).append("TIME");
				timeString.append(timeMessage.toString());
				//开始进行时间消息的发送
				LOG.debug("the timeString "+timeString.toString());
				StringBuilder sentTime=new StringBuilder();
				sentTime.append(ClientConstants.RTMHEAD).append(timeString.toString()).append(ClientConstants.RTMEND);
				HttpClientUtil.send(rtmUrl, sentTime.toString());
				
				LOG.debug("the senttile is "+sentTime.toString());
				String netOut=jsonarray[i].getString(ClientConstants.NET_OUT);
				//String netIn=jsonarray[i].getString(clientConstants.NET_IN);
				if("0".equals(netOut))
					continue;
				else{
					streamString.append(lastDayUtil.messageHead(userId, ClientConstants.SOURCE_STREAM,ClientConstants.ASLB_SYSTEM)).append(ClientConstants.HEAD_SPLIT);
					StringBuilder streamMessage= new StringBuilder();
					streamMessage.append(ClientConstants.SOURCE_SLB).append(ClientConstants.MESSAGE_SPLIT).append(userId).append(ClientConstants.MESSAGE_SPLIT).append(instanceId)
					.append(ClientConstants.MESSAGE_SPLIT).append(regionId).append(ClientConstants.MESSAGE_SPLIT).append(netOut).append(ClientConstants.MESSAGE_SPLIT).append(start).append(ClientConstants.MESSAGE_SPLIT).append("STREAM");
					streamString.append(streamMessage.toString());
					LOG.debug("the stream is "+streamString.toString());
					StringBuilder sentStream=new StringBuilder();
					sentStream.append(ClientConstants.RTMHEAD).append(streamString.toString()).append(ClientConstants.RTMEND);
					HttpClientUtil.send(rtmUrl, sentStream.toString());
					LOG.debug("the senttile is "+sentStream.toString());
					//HttpClientUtil.send(rtmUrl, "{\"transData\":\"1322131\"}");
				}
			}
		}
	}

}
