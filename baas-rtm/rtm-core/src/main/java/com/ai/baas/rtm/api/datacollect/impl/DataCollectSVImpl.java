package com.ai.baas.rtm.api.datacollect.impl;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.rtm.api.datacollect.interfaces.IdataCollectSV;
import com.ai.baas.rtm.api.datacollect.params.DataVO;
import com.ai.baas.rtm.api.datacollect.params.JsDataVO;
import com.ai.baas.rtm.api.datacollect.params.RtmResponse;
import com.ai.baas.rtm.constants.RtmConstants;
import com.ai.baas.rtm.executor.ProcessHandler;
import com.ai.baas.rtm.generators.UpdateBatch;
import com.ai.baas.rtm.initload.McsClient;
import com.ai.baas.rtm.initload.ThreadPoolInit;
import com.ai.baas.rtm.processor.JsEndProcessor;
import com.ai.baas.rtm.processor.RestProcessor;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class DataCollectSVImpl implements IdataCollectSV{

	Logger logger=LoggerFactory.getLogger(DataCollectSVImpl.class);
	public RtmResponse transResource(DataVO obj) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		String message=obj.getTransData();
		RtmResponse res=new RtmResponse();
		try{
			logger.debug("the message put into the taskqueue.......");
			ProcessHandler.taskQueue.put(message);
			logger.debug("the size of the message is "+ProcessHandler.taskQueue.size());
			res.setResponseMessage("000000");
			return res;
		}catch(Exception e){
			res.setResponseMessage("999999");
			e.printStackTrace();
			return res;
		}
		
	}
	
	public static void main(String[] args){
		DataVO vo=new DataVO();
		String message = "BAASGPRS12345weTIANRUN1231313223redwwSXCM_DATA17090181836123201604081111112345624567123410.1.234.12SXCM_DATA17090181837123201604081111112345624567123410.1.234.12";
		vo.setTransData(message);
		System.out.println("the message is :"+com.alibaba.fastjson.JSON.toJSONString(vo));
	}

	public RtmResponse JsResource(JsDataVO obj) throws BusinessException, SystemException {
		StringBuilder batchMessage=new StringBuilder();
		String message=obj.getMessage();
		String JsBsn=obj.getjSBsn();
		RtmResponse res=new RtmResponse();
		batchMessage.append(JsBsn).append(RtmConstants.JS_SPLIT).append(message);
//		if("JS0000".equals(JsBsn)){
////			String bsn=McsClient.client.get("JSBSN");
////			ThreadPoolInit.execute(new JsEndProcessor(bsn));
//			ProcessHandler.taskQueue.put(batchMessage.toString());
//			res.setResponseMessage("000000");
//			return res;
//		}else{
//			McsClient.client.set("JSBSN", JsBsn);
//			logger.debug("the jbsn is "+JsBsn);
			
			try{
				logger.debug("the message put into the taskqueue......."+batchMessage.toString());
				ProcessHandler.taskQueue.put(batchMessage.toString());
				logger.debug("the message has input into the queue......");
				res.setResponseMessage("000000");
				return res;
			}catch(Exception e){
				res.setResponseMessage("999999");
				e.printStackTrace();
				return res;
			}
		//}
	}
	public RtmResponse demoResource(DataVO obj) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		String message=obj.getTransData();
		RtmResponse res=new RtmResponse();
		String []splitMessage=StringUtils.splitPreserveAllTokens(message, RtmConstants.INFO_SPLIT);
		StringBuilder mcsKey=new StringBuilder();
		mcsKey.append(splitMessage[0].toUpperCase()).append(RtmConstants.KEY_JOINER).append(RtmConstants.DEMO_PARAM);
		String demoTotal=McsClient.client.get(mcsKey.toString());   //配置表中的总数
		String acct=new SimpleDateFormat("yyyyMM").format(new Date());
		StringBuilder mcsAcct=new StringBuilder();
		mcsAcct.append(splitMessage[0].toUpperCase()).append(RtmConstants.KEY_JOINER).append(RtmConstants.DEMO_NUM);
		logger.debug("the mcsACCT is "+mcsAcct.toString());
		String demoNum=McsClient.client.hget(mcsAcct.toString(), acct);
		logger.debug("the demoNUm is "+demoNum);
		if(demoNum==null){
			McsClient.client.hset(mcsAcct.toString(), acct,"0");
			try{
				logger.debug("the message put into the taskqueue.......");
				ProcessHandler.taskQueue.put(message);
				logger.debug("the size of the message is "+ProcessHandler.taskQueue.size());
				res.setResponseMessage("000000");
				return res;
			}catch(Exception e){
				res.setResponseMessage("999999");
				e.printStackTrace();
				return res;
			}
		}
		else{
			if(demoTotal==null||Integer.parseInt(demoTotal)>Integer.parseInt(demoNum)){
				try{
					logger.debug("the message put into the taskqueue.......");
					ProcessHandler.taskQueue.put(message);
					logger.debug("the size of the message is "+ProcessHandler.taskQueue.size());
					res.setResponseMessage("000000");
					return res;
				}catch(Exception e){
					res.setResponseMessage("999999");
					e.printStackTrace();
					return res;
				}
			}else {
				res.setResponseMessage("999999");
				return res;
			}
		}
			
	}
}
