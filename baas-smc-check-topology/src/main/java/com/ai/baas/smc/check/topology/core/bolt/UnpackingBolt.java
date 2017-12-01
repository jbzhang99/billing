package com.ai.baas.smc.check.topology.core.bolt;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

import com.ai.baas.smc.check.topology.util.LoadConfUtil;
import com.ai.baas.storm.jdbc.JdbcProxy;
import com.ai.baas.storm.message.MappingRule;
import com.ai.baas.storm.message.MessageParser;
import com.ai.baas.storm.util.BaseConstants;
import com.alibaba.fastjson.JSON;

/**
 * 拆包<br>
 * Date: 2016年3月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author mayt
 */
public class UnpackingBolt extends BaseBasicBolt {
    private static final long serialVersionUID = -8200039989835637219L;

    private static final Logger LOG = LoggerFactory.getLogger(UnpackingBolt.class);

    private MappingRule[] mappingRules = new MappingRule[2];

    private String[] outputFields = new String[] { "data" };

    @SuppressWarnings("unchecked")
    @Override
    public void prepare(@SuppressWarnings("rawtypes")
    Map stormConf, TopologyContext context) {
        LOG.info(" ====== UnpackingBolt.prepare");
        LoadConfUtil.loadPaasConf(stormConf);
        JdbcProxy.loadResources(Arrays.asList(BaseConstants.JDBC_DEFAULT), stormConf);
        mappingRules[0] = MappingRule.getMappingRule(MappingRule.FORMAT_TYPE_OUTPUT,
                BaseConstants.JDBC_DEFAULT);
        mappingRules[1] = mappingRules[0];
    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        System.out.println(" ====== 开始执行解包bolt，Tuple = " + JSON.toJSONString(input));
        String line = "";
        try {
            line = input.getString(0);
            System.out.println(" ====== 解包bolt接收到消息报文:[" + line + "]");
            List<Object> values = null;
            String[] inputDatas = StringUtils.splitPreserveAllTokens(line,
                    BaseConstants.RECORD_SPLIT);
            MessageParser messageParser = null;
            for (String inputData : inputDatas) {
                messageParser = MessageParser.parseObject(inputData, mappingRules, outputFields);
                values = messageParser.toTupleData();
                if (CollectionUtils.isNotEmpty(values)) {
                    collector.emit(values);
                }
            }
        } catch (Exception e) {
            LOG.error("[" + line + "]解包失败", e);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields(outputFields));
    }

}
