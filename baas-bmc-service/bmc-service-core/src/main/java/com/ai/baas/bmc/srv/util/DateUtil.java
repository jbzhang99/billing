package com.ai.baas.bmc.srv.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.srv.failbill.BusinessException;


public class DateUtil {
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	
	/**
	 * 判断时间是否在区间范围内
	 * @param star
	 * @param end
	 * @param now
	 * @return true:在范围内
	 *         false:不在范围内
	 */
//	public static boolean isRange(String star,String end,String now) throws BusinessException{
//		boolean isSucc = false;
//		if(StringUtils.isBlank(end)||StringUtils.isBlank(star)){
//			return isSucc;
//		}
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//		try{
//	        Date sdate = format.parse(filterUnNumber(star));
//	        Date edate = format.parse(filterUnNumber(end));
//	        Date ndate = format.parse(filterUnNumber(now));
//	        //System.out.println(ndate+"##"+sdate.getTime()+"##"+edate.getTime());
//	        if(ndate.after(sdate)&& ndate.before(edate)){
//	        	isSucc = true;
//	        }
//	    }catch(Exception e){
//	    	logger.error("error", e);
//	    	throw new BusinessException("BMC-B0008","生效时间或失效时间不正确!");
//	    }
//		return isSucc;
//	}
	
	public static boolean isRange(String star,String end,String now) throws BusinessException{
		boolean isSucc = false;
		if(StringUtils.isBlank(end)||StringUtils.isBlank(star)){
			return isSucc;
		}
		try{
	        long sdate = Long.parseLong(verifyTextTime(filterUnNumber(star)));
	        long edate = Long.parseLong(verifyTextTime(filterUnNumber(end)));
	        long ndate = Long.parseLong(now);
	        //System.out.println(ndate+"##"+sdate.getTime()+"##"+edate.getTime());
	        //logger.info("--->>> star="+sdate+",end="+edate+",start_time="+ndate);
	        if (ndate >= sdate && ndate <= edate) {
	        	isSucc = true;
	        }
	    }catch(Exception e){
	    	logger.error("error", e);
	    	throw new BusinessException("BMC-B0008","生效时间或失效时间不正确!");
	    }
		return isSucc;
	}
	
	public static String filterUnNumber(String str){
		if (str == null) {
			return "";
		}
		String regEx = "[^0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
	
	public static String verifyTextTime(String time) throws BusinessException{
		int len = StringUtils.length(time);
		if(len == 14){
			return time;
		}else if(len > 14){
			return StringUtils.substring(time, 0, 14);
		}else{
			throw new BusinessException("BMC-B0088","生效时间或失效时间格式不正确,不是yyyyMMddHHmmss样式!");
		}
	}
	
}
