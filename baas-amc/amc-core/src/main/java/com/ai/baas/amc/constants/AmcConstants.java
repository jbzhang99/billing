package com.ai.baas.amc.constants;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.ai.opt.sdk.util.DateUtil;

/**
 * 账务管理常量定义类
 * Title: MVNO-CRM <br>
 * Description: <br>
 * Date: 2016年3月22日 <br>
 * Copyright (c) 2016 AILK <br>
 * 
 * @author fanpw
 */
public final class AmcConstants {

    private AmcConstants() {
        
    }
    
    /**
     * 账本信息 Date: 2015年7月27日 <br>
     * Copyright (c) 2015 asiainfo.com <br>
     * 
     * @author lilg
     */
    public static final class FunFundBook {

        private FunFundBook() {
        }

        /**
         * 账本状态 Date: 2015年7月27日 <br>
         * Copyright (c) 2015 asiainfo.com <br>
         * 
         * @author lilg
         */
        public static class BookStatus {

            protected BookStatus() {
            }

            /**
             * 有效
             */
            public static final String VALID = "1";

            /**
             * 无效
             */
            public static final String INVALID = "0";

            /**
             * 冻结
             */
            public static final String FREEZE = "2";
        }

        /**
         * 资金类型，继承Subject资金类型 Date: 2015年8月20日 <br>
         * Copyright (c) 2015 asiainfo.com <br>
         * 
         * @author lilg
         */
        public static final class FundType extends FunSubject.FundType {
            private FundType() {
            }
        }

        /**
         * 默认的生失效时间 Date: 2015年8月20日 <br>
         * Copyright (c) 2015 asiainfo.com <br>
         * 
         * @author lilg
         */
        public static class DefaultDate {
            protected DefaultDate() {
            };

            /**
             * 默认失效时间
             */
            public static final Timestamp EXPIREDATE = DateUtil.getTimestamp("2099-12-31 23:59:59",
                    "yyyy-MM-dd HH:mm:ss");
        }
    }

    /**
     * 交易订单流水 Date: 2015年8月17日 <br>
     * Copyright (c) 2015 asiainfo.com <br>
     * 
     * @author lilg
     */
    public static final class FunFundSerial {
        private FunFundSerial() {
        }

        /**
         * 操作类型 Date: 2015年8月17日 <br>
         * Copyright (c) 2015 asiainfo.com <br>
         * 
         * @author lilg
         */
        public static final class OptType {
            private OptType() {
            }

            /**
             * 存入
             */
            public static final String DEPOSIT = "1";

            /**
             * 扣款
             */
            public static final String DEDUCT = "2";

            /**
             * 转账
             */
            public static final String TRANSFER = "3";

            /**
             * 提现
             */
            public static final String WITHDROW = "4";

            /**
             * 预存转兑
             */
            public static final String PREEXCHANGE = "5";

            /**
             * 冲正
             */
            public static final String REVERSE = "6";

            /**
             * 活动终止
             */
            public static final String PAY_RULE_CANCLE = "7";

        }

        /**
         * 交易状态 Date: 2015年8月17日 <br>
         * Copyright (c) 2015 asiainfo.com <br>
         * 
         * @author lilg
         */
        public static final class PayStatus {
            private PayStatus() {
            }

            /**
             * 0 交易未完成
             */
            public static final String UNFINISHED = "0";

            /**
             * 1 交易成功
             */
            public static final String SUCCESS = "1";

            /**
             * 2 交易失败
             */
            public static final String FAILURE = "2";
        }
    }

    /**
     * 科目 Date: 2015年8月17日 <br>
     * Copyright (c) 2015 asiainfo.com <br>
     * 
     * @author lilg
     */
    public static final class FunSubject {
        private FunSubject() {
        }

        /**
         * 科目类型 Date: 2015年8月17日 <br>
         * Copyright (c) 2015 asiainfo.com <br>
         * 
         * @author lilg
         */
        public static final class SubjectType {
            private SubjectType() {
            }

