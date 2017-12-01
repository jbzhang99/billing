package com.ai.baas.bmc.srv.failbill;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ai.baas.bmc.srv.entity.FailureBill;
import com.ai.baas.bmc.srv.executor.LoopThread;
import com.ai.baas.bmc.srv.persistence.service.FailureBillService;
import com.ai.baas.bmc.srv.util.BaasConstants;
import com.ai.opt.sdk.util.ApplicationContextUtil;
import com.alibaba.fastjson.JSON;

@Component
public class FailBillHandler extends LoopThread {
	private static Logger logger = LoggerFactory.getLogger(FailBillHandler.class);
	private static BlockingQueue<FailureBill> msgQueue = new LinkedBlockingQueue<FailureBill>();
	private FailBillHandler failBillHandler;
	
	@PostConstruct
	public void startup(){
		if(failBillHandler == null){
			failBillHandler = new FailBillHandler();
			failBillHandler.start();
			logger.debug("错单处理器启动成功...");
		}
	}
	
	@Override
	public void work() {
		FailureBill failureBill = null;
		try{
			//logger.debug("*********************");
			failureBill = msgQueue.take();
		}catch(InterruptedException e){
			logger.error("context", e);
			exitFlag = true;
		}
		//logger.debug("-----------------work   begin");
		try{
			FailureBillService failureBillService = ApplicationContextUtil.getService("failureBillService");
			failureBillService.insertFailBillData(failureBill);
		}catch(Exception e){
			logger.error("错单数据处理失败@@@@@@@@@@@@@@");
			e.printStackTrace();
			logger.error("插入错单数据异常：",e);
		}
		//logger.debug("-----------------work   end");
	}
	
	/**
	 * 向错单队列中增加一条错单信息
	 * @param failureBill
	 */
	public static void addFailBillMsg(FailureBill failureBill){
		try {
			msgQueue.put(failureBill);
		} catch (InterruptedException e) {
			logger.error("context", e);
		}
	}
	
	/**
	 * 插入错单数据到队列
	 * @param data
	 * @param failStep
	 * @param failCode
	 * @param failReason
	 */
	public static void addFailBillMsg(Map<String, String> data,String failCode,String failReason){
		//logger.debug("开始拼装错误数据1111111111");
		if(data == null){
			return;
		}
		FailureBill failureBill = new FailureBill();
		failureBill.setTenantId(data.get(BaasConstants.TENANT_ID));
		failureBill.setServiceType(data.get(BaasConstants.SERVICE_TYPE));
		failureBill.setSource(data.get(BaasConstants.SOURCE));
		failureBill.setBsn(data.get(BaasConstants.BATCH_SERIAL_NUMBER));
		failureBill.setSn(data.get(BaasConstants.SERIAL_NUMBER));
		failureBill.setAccountPeriod(data.get(BaasConstants.ACCOUNT_PERIOD)+"01");
		failureBill.setArrivalTime(data.get(BaasConstants.ARRIVAL_TIME));
		failureBill.setFailStep(BaasConstants.FAIL_STEP);
		failureBill.setFailCode(StringUtils.defaultString(failCode));
		failureBill.setFailDate(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		failureBill.setFailReason(failReason);
		failureBill.setFailPakcet(JSON.toJSONString(data));
		msgQueue.add(failureBill);
		//logger.debug("发送数据到队列");
	}
	
	/**
	 * 插入错单数据到队列
	 * @param original
	 * @param failStep
	 * @param failCode
	 * @param failReason
	 */
	public static void addFailBillMsg(String original,String failCode,String failReason){
		//logger.debug("开始拼装错误数据2222222");
		if(StringUtils.isBlank(original)){
			return;
		}
		//System.out.println("original="+original);
		String[] inputs = StringUtils.splitPreserveAllTokens(original,BaasConstants.FIELD_SPLIT);
		FailureBill failureBill = new FailureBill();
		if (inputs.length >= 7) {
			failureBill.setTenantId(inputs[0]);
			failureBill.setServiceType(inputs[1]);
			failureBill.setSource(inputs[2]);
			failureBill.setBsn(inputs[3]);
			failureBill.setSn(inputs[4]);
			failureBill.setArrivalTime(inputs[5]);
			failureBill.setAccountPeriod(inputs[6]+"01");
		}
		failureBill.setFailStep(BaasConstants.FAIL_STEP);
		failureBill.setFailCode(StringUtils.defaultString(failCode));
		failureBill.setFailDate(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		failureBill.setFailReason(failReason);
		failureBill.setFailPakcet(original);
		
		msgQueue.add(failureBill);
		//logger.debug("发送数据到队列");
	}
	
	@Override
	public boolean init() {
		return true;
	}

	@Override
	public boolean unInit() {
		return true;
	}

}
