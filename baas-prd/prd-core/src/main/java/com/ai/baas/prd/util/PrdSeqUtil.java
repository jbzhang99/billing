package com.ai.baas.prd.util;

import com.ai.baas.prd.constants.PrdConstants.SEQ;
import com.ai.opt.sdk.components.sequence.util.SeqUtil;

/**
 * seq 工具类
 * @author wangluyang
 *
 */
public final class PrdSeqUtil {

    private PrdSeqUtil() {
    
    }
    
    public static Long getPolicyInfoId() {
        return Long.valueOf(SeqUtil.getNewId(SEQ.PM_POLICY_INFO$POLICY_ID$SEQ,10));
    }

    public static Long getPolicyVarId() {
        return Long.valueOf(SeqUtil.getNewId(SEQ.PM_POLICY_VARIABLE$ID$SEQ,10));
    }

    public static Long getPolicyDetailId() {
        return Long.valueOf(SeqUtil.getNewId(SEQ.PM_POLICY_DETAIL$ID$SEQ,10));
    }
    
    public static Long getPolicyFactorId() {
        return Long.valueOf(SeqUtil.getNewId(SEQ.PM_POLICY_FACTOR$ID$SEQ,10));
    }
    
    /**
     * 维度信息表id维护
     * @return
     * @author gaogang
     */
    public static Long getDimensionId() {
        return Long.valueOf(SeqUtil.getNewId(SEQ.PM_DIMENSION_INFO$ID$SEQ,10));
    }
    /**
     * 维度分支表
     * @return
     * @author gaogang
     */
    public static Long getBranchnId() {
        return Long.valueOf(SeqUtil.getNewId(SEQ.PM_DIMENSION_BRANCH$ID$SEQ,10));
    }
    /**
     * 产品类目信息表
     * @return
     * @author gaogang
     */
    public static Long getCategoryInfoId() {
        return Long.valueOf(SeqUtil.getNewId(SEQ.PM_CATEGORY_INFO$ID$SEQ,10));
    }
}