            // 资源科目
            public static final String RESOURCE = "1";

            // 消费科目
            public static final String FEE = "2";

            // 资金科目
            public static final String FUND = "9";

        }

        /**
         * 资金类型 Date: 2015年8月20日 <br>
         * Copyright (c) 2015 asiainfo.com <br>
         * 
         * @author lilg
         */
        public static class FundType {
            protected FundType() {
            }

            /**
             * 现金
             */
            public static final String CASH = "1";

            /**
             * 通信现金
             */
            public static final String TELE_CASH = "2";

            /**
             * 赠款
             */
            public static final String GRANT = "3";

            /**
             * 押金科目
             */
            public static final String FOREGIFT = "8";
            /*
             * 1：现金 2：通讯现金 3：赠款 4：红包 5：优惠券 6: 预存转兑 7: 月费返还 8：押金
             */
        }

        /**
         * 账本叠加方式 Date: 2015年8月20日 <br>
         * Copyright (c) 2015 asiainfo.com <br>
         * 
         * @author lilg
         */
        public static final class ValidType {
            private ValidType() {
            }

            /**
             * 0：新增时叠加在同账本上，账本不受有效期限制
             */
            public static final String MERGE = "0";

            /**
             * 1：新增时叠加在同账本上，有效期按照规则顺延
             */
            public static final String DELAY = "1";

            /**
             * 2：新增（不叠加）
             */
            public static final String ADD = "2";

        }

        /**
         * 对象在缓存中的属性名 Date: 2015年8月20日 <br>
         * Copyright (c) 2015 asiainfo.com <br>
         * 
         * @author lilg
         */
        public static final class CacheJsonKey {
            private CacheJsonKey() {
            }

            public static final String TENANT_ID = "tenantId";

            public static final String VALID_TYPE = "validType";

            public static final String FUND_TYPE = "fundType";

            public static final String SUBJECT_TYPE = "subjectType";

            public static final String USE_PRI = "usePri";

        }
    }

    /**
     * 资源账本 <br>
     *
     * Date: 2015年10月28日 <br>
     * Copyright (c) 2015 asiainfo.com <br>
     * 
     * @author lilg
     */
    public static final class FunResBook {

        private FunResBook() {
        }

        /**
         * 账本状态<br>
         *
         * Date: 2015年10月28日 <br>
         * Copyright (c) 2015 asiainfo.com <br>
         * 
         * @author lilg
         */
        public static final class BookStatus extends FunFundBook.BookStatus {
            private BookStatus() {

            }

            /**
             * 未激活，非即买即用提前入账时的状态
             */
            public static final String UN_ACTIVATED = "3";
        }

        /**
         * 默认的生失效时间 <br>
         *
         * Date: 2015年10月28日 <br>
         * Copyright (c) 2015 asiainfo.com <br>
         * 
         * @author lilg
         */
        public static final class DefaultDate extends FunFundBook.DefaultDate {
            private DefaultDate() {

            }

            /**
             * 默认生效时间
             */
            public static final Timestamp effectDate = DateUtil.getTimestamp("2000-01-01 00:00:00",
                    "yyyy-MM-dd HH:mm:ss");
        }

        /**
         * 账本产生的来源类型<br>
         *
         * Date: 2015年10月28日 <br>
         * Copyright (c) 2015 asiainfo.com <br>
         * 
         * @author lilg
         */
        public static final class SourceType {

            private SourceType() {

            }

            /**
             * 产品订购
             */
            public static final int PRODUCT_ORDER = 0;

            /**
             * 资源转赠
             */
            public static final int RES_PRESENT = 1;

            /**
             * 资源转换
             */
            public static final int RES_CONVERT = 2;

            /**
             * 资源交易
             */
            public static final int RES_TRANS = 3;

            /**
             * 活动受理
             */
            public static final int PAY_RULE = 4;

