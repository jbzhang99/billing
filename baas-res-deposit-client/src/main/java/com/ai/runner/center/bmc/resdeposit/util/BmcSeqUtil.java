package com.ai.runner.center.bmc.resdeposit.util;

import com.ai.opt.sdk.components.sequence.util.SeqUtil;

public final class BmcSeqUtil {

    private BmcSeqUtil() {
    
    }

    public static String getResDupLogId() {
        return String.valueOf(SeqUtil.getNewId("RES_DUP_LOG$ID$SEQ"));
    }

    public static String getFailMsgLogId() {
        return String.valueOf(SeqUtil.getNewId("FAIL_MSG_LOG$ID$SEQ"));
    }
}
