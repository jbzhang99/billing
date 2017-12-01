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

public class OssCalProcessorImpl implements ICalProcessor{
	private static final Logger LOG=LoggerFactory.getLogger(OssCalProcessorImpl.class);
	@Override
	public void buildCalAdapt(String response, String serviceId) throws Exception {
		String oss=getUrl.getInstance().getOSS();
		if(!("".equals(oss))){
			response=oss;
			LOG.debug("hte oss is   "+response);
		}
//		response="{\"data\":\"{\\\"requestId\\\":\\\"57b93b65c9e77c000960f103\\\",\\\"state\\\":\\\"succeeded\\\",\\\"usage_and_expenses_format\\\":[{\\\"name\\\":\\\"sync_in\\\",\\\"in_list\\\":true},{\\\"name\\\":\\\"bucket\\\",\\\"in_list\\\":true},{\\\"name\\\":\\\"network_in\\\",\\\"in_list\\\":true},{\\\"name\\\":\\\"network_out\\\",\\\"in_list\\\":true},{\\\"name\\\":\\\"put_request\\\",\\\"in_list\\\":true},{\\\"name\\\":\\\"get_request\\\",\\\"in_list\\\":true},{\\\"name\\\":\\\"sync_out\\\",\\\"in_list\\\":true},{\\\"name\\\":\\\"storage\\\",\\\"in_list\\\":true},{\\\"name\\\":\\\"region\\\",\\\"in_list\\\":true},{\\\"name\\\":\\\"provider_id\\\",\\\"in_list\\\":true},{\\\"name\\\":\\\"cdn_in\\\",\\\"in_list\\\":true},{\\\"name\\\":\\\"end_time\\\",\\\"in_list\\\":true},{\\\"name\\\":\\\"start_time\\\",\\\"in_list\\\":true},{\\\"name\\\":\\\"cdn_out\\\",\\\"in_list\\\":true},{\\\"name\\\":\\\"RegionId\\\",\\\"in_list\\\":true}],\\\"usage_and_expenses_data\\\":[{\\\"put_request\\\":\\\"271\\\",\\\"network_out\\\":\\\"222\\\",\\\"sync_out\\\":\\\"0\\\",\\\"network_in\\\":\\\"0\\\",\\\"end_time\\\":\\\"2016-08-19T00:00:00Z\\\",\\\"supplier_instance_id\\\":\\\"1596769687493741\\\",\\\"sync_in\\\":\\\"0\\\",\\\"storage\\\":\\\"123\\\",\\\"bucket\\\":\\\"quota_for_get_service_\\\",\\\"start_time\\\":\\\"2016-08-18T00:00:00Z\\\",\\\"cdn_in\\\":\\\"0\\\",\\\"instance_id\\\":\\\"c6f3503c-3533-4990-8b39-87af942dac171\\\",\\\"cdn_out\\\":\\\"0\\\",\\\"get_request\\\":\\\"0\\\",\\\"provider_id\\\":\\\"26842\\\",\\\"RegionId\\\":\\\"cn-beijing\\\",\\\"region\\\":\\\"cn-hangzhou\\\"}]}\",\"resultCode\":\"000000\",\"resultMessage\":\"请求成功\"}";
		JSONObject [] jsonarray=MyJsonUtil.analyJson(response);
		String rtmUrl=getUrl.getInstance().getRtm();
		for(int i=0;i<jsonarray.length;i++){
			String instanceId=jsonarray[i].getString(ClientConstants.INSTANCE_ID);
			String regionId=jsonarray[i].getString(ClientConstants.REGION_ID);
			List<Map<String, String>> results=UserIdUtil.getUserId(instanceId);
			String userId=null;
			if(results==null){
				System.out.println("Oss instance id "+instanceId +"  not get user_id");	
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
				String getResquest=jsonarray[i].getString(ClientConstants.GET_REQUEST);
				String putRequest=jsonarray[i].getString(ClientConstants.PUT_REQUEST);
				String netOut=jsonarray[i].getString(ClientConstants.NET_OUT);
				//String netIn=jsonarray[i].getString(clientConstants.NET_IN);
				String storage=jsonarray[i].getString(ClientConstants.STORAGE);
				String bucket=jsonarray[i].getString(ClientConstants.BUCKET);
				String start=lastDayUtil.norTime(startTime);
				StringBuilder requestString=new StringBuilder();
				StringBuilder getString=new StringBuilder();
				if(!"0".equals(getResquest)){
					getString.append(lastDayUtil.messageHead(userId, ClientConstants.SOURCE_RES,ClientConstants.AOSS_SYSTEM)).append(ClientConstants.HEAD_SPLIT);
					StringBuilder getMessage= new StringBuilder();
					getMessage.append(ClientConstants.SOURCE_OSS).append(ClientConstants.MESSAGE_SPLIT).append(userId).append(ClientConstants.MESSAGE_SPLIT).append(instanceId).append(ClientConstants.MESSAGE_SPLIT).append(regionId)
					.append(ClientConstants.MESSAGE_SPLIT).append(getResquest).append(ClientConstants.MESSAGE_SPLIT).append(start).append(ClientConstants.MESSAGE_SPLIT).append("API").append(ClientConstants.MESSAGE_SPLIT).append("get").append(ClientConstants.MESSAGE_SPLIT).append(bucket);
					getString.append(getMessage.toString());
					StringBuilder sentGet=new StringBuilder();
					sentGet.append(ClientConstants.RTMHEAD).append(getString.toString()).append(ClientConstants.RTMEND);
					//HttpClientUtil.send(rtmUrl, sentGet.toString());
					MsgUtil.putrtmMsg(sentGet.toString());
				}
				if(!"0".equals(putRequest)){
				//开始进行消息头的拼接
					requestString.append(lastDayUtil.messageHead(userId, ClientConstants.SOURCE_RES,ClientConstants.AOSS_SYSTEM)).append(ClientConstants.HEAD_SPLIT);
					StringBuilder resMessage= new StringBuilder();
					resMessage.append(ClientConstants.SOURCE_OSS).append(ClientConstants.MESSAGE_SPLIT).append(userId).append(ClientConstants.MESSAGE_SPLIT).append(instanceId).append(ClientConstants.MESSAGE_SPLIT).append(regionId)
					.append(ClientConstants.MESSAGE_SPLIT).append(putRequest).append(ClientConstants.MESSAGE_SPLIT).append(start).append(ClientConstants.MESSAGE_SPLIT).append("API").append(ClientConstants.MESSAGE_SPLIT).append("put").append(ClientConstants.MESSAGE_SPLIT).append(bucket);
					requestString.append(resMessage.toString()); 
					//开始进行时间消息的发送
					StringBuilder sentPut=new StringBuilder();
					sentPut.append(ClientConstants.RTMHEAD).append(requestString.toString()).append(ClientConstants.RTMEND);
					//HttpClientUtil.send(rtmUrl,sentPut.toString());
					MsgUtil.putrtmMsg(sentPut.toString());
				}
				
				if((!"0".equals(netOut))){
	
					StringBuilder streamString=new StringBuilder();
					streamString.append(lastDayUtil.messageHead(userId, ClientConstants.SOURCE_STREAM,ClientConstants.AOSS_SYSTEM)).append(ClientConstants.HEAD_SPLIT);
					StringBuilder streamMessage= new StringBuilder();
					streamMessage.append(ClientConstants.SOURCE_OSS).append(ClientConstants.MESSAGE_SPLIT).append(userId).append(ClientConstants.MESSAGE_SPLIT).append(instanceId).append(ClientConstants.MESSAGE_SPLIT).append(regionId)
					.append(ClientConstants.MESSAGE_SPLIT).append(netOut).append(ClientConstants.MESSAGE_SPLIT).append(start).append(ClientConstants.MESSAGE_SPLIT).append("STREAM").append(ClientConstants.MESSAGE_SPLIT).append(" ").append(ClientConstants.MESSAGE_SPLIT).append(bucket);
					streamString.append(streamMessage.toString());
					StringBuilder sentNet=new StringBuilder();
					sentNet.append(ClientConstants.RTMHEAD).append(streamString.toString()).append(ClientConstants.RTMEND);
					//HttpClientUtil.send(rtmUrl, sentNet.toString());
					MsgUtil.putrtmMsg(sentNet.toString());
				}
				if(!"0".equals(storage)){
					StringBuilder storageString=new StringBuilder();
					storageString.append(lastDayUtil.messageHead(userId, ClientConstants.SOURCE_STORAGE,ClientConstants.AOSS_SYSTEM)).append(ClientConstants.HEAD_SPLIT);
					StringBuilder storageMessage= new StringBuilder();
					storageMessage.append(ClientConstants.SOURCE_OSS).append(ClientConstants.MESSAGE_SPLIT).append(userId).append(ClientConstants.MESSAGE_SPLIT).append(instanceId).append(ClientConstants.MESSAGE_SPLIT).append(regionId)
					.append(ClientConstants.MESSAGE_SPLIT).append(storage).append(ClientConstants.MESSAGE_SPLIT).append(start).append(ClientConstants.MESSAGE_SPLIT).append("STORAGE").append(ClientConstants.MESSAGE_SPLIT).append(" ").append(ClientConstants.MESSAGE_SPLIT).append(bucket);
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
