package com.ai.runner.center.ctp.rtm.core.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

public class ScanPathContainer {

	private static Map<String,String> scanPathMap = new HashMap<String,String>();
	
	public static void loadPath(String path){
		String[] pathNames = StringUtils.splitPreserveAllTokens(path,",");
		for(String pathName:pathNames){
			if(StringUtils.contains(pathName, "$")){
				String[] tempSplit = StringUtils.splitPreserveAllTokens(pathName,"$");
				scanPathMap.put(tempSplit[0], tempSplit[1]);
			}else{
				int lastSeparate = FilenameUtils.indexOfLastSeparator(pathName);
				String service_id = pathName.substring(lastSeparate+1);
				scanPathMap.put(service_id, pathName);
			}
		}
	}

	public static Map<String, String> getScanPathContainer() {
		return scanPathMap;
	}
	
	public static String getScanPathByKey(String key){
		return scanPathMap.get(key);
	}
	
}
