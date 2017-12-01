package com.ai.baas.smc.api.sysparammanage.param;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class QuerySysParamResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /**
     * 查询业务参数配置表返回值
     */
    private PageInfo<SysParamInfo> pageInfo;

    public PageInfo<SysParamInfo> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<SysParamInfo> pageInfo) {
        this.pageInfo = pageInfo;
    }

}
