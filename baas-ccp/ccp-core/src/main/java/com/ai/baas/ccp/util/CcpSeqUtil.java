package com.ai.baas.ccp.util;

import com.ai.baas.ccp.constants.SeqConstants;
import com.ai.opt.sdk.components.sequence.util.SeqUtil;

/**
 *  seq 工具类
 */
public final class CcpSeqUtil {

    private CcpSeqUtil() {
    
    }

    public static String createNoticeRecordId() {
        return String.valueOf(SeqUtil.getNewId(SeqConstants.NOTICE_RECORD$RECORD_ID$SEQ));
    }

    public static String createNoticeSubscribeRecordSeqId() {
        return String.valueOf(SeqUtil.getNewId(SeqConstants.NOTICE_SUBSCRIBE_RECORD$SEQ_ID$SEQ));
    }

    public static String createStopNoticeLogId() {
        return String.valueOf(SeqUtil.getNewId(SeqConstants.STOP_NOTICE_LOG$LOG_ID$SEQ));
    }

}
