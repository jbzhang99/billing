package com.ai.baas.batch.client.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.batch.client.constants.ClientConstants;
import com.ai.baas.batch.client.service.ICalProcessor;
import com.ai.baas.batch.client.util.HttpClientUtil;
import com.ai.baas.batch.client.util.MsgUtil;
import com.ai.baas.batch.client.util.MyJsonUtil;
import com.ai.baas.batch.client.util.UserIdUtil;
import com.ai.baas.batch.client.util.getUrl;
import com.ai.baas.batch.client.util.lastDayUtil;
import com.alibaba.fastjson.JSONObject;

public class DrdsCalProcessorImpl implements ICalProcessor{

	private static final Logger LOG = LoggerFactory
			.getLogger(DrdsCalProcessorImpl.class);

	@Override
	public void buildCalAdapt(String response, String serviceId)
			throws Exception {
		String drds = getUrl.getInstance().getDrds();
		if (!("".equals(drds))) {
			response = drds;
			LOG.debug("the Drds  " + drds);
		}
		JSONObject[] jsonarray = MyJsonUtil.analyJson(response);
		String rtmUrl = getUrl.getInstance().getRtm();
		System.out.println("the jsonarray " + jsonarray.toString());
		for (int i = 0; i < jsonarray.length; i++) {
			LOG.debug(i + ":" + jsonarray[i].toString());
			String instanceId = jsonarray[i]
					.getString(ClientConstants.INSTANCE_ID);
			LOG.debug("the instance id is " + instanceId);
			List<Map<String, String>> results = UserIdUtil
					.getUserId(instanceId);
			String userId = null;
			if (results == null) {
				System.out.println("drds instance id " + instanceId
						+ "  not get user_id");
			} else {
				for (Map<String, String> data : results) {
					for (Entry<String, String> entry : data.entrySet()) {
						String key = entry.getKey();
						if ("user_id".equals(key))
							userId = entry.getValue();
					}
				}

				String endTime = jsonarray[i]
						.getString(ClientConstants.END_TIME);
				String startTime = jsonarray[i]
						.getString(ClientConstants.START_TIME);
				String regionId = jsonarray[i]
						.getString(ClientConstants.REGION_ID);
				String lastTime = lastDayUtil.difHour(startTime, endTime);
				String start = lastDayUtil.norTime(startTime);
				// 开始进行消息头的拼接
				StringBuilder timeString = new StringBuilder();
				StringBuilder streamString = new StringBuilder();
				timeString.append(
						lastDayUtil.messageHead(userId,
								ClientConstants.SOURCE_TIME,
								ClientConstants.ADRDS_SYSTEM)).append(
						ClientConstants.HEAD_SPLIT);
				StringBuilder timeMessage = new StringBuilder();
				timeMessage.append(ClientConstants.SOURCE_DRDS)
						.append(ClientConstants.MESSAGE_SPLIT).append(userId)
						.append(ClientConstants.MESSAGE_SPLIT)
						.append(instanceId)
						.append(ClientConstants.MESSAGE_SPLIT).append(regionId)
						.append(ClientConstants.MESSAGE_SPLIT).append(lastTime)
						.append(ClientConstants.MESSAGE_SPLIT).append(start)
						.append(ClientConstants.MESSAGE_SPLIT).append("TIME");
				timeString.append(timeMessage.toString());
				// 开始进行时间消息的发送
				System.out.println("the timeString " + timeString.toString());
				StringBuilder sentTime = new StringBuilder();
				sentTime.append(ClientConstants.RTMHEAD)
						.append(timeString.toString())
						.append(ClientConstants.RTMEND);
				//HttpClientUtil.send(rtmUrl, sentTime.toString());
				MsgUtil.putrtmMsg(sentTime.toString());

				System.out.println("the senttile is " + sentTime.toString());
//				String netOut = jsonarray[i].getString(ClientConstants.NET_OUT);
//				// String netIn=jsonarray[i].getString(clientConstants.NET_IN);
//				if ("0".equals(netOut))
//					continue;
//				else {
//					streamString.append(
//							lastDayUtil.messageHead(userId,
//									ClientConstants.SOURCE_STREAM,
//									ClientConstants.AEIP_SYSTEM)).append(
//							ClientConstants.HEAD_SPLIT);
//					StringBuilder streamMessage = new StringBuilder();
//					streamMessage.append(ClientConstants.SOURCE_EIP)
//							.append(ClientConstants.MESSAGE_SPLIT)
//							.append(userId)
//							.append(ClientConstants.MESSAGE_SPLIT)
//							.append(instanceId)
//							.append(ClientConstants.MESSAGE_SPLIT)
//							.append(regionId)
//							.append(ClientConstants.MESSAGE_SPLIT)
//							.append(netOut)
//							.append(ClientConstants.MESSAGE_SPLIT)
//							.append(start)
//							.append(ClientConstants.MESSAGE_SPLIT)
//							.append("STREAM");
//					streamString.append(streamMessage.toString());
//					System.out.println("the stream is "
//							+ streamString.toString());
//					StringBuilder sentStream = new StringBuilder();
//					sentStream.append(ClientConstants.RTMHEAD)
//							.append(streamString.toString())
//							.append(ClientConstants.RTMEND);
//					HttpClientUtil.send(rtmUrl, sentStream.toString());
//					System.out.println("the senttile stream is "
//							+ sentStream.toString());
//					// HttpClientUtil.send(rtmUrl,
//					// "{\"transData\":\"1322131\"}");
//				}

				/*
				 * String
				 * endTime=jsonarray[i].getString(ClientConstants.END_TIME);
				 * String
				 * startTime=jsonarray[i].getString(ClientConstants.START_TIME);
				 * String
				 * regionId=jsonarray[i].getString(ClientConstants.REGION_ID);
				 * String lastTime=lastDayUtil.difHour(startTime, endTime);
				 * String start=lastDayUtil.norTime(startTime); String
				 * networkOut = jsonarray[i].getString(ClientConstants.NET_OUT);
				 * //开始进行消息头的拼接 StringBuilder timeString=new StringBuilder();
				 * timeString.append(lastDayUtil.messageHead(userId,
				 * ClientConstants
				 * .SOURCE_TIME,ClientConstants.AEIP_SYSTEM)).append
				 * (ClientConstants.HEAD_SPLIT); StringBuilder timeMessage= new
				 * StringBuilder();
				 * timeMessage.append(ClientConstants.SOURCE_EIP
				 * ).append(ClientConstants
				 * .MESSAGE_SPLIT).append(userId).append(
				 * ClientConstants.MESSAGE_SPLIT).append(instanceId)
				 * .append(ClientConstants
				 * .MESSAGE_SPLIT).append(regionId).append
				 * (ClientConstants.MESSAGE_SPLIT
				 * ).append(networkOut).append(ClientConstants
				 * .MESSAGE_SPLIT).append
				 * (start).append(ClientConstants.MESSAGE_SPLIT
				 * ).append("STREAM");
				 * timeString.append(timeMessage.toString()); //开始进行时间消息的发送
				 * StringBuilder sentTime=new StringBuilder();
				 * sentTime.append(ClientConstants
				 * .RTMHEAD).append(timeString.toString
				 * ()).append(ClientConstants.RTMEND); LOG.debug("the rtmmsg:" +
				 * sentTime); HttpClientUtil.send(rtmUrl, sentTime.toString());
				 */
			}
		}
	}


}
