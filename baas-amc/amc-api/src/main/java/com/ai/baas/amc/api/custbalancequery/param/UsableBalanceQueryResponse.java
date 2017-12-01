package com.ai.baas.amc.api.custbalancequery.param;

import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;

/**
 * 可用余额分页查询返回结果
 *
 * Date: 2016年4月14日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public class UsableBalanceQueryResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /**
     * 可用余额分页结果
     */
    private PageInfo<UsableBalanceVo> pageInfo;

    public PageInfo<UsableBalanceVo> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<UsableBalanceVo> pageInfo) {
        this.pageInfo = pageInfo;
    }
}
