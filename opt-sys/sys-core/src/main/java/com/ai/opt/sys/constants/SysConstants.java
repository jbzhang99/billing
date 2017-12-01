package com.ai.opt.sys.constants;

/**
 * 系统管理常量定义类
 *
 * Date: 2016年3月28日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public final class SysConstants {

    private SysConstants() {
        
    }
    /**
     * 操作結果常量定義
     * Date: 2016年4月8日 <br>
     * Copyright (c) 2016 asiainfo.com <br>
     * 
     * @author zhanglh
     */
    public final static class ResultCode{
        private ResultCode(){}
        
        public static final String SUCCESS_CODE = "000000";
        
        public static final String FAIL_CODE = "000001";
    }
    public final static class SEQ {
        private SEQ() {
        }

        public static final String FUNC_ID_SEQ = "GN_FUNC$FUNC_ID$SEQ";
        public static final String MAIL_ID_SEQ = "GN_STATION_MAILS$MAIL_ID$SEQ";

    }
    /**
     * 租户ID常量定义
     *
     * Date: 2016年3月28日 <br>
     * Copyright (c) 2016 asiainfo.com <br>
     * @author fanpw
     */
    public final static class TenantId {
        
        private TenantId() {
            
        }

        public final static String ALL_TENANT = "ALL";
    }
    
    public final static class Tenant {
    	private Tenant(){}
    	
    	/** * 状态：未签约*/
    	public static final String STATE_NOTSIGNED = "0";
    	/** * 状态：已签约*/
    	public static final String STATE_SIGNED = "1";
    	/** * 状态：到期*/
    	public static final String STATE_EXPIRE = "2";
    	/** * 状态：中断*/
    	public static final String STATE_INTERRUPT = "3";
    }
    
    /**
     * 资金科目常量定义
     *
     * Date: 2016年3月28日 <br>
     * Copyright (c) 2016 asiainfo.com <br>
     * @author fanpw
     */
    public final static class GnSubject {
        
        private GnSubject() {
            
        }

        public final static class SubjectType {
            
            private SubjectType() {
                
            }

            // 科目类型，资金科目
            public final static String FUND = "9";
        }

        // 所有行业0
        public final static String ALL_INDUSTRY = "0";
    }
    public final static class GnFunc {
        private GnFunc() {
        }

        /** 正常状态 */
        public static final String FUNC_NORMAL_STATE = "1";
        /** 失效状态 */
        public static final String FUNC_INVALID_STATE = "2";

        /*** 失效时间 */
        public static final String INACTIVE_DATE = "2099-12-31 23:59:59";

        
    }
}
