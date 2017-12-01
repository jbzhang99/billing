package com.ai.opt.sys.api.citicrestcommon.param;

import com.ai.opt.base.vo.BaseResponse;

import java.util.List;

/**
 * 中信机构查询管理接口查询响应
 *
 * Date: 2016年7月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
public class OrgQueryResponse extends BaseResponse{
    /**
     * 租户组织机构列表
     */
    private List<OrgInfo> orgs;

    public List<OrgInfo> getOrgs() {
        return orgs;
    }

    public void setOrgs(List<OrgInfo> orgs) {
        this.orgs = orgs;
    }
}
