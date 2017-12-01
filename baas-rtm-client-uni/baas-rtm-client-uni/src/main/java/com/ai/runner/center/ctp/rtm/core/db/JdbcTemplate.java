package com.ai.runner.center.ctp.rtm.core.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.runner.center.ctp.rtm.core.util.DataSourceUtil;

public class JdbcTemplate {

	private static Logger logger = LoggerFactory.getLogger(JdbcTemplate.class);
	
//	public static Connection getConnection(String dataSourceName) throws SQLException{
//		return DataSourceUtil.getDataSource(dataSourceName).getConnection();
//	}
	
	
	/**
	 * 更新（包括UPDATE、INSERT、DELETE，返回受影响的行数）
	 * @param DataSourceName
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int update(String dataSourceName,String sql, Object... params) {
		int result = 0;
		Connection conn = null;
		QueryRunner runner = new QueryRunner();
		try {
			conn = DataSourceUtil.getConnection(dataSourceName);
			result = runner.update(conn, sql, params);
			conn.commit();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error(e1.getMessage());
			}
		} finally{
			DbUtils.closeQuietly(conn);
		}
		return result;
	}
	
	public static int[] batchUpdate(String dataSourceName,String sql,List<Object[]> inputParams){
		int[] results = null;
		Connection conn = null;
		QueryRunner runner = new QueryRunner();
		int len = inputParams.size();
		Object[][] params = new Object[len][];
		try {
			for(int i=0;i<len;i++){
				params[i] = inputParams.get(i);
			}
			conn = DataSourceUtil.getConnection(dataSourceName);
			results = runner.batch(conn, sql, params);
			conn.commit();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error(e1.getMessage());
			}
		} finally{
			DbUtils.closeQuietly(conn);
		}
		return results;
	}
	
	
}
