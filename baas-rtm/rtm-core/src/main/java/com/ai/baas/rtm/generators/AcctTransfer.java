package com.ai.baas.rtm.generators;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.rtm.constants.RtmConstants;
import com.ai.baas.rtm.initload.McsClient;
import com.ai.baas.rtm.processor.RestProcessor;


public class AcctTransfer {
	private static Logger logger=LoggerFactory.getLogger(AcctTransfer.class);
	private static String ActAcct;
	public static String getAcct(String recordType,String[] record,String arrival_time,int type,String tenantId,String systemId){
		logger.debug("the recordType is... "+recordType);
		logger.debug("the RtmConstants.START_TIME" +RtmConstants.START_TIME);
		try{
			String startLength=McsClient.client.hget(recordType,RtmConstants.START_TIME);
			logger.debug("the recordType is "+recordType);
			logger.debug("the startNum is "+startLength);
			//String arrivalNum=McsClient.client.hget(recordType, RtmConstants.ARRIVAL_TIME);
			if(startLength==null)
				logger.error("the start_time not in the mcs");
			String[] startNums=StringUtils.splitPreserveAllTokens(startLength, RtmConstants.KEY_JOINER);
			String startTime=record[Integer.valueOf(startNums[0])-1];
			logger.debug("the startTime is "+startTime);
			//String arrivalTime=record[Integer.valueOf(arrivalNum)-1];
			String startAcct;
			String arrivalAcct;
			if(type==1){
				return startTime.substring(0, 6);
			}else{
				StringBuilder firstKey=new StringBuilder();
				firstKey.append(tenantId.toUpperCase()).append(RtmConstants.KEY_JOINER).append(systemId.toUpperCase())
				.append(RtmConstants.KEY_JOINER).append(recordType.toUpperCase());
				String transDay=McsClient.client.hget(firstKey.toString(),RtmConstants.TRANS_DAY);
				String transTime=McsClient.client.hget(firstKey.toString(),RtmConstants.TRANS_TIME);
				//if(("".equals(transDay)||"".equals(transTime))||(transDay==null||transTime==null)){
				if(("".equals(transDay))||(transDay==null)){
					if(startTime.length()>=6){
						 startAcct=startTime.substring(0, 6);
						 logger.debug("the acct is "+startAcct);
					}else{
						return null;
						
					}
					if(arrival_time.length()==14){
						 arrivalAcct=arrival_time.substring(0,10);
					}else{
						return null;
					}
					if(startAcct.equals(arrivalAcct.substring(0,6)))
						return startAcct;
					 else{
						 StringBuilder acctTemp=new StringBuilder();
						 if("12".equals(startAcct.substring(4, 6))){
							 acctTemp.append(String.valueOf((Integer.valueOf(startAcct.substring(0, 4))+1))).append("01"); 
							 logger.debug("跨年日期，当前月份为："+acctTemp.toString());
						 }else{
							 acctTemp.append(String.valueOf(Integer.valueOf(startAcct)+1)); 
							 logger.debug("非跨年日期，当前月份为："+acctTemp.toString()); 
						 }
						 
						 logger.debug("the arrivalAcct is "+arrivalAcct.substring(0,6));						 
						 if(acctTemp.toString().equals(arrivalAcct.substring(0,6))){
							 if("".equals(transTime)||transTime==null){
								 String timearrival="0109";
								 acctTemp.append(timearrival);
							 }
							 else{//表示此时是按照自然账期切换的，但是延时不是默认的9个小时，而是通过表配置的时间
									if("0".equals(transTime)){
										return arrivalAcct.substring(0,6);
									}else{
										int num=Integer.valueOf(transTime);
										int day=num/24+1;
										int hour=num%24;
										if(day<10){
											acctTemp.append("0").append(String.valueOf(day));
										}else {
											acctTemp.append(String.valueOf(day));
										}
										if(hour<10)
											acctTemp.append("0").append(String.valueOf(hour));
										else
											acctTemp.append(String.valueOf(hour));
									}
								 }
								 if(Integer.valueOf(arrivalAcct)-Integer.valueOf(acctTemp.toString())>0)
									 return arrivalAcct.substring(0,6);
								 else {
									return startAcct;
								}
						 }
						else{
							 return null;
						}
					 }
				}else{
					//arrival_time="20160828082400";
					String arrivalDay=arrival_time.substring(6, 8);
					if(Integer.parseInt(arrivalDay)>Integer.parseInt(transDay)) return nextAcct(arrival_time);
					if(Integer.parseInt(arrivalDay)<Integer.parseInt(transDay)) return arrival_time.substring(0, 6);
					if(Integer.parseInt(arrivalDay)==Integer.parseInt(transDay)){
						if(startTime.length()<8){
							logger.error("the startTime is too short  no time .........");
							return null;
						}else{
							if((startTime.substring(0, 8)).equals(arrival_time.substring(0,8))){
								return nextAcct(arrival_time);
							}else{
								if(Integer.parseInt(arrival_time.substring(8,10))<Integer.parseInt(transTime))
									return arrival_time.substring(0, 6);
								else
									return nextAcct(arrival_time);
							}
						}
					}
					return null;
				}
			}
		}catch(Exception e){
			logger.error("the getacct is error "+e.getMessage());
			return null;
		}
	}
	
	private static String nextAcct(String thisAcct){
		String thisYear=thisAcct.substring(0, 4);
		String thisMonth=thisAcct.substring(4,6);
		if("12".equals(thisMonth)){
			return String.valueOf(Integer.parseInt(thisYear)+1)+"01";
		}
		else
			if(Integer.parseInt(thisMonth)+1<10)
				return thisYear+"0"+String.valueOf(Integer.parseInt(thisMonth)+1);
			else {
				return thisYear+String.valueOf(Integer.parseInt(thisMonth)+1);
			}
	}
	
//	public static void main(String[] args){
//		String[] a=new String[3];
//		McsClient.getClient();
//		String acct=AcctTransfer.getAcct("ALIECS",a,"20160904201111",0,"ECITIC","ECS");
//		System.out.println("the acct is "+ acct);
//	}
}


