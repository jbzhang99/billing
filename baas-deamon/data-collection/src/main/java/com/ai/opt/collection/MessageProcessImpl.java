package com.ai.opt.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.rtm.api.datacollect.params.DataVO;
import com.ai.opt.collection.model.TRUserInfo;
import com.ai.opt.collection.util.TradeSeqUtil;
import com.ai.opt.sdk.dubbo.extension.DubboRestResponse;
import com.ai.opt.sdk.dubbo.mapper.RestResponse;
import com.ai.opt.sdk.dubbo.util.HttpClientUtil;
import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.vo.MessageAndMetadata;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;


public class MessageProcessImpl implements IMessageProcessor {
	private static Logger logger = LoggerFactory.getLogger(MessageProcessImpl.class);
	public final static String HEAD_SPLIT = new String(new char[] { (char) 3 }); // 消息各个字段分割
	public final static String MESS_SPLIT = new String(new char[] { (char) 1 }); // 消息头的分割
	public final static String RECORD_SPLIT = new String(new char[] { (char) 2 }); // 消息和消息头的分割

	private TRUserInfo info;
	public MessageProcessImpl(TRUserInfo info){
		this.info=info;
	}
	
	
	@Override
	public void process(MessageAndMetadata message) throws Exception {
		logger.error("--------------------->开始处理消息");

		if (null == message)
			return;
		String content = new String(message.getMessage(), "UTF-8");
		
		String[] messages = content.split(MESS_SPLIT);
		logger.error(content);
		// 方案1，一个消息一个消息的发送
		/*for (int i = 0; i < messages.length; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append(info.getTenantId()).append(HEAD_SPLIT).append(info.getServiceType()).append(HEAD_SPLIT).append(TradeSeqUtil.newTradeSeq(info.getTenantId()))
					.append(HEAD_SPLIT).append(info.getUserName()).append(HEAD_SPLIT).append(info.getPassword());
			sb.append(RECORD_SPLIT).append(info.getResource()).append(MESS_SPLIT).append(messages[i]);
			DataVO vo = new DataVO();
			vo.setTransData(sb.toString());
			System.out.println("发送的消息格式为" + sb.toString());
			String url = "http://10.1.130.84:10771/baasrtm/dataService/transResource"; // rest地址
			String result = HttpClientUtil.sendPost(url, JSON.toJSONString(vo));
			System.out.println("---------------结果为---------------》" + result);
		}*/
		// 方案2：一次性发送

		if (messages.length == 1) {
			StringBuilder sb = new StringBuilder();
			sb.append(info.getTenantId()).append(HEAD_SPLIT).append(info.getServiceType()).append(HEAD_SPLIT).append(TradeSeqUtil.newTradeSeq(info.getTenantId()))
					.append(HEAD_SPLIT).append(info.getUserName()).append(HEAD_SPLIT).append(info.getPassword());
			sb.append(RECORD_SPLIT).append(info.getResource()).append(MESS_SPLIT).append(messages[0]);
			DataVO vo = new DataVO();
			vo.setTransData(sb.toString());
			String url = "http://10.1.130.84:10771/baasrtm/dataService/transResource"; // rest地址
			String result = HttpClientUtil.sendPost(url, JSON.toJSONString(vo));
			logger.error("调用rtm的结果--->"+result);
		//	
			Gson gson = new Gson();
			DubboRestResponse response=gson.fromJson(result, DubboRestResponse.class);
			if(!"000000".equals(response.getResultCode())){
				logger.error("消息发送失败：{} {}",response.getResultCode(),response.getResultMessage());
				
			}
		}
		if(messages.length>1){
			StringBuilder sb1 = new StringBuilder();
			for (int i = 0; i < messages.length; i++) {
				//消息头
				sb1.append(info.getTenantId()).append(HEAD_SPLIT).append(info.getServiceType()).append(HEAD_SPLIT).append(TradeSeqUtil.newTradeSeq(info.getTenantId()))
				.append(HEAD_SPLIT).append(info.getUserName()).append(HEAD_SPLIT).append(info.getPassword());
				sb1.append(RECORD_SPLIT).append(info.getResource()).append(MESS_SPLIT).append(messages[0]);
				if(i!=messages.length-1){
					sb1.append(RECORD_SPLIT);	
				}
			}
			DataVO vo = new DataVO();
			vo.setTransData(sb1.toString());
			String url = "http://10.1.130.84:10771/baasrtm/dataService/transResource"; // rest地址
			String result = HttpClientUtil.sendPost(url, JSON.toJSONString(vo));
			logger.error("调用rtm的结果--->"+result);
			Gson gson = new Gson();
			DubboRestResponse response=gson.fromJson(result, DubboRestResponse.class);
			if(!"000000".equals(response.getResultCode())){
				logger.error("消息发送失败：{} {}",response.getResultCode(),response.getResultMessage());
				
			}
		}
		

		/*
		 * DataVO vo=new DataVO(); vo.setTransData(""); String
		 * url="http://10.1.130.84:10771/baasrtm/dataService/transResource";
		 * //rest地址 String result = HttpClientUtil.sendPost(url, null);//调用接口
		 */
		logger.error("--------------------->消息处理结束");

	}

}
