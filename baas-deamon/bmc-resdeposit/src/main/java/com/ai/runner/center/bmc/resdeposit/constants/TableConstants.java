package com.ai.runner.center.bmc.resdeposit.constants;

public final class TableConstants {
    /**
     * subs_comm表表名
     */
    public static final String SUBS_COMM = "bl_subs_comm";
    /**
     * subs_comm表字段
     */
    public final class SubsComm{
        /**
         * 用户id
         */
        public static final String SUBS_ID = "subs_id";
        /**
         * 租户id
         */
        public static final String TENANT_ID = "tenant_id";
        /**
         * 产品id
         */
        public static final String PRODUCT_ID = "product_id";
        /**
         * 产品实例id
         */
        public static final String SUBS_PRODUCT_ID = "subs_product_id";
        /**
         * 系统id
         */
        public static final String SYSTEM_ID = "system_id";
        /**
         * 生效时间
         */
        public static final String ACTIVE_TIME = "active_time";
        /**
         * 失效时间
         */
        public static final String INACTIVE_TIME = "inactive_time";
        /**
         * 清零标识
         */
        public static final String RES_CLEAR_FLAG = "res_clear_flag";
        /**
         * 赠送标识
         */
        public static final String RES_BONUS_FLAG = "res_bonus_flag";
    }
    /**
     * 价格详情表表名
     */
    public static final String PRICE_DETAIL = "cp_price_detail";
    
    /**
     * 价格详情表字段类
     */
    public final class PriceDetail{
        /**
         * 价格编号
         */
        public static final String PRICE_CODE = "price_code";
        /**
         * 费用类型
         */
        public static final String CHARGE_TYPE = "charge_type";
        /**
         * 详细编号
         */
        public static final String DETAIL_CODE = "detail_code";
    }
    
    /**
     * package_info表名
     */
    public static final String PACKAGE_INFO = "cp_package_info";
    /**
     * package_info表字段
     */
    public final class PackageInfo{
        /**
         * 详细编号
         */
        public static final String DETAIL_CODE = "detail_code";
        /**
         * 数量
         */
        public static final String AMOUNT = "amount";
    }
    
    /**
     * price_info表名
     */
    public static final String PRICE_INFO = "cp_price_info";
    /**
     * price_info表字段
     */
    public final class PriceInfo{
        /**
         * 租户
         */
        public static final String TENANT_ID = "tenant_id";
        /**
         * 产品id
         */
        public static final String PRICE_CODE = "price_code";
        /**
         * 产品类型
         */
        public static final String PRODUCT_TYPE = "product_type";
    }
}
