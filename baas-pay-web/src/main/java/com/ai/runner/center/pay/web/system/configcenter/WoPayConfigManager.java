package com.ai.runner.center.pay.web.system.configcenter;

import com.ai.runner.center.pay.web.system.constants.PayConstants;
import com.ai.runner.center.pay.web.system.util.ConfigUtil;

public class WoPayConfigManager extends AbstractPayConfigManager {

    private static final String WEB_PAY_ACTION = "/wopay/webpayment/wopayapi";
    private static final String WAP_PAY_ACTION = null;
    private static final String APP_PAY_ACTION = null;
    public static final String PAY_ORG_CODE = "WO";
    public static final String MER_NO = "MER_NO";
    private static final String MERCHANT_SIGN_KEY = "MERCHANT_SIGN_KEY";
    public static final String MERCHANT_BACK_SIGN_KEY = "MERCHANT_BACK_SIGN_KEY";
    public static final String INPUT_CHARSET = "UTF-8";
    private static final String ORDER_PAY_URL = "ORDER_PAY_URL";

    public static String getOrderPayUrl() {
        return ConfigUtil.getPayOrgProperty(PAY_ORG_CODE, ORDER_PAY_URL);
    }
    
    @Override
    public String getPayActionUrl(String requestSource) {
        if(PayConstants.RequestSource.WEB.equals(requestSource)) {
            return WEB_PAY_ACTION;
        } else if(PayConstants.RequestSource.WAP.equals(requestSource)) {
            return WAP_PAY_ACTION;
        } else if(PayConstants.RequestSource.WEIXIN.equals(requestSource)) {
            return WAP_PAY_ACTION;
        } else if(PayConstants.RequestSource.APP.equals(requestSource)) {
            return APP_PAY_ACTION;
        } 
        
        return null;
    }

    @Override
    public String getRefundActionUrl(String requestSource) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getBatchRefundActionUrl(String requestSource) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getWithDrawActionUrl(String requestSource) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getBatchWithDrawActionUrl(String requestSource) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getQueryActionUrl(String requestSource) {
        // TODO Auto-generated method stub
        return null;
    }

    public static String getMerchantSignKey(String tenantId) {
        return ConfigUtil.getProperty(tenantId, PAY_ORG_CODE, MERCHANT_SIGN_KEY);
    }

}
