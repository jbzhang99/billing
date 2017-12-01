package com.ai.baas.bmc.topology.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.storm.exception.BusinessException;

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
	public static boolean isRange(String star,String end,String now) throws BusinessException{
		boolean isSucc = false;
		if(StringUtils.isBlank(end)||StringUtils.isBlank(star)){
			return isSucc;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		try{
	        Date sdate = format.parse(star);
	        Date edate = format.parse(end);
	        Date ndate = format.parse(now);
	        //System.out.println(ndate+"##"+sdate.getTime()+"##"+edate.getTime());
	        if(ndate.after(sdate)&& ndate.before(edate)){
	        	isSucc = true;
	        }
	    }catch(Exception e){
	    	logger.error("error", e);
	    	throw new BusinessException("BMC-B0002","生效时间或失效时间不正确!");
	    }
		return isSucc;
	}
	
	
}
