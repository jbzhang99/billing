package com.ai.runner.center.bmc.resdeposit.constants;

public class Constants {
	
	//联通账期日
	public static final int CU_BILL_DAY = 27;
	//移动账期日
	public static final int CM_BILL_DAY = 1;
	
	//联通运营商标识以什么开头
	public static final String CU_BASIC_START = "2";
	//移动运营商标识以什么开头
	public static final String CM_BASIC_START = "1";
	//决定入账接口调用开关
	public static final String VIV = "viv";
	public static final String CITIC = "citic";

	//决定判重表使用的接口实现，hbase表 or mysql表
	public static final String HBASE = "hbase";
	public static final String MYSQL = "mysql";
	/**
	 * 服务端的信息
	 * @author biancx
	 *
	 */
	public static final class ShmClientInfo{
		/**
		 * rmc采用协议
		 */
		public static final String SHM_SERVER_PROTOCAL = "rmi";
		/**
		 * 服务主机地址
		 */
		public static final String SHM_SERVER_HOST = "${10.1.234.164}";
		/**
		 * 端口
		 */
		public static final String SHM_SERVER_PORT = "${PORT}"; 
		/**
		 * 服务名
		 */
		public static final String SERVICE_FACTORY_NAME = "serviceFactory";
		/**
		 * 服务前缀
		 */
		public static final String SERVICE_PREFIX = SHM_SERVER_PROTOCAL + "://" + SHM_SERVER_HOST + ":" + SHM_SERVER_PORT + "/";
		/**
		 * 服务url
		 */
		public static final String SERVICE_FACTORY_PATH = SERVICE_PREFIX + SERVICE_FACTORY_NAME;
	}
	public static final class ShmServiceCode{

		/**
		 * 共享内存服务编码
		 */
		public static final int SHM_SERVICE_CODE = 10001;
	}
	/**
	 * 服务端的信息
	 * @author chenjy7
	 *
	 */
	public static final class ShmServerInfo{
		/**
		 * rmc采用协议
		 */
		public static final String SHM_SERVER_PROTOCAL = "rmi";
		/**
		 * 服务主机地址
		 */
		public static final String SHM_SERVER_HOST = "localhost";
		/**
		 * 端口
		 */
		public static final String SHM_SERVER_PORT = "${PORT}"; 
		/**
		 * 默认端口
		 */
		public static final String SHM_SERVER_PORT_DEFAULT = "8686"; 
		/**
		 * 服务前缀
		 */
		public static final String SERVICE_PREFIX = SHM_SERVER_PROTOCAL + "://" + SHM_SERVER_HOST + ":" + SHM_SERVER_PORT + "/";
		/**
		 * 服务工厂
		 */
		public static final String SERVICE_FACTORY_NAME = "serviceFactory";
		/**
		 * shm服务名
		 */
		public static final String SHM_SERVICE_NAME = "shmService";
		/**
		 * shm ui服务名
		 */
		public static final String SHM_UI_SERVICE_NAME = "shmUIService";
		/**
		 * 服务工厂路径
		 */
		public static final String SERVICE_FACTORY_PATH = SERVICE_PREFIX + SERVICE_FACTORY_NAME;
		/**
		 * shm服务路径
		 */
		public static final String SHM_SERVICE_PATH = SERVICE_PREFIX + SHM_SERVICE_NAME;
		/**
		 * shm ui服务路径
		 */
		public static final String SHM_UI_SERVICE_PATH = SERVICE_PREFIX + SHM_UI_SERVICE_NAME;
		
	}
}
