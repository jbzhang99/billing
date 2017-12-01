package com.ai.runner.center.ctp.rtm.core.db;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HBaseProxy {
	private static Logger logger = LoggerFactory.getLogger(HBaseProxy.class);
	private static Connection connection;
	
	static{
		Configuration configuration = HBaseConfiguration.create();
		try {
			connection = ConnectionFactory.createConnection(configuration);
		} catch (IOException e) {
			logger.error("error", e);
		}
	}
	
	public static Connection getConnection(){
		return connection;
	}
	
}
