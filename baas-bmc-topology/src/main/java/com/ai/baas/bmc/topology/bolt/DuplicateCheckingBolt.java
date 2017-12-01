package com.ai.baas.bmc.topology.bolt;

import java.util.ArrayList;
import java.util.Map;

import com.ai.baas.bmc.topology.util.BmcConstants;
import com.ai.baas.bmc.topology.util.ExceptionUtil;
import com.ai.baas.storm.exception.BusinessException;
import com.ai.baas.storm.failbill.FailBillHandler;

import org.apache.commons.lang.StringUtils;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

import com.ai.baas.storm.duplicate.DuplicateCheckingConfig;
import com.ai.baas.storm.duplicate.DuplicateCheckingFromHBase;
import com.ai.baas.storm.duplicate.IDuplicateChecking;
import com.ai.baas.storm.jdbc.JdbcProxy;
import com.ai.baas.storm.message.MappingRule;
import com.ai.baas.storm.message.MessageParser;
import com.ai.baas.storm.util.BaseConstants;
import com.ai.baas.storm.util.HBaseProxy;

/**
 * 查重bolt
 * @author majun
 * @since 2016.3.21
 *
 */
public class DuplicateCheckingBolt extends BaseBasicBolt {
	private static final long serialVersionUID = -4549737615575118377L;
	private IDuplicateChecking duplicateChecking;
	private MappingRule[] mappingRules = new MappingRule[2];
	private String[] outputFields;
	
	public DuplicateCheckingBolt(String aOutputFields){
		System.out.println("===============DuplicateCheckingBolt=======1111111111111111111=======");
		outputFields = StringUtils.splitPreserveAllTokens(aOutputFields, ",");
	}

	@Override
	@SuppressWarnings("unchecked")
	public void prepare(Map config, TopologyContext context) {
		System.out.println("+++++++++++++++DuplicateCheckingBolt+++++");
		JdbcProxy.loadDefaultResource(config);
		HBaseProxy.loadResource(config);
		DuplicateCheckingConfig.getInstance();
		mappingRules[0] = MappingRule.getMappingRule(MappingRule.FORMAT_TYPE_INPUT, BaseConstants.JDBC_DEFAULT);
		mappingRules[1] = MappingRule.getMappingRule(MappingRule.FORMAT_TYPE_OUTPUT, BaseConstants.JDBC_DEFAULT);
		duplicateChecking = new DuplicateCheckingFromHBase();
	}
	
	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		Map<String, String> data = null;
		String line = "";
		try{
			line = input.getStringByField(BaseConstants.RECORD_DATA);
			System.out.println("DuplicateCheckingBolt--inputData>>>>"+line);
			MessageParser messageParser = MessageParser.parseObject(line, mappingRules, outputFields);
			data = messageParser.getData();
			if(duplicateChecking.checkData(messageParser.getData())){
				//System.out.println("---->>>>>>>>>>>>>>"+messageParser.getData());
				collector.emit(messageParser.toTupleData());
			}else{
				//如错单表
				System.out.println("*************错单数据");
				//FailBillHandler.addFailBillMsg(data, BmcConstants.FAIL_STEP, "BMC-S0001", "重复数据");
				ExceptionUtil.out(data, line, "BMC-S0001", "重复数据");
			}
			//collector.emit(messageParser.toTupleData());
		}catch(Exception e){
			ExceptionUtil.out(data, line, "BMC-S0003", e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(outputFields));
	}
	

}
