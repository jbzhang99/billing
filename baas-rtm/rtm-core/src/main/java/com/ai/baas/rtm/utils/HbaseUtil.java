package com.ai.baas.rtm.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.PUT;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public final class HbaseUtil {

	    private static Connection connection;
	    private static Logger logger=LoggerFactory.getLogger(HbaseUtil.class);
	    public static void init() {
	        Configuration configuration = HBaseConfiguration.create();
	        configuration.set("hbase.zookeeper.quorum", (String) PropertiesUtil.getValue("zookeeper.quorum"));
	        configuration.set("hbase.zookeeper.property.clientPort",  (String) PropertiesUtil.getValue("hbase.client.port"));
	        try {
	            connection = ConnectionFactory.createConnection(configuration);
	        } catch (IOException e) {
	            throw new RuntimeException("initHBaseClient failure", e);
	        }
	    }
	    public void addRecord(String tableName,String rowKey,String family,String qualifier,String value) throws IOException{
	    	Table table=null;
	    	try{
	    		logger.debug("habse add record start........");
	    		//System.out.println("habse add record start........");
	    		table=connection.getTable(TableName.valueOf(tableName));
	    		Put put=new Put(Bytes.toBytes(rowKey));
	    		put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
	    		table.put(put);
	    		table.close();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
	    		if(table!=null){
	    			table.close();
	    		}
	    	}
	    }
	    public void detailInsert(String[] records,String tenant_id,String system_id,String bsn,String fieldSplit){
	    	int num=0;
	    	StringBuilder rowKey;
	    	//String fieldSplit=(String) PropertiesUtil.getValue("ctp.field.split");
	    	String tableName=(String)PropertiesUtil.getValue("hbase.output.tableName");
	    	String acct=null;
	    	String acctNext=null;
			List<Put> puts = new ArrayList<Put>();
			List<Put> putsNext = new ArrayList<Put>();
			Put put = null;
			String acctNew=null;
			StringBuilder finishRecord;
		    byte[] familyName1 = "detail".getBytes();
		    byte[] dataColumn="record".getBytes();
		    byte[] familyName2 = "bsn".getBytes();
		    byte[] bsnColumn="value".getBytes();
	    	for(String record:records){
	    		rowKey=new StringBuilder();
	    		//finishRecord=new StringBuilder();
	    		logger.debug("the value is "+record);
	    		String[] recordList=StringUtils.splitPreserveAllTokens(record,fieldSplit);
	    		rowKey.append(recordList[4]);
    			put = new Put(rowKey.toString().getBytes());
//    			finishRecord.append(tenant_id).append(fieldSplit).append(system_id).append(fieldSplit).append(bsn)
//    			.append(fieldSplit).append(record);
    			put.addColumn(familyName1, dataColumn, record.toString().getBytes());
    			put.addColumn(familyName2, bsnColumn, bsn.getBytes());
    			logger.debug("the bsn is "+bsn);
	    		if(num==0){
	    			acct=recordList[6];
	    			logger.debug("the acct is "+acct);
	    			puts.add(put);
	    			num++;
	    		}else{
	    			acctNext=recordList[6];
	    			if(acct.equals(acctNext)){
	    				puts.add(put);
	    			}else{
	    				putsNext.add(put);
	    				acctNew=acctNext;
	    			}
	    			num++;
	    		}	
	    	}
	    	try{
		    	if(puts.size()>0){
		    		StringBuilder tablename= new StringBuilder();
		    		tablename.append(tableName).append(acct);
					Table table = connection.getTable(TableName.valueOf(tablename.toString()));
					table.put(puts);
					table.close();
				}
		    	if(putsNext.size()>0){
		    		StringBuilder tablename= new StringBuilder();
		    		tablename.append(tableName).append(acctNew);
					Table table = connection.getTable(TableName.valueOf(tablename.toString()));
					table.put(putsNext);
					table.close();
		    	}
	    	}catch(IOException e){
	    		e.printStackTrace();
	    	}
	    }
	    
	    
	    //向复制副本表中插入数据
		public void batchCopy(String message,String yyyymm,String remark,String flow,String system_id) {
			try{
				List<Put> puts = new ArrayList<Put>();
				StringBuilder hbaseName=new StringBuilder();
				hbaseName.append((String)PropertiesUtil.getValue("hbase.copy.tableName")).append(yyyymm);
				Put put=new Put(Bytes.toBytes(flow));
				String family;
				family="batch_detail";
				put.addColumn(Bytes.toBytes(family), Bytes.toBytes(system_id), Bytes.toBytes(message));
				puts.add(put);
				family="batch_remark";
				put.addColumn(Bytes.toBytes(family), Bytes.toBytes(system_id), Bytes.toBytes(remark));
				puts.add(put);
				if(puts.size()>0){
					Table table=connection.getTable(TableName.valueOf(hbaseName.toString()));
					table.put(puts);
					table.close();
				}
				
			}catch(IOException e){
				e.printStackTrace();
			}
		}
}
