package com.ai.runner.center.ctp.rtm.core.reader;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.runner.center.ctp.rtm.core.db.DaoFactory;
import com.ai.runner.center.ctp.rtm.core.db.dao.BlUserinfoDao;
import com.ai.runner.center.ctp.rtm.core.db.entity.BlUserinfo;
import com.ai.runner.center.ctp.rtm.core.executor.WrapperExecutor;
import com.ai.runner.center.ctp.rtm.core.processor.ServiceProcessor;
import com.ai.runner.utils.util.CollectionUtil;
import com.ai.runner.utils.util.DateUtil;

public class SXServiceReader implements Reader {

	private static Logger logger = LoggerFactory.getLogger(SXServiceReader.class);
	public static final String readerName = "service-reader";
	
	private Long intervals;//间隔多久进行请求
	private int threadNum;
	private Long firstSleepMinutes;
	
	public int getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	public Long getIntervals() {
		return intervals;
	}

	public void setIntervals(Long intervals) {
		this.intervals = intervals;
	}

	public Long getFirstSleepMinutes() {
		return firstSleepMinutes;
	}

	public void setFirstSleepMinutes(Long firstSleepMinutes) {
		this.firstSleepMinutes = firstSleepMinutes;
	}

	public void run() {
		//程序第一次启动休眠多少分钟
		try {
			logger.debug("程序启动，首次休眠【{}】分钟后进行开始请求话单。",firstSleepMinutes);
			TimeUnit.MINUTES.sleep(firstSleepMinutes);
			logger.debug("第一次休眠结束，开始请求话单。",firstSleepMinutes);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (true) {			
			logger.debug("【{}】开始进行扫描请求处理话单",DateUtil.getDateString(DateUtil.getSysDate(), DateUtil.yyyyMMddHHmmssSSS));
			//从数据库中取到所有的联通用户，取出所有的用户iccid
			List<BlUserinfo> users = getAllCuUsers();
			logger.debug("获取到对应的联通用户【{}】",JSONArray.fromObject(users).toString());
			if (!CollectionUtil.isEmpty(users)) {
				//根据这些iccid 根据配置的线程数去请求联通的话单  
				int size = users.size();
				int yushu = size % threadNum;
				int mol = size / threadNum;
				if (yushu!=0) {
					mol++;
				}
				int temp = threadNum;
				if (size<threadNum) {
					temp = size;
				}
				for (int i = 0; i < temp; i++) {
					int start = i*mol;
					int end = (i+1)*mol;
					if (end>(size)) {
						end = size-1;
					}
					List<BlUserinfo> subsUser = users.subList(start, end);
					//多线程去跑对应的用户流量信息
					ServiceProcessor processor = new ServiceProcessor(subsUser);
					logger.debug("线程【{}/{}】【{}】请求用户【{}】的话单",(i+1),temp,processor.getName(),JSONArray.fromObject(subsUser).toString());
//					processor.start();
					WrapperExecutor.execute(processor);
//					try {
//						logger.debug("从所有中【{},{}】往阻塞队列中写入了【{}】",start,end,JSONArray.fromObject(subsUser).toString());
//						ProcessHandler.taksQueue.put(subsUser);
//					} catch (InterruptedException e) {
//						logger.info("往阻塞队列中添加出错【{}】",JSONArray.fromObject(subsUser).toString(),e);
//					}
				}
			}
			logger.debug("【{}】结束进行话单请求，由线程去跑对应的话单程序",DateUtil.getDateString(DateUtil.getSysDate(), DateUtil.yyyyMMddHHmmssSSS));
			//休眠多少分钟
			try {
				TimeUnit.MINUTES.sleep(intervals);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static List<BlUserinfo> getAllCuUsers(){
		BlUserinfoDao userinfoDao = (BlUserinfoDao) DaoFactory.getInstance(BlUserinfoDao.name);
		BlUserinfo user = new BlUserinfo();
		return userinfoDao.queryUsers(user);
	}
	
}
