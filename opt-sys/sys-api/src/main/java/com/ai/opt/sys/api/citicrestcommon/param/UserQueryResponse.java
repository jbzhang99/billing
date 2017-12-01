package com.ai.opt.sys.api.citicrestcommon.param;

import com.ai.opt.base.vo.BaseResponse;

import java.util.List;

/**
 * 中信用户查询管理接口查询响应
 *
 * Date: 2016年7月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
public class UserQueryResponse extends BaseResponse{
    /**
     * 用户列表
     */
    private List<UserInfo> users;

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }
}
