package com.ai.baas.omc.util;

import com.ai.baas.omc.constants.TableCon;
import com.ai.opt.sdk.components.sequence.util.SeqUtil;

/**
 * OMC seq 工具类
 *
 * Date: 2017年3月2日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * @author wangjing19
 */
public final class OmcSeqUtil {

    private OmcSeqUtil() {
    
    }

    public static String getAvoidSeq() {
        return String.valueOf(SeqUtil.getNewId(TableCon.OMC_AVOIDSCOUT$AVOID_SEQ));
    }

}
