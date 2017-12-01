package com.ai.baas.batch.client.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.batch.client.constants.ClientConstants;





public class lastDayUtil {
	private static final Logger logger=LoggerFactory.getLogger(lastDayUtil.class);
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
		SimpleDateFormat ds= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
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
	//用于进行消息头的拼接
	public static String messageHead(String userId,String type,String systemType){
		StringBuilder head=new StringBuilder();
		head.append(ClientConstants.TENANT_ID).append(ClientConstants.HEAD_SPLIT).append(systemType)
		.append(ClientConstants.HEAD_SPLIT).append(userId).append(type);
		Date dt=new Date();
		String time=String.valueOf(dt.getTime());
		head.append(time).append(ClientConstants.HEAD_SPLIT).append(ClientConstants.USER).append(ClientConstants.HEAD_SPLIT)
		.append(ClientConstants.PASSWD);
		return head.toString();
	}
	
}
