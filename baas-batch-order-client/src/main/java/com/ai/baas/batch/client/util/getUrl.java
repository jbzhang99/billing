package com.ai.baas.batch.client.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.batch.client.constants.ClientConstants;
import com.ai.opt.sdk.exception.SDKException;
import com.alibaba.fastjson.JSON;


public class getUrl {
	private static final Logger LOG = LoggerFactory.getLogger(getUrl.class);
	private static Properties prop=null;
	private static getUrl instance=null;
	private getUrl(){
		//禁止初始化
	}
	public static getUrl getInstance(){
		if(null==instance||null==prop){
			synchronized (getUrl.class) {
				if(null==instance){
					instance=new getUrl();
					instance.urlAdr();
				}
			}
		}
		return instance;
		
	}
	
	private void urlAdr(){
	InputStream io=getUrl.class.getClassLoader().getResourceAsStream(ClientConstants.URL_CONFIG_FILE);
	 LOG.debug("【加载的属性文件流】:"+JSON.toJSONString(io));
	try{
		prop=new Properties();
		prop.load(io);
		LOG.debug("【加载的属性文件prop】:"+JSON.toJSONString(prop));
		//return prop.getProperty("url.address");
	}catch (IOException e) {
        throw new SDKException("loding url config file failed", e);
    }
	
	}
	public String getDetailZX(){
		return prop.getProperty("detail.address");
	}
	public String getRtm(){
		return prop.getProperty("rtm.address");
	}
	public String getSupplyUrl(){
		return prop.getProperty("supply.address");
	}
	public String getAdaptUrl(){
		return prop.getProperty("adapt.address");
	}
	public String getStartTime(){
		if(prop.containsKey("start.time"))
			return prop.getProperty("start.time");
		else 
			return "";

	}
	public String getEndTime(){
		return prop.getProperty("end.time");
	}
	public String getECS(){
		if(prop.containsKey("ecs"))
			return prop.getProperty("ecs");
		else 
			return "";
	}
	public String getEIP(){
		if(prop.containsKey("eip"))
			return prop.getProperty("eip");
		else 
			return "";
	}
	public String getYUP(){
		if(prop.containsKey("yup"))
			return prop.getProperty("yup");
		else 
			return "";
	}
	
	public String getDrds(){
		if(prop.containsKey("drds"))
			return prop.getProperty("drds");
		else 
			return "";
	}	
	public String getKVS(){
		if(prop.containsKey("kvs"))
			return prop.getProperty("kvs");
		else 
			return "";
	}
	public String getRDS(){
		if(prop.containsKey("rds"))
			return prop.getProperty("rds");
		else 
			return "";
	}
	public String getOSS(){
		if(prop.containsKey("oss"))
			return prop.getProperty("oss");
		else 
			return "";
	}
	public String getServiceId(){
		if(prop.containsKey("service.id"))
			return prop.getProperty("service.id");
		else 
			return "";
	}
	public String getSlb(){
		if(prop.containsKey("slb"))
			return prop.getProperty("slb");
		else 
			return "";
	}
}
