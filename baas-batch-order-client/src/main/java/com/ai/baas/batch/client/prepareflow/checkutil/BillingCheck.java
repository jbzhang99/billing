package com.ai.baas.batch.client.prepareflow.checkutil;

import java.util.ArrayList;
import java.util.List;

import com.ai.baas.batch.client.util.BatchConstants;
import com.alibaba.fastjson.JSON;

public class BillingCheck {
   /*
    * 需要查价格的计费模式 : onetime;package;usage
    */
    private static List<String> priceBillingMode = new ArrayList<>();

    static {
    	priceBillingMode.add(BatchConstants.Billing.ONETIME);
    	priceBillingMode.add(BatchConstants.Billing.PACKAGE);
    	priceBillingMode.add(BatchConstants.Billing.USAGE);
             
    }
    /*
     * 判断是否需要查价格
     */
    public static boolean needPriceMaking (String type){
        if(priceBillingMode.contains(type)){
            return true;
        }else{
            return false;
        }
    }
    /*
     * 判断是否需要查价格 string[]
     */
	public static boolean needPriceMaking(String[] billings) {
		for(String type : billings){
	        if(priceBillingMode.contains(type)){
	            return true;
	        }
		}
		return false;
		
	}
    
}
