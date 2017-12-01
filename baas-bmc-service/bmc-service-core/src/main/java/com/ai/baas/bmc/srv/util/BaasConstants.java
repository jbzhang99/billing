package com.ai.baas.bmc.srv.util;

public class BaasConstants {

	public static final String FIELD_SPLIT = new String(new char[] { (char) 1 });
	public static final String RECORD_SPLIT = new String(new char[] { (char) 2 });
	public static final String DUP_DB_PATH = "/com/ai/baas/bmc/service/dup.tables";
	public static final String DR_DB_PATH = "/com/ai/baas/bmc/service/detail.record.tables";
	public static final String EXECUTOR_MAX_NUM = "/com/ai/baas/bmc/service/executor.max.num";
	public static final String COMMON_SPLIT = ",";
	public static final String COMMON_JOINER = "_";
	public static final String COMMON_HYPHEN = "-";
	
	public static final String BMC_ROUTE_PREFIX = "BMC";
	
	public static final String SERVICE_TYPE = "service_type";
	public static final String SERVICE_ID = "service_id";
	public static final String TENANT_ID = "tenant_id";
	public static final String SOURCE = "source";
	public static final String BATCH_SERIAL_NUMBER = "bsn";
	public static final String SERIAL_NUMBER = "sn";
	public static final String ACCOUNT_PERIOD = "account_period";
	public static final String ARRIVAL_TIME = "arrival_time";
	public static final String RECORD_DATA = "data";
	public static final String START_TIME  = "start_time";
	public static final String SUBS_ID = "subs_id";
	public static final String CUST_ID = "cust_id";
	public static final String ACCT_ID = "acct_id";
	
	public static final String ACTIVE_TIME = "active_time";
	public static final String INACTIVE_TIME = "inactive_time";
	public static final String PRODUCT_ID = "product_id";
	public static final String PRICE_CODE = "price_code";
	public static final String DETAIL_CODE = "detail_code";
	public static final String PRODUCT_PRIORITY = "product_priority";
	public static final String CUNIT_PRICE_CODE = "cunit_price_code";
	public static final String FEE_ITEM_CODE = "fee_item_code";
	public static final String CHARGE_TYPE = "charge_type";
	public static final String DURATION = "duration";
	public static final String SUBJECT_CODE = "subject_code";
	public static final String UNIT_TYPE = "unit_type";
	public static final String CAL_TYPE = "cal_type";
	public static final String USAGE_AMOUNT = "usage_amount";
	public static final String FACTOR_CODE = "factor_code";
	public static final String FACTOR_NAME = "factor_name";
	public static final String FACTOR_VALUE = "factor_value";
	public static final String PRICE_VALUE = "price_value";
	public static final String STEP_SAME_INTERVAL = "0";
	public static final String STEP_CONNECTED_INTERVAL = "1";
	public static final String STEP_UNCONNECTED_INTERVAL = "2";
	public static final String STEP_GROUP = "step_group";
	public static final String EXT_CODE = "ext_code";
	public static final String EXT_OWNER = "ext_owner";
	public static final String EXT_NAME = "ext_name";
	public static final String EXT_VALUE = "ext_value";
	public static final String UNIT_CODE = "unit_code";
	public static final String ADD_UP_BEFORE = "add_up_before";
	public static final String ADD_UP_AFTER = "add_up_after";
	public static final String STEP_HASH_KEY = "hash_key";
	
	public static final String FAIL_STEP = "BMC";
	public static final String CHARGE_TYPES_PACKAGE = "PACKAGE";
	public static final String CHARGE_TYPES_STEP = "STEP";
	public static final String CHARGE_TYPES_UNIT = "UNIT";
	public static final String CHARGE_TYPES_CUNIT = "CUNIT";
	public static final String REAL_TIME = "real_time";
	public static final String REAL_TIME_MONTH = "real_time:month";
	public static final String REAL_TIME_YEAR = "real_time:year";
	public static final String REAL_TIME_ALWAYS = "real_time:always";
}
