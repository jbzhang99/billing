package com.ai.baas.smc.service.busi.interfaces;

import com.ai.baas.smc.api.queryimportlog.param.QueryImportLogRequest;
import com.ai.baas.smc.api.queryimportlog.param.QueryImportLogResponse;
import com.ai.opt.base.exception.BusinessException;

public interface IQueryImportLogBusiSV {

    /**
     * 数据日志表查询<br>
     * 
     * @param queryImportLogRequest
     * @return
     * @throws BusinessException
     * @author wangjl9
     * @ApiDocMethod
     */
    QueryImportLogResponse queryImportLog(QueryImportLogRequest queryImportLogRequest)
            throws BusinessException;

}
