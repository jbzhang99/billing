package com.ai.baas.rtm.entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ai.baas.rtm.constants.RtmConstants;
import com.ai.baas.rtm.executor.ProcessHandler;
import com.ai.baas.rtm.initload.ConfigInit;
import com.ai.baas.rtm.initload.GetMdsProperty;
import com.ai.baas.rtm.initload.McsClient;
import com.ai.baas.rtm.initload.ThreadPoolInit;
import com.ai.baas.rtm.utils.ApplicationContextUtil;
import com.ai.baas.rtm.utils.HbaseUtil;
import com.ai.baas.rtm.utils.PropertiesUtil;





public class RtmRestStart {
	private static Logger logger=LoggerFactory.getLogger(RtmRestStart.class);
	public static JdbcTemplate jdbcTemplate;
	//测试时添加的
	//public static ApplicationContext context = new ClassPathXmlApplicationContext("context/rtm-context.xml");
	public void start(){
		logger.debug("启动rest服务时加载rtm初始化资源....");
		try{
			runTool();
		}catch(Exception e){
			logger.error("context", e);
			logger.debug("[资源加载失败...]");
		}
		logger.debug("资源加载成功........");
	}
	
	private void runTool(){
		PropertiesUtil.load("context/rtm-core.properties");
		logger.debug("读取配置文件成功........");
		ThreadPoolInit.create();
		logger.debug("线程池创建成功......");
		McsClient.getClient();
		logger.debug("获取mcs的连接权........");
		ApplicationContextUtil appContextUtil = ApplicationContextUtil.getInstance();
		jdbcTemplate = (JdbcTemplate) appContextUtil.getBean("jdbcTemplate");
		logger.debug("创建数据库连接成功...........");
		ConfigInit conf= new ConfigInit();
		conf.loadAutnInfo();
		conf.loadRtmInfo();
		conf.loadRtmRecord();
		conf.loadMds();
		conf.loadDemoNum();
		conf.loadRtmParam();
		logger.debug("将基础数据加载进mcs中 ........");
//		HbaseUtil.init();
//		logger.debug("hbase 建立连接......");
		GetMdsProperty mds=new GetMdsProperty();
		mds.loadProperty();
		logger.debug("mds 初始化完成");
		ProcessHandler processHandler = new ProcessHandler();
		processHandler.start();
		logger.debug("任务处理器启动成功......");
		
	}
	public static void main(String[] args){
		//ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:context/rtm-core.xml");
		PropertiesUtil.load("context/rtm-core.properties");
		logger.debug("读取配置文件成功........");
		ThreadPoolInit.create();
		logger.debug("线程池创建成功......");
		McsClient.getClient();
		logger.debug("获取mcs的连接权........");
		ConfigInit conf= new ConfigInit();
		ApplicationContextUtil appContextUtil = ApplicationContextUtil.getInstance();
		//properties.load(ChargeOperatorImpl.class.getClassLoader().getResourceAsStream("context/mcspublish.properties"));
		jdbcTemplate = (JdbcTemplate) appContextUtil.getBean("jdbcTemplate");
		conf.loadAutnInfo();
		conf.loadRtmInfo();
		conf.loadRtmRecord();
		conf.loadBsn();
		conf.loadMds();
		conf.loadDemoNum();
		conf.loadRtmParam();
		logger.debug("将基础数据加载进mcs中 ........");
		//HbaseUtil.init();
		logger.debug("hbase 建立连接......");
		GetMdsProperty mds=new GetMdsProperty();
		mds.loadProperty();
		logger.debug("mds 初始化完成");
		//System.out.println("the hhhhh "+RtmConstants.JS_SPLIT);
		//\"JSMVNE20160301001","message":\"MVNE\\u0003sms-detail\u00031\u0003mvne\u0003123456\u0003sms-detail\u000120160301001\u000110\u00011\u0001hx\u0001bw\u000117091234567\u000120160301010101\u0001con\u00011\u000110001\u000160"
		//String message = "JSBIUGZT20160503004BIUBIU-GZT-detailGZT20160503004BIU123456BIU-GZT-detailGZT2016050300410310104197602066812姚桢姚桢310104197602066812一致yxzxweb20160430171100gzt1000031.5";
		
		String message="ZXALIchenshuliang111ZXTime1469609113779ZX123456ECSchenshuliang111i-25m6aeon2cn-beijing-btc-a01120160801090000TIME";
				//+ "SXCM_DATA17090181837123201604081111112345624567123410.1.234.12";
		
		try{
			ProcessHandler.taskQueue.put(message);
			logger.debug("the size of the message is "+ProcessHandler.taskQueue.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		ProcessHandler processHandler = new ProcessHandler();
		processHandler.start();
		logger.debug("任务处理器启动成功......");
	}
}
