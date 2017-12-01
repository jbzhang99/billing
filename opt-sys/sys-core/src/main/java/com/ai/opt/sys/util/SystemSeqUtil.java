package com.ai.opt.sys.util;

import com.ai.opt.sdk.components.sequence.util.SeqUtil;
import com.ai.opt.sys.constants.SysConstants.SEQ;

/**
 * 系统ID生成服务
 * Date: 2016年4月8日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author zhanglh
 */
public final class SystemSeqUtil {

    private SystemSeqUtil() {
    }


/**
 * 生成功能Id
 * @return
 * @author zhanglh
 * @ApiCode
 */
    public static long createFuncId() {
        return SeqUtil.getNewId(SEQ.FUNC_ID_SEQ);

    }

    /**
     * 生成功能Id
     * @return
     * @author wangyx13
     * @ApiCode
     */
    public static long createMailId() {
        return SeqUtil.getNewId(SEQ.MAIL_ID_SEQ);

    }
}
