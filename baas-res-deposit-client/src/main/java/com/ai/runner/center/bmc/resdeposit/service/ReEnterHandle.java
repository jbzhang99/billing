package com.ai.runner.center.bmc.resdeposit.service;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.paas.ipaas.util.StringUtil;
import com.ai.runner.center.bmc.resdeposit.dao.mapper.bo.FailMsgLog;
import com.ai.runner.center.bmc.resdeposit.service.interfaces.IFailMsgSV;
import com.ai.runner.center.bmc.resdeposit.util.BmcSeqUtil;
import com.ai.runner.center.bmc.resdeposit.util.LoggerUtil;
import com.ai.runner.center.bmc.resdeposit.vo.UserMsg;
import com.google.gson.Gson;

/**
 * Date: 2016年5月4日 <br>
 * 
 * @author zhoushanbin 
 * 
 * Copyright (c) 2016 asiainfo.com <br>
 */
@Component
public final class ReEnterHandle {

    private static final Logger LOG = LoggerFactory
            .getLogger(ReEnterHandle.class);

    private static final int CAPACITY = 2000;

    private static ArrayBlockingQueue<String[]> msgQueue2;
    
    private ScheduledExecutorService inExecutor;

    private static final String UNDEAL = "undeal";
    
    private static final String SYSTEM_ID = "RESDEPOSIT";
    
    private static final String TENANT_ID = "VIV-BYD";

    @Autowired
    private IFailMsgSV failMsgSv;

    static {
        msgQueue2 = new ArrayBlockingQueue<String[]>(CAPACITY);
        
    }
    
    public ReEnterHandle() {
    
        inExecutor = Executors.newScheduledThreadPool(1);

    }

    public void init() {

        LOG.info("init ReEnterHandle!!!");

    }

    public void destory() {
        inExecutor.shutdown();
    }

    public void start() {
        
        LOG.info("启动异常处理器！！！");
        inExecutor.scheduleWithFixedDelay(new PutMsgToDB(), 5, 30,
                TimeUnit.SECONDS);
    }

    
    public static void pushToStatic(Object msg,String errorMsg,String type){
        Gson gson = new Gson();
        if(msg instanceof UserMsg){
            pushToStatic(gson.toJson(msg).toString(),errorMsg,type);
        }
            
    }
    
    
    public static void pushToStatic(String msg, String errorMsg,String type){
        if(StringUtil.isBlank(msg)){
            return;
        }
        try {
            String str[] = new String[3];
            str[0] = msg;
            if(!StringUtils.isBlank(errorMsg) && errorMsg.length() > 1500){
                errorMsg = errorMsg.substring(0, 1500);
            }
            str[1] = StringUtils.defaultIfBlank(errorMsg, "error");
            str[2] = type;
            msgQueue2.put(str);
        } catch (InterruptedException e) {
            LoggerUtil.log.error("msg queue put error!", e);
            e.printStackTrace();
        }
    }
    
    private void pushToStatic2(String msg, String errorMsg,String type) {
        int tryTimes = 5;
        
        try {
            
            FailMsgLog log = new FailMsgLog();
            log.setId(Integer.valueOf(BmcSeqUtil.getFailMsgLogId()));
            log.setSystemId(SYSTEM_ID);
            UserMsg userMsg = JSON.parseObject(msg, UserMsg.class);
            log.setTenantId(userMsg.getTENANT_ID());
            log.setMsg(msg);
            //log.setDate(getCurMonth());
            log.setDate(getCurTime());
            log.setErrorMsg(errorMsg);
            log.setType(type);
            log.setStatus(UNDEAL);
            failMsgSv.insert(log);

        } catch (Exception e) {
            //防止随机生成的id冲突重新入库
            tryTimes--;
            if(tryTimes < 0){
                return;
            }
            e.printStackTrace();
            LoggerUtil.log.error("", e);
            pushToStatic2(msg, errorMsg,type);
        }
    }

    public class PutMsgToDB implements Runnable{

        @Override
        public void run() {
            try {
                List<String[]> list = new ArrayList<String[]>();
                msgQueue2.drainTo(list, 100);
                for (String []msg : list) {
                    
                    pushToStatic2(msg[0],msg[1],msg[2]);
                }
                list.clear();
            } catch (Exception e) {
                e.printStackTrace();
                LoggerUtil.log.error("", e);
            }
        }
        
    }
    
