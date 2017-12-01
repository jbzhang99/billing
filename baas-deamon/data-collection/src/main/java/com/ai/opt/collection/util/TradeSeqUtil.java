package com.ai.opt.collection.util;

import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.RandomUtil;

public class TradeSeqUtil {
    
    /**
     * 消息流水号 
     * 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号
     * @param tenantId 租户ID
     * @return 流水号 
     */
    public static String newTradeSeq(String tenantId){
        String tradeSeq = null;
        synchronized (TradeSeqUtil.class) {
            //消息流水 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号
            tradeSeq = tenantId + DateUtil.getDateString("yyMMddHHmmssSSS") + RandomUtil.randomNum(9);
        }
        return tradeSeq;
    }
  
    public static void main(String[] args) {
    	System.out.println(TradeSeqUtil.newTradeSeq("BYD"));	
	}
}
