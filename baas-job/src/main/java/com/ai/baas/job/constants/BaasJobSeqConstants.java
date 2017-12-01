package com.ai.baas.job.constants;

public final class BaasJobSeqConstants {
    private BaasJobSeqConstants(){}
    public final static class SEQ {
        private SEQ() {
        }
        public static final String PRICE_INFO_ID_SEQ = "CP_PRICE_INFO$PRICE_INFO_ID$SEQ";
        public static final String PRICE_CODE_SEQ = "CP_PRICE_INFO$PRICE_CODE$SEQ";
        public static final String DETAIL_ID_SEQ = "CP_PRICE_DETAIL$DETAIL_ID$SEQ";
        public static final String DETAIL_CODE_SEQ = "CP_PRICE_DETAIL$DETAIL_CODE$SEQ";
        public static final String PRESENT_ID_SEQ = "CP_FULL_PRESENT$PRESENT_ID$SEQ";
        public static final String PRESENT_CODE_SEQ = "CP_FULL_PRESENT$PRESENT_CODE$SEQ";
        public static final String RECORD_FMT_ID_SEQ = "BMC_RECORD_FMT$ID$SEQ";
        public static final String ROWKEY_SPLIT = new String(new char[] {(char) 1 });

        public static final String REDUCE_CODE_SEQ = "CP_FULL_REDUCE$REDUCE_CODE$SEQ";
        public static final String REDUCE_ID_SEQ = "CP_FULL_REDUCE$REDUCE_ID$SEQ";
        public static final String BMC_INFO_CODE="INFO_CODE:";
        public static final String BMC_BAK_HTABLE_SPLIT="_";
        public static final String BMC_BAK_ROUTE_TYPE="REBMC";

        public static final String BL_CUSTINFO$CUST_ID$SEQ = "BL_CUSTINFO$CUST_ID$SEQ";
    
        public static final String BL_ACCT_INFO$ACCT_ID$SEQ = "BL_ACCT_INFO$ACCT_ID$SEQ";
    
        public static final String BL_USERINFO$SUBS_ID$SEQ = "BL_USERINFO$SUBS_ID$SEQ";
    
        public static final String BL_SUBSCOMM_EXT$EXT_ID$SEQ = "BL_SUBSCOMM_EXT$EXT_ID$SEQ";
    
        public static final String BL_SUBS_COMM$SUBS_PRODUCT_ID$SEQ = "BL_SUBS_COMM$SUBS_PRODUCT_ID$SEQ";
    
    }
}
