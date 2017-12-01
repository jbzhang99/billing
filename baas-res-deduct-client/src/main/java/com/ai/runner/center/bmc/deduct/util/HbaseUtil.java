package com.ai.runner.center.bmc.deduct.util;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.hadoop.hbase.util.Bytes;


public class HbaseUtil {
	private static Configuration config=null;
	private static Logger logger = LoggerFactory.getLogger(HbaseUtil.class);
	//
	private static Connection connection =null;
	//加载集群信息
	static{
		Properties prop = new Properties();
        try {
			prop.load(HbaseUtil.class.getClassLoader().getResourceAsStream(
			        "context/jdbc.properties"));		
			config=HBaseConfiguration.create();
            config.set("hbase.zookeeper.property.clientPort", prop.getProperty("hbase.zookeeper.property.clientPort"));
            config.set("hbase.zookeeper.quorum", prop.getProperty("hbase.zookeeper.quorum"));
            config.set("hbase.master", prop.getProperty("hbase.master"));
            //config.set("hbase.master.port", prop.getProperty("hbase.master.port"));
            connection = ConnectionFactory.createConnection(config);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		File workaround = new File("."); 

        System.getProperties().put("hadoop.home.dir", workaround.getAbsolutePath()); 

        new File("./bin").mkdirs();         
        try {
			new File("./bin/winutils.exe").createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//config.set( "hbase.zookeeper.property.clientPort", "2181" );
	}
	//创建新表
	public static void createTable(String tableName,String[] familys) throws Exception{ 
		Connection connection = ConnectionFactory.createConnection(config);
		Admin admin=connection.getAdmin();
		Table table = connection.getTable(TableName.valueOf(tableName)); 
		if(table!=null){
			table.close();
			connection.close();
			logger.debug("the table has existed");
		}
		else{
			HTableDescriptor hdes= new HTableDescriptor(TableName.valueOf(tableName));
			for(int i=0;i<familys.length;i++){
				hdes.addFamily(new HColumnDescriptor(familys[i]));
			}
			admin.createTable(hdes);
			connection.close();
			logger.debug(" success to create table "+tableName);
		}
	}
	//插入一条记录
	public static void addRecord(String tableName,String rowKey,String family,String qualifier,String value) throws Exception{
		
		Table table=null;
		try{
			logger.debug("");
			table = connection.getTable(TableName.valueOf(tableName));			
			if(table!=null){
				System.out.println("::::::::::::::::::::: tableName = ["+tableName+"]");
			}
			Put put=new Put(Bytes.toBytes(rowKey));
			put.addColumn(Bytes.toBytes(family),Bytes.toBytes(qualifier),Bytes.toBytes(value));
			table.put(put);
			table.close();
			System.out.println("insert record "+rowKey+" into tablename "+tableName+" is OK!");
			logger.debug("insert record "+rowKey+" into tablename "+tableName+" is OK!");
		}catch(Exception e){	
			e.printStackTrace();
		}finally{
			if(table!=null){
				System.out.println("000000000000000000000000000");
				table.close();			
			}
		}
		
	}
	public static void main(String [] args){
		String[] family={"mess_context","deal_flag"};
		String family1;
		String qualifier;
		String value;
		String tableName="funDeductDetail";
		String rowKey;
//		try {
//			createTable("funDeductDetail",family);
//			System.out.println("hahahahahahahahahahaha");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try{
			 family1="mess_context";
			 qualifier="123";
			 value="subs_id:123,comm_id:234";
			 rowKey="345";
			 System.out.println("hahahahahahahahahahaha    tttttttt");
			 addRecord(tableName, rowKey, family1, qualifier, value);
			 family1="deal_flag";
			 qualifier="123"; 
			 value="1";
			 HbaseUtil.addRecord(tableName, rowKey, family1, qualifier, value);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
