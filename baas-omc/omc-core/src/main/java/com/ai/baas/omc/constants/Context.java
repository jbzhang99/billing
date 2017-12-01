package com.ai.baas.omc.constants;

/**
 * 环境参数 Date: 2016年3月23日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author caoyf
 */
public final class Context {
	/**
	 * hbase的rowkey分割符
	 */
	public static final String SPLIT = ";";
	/**
	 * orderinfo的接口编号
	 */
	public static final String ORDER_INFO_CODE = "BaaS-0002";
	/**
	 * custinfo
	 */
	public static final String CUST_INFO_CODE = "BaaS-0001";
	/**
	 * priceinfo中标准资费更新服务接口编号
	 */
	public static final String UPDATE_PRICEINFO = "BaaS-00003";

	/**
	 * 已存在
	 */
	public static final String EXIST = "BaaS-000004";
	/**
	 * 新建产品
	 */
	public static final String AddProduct = "BaaS-00005";
	/**
	 * 缺失tenantid
	 */
	public static final String MissTenantId = "BaaS-00006";
	/**
	 * 参数校验出错
	 */
	public static final String ValidateParamError = "BaaS-00007";
	
	/**
	 * 成功
	 */
	public static final String SUCCUSS = "BaaS-000000";
	
	/**
	 * 失败
	 */
	public static final String ERROR = "error";
}
