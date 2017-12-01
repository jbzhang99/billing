package com.ai.runner.center.ctp.rtm.core.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BpsRecordManager {

	private static Logger logger = LoggerFactory.getLogger(BpsRecordManager.class);
	private static MultiMap<String,String> mapping = new MultiValueMap<String,String>();
	private static BpsRecordManager instance = null;
	private static String dsName = RtmConstants.MYSQL_DATASOURCE_NAME;
	
	
	public static BpsRecordManager getInstance(){
		if(instance == null){
			synchronized (BpsRecordManager.class){
				if(instance == null){
					instance = new BpsRecordManager();
					loadData();
				}
			}
		}
		return instance;
	}
	
	private static void loadData(){
		List<String> fileTypes = getFileTypes();
		for(String fileType:fileTypes){
			fileType = StringUtils.trim(fileType);
			List<String> fieldNames = getFieldNamesByType(fileType);
			for(String fieldName:fieldNames){
				mapping.put(fileType, StringUtils.trim(fieldName));
			}
		}
	}
	
	private static List<String> getFileTypes(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select bps.FILE_TYPE fileType ");
		sql.append(" from bps_dst_record bps ");
		sql.append(" group by bps.FILE_TYPE ");
		QueryRunner configRunner = new QueryRunner();
		List<String> fileTypes = null;
		Connection conn = null;
		try {
			conn = DataSourceUtil.getConnection(dsName);
			fileTypes = configRunner.query(conn,sql.toString(), new ColumnListHandler<String>("fileType"));
		} catch (SQLException e) {
			logger.error("error",e);
		} finally{
			DbUtils.closeQuietly(conn);
		}
		return fileTypes;
	}
	
	private static List<String> getFieldNamesByType(String fileTyps){
		StringBuilder sql = new StringBuilder();
		sql.append(" select b.field_name fieldName ");
		sql.append(" from bps_dst_record b ");
		sql.append(" where b.file_type = '").append(fileTyps).append("' ");
		sql.append(" order by b.field_order ");
		QueryRunner configRunner = new QueryRunner();
		List<String> fileNames = null;
		Connection conn = null;
		try {
			conn = DataSourceUtil.getConnection(dsName);
			fileNames = configRunner.query(conn,sql.toString(), new ColumnListHandler<String>("fieldName"));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbUtils.closeQuietly(conn);
		}
		return fileNames;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getFieldNames(String fileType){
		return (List<String>)mapping.get(fileType);
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		PropertiesUtil.load("ctp-rtm.properties");
		DataSourceUtil.load();
		BpsRecordManager.getInstance().loadData();
	}

}