            /**
             * 资源分配
             */
            public static final int RES_ALLOT = 5;

            /**
             * 资源批发
             */
            public static final int RES_WHOLESALE = 6;

            private static final Integer[] arrAll = { PRODUCT_ORDER, RES_PRESENT, RES_CONVERT,
                    RES_TRANS, PAY_RULE, RES_ALLOT, RES_WHOLESALE };

            /**
             * 所有值(升序)
             */
            public static final List<Integer> ALL = Collections.unmodifiableList(Arrays
                    .asList(arrAll));
            static {
                Arrays.sort(arrAll);
            }
        }

        /**
         * 资源类型 <br>
         *
         * Date: 2015年10月28日 <br>
         * Copyright (c) 2015 asiainfo.com <br>
         * 
         * @author lilg
         */
        public static final class ResourceType {

            private ResourceType() {
            }

            /**
             * 语音
             */
            public static final int CALL = 10;

            /**
             * 短信
             */
            public static final int MSG = 50;

            /**
             * 流量
             */
            public static final int DATA = 60;

            /**
             * G币 (国美GB)
             */
            public static final int GB = 99;

            private static final Integer[] arrAll = { CALL, MSG, DATA, GB };

            /**
             * 所有值(升序)
             */
            public static final List<Integer> ALL = Collections.unmodifiableList(Arrays
                    .asList(arrAll));
            static {
                Arrays.sort(arrAll);
            }
        }

        /**
         * 属主类型 <br>
         *
         * Date: 2015年10月28日 <br>
         * Copyright (c) 2015 asiainfo.com <br>
         * 
         * @author lilg
         */
        public static final class OwnerType {
            private OwnerType() {

            }

            /**
             * 用户
             */
            public static final int USER = 0;

            /**
             * 账户
             */
            public static final int ACCOUNT = 1;

            /**
             * 属组
             */
            public static final int GROUP = 2;

            private static final Integer[] arrAll = { USER, ACCOUNT, GROUP };

            /**
             * 所有值(升序)
             */
            public static final List<Integer> ALL = Collections.unmodifiableList(Arrays
                    .asList(arrAll));
            static {
                Arrays.sort(arrAll);
            }
        }

        /**
         * 清零标志 <br>
         *
         * Date: 2015年10月28日 <br>
         * Copyright (c) 2015 asiainfo.com <br>
         * 
         * @author lilg
         */
        public static final class AllowClear {
            private AllowClear() {

            }

            /**
             * 清零
             */
            public static final int YES = 0;

            /**
             * 不清零
             */
            public static final int NO = 1;

            private static final Integer[] arrAll = { YES, NO };

            /**
             * 所有值(升序)
             */
            public static final List<Integer> ALL = Collections.unmodifiableList(Arrays
                    .asList(arrAll));
            static {
                Arrays.sort(arrAll);
            }
        }

        /**
         * 可转增标识
         *
         * Date: 2015年11月16日 <br>
         * Copyright (c) 2015 asiainfo.com <br>
         * 
         * @author lilg
         */
        public static final class AllowPresent {
            private AllowPresent() {

            }

            /**
             * 1 可转赠
             */
            public static final int YES = 1;

            /**
             * 0 不可转赠
             */
            public static final int NO = 0;

            private static final Integer[] arrAll = { YES, NO };

            /**
             * 所有值(升序)
             */
            public static final List<Integer> ALL = Collections.unmodifiableList(Arrays
                    .asList(arrAll));
            static {
                Arrays.sort(arrAll);
            }
        }

        /**
         * 可转兑/买卖标识
         *
         * Date: 2015年11月16日 <br>
         * Copyright (c) 2015 asiainfo.com <br>
         * 
         * @author lilg
         */
        public static final class AllowConvert {
            private AllowConvert() {

            }

            /**
             * 1 可转兑/买卖
             */
            public static final int YES = 1;

            /**
             * 0 不可转兑/买卖
             */
            public static final int NO = 0;

