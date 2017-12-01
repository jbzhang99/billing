package com.ai.baas.bmc.topology.bolt;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

import com.ai.baas.bmc.topology.billing.BillingMaster;
import com.ai.baas.bmc.topology.billing.config.HBaseOutputMapping;
import com.ai.baas.bmc.topology.billing.output.Account;
import com.ai.baas.bmc.topology.billing.output.CreditControl;
import com.ai.baas.bmc.topology.billing.output.HBaseOutput;
import com.ai.baas.bmc.topology.billing.output.IOutput;
import com.ai.baas.bmc.topology.billing.output.RealCharge;
import com.ai.baas.bmc.topology.billing.output.ResourceBook;
import com.ai.baas.bmc.topology.cache.CacheProxy;
import com.ai.baas.bmc.topology.entity.BillingDetailRecord;
import com.ai.baas.bmc.topology.util.ExceptionUtil;
import com.ai.baas.storm.exception.BusinessException;
import com.ai.baas.storm.jdbc.JdbcProxy;
import com.ai.baas.storm.message.MappingRule;
import com.ai.baas.storm.message.MessageParser;
import com.ai.baas.storm.util.BaseConstants;
import com.ai.baas.storm.util.HBaseProxy;

/**
 * 计费bolt
 * @author majun
 * @since 2016.3.21
 *
 */
public class BillingBolt extends BaseBasicBolt {
	private static final long serialVersionUID = -3214008757998306486L;
	private static Logger logger = LoggerFactory.getLogger(BillingBolt.class);
	private MappingRule[] mappingRules = new MappingRule[2];
	private String[] outputFields = new String[0];
	private BillingMaster billingMaster;
	private IOutput detailBillOutput;
	private IOutput realChargeOutput;
	private IOutput creditControlOutput;
	private IOutput accountOutput;
	private IOutput resourceBookOutput;
	
	@Override
	public void prepare(Map stormConf, TopologyContext context) {
		System.out.println("----------BillingBolt--------");
		JdbcProxy.loadDefaultResource(stormConf);
		CacheProxy.loadResource(stormConf);
		HBaseProxy.loadResource(stormConf);
		mappingRules[0] = MappingRule.getMappingRule(
				MappingRule.FORMAT_TYPE_OUTPUT, BaseConstants.JDBC_DEFAULT);
		mappingRules[1] = mappingRules[0];
		detailBillOutput = new HBaseOutput(new HBaseOutputMapping());
		realChargeOutput = new RealCharge();
		creditControlOutput = new CreditControl(stormConf);
		accountOutput = new Account(stormConf);
		resourceBookOutput = new ResourceBook(stormConf);
		billingMaster = new BillingMaster();
	}
	
	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		String line = "";
		Map<String, String> data = null;
		try {
			line = input.getStringByField(BaseConstants.RECORD_DATA);
			System.out.println("BillingBolt--inputData=>>>>>"+line);
			MessageParser messageParser = MessageParser.parseObject(line, mappingRules, outputFields);
			data = messageParser.getData();
			BillingDetailRecord detailRecord = billingMaster.billing(data);
			//将返回数据输出到详单表中
			//List<BillingDetailRecord> detailRecords = Lists.newArrayList(detailRecord);
			System.out.println("---->>>>输出到详单中");
			System.out.println("subject_code="+detailRecord.getFees().get(0).getSubjectCode());
			System.out.println("price="+detailRecord.getFees().get(0).getPrice().doubleValue());
			if(detailRecord.getFees().size()>0){
				realChargeOutput.send(detailRecord);
				detailBillOutput.send(detailRecord);
				creditControlOutput.send(detailRecord);
				accountOutput.send(detailRecord);
				resourceBookOutput.send(detailRecord);
			}
		}catch(BusinessException be){
			ExceptionUtil.out(data, line, be.getCode(), be.getStrStackTrace());
			be.printStackTrace();
		}catch(Exception e){
			ExceptionUtil.out(data, line, "BMC-S0005", e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	}

//	private void exceptionOut(Map<String, String> data,String input,String failCode, String failReason){
//		if (data != null && data.size() > 0) {
//			FailBillHandler.addFailBillMsg(data, BmcConstants.FAIL_STEP, failCode, failReason);
//		}else{
//			FailBillHandler.addFailBillMsg(input, BmcConstants.FAIL_STEP, failCode, failReason);
//		}
//	}

}
