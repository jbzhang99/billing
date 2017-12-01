package com.ai.runner.center.ctp.rtm.core.generator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;


public class PsnContainer {

	private static Map<String,String> placeholderMap = new HashMap<String,String>();
	private static Set<String> timestampSet = new HashSet<String>();
	private static Map<String,IPsnGenerator> psnGeneratorMap = new HashMap<String,IPsnGenerator>();
	private String strPlaceholder;
	private String strTimestamp;
	public  void initContainer(){
			if(StringUtils.isNotBlank(strPlaceholder)){
			String[] placeholderSplit = StringUtils.splitPreserveAllTokens(strPlaceholder,",");
			for(String str:placeholderSplit){
				String[] tmpSplit = StringUtils.splitPreserveAllTokens(str,"$");
				placeholderMap.put(tmpSplit[0], tmpSplit[1]);
			}
		}
			if(StringUtils.isNotBlank(strTimestamp)){
			String[] timestampSplit = StringUtils.splitPreserveAllTokens(strTimestamp,",");
			for(String str:timestampSplit){
				timestampSet.add(str);
			}
		}
	}
	//private PsnTimestampGenerator  psnTimestampGenerator;
	public  IPsnGenerator getGenerator(String service_id){
		IPsnGenerator psnGenerator = psnGeneratorMap.get(service_id);
		if(psnGenerator != null){
			return psnGenerator;
		}
		if(placeholderMap.containsKey(service_id)){
			psnGenerator = new PsnPlaceholderGenerator(placeholderMap.get(service_id));
			psnGeneratorMap.put(service_id, psnGenerator);
			return psnGenerator;
		}
		if(timestampSet.contains(service_id)){
			psnGenerator = new PsnTimestampGenerator(service_id);
			psnGeneratorMap.put(service_id, psnGenerator);
			return psnGenerator;
		}
		return psnGenerator;
	}


	public String getStrPlaceholder() {
		return strPlaceholder;
	}


	public void setStrPlaceholder(String strPlaceholder) {
		this.strPlaceholder = strPlaceholder;
	}


	public String getStrTimestamp() {
		return strTimestamp;
	}


	public void setStrTimestamp(String strTimestamp) {
		this.strTimestamp = strTimestamp;
	}
	
	
}
