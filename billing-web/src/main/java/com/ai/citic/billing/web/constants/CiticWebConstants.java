package com.ai.citic.billing.web.constants;

public final class CiticWebConstants {
    public static final String CACHE_KEY_VERIFY_PICTURE = "usage_export_captcha";
    public static final String CACHE_NAMESPACE = "com.ai.citic.billing.web.captcha.cache";
    public static final String USERINFO_CACHE_NAMESPACE = "com.ai.citic.billing.web.userinfo.cache";
    public static final String ORGINFO_CACHE_NAMESPACE = "com.ai.citic.billing.web.orginfo.cache";

    private CiticWebConstants() {
    }

    public static final String PAGESIZE = "pageSize";
    public static final String PAGENO = "pageNo";

    public final class PictureVerifyConstants {
        private PictureVerifyConstants() {
        }

        /** 图片验证码长度 */
        public static final int VERIFY_SIZE = 4;

        // /**图片验证码超时时间 */
        // public static final int VERIFY_OVERTIME = 600;
        /** 图片验证码超时时间 配置key */
        public static final String VERIFY_OVERTIME_KEY = "/picture_verifycode_overtime";
    }

    public final class CategoryType {
        private CategoryType() {
        }

        public static final String SELF_CREATE = "ZJ"; //自建
        public static final String STANDARD = "BZ"; //标准

    }

    public final class TradeCode {
        private TradeCode() {
        }

        public static final String SELF_CREATE = "IAAS"; //自建
        public static final String STANDARD = "PAAS"; //标准

    }

    public final class ResultCodeConstants {
        private ResultCodeConstants() {
        }

        /*** 成功ID */
        public static final String SUCCESS_CODE = "000000";
        /*** 失败ID */
        public static final String ERROR_CODE = "111111";

        /*** 用户信息没有(失效) */
        public static final String USER_INFO_NULL = "100000";

        /** 图片验证码 错误ID */
        public static final String REGISTER_PICTURE_ERROR = "100001";
        /** 验证码错误ID */
        public static final String REGISTER_VERIFY_ERROR = "100002";
        /** 密码错误 */
        public static final String PASSWORD_ERROR = "100003";
        /** 用户名错误 */
        public static final String USERNAME_ERROR = "100004";
        /** 手机号错误 */
        public static final String PHONE_ERROR = "100005";
        /** 邮箱号错误 */
        public static final String EMAIL_ERROR = "100006";
    }
}
