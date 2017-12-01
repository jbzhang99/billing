package com.ai.baas.rtm.generators;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.rtm.constants.RtmConstants;
import com.ai.baas.rtm.initload.McsClient;

public class SnGenerator {
	private static Logger logger =LoggerFactory.getLogger(SnGenerator.class);
	public static String getSn(String[] dataRecords){
		StringBuilder sn= new StringBuilder();
		StringBuilder snKey= new StringBuilder();
		snKey.append(dataRecords[0]).append(RtmConstants.KEY_JOINER).append("SN");
		logger.debug("the snKey is "+snKey);
		String snValue=McsClient.client.get(snKey.toString());
		logger.debug("the snValue is "+snValue);
		String[] snValues=StringUtils.splitPreserveAllTokens(snValue, RtmConstants.KEY_JOINER);
		for(String value:snValues){
			sn.append(dataRecords[Integer.valueOf(value)-1]);
			logger.debug("the value is "+value.toString());
		}
		return sn.toString();
	}
	
}
