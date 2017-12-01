package com.ai.baas.smc.check.topology.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.BinaryPrefixComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;

import com.ai.baas.dshm.client.CacheFactoryUtil;
import com.ai.baas.dshm.client.impl.CacheBLMapper;
import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.smc.check.topology.constants.SmcCacheConstant;
import com.ai.baas.smc.check.topology.constants.SmcConstant;
import com.ai.baas.smc.check.topology.constants.SmcExceptCodeConstant;
import com.ai.baas.smc.check.topology.constants.SmcHbaseConstant;
import com.ai.baas.smc.check.topology.constants.SmcCacheConstant.Dshm.FieldName;
import com.ai.baas.storm.util.HBaseProxy;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;

public class TestTopology {

    public static void main(String[] args) throws IOException {
        String tenantId = "MVNE";
        String billTimeSn = "201604";
        Long billIdSys = 1455L;
        String objectId = "sms";
        StringBuilder key = new StringBuilder().append(tenantId)
                .append(SmcHbaseConstant.ROWKEY_SPLIT).append(billIdSys)
                .append(SmcHbaseConstant.ROWKEY_SPLIT).append(billTimeSn)
                .append(SmcHbaseConstant.ROWKEY_SPLIT).append(objectId)
                .append(SmcHbaseConstant.ROWKEY_SPLIT).append(SmcConstant.StlBillData.BillFrom.SYS);
        RowFilter rowFilter = new RowFilter(CompareOp.EQUAL, new BinaryPrefixComparator(key
                .toString().getBytes()));
        Scan scan = new Scan();
        scan.setFilter(rowFilter);
        String yyyyMm = "201604";
        Table tableBillDetailData = HBaseProxyTest.getConnection().getTable(
                TableName.valueOf(SmcHbaseConstant.TableName.STL_BILL_DETAIL_DATA_ + yyyyMm));
        ResultScanner resultScanner = tableBillDetailData.getScanner(scan);
        for (Result resultTmp : resultScanner) {
            NavigableMap<byte[], byte[]> map = resultTmp
                    .getFamilyMap(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes());
            String orderIdTmp = new String(map.get(SmcHbaseConstant.ColumnName.ORDER_ID.getBytes()));
            Long billId3pl = 1203L;
            // 查询第三方详单
            String rowKey = new StringBuilder().append(tenantId)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT).append(billId3pl)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT).append(billTimeSn)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT).append(objectId)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT)
                    .append(SmcConstant.StlBillData.BillFrom.IMPORT)
                    .append(SmcHbaseConstant.ROWKEY_SPLIT).append(orderIdTmp).toString();
            rowFilter = new RowFilter(CompareOp.EQUAL, new BinaryComparator(rowKey.getBytes()));
            scan = new Scan();
            scan.setFilter(rowFilter);
            ResultScanner resultScannerTmp = tableBillDetailData.getScanner(scan);
            Result result3pl = resultScannerTmp.next();
            if (result3pl == null) {
                // 插入差异表
                String itemFeeTmp = new String(map.get(SmcHbaseConstant.ColumnName.ITEM_FEE
                        .getBytes()));
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
                        SmcHbaseConstant.ColumnName.DIFF_FEE.getBytes(), itemFeeTmp.getBytes());
                put.addColumn(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes(),
                        SmcHbaseConstant.ColumnName.CHECK_STATE.getBytes(),
                        SmcConstant.StlBillDetailDiffData.CheckState.DIFF.getBytes());
                put.addColumn(SmcHbaseConstant.FamilyName.COLUMN_DEF.getBytes(),
                        SmcHbaseConstant.ColumnName.CHECK_STATE_DESC.getBytes(), "记录缺失".getBytes());
                Table tableBillDetailDiffData = HBaseProxyTest.getConnection().getTable(
                        TableName.valueOf(SmcHbaseConstant.TableName.STL_BILL_DETAIL_DIFF_DATA_
                                + yyyyMm));
                tableBillDetailDiffData.put(put);
            }
        }
    }

}
