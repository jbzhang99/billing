package com.ai.baas.smc.api.querybillstyle.param;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class QueryBillStyleListVoResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /**
     * 账单样式信息
     */
    PageInfo<QueryBillStyleListVo> pageInfo;

    public PageInfo<QueryBillStyleListVo> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<QueryBillStyleListVo> pageInfo) {
        this.pageInfo = pageInfo;
    }

}
