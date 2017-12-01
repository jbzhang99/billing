package com.ai.runner.center.ctp.rtm.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class GenerateAPNUtil {
	private static Logger logger = LoggerFactory.getLogger(GenerateAPNUtil.class);
	
	private static Map<String, String> baseMap_apn = new ConcurrentHashMap<String, String>();

	public static void load(String resourcesName){
		if(StringUtils.isBlank(resourcesName)){
			return;
		}
		loadResources(resourcesName);
	}
	
	private static void loadResources(String fileName){

		Properties properties = loadProperties(fileName);
		if(properties==null){
			return;
		}
		Iterator<Entry<Object, Object>> itor = properties.entrySet().iterator();
		while (itor.hasNext()) {
			Entry<Object, Object> entry = itor.next();
			String name = (String) entry.getKey();
			String value = (String) entry.getValue();
			GenerateAPNUtil.baseMap_apn.put(name, value);
		}
	}
	
	public static String getValue(String key) {
		String value = GenerateAPNUtil.baseMap_apn.get(key);
		return value;
	}

	public static Object remove(String k) {
		Object v = GenerateAPNUtil.baseMap_apn.remove(k);
		return v;
	}
	
	public static Properties loadProperties(String fileName){
		Properties properties = new Properties();
		InputStream inputStream = null;
		try{
			inputStream = GenerateAPNUtil.class.getClassLoader().getResourceAsStream(fileName);
			properties.load(inputStream);
		}catch(IOException e){
			logger.error("context", e);
			properties = null;
		}finally{
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("context", e);
				}
			}
		}
		return properties;
	}
	
	
}
