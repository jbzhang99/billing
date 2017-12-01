package com.ai.runner.center.ctp.rtm.core.util;

public class RtmConstants {
	//public final static String BUSINESS_ID = "business_id";
	public final static String SYSTEM_ID = "system_id";
	public final static String SERVICE_ID = "service_id";
	public final static String TENANT_ID = "tenant_id";
	//public final static String BUSINESS_TYPE = "business_type";
	public final static String PACKET_SERIAL_NUMBER = "psn";
	public final static String PACKET_CREATE_DATE = "create_date";
	public final static String PACKET_DATA = "data";
	public final static String PACKET_DATA_ROW_NUM = "$row_num$";
	public final static String PACKET_DATA_SOURCE = "$source$";
	
	public final static String FIELD_SPLIT = new String(new char[] {(char) 1 });
	public final static String RECORD_SPLIT = new String(new char[] {(char) 2 });
	public final static String HEAD_SPLIT = new String(new char[] {(char) 3 });
	public final static String PACKET_HEADER_SPLIT = ",";
	
	public final static String MYSQL_DATASOURCE_NAME = "mysql";
	public final static String HBASE_DATASOURCE_NAME = "hbase";
	
	public final static String PLACEHOLDER_GENERATOR_MODEL_CN = "column number";
	public final static String PLACEHOLDER_GENERATOR_MODEL_KEY = "key";
}
