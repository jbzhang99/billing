package com.ai.baas.omc.constants;
/**
 * 和表相关的字段
 * Date: 2016年3月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author caoyf
 */
public final class TableCon {
    /**
     * hbase的交易流水日志表表名
     */
    public static final String TRADE_SEQ_LOG = "BMC_TRADE_SEQ_LOG";
    /**
     * 客户信息表表名
     */
    public static final String BL_CUSTINFO = "bl_custinfo";
    /**
     * 用户信息表表名
     */
    public static final String BL_USERINFO = "bl_userinfo";
    /**
     * 产品订购信息表表名
     */
    public static final String BL_SUBS_COMM = "bl_subs_comm";
    /**
     * 账户信息表表名
     */
    public static final String BL_ACCT_INFO = "bl_acct_info";
    /**
     * TRADE_SEQ_LOG对应的数据结构名称
     */
    public class ConTradeSeqLog{
        public static final String TENANT_ID = "TENANT_ID";
        public static final String INTERFACE_CODE = "INTERFACE_CODE";
        public static final String TRADE_SEQ = "TRADE_SEQ";
        public static final String RECEIVE_TIME = "RECEIVE_TIME";
        public static final String MSG_CONTENT = "MSG_CONTENT";
    }
    
    /**
     * sys_sequences中的信控免催停的序列信息
     */
    public static final String OMC_AVOIDSCOUT$AVOID_SEQ = "OMC_AVOIDSCOUT$AVOID_SEQ";
    
    public final class UserInfo{
        public static final String SUBS_ID = "subs_id";
        public static final String ACCT_ID = "acct_id";
        public static final String CUST_ID = "cust_id";
        public static final String TENANT_ID = "tenant_id";
        public static final String SERVICE_ID = "service_id";
	}
    
    public final class CustInfo{
        public static final String CUST_ID = "cust_id";
        public static final String TENANT_ID = "tenant_id";
        public static final String EXT_CUST_ID = "ext_cust_id";
	}
    
    public static final String OWNER_TYPE_SUBS = "subs";
        
    public static enum SpeType{
        SPETYPE_STOP("STOP"),//停
        SPETYPE_URGE("URGE"),//催缴
        SPETYPE_STOPANDURGE("STOPANDURGE"); //停和缴
        private final String text;
        private SpeType(final String text){
            this.text=text;
        }
        @Override
        public String toString(){
            return text;
        }
}
}
