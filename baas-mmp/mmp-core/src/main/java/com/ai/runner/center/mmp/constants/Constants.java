package com.ai.runner.center.mmp.constants;

public final class Constants {
	private Constants() {

	}

	/**
	 * storm task info 状态
	 * 
	 * @author wangjl9
	 *
	 */
	public static final class StatusTaskInfo {
		private StatusTaskInfo() {

		}

		public static final String REGISTERED = "0";
		public static final String RUNNING = "1";
		public static final String STOPED = "2";
		public static final String DELETED = "-1";
	}

	/**
	 * storm集群信息
	 */
	public static final class StormClusterInfo {
		private StormClusterInfo() {

		}

		/**
		 * 所有storm home路径
		 */
		public static final String STORM_HOME = "${STORM_HOME}";
		/**
		 * storm集群所有主机ip 目前在这里配置，后续可能有zookeper中获取
		 */
		// 停用public static final String[] HOSTS = { "10.1.249.31", "10.1.249.33" };
	}

	/**
	 * 代理程序的相关配置信息
	 * 
	 * @author ja-ence
	 * 
	 */
	public static final class AgentInfo {
		private AgentInfo() {

		}

		/**
		 * 用于代理的端口
		 */
		public static final String PORT = "60001";
		/**
		 * 代理cmd服务的虚拟路径码
		 */
		public static final String REMOTE_CMD_SV_CODE = "";
		/**
		 * 代理远程上传的服务的虚拟路径编码
		 */
		public static final String REMOTE_UPLOAD_SV_CODE = "/normal/file";
		/**
		 * 代理远程xml节点添加服务的虚拟路径编码
		 */
		public static final String REMOTE_XML_APPEND_SV_CODE = "/xml/append";
		/**
		 * 操作成功返回字符串
		 */
		public static final String OPER_SUCCESS_RESULT = "SUCCESS";
		/**
		 * 操作失败返回字符串
		 */
		public static final String OPER_FAIL_RESULT = "FAIL";
	}

	/**
	 * 
	 */
	/**
	 * linux环境下的服务器日志功能配置
	 * 
	 * @author ja-ence
	 *
	 */
	public static final class LogTaskInfo {
		private LogTaskInfo() {

		}

		// 参数变量名称
		public static final String LOG_DIR_VAR_NAME = "${storm.log.dir}";
		public static final String LOG_FILE_VAR_NAME = "${logfile.name}";
		public static final String LOG_CONF_FILE_VAR_NAME = "${log.conf.filename}";
		public static final String LOG_LOGGER_VAR_NAME = "${logger.name}";
		public static final String LOG_LOGGER_LEVEL_VAR_NAME = "${logger.level}";

		// 变量默认值
		public static final String LOG_DIR_DEFAULT = "${STORM_HOME}/logs";
		public static final String LOG_LOGGER_NAME_DEFAULT = "ROOT";
		public static final String LOG_LOGGER_LEVEL_DEFAULT = "INFO";
		public static final String LOG_CONF_DIR_DEFAULT = "${STORM_HOME}/logback/topology";
		public static final String LOG_CONF_INCLUDED_FILE_PATH_DEFAULT = "${STORM_HOME}/logback/included-logback.xml";
		// 二次参数变量
		public static final String LOG_FILE = "topology-" + LOG_LOGGER_VAR_NAME;
		public static final String LOG_CONF_FILE_NAME = LOG_CONF_FILE_VAR_NAME + "-logback.xml";
		// 生成配置文件的模板
		public static final String LOGBACK_INCLUDED_NODE_NAME = "included";
		public static final String LOGBACK_INCLUDED_XMLNODE_MODEL = "<include file=\"" + LOG_CONF_DIR_DEFAULT + "/"
				+ LOG_CONF_FILE_NAME + "\"/>";
		public static final String LOGBACK_CONF_FILE_MODEL = "<included>" + "<appender name=\"" + LOG_LOGGER_VAR_NAME
				+ "\" class=\"ch.qos.logback.core.rolling.RollingFileAppender\">" + "	<file>" + LOG_DIR_VAR_NAME + "/"
				+ LOG_FILE_VAR_NAME + "</file>"
				+ "	<rollingPolicy class=\"ch.qos.logback.core.rolling.FixedWindowRollingPolicy\">"
				+ "	  <fileNamePattern>" + LOG_DIR_VAR_NAME + "/" + LOG_FILE_VAR_NAME + ".%i</fileNamePattern>"
				+ "	  <minIndex>1</minIndex>" + "	  <maxIndex>9</maxIndex>" + "	</rollingPolicy>"
				+ "	<triggeringPolicy class=\"ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy\">"
				+ "	  <maxFileSize>100MB</maxFileSize>" + "	</triggeringPolicy>" + "	<encoder>"
				+ "	  <pattern>[%d{yyyy-MM-dd' 'HH:mm:ss.SSSZZ}] %c [%p] %m%n</pattern>" + "	</encoder>"
				+ " </appender>" + " <logger name=\"" + LOG_LOGGER_VAR_NAME + "\">" + "	 <level value=\""
				+ LOG_LOGGER_LEVEL_VAR_NAME + "\" />" + "	 <appender-ref ref=\"" + LOG_LOGGER_VAR_NAME + "\" />"
				+ " </logger>" + "</included>";
	}
}