            private static final Integer[] arrAll = { YES, NO };

            /**
             * 所有值(升序)
             */
            public static final List<Integer> ALL = Collections.unmodifiableList(Arrays
                    .asList(arrAll));
            static {
                Arrays.sort(arrAll);
            }
        }

        /**
         * 即买即用标识
         *
         * Date: 2015年11月17日 <br>
         * Copyright (c) 2015 asiainfo.com <br>
         * 
         * @author lilg
         */
        public static final class UseFlag {
            private UseFlag() {

            }

            /**
             * 即买即用
             */
            public static final String IMMEDIATELY = "1";

            /**
             * 非即买即用
             */
            public static final String UM_IMMEDIATELY = "0";
        }

    }

    /**
     * 资源操作记录
     *
     * Date: 2015年11月16日 <br>
     * Copyright (c) 2015 asiainfo.com <br>
     * 
     * @author lilg
     */
    public static final class FunResOperaDetail {
        private FunResOperaDetail() {

        }

        /**
         * 操作记录状态
         *
         * Date: 2015年11月16日 <br>
         * Copyright (c) 2015 asiainfo.com <br>
         * 
         * @author lilg
         */
        public static final class Status {
            private Status() {
            }

            /**
             * 等待抵扣
             */
            public static final String WAIT_DEDUCT = "1";

            /**
             * 已抵扣
             */
            public static final String ALREADY_DEDUCT = "0";
        }

        /**
         * 操作类型
         *
         * Date: 2015年11月16日 <br>
         * Copyright (c) 2015 asiainfo.com <br>
         * 
         * @author lilg
         */
        public static final class OptType {
            private OptType() {
            }

            /**
             * 入账
             */
            public static final int DEPOSIT = 0;

            /**
             * 抵扣
             */
            public static final int DEDUCT = 1;

            /**
             * 剩余抵扣 <br>
             * 注：后台抵扣进程遇到账本余额不足只能抵扣部分时,会根据剩余未抵扣的金额新生成该操作类型一条操作记录
             */
            public static final int PART_DEDUCT = 2;

            /**
             * 账本生失效维护
             */
            public static final int MAINTAIN_RESBOOK = 3;

        }
    }
    
    /**
     * 账单优惠常量定义
     *
     * Date: 2016年4月6日 <br>
     * Copyright (c) 2016 asiainfo.com <br>
     * @author fanpw
     */
    public static final class AmcProductInfo {
        
        private AmcProductInfo() {
            
        }
        
        /**
         * 默认优先级
         */
        public static final String DEFAULT_PRIORITY = "0";
        
        /**
         * 优惠科目编码
         */
        public static final String BILL_SUBJECT_CODE = "A5";
        
        /**
         * 参考科目编码
         */
        public static final String REFER_SUBJECT_CODE = "A6";
        
        /**
         * 优惠条件
         */
        public static final String PREFERENTIAL_TERMS_CODE = "C6";
        
        /**
         * 产品状态
         *
         * Date: 2016年4月6日 <br>
         * Copyright (c) 2016 asiainfo.com <br>
         * @author fanpw
         */
        public static final class Status {
            
            private Status() {
                
            }
            
            /**
             * 失效
             */
            public static final String INVALID = "0";
            
            /**
             * 生效
             */
            public static final String EFFECTIVE = "1";

            /**
             * 待生效
             */
            public static final String TO_BE_EFFECTIVE = "2";

        }
        
        /**
         * 优惠类型
         *
         * Date: 2016年4月6日 <br>
         * Copyright (c) 2016 asiainfo.com <br>
         * @author fanpw
         */
        public static final class CalcType {
            
            private CalcType() {
                
            }
            
            /**
             * 满赠
             */
            public static final String CALC_TYPE_MZ = "mz";
            
            /**
             * 满减
             */
            public static final String CALC_TYPE_MJ = "mj";
            
            /**
             * 限时折扣
             */
            public static final String CALC_TYPE_DZ = "dz";
            
