package com.ai.baas.bmc.topology.util;

import com.ai.baas.storm.failbill.FailBillHandler;

import java.util.Map;

/**
 * Created by majun on 2016/4/18.
 */
public class ExceptionUtil {

    static{
        FailBillHandler.startup();
    }

    public static void out(Map<String, String> data, String input, String failCode, String failReason){
        System.out.println("调用错误输出.....");
        if (data != null && data.size() > 0) {
            FailBillHandler.addFailBillMsg(data, BmcConstants.FAIL_STEP, failCode, failReason);
        }else{
            FailBillHandler.addFailBillMsg(input, BmcConstants.FAIL_STEP, failCode, failReason);
        }
    }


}
