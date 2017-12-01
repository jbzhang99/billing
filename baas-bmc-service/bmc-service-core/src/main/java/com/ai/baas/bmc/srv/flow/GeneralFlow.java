package com.ai.baas.bmc.srv.flow;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.srv.entity.BillingDetailRecord;
import com.ai.baas.bmc.srv.failbill.BusinessException;
import com.ai.baas.bmc.srv.failbill.FailBillHandler;
import com.ai.baas.bmc.srv.flow.adapt.IRuleProcessor;
import com.ai.baas.bmc.srv.flow.billing.BillingMaster;
import com.ai.baas.bmc.srv.flow.duplicate.DuplicateCheckingFromMysql;
import com.ai.baas.bmc.srv.flow.duplicate.IDuplicateChecking;
import com.ai.baas.bmc.srv.flow.output.IOutput;
import com.ai.baas.bmc.srv.flow.output.MDSOutput;
import com.ai.baas.bmc.srv.flow.output.MysqlOutput;
import com.ai.baas.bmc.srv.flow.route.RouteMapping;
import com.ai.baas.bmc.srv.message.MessageParser;
import com.google.common.base.Joiner;


/**
 * 计费主流程
 * 
 * @author majun
 * @since 2016.9.29
 * 
 */
public class GeneralFlow extends BaseFlow{
	private static Logger logger = LoggerFactory.getLogger(GeneralFlow.class);
	private String inputMessage;
	
	public GeneralFlow(String message){
		this.inputMessage = message;
	}

	@Override
	public void process() {
		Map<String,String> data = null;
		try {
			//logger.info("[计费流程开始].....");
			//System.out.println("[计费流程开始].....");
			data = MessageParser.parseObject(inputMessage);
			//System.out.println("input data:"+data.toString());
			//logger.info("sn--"+data.get("sn"));
			//System.out.println("sn--"+data.get("sn"));
			IDuplicateChecking duplicateChecking = new DuplicateCheckingFromMysql();
			if(!duplicateChecking.checkData(data)){
				//需要将重复数据输出到错单队列中....
				String errMsg = Joiner.on("").join("重复数据[sn=",data.get("sn"),"]");
				outputFailBillMsg(data,"BMC-S0001",errMsg);
				return;
			}
			//logger.info("查重功能完成----");
			//System.out.println("查重功能完成----");
			IRuleProcessor ruleProcessor = RouteMapping.getProcessorByRoute(data);
			ruleProcessor.buildRuleAdapt(data);
			//logger.info("规则适配功能完成+++");
			BillingMaster BillingMaster = new BillingMaster();
			BillingDetailRecord billingDetailRecord = BillingMaster.billing(data);
			//logger.info("算费功能完成****");
			if(!billingDetailRecord.isPriceSuccess()){
				//批价失败，需要进入错单...
				outputFailBillMsg(data,"BMC-B0019","批价失败,没有匹配的产品资费信息!");
				return;
			}
			//if (billingDetailRecord.isInstDr()) {
			//logger.info("批价后金额="+billingDetailRecord.getData().get("fee1"));
			IOutput mysqlOutput = new MysqlOutput();
			mysqlOutput.send(billingDetailRecord);
			//System.out.println("输出详单信息****");
			IOutput msgOutput = new MDSOutput();
			msgOutput.send(billingDetailRecord);
			//}
			//logger.info("计费流程结束.....");
			System.out.println("计费流程结束.....");
		} catch (BusinessException e1) {
			e1.printStackTrace();
			//logger.debug("主流程出现异常aaaaaaaaa");
			outputFailBillMsg(data, e1.getCode(), e1.getStrStackTrace());
		} catch (Exception e2){
			e2.printStackTrace();
			//logger.debug("主流程出现异常bbbbbbbb");
			outputFailBillMsg(data, "BMC-S0002", e2.getMessage());
		}
	}
	
	private void outputFailBillMsg(Map<String, String> data,String failCode, String failReason){
		try{
			if (data != null && data.size() > 0) {
				FailBillHandler.addFailBillMsg(data, failCode, failReason);
			}else{
				FailBillHandler.addFailBillMsg(inputMessage, failCode, failReason);
			}
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}
	
//	@Override
//	public void process() {
//		//SimpleDistributedMutexTemp lock = SimpleDistributedMutexTemp.getInstance();
//		//DistributedMultiLock multiLock = DistributedMultiLock.getInstance();
//		Map<String, String> data = null;
//		try {
//			data = MessageParser.parseObject(inputMessage);
//		} catch (BusinessException e1) {
//			e1.printStackTrace();
//		}
//		String lockName = data.get("bsn");
//		DistributedMultiLock lock = new DistributedMultiLock(lockName);
//		try{
//			lock.acquire();
//			//System.out.println("thread id = "+Thread.currentThread().getId());
//			//System.out.println("thread name = "+Thread.currentThread().getName());
//			System.out.println(data.get("sn")+"  is working...");
//			Thread.currentThread().sleep(20000);
//			System.out.println(data.get("sn")+"  is stop ");
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			lock.release();
//		}
//	}

}