            /**
             * 保底
             */
            public static final String CALC_TYPE_BD = "bd";
            
            /**
             * 封顶
             */
            public static final String CALC_TYPE_FD = "fd";
            
        }
        
        /**
         * 赠送业务生效方式
         *
         * Date: 2016年4月7日 <br>
         * Copyright (c) 2016 asiainfo.com <br>
         * @author fanpw
         */
        public static final class ActiveMode {
            
            private ActiveMode() {
                
            }
            
            /**
             * 立即生效
             */
            public static final String IMMEDIATELY = "0";
            
            /**
             * 下月生效
             */
            public static final String NEXT_MONTH = "1";
            
            /**
             * 指定日期到账
             */
            public static final String FIXED_DAY = "2";
            
        }
        
        /**
         * 赠送业务周期
         *
         * Date: 2016年4月9日 <br>
         * Copyright (c) 2016 asiainfo.com <br>
         * @author fanpw
         */
        public static final class ActivePeriod {
            
            private ActivePeriod() {
                
            }
            
            /**
             * 一个月
             */
            public static final String ONE_MONTH = "0";
            
            /**
             * 三个月
             */
            public static final String THREE_MONTHS = "1";
            
            /**
             * 指定时间
             */
            public static final String FIXED_DAY = "2";
        }
        
        /**
         * 属性名称
         *
         * Date: 2016年4月7日 <br>
         * Copyright (c) 2016 asiainfo.com <br>
         * @author fanpw
         */
        public static final class AttrName {
            
            private AttrName() {
                
            }
            
            /**
             * 优惠科目编码
             */
            public static final String BILL_SUBJECT_CODE = "A5";
            
            /**
             * 参考科目编码
             */
            public static final String REFER_SUBJECT_CODE = "A6";
            
            /**
             * 优惠条件
             */
            public static final String PREFERENTIAL_TERMS_CODE = "C6";
            
            /**
             * 扣减金额
             */
            public static final String DISCOUNT_AMOUNT = "discount_amount";
            
            /**
             * 折扣比例
             */
            public static final String DISCOUNT_PERCENT = "discount_percent";
            
            /**
             * 满赠（满减）到达金额
             */
            public static final String FULL_COST_AMOUNT = "full_cost_amount";
            
            /**
             * 保底金额
             */
            public static final String BD_AMOUNT = "bd_amount";
            
            /**
             * 封顶金额
             */
            public static final String FD_AMOUNT = "fd_amount";
            
            /**
             * 生效方式
             */
            public static final String ACTIVE_MODE = "active_mode";
            
            /**
             * 赠送业务周期
             */
            public static final String ACTIVE_PERIOD = "active_period";
            
            /**
             * 生效时间
             */
            public static final String EFFECT_DATE = "effect_date";
        }
        
    }
    /**
     * 输入数据格式编码
     * Date: 2016年3月30日 <br>
     * Copyright (c) 2016 asiainfo.com <br>
     * 
     * @author LiangMeng
     */
    public static class FmtFeildName{
    	
    	private FmtFeildName(){}
        /*租户ID*/
        public static final String TENANT_ID = "tenant_id";
        /*业务类型*/
        public static final String SERVICE_ID = "service_id";
        /*来源*/
        public static final String SOURCE = "source";
        /*批次号*/
        public static final String BSN = "bsn";
        /*唯一标识*/
        public static final String SN = "sn";
        /*客户ID*/
        public static final String CUST_ID = "cust_id";
        /*用户ID*/
        public static final String SUBS_ID = "subs_id";
        /*账户ID*/
        public static final String ACCT_ID = "acct_id";
        /*开始时间*/
        public static final String START_TIME = "start_time";
        /*费用*/
        public static final String FEE1 = "fee1";
        /*详单科目*/
        public static final String SUBJECT1 = "subject1";
        /*费用*/
        public static final String FEE2 = "fee2";
        /*详单科目*/
        public static final String SUBJECT2 = "subject2";
        /*费用*/
        public static final String FEE3 = "fee3";
        /*详单科目*/
        public static final String SUBJECT3 = "subject3";
        

