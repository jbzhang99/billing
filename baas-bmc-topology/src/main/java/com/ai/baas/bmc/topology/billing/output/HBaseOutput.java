package com.ai.baas.bmc.topology.billing.output;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.topology.billing.config.HBaseOutputMapping;
import com.ai.baas.bmc.topology.entity.BillingDetailRecord;
import com.ai.baas.bmc.topology.entity.BmcOutputDetail;
import com.ai.baas.bmc.topology.entity.BmcOutputInfo;
import com.ai.baas.storm.util.HBaseProxy;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class HBaseOutput implements IOutput{
	private static Logger logger = LoggerFactory.getLogger(HBaseOutput.class);
	private HBaseOutputMapping hbaseOutputMapping;
	//private Connection conn = HBaseProxy.getConnection();
	private byte[] detailBillFamilyName = "detail_bill".getBytes();
	
	public HBaseOutput(HBaseOutputMapping hbaseOutputMapping){
		this.hbaseOutputMapping = hbaseOutputMapping;
	}

	@Override
	public void send(BillingDetailRecord detailRecord) {
		Map<String, List<Put>> batchPut = Maps.newHashMap();
		try {
			List<BmcOutputInfo> outputInfos = hbaseOutputMapping.getOutputMappingValue(detailRecord.getRecordFmtKey());
			if (outputInfos != null){
				for (BmcOutputInfo outputInfo : outputInfos) {
					String tableName = outputInfo.getTableName() + detailRecord.getAccountPeriod();
					List<Put> puts = batchPut.get(tableName);
					if (puts == null) {
						puts = Lists.newArrayList();
						batchPut.put(tableName, puts);
					}
					Put put = new Put(outputInfo.getRowKey(detailRecord).getBytes());
					for (BmcOutputDetail detail : outputInfo.getDetails()) {
						byte[] colName = Bytes.toBytes(detail.getColumnName());
						String value = detailRecord.getDataValue(detail.getParamName());
						if("service_type".equals(detail.getColumnName()))
							value="data";
						put.addColumn(detailBillFamilyName, colName, Bytes.toBytes(value == null ? "" : value));
					}
					puts.add(put);
				}
			}
			for (Entry<String, List<Put>> entry : batchPut.entrySet()) {
				String tableName = entry.getKey();
				createHBaseTableIfNecessary(tableName);
				Table table = HBaseProxy.getConnection().getTable(TableName.valueOf(tableName));
				table.batch(entry.getValue(), new Object[entry.getValue().size()]);
				table.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createHBaseTableIfNecessary(String tableName) throws IOException {
		Admin admin = HBaseProxy.getConnection().getAdmin();
		if (!admin.isTableAvailable(TableName.valueOf(tableName))) {
			HTableDescriptor tableDesc = new HTableDescriptor(
					TableName.valueOf(tableName));
			tableDesc.addFamily(new HColumnDescriptor(detailBillFamilyName));
			admin.createTable(tableDesc);
			logger.info("Create table [{0}] ok!",tableName);
		}
	}

}
