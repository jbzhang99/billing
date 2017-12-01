package com.ai.runner.center.ctp.rtm.core.db.dao;

import java.io.IOException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import com.ai.runner.center.ctp.rtm.core.db.HBaseProxy;
import com.ai.runner.center.ctp.rtm.core.service.model.GetTerminalUsageDataDetail;
import com.ai.runner.center.ctp.rtm.core.util.PropertiesUtil;
import com.ai.runner.center.ctp.rtm.core.util.RtmConstants;
import com.ai.runner.utils.util.DateUtil;
import com.ai.runner.utils.util.StringUtil;


public class HbaseWordOrderToWeightDao {
	
//	private static Logger logger = LoggerFactory.getLogger(HbaseWordOrderToWeightDao.class);

	private Connection conn = HBaseProxy.getConnection();
	private String tableNameProfix = (String)PropertiesUtil.getValue("ctp.rtm.requestm2m.cuwordorder.tableprofixname");
	private byte[] familyName = "detail".getBytes();
	private byte[] iccidColumn = "iccid".getBytes();
	private byte[] cycleStartDateColumn = "cycleStartDate".getBytes();
	private byte[] billableColumn = "billable".getBytes();
	private byte[] zoneColumn = "zone".getBytes();
	private byte[] sessionStartTimeColumn = "sessionStartTime".getBytes();
	private byte[] durationColumn = "duration".getBytes();
	private byte[] dataVolumeColumn = "dataVolume".getBytes();
	private byte[] countryCodeColumn = "countryCode".getBytes();
	private byte[] serviceTypeColumn = "serviceType".getBytes();
	
	public boolean isRepeat(String rowKey,String dateString) throws IOException{
		String tableName = tableNameProfix+dateString;
//		this.createHBaseTableIfNecessary(tableName);
		Table table = conn.getTable(TableName.valueOf(tableName));
		Get get = new Get(rowKey.toString().getBytes());
		Result result = table.get(get);
		if (result==null) {
			return false;
		}else {
			byte[] res = result.getRow();
			if (res!=null&&res.length>0) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	public void writeWordOrderToWeight(GetTerminalUsageDataDetail detail) throws IOException{
		Put put = new Put(createRowkey(detail).getBytes());
		String dateString = DateUtil.getDateString(DateUtil.YYYYMM);
		if (!StringUtil.isBlank(detail.getIccid())) {
			put.addColumn(familyName, iccidColumn, detail.getIccid().getBytes());
		}
		if (!StringUtil.isBlank(detail.getCycleStartDate())) {
			put.addColumn(familyName, cycleStartDateColumn, detail.getCycleStartDate().getBytes());
		}
		if (!StringUtil.isBlank(detail.getBillable())) {
			put.addColumn(familyName, billableColumn, detail.getBillable().getBytes());
		}
		if (!StringUtil.isBlank(detail.getZone())) {
			put.addColumn(familyName, zoneColumn, detail.getZone().getBytes());
		}
		if (!StringUtil.isBlank(detail.getSessionStartTime())) {
			String value = detail.getSessionStartTime();
			dateString = value.substring(0, 4)+value.substring(5, 7);
			put.addColumn(familyName, sessionStartTimeColumn, value.getBytes());
		}
		if (!StringUtil.isBlank(detail.getDuration())) {
			put.addColumn(familyName, durationColumn, detail.getDuration().getBytes());
		}
		if (!StringUtil.isBlank(detail.getDataVolume())) {
			put.addColumn(familyName, dataVolumeColumn, detail.getDataVolume().getBytes());
		}
		if (!StringUtil.isBlank(detail.getCountryCode())) {
			put.addColumn(familyName, countryCodeColumn, detail.getCountryCode().getBytes());
		}
		if (!StringUtil.isBlank(detail.getServiceType())) {
			put.addColumn(familyName, serviceTypeColumn, detail.getServiceType().getBytes());
		}
		String tableName = tableNameProfix+dateString;
//		this.createHBaseTableIfNecessary(tableName);
		Table table = conn.getTable(TableName.valueOf(tableName));
		table.put(put);
	}
	
	public String createRowkey(GetTerminalUsageDataDetail detail){
		StringBuilder rowKey = new StringBuilder();
		rowKey.append(detail.getIccid());
		rowKey.append(RtmConstants.HBASE_ROWKEY_SPILT_STRING);
		rowKey.append(detail.getSessionStartTime());
		rowKey.append(RtmConstants.HBASE_ROWKEY_SPILT_STRING);
		rowKey.append(detail.getDataVolume());
		return rowKey.toString();
	}
	
//	private void createHBaseTableIfNecessary(String tableName) throws IOException {
//		Admin admin = HBaseProxy.getConnection().getAdmin();
//        if (!admin.isTableAvailable(TableName
//                .valueOf(tableName))) {
//            HTableDescriptor tableDesc = new HTableDescriptor(
//                    TableName.valueOf(tableName));
//            tableDesc.addFamily(new HColumnDescriptor(
//            		new String(familyName)));
//            admin.createTable(tableDesc);
//            logger.info("Create table [{}] ok!",
//                    tableName);
//        }
//    }
	
}