        /*账期*/
        public static final String BILL_MONTH = "bill_month";

        /*开始时间*/
        public static final String ARRIVE_TIME = "arrive_time";
    }
    /**
     * bolt名称配置
     * Date: 2016年3月30日 <br>
     * Copyright (c) 2016 asiainfo.com <br>
     * 
     * @author LiangMeng
     */
    public static class BoltName{
    	private BoltName(){}
        /*查重bolt*/
        public static final String DUPLICATE_CHECKING_BOLT = "duplicate_checking";
        /*账务优惠bolt*/
        public static final String ACCOUNT_PREFERENTIAL_BOLT = "account_preferential";
        /*账务优惠bolt*/
        public static final String WRITE_OFF_BOLT = "write_off";
    }
    /**
     * 错误编码、环节定义
     * Date: 2016年3月30日 <br>
     * Copyright (c) 2016 asiainfo.com <br>
     * 
     * @author LiangMeng
     */
    public static class FailConstant{
    	private FailConstant(){}
        /**错误环节定义*/
        /*查重环节*/
        public static final String FAIL_STEP_DUP = "AMC-duplicate-checking";
        /*查重环节*/
        public static final String FAIL_STEP_PRE = "AMC-account-preferential";
        /*销账环节*/
        public static final String FAIL_STEP_OWE = "AMC-account-writeoff";
        
        /**错误编码定义*/
        /*重复数据*/
        public static final String FAIL_CODE_DUP = "AMC-000001";
        /**/
        public static final String FAIL_CODE_GET_CACHE_DATA = "AMC-000002";
        /**/
        public static final String FAIL_CODE_READ_DB_DATA = "AMC-000003";
        /**/
        public static final String FAIL_CODE_WRITE_DB_DATA = "AMC-000004";
        /**/
        public static final String FAIL_CODE_SEND_KFK_MSG = "AMC-000005";
        /**/
        public static final String FAIL_CODE_OWE = "AMC-000006";
        
    }
    /**
     * 优惠产品相关配置
     * Date: 2016年3月30日 <br>
     * Copyright (c) 2016 asiainfo.com <br>
     * 
     * @author LiangMeng
     */
    public static class ProductInfo{
    	private ProductInfo(){}
        //产品ID
        public static final String PRODUCT_ID = "product_id";
        //优惠类型
        public static final String CALC_TYPE = "calc_type";
        //新科目ID用于保底
        public static final String NEW_SUBJECT = "new_subject_id";
        //账单科目
        public static final String BILL_SUBJECT = "bill_subject_id";
        //参考科目
        public static final String REF_SUBJECT = "ref_subject_id";
        
        

        //扩展名称字段
        public static final String EXT_NAME = "ext_name";
        //扩展值字段
        public static final String EXT_VALUE = "ext_value";
        
        //优惠类型:保底
        public static final String CALC_TYPE_BD = "bd";
        //保底金额
        public static final String BD_AMOUNT = "bd_amount";
        //优惠类型:封顶
        public static final String CALC_TYPE_FD = "fd";
        //封顶金额
        public static final String FD_AMOUNT = "fd_amount";
        
        
        //优惠类型:显示折扣
        public static final String CALC_TYPE_XSZK = "dz";
        //折扣率
        public static final String XSZK_ZKL = "discount_percent";
        


        //执行时段标志
        public static final String XSZK_TIME_FLAG = "xszk_time_flag";
        //执行生效时段
        public static final String XSZK_EFFECT_TIME = "discount_start_time";
        //执行失效时段
        public static final String XSZK_EXPIRE_TIME = "discount_end_time";
        
        /**
         * 产品状态
         * Date: 2016年4月21日 <br>
         * Copyright (c) 2016 asiainfo.com <br>
         * 
         * @author LiangMeng
         */
        public static final class Status {
            
