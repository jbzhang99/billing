package com.ai.baas.abm.util;

import com.ai.opt.sdk.components.sequence.util.SeqUtil;

/**
 * 账务管理序列工具类
 * Date: 2015年8月13日 <br>
 * Copyright (c) 2015 asiainfo.com <br>
 * 
 * @author fanpw
 */
public final class AmcSeqUtil {
    
    private AmcSeqUtil() {
        
    }
    
    /**
     * 获取账本ID
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public static Long createBookId() {
        return SeqUtil.getNewId("AMC_FUND_BOOK$BOOK_ID$SEQ");
    }
    
    /**
     * 获取交易流水号
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public static Long createPaySerialCode() {
        return SeqUtil.getNewId("AMC_FUND_SERIAL$PAY_SERIAL_CODE$SEQ");
    }
    
    /**
     * 获取资金异动明细流水号
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public static Long createFundDetailSerialCode() {
        return SeqUtil.getNewId("AMC_FUND_DETAIL$SERIAL_CODE$SEQ");
    }
    
    /**
     * 获取账单优惠产品ID
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public static Long createProductId() {
        return SeqUtil.getNewId("AMC_PRODUCT_INFO$PRODUCT_ID$SEQ");
    }
    
    public static Long createInvoiceId(){
    	return SeqUtil.getNewId("AMC_INVOICE_INFO$INVOICE_INFO_ID$SEQ");
    }
    
    public static Long createResBookId(){
    	return SeqUtil.getNewId("AMC_RES_BOOK$BOOK_ID$SEQ");
    }
}
