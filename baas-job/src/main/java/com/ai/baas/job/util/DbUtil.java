package com.ai.baas.job.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;



/**
 * 数据库JDBC公共类
 * 
 * @author biancx
 *
 */
public class DbUtil {
	// 定义JDBC需要的参数
	private static String driver = null;
	private static String url = null;
	private static String user = null;
	private static String password = null; 
	private static Logger log =Logger.getLogger(DbUtil.class);
	private static String databaseName=null;
	static {
		// 可以保证只加载一次，而且调用的时候肯定已经加载完成
		try {
			// 获取数据库配置信息
			Properties properties = new Properties();
			// 加载配置文件
			properties.load(DbUtil.class.getClassLoader().getResourceAsStream("context/jdbc.properties"));
			// 获取配置文件里的配置信息
			driver = properties.getProperty("jdbc.driverClassName");
			url = properties.getProperty("jdbc.url");
			user = properties.getProperty("jdbc.username");
			password = properties.getProperty("jdbc.password");
			// 加载驱动
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void judBaseType(){
		if(driver.contains("oracle")){
			databaseName="oracle";
		}else{
			if(driver.contains("mysql"))
				databaseName="mysql";
			else {
				log.error("the driver is error,please check.......");
			}
		}
	}

	/**
	 * 获取inv库的连接
	 */
	public static Connection getConnection() {
		Connection connection = null;
		judBaseType();
		try {
			if("mysql".equals(databaseName))
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			log.error("DataBaseUtil.getConnection()" + url + ":" + user + ":" + password);
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * 关闭连接
	 * 
	 * @param connection
	 */
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取清单对象
	 * 
	 * @param connection
	 * @return
	 */
	public static Statement getStatement(Connection connection) {
		Statement statement = null;
		try {
			// 判断连接是否为空 如果为空创建一个新的
			if (connection == null) {
				connection = getConnection();
			}
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}

	/**
	 * 关闭清单对象
	 * 
	 * @param statement
	 */
	public static void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取预处理清单对象
	 * 
	 * @param connection
	 * @param sql
	 * @return
	 */
	public static PreparedStatement getPstmt(Connection connection, String sql) {
		PreparedStatement preparedStatement = null;
		try {
			// 判断连接是否为空 如果为空创建一个新的
			if (connection == null) {
				connection = getConnection();
			}
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}

	/**
	 * 关闭结果集合
	 * 
	 * @param resultSet
	 */
	public static void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 释放所有的资源
	 * 
	 * @param connection
	 * @param statement
	 * @param resultSet
	 */
	public static void closeConnStmtRs(Connection connection, Statement statement, ResultSet resultSet) {
		closeResultSet(resultSet);
		closeStatement(statement);
		closeConnection(connection);
	}

}
