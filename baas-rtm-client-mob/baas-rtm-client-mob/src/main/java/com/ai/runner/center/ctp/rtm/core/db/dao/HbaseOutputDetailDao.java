package com.ai.runner.center.ctp.rtm.core.db.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;

import com.ai.runner.center.ctp.rtm.core.db.HBaseProxy;
import com.ai.runner.center.ctp.rtm.core.util.StringLine;


public class HbaseOutputDetailDao {

	private Connection conn = HBaseProxy.getConnection();
	//private String tableName = "RTM_OUTPUT_DETAIL_A";
	private String tableName = "RTM_OUTPUT_DETAIL";
	private byte[] familyName = "detail".getBytes();
	private byte[] psnColumn = "psn".getBytes();
	private byte[] snColumn = "sn".getBytes();
	private byte[] lvColumn = "line_value".getBytes();
	
	public void batchInstOutputDetail(List<StringLine> lines) throws IOException{
		List<Put> puts = new ArrayList<Put>();
		Put put = null;
		StringBuilder rowKey;
		for(StringLine line:lines){
			rowKey = new StringBuilder();
			rowKey.append(line.getPsn()).append(line.getSn());
			put = new Put(rowKey.toString().getBytes());
			put.addColumn(familyName, psnColumn, line.getPsn().getBytes());
			put.addColumn(familyName, snColumn, line.getSn().getBytes());
			put.addColumn(familyName, lvColumn, line.getData().getBytes());
			
			puts.add(put);
		}
		if(puts.size()>0){
			Table table = conn.getTable(TableName.valueOf(tableName));
			table.put(puts);
		}
	}
	
}
