package com.ai.baas.dst.constants;
/**
 * BMC常量类
 *
 * Date: 2016年4月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
public final class DstConstants {

    private DstConstants() {

    }
    public final static class SEQ {
            private SEQ() {
            }

        public static final String DST_DISCOUNT_INFO$DISCOUNT_ID$SEQ = "DST_DISCOUNT_INFO$DISCOUNT_ID$SEQ";
        public static final String DST_COUPON_INFO$COUPON_ID$SEQ = "DST_COUPON_INFO$COUPON_ID$SEQ";
        public static final String DST_COUPON_CODE$CODE_ID$SEQ = "DST_COUPON_CODE$CODE_ID$SEQ";
    }

    public static final class TenantId {
        private TenantId() {
        }

        public static final String PUB = "PUB";

        public static final String ZX = "ECITIC";
    }
    public static final class CouponStatus{
    	private CouponStatus(){}
    	public static final String CREATE="CREATE";
    	public static final String EXPORT="EXPORT";
    	public static final String USED="USED";
    	public static final String EFFECTIVE="EFFECTIVE";
    	public static final String DEL="DEL";
    	public static final String GOT="GOT";
    	public static final String REFUSED="REFUSED";
    	public static final String INVALID="INVALID";
    }
    
    public static final class CouponHead{
    	private CouponHead(){}
    	public static final String HEAD="QD";
    }
    public static final class CouponRule{
    	private CouponRule(){}
    	/**
    	 * 公开
    	 */
    	public static final String ALL="ALL";
    	/**
    	 * 仅运营可见
    	 */
    	public static final String ONLYOP="ONLYOP";
    	public static final String OPERATOR="OP";
    	public static final String CHANNEL="QD";
    	public static final String APPLY="APPLY";
    	public static final String DISTRIBUTE="DISTRIBUTE";
    	
    	
    }
    /**
     * 账单优惠常量定义
     * @author wangluyang
     *
     */
    public static final class DiscountInfo {
        
        private DiscountInfo() {
            
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
         * @author wangluyang
         *
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
         * @author wangluyang
         *
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
         * 是否所有产品参与优惠
         * @author wangluyang
         *
         */
        public static final class PrdDiscountType{
        	private PrdDiscountType(){}
        	
        	/**
        	 * 用户自己选择的产品参与优惠
        	 */
        	public static final String DEFAULT = "0";
        	
        	/**
        	 * 所有产品参与优惠
        	 */
        	public static final String ALL = "1";
        }
        
        /**
         * 赠送业务生效方式
         * @author wangluyang
         *
         */
        public static final class ActiveMode {
            
            private ActiveMode() {
                
            }
            
            /**
             * 立即生效
             */
            public static final String IMMEDIATELY = "immediately";
            
            /**
             * 下月生效
             */
            public static final String NEXT_MONTH = "next_month";
            
            /**
             * 指定日期到账
             */
            public static final String FIXED_DAY = "special_date";
            
        }
        
        /**
         * 赠送业务周期
         * @author wangluyang
         *
         */
        public static final class ActivePeriod {
            
            private ActivePeriod() {
                
            }
            
            /**
             * 一个月
             */
            public static final String ONE_MONTH = "one_month";
            
            /**
             * 三个月
             */
            public static final String THREE_MONTHS = "three_month";
            
            /**
             * 指定时间
             */
            public static final String FIXED_DAY = "specified_time";
        }
        
        /**
         * 满赠赠品类型
         * @author wangluyang
         *
         */
        public static final class GiftType{
        	private GiftType(){}
        	
        	/**
        	 * 赠送业务
        	 */
        	public static final String GIFT_TYPE_YW = "yw";
        	
        	/**
        	 * 赠送现金
        	 */
        	public static final String GIFT_TYPE_XJ = "xj";
        	
        	/**
        	 * 赠送虚拟币
        	 */
        	public static final String GIFT_TYPE_XNB = "xnb";
        	
        	/**
        	 * 赠送优惠券
        	 */
        	public static final String GIFT_TYPE_YHQ = "yhq";
        }
        
        /**
         * 属性名称
         * @author wangluyang
         *
         */
        public static final class AttrName {
            
            private AttrName() {
                
            }
            
            /**
             * 优惠产品编码
             */
            public static final String DISCOUNT_PRODUCT_ID = "discount_product_id";
            
            /**
             * 赠送产品id
             */
            public static final String GIFT_PRODUCT_ID = "gift_product_id";
            
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
             * 扣减单位
             */
            public static final String DISCOUNT_UNIT_ID = "discount_unit_id";
            
            /**
             * 折扣比例
             */
            public static final String DISCOUNT_PERCENT = "discount_percent";
            
            /**
             * 扣减类型，单位减折次
             */
            public static final String DISCOUNT_RULL_TYPE = "discount_rull_type";
            
            /**
             * 满赠（满减）到达金额
             */
            public static final String FULL_COST_AMOUNT = "full_cost_amount";
            
            /**
             * 满赠（满减）到达金额/数量／时间的单位
             */
            public static final String FULL_COST_UNIT_ID = "full_cost_unit_id";
            
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
        
        public static final class DecimalMode {
        	
        	/**
        	 * 向上取整
        	 */
	       	public static final String UP = "UP";
	       	
	       	/**
        	 * 向下取整
        	 */
	       	public static final String DOWN = "DOWN";
	       	
	       	/**
        	 * 四舍五入
        	 */
	       	public static final String ROUND = "ROUND";
	       	
        }
        
        public static final class ConfigSetting {
	        /**
	         * 配置文件路径
	         */
	        public static final String CONFIG_PATH = "com/ai/opt/config";
        }
        
        public static final class UnitId {
        	 public static final String COST_UNIT = "yuan";
        	 public static final String MONTH_UNIT = "month";
        	 public static final String DISCOUNT_UNIT = "DISCOUNT";
        }
        

        public static final class CouponConType {
        	public static final String FULLREDUCE = "FULLREDUCE";
       	 	public static final String IMREDUCE = "IMREDUCE";
        }
        
        public static final class DstTypeUnit {
        	public static final String REDUCE = "REDUCE";
       	 	public static final String DISCOUNT = "DISCOUNT";
        }
        
        public static final class CouponType {
        	public static final String ALL = "ALL";
       	 	public static final String APPOINT = "APPOINT";
        }
        
    }
    
}
