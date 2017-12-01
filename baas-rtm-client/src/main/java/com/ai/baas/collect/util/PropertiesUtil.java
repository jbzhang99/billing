package com.ai.baas.collect.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.collect.vo.ServiceParam;

public class PropertiesUtil {


	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	private ConcurrentHashMap<String, String> dataMap = new ConcurrentHashMap<String, String>();
	private static PropertiesUtil instance = new PropertiesUtil();
	
	public static void load(String resourcesName){
		if(StringUtils.isBlank(resourcesName)){
			return;
		}
		loadResources(resourcesName);
	}
	
	private static void loadResources(String fileName){
//		Properties properties = new Properties();
//		InputStream inputStream = null;
//		try{
//			inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
//			properties.load(inputStream);
//		}catch(IOException e){
//			e.printStackTrace();
//		}finally{
//			if(inputStream!=null){
//				try {
//					inputStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		Properties properties = loadProperties(fileName);
		if(properties==null){
			return;
		}
		Iterator<Entry<Object, Object>> itor = properties.entrySet().iterator();
		while (itor.hasNext()) {
			Entry<Object, Object> entry = itor.next();
			String name = (String) entry.getKey();
			String value = (String) entry.getValue();
			instance.dataMap.put(name, value);
		}
		//logger.debug("load resources "+fileName);
	}
	
	public static String getValue(String key) {
		String value = instance.dataMap.get(key);
		return value;
	}

	public static Object remove(String k) {
		Object v = instance.dataMap.remove(k);
		return v;
	}
	
	public static Properties loadProperties(String fileName){
		Properties properties = new Properties();
		InputStream inputStream = null;
		try{
			inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
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
	
	/**
	 * 
	 * 
	 * @Description: 获取某服务的配置参数
	 *
	 * @param: 服务名
	 * @return：参数结构体
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: hanzf
	 * @date: 2017年3月14日 下午5:21:50
	 *
	 * Modification History:
	 * Date           Author          Version            Description
	 *---------------------------------------------------------*
	 * 2017年3月14日       hanzf           v1.0.0               创建
	 */
	public static ServiceParam getServiceParam(String serviceName)
	{
		ServiceParam serviceParam = new ServiceParam();
		serviceParam.setName(serviceName);
		serviceParam.setPort(Integer.parseInt(getValue(serviceName+"." + "PORT")));
		serviceParam.setAddr(getValue(serviceName+"." + "ADDR"));
		serviceParam.setBakPath(getValue(serviceName+"." + "BAK_PATH"));
		serviceParam.setFileName(getValue(serviceName+"." + "FILE_NAME"));
		serviceParam.setLocalPath(getValue(serviceName+"." + "LOCAL_PATH"));
		serviceParam.setPassWord(getValue(serviceName+"." + "PASSWORD"));
		serviceParam.setRemotePath(getValue(serviceName+"." + "REMOTE_PATH"));
		serviceParam.setScanInter(Integer.parseInt(getValue(serviceName+"." + "SCAN_INTER")));
		serviceParam.setSplitNum(Integer.parseInt(getValue(serviceName+"." + "SPLIT_NUM")));
		serviceParam.setTransferProtocol(getValue(serviceName+"." + "TRANSFER_PROTOCOL"));
		serviceParam.setUser(getValue(serviceName+"." + "USER"));
		
		serviceParam.setSplitScanInter(Integer.parseInt(getValue(serviceName+"." + "SPLIT_SCAN_INTER")));
		serviceParam.setDealThreadNum(Integer.parseInt(getValue(serviceName+"." + "DEAL_THREAD_NUM")));
		serviceParam.setDealScanInter(Integer.parseInt(getValue(serviceName+"." + "DEAL_SCAN_INTER")));
		serviceParam.setErrorPath(getValue(serviceName+"." + "ERROR_PATH"));

		serviceParam.setIsExchange(getValue(serviceName+"." + "IS_EXCHANGE"));
		serviceParam.setTenantId(getValue(serviceName+"." + "TENANT_ID"));
		serviceParam.setRtmUser(getValue(serviceName+"." + "RTM_USER"));
		serviceParam.setRtmPassword(getValue(serviceName+"." + "RTM_PASSWORD"));
		serviceParam.setSystemId(getValue(serviceName+"." + "SYSTEM_ID"));
		serviceParam.setSource(getValue(serviceName+"." + "SOURCE"));
		serviceParam.setRtmUrl(getValue(serviceName+"." + "RTM_URL"));
		
		

		return serviceParam;
	}
	

}
