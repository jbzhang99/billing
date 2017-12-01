package com.ai.baas.amc.api.queryinoutfundserial.params;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

public class QueryFundSerialResponse extends BaseResponse{

    private static final long serialVersionUID = 1L;
    PageInfo<FundSerialInfo> pageInfo;
    public PageInfo<FundSerialInfo> getPageInfo() {
        return pageInfo;
    }
    public void setPageInfo(PageInfo<FundSerialInfo> pageInfo) {
        this.pageInfo = pageInfo;
    }
    
}
