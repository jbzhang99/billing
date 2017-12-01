package com.ai.runner.center.ctp.rtm.core.generator;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


public class SnContainer {
	private static Map<String,String> placeholderMap = new HashMap<String,String>();
	//private static Set<String> timestampSet = new HashSet<String>();
	private static Map<String,ISnGenerator> snGeneratorMap = new HashMap<String,ISnGenerator>();
	private String strPlaceholder;
	private static SnContainer intence=new SnContainer();
	private SnContainer(){}
	public static SnContainer getSnContainer(){
		return intence;
	}
	public  void initContainer(){
		if(StringUtils.isNotBlank(strPlaceholder)){
			String[] placeholderSplit = StringUtils.splitPreserveAllTokens(strPlaceholder,",");
			for(String str:placeholderSplit){
				String[] tmpSplit = StringUtils.splitPreserveAllTokens(str,"$");
				placeholderMap.put(tmpSplit[0], tmpSplit[1]);
			}
		}
//		String strTimestamp = (String)PropertiesUtil.getValue("ctp.rtm.packet.sn.strategy.timestamp");
//		if(StringUtils.isNotBlank(strTimestamp)){
//			String[] timestampSplit = StringUtils.splitPreserveAllTokens(strTimestamp,",");
//			for(String str:timestampSplit){
//				timestampSet.add(str);
//			}
//		}
	}
	
	public  ISnGenerator getGenerator(String service_id){
		ISnGenerator snGenerator = snGeneratorMap.get(service_id);
		if(snGenerator != null){
			return snGenerator;
		}
		if(placeholderMap.containsKey(service_id)){
			snGenerator = new SnPlaceholderGenerator(placeholderMap.get(service_id));
			snGeneratorMap.put(service_id, snGenerator);
		}
		return snGenerator;
	}
	public String getStrPlaceholder() {
		return strPlaceholder;
	}
	public void setStrPlaceholder(String strPlaceholder) {
		this.strPlaceholder = strPlaceholder;
	}
	
	
	
}
