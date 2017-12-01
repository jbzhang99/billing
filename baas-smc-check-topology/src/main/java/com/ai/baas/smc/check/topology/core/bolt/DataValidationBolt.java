package com.ai.baas.smc.check.topology.core.bolt;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

import com.ai.baas.dshm.client.CacheFactoryUtil;
import com.ai.baas.dshm.client.impl.CacheBLMapper;
import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.dshm.client.interfaces.IDshmClient;
import com.ai.baas.smc.check.topology.DAO.StlBillDataDAO;
import com.ai.baas.smc.check.topology.constants.SmcCacheConstant;
import com.ai.baas.smc.check.topology.constants.SmcCacheConstant.Dshm.FieldName;
import com.ai.baas.smc.check.topology.constants.SmcConstant;
import com.ai.baas.smc.check.topology.constants.SmcExceptCodeConstant;
import com.ai.baas.smc.check.topology.constants.SmcHbaseConstant;
import com.ai.baas.smc.check.topology.util.LoadConfUtil;
import com.ai.baas.smc.check.topology.vo.StlBillData;
import com.ai.baas.smc.check.topology.vo.StlBillDetailStyleItem;
import com.ai.baas.smc.check.topology.vo.StlElement;
import com.ai.baas.smc.check.topology.vo.StlPolicy;
import com.ai.baas.storm.failbill.FailBillHandler;
import com.ai.baas.storm.jdbc.JdbcProxy;
import com.ai.baas.storm.message.MappingRule;
import com.ai.baas.storm.message.MessageParser;
import com.ai.baas.storm.util.BaseConstants;
import com.ai.baas.storm.util.HBaseProxy;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.alibaba.fastjson.JSON;

/**
 * 校验<br>
 * Date: 2016年3月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author mayt
 */
public class DataValidationBolt extends BaseBasicBolt {
    private static final long serialVersionUID = 8475030105476807164L;

    private static Logger LOG = LoggerFactory.getLogger(DataValidationBolt.class);

    private ICacheClient billStyleCacheClient;

    private ICacheClient elementCacheClient;

    private ICacheClient calParamCacheClient;

    private ICacheClient countCacheClient;

    private ICacheClient policyClient;

    private MappingRule[] mappingRules = new MappingRule[2];

    private String[] outputFields = new String[] { "data" };

    private IDshmClient dshmClient;

    private StlBillDataDAO stlBillDataDAO;

