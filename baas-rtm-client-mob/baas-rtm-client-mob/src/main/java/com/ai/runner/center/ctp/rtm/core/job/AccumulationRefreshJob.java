//package com.ai.runner.center.ctp.rtm.core.job;
//
//import java.sql.Timestamp;
//import java.util.Date;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.ai.runner.center.ctp.rtm.core.cache.CacheProxy;
//import com.ai.runner.center.ctp.rtm.core.cache.ICacheClient;
//import com.ai.runner.center.ctp.rtm.core.persistence.entity.RtmOutputLog;
//import com.ai.runner.center.ctp.rtm.core.persistence.service.RtmOutputLogService;
//import com.ai.runner.center.ctp.rtm.core.util.NumberUtil;
//@Component
//public class AccumulationRefreshJob {
//
//	@SuppressWarnings("unused")
//	private static Logger logger = LoggerFactory.getLogger(AccumulationRefreshJob.class);
//	@Autowired
//	private CacheProxy cacheProxy;
//	private ICacheClient cacheClient ;
//	private static final String lock = ".lock";
//	private String system_id;
//	private String tenant_id;
//	private String service_id;
//	private RtmOutputLogService rtmOutputLogService;
//	
//	public AccumulationRefreshJob(){
//		//System.out.println("AccumulationRefreshJob====================");
//	}
//	
//	public void work(){
//		//System.out.println("date:" + new Date().toString());
//		//String strCache = (String)PropertiesUtil.getValue("ctp.rtm.packet.psn.accumulate.cache");
//		StringBuilder colleKey = new StringBuilder();
//		colleKey.append(system_id).append("$");
//		colleKey.append(tenant_id).append("$");
//		colleKey.append(service_id).append("$");
//		colleKey.append("PSN");
//		String strColleKey = colleKey.toString();
//		cacheClient=cacheProxy.getCacheClient();
//		if(cacheClient.exists(strColleKey)){
//			StringBuilder lockKey = new StringBuilder();
//			lockKey.append(colleKey).append(lock);
//			String lockTmp = lockKey.toString();
//			if(!cacheClient.exists(lockTmp)){
//				cacheClient.rename(strColleKey, lockTmp);
//			}
//			//Set<String> members = cacheClient.smembers(lockTmp);
//			//cacheClient.del(lockTmp);
//			Map<String,String> lockPsn = cacheClient.hgetAll(lockTmp);
//			//System.out.println(lockPsn);
//			refreshDatabase(lockPsn);
//			cacheClient.del(lockTmp);
//		}
//	}
//	
//	private void refreshDatabase(Map<String,String> lockPsn){
//		RtmOutputLog rtmOutputLog;
//		for(Entry<String,String> entry:lockPsn.entrySet()){
//			//addNum = cacheClient.get(member);
//			//cacheClient.del(member);
////			if(!NumberUtil.isNumber(addNum)){
////				continue;
////			}
//			rtmOutputLog = new RtmOutputLog();
//			rtmOutputLog.setSystemId(system_id);
//			rtmOutputLog.setTenantId(tenant_id);
//			rtmOutputLog.setServiceId(service_id);
//			rtmOutputLog.setPsn(entry.getKey());
//			rtmOutputLog.setTotalSend(new Integer(entry.getValue()));
//			rtmOutputLog.setFinishedTime(new Timestamp(System.currentTimeMillis()));
//			rtmOutputLogService.updateAccumulatedPsn(rtmOutputLog);
//		}
//	}
//
//	public void setSystem_id(String system_id) {
//		this.system_id = system_id;
//	}
//
//	public void setTenant_id(String tenant_id) {
//		this.tenant_id = tenant_id;
//	}
//
//	public void setService_id(String service_id) {
//		this.service_id = service_id;
//	}
//
//	public void setRtmOutputLogService(RtmOutputLogService rtmOutputLogService) {
//		this.rtmOutputLogService = rtmOutputLogService;
//	}
//	
//}
