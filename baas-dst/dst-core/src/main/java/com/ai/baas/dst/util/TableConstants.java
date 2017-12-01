package com.ai.baas.dst.util;

public final class TableConstants {
    
    
    public static final String USER_INFO = "bl_userinfo";
    
    public final class UserInfo{
        public static final String SUBS_ID = "subs_id";
        public static final String ACCT_ID = "acct_id";
        public static final String CUST_ID = "cust_id";
        public static final String TENANT_ID = "tenant_id";
        public static final String SERVICE_ID = "service_id";
    }
    public static final String SUBS_COMM_EXT = "bl_subscomm_ext";
    public static final String SUBS_COMM = "bl_subs_comm";

    public final class SubsComm{

        public static final String SUBS_ID = "subs_id";
        public static final String TENANT_ID = "tenant_id";
        public static final String PRODUCT_ID = "product_id";
        public static final String SUBS_PRODUCT_ID = "subs_product_id";
        public static final String SYSTEM_ID = "system_id";
        public static final String ACTIVE_TIME = "active_time";
        public static final String INACTIVE_TIME = "inactive_time";
        public static final String RES_CLEAR_FLAG = "res_clear_flag";
        public static final String RES_BONUS_FLAG = "res_bonus_flag";
        public static final String EXT_NAME = "ext_name";
    }

    public static final String PRICE_DETAIL = "cp_price_detail";
    

    public final class PriceDetail{

        public static final String PRICE_CODE = "price_code";
        public static final String CHARGE_TYPE = "charge_type";
        public static final String DETAIL_CODE = "detail_code";
    }
    

    public static final String PACKAGE_INFO = "cp_package_info";

    public final class PackageInfo{

        public static final String DETAIL_CODE = "detail_code";
        public static final String AMOUNT = "amount";
        public static final String SERVICE_TYPE = "service_type";
    }
    
    public static final String PRICE_INFO = "cp_price_info";

    public final class PriceInfo{

        public static final String TENANT_ID = "tenant_id";
        public static final String PRICE_CODE = "price_code";
        public static final String PRODUCT_TYPE = "product_type";
    }
}
