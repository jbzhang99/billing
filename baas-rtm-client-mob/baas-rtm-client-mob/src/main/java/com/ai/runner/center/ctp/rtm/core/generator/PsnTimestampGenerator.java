package com.ai.runner.center.ctp.rtm.core.generator;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.ai.runner.center.ctp.rtm.core.db.DaoFactory;
import com.ai.runner.center.ctp.rtm.core.db.dao.RtmOutputLogDao;
import com.ai.runner.center.ctp.rtm.core.db.entity.RtmOutputLog;
import com.ai.runner.center.ctp.rtm.core.util.ScanPathContainer;

/**
 * PSN生成器,18位
 * @author majun
 *
 */
public class PsnTimestampGenerator implements IPsnGenerator {
	private int count=0;//记录计数器
	private int max=100;//记录最大值
	private String psn;
	private int seed = RandomUtils.nextInt(20);
	private final int SEED_MAX = 99999;
	private Lock lock = new ReentrantLock();
	private RtmOutputLogDao rtmOutputLogDao=(RtmOutputLogDao)DaoFactory.getInstance(RtmOutputLogDao.name);
	private String system_id;
	private String service_id;
	private String tenant_id;
	private String sourcePath;
	private String psnLimit="10";
	public PsnTimestampGenerator(String service_id){
		this.service_id = service_id;
		init();
	}
	
	private void init(){
			if(StringUtils.isNotBlank(psnLimit)){
			max = Integer.parseInt(psnLimit);
		}
		sourcePath = ScanPathContainer.getScanPathByKey(service_id);
		//System.out.println(sourcePath);
		psn = newPsn();
	}
	
	@Override
	public String getNext(Object data){
		lock.lock();
		int add = NumberUtils.toInt((String)data);
		if(count>=max){
//			System.out.println("111 count="+count);
//			System.out.println("old psn="+psn);
			//rtmOutputLogDao.updateFinished(psn,count,sourcePath);
			insertOutputLog();
			psn = newPsn();
			count = add;
//			System.out.println("new psn="+psn);
//			System.out.println("333 count="+count);
		}else{
			count=count+add;
			//System.out.println("222 count="+count);
		}
		lock.unlock();
		return psn;
	}
	
	private String newPsn(){
		if(seed>=SEED_MAX){
			seed = RandomUtils.nextInt(20);
		}else{
			seed++;
		}
		//String psn = String.valueOf(System.currentTimeMillis()*100000+seed);
		//insertNewPsn(psn);
		return String.valueOf(System.currentTimeMillis()*100000+seed);
	}
	
	private int insertOutputLog(){
		RtmOutputLog rtmOutputLog = new RtmOutputLog();
		rtmOutputLog.setPsn(psn);
		//rtmOutputLog.setBusiness_id(business_id);
		rtmOutputLog.setSystem_id(system_id);
		rtmOutputLog.setService_id(service_id);
		rtmOutputLog.setTenant_id(tenant_id);
		rtmOutputLog.setTotal_send(count);
		rtmOutputLog.setFinished(1);
		rtmOutputLog.setFinished_time(new Timestamp(new Date().getTime()));
		rtmOutputLog.setPath(sourcePath);
		return rtmOutputLogDao.insertOutputLog(rtmOutputLog);
	}

	public String getSystem_id() {
		return system_id;
	}

	public void setSystem_id(String system_id) {
		this.system_id = system_id;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public String getPsnLimit() {
		return psnLimit;
	}

	public void setPsnLimit(String psnLimit) {
		this.psnLimit = psnLimit;
	}
	
}
