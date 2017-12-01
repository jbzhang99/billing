package com.ai.runner.center.ctp.rtm.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class NumberUtil {

	public static boolean isNumber(String str){
		if(StringUtils.isBlank(str)){
			return false;
		}
		String regEx = "[0-9]*";
		Pattern pattern = Pattern.compile(regEx);
		Matcher match = pattern.matcher(str);
		return match.matches();
	}
	
	
	public static void main(String[] args) {
		String str = "abc12";
		System.out.println(NumberUtil.isNumber("ab1"));
	}
	
}
