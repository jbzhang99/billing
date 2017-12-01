package com.ai.runner.center.ctp.rtm.core.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.ai.runner.center.ctp.rtm.core.util.RtmConstants;

public class PsnPlaceholderGenerator implements IPsnGenerator{

	private String rule;   //{1}-{2}-{3}
	@SuppressWarnings("unused")
	private String model=RtmConstants.PLACEHOLDER_GENERATOR_MODEL_CN;
	private String[] varName; //[1,2,3]
	private String[] varStr;  //[{1},{2},{3}]
	
	public PsnPlaceholderGenerator(String rule){
		this.rule = rule;
		init();
	}
	
	private void init(){
		//rule = (String)PropertiesUtil.getValue("ctp.rtm.packet.psn.placeholder.rule");
		varName = StringUtils.substringsBetween(rule, "{", "}");
		List<String> varTmp = new ArrayList<String>();
		for(String vname:varName){
			varTmp.add("{"+vname+"}");
		}
		varStr = varTmp.toArray(new String[varName.length]);
		if(isNumber(varName[0])){
			model=RtmConstants.PLACEHOLDER_GENERATOR_MODEL_CN;
		}else{
			model=RtmConstants.PLACEHOLDER_GENERATOR_MODEL_KEY;
		}
	}
	
	private static boolean isNumber(String str){
		String regEx = "[0-9]*";
		Pattern pattern = Pattern.compile(regEx);
		Matcher match = pattern.matcher(str);
		return match.matches();
	}
	
	
	@Override
	public String getNext(Object data){
		String[] srcData = (String[])data;
		String[] replaceData = null;
		List<String> tmpList = new ArrayList<String>();
		int order;
		for(String varStr:varName){
			order = Integer.parseInt(varStr);
			tmpList.add(srcData[order-1]);			
		}
		replaceData = tmpList.toArray(new String[tmpList.size()]);
		return StringUtils.replaceEach(rule, varStr, replaceData);
	}
	
	
}
