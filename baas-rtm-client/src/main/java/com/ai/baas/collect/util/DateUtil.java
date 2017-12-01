package com.ai.baas.collect.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DateUtil {
	 private static final Log logger = LogFactory.getLog(FileUtil.class);
	public String getLast2Day(){
		Date dnow=new Date();
		Date dbefore=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(dnow);
		calendar.add(Calendar.DAY_OF_MONTH, -2);
		dbefore=calendar.getTime();
		SimpleDateFormat dfor=new SimpleDateFormat("yyyyMMdd");
		return dfor.format(dbefore);
	}
	public String getLastDay(){
		Date dnow=new Date();
		Date dbefore=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(dnow);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		dbefore=calendar.getTime();
		SimpleDateFormat dfor=new SimpleDateFormat("yyyyMMdd");
		return dfor.format(dbefore);
	}
	
	public static String difHour(String startTime,String endTime){
		SimpleDateFormat ds= new SimpleDateFormat("yyyyMMddHHmmss");
		try{
			Date start=ds.parse(startTime);
			Date end=ds.parse(endTime);
			long dif=end.getTime()-start.getTime();
			return  String.valueOf(dif/(1000*60*60));
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
   //用于获取标准格式的时间
	public static String norTime(String startTime){
		SimpleDateFormat ds= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		SimpleDateFormat nords=new SimpleDateFormat("yyyyMMddHHmmss");
		try{
			Date start=ds.parse(startTime);
			String time=nords.format(start);
			return time;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
