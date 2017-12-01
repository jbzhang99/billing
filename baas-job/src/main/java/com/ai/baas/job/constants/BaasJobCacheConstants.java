package com.ai.baas.job.constants;

public final class BaasJobCacheConstants {
    private BaasJobCacheConstants(){}
    public static final class NameSpace{
        private NameSpace(){}
        public static final String BMC_BASEDATA_CODE = "com.ai.baas.bmc.api.baseInfo";
        
    }
    public static final class ParamType{
        private ParamType(){}
        public static final String ADAPTER_PARAM = "ADAPTER_PARAM";
        public static final String ECITIC_BANK = "ECITIC_BANK";
        
    }
    public static final class ParamCode{
        private ParamCode(){}
        public static final String ORG_QUERY_PATH = "ORG_QUERY_PATH";
        public static final String URL = "URL";
        public static final String USER_NAME = "USER_NAME";
        
    }
    
    public static class Dshm {
        public static class FieldName {
            public static final String TENANT_ID = "tenant_id";
            public static final String PRICE_PRODUCT_TYPE = "price_product_type";
            public static final String PRICE_PRODUCT_ID = "price_product_id";
            public static final String FACTOR_NAME = "factor_name";
            public static final String FACTOR_VALUE = "factor_value";
            public static final String EXT_CUST_ID = "ext_cust_id";
            public static final String ACTIVE_TIME = "active_time";
            public static final String INACTIVE_TIME = "inactive_time";
            public static final String CUST_ID = "cust_id";
            public static final Object CUST_NAME = "cust_name";
        }

        public static class TableName {
            public static final String CP_PRICEMAKING_FACTOR = "cp_pricemaking_factor";
            public static final String BL_CUSTINFO = "bl_custinfo";
            public static final String BL_USERINFO = "bl_userinfo";
            public static final String BL_ACCT_INFO = "bl_acct_info";
            public static final String BL_SUBS_COMM = "bl_subs_comm";
            public static final String BL_SUBSCOMM_EXT = "bl_subscomm_ext";
        }
        public static class OptType {
            public static final int INSERT = 1;

            public static final int UPDATE = 0;
        }
        public static final class InitLoaderReault {

            public static final int SUCCESS = 1;
            
        }
    }
}
