package com.ai.baas.amc.api.oweinfoquery.param;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

/**
 * 欠费列表查询返回结果
 *
 * Date: 2016年4月13日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class OweInfoListQueryResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /**
     * 欠费列表分页结果
     */
    private PageInfo<OweInfo> pageInfo;

    public PageInfo<OweInfo> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<OweInfo> pageInfo) {
        this.pageInfo = pageInfo;
    }
}
