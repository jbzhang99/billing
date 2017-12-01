package com.ai.baas.smc.check.topology.core.bolt;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Properties;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.BinaryPrefixComparator;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

import com.ai.baas.dshm.client.impl.CacheBLMapper;
import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.dshm.client.interfaces.IDshmClient;
import com.ai.baas.smc.check.topology.DAO.StlBillDataDAO;
import com.ai.baas.smc.check.topology.DAO.StlBillItemDataDAO;
import com.ai.baas.smc.check.topology.DAO.StlImportLogDAO;
import com.ai.baas.smc.check.topology.constants.SmcCacheConstant;
import com.ai.baas.smc.check.topology.constants.SmcCacheConstant.Dshm.FieldName;
import com.ai.baas.smc.check.topology.constants.SmcCacheConstant.ParamCode;
import com.ai.baas.smc.check.topology.constants.SmcCacheConstant.TypeCode;
import com.ai.baas.smc.check.topology.constants.SmcConstant;
import com.ai.baas.smc.check.topology.constants.SmcConstant.StlBillDetailStyleItem.IsSplitItem;
import com.ai.baas.smc.check.topology.constants.SmcExceptCodeConstant;
import com.ai.baas.smc.check.topology.constants.SmcHbaseConstant;
import com.ai.baas.smc.check.topology.constants.SmcHbaseConstant.FamilyName;
import com.ai.baas.smc.check.topology.util.LoadConfUtil;
import com.ai.baas.smc.check.topology.vo.StlBillData;
import com.ai.baas.smc.check.topology.vo.StlBillDetailStyleItem;
import com.ai.baas.smc.check.topology.vo.StlBillItemData;
import com.ai.baas.smc.check.topology.vo.StlImportLog;
import com.ai.baas.smc.check.topology.vo.StlPolicy;
import com.ai.baas.smc.check.topology.vo.StlSysParam;
import com.ai.baas.storm.duplicate.DuplicateCheckingConfig;
import com.ai.baas.storm.duplicate.DuplicateCheckingFromHBase;
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
import com.alibaba.fastjson.JSONArray;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * 对账<br>
 * Date: 2016年3月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author mayt
 */
public class BillDetailCheckBolt extends BaseBasicBolt {
    private static final Logger LOG = LoggerFactory.getLogger(BillDetailCheckBolt.class);

    private static final long serialVersionUID = -3214008757998306486L;

    private static final String noSplitKsy = "_no_split_";

    private transient ICacheClient policyCacheClient;

    private transient ICacheClient billStyleCacheClient;

    private transient ICacheClient calParamCacheClient;

    private transient ICacheClient countCacheClient;

    private transient ICacheClient sysParamCacheClient;

    private transient IDshmClient dshmClient;

    private String[] outputFields = new String[] { "data" };

    private MappingRule[] mappingRules = new MappingRule[2];

    private transient StlBillDataDAO stlBillDataDAO;

    private transient StlBillItemDataDAO stlBillItemDataDAO;

    private transient StlImportLogDAO importLogDAO;

