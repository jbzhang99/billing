package com.ai.runner.center.bmc.deduct.util;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * properties 工具类 Date: 2016年4月19日 <br>
 * 
 * @author zhoushanbin 
 * 
 * Copyright (c) 2016 asiainfo.com <br>
 */
public final class PropertiesUtil {

	private static Logger LOGGER = LoggerFactory
			.getLogger(PropertiesUtil.class);
	/**
	 * 信控mds 配置文件
	 */
	private static final String PRODUCE_MDS_AUTH = "produceMdsAuth.properties";

	/**
	 * 属性map
	 */
	private static  Map<String, String> proMap = new ConcurrentHashMap<String, String>();

	static {

		init();
	}

	private static void init() {
		InputStream in = null;
		Properties pPro = null;
		try {

			in = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					PRODUCE_MDS_AUTH);

			pPro = new Properties();

			pPro.load(in);

			Enumeration<?> elements = pPro.propertyNames();
			
			while(elements.hasMoreElements()){
				String key = ((String)elements.nextElement()).trim();
				String value = pPro.getProperty(key).trim();
				proMap.put(key, value);
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("load mds configure error", e);
			
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
	/**
	 * 获取配置map
	 * @return
	 */
	public static Map<String, String> getConfMap() {
		if(null == proMap){
			proMap = new ConcurrentHashMap<String, String>();
			init();
		}
		return proMap;
	}
	 /**
	  * 获取key -> value
	  * @param key
	  * @return
	  */
	public static String getProValue(String key){
		if(null == proMap){
			proMap = new ConcurrentHashMap<String, String>();
			init();
		}
		return proMap.get(key);
	}
	

}
