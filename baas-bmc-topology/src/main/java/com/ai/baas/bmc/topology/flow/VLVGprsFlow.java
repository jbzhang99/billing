package com.ai.baas.bmc.topology.flow;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.topology.bolt.DuplicateCheckingBolt;
import com.ai.baas.bmc.topology.bolt.RuleAdaptBolt;
import com.ai.baas.bmc.topology.bolt.UnpackingBolt;
import com.ai.baas.bmc.topology.bolt.VIVBillingBolt;
import com.ai.baas.bmc.topology.util.BmcConstants;
import com.ai.baas.storm.flow.BaseFlow;
import com.ai.baas.storm.util.BaseConstants;


/**
 * VIV专用拓扑图
 * @author biancx
 * @since 2016.5.25
 */
public class VLVGprsFlow extends BaseFlow {
	private static Logger logger = LoggerFactory.getLogger(VoiceGeneralFlow.class);
	
	@Override
	@SuppressWarnings("unchecked")
	public void define() {
		super.setKafkaSpout();
		Map<String,String> outputFieldMapping = (Map<String,String>)conf.get("bmc.gprs.bolt.output.field");
		builder.setBolt(BmcConstants.UNPACKING_BOLT, new UnpackingBolt(outputFieldMapping.get(BmcConstants.UNPACKING_BOLT)), getParallelNum("bmc.voice.unpacking.executor.num",1)).shuffleGrouping(BaseConstants.KAFKA_SPOUT_NAME);
		builder.setBolt(BmcConstants.DUPLICATE_CHECKING_BOLT, new DuplicateCheckingBolt(outputFieldMapping.get(BmcConstants.DUPLICATE_CHECKING_BOLT)), getParallelNum("bmc.voice.duplicate.checking.executor.num",1)).shuffleGrouping(BmcConstants.UNPACKING_BOLT);
		builder.setBolt(BmcConstants.RULE_ADAPT_BOLT, new RuleAdaptBolt(outputFieldMapping.get(BmcConstants.RULE_ADAPT_BOLT)), getParallelNum("bmc.voice.rule.adapt.executor.num",1)).shuffleGrouping(BmcConstants.DUPLICATE_CHECKING_BOLT);
		builder.setBolt(BmcConstants.BILLING_BOLT, new VIVBillingBolt(), getParallelNum("bmc.voice.billing.executor.num",1)).shuffleGrouping(BmcConstants.RULE_ADAPT_BOLT);
	}

	public static void main(String[] args) {
		VLVGprsFlow flow = new VLVGprsFlow();
		flow.run(args);
	}

	
}