    @SuppressWarnings("unchecked")
    @Override
    public void prepare(@SuppressWarnings("rawtypes")
    Map stormConf, TopologyContext context) {
        super.prepare(stormConf, context);
        LoadConfUtil.loadPaasConf(stormConf);
        JdbcProxy.loadResources(Arrays.asList(BaseConstants.JDBC_DEFAULT), stormConf);
        if (policyCacheClient == null) {
            policyCacheClient = MCSClientFactory
                    .getCacheClient(SmcCacheConstant.NameSpace.POLICY_CACHE);
        }
        if (billStyleCacheClient == null) {
            billStyleCacheClient = MCSClientFactory
                    .getCacheClient(SmcCacheConstant.NameSpace.BILL_STYLE_CACHE);
        }
        if (calParamCacheClient == null) {
            calParamCacheClient = MCSClientFactory.getCacheClient(CacheBLMapper.CACHE_BL_CAL_PARAM);
        }
        if (countCacheClient == null) {
            countCacheClient = MCSClientFactory
                    .getCacheClient(SmcCacheConstant.NameSpace.CHECK_COUNT_CACHE);
        }
        if (sysParamCacheClient == null) {
            sysParamCacheClient = MCSClientFactory
                    .getCacheClient(SmcCacheConstant.NameSpace.SYS_PARAM_CACHE);
        }
        if (dshmClient == null) {
            dshmClient = new DshmClient();
        }
        mappingRules[0] = MappingRule.getMappingRule(MappingRule.FORMAT_TYPE_OUTPUT,
                BaseConstants.JDBC_DEFAULT);
        mappingRules[1] = mappingRules[0];
        /* 3.初始化hbase */
        HBaseProxy.loadResource(stormConf);
        if (stlBillDataDAO == null) {
            stlBillDataDAO = new StlBillDataDAO();
        }
        if (stlBillItemDataDAO == null) {
            stlBillItemDataDAO = new StlBillItemDataDAO();
        }
        if (importLogDAO == null) {
            importLogDAO = new StlImportLogDAO();
        }

        DuplicateCheckingConfig.getInstance();
        FailBillHandler.startup();
    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        Map<String, String> data = null;
        try {
            String inputData = input.getString(0);
            LOG.info(" ====== 开始执行对账bolt，inputData = [" + inputData + "]");
            /* 1.获取并解析输入信息 */
            MessageParser messageParser = MessageParser.parseObject(inputData, mappingRules,
                    outputFields);
            data = messageParser.getData();
            LOG.info("对账bolt格式化数据 data = [" + data + "]");
            String tenantId = data.get(SmcHbaseConstant.ColumnName.TENANT_ID);
            String batchNo = data.get(SmcHbaseConstant.ColumnName.BATCH_NO);
            String totalRecord = data.get(SmcHbaseConstant.ColumnName.TOTAL_RECORD);
            String orderId = data.get(SmcHbaseConstant.ColumnName.ORDER_ID);
            String feeItemId3pl = data.get(SmcHbaseConstant.ColumnName.FEE_ITEM_ID);
            String itemFee3pl = data.get(SmcHbaseConstant.ColumnName.ITEM_FEE);
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
            LOG.error(" ====== billTimeSn = " + billTimeSn);
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
            LOG.info("第三方账单ID billId3pl = " + billId3pl);
            // 1， 根据第三方账单的租户、政策编码、结算账期、结算方加载本系统结算算费结果帐单数据
            stlBillDataQuery = new StlBillData();
            stlBillDataQuery.setTenantId(tenantId);
            stlBillDataQuery.setPolicyCode(policyCode);
            stlBillDataQuery.setBillTimeSn(billData3pl.getBillTimeSn());
            stlBillDataQuery.setStlElementSn(billData3pl.getStlElementSn());
            stlBillDataQuery.setBillFrom(SmcConstant.StlBillData.BillFrom.SYS);
            stlBillDatas = stlBillDataDAO.query(
                    JdbcProxy.getConnection(BaseConstants.JDBC_DEFAULT), yyyyMm, stlBillDataQuery);
            StlBillData billDataSys = stlBillDatas.get(0);// 本系统账单
            Long billIdSys = billDataSys.getBillId();// 本系统账单ID
            LOG.info("本系统账单ID billIdSys = " + billIdSys);
            // 查询政策信息
            StringBuilder key = new StringBuilder();
            key.append(tenantId).append(".").append(policyCode);
            String policyStr = policyCacheClient.hget(SmcCacheConstant.NameSpace.POLICY_CACHE,
                    key.toString());
            if (StringUtil.isBlank(policyStr)) {
                throw new BusinessException(SmcExceptCodeConstant.BUSINESS_EXCEPTION, "政策["
                        + policyCode + "]不存在");
            }
            StlPolicy stlPolicy = JSON.parseObject(policyStr, StlPolicy.class);
            // 查询详单项配置
            StringBuilder keyStringBuilder = new StringBuilder();
            keyStringBuilder.append(tenantId).append(SmcCacheConstant.CACHE_KEY_SPLIT)
                    .append(stlPolicy.getBillStyleSn()).append(SmcCacheConstant.CACHE_KEY_SPLIT)
                    .append(SmcCacheConstant.BILL_DETAIL_ITEM);
            String cacheStr = billStyleCacheClient.hget(
                    SmcCacheConstant.NameSpace.BILL_STYLE_CACHE, keyStringBuilder.toString());
            if (StringUtil.isBlank(cacheStr)) {
                throw new SystemException("账单样式编码[" + stlPolicy.getBillStyleSn() + "]详单项配置不存在");
            }
            List<StlBillDetailStyleItem> billDetailStyleItems = JSON.parseArray(cacheStr,
                    StlBillDetailStyleItem.class);
            // 3， 根据详单项配置解析本详单数据；(开头已解析)

            // 4， 根据本详单的租户、流水号、政策编码、账期获取本系统结算算费结果详单数据
            // KEY:租户ID_账单ID_账期ID_数据对象_账单来源_流水ID
            String rowKey = new StringBuilder().append(tenantId)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT).append(billIdSys)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT).append(billTimeSn)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT).append(objectId)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT)
                    .append(SmcConstant.StlBillData.BillFrom.SYS)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT).append(orderId).toString();
            RowFilter rowFilter = new RowFilter(CompareOp.EQUAL, new BinaryComparator(
                    rowKey.getBytes()));
            Scan scan = new Scan();
            scan.setFilter(rowFilter);
            Table tableBillDetailData = HBaseProxy.getConnection().getTable(
                    TableName.valueOf(SmcHbaseConstant.TableName.STL_BILL_DETAIL_DATA_ + yyyyMm));
            ResultScanner resultScanner = tableBillDetailData.getScanner(scan);
            Result result = resultScanner.next();

            String feeItemIdSys = null;
            String itemFeeSys = "0";
            NavigableMap<byte[], byte[]> billDetailDataSysMap = null;
            if (result != null) {
                billDetailDataSysMap = result.getFamilyMap(SmcHbaseConstant.FamilyName.COLUMN_DEF
                        .getBytes());
                feeItemIdSys = new String(
                        billDetailDataSysMap.get(SmcHbaseConstant.ColumnName.FEE_ITEM_ID.getBytes()) == null ? new byte[0]
                                : billDetailDataSysMap.get(SmcHbaseConstant.ColumnName.FEE_ITEM_ID
                                        .getBytes()));
                itemFeeSys = new String(
                        billDetailDataSysMap.get(SmcHbaseConstant.ColumnName.ITEM_FEE.getBytes()));

            }
            // 5， 如果不存在此流水或此流水对应的科目金额不一致，
            // 向详单差异表中插入此差异详单表（stl_bill_detail_diff_data_yyyymm）
            if (StringUtil.isBlank(feeItemIdSys) || !feeItemId3pl.equals(feeItemIdSys)
                    || Long.parseLong(itemFee3pl) - Double.parseDouble(itemFeeSys) != 0) {
                String diffFee = itemFee3pl;
                String checkStateDesc = SmcConstant.StlBillDetailDiffData.CheckStateDesc.NOT_FIND_SYS;
                if (!StringUtil.isBlank(feeItemIdSys)) {
                    diffFee = String.valueOf(Long.parseLong(itemFee3pl)
                            - Double.parseDouble(itemFeeSys));
                    checkStateDesc = SmcConstant.StlBillDetailDiffData.CheckStateDesc.DIFF_FEE;
                }
                // 查询第三方详单
                // KEY:租户ID_账单ID_账期ID_数据对象_账单来源_流水ID
                rowKey = new StringBuilder().append(tenantId).append(SmcHbaseConstant.ROWKEY_SPLIT)
                        .append(billId3pl).append(SmcHbaseConstant.ROWKEY_SPLIT).append(billTimeSn)
                        .append(SmcHbaseConstant.ROWKEY_SPLIT).append(objectId)
                        .append(SmcHbaseConstant.ROWKEY_SPLIT)
                        .append(SmcConstant.StlBillData.BillFrom.IMPORT)
                        .append(SmcHbaseConstant.ROWKEY_SPLIT).append(orderId).toString();
                rowFilter = new RowFilter(CompareOp.EQUAL, new BinaryComparator(rowKey.getBytes()));
                scan = new Scan();
                scan.setFilter(rowFilter);
                resultScanner = tableBillDetailData.getScanner(scan);
                result = resultScanner.next();
                if (result == null) {
                    throw new BusinessException(SmcExceptCodeConstant.BUSINESS_EXCEPTION,
                            "第三方详单数据不存在[rowKey:" + rowKey + "]");
                }
                // 写入差异表
                NavigableMap<byte[], byte[]> map = result
                        .getFamilyMap(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes());
                rowKey = new String(map.get(SmcHbaseConstant.ColumnName.STL_ORDER_DATA_KEY
                        .getBytes()));
                Put put = new Put(rowKey.getBytes());
                while (true) {
                    Entry<byte[], byte[]> entry = map.pollFirstEntry();
                    if (entry == null) {
                        break;
                    }
                    put.addColumn(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes(),
                            entry.getKey(), entry.getValue());
                }
                put.addColumn(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes(),
                        SmcHbaseConstant.ColumnName.DIFF_FEE.getBytes(), diffFee.getBytes());
                put.addColumn(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes(),
                        SmcHbaseConstant.ColumnName.CHECK_STATE.getBytes(),
                        SmcConstant.StlBillDetailDiffData.CheckState.DIFF.getBytes());
                put.addColumn(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes(),
                        SmcHbaseConstant.ColumnName.CHECK_STATE_DESC.getBytes(),
                        checkStateDesc.getBytes());
                Table tableBillDetailDiffData = HBaseProxy.getConnection().getTable(
                        TableName.valueOf(SmcHbaseConstant.TableName.STL_BILL_DETAIL_DIFF_DATA_
                                + yyyyMm));
                // 若两方的详单都存在，将本系统特有的字段拷贝到差异表
                if (billDetailDataSysMap != null) {
                    for (Entry<byte[], byte[]> entry : billDetailDataSysMap.entrySet()) {
                        if (!put.has(FamilyName.COLUMN_DEF.getBytes(), entry.getKey())) {
                            put.addColumn(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes(),
                                    entry.getKey(), entry.getValue());
                        }
                    }
                }

                tableBillDetailDiffData.put(put);
            }

            /* 查重 */
            DuplicateCheckingFromHBase checking = new DuplicateCheckingFromHBase();
            if (!checking.checkData(data)) {
                throw new BusinessException(SmcExceptCodeConstant.FAIL_CODE_DUP, "重复流水");
            }
            // 6， 本账单对账次数加1（redis），
            String countKey = "billdata_" + tenantId + "_" + batchNo + "_records";
            // Long countRecord = 10l;
            Long countRecord = countCacheClient.incr(countKey);
            LOG.info("对账次数累加器key = " + countKey);
            LOG.info("对账次数累加器value = " + countRecord);
            // 如果对账次数＝第三方账单详单记录数，则说明第三方详单都已对账完成：
            if (Long.parseLong(totalRecord) != countRecord.longValue()) {
                return;
            }
            // a) 查询本系统结算算费结果详单，查询本系统存在记录，而第三方详单不存在的记录，把
            // 些记录插入差异详单表（stl_bill_detail_diff_data_yyyymm）
            // KEY:租户ID_账单ID_账期ID_数据对象_账单来源_流水ID
            key = new StringBuilder().append(tenantId).append(SmcHbaseConstant.ROWKEY_SPLIT)
                    .append(billIdSys).append(SmcHbaseConstant.ROWKEY_SPLIT).append(billTimeSn)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT).append(objectId)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT)
                    .append(SmcConstant.StlBillData.BillFrom.SYS);
            LOG.info("@@@@@@@@@@@@@@@sys的key值为 = " + key);
            rowFilter = new RowFilter(CompareOp.EQUAL, new BinaryPrefixComparator(key.toString()
                    .getBytes()));
            scan = new Scan();
            scan.setFilter(rowFilter);
            scan.setCaching(500);
            resultScanner = tableBillDetailData.getScanner(scan);
            for (Result resultTmp : resultScanner) {
                NavigableMap<byte[], byte[]> map = resultTmp
                        .getFamilyMap(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes());
                String orderIdTmp = new String(map.get(SmcHbaseConstant.ColumnName.ORDER_ID
                        .getBytes()));
                // 查询第三方详单
                rowKey = new StringBuilder().append(tenantId).append(SmcHbaseConstant.ROWKEY_SPLIT)
                        .append(billId3pl).append(SmcHbaseConstant.ROWKEY_SPLIT).append(billTimeSn)
                        .append(SmcHbaseConstant.ROWKEY_SPLIT).append(objectId)
                        .append(SmcHbaseConstant.ROWKEY_SPLIT)
                        .append(SmcConstant.StlBillData.BillFrom.IMPORT)
                        .append(SmcHbaseConstant.ROWKEY_SPLIT).append(orderIdTmp).toString();
                LOG.info("@@@@@@@@@@@@@@@3pl的key值为 = " + rowKey);
                rowFilter = new RowFilter(CompareOp.EQUAL, new BinaryComparator(rowKey.getBytes()));
                scan = new Scan();
                scan.setFilter(rowFilter);
                ResultScanner resultScannerTmp = tableBillDetailData.getScanner(scan);
                Result result3pl = resultScannerTmp.next();
                LOG.info("@@@@@@@@@@@@@@@@@@result3pl = " + result3pl);
                if (result3pl == null) {
                    // 插入差异表
                    LOG.info("@@@@@@@@@@@@@@@@@@开始插入差异表");
                    String itemFeeTmp = new String(map.get(SmcHbaseConstant.ColumnName.ITEM_FEE
                            .getBytes()));
                    String row = new String(resultTmp.getRow());
                    LOG.info("@@@@@@@@@@@@@@@@@@插入差异表rowKey为：" + row);
                    Put put = new Put(row.getBytes());
                    while (true) {
                        Entry<byte[], byte[]> entry = map.pollFirstEntry();
                        if (entry == null) {
                            break;
                        }
                        put.addColumn(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes(),
                                entry.getKey(), entry.getValue());
                    }
                    put.addColumn(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes(),
                            SmcHbaseConstant.ColumnName.DIFF_FEE.getBytes(), itemFeeTmp.getBytes());
                    put.addColumn(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes(),
                            SmcHbaseConstant.ColumnName.CHECK_STATE.getBytes(),
                            SmcConstant.StlBillDetailDiffData.CheckState.DIFF.getBytes());
                    put.addColumn(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes(),
                            SmcHbaseConstant.ColumnName.CHECK_STATE_DESC.getBytes(),
                            SmcConstant.StlBillDetailDiffData.CheckStateDesc.NOT_FIND_3PL
                                    .getBytes());
                    Table tableBillDetailDiffData = HBaseProxy.getConnection().getTable(
                            TableName.valueOf(SmcHbaseConstant.TableName.STL_BILL_DETAIL_DIFF_DATA_
                                    + yyyyMm));
                    LOG.info("@@@@@@@@@@@@@@@@@@插入差异表值为：" + put);
                    tableBillDetailDiffData.put(put);
                }
            }
            // b) 修改账单数据表（第三方账单和本系统结算算费结果帐单）中的对账结果（差异金额为0则沉淀状态为账单一致，否则沉淀状态为有差异）。
            // 7， 如果对账结果为有差异，则调用对账错误详单文件生成方法，生成错误详单文件，并向账详单处理结果文件清单表新增记录。
            // 生成文件
            createFile(billData3pl, billDataSys, objectId, batchNo, billDetailStyleItems,
                    importLogMap, totalRecord);
        } catch (BusinessException e) {
            LOG.error("详单对账bolt出现异常", e);
            FailBillHandler.addFailBillMsg(data, SmcConstant.BILL_DETAIL_CHECK_BOLT,
                    e.getErrorCode(), e.getErrorMessage());
        } catch (Exception e) {
            LOG.error("详单对账bolt出现异常", e);
            FailBillHandler.addFailBillMsg(data, SmcConstant.BILL_DETAIL_CHECK_BOLT,
                    SmcExceptCodeConstant.SYSTEM_EXCEPTION, e.getMessage());
        } finally {

        }

    }

    private void createFile(StlBillData billData3pl, StlBillData billDataSys, String objectId,
            String batchNo, List<StlBillDetailStyleItem> billDetailStyleItems,
            Map<String, String> importLogMap, String totalRecordDetail) {
        String tenantId = billData3pl.getTenantId();
        Long billId3pl = billData3pl.getBillId();
        Long billIdSys = billDataSys.getBillId();
        String billTimeSn = billData3pl.getBillTimeSn();
        String yyyyMm = StringUtil.restrictLength(billTimeSn, 6);
        int totalRecord = 0;
        // 1. 根据租户ID、账期月份、账单ID查询账单表及账单科目汇总表，获取账单信息；
        StlBillItemData stlBillItemDataQuery = new StlBillItemData();
        stlBillItemDataQuery.setTenantId(tenantId);
        stlBillItemDataQuery.setBillId(billId3pl);
        List<StlBillItemData> stlBillItemDatas;
        try {
            try {
                stlBillItemDatas = stlBillItemDataDAO.query(
                        JdbcProxy.getConnection(BaseConstants.JDBC_DEFAULT), yyyyMm,
                        stlBillItemDataQuery);
            } catch (Exception e) {
                throw new SystemException(e);
            }

            // 2. 根据账单模板生成账单excel文件(文件名：ERR_租户ID_结算方ID 政策编码_账期_账单.xlsx)；
            LOG.info("开始生成账单文件...");
            Workbook wb = new XSSFWorkbook();

            XSSFCellStyle cellStyle = (XSSFCellStyle) wb.createCellStyle();
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());

            XSSFSheet sheet0 = (XSSFSheet) wb.createSheet("账单");
            XSSFRow row0 = sheet0.createRow(0);// 第一行
            XSSFCell cell = row0.createCell(0);
            cell.setCellValue("结算方");
            cell.setCellStyle(cellStyle);
            cell = row0.createCell(1);
            cell.setCellValue(billData3pl.getStlElementSn());
            cell = row0.createCell(2);
            cell.setCellValue("批次号");
            cell.setCellStyle(cellStyle);
            cell = row0.createCell(3);
            cell.setCellValue(billData3pl.getBatchNo());

            XSSFRow row1 = sheet0.createRow(1);// 第二行
            cell = row1.createCell(0);
            cell.setCellValue("政策编码");
            cell.setCellStyle(cellStyle);
            cell = row1.createCell(1);
            cell.setCellValue(billData3pl.getPolicyCode());
            cell = row1.createCell(2);
            cell.setCellValue("账期");
            cell.setCellStyle(cellStyle);
            cell = row1.createCell(3);
            cell.setCellValue(billData3pl.getBillTimeSn());

            XSSFRow row2 = sheet0.createRow(2);// 第三行
            cell = row2.createCell(0);
            cell.setCellValue("开始时间");
            cell.setCellStyle(cellStyle);
            cell = row2.createCell(1);
            cell.setCellValue(DateUtil.getDateString(billData3pl.getBillStartTime(),
                    DateUtil.DATE_FORMAT));
            cell = row2.createCell(2);
            cell.setCellValue("结束时间");
            cell.setCellStyle(cellStyle);
            cell = row2.createCell(3);
            cell.setCellValue(DateUtil.getDateString(billData3pl.getBillEndTime(),
                    DateUtil.DATE_FORMAT));

            XSSFRow row3 = sheet0.createRow(3);// 第四行
            cell = row3.createCell(0);
            cell.setCellValue("结算金额(元)");
            cell.setCellStyle(cellStyle);
            cell = row3.createCell(1);
            cell.setCellValue(billData3pl.getOrigFee() / 1000);
            cell = row3.createCell(2);
            cell.setCellValue("差异金额(元)");
            cell.setCellStyle(cellStyle);
            cell = row3.createCell(3);
            cell.setCellValue(billData3pl.getDiffFee() / 1000);

            XSSFRow row5 = sheet0.createRow(5);// 第六行
            cell = row5.createCell(0);
            cell.setCellValue("科目ID");
            cell.setCellStyle(cellStyle);
            cell = row5.createCell(1);
            cell.setCellValue("科目名称");
            cell.setCellStyle(cellStyle);
            cell = row5.createCell(2);
            cell.setCellValue("总金额(元)");
            cell.setCellStyle(cellStyle);
            cell = row5.createCell(3);
            cell.setCellValue("差异金额(元)");
            cell.setCellStyle(cellStyle);

            int i = 6;
            for (StlBillItemData stlBillItemData : stlBillItemDatas) {
                XSSFRow rowTmp = sheet0.createRow(i);
                cell = rowTmp.createCell(0);
                cell.setCellValue(stlBillItemData.getFeeItemId());
                cell = rowTmp.createCell(1);
                cell.setCellValue(getSysParamDesc(stlBillItemData.getTenantId(),
                        TypeCode.STL_POLICY_ITEM_PLAN, ParamCode.FEE_ITEM,
                        stlBillItemData.getFeeItemId()));
                cell = rowTmp.createCell(2);
                cell.setCellValue(stlBillItemData.getTotalFee() / 1000);
                cell = rowTmp.createCell(3);
                cell.setCellValue(stlBillItemData.getDiffFee() / 1000);
                i++;
            }

            String excelFileName = "ERR_" + billData3pl.getTenantId() + "_"
                    + billData3pl.getStlElementSn() + "_" + billData3pl.getPolicyCode() + "_"
                    + billData3pl.getBillTimeSn() + "_BILL.xlsx";
            String tmpPath = System.getProperty("user.dir") + "/tmp/" + billData3pl.getTenantId()
                    + billData3pl.getBillTimeSn() + "/"
                    + DateUtil.getDateString(DateUtil.YYYYMMDDHHMMSS);
            LOG.info("excelFileName = " + excelFileName);

            File file = new File(tmpPath);
            if (!file.exists()) {
                if (file.mkdirs()) {

                }
            }
            FileOutputStream fileOut = null;
            fileOut = new FileOutputStream(tmpPath + "/" + excelFileName);
            wb.write(fileOut);
            wb.close();
            // 3. 生成详单文件（文件名：ERR_租户ID_结算方ID 政策编码_账期_详单_序号.cvs)）
            LOG.info("开始生成详单文件...");
            // 判断是否需要拆分详单文件
            String splitItemCode = this.getAplitItemCode(billDetailStyleItems);
            // 取差异详单
            // KEY:租户ID_账单ID_账期ID_数据对象_账单来源_流水ID_主键ID
            // 第三方差异详单
            String rowKey = new StringBuilder().append(tenantId)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT).append(billId3pl)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT).append(billTimeSn)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT).append(objectId)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT)
                    .append(SmcConstant.StlBillData.BillFrom.IMPORT)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT).toString();

            RowFilter rowFilter = new RowFilter(CompareOp.EQUAL, new BinaryPrefixComparator(
                    rowKey.getBytes()));
            Scan scan = new Scan();
            scan.setFilter(rowFilter);

            Table tableBillDetailDiffData = HBaseProxy.getConnection().getTable(
                    TableName.valueOf(SmcHbaseConstant.TableName.STL_BILL_DETAIL_DIFF_DATA_
                            + yyyyMm));
            ResultScanner resultScanner3pl = tableBillDetailDiffData.getScanner(scan);
            // 本系统差异详单
            rowKey = new StringBuilder().append(tenantId).append(SmcHbaseConstant.ROWKEY_SPLIT)
                    .append(billIdSys).append(SmcHbaseConstant.ROWKEY_SPLIT).append(billTimeSn)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT).append(objectId)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT)
                    .append(SmcConstant.StlBillData.BillFrom.SYS)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT).toString();

            rowFilter = new RowFilter(CompareOp.EQUAL,
                    new BinaryPrefixComparator(rowKey.getBytes()));
            scan = new Scan();
            scan.setFilter(rowFilter);

            ResultScanner resultScannerSys = tableBillDetailDiffData.getScanner(scan);
            // 保存所有的差异记录
            Map<String, List<NavigableMap<byte[], byte[]>>> totalMap = new HashMap<String, List<NavigableMap<byte[], byte[]>>>();
            // 不拆分文件
            if (StringUtil.isBlank(splitItemCode)) {
                for (Result result : resultScanner3pl) {
                    totalRecord++;
                    NavigableMap<byte[], byte[]> navigableMap = result
                            .getFamilyMap(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes());
                    List<NavigableMap<byte[], byte[]>> list = new ArrayList<NavigableMap<byte[], byte[]>>();
                    list.add(navigableMap);
                    totalMap.put(noSplitKsy, list);
                }
                for (Result result : resultScannerSys) {
                    totalRecord++;
                    NavigableMap<byte[], byte[]> navigableMap = result
                            .getFamilyMap(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes());
                    if (totalMap.containsKey(noSplitKsy)) {
                        List<NavigableMap<byte[], byte[]>> list = totalMap.get(noSplitKsy);
                        list.add(navigableMap);
                    } else {
                        List<NavigableMap<byte[], byte[]>> list = new ArrayList<NavigableMap<byte[], byte[]>>();
                        list.add(navigableMap);
                        totalMap.put(noSplitKsy, list);
                    }
                }
            } else {// 拆分文件

                for (Result result : resultScanner3pl) {
                    totalRecord++;
                    NavigableMap<byte[], byte[]> navigableMap = result
                            .getFamilyMap(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes());
                    String splitKey;
                    if (navigableMap.containsKey(splitItemCode.getBytes())
                            && navigableMap.get(splitItemCode.getBytes()) != null) {
                        splitKey = new String(navigableMap.get(splitItemCode.getBytes()));
                    } else {
                        splitKey = "null";
                    }
                    if (totalMap.containsKey(splitKey)) {
                        List<NavigableMap<byte[], byte[]>> list = totalMap.get(splitKey);
                        list.add(navigableMap);
                    } else {
                        List<NavigableMap<byte[], byte[]>> list = new ArrayList<NavigableMap<byte[], byte[]>>();
                        list.add(navigableMap);
                        totalMap.put(splitKey, list);
                    }
                }
                for (Result result : resultScannerSys) {
                    totalRecord++;
                    NavigableMap<byte[], byte[]> navigableMap = result
                            .getFamilyMap(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes());
                    String splitKey;
                    if (navigableMap.containsKey(splitItemCode.getBytes())
                            && navigableMap.get(splitItemCode.getBytes()) != null) {
                        splitKey = new String(navigableMap.get(splitItemCode.getBytes()));
                    } else {
                        splitKey = "null";
                    }
                    if (totalMap.containsKey(splitKey)) {
                        List<NavigableMap<byte[], byte[]>> list = totalMap.get(splitKey);
                        list.add(navigableMap);
                    } else {
                        List<NavigableMap<byte[], byte[]>> list = new ArrayList<NavigableMap<byte[], byte[]>>();
                        list.add(navigableMap);
                        totalMap.put(splitKey, list);
                    }
                }

            }
            // 拆分字段翻译
            if (!StringUtil.isBlank(splitItemCode)) {
                Map<String, List<NavigableMap<byte[], byte[]>>> totalMapTmp = new HashMap<String, List<NavigableMap<byte[], byte[]>>>();
                for (Entry<String, List<NavigableMap<byte[], byte[]>>> entry : totalMap.entrySet()) {
                    String splitName = getSysParamDesc(tenantId, TypeCode.BILL_DETAIL_STYLE_ITEM,
                            ParamCode.SPLIT_ITEM_VALUE, entry.getKey());
                    if (totalMapTmp.containsKey(splitName)) {
                        List<NavigableMap<byte[], byte[]>> list = totalMapTmp.get(splitName);
                        list.addAll(entry.getValue());
                        totalMapTmp.put(splitName, list);
                    } else {
                        totalMapTmp.put(splitName, entry.getValue());
                    }
                }
                totalMap = totalMapTmp;
            }

            for (Entry<String, List<NavigableMap<byte[], byte[]>>> entry : totalMap.entrySet()) {
                int sort = 0;
                String splitKey = entry.getKey();
                if (noSplitKsy.equals(splitKey)) {
                    splitKey = "";
                }
                List<NavigableMap<byte[], byte[]>> navigableMaps = entry.getValue();
                boolean hasNext = true;
                while (hasNext) {
                    sort++;

                    wb = new XSSFWorkbook();

                    cellStyle = (XSSFCellStyle) wb.createCellStyle();
                    cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                    cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());

                    sheet0 = (XSSFSheet) wb.createSheet("详单");
                    row0 = sheet0.createRow(0);// 第一行
                    cell = row0.createCell(0);
                    cell.setCellValue("批次号");
                    cell.setCellStyle(cellStyle);
                    cell = row0.createCell(1);
                    cell.setCellValue(batchNo);
                    cell = row0.createCell(2);
                    cell.setCellValue("总数量");
                    cell = row0.createCell(3);
                    cell.setCellValue(totalRecord);
                    cell = row0.createCell(4);
                    cell.setCellValue("本文件记录数");
                    cell = row0.createCell(5);
                    if (sort <= navigableMaps.size() / 50000) {
                        cell.setCellValue("50000");
                    } else {
                        cell.setCellValue(String.valueOf(navigableMaps.size() % 50000));
                    }
                    row1 = sheet0.createRow(1);// 第二行
                    i = 0;
                    for (StlBillDetailStyleItem billStyleItem : billDetailStyleItems) {
                        cell = row1.createCell(i++);
                        cell.setCellValue(billStyleItem.getItemTitle());
                    }
                    cell = row1.createCell(i++);
                    cell.setCellValue("对账结果");
                    cell = row1.createCell(i++);
                    cell.setCellValue("差异金额(元)");
                    cell = row1.createCell(i++);
                    cell.setCellValue("差异说明");

                    int count = 0;// 当前文件条数
                    while (count < 50000) {
                        if (((sort - 1) * 50000 + count) >= navigableMaps.size()) {
                            hasNext = false;
                            break;
                        }
                        XSSFRow row = sheet0.createRow(count + 2);
                        NavigableMap<byte[], byte[]> map = navigableMaps.get((sort - 1) * 50000
                                + count);
                        i = 0;
                        for (StlBillDetailStyleItem billStyleItem : billDetailStyleItems) {
                            String value = "";
                            if (SmcHbaseConstant.ColumnName.ITEM_FEE.equals(billStyleItem
                                    .getItemCode())) {
                                value = String.valueOf(Float.parseFloat(new String(map
                                        .get(billStyleItem.getItemCode().getBytes()))) / 1000);
                            } else {
                                value = new String(
                                        map.get(billStyleItem.getItemCode().getBytes()) == null ? new byte[1]
                                                : map.get(billStyleItem.getItemCode().getBytes()));
                            }
                            cell = row.createCell(i++);
                            cell.setCellValue(value);
                        }
                        String checkStateNum = new String(
                                map.get(SmcHbaseConstant.ColumnName.CHECK_STATE.getBytes()));
                        String checkState = "";
                        if (checkStateNum.equals("1")) {
                            checkState = "一致";
                        } else if (checkStateNum.equals("2")) {
                            checkState = "不一致";
                        } else {
                            checkState = "结果错误";
                        }
                        cell = row.createCell(i++);
                        cell.setCellValue(checkState);
                        String diffFee = new String((map.get(SmcHbaseConstant.ColumnName.DIFF_FEE
                                .getBytes())));

                        cell = row.createCell(i++);
                        cell.setCellValue(String.valueOf(Double.parseDouble(diffFee) / 1000));
                        cell = row.createCell(i++);
                        cell.setCellValue(new String(map
                                .get(SmcHbaseConstant.ColumnName.CHECK_STATE_DESC.getBytes())));
                        count++;
                    }
                    String detailFileName = "ERR_" + billData3pl.getTenantId() + "_"
                            + billData3pl.getStlElementSn() + "_" + billData3pl.getPolicyCode()
                            + "_" + billData3pl.getBillTimeSn() + "_BILL_DETAIL_" + splitKey + "_"
                            + sort + ".xlsx";
                    LOG.info("detailFileName = " + detailFileName);
                    file = new File(tmpPath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    fileOut = null;
                    fileOut = new FileOutputStream(tmpPath + "/" + detailFileName);
                    wb.write(fileOut);
                    wb.close();
                }
            }

            // 生成zip文件
            // 文件名：ERR_租户ID_结算方ID _政策编码_账期_YYYYMMDDHHMISS.zip
            String targetName = "ERR_" + billData3pl.getTenantId() + "_"
                    + billData3pl.getStlElementSn() + "_" + billData3pl.getPolicyCode() + "_"
                    + billData3pl.getBillTimeSn() + "_"
                    + DateUtil.getDateString(DateUtil.YYYYMMDDHHMMSS) + ".zip";
            String pathRes = tmpPath;
            String targetPath = System.getProperty("user.dir") + "/tmpzip/"
                    + billData3pl.getTenantId() + billData3pl.getBillTimeSn();
            File resourcesFile = new File(pathRes); // 源文件
            File targetFile = new File(targetPath); // 目的
            // 如果目的路径不存在，则新建
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }

            FileOutputStream outputStream = new FileOutputStream(targetPath + "/" + targetName);
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(outputStream));
            createCompressedFile(out, resourcesFile, "");
            out.close();
            // 上传到ftp
            String url = getSysParams(tenantId, TypeCode.SFTP_CONF, ParamCode.UPLOAD_URL_DIFF_FILE)
                    .get(0).getColumnValue();
            String host = url.split(":")[0];
            int port = 22;
            String username = getSysParams(tenantId, TypeCode.SFTP_CONF, ParamCode.USER_NAME)
                    .get(0).getColumnValue();
            String password = getSysParams(tenantId, TypeCode.SFTP_CONF, ParamCode.PWD).get(0)
                    .getColumnValue();
            ChannelSftp sftp = null;
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            Session session = jsch.getSession(username, host, port);
            session.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            session.setConfig(sshConfig);
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            System.out.println("Connected to " + host + " success");
            String directory = url.split(":")[1];
            String uploadFile = targetPath + "/" + targetName;
            try {
                sftp.cd(directory);
            } catch (SftpException sException) {
                if (ChannelSftp.SSH_FX_NO_SUCH_FILE == sException.id) {
                    makeDir(directory, sftp);
                    sftp.cd(directory);
                }
            }

            File fileUploadZip = new File(uploadFile);
            LOG.info("压缩文件开始上传到服务器...");
            sftp.put(new FileInputStream(fileUploadZip), fileUploadZip.getName());
            sftp.disconnect();
            if (session != null) {
                if (session.isConnected()) {
                    session.disconnect();
                }
            }
            if (channel != null) {
                if (channel.isConnected()) {
                    channel.disconnect();
                }
            }
            LOG.info("压缩文件上传成功...");
            // 更新导入日志表
            StlImportLog importLog = new StlImportLog();
            importLog.setTenantId(importLogMap.get(SmcCacheConstant.Dshm.FieldName.TENANT_ID));
            importLog.setLogId(Long.parseLong(importLogMap
                    .get(SmcCacheConstant.Dshm.FieldName.LOG_ID)));
            importLog.setImportRecords(Long.parseLong(totalRecordDetail));
            importLog.setRstFileName(targetName);
            importLog.setRstFileUrl(url);
            importLog.setState(SmcConstant.StlImportLog.State.DATA_SUCCESS);
            importLog.setStateDesc("数据处理完成");
            importLogDAO.update(JdbcProxy.getConnection(BaseConstants.JDBC_DEFAULT), importLog);

        } catch (IOException e) {
            throw new SystemException(e);
        } catch (SftpException e) {
            throw new SystemException(e);
        } catch (JSchException e) {
            throw new SystemException(e);
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {
        }
    }

    private String getAplitItemCode(List<StlBillDetailStyleItem> billDetailStyleItems) {
        for (StlBillDetailStyleItem billDetailStyleItem : billDetailStyleItems) {
            if (IsSplitItem.YES.equals(billDetailStyleItem.getIsSplitItem())) {
                return billDetailStyleItem.getItemCode();
            }
        }
        return "";
    }

    private static void makeDir(String directory, ChannelSftp sftp) throws SftpException {
        System.out.println(directory);
        System.out.println(sftp.pwd());
        String parentPath = new File(directory).getParentFile().getPath().replace("\\", "/");
        if (parentPath.equals("/")) {
            sftp.mkdir(directory.substring(1));
        } else {
            try {
                sftp.cd(parentPath);
            } catch (SftpException sException) {
                if (ChannelSftp.SSH_FX_NO_SUCH_FILE == sException.id) {
                    makeDir(parentPath, sftp);
                }
            }
            sftp.mkdir(directory);
        }
    }

    void createCompressedFile(ZipOutputStream out, File file, String dir) {
        try {
            // 如果当前的是文件夹，则进行进一步处理
            if (file.isDirectory()) {
                // 得到文件列表信息
                File[] files = file.listFiles();
                // 将文件夹添加到下一级打包目录
                out.putNextEntry(new ZipEntry(dir + "/"));

                dir = dir.length() == 0 ? "" : dir + "/";

                // 循环将文件夹中的文件打包
                for (int i = 0; i < files.length; i++) {
                    createCompressedFile(out, files[i], dir + files[i].getName()); // 递归处理
                }
            } else { // 当前的是文件，打包处理
                // 文件输入流
                FileInputStream fis = new FileInputStream(file);

                out.putNextEntry(new ZipEntry(dir));
                // 进行写操作
                int j = 0;
                byte[] buffer = new byte[1024];
                while ((j = fis.read(buffer)) > 0) {
                    out.write(buffer, 0, j);
                }
                // 关闭输入流
                fis.close();
            }
        } catch (FileNotFoundException e) {
            throw new SystemException(e);
        } catch (IOException e) {
            throw new SystemException(e);
        } finally {

        }
    }

    List<StlSysParam> getSysParams(String tenantId, String typeCode, String paramCode) {
        StringBuilder key = new StringBuilder();
        key.append(tenantId);
        key.append(".");
        key.append(typeCode);
        key.append(".");
        key.append(paramCode);
        String data = sysParamCacheClient.hget(SmcCacheConstant.NameSpace.SYS_PARAM_CACHE,
                key.toString());
        if (StringUtil.isBlank(data)) {
            return null;
        }
        return JSONArray.parseArray(data, StlSysParam.class);
    }

    StlSysParam getSysParam(String tenantId, String typeCode, String paramCode, String columnValue) {
        StringBuilder key = new StringBuilder();
        key.append(tenantId);
        key.append(".");
        key.append(typeCode);
        key.append(".");
        key.append(paramCode);
        key.append(".");
        key.append(columnValue);
        String data = sysParamCacheClient.hget(SmcCacheConstant.NameSpace.SYS_PARAM_CACHE,
                key.toString());
        if (StringUtil.isBlank(data)) {
            return null;
        }
        return JSON.parseArray(data, StlSysParam.class).get(0);
    }

    String getSysParamDesc(String tenantId, String typeCode, String paramCode, String columnValue) {
        StlSysParam sysParam = getSysParam(tenantId, typeCode, paramCode, columnValue);
        return sysParam == null ? columnValue : sysParam.getColumnDesc();
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields(outputFields));
    }

}
