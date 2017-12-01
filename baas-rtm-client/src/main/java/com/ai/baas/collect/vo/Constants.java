package com.ai.baas.collect.vo;

public class Constants {
	
	public static final String RTMHEAD="{\"transData\":\"";
	public static final String RTMEND="\"}";
	public static final String HEAD_SPLIT="\\u0003";
	public static final String MESSAGE_SPLIT="\\u0001";
	
	//分隔符
	public static final String WO_SPLIT = ";"; 
	//字段数
	public static final int WO_FIELD_NUM = 12;
	
	
	//资源类型
	//主机	1001000
	public static final String RES_HOST = "1001000";
	//对象存储	1002001
	public static final String RES_OBJECT = "1002001";
	//文件存储	1002002
	public static final String RES_FILE = "1002002";
	//块存储	1002003
	public static final String RES_BLOCK = "1002003";
	//虚拟路由器	1004001
	public static final String RES_VROUTER = "1004001";
	//负载均衡	1005001
	public static final String RES_LVS = "1005001";
	//公网IP	1006001
	public static final String RES_PUBIP = "1006001";
	//互联网带宽	1007001
	public static final String RES_INTERNET = "1007001";
	
	//计费资源项
	//时间	10101
	public static final String BILL_TIME = "10101";
	//流量	10201
	public static final String BILL_STREAM = "10201";
	//容量	10301
	public static final String BILL_STORAGE = "10301";
	//次数	10401
	public static final String BILL_TIMES = "10401";
	
	//上传rtm的类型
	public static final String TYPE_TIME = "TIME";  //时间
	public static final String TYPE_STREAM = "STREAM";  //流量
	public static final String TYPE_STORAGE = "STORAGE";  //容量
	public static final String TYPE_TIMES = "TIMES";  //次数
	
	public static final String RTM_SUCCESS_CODE = "000000";
}
