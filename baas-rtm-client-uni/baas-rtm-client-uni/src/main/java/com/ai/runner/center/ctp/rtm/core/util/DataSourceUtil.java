package com.ai.runner.center.ctp.rtm.core.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceUtil {

	private static Logger logger = LoggerFactory.getLogger(DataSourceUtil.class);
	private static Map<String,BasicDataSource> dsMap = new HashMap<String,BasicDataSource>();
	
	private DataSourceUtil(){};
	
	public static void load(){
		try {
			//properties = PropertiesUtil.loadProperties("db.properties");
			String dbName = PropertiesUtil.getValue("database.name");
			if(StringUtils.isBlank(dbName)){
				throw new Exception("连接池没有配置!");
			}
			String[] dsNames = StringUtils.splitPreserveAllTokens(dbName,",");
			BasicDataSource dataSource = null;
			Properties properties = null;
			for(String dsName:dsNames){
				StringBuilder dbFileName = new StringBuilder();
				dbFileName.append(dsName).append(".").append("db.properties");
				properties = PropertiesUtil.loadProperties(dbFileName.toString());
				dataSource = BasicDataSourceFactory.createDataSource(properties);
				dsMap.put(dsName, dataSource);
			}
		} catch (Exception e) {
			logger.error("context", e);
		}
		
	}
	
	public static Connection getConnection(String dataSourceName) throws SQLException{
		BasicDataSource ds = dsMap.get(dataSourceName);
		return ds==null?null:ds.getConnection();
	}
	
	public static BasicDataSource getBasicDataSource(String dataSourceName) throws SQLException{
		return dsMap.get(dataSourceName);
	}
	
}
