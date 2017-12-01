package com.ai.baas.op.web.constants;

public final class BaaSOPConstants {
    private BaaSOPConstants() {
    }

    public static final String PAGESIZE = "pageSize";
    public static final String PAGENO = "pageNo";
    
    public static final class ExceptionCode {
        private ExceptionCode() {
        }

        public static final java.lang.String SUCCESS = "000000";// 成功

        public static final java.lang.String SYSTEM_ERROR = "999999";// 系统错误

        public static final java.lang.String PARAM_IS_NULL = "888888";// 参数为空

        public static final java.lang.String NO_RESULT = "000001";// 无结果

        public static final java.lang.String PARAM_TYPE_NOT_RIGHT = "000002";// 参数类型错误

        public static final java.lang.String NO_DATA_OR_CACAE_ERROR = "000003";// 无数据或缓存错误

        public static final java.lang.String PARAM_VALUE_NOT_RIGHT = "000004";// 参数取值错误

        public static final java.lang.String PARAM_VALUE_EXIST_ERROR = "000005";// 参数值重复错误

        public static final java.lang.String RESULT_IS_NULL = "000006";// 结果为空
    }

    public static final class SubjectCode{
    	public static final String SUBJECT_INDUSTRY_CODE = "1";
    	public static final String BILL_SUBJECT="21";
    	public static final String DR_SUBJECT="2";
    }
    
    public static final class FeedbackState {

        public static final String INIT = "01";

        public static final String DONE = "02";  

    }
    
    public static final class Coupon{
    	private Coupon(){}
    	public static final String ALL="ALL";
    	public static final String ALL_USE="全场通用";
    	public static final String NEW="新建";
    	public static final String EFFECTIVE="生效";
    	public static final String USED="已使用";
    	public static final String INVALID="已失效";
    	public static final String GOT="被领用";
    	public static final String APPOINT="APPOINT";
    	public static final String APPOINT_TYPE="指定产品";
    	public static final String REFUSED="REFUSED";
    	public static final String REFUSE_NAME="审核不通过";
    	
    
    }
    public static final class CouponStatus{
    	private CouponStatus(){}
    
    	public static final String NEW="NEW";
    	public static final String EFFECTIVE="EFFECTIVE";
    	public static final String USED="USED";
    	public static final String INVALID="INVALID";
    
    }
    
    public static final class UpgState {
        
        //进行中
        public static final String ING = "1";
        
        //取消
        public static final String CANCEL = "2";
    }
    
    /**
     * 账单优惠管理属性名称
     */
    public static final class BDAttrName {
        private BDAttrName(){}
        
        /** 优惠科目编码 */
        public static final String BILL_SUBJECT_CODE = "A5";
        
        /** 参考科目编码 */
        public static final String REFER_SUBJECT_CODE = "A6";
        
        /** 优惠条件 */
        public static final String PREFERENTIAL_TERMS_CODE = "C6";
        
        /**
         * 优惠产品编码
         */
        public static final String DISCOUNT_PRODUCT_ID = "discount_product_id";
        
        /**
         * 赠送产品id
         */
        public static final String GIFT_PRODUCT_ID = "gift_product_id";
        
        /** 扣减金额 */
        public static final String DISCOUNT_AMOUNT = "discount_amount";
        
        /** 折扣比例 */
        public static final String DISCOUNT_PERCENT = "discount_percent";
        
        /** 满赠（满减）到达金额 */
        public static final String FULL_COST_AMOUNT = "full_cost_amount";
        
        /**
         * 扣减单位
         */
        public static final String DISCOUNT_UNIT_ID = "discount_unit_id";
        
        /**
         * 扣减类型，单位减折次
         */
        public static final String DISCOUNT_RULL_TYPE = "discount_rull_type";
        
        /**
         * 满赠（满减）到达金额/数量／时间的单位
         */
        public static final String FULL_COST_UNIT_ID = "full_cost_unit_id";
        
        /** 保底金额 */
        public static final String BD_AMOUNT = "bd_amount";
        
        /** 封顶金额 */
        public static final String FD_AMOUNT = "fd_amount";
        
        /** 生效方式 */
        public static final String ACTIVE_MODE = "active_mode";
        
        /** 赠送业务周期 */
        public static final String ACTIVE_PERIOD = "active_period";
        
        /** 赠品生效时间 */
        public static final String EFFECT_DATE = "effect_date";
        
        /** 折扣开始时间 */
        public static final String DISCOUNT_START_TIME = "discount_start_time";
        
        /** 折扣结束时间 */
        public static final String DISCOUNT_END_TIME = "discount_end_time";
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
     * 账单优惠管理：优惠类型
     */
    public static final class DiscountType{
        private DiscountType(){}
        public static final String MZ = "mz"; //满赠
        public static final String MJ = "mj"; //满减
        public static final String DZ = "dz"; //折扣
        public static final String BD = "bd"; //保底
        public static final String FD = "fd"; //封顶
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
     * 账单优惠管理：计费类型
     */
    public static final class BillingType{
        private BillingType(){}
        public static final String STEP_GROUP_TYPE = "STEP_GROUP_TYPE"; //阶梯组合套餐产品
        public static final String STANDARD_GROUP_TYPE = "STANDARD_GROUP_TYPE"; //标准组合套餐产品
    }
    
    public static final class ProferName{
    	 private ProferName(){}
    	 /**
    	  * 赠送业务类型：赠送业务
    	  */
    	 public static final String SERVICE_OFFER = "service_offer";
    	 
    	 /**
    	  * 详单科目类型
    	  */
    	 public static final String DR_SUBJECT="2";
    	 
    }
    
    public static final class URLConstant{
    	private URLConstant(){}
    	public static final String BAAS_PT_INDEX_URL_KEY = "/baas_pt_index_url";
    }
    
    public static final class DetailBillConstant{
    	private DetailBillConstant(){}
    	public static final String YUAN = "元";
    	public static final String MB = "MB";
    }
    
    
}