    @SuppressWarnings("unused")
    private String getCurMonth() {

        Locale locale = new Locale("zh_CN");
        Calendar now = Calendar.getInstance(locale);

        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;

        String yyyymm = "";
        if (month < 10) {
            yyyymm = String.valueOf(year) + "0" + String.valueOf(month);
        } else {
            yyyymm = String.valueOf(year) + String.valueOf(month);
        }
        return yyyymm;
    }
    
    private String getCurTime(){
        
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fm.format(new Date());
    }
    
    
    private int generateId() {
        SecureRandom random = new SecureRandom();
        return random.nextInt(Integer.MAX_VALUE);
    }
}

//package com.ai.runner.center.bmc.resdeposit.service;
//
//import java.security.SecureRandom;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import com.ai.paas.ipaas.util.StringUtil;
//import com.ai.runner.center.bmc.resdeposit.dao.mapper.bo.FailMsgLog;
//import com.ai.runner.center.bmc.resdeposit.service.interfaces.IFailMsgSV;
//import com.ai.runner.center.bmc.resdeposit.util.KafkaUtil;
//import com.ai.runner.center.bmc.resdeposit.util.LoggerUtil;
//import com.ai.runner.center.bmc.resdeposit.vo.PageInfo;
//import com.ai.runner.center.bmc.resdeposit.vo.UserMsg;
//import com.google.gson.Gson;
//
//
///**
// * Date: 2016年5月4日 <br>
// * 
// * @author zhoushanbin 
// * 
// * Copyright (c) 2016 asiainfo.com <br>
// */
//@Component
//public final class ReEnterHandle {
//
//	private static final Logger LOG = LoggerFactory
//			.getLogger(ReEnterHandle.class);
//
//	private static final int CAPACITY = 2000;
//
//	private ArrayBlockingQueue<String> msgQueue;
//
//	private static ArrayBlockingQueue<String[]> msgQueue2;
//	
//	// private static ReEnterHandle instance;
//
//	private ScheduledExecutorService getExecutor;
//
//	private ScheduledExecutorService outExecutor;
//	
//	private ScheduledExecutorService inExecutor;
//
//	private static final String UNDEAL = "undeal";
//
//	private static final String DEAL = "deal";
//
//	private static final String SYSTEM_ID = "RESDEPOSIT";
//
//	private static final String TENANT_ID = "VIV-BYD";
//	
//	//private static final String SPLIT = "$#@!!qaz";
//	
//	private KafkaUtil kafka;
//	
//	@Autowired
//	private IFailMsgSV failMsgSv;
//
//	static {
//		msgQueue2 = new ArrayBlockingQueue<String[]>(CAPACITY);
//	}
//	
//	public ReEnterHandle() {
//		
//		kafka = new KafkaUtil();
//		
//		msgQueue = new ArrayBlockingQueue<String>(CAPACITY);
//	
//		getExecutor = Executors.newScheduledThreadPool(1);
//
//		outExecutor = Executors.newScheduledThreadPool(1);
//		
//		inExecutor = Executors.newScheduledThreadPool(1);
//
//	}
//
//	public void init() {
//
//		LOG.info("init ReEnterHandle!!!");
//
//	}
//
//	public void destory() {
//		if (null != outExecutor) {
//			outExecutor.shutdown();
//		}
//		if (null != getExecutor) {
//			getExecutor.shutdown();
//		}
//	}
//
//	public void start() {
//
//		//getExecutor.scheduleWithFixedDelay(new GetMsgFromDB(), 5, 60,
//		//		TimeUnit.SECONDS);
//
//		//outExecutor.scheduleWithFixedDelay(new OutMsgToMsgSource(), 5, 60,
//		//		TimeUnit.SECONDS);
//		
//		inExecutor.scheduleWithFixedDelay(new PutMsgToDB(), 5, 30,
//				TimeUnit.SECONDS);
//	}
//
//	/**
//	 * public static ReEnterHandle getInstance() { if (null != instance) {
//	 * return instance; } else { synchronized (ReEnterHandle.class) { if (null
//	 * == instance) { instance = new ReEnterHandle(); } } } return instance; }
//	 **/
//	
//	public static void pushToStatic(Object msg,String errorMsg){
//		Gson gson = new Gson();
//		if(msg instanceof UserMsg){
//			pushToStatic(gson.toJson(msg).toString(),errorMsg);
//		}
//			
//	}
//	
//	
//	public static void pushToStatic(String msg, String errorMsg){
//		if(StringUtil.isBlank(msg)){
//			return;
//		}
//		try {
//			String str[] = new String[2];
//			str[0] = msg;
//			str[1] = StringUtils.defaultIfBlank(errorMsg, "error");
//			msgQueue2.put(str);
//		} catch (InterruptedException e) {
//			LoggerUtil.log.error("msg queue put error!", e);
//			e.printStackTrace();
//		}
//	}
//	
//	private void pushToStatic2(String msg, String errorMsg) {
//		int tryTimes = 5;
//		try {
//			FailMsgLog log = new FailMsgLog();
//			log.setId(generateId());
//			log.setSystemId(SYSTEM_ID);
//			log.setTenantId(TENANT_ID);
//			log.setMsg(msg);
//			//log.setDate(getCurMonth());
//			log.setDate(getCurTime());
//			log.setErrorMsg(errorMsg);
//			log.setType("BATCH");
//			log.setStatus(UNDEAL);
//			failMsgSv.insert(log);
//
//		} catch (Exception e) {
//			//防止随机生成的id冲突重新入库
//			tryTimes--;
//			if(tryTimes < 0){
//				return;
//			}
//			e.printStackTrace();
//			LoggerUtil.log.error("", e);
//			pushToStatic(msg, errorMsg);
//		}
//	}
//
//	private void addMsgToShm(String msg) {
//		try {
//			msgQueue.put(msg);
//		} catch (InterruptedException e) {
//			LoggerUtil.log.error("msg queue put error!", e);
//			e.printStackTrace();
//		} finally {
//
//		}
//	}
//
//	private void updateStaticState(FailMsgLog failMsgLog) {
//		
//		try{
//			failMsgSv.updateByPrimaryKey(failMsgLog);
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			LoggerUtil.log.error("", e);
//		}
//		
//	}
//
//	class GetMsgFromDB implements Runnable {
//
//		@Override
//		public void run() {
//			try {
//				PageInfo<FailMsgLog> page = new PageInfo<FailMsgLog>();
//				page = failMsgSv.query(SYSTEM_ID, TENANT_ID, getCurMonth(),
//						UNDEAL, page);
//				page.setCurrPosInDb(0);
//				page.setSize(0);
//
//				for (FailMsgLog msgLog : page.getList()) {
//					addMsgToShm(msgLog.getMsg());
//					msgLog.setStatus(DEAL);
//					updateStaticState(msgLog);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				LoggerUtil.log.error("", e);
//			}
//		}
//
//	}
//
//	class OutMsgToMsgSource implements Runnable {
//
//		@Override
//		public void run() {
//			try {
//				List<String> list = new ArrayList<String>();
//				msgQueue.drainTo(list, 100);
//
//				for (String msg : list) {
//					LoggerUtil.log.debug(msg);
//					System.out.println(msg + "to msg source!");
//					kafka.addQueue(msg);
//				}
//				list.clear();
//			} catch (Exception e) {
//				e.printStackTrace();
//				LoggerUtil.log.error("", e);
//			}
//		}
//
//	}
//	
//	class PutMsgToDB implements Runnable{
//
//		@Override
//		public void run() {
//			try {
//				List<String[]> list = new ArrayList<String[]>();
//				msgQueue2.drainTo(list, 100);
//				for (String []msg : list) {
//					
//					pushToStatic2(msg[0],msg[1]);
//				}
//				list.clear();
//			} catch (Exception e) {
//				e.printStackTrace();
//				LoggerUtil.log.error("", e);
//			}
//		}
//		
//	}
//	
//	private String getCurMonth() {
//
//		Locale locale = new Locale("zh_CN");
//		Calendar now = Calendar.getInstance(locale);
//
//		int year = now.get(Calendar.YEAR);
//		int month = now.get(Calendar.MONTH) + 1;
//
//		String yyyymm = "";
//		if (month < 10) {
//			yyyymm = String.valueOf(year) + "0" + String.valueOf(month);
//		} else {
//			yyyymm = String.valueOf(year) + String.valueOf(month);
//		}
//		return yyyymm;
//	}
//	
//	private String getCurTime(){
//		
//		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		return fm.format(new Date());
//	}
//	
//	
//	private int generateId() {
//		SecureRandom random = new SecureRandom();
//		return random.nextInt(Integer.MAX_VALUE);
//	}
//}
