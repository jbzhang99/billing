package com.ai.baas.smc.api.queryimportlog.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.baas.smc.api.queryimportlog.interfaces.IQueryImportLogSV;
import com.ai.baas.smc.api.queryimportlog.param.QueryImportLogRequest;
import com.ai.baas.smc.api.queryimportlog.param.QueryImportLogResponse;
import com.ai.baas.smc.service.busi.interfaces.IQueryImportLogBusiSV;
import com.ai.baas.smc.util.BusinessUtil;
import com.ai.opt.base.exception.BusinessException;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class QueryImportLogSVImpl implements IQueryImportLogSV {

    @Autowired
    private IQueryImportLogBusiSV iQueryImportLogBusiSV;

    @Override
    public QueryImportLogResponse queryImportLog(QueryImportLogRequest queryImportLogRequest)
            throws BusinessException {
        BusinessUtil.checkBaseInfo(queryImportLogRequest);
        return iQueryImportLogBusiSV.queryImportLog(queryImportLogRequest);
    }

}
