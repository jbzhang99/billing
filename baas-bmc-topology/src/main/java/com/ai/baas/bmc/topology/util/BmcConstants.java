package com.ai.baas.bmc.topology.util;

public class BmcConstants {

	public static final String UNPACKING_BOLT ="unpacking";
	public static final String DUPLICATE_CHECKING_BOLT = "duplicate_checking";
	public static final String RULE_ADAPT_BOLT = "rule_adapt";
	public static final String BILLING_BOLT = "billing";

	public static final String BMC_ROUTE_PREFIX = "BMC";
	
	public static final String ACTIVE_TIME = "active_time";
	public static final String INACTIVE_TIME = "inactive_time";
	public static final String PRODUCT_ID = "product_id";
	public static final String PRICE_CODE = "price_code";
	public static final String DETAIL_CODE = "detail_code";
	public static final String FEE_ITEM_CODE = "fee_item_code";
	public static final String FACTOR_CODE = "factor_code";
	public static final String CHARGE_TYPE = "charge_type";
	public static final String DURATION = "duration";
	public static final String SUBJECT_CODE = "subject_code";
	public static final String UNIT_TYPE = "unit_type";
	public static final String STEP_SAME_INTERVAL = "0";
	public static final String STEP_CONNECTED_INTERVAL = "1";
	public static final String STEP_UNCONNECTED_INTERVAL = "2";
	public static final String FAIL_STEP = "BMC";
	public static final String EVENT_ID = "event_id";
	public static final String SOURCE_TYPE = "source_type";
	public static final String SOURCE_TYPE_VALUE = "bmc";
	public static final String SYSTEM_ID = "system_id";
	public static final String OWNER_TYPE = "owner_type";
	public static final String OWNER_TYPE_SERV = "subs";
	public static final String OWNER_ID = "owner_id";
	public static final String EVENT_TYPE = "event_type";
	public static final String EVENT_TYPE_SUB_DATA = "DATA";
	public static final String AMOUNT = "amount";
	public static final String AMOUNT_MARK = "amount_mark";
	public static final String AMOUNT_MARK_MINUS = "MINUS";
	public static final String AMOUNT_TYPE = "amount_type";
	public static final String AMOUNT_TYPE_DATA = "DATA";
	public static final String AMOUNT_TYPE_BOOK = "BOOK";
	public static final String START_DATE="start_date";
	public static final String START_TIME_IN="start_time_in";
	public static final String EXPANDED_INFO ="expanded_info";
	public static final String NODEDUCT_APN="apn1";
	public static final String APN_CODE="apn_code";
	public static final String APN_MATCH="apn_match";
	public static final String START_TIME="start_time";
	
	public enum CHARGE_TYPES {
		PACKAGE, STEP, UNIT, CUNIT
	}
	
	
	
}