            private Status() {
                
            }
            
            /**
             * 失效
             */
            public static final String INVALID = "0";
            
            /**
             * 生效
             */
            public static final String EFFECTIVE = "1";

            /**
             * 待生效
             */
            public static final String TO_BE_EFFECTIVE = "2";

        }

    }
    /**
     * 卡夫卡相关配置
     * Date: 2016年3月30日 <br>
     * Copyright (c) 2016 asiainfo.com <br>
     * 
     * @author LiangMeng
     */
    public static class KafkaConfig{
    	
    	private KafkaConfig(){}
        public static final String MDS_AUTH_URL = "paas.auth.url";
        
        public static final String MDS_AUTH_PID = "paas.auth.pid";
       
        public static final String MDS_SERVICE_ID = "paas.mds.serviceid";
        
        public static final String MDS_SERVICE_PASSWORD = "paas.mds.servicepassword";

        public static final String MDS_TOPIC = "paas.mds.topic";
        
        
    }
    /**
     * 缓存表配置
     * Date: 2016年3月31日 <br>
     * Copyright (c) 2016 asiainfo.com <br>
     * 
     * @author LiangMeng
     */
    public static class CacheConfig{
    	private CacheConfig(){}
        public static final String CCS_AUTH_URL = "paas.auth.url";
        
        public static final String CCS_AUTH_PID = "paas.auth.pid";
       
        public static final String CCS_SERVICE_ID = "paas.ccs.serviceid";
        
        public static final String CCS_SERVICE_PASSWORD = "paas.ccs.servicepassword";
        
        
//        public static final String CCS_APPNAME = "ccs.appname";
//      
//      public static final String CCS_ZK_ADDRESS = "ccs.zk_address";
        //
        public static final String AMC_DR_BILL_SUBJECT_MAP = "amc_dr_bill_subject_map";
        //
        public static final String BL_SUBS_COMM = "bl_subs_comm";
        //
        public static final String AMC_PRODUCT_INFO = "amc_product_info";
        //
        public static final String AMC_PRODUCT_DETAIL = "amc_product_detail";
        //
        public static final String AMC_PRODUCT_EXT = "amc_product_ext";
        //
        public static final String GN_SUBJECT_FUND = "gn_subject_fund";
        
    }
    /**
     * 序列名称
     * Date: 2016年4月12日 <br>
     * Copyright (c) 2016 asiainfo.com <br>
     * 
     * @author LiangMeng
     */
    public static class SeqName{
    	private SeqName(){};

        public static final String AMC_CHARGE$SERIAL_CODE$SEQ = "AMC_CHARGE$SERIAL_CODE$SEQ";
        
        public static final String AMC_INVOICE$SERIAL_CODE$SEQ = "AMC_INVOICE$SERIAL_CODE$SEQ";
        
        public static final String AMC_SETTLE_LOG$SERIAL_CODE$SEQ = "AMC_SETTLE_LOG$SERIAL_CODE$SEQ";
        
        public static final String AMC_SETTLE_DETAIL$SERIAL_CODE$SEQ = "AMC_SETTLE_DETAIL$SERIAL_CODE$SEQ";
        
        public static final String AMC_FUND_DETAIL$SERIAL_CODE$SEQ = "AMC_FUND_DETAIL$SERIAL_CODE$SEQ";
        
        public static final String AMC_FUND_SERIAL$PAY_SERIAL_CODE$SEQ = "AMC_FUND_SERIAL$PAY_SERIAL_CODE$SEQ";
        
        public static final String AMC_CC_DETAIL$SERIAL_CODE$SEQ = "AMC_CC_DETAIL$SERIAL_CODE$SEQ";
        
        public static final String AMC_CC_CHARGE$SERIAL_CODE$SEQ = "AMC_CC_CHARGE$SERIAL_CODE$SEQ";
        
    }
    public static final class MDSTopic {

