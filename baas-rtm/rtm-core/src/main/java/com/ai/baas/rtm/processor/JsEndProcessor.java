package com.ai.baas.rtm.processor;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.rtm.executor.LoopThread;
import com.ai.baas.rtm.generators.UpdateBatch;
import com.ai.baas.rtm.initload.McsClient;

public class JsEndProcessor extends LoopThread{

	private static Logger logger=LoggerFactory.getLogger(RestProcessor.class);
	private String JsBsn;
//	public JsEndProcessor() {
//		this.JsBsn=JsBsn;
//	}
	@Override
	public boolean init() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean unInit() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void work() {
		try{
			Thread.sleep(1800000);
		}catch(Exception e){
			e.printStackTrace();
		}
		JsBsn=McsClient.client.get("JSBSN");
		String acct=new SimpleDateFormat("yyyyMM").format(new Date());
		UpdateBatch update=new UpdateBatch();
		update.updateSql(acct, JsBsn);
		exitFlag=true;
		
	}

}