    @SuppressWarnings("unchecked")
    @Override
    public void prepare(@SuppressWarnings("rawtypes")
    Map stormConf, TopologyContext context) {
        LOG.info("数据校验bolt[prepare方法]...");
        super.prepare(stormConf, context);
        LoadConfUtil.loadPaasConf(stormConf);
        JdbcProxy.loadResources(Arrays.asList(BaseConstants.JDBC_DEFAULT), stormConf);
        if (billStyleCacheClient == null) {
            billStyleCacheClient = MCSClientFactory
                    .getCacheClient(SmcCacheConstant.NameSpace.BILL_STYLE_CACHE);
        }
        if (elementCacheClient == null) {
            elementCacheClient = MCSClientFactory
                    .getCacheClient(SmcCacheConstant.NameSpace.ELEMENT_CACHE);
        }
        if (calParamCacheClient == null) {
            calParamCacheClient = CacheFactoryUtil.getCacheClient(CacheBLMapper.CACHE_BL_CAL_PARAM);
        }
        if (countCacheClient == null) {
            countCacheClient = MCSClientFactory
                    .getCacheClient(SmcCacheConstant.NameSpace.CHECK_COUNT_CACHE);
        }
        if (policyClient == null) {
            policyClient = MCSClientFactory.getCacheClient(SmcCacheConstant.NameSpace.POLICY_CACHE);
        }
        /* 初始化hbase */
        HBaseProxy.loadResource(stormConf);
        /* 2.获取报文格式信息 */
        mappingRules[0] = MappingRule.getMappingRule(MappingRule.FORMAT_TYPE_OUTPUT,
                BaseConstants.JDBC_DEFAULT);
        mappingRules[1] = mappingRules[0];
        if (dshmClient == null) {
            dshmClient = new DshmClient();
        }
        if (stlBillDataDAO == null) {
            stlBillDataDAO = new StlBillDataDAO();
        }
        FailBillHandler.startup();
    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        Map<String, String> data = null;
        try {
            /* 接收输入报文 */
            String inputData = input.getString(0);
            LOG.info("数据校验bolt输入消息报文：[" + inputData + "]...");
            /* 解析报文 */
            MessageParser messageParser = null;
            messageParser = MessageParser.parseObject(inputData, mappingRules, outputFields);
            data = messageParser.getData();
            data.put(SmcHbaseConstant.ColumnName.ITEM_FEE, String.valueOf((long) (Float
                    .parseFloat(data.get(SmcHbaseConstant.ColumnName.ITEM_FEE)) * 1000)));
            String line = input.getStringByField(BaseConstants.RECORD_DATA);
            LOG.info("-------------------line==" + line);
            String tenantId = data.get(BaseConstants.TENANT_ID);
            String batchNo = data.get(SmcHbaseConstant.ColumnName.BATCH_NO);
            String orderId = data.get(SmcHbaseConstant.ColumnName.ORDER_ID);
            String itemFee = data.get(SmcHbaseConstant.ColumnName.ITEM_FEE);
            // 查询导入日志
            Map<String, String> params = new TreeMap<String, String>();
            params.put(SmcCacheConstant.Dshm.FieldName.TENANT_ID, tenantId);
            params.put(SmcCacheConstant.Dshm.FieldName.BATCH_NO, batchNo);
            List<Map<String, String>> results = dshmClient
                    .list(SmcCacheConstant.Dshm.TableName.STL_IMPORT_LOG).where(params)
                    .executeQuery(calParamCacheClient);
            if (CollectionUtil.isEmpty(results)) {
                throw new BusinessException(SmcExceptCodeConstant.BUSINESS_EXCEPTION, "导入日志不存在");
            }
            Map<String, String> importLogMap = results.get(0);// 导入日志map
            String billTimeSn = importLogMap.get(FieldName.BILL_TIME_SN);// 账期
            String yyyyMm = StringUtil.restrictLength(billTimeSn, 6);
            String objectId = importLogMap.get(FieldName.OBJECT_ID);// 数据对象
            // 查询第三方账单
            StlBillData stlBillDataQuery = new StlBillData();
            stlBillDataQuery.setTenantId(tenantId);
            stlBillDataQuery.setBatchNo(batchNo);
            stlBillDataQuery.setBillFrom(SmcConstant.StlBillData.BillFrom.IMPORT);
            stlBillDataQuery.setBillTimeSn(billTimeSn);
            List<StlBillData> stlBillDatas = stlBillDataDAO.query(
                    JdbcProxy.getConnection(BaseConstants.JDBC_DEFAULT), yyyyMm, stlBillDataQuery);
            StlBillData billData3pl = stlBillDatas.get(0);// 第三方账单
            String policyCode = billData3pl.getPolicyCode();// 政策编码
            Long billId3pl = billData3pl.getBillId();// 第三方账单ID
            // 根据政策编码获取账单格式定义
            String policyStr = policyClient.hget(SmcCacheConstant.NameSpace.POLICY_CACHE, tenantId
                    + "." + policyCode);
            if (StringUtil.isBlank(policyStr)) {
                throw new BusinessException(SmcExceptCodeConstant.BUSINESS_EXCEPTION, "政策编码["
                        + policyCode + "]定义不存在");
            }
            StlPolicy stlPolicy = JSON.parseObject(policyStr, StlPolicy.class);
            String billStyleSn = stlPolicy.getBillStyleSn();
            /* 获取此账单格式定义下的所有的详单项定义； */
            String billDetailStyleStr = billStyleCacheClient.hget(
                    SmcCacheConstant.NameSpace.BILL_STYLE_CACHE, tenantId + "." + billStyleSn + "."
                            + SmcCacheConstant.BILL_DETAIL_ITEM);
            if (StringUtil.isBlank(billDetailStyleStr)) {
                throw new BusinessException(SmcExceptCodeConstant.BUSINESS_EXCEPTION, "账单样式编码["
                        + billStyleSn + "]详单项定义不存在");
            }
            List<StlBillDetailStyleItem> stlBillDetailStyleItems = JSON.parseArray(
                    billDetailStyleStr, StlBillDetailStyleItem.class);
            /* 判断本详单数据是否满足详单项的格式定义（是否必填，取值类型）。 */
            String verifyState = SmcConstant.StlBillItemData.VerifyState.PASS;
            String verifyDesc = "通过";
            try {
                for (int i = 0; i < stlBillDetailStyleItems.size(); i++) {
                    StlBillDetailStyleItem stlBillDetailStyleItem = stlBillDetailStyleItems.get(i);
                    Long elementId = stlBillDetailStyleItem.getElementId();
                    if (elementId == null || elementId == 0) {
                        continue;
                    }
                    String key = new StringBuilder().append(stlBillDetailStyleItem.getTenantId())
                            .append(SmcCacheConstant.CACHE_KEY_SPLIT).append(elementId).toString();
                    String elementStr = elementCacheClient.hget(
                            SmcCacheConstant.NameSpace.ELEMENT_CACHE, key.toString());
                    if (StringUtil.isBlank(elementStr)) {
                        throw new BusinessException(SmcExceptCodeConstant.BUSINESS_EXCEPTION,
                                "元素ID[" + elementId + "]不存在");
                    }
                    StlElement stlElement = JSON.parseObject(elementStr, StlElement.class);
                    String value = data.get(stlBillDetailStyleItem.getItemCode());
                    if (SmcConstant.StlElement.IsNecessary.YES.equals(stlElement.getIsNecessary())) {
                        if (StringUtil.isBlank(value)) {
                            throw new BusinessException(SmcExceptCodeConstant.PARAM_IS_NULL, "["
                                    + stlBillDetailStyleItem.getItemCode() + "]不能为空");
                        }
                    }
                    if (!StringUtil.isBlank(value)) {
                        if (SmcConstant.StlElement.ValueType.DATE_TIME.equals(stlElement
                                .getValueType())) {
                            try {
                                DateUtil.getTimestamp(value, DateUtil.YYYYMMDDHHMMSS);
                            } catch (SystemException e) {
                                throw new BusinessException(
                                        SmcExceptCodeConstant.PARAM_TYPE_NOT_RIGHT, "["
                                                + stlBillDetailStyleItem.getItemCode() + "]格式不正确");
                            }
                        } else if (SmcConstant.StlElement.ValueType.ENUM.equals(stlElement
                                .getValueType())) {

                        } else if (SmcConstant.StlElement.ValueType.FLOAT.equals(stlElement
                                .getValueType())) {
                            try {
                                Float.parseFloat(value);
                            } catch (SystemException e) {
                                throw new BusinessException(
                                        SmcExceptCodeConstant.PARAM_TYPE_NOT_RIGHT, "["
                                                + stlBillDetailStyleItem.getItemCode() + "]格式不正确");
                            }
                        } else if (SmcConstant.StlElement.ValueType.INT.equals(stlElement
                                .getValueType())) {
                            try {
                                Integer.parseInt(value);
                            } catch (SystemException e) {
                                throw new BusinessException(
                                        SmcExceptCodeConstant.PARAM_TYPE_NOT_RIGHT, "["
                                                + stlBillDetailStyleItem.getItemCode() + "]格式不正确");
                            }
                        } else if (SmcConstant.StlElement.ValueType.STRING.equals(stlElement
                                .getValueType())) {

                        }
                    }
                }
            } catch (BusinessException e) {
                verifyState = SmcConstant.StlBillItemData.VerifyState.NOT_PASS;
                verifyDesc = e.getErrorMessage();
            }

            // 如果不满足则记录原因，沉淀数据库（在对应的详单数据记录后面增加一下校验结果【失败】及失败原因）。
            // 如果满足，则沉淀数据库（在对应的详单数据记录后面增加一下校验结果:通过）。
            // KEY:租户ID_账单ID_账期ID_数据对象_账单来源_流水ID
            String rowKey = new StringBuilder().append(tenantId)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT).append(billId3pl)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT).append(billTimeSn)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT).append(objectId)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT)
                    .append(SmcConstant.StlBillData.BillFrom.IMPORT)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT).append(orderId).toString();
            Put put = new Put(rowKey.getBytes());
            for (Entry<String, String> entry : data.entrySet()) {
                put.addColumn(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes(), entry.getKey()
                        .getBytes(), entry.getValue().getBytes());
            }
            put.addColumn(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes(),
                    SmcHbaseConstant.ColumnName.BILL_ID.getBytes(), Bytes.toBytes(billId3pl));
            put.addColumn(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes(),
                    SmcHbaseConstant.ColumnName.TENANT_ID.getBytes(), tenantId.getBytes());
            put.addColumn(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes(),
                    SmcHbaseConstant.ColumnName.OBJECT_ID.getBytes(), objectId.getBytes());
            put.addColumn(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes(),
                    SmcHbaseConstant.ColumnName.BILL_FROM.getBytes(),
                    SmcConstant.StlBillData.BillFrom.IMPORT.getBytes());
            put.addColumn(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes(),
                    SmcHbaseConstant.ColumnName.STL_ORDER_DATA_KEY.getBytes(), rowKey.getBytes());
            put.addColumn(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes(),
                    SmcHbaseConstant.ColumnName.ITEM_FEE.getBytes(), itemFee.getBytes());
            put.addColumn(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes(),
                    SmcHbaseConstant.ColumnName.VERIFY_STATE.getBytes(), verifyState.getBytes());
            put.addColumn(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes(),
                    SmcHbaseConstant.ColumnName.VERIFY_DESC.getBytes(), verifyDesc.getBytes());
            Table table = HBaseProxy.getConnection().getTable(
                    TableName.valueOf(SmcHbaseConstant.TableName.STL_BILL_DETAIL_DATA_ + yyyyMm));
            table.put(put);
            /* 更新结算详单校验计数器（redis）的校验通过记录数和校验失败记录数。 */
            String countKey;
            if (SmcConstant.StlBillItemData.VerifyState.PASS.equals(verifyState)) {
                countKey = "billdata_" + tenantId + "_" + batchNo + "_verify_success";
            } else {
                countKey = "billdata_" + tenantId + "_" + batchNo + "_verify_failure";
            }
            LOG.info("详单校验计数器 key:" + countKey);
            Long countTmp = countCacheClient.incr(countKey);
            LOG.info("详单校验计数器 value:" + countTmp);
            // 报文发送到下一环节
            List<Object> value = messageParser.toTupleData();
            if (!CollectionUtil.isEmpty(value)) {
                collector.emit(value);
            }
        } catch (BusinessException e) {
            LOG.error("数据校验bolt出现异常", e);
            FailBillHandler.addFailBillMsg(data, SmcConstant.DATA_VALIDATION_BOLT,
                    e.getErrorCode(), e.getErrorMessage());
        } catch (Exception e) {
            LOG.error("数据校验bolt出现异常", e);
            FailBillHandler.addFailBillMsg(data, SmcConstant.DATA_VALIDATION_BOLT,
                    SmcExceptCodeConstant.SYSTEM_EXCEPTION, e.getMessage());
        } finally {

        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields(outputFields));

    }
}
