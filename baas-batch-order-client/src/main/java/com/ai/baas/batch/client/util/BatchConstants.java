package com.ai.baas.batch.client.util;

import kafka.consumer.StaticTopicCount;

public class BatchConstants {

	public static final String UNPACKING_BOLT ="unpacking";
	public static final String DUPLICATE_CHECKING_BOLT = "duplicate_checking";
	public static final String RULE_ADAPT_BOLT = "rule_adapt";
	public static final String BILLING_BOLT = "billing";
	
	public static final String CP_PRICE_INFO = "cp_price_info";
	public static final String CP_PRICE_DETAIL = "cp_price_detail";
	public static final String CP_CUNITPRICE_DETAIL = "cp_cunitprice_detail";
	public static final String CP_CUNITPRICE_INFO = "cp_cunitprice_info";
    public static final String CP_STEP_INFO = "cp_step_info";
    public static final String CP_PACKAGE_INFO = "cp_package_info";
    public static final String CP_FACTOR_INFO = "cp_factor_info";
    public static final String CP_EXT_INFO = "cp_ext_info";
    public static final String BL_CUST_INFO = "bl_custinfo";
    public static final String BL_USERINFO = "bl_userinfo";
    
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
    public static final String COMMENTS = "comments";
    public static final String SERVICE_TYPE = "service_type";
    public static final String ACTIVE_STATUS = "active_status";
    public static final String CREATE_TIME = "create_time";
    public static final String PRODUCT_TYPE = "product_type";
    public static final String TENANT_ID = "tenant_id";
    public static final Object DETAIL_ID = "detail_id";
    

    
    
    public static final class ZxServiceId {

        public static final String ECS = "576206bb6ae6ca04e145958d";
        public static final String RDS = "5762107c6ae6ca04e14595b8";
        public static final String CS = "57721abd2fa45f06e1c013d2";
        public static final String SMARTCLOUD = "57981ab7e1983a04434e1b4e";
        public static final String KVS = "5785e232b9aa1e3769039c19";
        public static final String ONS = "57721e052fa45f06e1c013da";
        public static final String OSS = "57721cb62fa45f06e1c013d6";
        public static final String YOUHR = "20e7fd16-e5b0-46ec-bc8a-5a4a8232b313";
        public static final String YOUYC = "a732806f-46c5-4386-9877-41c5687a5fbc";
        public static final String YOUBZ = "a732806f-46c5-4386-9877-41c5687a5fbd";
        
        public static final String SLB = "583b954378c93e0473411da8";
        public static final String DISK = "5836a0462fbaaf0aee0db7a2";
        public static final String EXCONN = "58255cdfa71e5008f7e2a14a";
        public static final String VPC = "58184aafcbac2f0a712c735d";
        public static final String WAF = "58465454b992230c4edfe723";
        
        public static final String  CYD = "43a3921d-7479-43d0-a970-031699dcf9b6";
        public static final String  ZBJZ = "97acb1b1-fbb3-4b4f-9c23-95acba084ac6";
        public static final String  CMSTOP = "ce4f19ab-d83f-4164-9974-9487b97cddcd";

        public static final String  TBTION = "41fc31a4-d0d7-4a1a-a3a5-744b8cec72a6";
        //域名解析
        public static final String  GIXI ="206c3efc853b92c3779d730b9e7c2def";
        public static final String EIP = "58b3cc2618e1fb442d8f4077";
        //域名注册
        public static final String ZHUCE = "1446f6b8a4aa11e680f576304dec7eb7";

        public static final String SMS = "97efd33f-56de-4733-aea4-10f9a4b7034b";
        public static final String FR = "d149adb5-b1fe-481a-a294-68c7c2796fee";
        public static final String ER = "3d47b510-dc32-4801-88c3-a354e09c983e";
        public static final String COMV = "a27a75c0-533f-4e75-8d92-5600f9141e8b";
        public static final String CAPI = "c3472270-671d-4897-af94-c67fcbb04722";
        public static final String BDM = "4b524916-3ca4-4c4b-8e5a-205e4224ce41";
        public static final String TMCH = "94b5ddc6d65711e6bf26cec0c932ce01";
        public static final String IMP = "8292cbcabc2d11e6a4a6cec0c932ce01";
         
        public static final String DRDS = "58e74c1a557f56075552c503";

    }
    
    public static final class Billing {
    	public static final String PACKAGE = "package";
    	public static final String ONETIME = "onetime";
    	public static final String USAGE = "usage";
    	public static final String STEP = "step";
    }
    
    public static final class Subject{
    	public static final String SUBJECT_CODE = "subject_code";
    	public static final String RECORD_TYPE = "record_type";
    }
    
    public static final class ResultCode{
    	public static final String SUCCESS = "000000";
    	/*
    	 * 错误编码
    	 */
    	public static final String CHECKJSON = "000001";
    }
    
	public enum CHARGE_TYPES {
		PACKAGE, STEP, UNIT, CUNIT
	}


	
	
	
}
