package com.ai.baas.rtm.generators;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.rtm.initload.McsClient;
import com.ai.baas.rtm.utils.PropertiesUtil;


public class BsnGenerator {
	//将bsn保存到缓存中
	private static Logger logger=LoggerFactory.getLogger(BsnGenerator.class);
	private  static String bsn;
	public static String[] getBsn(){
		Date dt=new Date();
		Long time=dt.getTime();
		String[] bsns= new String[2];
		String lastBsn1=McsClient.client.get("BSN-BAAS");
		try{
			if(lastBsn1==null){
				McsClient.client.set("BSN-BAAS",String.valueOf(time));
				bsns[0]=String.valueOf(String.valueOf(time)) ;
				bsns[1]=String.valueOf(String.valueOf(time)) ;
				return bsns;
			}else{
				long lastBsn=Long.valueOf(lastBsn1);
				String lastTime=(String) PropertiesUtil.getValue("rtm.bsn.last.time");
				
				if(time-lastBsn>=Long.valueOf(lastTime)){
					bsns[0]=String.valueOf(lastBsn);
					logger.debug("the lastBsn is...... "+lastBsn);
					bsns[1]=String.valueOf(time);
					logger.debug("the time is .......  "+time);
					McsClient.client.set("BSN-BAAS",String.valueOf(time));
					return bsns;
				}
				else {
					bsns[0]=String.valueOf(lastBsn);
					bsns[1]=String.valueOf(lastBsn);
					return bsns;
				}
			}
		}catch(Exception e){
			logger.error("get bsn is error  "+e.getMessage());
			return null;
		}
	}
	
}
