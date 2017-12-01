package com.ai.baas.amc.service.business.interfaces;

import com.ai.baas.amc.api.oweinfoquery.param.OweDetailInfoQueryRequest;
import com.ai.baas.amc.api.oweinfoquery.param.OweDetailInfoQueryResponse;
import com.ai.baas.amc.api.oweinfoquery.param.OweInfo;
import com.ai.baas.amc.api.oweinfoquery.param.OweInfoListQueryRequest;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;

/**
 * 欠费查询服务业务定义类
 *
 * Date: 2016年4月14日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public interface IOweInfoQueryBusiSV {

    PageInfo<OweInfo> queryOweInfoList(OweInfoListQueryRequest request) throws BusinessException;

    OweDetailInfoQueryResponse queryOweDetailInfo(OweDetailInfoQueryRequest request)
            throws BusinessException;
}
