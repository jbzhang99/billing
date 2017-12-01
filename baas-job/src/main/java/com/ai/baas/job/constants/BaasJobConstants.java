package com.ai.baas.job.constants;

public final class BaasJobConstants {
    public static final class BlCustinfo {

        public static final class State {

            /**
             * 正常
             */
            public static final String NORMAL = "Normal";

            /**
             * 未返档
             */
            public static final String NODOC = "NoDoc";

            /**
             * 注册
             */
            public static final String REGISTER = "Register";

            /**
             * 欠费
             */
            public static final String OWEFEE = "OweFee";

            /**
             * 冻结
             */
            public static final String FREEZE = "Freeze";

        }

    }
    
    public static final class BlAcctInfo {

        public static final class OwnerType {

            public static final String CUST = "CUST";

            public static final String USER = "USER";
        }
    }
}