        private MDSTopic() {
        }
        public static final String AP_TOPIC = "BaaS_AMC_MDS";
        public static final String WO_TOPIC = "BaaS_AMC_WO_MDS";
        
    }
    /**
     * 销账记录表
     * Date: 2016年7月6日 <br>
     * Copyright (c) 2016 asiainfo.com <br>
     * 
     * @author LiangMeng
     */
    public static final class AmcSettleLog {

        private AmcSettleLog() {
        }
        /**
         * 销帐状态
         * Date: 2016年7月6日 <br>
         * Copyright (c) 2016 asiainfo.com <br>
         * 
         * @author LiangMeng
         */
        public static final class Status {

            private Status() {
            }
            //0：销帐；
            public static final int XZ = 0;
            //1：预销帐（资金审核）；
            public static final int YXZ = 1;
            //2：冲正（保留）；
            public static final int CZ = 2;
            //10：反销帐；
            public static final int FXZ = 10; 
        }
        /**
         * 销帐模式
         * Date: 2016年7月6日 <br>
         * Copyright (c) 2016 asiainfo.com <br>
         * 
         * @author LiangMeng
         */
        public static final class SettleMode {

            private SettleMode() {
            }
            //0：普通；
            public static final int PTXZ = 0;            
            //1：呆帐销帐；
            public static final int DZXZ = 1;
            //2：坏帐销帐。
            public static final int HZXZ = 2;
         
        }
        /**
         * 销帐冲抵类型
         * Date: 2016年7月6日 <br>
         * Copyright (c) 2016 asiainfo.com <br>
         * 
         * @author LiangMeng
         */
        public static final class SettleType {

            private SettleType() {
            }
            //0：销帐；
            public static final int XZ = 0;
            //1：冲抵。
            public static final int CD = 1;
        }
    }
    
    /**
     * 账务优惠报文解析
     * Date: 2016年7月8日 <br>
     * Copyright (c) 2016 asiainfo.com <br>
     * 
     * @author LiangMeng
     */
    public static final class APMessage {

        private APMessage() {
        }

        //租户ID
        public static final String TENANT_ID = "tenant_id";
        //账户ID
        public static final String ACCT_ID = "acct_id";
        //客户ID
        public static final String CUST_ID = "cust_id";
        //用户ID
        public static final String SUBS_ID = "subs_id";
        //费用
        public static final String FEE1 = "fee1";
        public static final String FEE2 = "fee2";
        public static final String FEE3 = "fee3";
        //科目
        public static final String SUBJECT1 = "subject1";
        public static final String SUBJECT2 = "subject2";
        public static final String SUBJECT3 = "subject3";
        public static final String SERVICE_ID = "service_id";
        public static final String ACCOUNT_MONTH = "account_period";
        //分摊信息
        public static final String APPORTION_LIST = "apportion_list";
        public static final class Apportion {

            private Apportion() {
            }
            public static final String SERVICE_ID = "acct_id";
            //分摊规则
            public static final String METHOD = "method";
            //比例
            public static final String VALUE = "value";

            //比例
            public static final String METHOD_RATIO = "ratio";
            
            public static final String COST_CENTER_ID = "cost_center_id";
            
        }
        public static final String FEE_INFO = "fee_info";
        public static final String FEE = "fee";
        public static final String SUBJECT = "subject";
        public static final String DR_KEY = "sn";
    }
    
    public static final class TotalBill {

        private TotalBill() {
        }
        public static final long CUST_ID = 99999;
        //分摊规则
        public static final String ACCT_ID = "99999";
        //比例
        public static final long SUBJECT_ID = 999999;
        
    }
    public static final class EmailSeng{
    	private EmailSeng(){
    		
    	}
    	public static final String YES ="YES";
    	
    	public static final String SEND_MAIL="SEND_MAIL";
    	
    	public static final String BIND_EMAIL="email/template/uac-register-binemail.xml";
    }
}
