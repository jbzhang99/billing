package com.ai.citic.billing.web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数字验证工具类
 * @author wangluyang
 *
 */
public class NumberValidationUtils {

	private static boolean isMatch(String regex, String orginal){  
        if (orginal == null || orginal.trim().equals("")) {  
            return false;  
        }  
        Pattern pattern = Pattern.compile(regex);  
        Matcher isNum = pattern.matcher(orginal);  
        return isNum.matches();  
    }  
  
	/**
	 * 是否正整数
	 * @param orginal
	 * @return
	 */
    public static boolean isPositiveInteger(String orginal) {  
        return isMatch("^\\+{0,1}[1-9]\\d*", orginal);  
    }  
    
    /**
     * 是否负整数
     * @param orginal
     * @return
     */
    public static boolean isNegativeInteger(String orginal) {  
        return isMatch("^-[1-9]\\d*", orginal);  
    }  
  
    /**
     * 整数
     * @param orginal
     * @return
     */
    public static boolean isWholeNumber(String orginal) {  
        return isMatch("[+-]{0,1}0", orginal) || isPositiveInteger(orginal) || isNegativeInteger(orginal);  
    }  
    
    /**
     * 正小数
     * @param orginal
     * @return
     */
    public static boolean isPositiveDecimal(String orginal){  
        return isMatch("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*", orginal);  
    }  
    
    /**
     * 负小数
     * @param orginal
     * @return
     */
    public static boolean isNegativeDecimal(String orginal){  
        return isMatch("^-[0]\\.[1-9]*|^-[1-9]\\d*\\.\\d*", orginal);  
    }  
    
    /**
     * 小数
     * @param orginal
     * @return
     */
    public static boolean isDecimal(String orginal){  
        return isMatch("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+", orginal);  
    }  
    
    /**
     * 实数
     * @param orginal
     * @return
     */
    public static boolean isRealNumber(String orginal){  
        return isWholeNumber(orginal) || isDecimal(orginal);  
    }  
    
    /**
	 * 采用正则表达式的方式来判断一个字符串是否为数字，这种方式判断面比较全  
     *  可以判断正负、整数小数  
     * ?:0或1个, *:0或多个, +:1或多个
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		return str.matches("-?[0-9]+.?[0-9]*");  
	}
}
