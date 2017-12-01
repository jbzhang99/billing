package com.ai.baas.dst.util;

import com.ai.baas.dst.constants.DstConstants.SEQ;
import com.ai.opt.sdk.components.sequence.util.SeqUtil;

/**
 * dst seq 工具类
 *
 * Date: 2016年4月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
public final class DstSeqUtil {

    private DstSeqUtil() {
    
    }

    /**
     * 获取账单优惠信息id
     * @return
     */
    public static String getDiscountInfoId() {
        return SeqUtil.getNewId(SEQ.DST_DISCOUNT_INFO$DISCOUNT_ID$SEQ,10);
    }
    public static String getCouponInfoId() {
        return SeqUtil.getNewId(SEQ.DST_COUPON_INFO$COUPON_ID$SEQ,20);
    }
    public static String getCouponCodeId() {
        return SeqUtil.getNewId(SEQ.DST_COUPON_CODE$CODE_ID$SEQ,20);
    }

}
