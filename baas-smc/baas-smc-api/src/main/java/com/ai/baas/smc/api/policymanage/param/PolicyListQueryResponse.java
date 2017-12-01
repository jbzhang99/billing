package com.ai.baas.smc.api.policymanage.param;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class PolicyListQueryResponse extends BaseResponse {
    private static final long serialVersionUID = 1L;

    /**
     * 政策列表信息
     */
    PageInfo<PolicyListQueryInfo> pageInfo;

    public PageInfo<PolicyListQueryInfo> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<PolicyListQueryInfo> pageInfo) {
        this.pageInfo = pageInfo;
    }
}
