package com.ai.baas.smc.check.topology.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.smc.check.topology.constants.SmcHbaseConstant;
import com.ai.baas.storm.message.MappingRule;
import com.ai.baas.storm.message.RecordFmt.RecordFmtKey;
import com.ai.baas.storm.util.BaseConstants;

/**
 * 单条数据解析类 将字符串数据解析成Map格式<br>
 * Date: 2016年4月9日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author mayt
 */
public class SmcMessageParser {
    private static Logger logger = LoggerFactory.getLogger(SmcMessageParser.class);

    private Map<String, String> data = new HashMap<String, String>();

    private MappingRule[] mappingRules;// 0:inputMappingRule 1:outMappingRule

    private String[] outputKeys;

    private String[] inputDatas;

    private RecordFmtKey recordFmtKey;

    private static String[] headerKeys;

    static {
        headerKeys = new String[] { BaseConstants.TENANT_ID, BaseConstants.SERVICE_ID,
                BaseConstants.SOURCE, SmcHbaseConstant.ColumnName.BATCH_NO,
                SmcHbaseConstant.ColumnName.TOTAL_RECORD };
    }

    private SmcMessageParser(String original, MappingRule[] mappingRules, String[] outputKeys) {
        String[] inputParams = StringUtils.splitPreserveAllTokens(original,
                BaseConstants.FIELD_SPLIT);
        data.put(SmcHbaseConstant.ColumnName.BATCH_NO, inputParams[0]);
        data.put(SmcHbaseConstant.ColumnName.TOTAL_RECORD, inputParams[1]);
        for (int i = 0; i < headerKeys.length; i++) {
            data.put(headerKeys[i], inputParams[i]);
        }
        int dataBeginPosi = headerKeys.length;
        int len = inputParams.length - dataBeginPosi;
        inputDatas = new String[len];
        System.arraycopy(inputParams, dataBeginPosi, inputDatas, 0, len);
        this.mappingRules = mappingRules;
        this.outputKeys = outputKeys;
    }

    private void init() throws Exception {
        recordFmtKey = new RecordFmtKey(data.get(BaseConstants.TENANT_ID),
                data.get(BaseConstants.SERVICE_ID), data.get(BaseConstants.SOURCE));
        Map<String, Integer> inputMappingRule = mappingRules[0].getIndexes(recordFmtKey);
        for (Entry<String, Integer> entry : inputMappingRule.entrySet()) {
            data.put(entry.getKey(), inputDatas[entry.getValue()]);
        }
    }

    public Map<String, String> getData() {
        return data;
    }

    public List<Object> toTupleData() {
        // String[] outputKeys = boltInOutKeys.get(1);
        List<Object> rtnValue = new ArrayList<Object>();
        for (String outputKey : outputKeys) {
            if (!outputKey.equals(BaseConstants.RECORD_DATA)) {
                rtnValue.add(data.get(outputKey));
            }
        }
        rtnValue.add(toDataString(mappingRules[1].getIndexes(recordFmtKey)));
        return rtnValue;
    }

    private String toDataString(Map<String, Integer> outputMappingRule) {
        String[] tmpArr = new String[outputMappingRule.size()];
        for (Entry<String, Integer> entry : outputMappingRule.entrySet()) {
            tmpArr[entry.getValue()] = StringUtils.defaultString(data.get(entry.getKey()));
        }
        StringBuilder record = new StringBuilder();
        for (String headerKey : headerKeys) {
            record.append(data.get(headerKey)).append(BaseConstants.FIELD_SPLIT);
        }
        for (String e : tmpArr) {
            record.append(e).append(BaseConstants.FIELD_SPLIT);
        }
        record.delete(record.length() - 1, record.length());
        return record.toString();
    }

    public static SmcMessageParser parseObject(String original, MappingRule[] mappingRules,
            String[] outputKeys) throws Exception {
        if (StringUtils.isBlank(original)) {
            throw new Exception("input String is null!");
        }
        SmcMessageParser messageParser = new SmcMessageParser(original, mappingRules, outputKeys);
        messageParser.init();
        return messageParser;
    }

}
