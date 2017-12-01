package com.ai.runner.center.ctp.rtm.core.processor;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.runner.center.ctp.rtm.core.executor.DeliverHandler;
import com.ai.runner.center.ctp.rtm.core.executor.LoopThread;
import com.ai.runner.center.ctp.rtm.core.util.RtmConstants;
import com.ai.runner.center.ctp.rtm.core.util.StringLine;

public class DubboProcessor extends LoopThread {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(DubboProcessor.class);
	private String message;
	@SuppressWarnings("unused")
	private String delimiter;
	private String service_id;
	private String inputDatas;
	
	public DubboProcessor(String message){
		this.message = message;
	}
	
	@Override
	public boolean init() {
		//delimiter = (String)PropertiesUtil.getValue("ctp.rtm.file-reader.data.delimiter");
		parserMessage();
		return true;
	}

	@Override
	public boolean unInit() {
		return true;
	}

	@Override
	public void work() {
		pushDataLineToQueue();
		exitFlag = true;
	}
	
	private void parserMessage(){
		String[] msgSplit = StringUtils.splitPreserveAllTokens(message,"$");
		//0:service_id 1:输入数据集合
		service_id = msgSplit[0];
		inputDatas = msgSplit[1];
	}
	
	private void pushDataLineToQueue(){
		String[] strDatas = StringUtils.splitPreserveAllTokens(inputDatas, ",");
		StringLine stringLine = null;
		for(String strData:strDatas){
			//System.out.println("[DubboProcessor]-->>>"+strData);
			stringLine = new StringLine(strData, RtmConstants.FIELD_SPLIT);
			DeliverHandler.deliverQueue.put(service_id, stringLine);
		}
		
		//优化可以装包处理
		
	}

}
