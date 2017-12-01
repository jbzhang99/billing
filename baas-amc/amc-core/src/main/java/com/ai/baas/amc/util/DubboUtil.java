package com.ai.baas.amc.util;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sys.api.subject.interfaces.ISubjectQuerySV;



/**
 * dubbo服务消费工具类
 *
 * Date: 2015年9月16日 <br>
 * Copyright (c) 2015 asiainfo.com <br>
 * @author fanpw
 */
public final class DubboUtil {

    private DubboUtil() {

    }

    /**
     * 获取科目查询服务
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public static ISubjectQuerySV getSubjectQuerySV() {
        ISubjectQuerySV sv = null;
        try {
            sv = DubboConsumerFactory.getService("subjectQuerySV", ISubjectQuerySV.class);
        } catch (Exception ex) {
            throw new SystemException(ex);
        }
        if (sv == null) {
            throw new SystemException("获取不到远程服务:" + ISubjectQuerySV.class);
        }
        return sv;
    }

}
