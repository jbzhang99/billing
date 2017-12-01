package com.ai.baas.pkgfee.constants;

public class CpPkgfeeConstants {

	public static final String PURCHASE_UNIT_MON = "M";
	public static final String PURCHASE_UNIT_YEAR = "Y";
	public static final String PURCHASE_UNIT_DAY = "D";
	public static final String PURCHASE_UNIT_HOUR = "H";
	
    public static final String PRODUCT_MODE_PKG = "PACKAGE";
    //public static final String PRODUCT_MODE_ONCE = "ONCE";
    public static final String PRODUCT_MODE_ONCE = "ONETIME";
    
    /**
     * dubbo服务返回参数成功
     */
    public static final String SUCCESS = "000000";
    
    /**
     * dubbo服务返回参数错误
     */
    public static final String ERR = "error";
    
    public static final String COMMON_SPLIT = ",";
    public static final String FEE_LIST_RECORD = "#";
    public static final String FEE_LIST_FIELD = "$";
    
    
    public static final String DONATION_SUBJECT = "DONATION";//赠款科目
    public static final String COUPON_SUBJECT = "COUPON";//优惠券科目
    public static final String REDPACKET_SUBJECT = "REDPACKET";//红包科目
    
    public static final String DEDUCT_MODE_PREPAY = "prepay";  //扣款模式预付费
    public static final String DEDUCT_MODE_POSTPAY = "postpay";//扣款模式后付费
    public static final String FUND_FREEZE_SUBJECT = "100002"; //冻结资金科目
    
    /**
     * 优惠信息
     */
    public static final String DST_CALC_TYPE_MZ = "mz"; //满赠
    public static final String DST_CALC_TYPE_MJ = "mj"; //满减
    public static final String DST_CALC_TYPE_DZ = "dz"; //打折
    public static final String DST_RULL_TYPE = "discount_rull_type";
    public static final String DST_FULL_COST_UNIT_ID = "full_cost_unit_id";
    public static final String DST_FULL_COST_AMOUNT = "full_cost_amount";
    public static final String DST_DISCOUNT_UNIT_ID = "discount_unit_id";
    public static final String DST_DISCOUNT_AMOUNT = "discount_amount";
    public static final String DST_RULL_TYPE_REDUCE = "reduce";
    public static final String DST_RULL_TYPE_DISCOUNT = "discount";
    public static final String DST_UNIT_YUAN = "yuan";
    public static final String DST_UNIT_MONTH = "month";
    public static final String DST_DISCOUNT_PERCENT = "discount_percent";
    
    /**
     * 优惠卷
     */
    public static final String COUPON_TYPE_ALL = "ALL"; //全类商品
    public static final String COUPON_TYPE_APPOINT = "APPOINT"; //指定商品
    public static final String COUPON_CON_TYPE_IMREDUCE = "IMREDUCE";  //立减
    public static final String COUPON_CON_TYPE_FULLREDUCE = "FULLREDUCE";//满减
    public static final String COUPON_RULL_TYPE_REDUCE = "REDUCE";    //减
    public static final String COUPON_RULL_TYPE_DISCOUNT = "DISCOUNT";//折
    public static final String COUPON_UNIT_YUAN = "YUAN";
    public static final String COUPON_UNIT_DISCOUNT = "DISCOUNT";
    public static final String COUPON_UNIT_MONTH = "MONTH";
    
    /**
     * 退订有效时间
     */
    public static final int PERIOD_WAIT = 7;
}
