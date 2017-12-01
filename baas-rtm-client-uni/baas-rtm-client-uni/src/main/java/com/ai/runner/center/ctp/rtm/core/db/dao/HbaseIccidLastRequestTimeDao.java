package com.ai.runner.center.ctp.rtm.core.db.dao;

import java.io.IOException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ai.runner.center.ctp.rtm.core.db.HBaseProxy;
import com.ai.runner.center.ctp.rtm.core.util.PropertiesUtil;

public class HbaseIccidLastRequestTimeDao {
	
	private static Logger logger = LoggerFactory.getLogger(HbaseIccidLastRequestTimeDao.class);

	private Connection conn = HBaseProxy.getConnection();
	private String tableNameProfix = (String)PropertiesUtil.getValue("ctp.rtm.requestm2m.getusagedatadetail.iccidrequesttime.tablename");
	private byte[] familyName = "detail".getBytes();
	private byte[] iccidColumn = "iccid".getBytes();
	private byte[] lastTimeColumn = "lastTime".getBytes();
	
	
	public void writeIccidLastRequestTime(String iccid,String lastTime) throws IOException{
		Put put = new Put(iccid.getBytes());
		put.addColumn(familyName, iccidColumn, iccid.getBytes());
		put.addColumn(familyName, lastTimeColumn, lastTime.getBytes());
		Table table = conn.getTable(TableName.valueOf(tableNameProfix));
		table.put(put);
	}
	
	public String getLastRequestTime(String iccid){
		try {
			Table table = conn.getTable(TableName.valueOf(tableNameProfix));
			Get get = new Get(iccid.getBytes());
			Result result = table.get(get);
			if (result!=null&&result.getExists()) {
				return new String(result.getValue(familyName, lastTimeColumn));
			}
		} catch (IOException e) {
			logger.error("查询上一次时间异常:",e);
		}
		return "";
	}
	
}
