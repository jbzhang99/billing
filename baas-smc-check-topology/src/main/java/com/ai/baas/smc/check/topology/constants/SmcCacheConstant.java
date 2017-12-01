package com.ai.baas.smc.check.topology.constants;

public final class SmcCacheConstant {
    private SmcCacheConstant() {
    }

    public static final String CACHE_KEY_SPLIT = ".";

    /**
     * 账单项
     */
    public static final String BILL_ITEM = "bill.item";

    /**
     * 详单项
     */
    public static final String BILL_DETAIL_ITEM = "bill.detail.item";

    public static final class TypeCode {

        private TypeCode() {
        }

        public static final String STL_POLICY_ITEM_PLAN = "STL_POLICY_ITEM_PLAN";

        public static final String SFTP_CONF = "SFTP_CONF";

        public static final String AUTH = "AUTH";

        public static final String DATA_COLLECT = "data_collect";

        public static final String BILL_DETAIL_STYLE_ITEM = "BILL_DETAIL_STYLE_ITEM";

    }

    public static final class ParamCode {

        private ParamCode() {
        }

        public static final String FEE_ITEM = "FEE_ITEM";

        public static final String USER_NAME = "USER_NAME";

        public static final String PWD = "PWD";

        public static final String USER_ID = "USER_ID";

        public static final String PAASWD = "PASSWD";

        public static final String URL = "url";

        public static final String UPLOAD_URL_DIFF_FILE = "upload_url_diff_file";

        public static final String SPLIT_ITEM_VALUE = "SPLIT_ITEM_VALUE";
    }

    public static final class NameSpace {

        private NameSpace() {
        }

        /**
         * sys_param
         */
        public static final String SYS_PARAM_CACHE = "com.ai.baas.smc.cache.sysparam";

        public static final String POLICY_CACHE = "com.ai.baas.smc.cache.policy";

        public static final String BILL_STYLE_CACHE = "com.ai.baas.smc.cache.billstyle";

        public static final String ELEMENT_CACHE = "com.ai.baas.smc.cache.element";

        /**
         * redis计数
         */
        public static final String CHECK_COUNT_CACHE = "com.ai.baas.smc.cache.check.count";
    }

    public static class Dshm {
        public static class TableName {
            public static final String STL_IMPORT_LOG = "stl_import_log";
        }

        public static class FieldName {

            public static final String TENANT_ID = "tenant_id";

            public static final String BATCH_NO = "batch_no";

            public static final String BILL_TIME_SN = "bill_time_sn";

            public static final String OBJECT_ID = "object_id";

            public static final String LOG_ID = "log_id";

        }

        public static class OptType {
            public static final int INSERT = 1;

            public static final int UPDATE = 0;
        }
    }
}
