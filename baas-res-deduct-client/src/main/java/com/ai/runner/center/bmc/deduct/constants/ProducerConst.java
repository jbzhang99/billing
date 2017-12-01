package com.ai.runner.center.bmc.deduct.constants;

/**
 * 生产者常熟类 Date: 2016年4月19日 <br>
 * 
 * @author zhoushanbin
 * 
 * Copyright (c) 2016 asiainfo.com <br>
 */
public final class ProducerConst {

	/**
	 * mds
	 */
	public static final String MDS_PRODUCER = "mds";

	/**
	 * 使用原生态kafka 策略
	 */
	public static final String KAFKA_STRATEGY = "kafka";

	/**
	 * 使用mds 策略
	 */
	public static final String MDS_STRATEGY = "mds";

	/**
	 * 策略
	 */
	public static final String STRATEGY = "strategy";

	/**
	 * sliver
	 */
	public static final String SLIVER = "sliver";

	/**
	 * srvId
	 */
	public static final String SRV_ID = "srvId";

	/**
	 * authAddr
	 */
	public static final String AUTH_ADDR = "authAddr";

	/**
	 * authUser
	 */
	public static final String AUTH_USER = "authUser";

	/**
	 * authPasswd
	 */
	public static final String AUTH_PASSWORD = "authPasswd";

	/**
	 * msgTopic
	 */
	public static final String MSG_TOPIC = "msgTopic";

	/**
	 * authPid
	 */
	public static final String AUTH_PID = "authPid";

	/**
	 * servicePasswd
	 */
	public static final String SERVICE_PASSWORD = "servicePasswd";

	public static final String TENANT_ID = "tenant_id";

	public static final String EVENT_ID = "event_id";

	public static final String SUBS_ID = "subs_id";

	public static final String BUSINESS_ID = "business_id";

	/**
	 * bmc 计费
	 */
	public static final String BMC = "bmc";

	/**
	 * 用户
	 */
	public static final String SUBS = "subs";

	public static final String DATA = "DATA";

	public static final String AMOUNT = "amount";

	/**
	 * MINUS：导致余额减少的
	 */
	public static final String MINUS = "MINUS";

	/**
	 * 数量的类型：取值范围：
	 * 
	 * VOICE:语音资源，单位：分钟
	 * 
	 * DATA:流量资源，单位：k
	 * 
	 * SM:短信资源，单位：条
	 * 
	 * VC:虚拟币资源,virtual currency，单位：个
	 * 
	 * BOOK:资金账本，单位：厘
	 * 
	 * PC: power_consumption,电量资源，单位：千瓦时
	 */
	public static final String AMOUNT_TYPE = "amount_type";

	private ProducerConst() {

	}
}
