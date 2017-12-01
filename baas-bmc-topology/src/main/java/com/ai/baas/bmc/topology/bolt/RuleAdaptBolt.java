package com.ai.baas.bmc.topology.bolt;

import java.util.Map;

import com.ai.baas.bmc.topology.util.ExceptionUtil;

import org.apache.commons.lang.StringUtils;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

import com.ai.baas.bmc.topology.adapt.RouteMapping;
import com.ai.baas.bmc.topology.adapt.processor.IRuleProcessor;
import com.ai.baas.bmc.topology.cache.CacheProxy;
import com.ai.baas.storm.exception.BusinessException;
import com.ai.baas.storm.jdbc.JdbcProxy;
import com.ai.baas.storm.message.MappingRule;
import com.ai.baas.storm.message.MessageParser;
import com.ai.baas.storm.util.BaseConstants;
import com.ai.baas.storm.util.HBaseProxy;

/**
 * 规则适配bolt
 * @author majun
 * @since 2016.3.21
 *
 */
public class RuleAdaptBolt extends BaseBasicBolt {
	private static final long serialVersionUID = 8475030105476807164L;
	private MappingRule[] mappingRules = new MappingRule[2];
	private RouteMapping routeMapping;
	private String[] outputFields;
	
	public RuleAdaptBolt(String aOutputFields){
		System.out.println("===============RuleAdaptBolt==============");
		outputFields = StringUtils.splitPreserveAllTokens(aOutputFields, ",");
	}

	@Override
	public void prepare(Map stormConf, TopologyContext context) {
		System.out.println("**************RuleAdaptBolt**************");
		JdbcProxy.loadDefaultResource(stormConf);
		CacheProxy.loadResource(stormConf);
		HBaseProxy.loadResource(stormConf);
		mappingRules[0] = MappingRule.getMappingRule(MappingRule.FORMAT_TYPE_OUTPUT, BaseConstants.JDBC_DEFAULT);
		mappingRules[1] = mappingRules[0];
		routeMapping = new RouteMapping();
	}
	
	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		String line = "";
		Map<String, String> data = null;
		try {
			line = input.getStringByField(BaseConstants.RECORD_DATA);
			System.out.println("RuleAdaptBolt--inputData=>>>>>"+line);
			MessageParser messageParser = MessageParser.parseObject(line, mappingRules, outputFields);
			data = messageParser.getData();
			IRuleProcessor processor = routeMapping.getProcessorByRoute(data);
			processor.buildRuleAdapt(data);
			collector.emit(messageParser.toTupleData());
		}catch(BusinessException be){
			ExceptionUtil.out(data, line, be.getCode(), be.getStrStackTrace());
			be.printStackTrace();
		}catch(Exception e){
			ExceptionUtil.out(data, line, "BMC-S0004", e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(outputFields));
	}

}
