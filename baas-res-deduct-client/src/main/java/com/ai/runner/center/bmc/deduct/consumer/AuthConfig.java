package com.ai.runner.center.bmc.deduct.consumer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: AuthConfig
 * @Description: 配置信息加载类
 * @author biancx
 */
public class AuthConfig implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(AuthConfig.class);
	private static String SRC_ID;
	private static String AUTH_ADDR;
	private static String AUTH_USER;
	private static String AUTH_PASSWD;
	private static String MSG_TOPIC;
	private static String AUTH_PID;
	private static String SERVICE_PASSWD;
	static {
		String path = "/mdsAuth.properties";
		File configFile = new File(path);
		Properties cfg = new Properties();
		InputStream in = null;
		try {
			if (configFile.exists())
				in = new FileInputStream(configFile);
			else
				in = AuthConfig.class.getResourceAsStream(path);

			if (in != null) {
				try {
					cfg.load(in);
				} finally {
					in.close();
				}
			}
//			SRC_ID = get("MDS0010", cfg.getProperty("srvId"));
//			AUTH_ADDR=get("http://10.1.228.198:1482100/iPaas-Auth/service/check", cfg.getProperty("authAddr"));
//			AUTH_USER=get("biancx@asiainfo.com",cfg.getProperty("authUser") );
//			AUTH_PASSWD = get("123456", cfg.getProperty("authPasswd"));
//			AUTH_PID=get("2AAE1D1AE3DE4209BB3EC1B1BA3F131C00", cfg.getProperty("authPid"));
//			SERVICE_PASSWD=get("111111", cfg.getProperty("servicePasswd"));
//			MSG_TOPIC=get("B973F8F7699B4DE3BE9F1D792B2FB1F3_MDS001_35902198500", cfg.getProperty("msgTopic"));
			
			SRC_ID =  cfg.getProperty("srvId");
			AUTH_ADDR= cfg.getProperty("authAddr");
			AUTH_USER=cfg.getProperty("authUser") ;
			AUTH_PASSWD = cfg.getProperty("authPasswd");
			AUTH_PID=cfg.getProperty("authPid");
			SERVICE_PASSWD=cfg.getProperty("servicePasswd");
			MSG_TOPIC=cfg.getProperty("msgTopic");
		} catch (Exception e) {
			logger.error("mdsAuth.properties couldn't be load ,please check!");
		}
	}

//	public static ProducerConfig getProducerConfig() {
//		Properties props = new Properties();
//		System.out.println(ZK_CONNECT);
//		props.put("zk.connect", ZK_CONNECT);
//		props.put("metadata.broker.list", METADATA_BROKER_LIST);
//		props.put("serializer.class", KAFKA_SERIALIZER);
//		props.put("zk.connectiontimeout.ms", ZK_CONNECTIONTIMEOUT);
//
//		return new ProducerConfig(props);
//	}

	public static Properties getAuthConfig() {
		Properties props = new Properties();
		
		props.put("srvId", SRC_ID);
		props.put("authAddr",AUTH_ADDR);
		
		props.put("authUser", AUTH_USER);
		props.put("authPasswd", AUTH_PASSWD);
		props.put("msgTopic", MSG_TOPIC);
		props.put("authPid", AUTH_PID);
		props.put("servicePasswd", SERVICE_PASSWD);
		//props.put("zookeeper.connection.timeout.ms", ZK_CONNECTIONTIMEOUT);
		//System.out.println("ZK_CONNECTIONTIMEOUT---------------------------------"+MSG_TOPIC);
		return props;
	}

	private AuthConfig() {
	}

	/**
	 * @Title: getInt
	 * @Description: 返回一个整形配置
	 * @param i
	 *            默认值
	 * @param value
	 *            转换值
	 * @return
	 * @author 董腾
	 */
	private static int getInt(int i, String value) {
		if (value != null) {
			try {
				return Integer.parseInt(value);
			} catch (Exception e) {
			}
		}
		return i;
	}

	/**
	 * @Title: get
	 * @Description: 返回一个字符串配置
	 * @param s
	 *            默认值
	 * @param value
	 *            转换值
	 * @return
	 * @author biancx
	 */
	private static String get(String s, String value) {
		return value == null ? s : value;
	}

	public static String getSRC_ID() {
		return SRC_ID;
	}

	public static void setSRC_ID(String sRC_ID) {
		SRC_ID = sRC_ID;
	}

	public static String getAUTH_ADDR() {
		return AUTH_ADDR;
	}

	public static void setAUTH_ADDR(String aUTH_ADDR) {
		AUTH_ADDR = aUTH_ADDR;
	}

	public static String getAUTH_USER() {
		return AUTH_USER;
	}

	public static void setAUTH_USER(String aUTH_USER) {
		AUTH_USER = aUTH_USER;
	}

	public static String getAUTH_PASSWD() {
		return AUTH_PASSWD;
	}

	public static void setAUTH_PASSWD(String aUTH_PASSWD) {
		AUTH_PASSWD = aUTH_PASSWD;
	}

	public static String getMSG_TOPIC() {
		return MSG_TOPIC;
	}

	public static void setMSG_TOPIC(String mSG_TOPIC) {
		MSG_TOPIC = mSG_TOPIC;
	}

	
}

